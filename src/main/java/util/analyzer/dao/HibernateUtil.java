package main.java.util.analyzer.dao;

import main.java.data.UnivRank;
import main.java.data.dto.UnivRankDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HibernateUtil {
    SessionFactory sessionFactory;
    List<UnivRankDTO> univRankDTOList;
    private final static Logger logger = LoggerFactory.getLogger(HibernateUtil.class);

    public HibernateUtil() {
        sessionFactory = new Configuration().configure()
                .buildSessionFactory();
    }

    public HibernateUtil(List<UnivRankDTO> univRankDTOList) {
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
            Query query = session.createQuery("from UnivRank as ur where ur.univName=:univName");
            query.setParameter("univName",univRankDTO.getUnivName());
            UnivRank univRankTemp =(UnivRank)query.uniqueResult();
            UnivRank univRank = new UnivRank();
            if((univRankTemp==null)) {
                univRank.setUnivName(univRankDTO.getUnivName());
                univRank.setCountry(univRankDTO.getCountry());
                univRank.setRank(Math.toIntExact(univRankDTO.getRank()));
                session.save(univRank);
            }
            else{
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
