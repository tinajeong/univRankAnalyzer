package main.java.util.analyzer.dao;

import main.java.data.UnivInfo;
import main.java.data.dto.UnivInfoDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UnivInfoRepo {
    SessionFactory sessionFactory;
    List<UnivInfoDTO> univInfoDTOList;
    private final static Logger logger = LoggerFactory.getLogger(UnivInfoRepo.class);

    public UnivInfoRepo() {
        sessionFactory = new Configuration().configure()
                .buildSessionFactory();
    }

    public UnivInfoRepo(List<UnivInfoDTO> univInfoDTOList) {
        this();
        this.univInfoDTOList = univInfoDTOList;
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

    private void persist(SessionFactory sessionFactory) {
        logger.info("=======persisting univ info=======");

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        for (UnivInfoDTO univInfoDTO : univInfoDTOList) {
            UnivInfo univInfo = new UnivInfo();
            univInfo.setName(univInfoDTO.getName());
            univInfo.setAddress(univInfoDTO.getAddress());
            univInfo.setWebsite(univInfoDTO.getWebsite());
            univInfo.setSummary(univInfoDTO.getSummary());
            session.save(univInfo);
        }
        session.getTransaction().commit();
        logger.info("=======successfully saved=======");


    }
}
