package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImplementation implements EmployeeDao {

    static Connection con = DatabaseConnection.getConnection();

    @Override
    public int add(Employee emp) throws SQLException {
        String query = "insert into laba5.emp(id, " + "name," + "age," + "salary," + "manager) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, String.valueOf(emp.getEmp_id()));
        ps.setString(2, emp.getEmp_name());
        ps.setString(3, String.valueOf(emp.getEmp_age()));
        ps.setString(4, String.valueOf(emp.getEmp_salary()));
        ps.setString(5, emp.getEmp_manager());
        int n = ps.executeUpdate();
        return n;
    }

    @Override
    public void delete(int id) throws SQLException {
        System.out.println(id);
        String query = "delete from laba5.emp where id =?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    @Override
    public List<Employee> getEmployees() throws SQLException {
        String query = "SELECT * from laba5.emp";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<Employee> ls = new ArrayList<>();

        while (rs.next()) {
            Employee emp = new Employee();
            emp.setEmp_id(rs.getInt("id"));
            emp.setEmp_name(rs.getString("name"));
            emp.setEmp_age(rs.getInt("age"));
            emp.setEmp_salary(rs.getDouble("salary"));
            emp.setEmp_manager(rs.getString("manager"));
            ls.add(emp);
        }
        return ls;
    }

    @Override
    public String[] getEmployeesColumns() throws SQLException {
        String query = "SELECT * from laba5.emp";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        String[] colums = new String[rs.getMetaData().getColumnCount()];
        System.out.println("types:");
        for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
            colums[i] = rs.getMetaData().getColumnName(i + 1);
        }
        return colums;
    }

    @Override
    public List<List<String>> getEmployeesLists()
            throws SQLException {
        String query = "SELECT * from laba5.emp";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        List<List<String>> b = new ArrayList<>();
        while (rs.next()) {
            List<String> a = new ArrayList<>();
            for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {
                a.add(rs.getObject(i).toString());
            }
            b.add(a);
        }

        return b;
    }

    @Override
    public List<List<String>> getSingleEmployee(int idEmp) throws SQLException {
        String query = "SELECT * from laba5.emp WHERE id=" + idEmp;
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        List<List<String>> b = new ArrayList<>();
        while (rs.next()) {
            List<String> a = new ArrayList<>();
            for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {
                a.add(rs.getObject(i).toString());
            }
            b.add(a);
        }

        return b;
    }
}
