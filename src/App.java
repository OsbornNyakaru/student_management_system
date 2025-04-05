import java.sql.*;
import java.util.ArrayList;


public class App {

    private static Connection connection;

    // Establish database connection
    public Connection getConnection() throws SQLException {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver"); // Load the driver
        String dbUrl = "jdbc:mysql://localhost:3306/student_management";
        String username = "root";
        String password = "Nyakaru&41733675";
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(dbUrl, username, password);
            System.out.println("Connected to the database.");
        }
        return connection;
    } catch (ClassNotFoundException e) {
        System.out.println("MySQL Driver not found: " + e.getMessage());
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }
    return null; // Return null if connection fails
}

}

 class ExecuteCode {

    private Connection connection;

    public ExecuteCode(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<String> executeTerminalCode(String sqlQuery) {
        ArrayList<String> results = new ArrayList<>();

        try (Statement stmt = connection.createStatement()) {
            // Execute the query (can be SELECT, INSERT, UPDATE, DELETE)
            if (sqlQuery.trim().toUpperCase().startsWith("SELECT")) {
                ResultSet rs = stmt.executeQuery(sqlQuery);
                while (rs.next()) {
                    // Assuming the result set has a column, handle accordingly
                    String result = "";
                    ResultSetMetaData metaData = rs.getMetaData();
                    int columnCount = metaData.getColumnCount();
                    for (int i = 1; i <= columnCount; i++) {
                        result += metaData.getColumnLabel(i) + ": " + rs.getString(i) + " | ";
                    }
                    results.add(result);
                }
            } else {
                int affectedRows = stmt.executeUpdate(sqlQuery);
                results.add("Rows affected: " + affectedRows);
            }
        } catch (SQLException e) {
            results.add("Error executing query: " + e.getMessage());
        }

        return results;
    }
}
