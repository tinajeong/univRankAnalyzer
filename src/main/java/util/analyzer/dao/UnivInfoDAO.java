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

public class UnivInfoDAO {
    private List<UnivInfoDTO> univInfoDTOList;
    private List<UnivRankDTO> univRankDTOList;
    private final static Logger logger = LoggerFactory.getLogger(UnivInfoDAO.class);

    private int updateUnivInfo(UnivInfo oldInfo, UnivInfo newInfo) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        int updatedEntities = session.createQuery(
                "update UnivInfo as ui\n" +
                        "set ui.address= :newAddress\n" +
                        ", ui.website= :newWebsite\n" +
                        ", ui.summary= :newSummary\n" +
                        "where ui.address = :oldAddress\n" +
                        "and ui.website = :oldWebsite\n" +
                        "and ui.summary = :oldSummary")
                .setParameter("newAddress", newInfo.getAddress())
                .setParameter("newWebsite", newInfo.getWebsite())
                .setParameter("newSummary", newInfo.getSummary())
                .setParameter("oldAddress", oldInfo.getAddress())
                .setParameter("oldWebsite", oldInfo.getWebsite())
                .setParameter("oldSummary", oldInfo.getSummary())
                .executeUpdate();

        session.getTransaction().commit();
        return updatedEntities;
    }

    public UnivInfoDAO() {
    }

    public UnivInfoDAO(List<UnivInfoDTO> univInfoDTOList) {
        this();
        this.univInfoDTOList = univInfoDTOList;
    }

    public UnivInfoDAO(List<UnivInfoDTO> univInfoDTOList, List<UnivRankDTO> univRankDTOList) {
        this();
        this.univInfoDTOList = univInfoDTOList;
        this.univRankDTOList = univRankDTOList;
    }

    public void storeAndRead() {
            persist();
            load();
    }

    public void read() {
            load();
    }
    public void getAddresses() {
        try {
            logger.info("=======loading univ info-address=======");
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            @SuppressWarnings("unchecked")
            List<UnivInfo> persons = session.createQuery("FROM UnivInfo as info WHERE info.address is not null").list();
            persons.forEach((x) -> logger.info("{}", x));
            session.close();
        } finally {
            HibernateSessionFactory.getSessionFactory().close();
        }
    }

    public void getAddresses(String searchTerm) {
        try {
            logger.info("=======loading univ info-address=======");
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            @SuppressWarnings("unchecked")
            Query peopleQuery = session.createQuery("FROM UnivInfo as info WHERE info.address like ?1");
            peopleQuery.setParameter(1, "%" + searchTerm + "%");;
            List<UnivInfo> personsList = peopleQuery.list();
            personsList.forEach((x) -> logger.info("{}", x));
            session.close();
        } finally {
            HibernateSessionFactory.getSessionFactory().close();
        }
    }
    private void load() {
        logger.info("=======loading univ info=======");
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        @SuppressWarnings("unchecked")
        List<UnivInfo> persons = session.createQuery("FROM UnivInfo").list();
        logger.info("number of rows: {}",persons.size());
        persons.forEach((x) -> logger.info("{}", x));
        session.close();
    }

    private void persist() {
        logger.info("=======persisting univ info=======");

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        for (UnivInfoDTO univInfoDTO : univInfoDTOList) {
            UnivInfo univInfo = new UnivInfo();

            if (univInfoDTO.getAddress() != null)
                univInfo.setAddress(univInfoDTO.getAddress());

            if (univInfoDTO.getWebsite() != null)
                univInfo.setWebsite(univInfoDTO.getWebsite());

            if (univInfoDTO.getSummary() != null)
                univInfo.setSummary(univInfoDTO.getSummary());

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
            InfoQuery.setParameter("univName", univRank.getUnivName());
            UnivInfo univInfoTemp = (UnivInfo) InfoQuery.uniqueResult();
            UnivRank univRankTemp = univInfoTemp.getUnivRank();
            if (univRankTemp != null) {
                updateUnivInfo(univInfoTemp, univInfo);
            } else
                session.save(univInfo);

        }
        session.getTransaction().commit();
        logger.info("=======successfully saved=======");


    }

}
