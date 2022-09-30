package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Table extends JFrame {

    private JTable table;
    private DefaultTableModel model;
    private JTextField textField;
    private String[] columnNames;
    private Integer buttonClick = 0;

    public Table() throws SQLException {

        createTable();

    }

    private void createTable() throws SQLException {

        EmployeeDaoImplementation empDao = new EmployeeDaoImplementation();
        List<List<String>> b = empDao.getEmployeesLists();
        columnNames = empDao.getEmployeesColumns();

        String[][] data = new String[b.size()][];
        String[] blankArray = new String[0];
        for (int i = 0; i < b.size(); i++) {

            data[i] = b.get(i).toArray(blankArray);
        }

        setTitle("LABA 5");
        model = new DefaultTableModel(data, columnNames);
        table = new JTable(model);
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        JButton deleteButton = new JButton("Remove");
        JButton addButton = new JButton("Add");
        JButton refreshButton = new JButton("Refresh");
        textField = new JTextField(20);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                if (table.getSelectedRow() != -1) {
                    try {
                        List<Employee>[] ls = new List[]{empDao.getEmployees()};
                        empDao.delete(ls[0].get(table.getSelectedRow()).emp_id);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    model.removeRow(table.getSelectedRow());
                    JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Please, select the row");
                }

            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                buttonClick += 1;
                if (buttonClick == 1) {
                    model.addRow(new Object[]{"null", "null", "null", "null", "null"});
                }

                if (buttonClick != 1) {
                    Boolean cont = false;

                    int check = 0;

                    Employee emp = new Employee();
                    int a = table.getRowCount();
                    List<String> input = new ArrayList<>();
                    for (int i = 0; i < table.getColumnCount(); i++) {

                        cont = String.valueOf(table.getValueAt(a - 1, i)).contains("null");

                        if (cont.equals(false)) {
                            input.add(String.valueOf(table.getValueAt(a - 1, i)));
                        } else  {
                            JOptionPane.showMessageDialog(null, "Value is null");
                            check = 1;
                            break;
                        }

                    }

                    if (check == 0) {
                        try {
                            Validator validator = new Validator();
                            if(validator.validate(input)) {
                                return;
                            }

                        JOptionPane.showMessageDialog(null, "Added successfully");
                        emp.setEmp_id(Integer.parseInt(input.get(0)));
                        emp.setEmp_name(input.get(1));
                        emp.setEmp_age(Integer.parseInt(input.get(2)));
                        emp.setEmp_salary(Double.parseDouble(input.get(3)));
                        emp.setEmp_manager(input.get(4));

                            empDao.add(emp);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        buttonClick = 0;
                    }
                }
            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {

                    model.setRowCount(0);
                    List<List<String>> b = empDao.getEmployeesLists();
                    String[][] data = new String[b.size()][];
                    String[] blankArray = new String[0];
                    for (int i = 0; i < b.size(); i++) {
                        data[i] = b.get(i).toArray(blankArray);
                    }
                    model = new DefaultTableModel(data, columnNames);
                    table.setModel(model);
                    model.fireTableDataChanged();

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText();
                try {
                    Validator validator = new Validator();
                    if(validator.presenceCheck(text)) {
                        return;
                    }
                    model.setRowCount(0);
                    List<List<String>> b = empDao.getSingleEmployee(Integer.parseInt(text));
                    String[][] data = new String[b.size()][];
                    String[] blankArray = new String[0];
                    data[0] = b.get(0).toArray(blankArray);

                    model = new DefaultTableModel(data, columnNames);
                    table.setModel(model);
                    model.fireTableDataChanged();

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(deleteButton, BorderLayout.LINE_START);
        add(addButton, BorderLayout.LINE_END);
        add(refreshButton, BorderLayout.PAGE_START);
        add(textField, BorderLayout.PAGE_END);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }


}
