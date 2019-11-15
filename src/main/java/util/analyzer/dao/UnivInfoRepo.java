package main.java.util.analyzer.dao;

import main.java.data.UnivInfo;
import main.java.data.UnivRank;
import main.java.data.dto.UnivInfoDTO;
import main.java.data.dto.UnivRankDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UnivInfoRepo {
    SessionFactory sessionFactory;
    List<UnivInfoDTO> univInfoDTOList;
    List<UnivRankDTO> univRankDTOList;
    private final static Logger logger = LoggerFactory.getLogger(UnivInfoRepo.class);

    public UnivInfoRepo() {
        sessionFactory = new Configuration().configure()
                .buildSessionFactory();
    }

    public UnivInfoRepo(List<UnivInfoDTO> univInfoDTOList) {
        this();
        this.univInfoDTOList = univInfoDTOList;
    }

    public UnivInfoRepo(List<UnivInfoDTO> univInfoDTOList, List<UnivRankDTO> univRankDTOList) {
        this();
        this.univInfoDTOList = univInfoDTOList;
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
        logger.info("=======loading univ info=======");
        Session session = sessionFactory.openSession();
        @SuppressWarnings("unchecked")
        List<UnivInfo> persons = session.createQuery("FROM UnivInfo").list();
        persons.forEach((x) -> logger.info("{}", x));
        session.close();
    }

    public void getAddresses() {
        logger.info("=======loading univ info-address=======");
        Session session = sessionFactory.openSession();
        @SuppressWarnings("unchecked")
        List<UnivInfo> persons = session.createQuery("FROM UnivInfo as info WHERE info.address is not null").list();
        persons.forEach((x) -> logger.info("{}", x));
        session.close();
    }

    private void persist(SessionFactory sessionFactory) {
        logger.info("=======persisting univ info=======");

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        for (UnivInfoDTO univInfoDTO : univInfoDTOList) {
            UnivInfo univInfo = new UnivInfo();

            if (univInfoDTO.getAddress() != null) {
                univInfo.setAddress(univInfoDTO.getAddress());
            } else {
                univInfo.setAddress("");
            }
            if (univInfoDTO.getWebsite() != null) {
                univInfo.setWebsite(univInfoDTO.getWebsite());
            } else {
                univInfo.setWebsite("");
            }
            if (univInfoDTO.getSummary() != null) {
                univInfo.setSummary(univInfoDTO.getSummary());
            } else {
                univInfo.setSummary("");
            }
            Query query = session.createQuery("from UnivRank as ur where ur.univName=:univName");
            query.setParameter("univName", univInfoDTO.getName());
            UnivRank univRank = (UnivRank) query.uniqueResult();
            if (univRank != null)
                univInfo.setUnivRank(univRank);
            else {
                logger.error("univ rank information is not found");
                continue;
            }

            Query InfoQuery = session.createQuery("from UnivInfo as ur where ur.univRank.univName= :univName");
            InfoQuery.setParameter("univName",univRank.getUnivName());
            UnivInfo univInfo2 = (UnivInfo) InfoQuery.uniqueResult();

            UnivRank univRank2 = univInfo2.getUnivRank();
            if(univRank2!=null)
                //TODO refactoring in update statements
                continue;
            session.save(univInfo);

        }
        session.getTransaction().commit();
        logger.info("=======successfully saved=======");


    }
}
