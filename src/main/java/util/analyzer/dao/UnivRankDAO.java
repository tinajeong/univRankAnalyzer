package main.java.util.analyzer.dao;

import main.java.data.UnivRank;
import main.java.data.UnivRank_;
import main.java.data.dto.UnivRankDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UnivRankDAO {
    private SessionFactory sessionFactory;
    private List<UnivRankDTO> univRankDTOList;
    private final static Logger logger = LoggerFactory.getLogger(UnivRankDAO.class);

    public UnivRankDAO() {
        sessionFactory = new Configuration().configure()
                .buildSessionFactory();
    }

    public UnivRankDAO(List<UnivRankDTO> univRankDTOList) {
        this();
        this.univRankDTOList = univRankDTOList;
    }

    public void accessDB() {
        try {
            persist(sessionFactory);
            load(sessionFactory);
        } finally {
            sessionFactory.close();
        }
    }

    public void read() {
        try {
            load(sessionFactory);
        } finally {
            sessionFactory.close();
        }
    }

    private void load(SessionFactory sessionFactory) {
        logger.info("=======loading univ ranks=======");

        Session session = sessionFactory.openSession();

        @SuppressWarnings("unchecked")
        List<UnivRank> persons = session.createQuery("FROM UnivRank").list();
        persons.forEach((x) -> logger.info("{}", x));

        session.close();
    }

    private void persist(SessionFactory sessionFactory) {
        logger.info("=======persisting univ ranks=======");

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        for (UnivRankDTO univRankDTO : univRankDTOList) {
//            CriteriaQuery supports type-safe
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<UnivRank> criteriaQuery = criteriaBuilder.createQuery(UnivRank.class);
            Root<UnivRank> root = criteriaQuery.from(UnivRank.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(UnivRank_.univName), univRankDTO.getUnivName()));
            UnivRank univRankTemp = session.createQuery(criteriaQuery).uniqueResult();

            UnivRank univRank = new UnivRank();
            int tempRank =Math.toIntExact(univRankDTO.getRank());
            if ((univRankTemp == null)) {
                univRank.setUnivName(univRankDTO.getUnivName());
                univRank.setCountry(univRankDTO.getCountry());
                univRank.setRank(tempRank);
                session.save(univRank);
            } else {
                updateRank(univRankTemp.getRank(),tempRank);
            }

        }

        session.getTransaction().commit();
        logger.info("=======successfully saved=======");
    }
    private int updateRank(int oldRank, int newRank)
    {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        int updatedEntities = session.createQuery(
                "update UnivRank as ur\n" +
                        "set ur.rank= :newRank\n" +
                        "where ur.rank = :oldRank" )
                .setParameter( "oldRank", oldRank )
                .setParameter( "newRank", newRank )
                .executeUpdate();

        session.getTransaction().commit();

        return updatedEntities;
    }
    public List<UnivRankDTO> getUnivRankDTOList() {
        return univRankDTOList;
    }

    public void setUnivRankDTOList(List<UnivRankDTO> univRankDTOList) {
        this.univRankDTOList = univRankDTOList;
    }
}
