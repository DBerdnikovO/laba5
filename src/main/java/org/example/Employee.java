package org.example;

public class Employee {

    int emp_id;
    String emp_name;
    int emp_age;
    Double emp_salary;
    String emp_manager;

    public Employee() {}

    public int getEmp_age()
    {
        return emp_age;
    }

    public void setEmp_age(int emp_age)
    {
        this.emp_age = emp_age;
    }

    public double getEmp_salary()
    {
        return emp_salary;
    }

    public void setEmp_salary(double emp_salary)
    {
        this.emp_salary = emp_salary;
    }

    public String getEmp_name()
    {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public int getEmp_id()
    {
        return emp_id;
    }

    public void setEmp_id(int emp_id)
    {
        this.emp_id = emp_id;
    }

    public String getEmp_manager()
    {
        return emp_manager;
    }

    public void setEmp_manager(String emp_manager)
    {
        this.emp_manager = emp_manager;
    }


}
