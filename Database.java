import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class Database {

    private  String url="jdbc:mysql://localhost:3306/airlinereservation";
    private  String user="root";
    private  String pass="P3009@riya#";
    private Statement statement;

    public Database() throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, pass);
        statement = connection.createStatement();
    }

    public Statement getStatement() {
        return statement;
    }
}
