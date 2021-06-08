package DataBase;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class H2Create {
    private static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS cities (\r\n" +
            "  id  INT(3) PRIMARY KEY AUTO_INCREMENT,\r\n" +
            "  name VARCHAR(30),\r\n" +
            "  region VARCHAR(30),\r\n" +
            "  district VARCHAR(30),\r\n" +
            "  population INT(20),\r\n" +
            "  foundation INT(4)\r\n" +
            "  );";
    private static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS cities";

    public static void main(String[] args) {
        new H2Create().createTable();
    }

    public void createTable() {
        try (Connection connection = JDBCUtils.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(CREATE_TABLE_SQL);
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }
    public void dropTable() {
        try (Connection connection = JDBCUtils.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(DROP_TABLE_SQL);
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }
}
