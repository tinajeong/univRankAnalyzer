package main.java.util.analyzer.dao;

import main.java.data.UnivRank;
import main.java.data.UnivRank_;
import main.java.data.dto.UnivRankDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UnivRankRepo {
    SessionFactory sessionFactory;
    List<UnivRankDTO> univRankDTOList;
    private final static Logger logger = LoggerFactory.getLogger(UnivRankRepo.class);

    public UnivRankRepo() {
        sessionFactory = new Configuration().configure()
                .buildSessionFactory();
    }

    public UnivRankRepo(List<UnivRankDTO> univRankDTOList) {
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

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<UnivRank> criteriaQuery = criteriaBuilder.createQuery(UnivRank.class);
            Root<UnivRank> root = criteriaQuery.from(UnivRank.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(UnivRank_.univName), univRankDTO.getUnivName()));
            UnivRank univRankTemp = session.createQuery(criteriaQuery).uniqueResult();

            UnivRank univRank = new UnivRank();
            if ((univRankTemp == null)) {
                univRank.setUnivName(univRankDTO.getUnivName());
                univRank.setCountry(univRankDTO.getCountry());
                univRank.setRank(Math.toIntExact(univRankDTO.getRank()));
                session.save(univRank);
            } else {
                //TODO refactoring in update statments
                logger.info("univname is already exists");
                continue;
            }

        }

        session.getTransaction().commit();
        logger.info("=======successfully saved=======");
    }

    public List<UnivRankDTO> getUnivRankDTOList() {
        return univRankDTOList;
    }

    public void setUnivRankDTOList(List<UnivRankDTO> univRankDTOList) {
        this.univRankDTOList = univRankDTOList;
    }
}
