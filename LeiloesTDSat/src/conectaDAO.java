import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conectaDAO {

    public Connection connectDB() {
        Connection conn = null;

        try {
            String url = "jdbc:mysql://localhost:3306/seu_banco_de_dados";
            String user = "seu_usuario";
            String password = "sua_senha";

            conn = DriverManager.getConnection(url, user, password);

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "conectaDAO: " + erro.getMessage());
        }

        return conn;
    }
}