package main.java.util.analyzer.dao;

import main.java.data.UnivRank;
import main.java.data.UnivRankDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HibernateUtil {
    SessionFactory sessionFactory;
    List<UnivRankDTO> univRankDTOList;

    public HibernateUtil() {
    }

    public HibernateUtil(List<UnivRankDTO> univRankDTOList) {
        this.univRankDTOList = univRankDTOList;
    }

    public void accessDB()
    {
        sessionFactory = new Configuration().configure()
                .buildSessionFactory();
        try {
            persist(sessionFactory);
            load(sessionFactory);
        } finally {
            sessionFactory.close();
        }
    }


    private void load(SessionFactory sessionFactory) {
        System.out.println("=======loading univ ranks=======");
        Session session = sessionFactory.openSession();
        @SuppressWarnings("unchecked")
        List<UnivRank> persons = session.createQuery("FROM UnivRank").list();
        persons.forEach((x) -> System.out.printf("- %s%n", x));
        session.close();
    }

    private void persist(SessionFactory sessionFactory) {
        System.out.println("=======persisting univ ranks=======");

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        for(UnivRankDTO univRankDTO:univRankDTOList) {
            UnivRank univRank =new UnivRank();
            univRank.setUnivName(univRankDTO.getUnivName());
            univRank.setCountry(univRankDTO.getCountry());
            univRank.setRank(Math.toIntExact(univRankDTO.getRank()));
            session.save(univRank);
        }
        session.getTransaction().commit();
        System.out.println("=======successfully saved=======");
    }

    public List<UnivRankDTO> getUnivRankDTOList() {
        return univRankDTOList;
    }

    public void setUnivRankDTOList(List<UnivRankDTO> univRankDTOList) {
        this.univRankDTOList = univRankDTOList;
    }
}
