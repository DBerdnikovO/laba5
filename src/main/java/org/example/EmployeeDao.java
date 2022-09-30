package org.example;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDao {

    int add(Employee emp)
            throws SQLException;

    void delete(int id)
            throws SQLException;

    List<Employee> getEmployees()
            throws SQLException;

    String[] getEmployeesColumns()
            throws SQLException;

    List<List<String>> getEmployeesLists()
                    throws SQLException;


    List<List<String>> getSingleEmployee(int idEmp) throws SQLException;
}
