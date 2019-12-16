import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.*;

public class TestHibernate {

    private static SessionFactory factory;

    public static void main(String[] args) {

        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        // COUNT
        System.out.println("Wyświetlenie liczby win w tabeli:");
        showQueryResults("SELECT COUNT(w) FROM Wino w");
        System.out.println();

//        System.out.println("Wyświetlenie procentu win z Francji i Chorwacji:");
//        String hql1 = "SELECT COUNT(id) FROM Wino WHERE krajPochodzenia IN :countryNames";
//        Query qIN = session.createQuery(hql1);
//        List<String> krajeWin = new ArrayList<>();
//        krajeWin.add("Francja");
//        krajeWin.add("Chorwacja");
//        qIN.setParameter("countryNames", krajeWin);
//        List listIN = qIN.list();
//        Long result1 = (Long) listIN.get(0);
//
//        String hql2 = "SELECT COUNT(id) FROM Wino";
//        Long result2 = (Long) session.createQuery(hql2).getSingleResult();
//
//        System.out.printf("%d/%d = %.2f%%\n\n", result1, result2, (100.0 * result1 / result2));
//
//        System.out.println("Użycie LIKE");
//        String hql3 = "SELECT nazwa FROM wina WHERE nazwa LIKE :wineName";
//        Query qLIKE = session.createSQLQuery(hql3)
//                .addScalar("nazwa",StandardBasicTypes.STRING)
////                https://docs.jboss.org/hibernate/orm/4.3/javadocs/org/hibernate/type/StandardBasicTypes.html
//                .setParameter("wineName", "%to%");
//        List listLIKE = qLIKE.list();
//        for (int i = 0; i < listLIKE.size(); i++)
//            System.out.println(listLIKE.get(i));
//
//        System.out.println();
//
//        // widok - wyswietlenie wersja 1
//        System.out.println("Widok czerwone wina - wyswietlenie wersja 1");
//        List<CzerwoneWinoWidok> widok = session.createQuery("FROM CzerwoneWinoWidok", CzerwoneWinoWidok.class).getResultList();
//        System.out.println(widok.toString());
//        System.out.println();
//        // widok - wyswietlenie wersja 2
//        System.out.println("Widok czerwone wina - wyswietlenie wersja 2");
//        showQueryResults("FROM CzerwoneWinoWidok");
//        System.out.println();
//
//        // UPDATE
//        String qryString = "update Wino w set w.nazwa ='NIEZNANE WINO' where w.nazwa ='Beaujolais'";
//        Query query = session.createQuery(qryString);
//        int count = query.executeUpdate();
        System.out.println("Wyświetlenie wszystkich win:");
//        System.out.println(count + " Record(s) Updated.");
        showQueryResults("FROM Wino");

        session.getTransaction().commit();
        session.close();


    }


    public static void showQueryResults(String queryString) {

        Session session = factory.openSession();
        Query query = session.createQuery(queryString);
        List list = query.list();

        for (int i = 0; i < list.size(); i++)
            System.out.println(list.get(i));

    }
}
