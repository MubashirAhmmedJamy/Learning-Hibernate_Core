package test;

import hibernate.Employee;
import hibernate.Utility;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class CriteriaAPItest {
    public static void main(String[] args) {
        SessionFactory sessionFactory = Utility.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        System.out.println("\n\nFetching all records::\n\n");
        
        Criteria criteria = session.createCriteria(Employee.class);
        
        List<Employee> list = criteria.list();
        
        for(Employee e: list){
            System.out.println(e.getName() + " | " + e.getEd().getExperience());
        }
        
        
        System.out.println("Fetching all records with restriction / condition id is between 4 and 10 inclusive");
        criteria = session.createCriteria(Employee.class);
        criteria.add(Restrictions.between("id", 4, 10));
        
        list = criteria.list();
        
        for(Employee e: list){
            System.out.println(e.getId()+ " | " +e.getName() + " | " + e.getEd().getExperience());
        }
        
        
        System.out.println("\n\nFetching with multiple OR Restrictions::\n\n");

        criteria = session.createCriteria(Employee.class);
        
        criteria.add(Restrictions.or(Restrictions.like("name", "Noman-63%"),Restrictions.lt("salary", 21200000.0))).add(Restrictions.gt("id", 9));
        list = criteria.list();
        
        for(Employee e: list){
            System.out.println(e.getId()+ " | " +e.getName() + " | " + e.getEd().getExperience()+ " | " + e.getSalary());
        }
        
        
        System.out.println("\n\nFetching selected properties with Projections::\n\n");

        criteria = session.createCriteria(Employee.class);
        
        ProjectionList projectionList = Projections.projectionList();

        projectionList.add(Projections.property("id"));
        projectionList.add(Projections.property("name"));
        projectionList.add(Projections.property("salary"));
//      projectionList.add(Projections.property("ed").as("ed"));
        
        criteria.setProjection(projectionList);
        
        criteria.add(Restrictions.or(Restrictions.like("name", "Noman-63%"),Restrictions.lt("salary", 21200000.0))).add(Restrictions.gt("id", 9));
        
        
        List<Object[]> l = criteria.list();
        
        
        System.out.println(l.get(0).length);
        
        l.forEach((e) -> {
            System.out.println(e[1]+ " | " +e[0] + " | " + e[2]);
        });        
    }
}
