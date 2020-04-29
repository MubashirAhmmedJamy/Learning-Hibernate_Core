package test;

import hibernate.Utility;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HQLupdateDeleteTest {
    public static void main(String args[]){
        SessionFactory sessionFactory = Utility.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        Query query = session.createQuery("UPDATE Employee SET salary = :newSal WHERE id = :empId");
        query.setParameter("newSal", 600000.00);
        query.setParameter("empId", 40);
        query.executeUpdate();
        
        
        query = session.createQuery("DELETE FROM Employee WHERE id = :empId");
        query.setParameter("empId", 6);
        query.executeUpdate();
        
//        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }
}
