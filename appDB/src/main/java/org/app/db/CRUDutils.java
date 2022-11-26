package org.app.db;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CRUDutils {
    private static String INSERT_EMPLOYEE = "INSERT INTO employee (first_name,last_name,gender,email,date_of_birth) VALUES (?,?,?,?,?); ";
    private static String UPDATE_EMPLOYEE = "UPDATE employee SET first_name = ?,last_name = ?,gender = ?,email = ?,date_of_birth = ?  WHERE id = ? ";
    private static String DELETE_EMPLOYEE = "DELETE FROM employee WHERE id = ?";

    public ResultSet getEmployee(Employee employee) {
        ResultSet resultSet = null;
        String select = "SELECT * FROM employee WHERE first_name = ? AND last_name = ?";
        try {
            Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.setString(1, employee.getFirst_name());
            preparedStatement.setString(2, employee.getLast_name());
            resultSet = preparedStatement.executeQuery(); //здесь сохраняем наши данные
        } catch ( SQLException e ) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public List<Employee> getEmployeeData(String query) {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            ResultSet rs = preparedStatement.executeQuery(); //здесь сохраняем наши данные
            while (rs.next()) {
                int id = rs.getInt("id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String gender = rs.getString("gender");
                String email = rs.getString("email");
                String date_of_birth = rs.getString("date_of_birth");
                employees.add(new Employee(id, first_name, last_name, gender, email, date_of_birth));
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
        return employees;
    }

    public void saveEmployeeData(Employee emplyee) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE);
        ) {
            System.out.println(emplyee.getDate_of_birth());
            java.sql.Date date = Date.valueOf(emplyee.getDate_of_birth());
            preparedStatement.setString(1, emplyee.getFirst_name());
            preparedStatement.setString(2, emplyee.getLast_name());
            preparedStatement.setString(3, emplyee.getGender());
            preparedStatement.setString(4, emplyee.getEmail());
            preparedStatement.setDate(5, date);
            preparedStatement.executeUpdate();
        } catch ( SQLException throwables ) {
            throwables.printStackTrace();
        }
    }

    public void updateEmployee(int idEmployee, String name, String surname, String gender, String emaiL, String date) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLOYEE);
        ) {
            java.sql.Date date1 = Date.valueOf(date);
            preparedStatement.setInt(6, idEmployee);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, gender);
            preparedStatement.setString(4, emaiL);
            preparedStatement.setDate(5, date1);
            preparedStatement.executeUpdate();
        } catch ( SQLException throwables ) {
            throwables.printStackTrace();
        }
    }

    public void deleteEmployee(int employeeId) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMPLOYEE);
        ) {
            preparedStatement.setInt(1, employeeId);
            preparedStatement.executeUpdate();
        } catch ( SQLException throwables ) {
            throwables.printStackTrace();
        }
    }
}

