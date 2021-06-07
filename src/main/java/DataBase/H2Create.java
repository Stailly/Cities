package DataBase;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class H2Create {
    private static final String CREATE_TABLE_SQL = "create table cities (\r\n" +
            "  id  int(3) primary key auto_increment,\r\n" +
            "  name varchar(30),\r\n" +
            "  region varchar(30),\r\n" +
            "  district varchar(30),\r\n" +
            "  population int(20),\r\n" +
            "  foundation int(4)\r\n" +
            "  );";

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
}
