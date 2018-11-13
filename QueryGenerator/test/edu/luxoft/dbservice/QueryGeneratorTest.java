package edu.luxoft.dbservice;

import org.junit.Before;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class QueryGeneratorTest {

    QueryGenerator<Employee> generator;
    Employee emp1;

    @Before
    public void prepare(){
        generator = new QueryGenerator<>();
        generator.compile(Employee.class);
        emp1 = new Employee();
        emp1.setInn("6556656533");
        emp1.setName("Ivan");
        emp1.setLastName("Ivanov");
        emp1.setSalary(125.5);
        emp1.setSex('M');
        emp1.setSinceYear(1999);


    }

    @org.junit.Test
    public void getSelect() {
        assertEquals("SELECT sex, sinceYear, employee_last_name, inn, Employee_Name, salary, birthYear FROM Employee",generator.getSelect());
        System.out.println(generator.getSelect());
    }

    @org.junit.Test
    public void getDelete() {
        assertEquals("DELETE FROM Employee WHERE inn = '6556656533'",generator.getDelete(emp1));
        assertEquals("DELETE FROM Employee WHERE Employee_Name = 'Ivan'",generator.getDelete(emp1,"employee_name"));
        System.out.println(generator.getDelete(emp1));
        System.out.println(generator.getDelete(emp1,"employee_name"));
    }

    @org.junit.Test
    public void getUpdate() {
        emp1.setBirthYear(1980);
        assertEquals("UPDATE Employee SET sex = 'M', sinceYear = 1999, employee_last_name = 'Ivanov', Employee_Name = 'Ivan', salary = 125.5, birthYear = 1980 WHERE inn = '6556656533'",
                      generator.getUpdate(emp1));
        System.out.println(generator.getUpdate(emp1));

        assertEquals("UPDATE Employee SET employee_last_name = 'Ivanov', Employee_Name = 'Ivan' WHERE inn = '6556656533'",
                generator.getUpdate(emp1,"employee_Name, employee_last_name"));
        System.out.println(generator.getUpdate(emp1,"employee_Name, employee_last_name"));
    }

    @org.junit.Test
    public void getInsert() {
        ArrayList<String> s = new ArrayList<>();
        ArrayList<Integer> i = new ArrayList<>();
        System.out.println(i.getClass()==s.getClass());
        assertEquals("INSERT Employee (sex, sinceYear, employee_last_name, inn, Employee_Name, salary, birthYear) VALUES ('M', 1999, 'Ivanov', '6556656533', 'Ivan', 125.5, 0)",
                generator.getInsert(emp1));
        System.out.println(generator.getInsert(emp1));

        assertEquals("INSERT Employee (sex, sinceYear, employee_last_name, inn, Employee_Name, birthYear) VALUES ('M', 1999, 'Ivanov', '6556656533', 'Ivan', 0)",
                generator.getInsert(emp1,"sex, sinceYear, employee_last_name, Employee_Name, birthYear, inn"));
        System.out.println(generator.getInsert(emp1,"sex, sinceYear, employee_last_name, Employee_Name, birthYear, inn"));
    }
}