package test;

import hibernate.Employee;
import hibernate.Utility;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Test {

    public static void main(String[] args) {
        /*
        - Create SessionFactory object from hibernate utility class
        - Create Session object from SessionFactory
        - Begin transaction from session
        - Save the target object using session
        - Get the transaction from session and commit it
        - Close the session and session factory resources
        
         */
        SessionFactory sessionFactory = Utility.getSessionFactory();
        Session session = sessionFactory.openSession();

        
//        for (int i = 211; i <= 220; i++) {
//            Employee emp = new Employee();
////            emp.setId(i*7*2);
//            emp.setName("Noman-"+i*15*2);
//            emp.setSalary(100000*i);
//            emp.setJoining_date(new Date());
//            session.beginTransaction(); //Must begin transaction before saving each object
//            session.save(emp);
//            session.getTransaction().commit();
//        }

        System.out.println("Getting the employee with id 14:");
        Employee e = (Employee) session.get(Employee.class, 14);
        System.out.println("Retrieved directly from database: "+e.getName()+" | "+e.getSalary());

        System.out.println("New name is being set for the first time:");
        e.setName("SumiJamySumi-6450");
        System.out.println("Session update called:");
        session.update(e);
        System.out.println("Transaction starting:");
        session.beginTransaction();
        System.out.println("Transaction commiting:");
        session.getTransaction().commit();
        
        System.out.println("Getting the employee with id 14:");
        e = (Employee) session.get(Employee.class, 14);
        System.out.println("Details after update: " + e.getName() + " | " + e.getSalary());
        
        System.out.println("Changing the Name again and salary:");
        e.setSalary(100000);
        e.setName("JamySumiJamy-6450");
        System.out.println("Session update calling again");
        session.update(e);
        System.out.println("Transaction starting again:");
        session.beginTransaction();
        System.out.println("Transaction commiting again:");
        session.getTransaction().commit();
        
        e = (Employee) session.get(Employee.class, 14);
        System.out.println("Details after updating where id =14: " + e.getName() + " | " + e.getSalary());

        session.close();
        sessionFactory.close();
    }
}
