package org.example;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class Validator {

    EmployeeDaoImplementation empDao = new EmployeeDaoImplementation();
    List<Employee>[] ls = new List[]{empDao.getEmployees()};


    public Validator() throws SQLException {}

    public boolean validate(List<String> newRow) {

        for (Employee number : ls[0]) {
            if (number.emp_id == Integer.parseInt(newRow.get(0))) {
                JOptionPane.showMessageDialog(null, "This id is already in use");
                return true;
            }
        }

        if (!isInteger(newRow.get(2))) {
            JOptionPane.showMessageDialog(null, "Age is not correct format!");
            return true;
        }

        if (!isDouble(newRow.get(3))) {
            JOptionPane.showMessageDialog(null, "Salary is not correct format!");
            return true;
        }

      return false;

    }

    public boolean presenceCheck(String number) {

        int check =0;

        if(!isInteger(number)) {
            JOptionPane.showMessageDialog(null, "Input is not int format!");
            return true;
        }
        for (Employee numberr : ls[0]) {
            if (numberr.emp_id == Integer.parseInt(number)) {
                check = 1;
            }
        }

        if (check == 0) {
            JOptionPane.showMessageDialog(null, "Can't find id!");
            return true;
        }

        return false;
    }

    boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
