package hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.Parameter;


@Entity
@Table(name = "Employee")
@NamedQuery(name = "one", query = "from Employee")
@NamedQuery(name = "two", query = "from Employee where id > ?100")
@NamedQuery(name = "three", query = "select name,salary from Employee")
@NamedQuery(name = "four", query = "select id,name,salary from Employee where id > :foo and salary > :range")

public class Employee implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "gen")
    @GenericGenerator(name = "gen", strategy = "foreign",parameters = {@Parameter(value = "ed", name = "property")})
    private int id;
    
    
//    @Id
    @Column(name = "name", nullable = false)
    private String name;
    
    private double salary;

    @Transient
    private String this_is_not_a_Column;    
    
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date joining_date = new Date();
    
    @OneToOne
    @JoinColumn(name = "id")
    EmployeeDetails ed = new EmployeeDetails();
    
    
    @OneToMany
    @JoinTable(name = "user_contact", joinColumns = @JoinColumn(name = "emp_id"), inverseJoinColumns = @JoinColumn(name = "adrs_id"))
    Collection<Address> a = new ArrayList<Address>();

    public Collection<Address> getA() {
        return a;
    }

    public void setA(Collection<Address> a) {
        this.a = a;
    }

    public EmployeeDetails getEd() {
        return ed;
    }

    public void setEd(EmployeeDetails ed) {
        this.ed = ed;
    }

    public Date getJoining_date() {
        return joining_date;
    }

    public void setJoining_date(Date joining_date) {
        this.joining_date = joining_date;
    }

    public String getThis_is_not_a_Column() {
        return this_is_not_a_Column;
    }

    public void setThis_is_not_a_Column(String this_is_not_a_Column) {
        this.this_is_not_a_Column = this_is_not_a_Column;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
