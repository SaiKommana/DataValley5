package Assignment5;

import java.sql.*;

public class DepartmentProgram {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        String url = "jdbc:oracle:thin:@localhost:1521/your_sid";
        String username = "sai";
        String password = "ganesh_2";

        Department department = new Department(10, "Engineering");

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            connection = DriverManager.getConnection(url, username, password);

            String sql = "INSERT INTO department (id, name) VALUES (?, ?)";

            statement = connection.prepareStatement(sql);

            statement.setInt(1, department.getId());
            statement.setString(2, department.getName());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Department record inserted successfully!");
            } else {
                System.out.println("No records inserted!");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Error: Oracle JDBC driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error: Database connection failed!");
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}

class DepartmentData {
    private int id;
    private String name;

    public DepartmentData(int id, String name) {
        this.id = id;
        this.name = name;
    }

}