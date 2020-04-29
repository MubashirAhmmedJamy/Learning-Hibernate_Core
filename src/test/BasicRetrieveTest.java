package test;

import hibernate.Employee;
import hibernate.Utility;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class BasicRetrieveTest {

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
        
        
        session.beginTransaction();

        System.out.println("1. Getting the employee with id 40:");
        Employee e = (Employee) session.get(Employee.class, 40);
        System.out.println("Retrieved directly from database: "+e.getId()+" | "+e.getName()+" | "+e.getSalary()+" | "+e.getEd().getExpertise()+" | "+e.getEd().getExperience());

        
        
        System.out.println("\n\n\nNew name is being set for the first time:");
        e.setName("Sumi-7450");
        System.out.println("Session update called:");
        session.update(e);
        

        
        System.out.println("2. Getting the employee with id 40:");
        e = (Employee) session.get(Employee.class, 40);
        System.out.println("1. Details after update: "+e.getId()+" | "+e.getName()+" | "+e.getSalary()+" | "+e.getEd().getExpertise()+" | "+e.getEd().getExperience());
        System.out.println("Transaction commiting:");
        session.getTransaction().commit();
        
        
        
        session.beginTransaction();
        System.out.println("\n\n\nChanging the Name again and salary:");
        e.setSalary(20000);
        e.setName("SumiJamy-6450");
        System.out.println("Session update calling again");
        session.update(e);
        
        System.out.println("3. Getting the employee with id 40:");
        e = (Employee) session.get(Employee.class, 40);
        System.out.println("2. Details after update: "+e.getId()+" | "+e.getName()+" | "+e.getSalary()+" | "+e.getEd().getExpertise()+" | "+e.getEd().getExperience());
        
        System.out.println("Transaction commiting:");        
        session.getTransaction().commit();

        session.close();
        sessionFactory.close();
    }
}
