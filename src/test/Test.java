
package test;

import hibernate.Employee;
import hibernate.Utility;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Test {
    public static void main(String[] args) {
        Employee emp = new Employee();
        emp.setId(0);
        emp.setName("Jamy");
        emp.setSalary(100000);
        
        SessionFactory sessionFactory = Utility.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(emp);
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }
}
