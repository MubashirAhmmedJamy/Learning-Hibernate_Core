package test;

import hibernate.Address;
import hibernate.Employee;
import hibernate.Utility;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class HQLnamedQueryTest {
    public static void main(String args[]){
        SessionFactory sf = Utility.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();


//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++        
        System.out.println("Selecting all records::\n\n");
        
        Query q = s.getNamedQuery("one");
        List<Employee> l = q.list();
//        s.getTransaction().commit();

        for(Employee e : l){
//            Collection<Address> c = e.getA();
//            Iterator i = c.iterator();
             Iterator<Address> i = e.getA().iterator();
             
//            Address a = (Address)i.next();
//            Address b = (Address)i.next();
//
//            System.out.println("Employee "+e.getName()+" lives in "+a.getEmp_address()+" and "+b.getEmp_address());
            System.out.println("Employee "+e.getName()+" lives in "+i.next().getEmp_address()+" and "+i.next().getEmp_address());
        }
        
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        
        System.out.println("\n\nParameter binding by position::\n");
        
        q = s.getNamedQuery("two");
        q.setInteger(100, 5);
//        q.setParameter(100, l);
        
        List<Employee> k = q.list();
        
        for(Employee e : k){
            System.out.println("Employee " +e.getId() +" | "+e.getName() +" | "+e.getEd().getExperience() + " | " + e.getA().iterator().next().getEmp_address());
        }

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


        System.out.println("\n\nSelecting all records with limited fields::\n\n");
        q = s.getNamedQuery("three");
        q.setFirstResult(3);
        q.setMaxResults(5);

//        s.beginTransaction();
        List<Object[]> m = q.list();

        for (Object e[] : m) {
            System.out.println("Employee " + (String) e[0] + " | " + (Double) e[1]);
        }
        
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


        System.out.println("\n\nParameter binding by name::\n\n");
        
        q = s.getNamedQuery("four");
        
//        q.setInteger("foo", 5);
//        q.setDouble("range", 21300000);
        
        q.setParameter("foo", 5);
        q.setParameter("range", 21300000.0);
        
        m = q.list();
        
        for (Object e[] : m) {
            System.out.println("Employee " + (Integer) e[0] + " | "+ (String) e[1] + " | " + (Double) e[2]);
        }
        
        s.getTransaction().commit();
        s.close();
        sf.close();     
    }
}
