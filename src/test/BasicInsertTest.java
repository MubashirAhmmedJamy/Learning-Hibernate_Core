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

public class BasicInsertTest {

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
        session.beginTransaction(); //Must begin transaction before saving each object
        
        for (int i = 1311; i <= 1320; i++) {
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
            
            
            
            session.save(emp);
            
            
            session.save(a1);
            session.save(a2);

        }
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }
}
