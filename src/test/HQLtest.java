package test;

import hibernate.Address;
import hibernate.Employee;
import hibernate.Utility;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class HQLtest {
    public static void main(String args[]){
        SessionFactory sf = Utility.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();


        Query q = s.createQuery("from Employee");
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
        
        System.out.println("\n\nSelecting limited fields::\n\n");
        q = s.createQuery("select name,salary from Employee");
        q.setFirstResult(3);
        q.setMaxResults(5);
        
//        s.beginTransaction();
        List<Object[]> m = q.list();

        for(Object e[] : m){
            System.out.println("Employee "+(String)e[0]+" | "+(Double)e[1]);
        }
        
        
        System.out.println("\n\nParameter binding::\n\n");
        
        q = s.createQuery("select id,name,salary from Employee where id > :foo and salary > :range");
        
        q.setInteger("foo", 5);
        q.setDouble("range", 21300000);
        
        m = q.list();
        
        for (Object e[] : m) {
            System.out.println("Employee " + (Integer) e[0] + " | "+ (String) e[1] + " | " + (Double) e[2]);
        }
        
        s.getTransaction().commit();
        s.close();
        sf.close();     
    }
}
