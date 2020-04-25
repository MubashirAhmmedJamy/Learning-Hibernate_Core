package test;

import hibernate.Address;
import hibernate.Employee;
import hibernate.EmployeeDetails;
import hibernate.Utility;
import java.util.ArrayList;
import java.util.Collection;
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

        
        for (int i = 211; i <= 220; i++) {
            Employee emp = new Employee();
            
            Address a1 = new Address();
            a1.setEmp_address("Dhaka");
            
            Address a2 = new Address();
            a2.setEmp_address("Narayanganj");
            
            Collection<Address> list = new ArrayList<Address>();
            list.add(a1);
            list.add(a2);
            
            emp.setA(list);
            
            
            EmployeeDetails ed = new EmployeeDetails();
            ed.setExperience(i);
            ed.setExpertise("Java");
            emp.setEd(ed);

//            emp.setId(i*7*2);

            emp.setName("Noman-"+i*15*2);
            emp.setSalary(100000*i);
            emp.setJoining_date(new Date());
            
            
            session.beginTransaction(); //Must begin transaction before saving each object
            session.save(emp);
            
            
            session.save(a1);
            session.save(a2);


            session.getTransaction().commit();
        }

//        System.out.println("Getting the employee with id 9:");
//        Employee e = (Employee) session.get(Employee.class, 9);
//        System.out.println("Retrieved directly from database: "+e.getName()+" | "+e.getSalary()+" | "+e.getEd().getExpertise()+" | "+e.getEd().getExperience());
//
//        System.out.println("New name is being set for the first time:");
//        e.setName("SumiJamySumi-6450");
//        System.out.println("Session update called:");
//        session.update(e);
//        System.out.println("Transaction starting:");
//        session.beginTransaction();
//        System.out.println("Transaction commiting:");
//        session.getTransaction().commit();
//        
//        System.out.println("Getting the employee with id 9:");
//        e = (Employee) session.get(Employee.class, 9);
//        System.out.println("Details after update: " + e.getName() + " | " + e.getSalary()+" | "+e.getEd().getExpertise()+" | "+e.getEd().getExperience());
//        
//        System.out.println("Changing the Name again and salary:");
//        e.setSalary(100000);
//        e.setName("JamySumiJamy-6450");
//        System.out.println("Session update calling again");
//        session.update(e);
//        System.out.println("Transaction starting again:");
//        session.beginTransaction();
//        System.out.println("Transaction commiting again:");
//        session.getTransaction().commit();
//        
//        e = (Employee) session.get(Employee.class, 9);
//        System.out.println("Details after updating where id =9: " + e.getName() + " | " + e.getSalary()+" | "+e.getEd().getExpertise()+" | "+e.getEd().getExperience());

        session.close();
        sessionFactory.close();
    }
}
