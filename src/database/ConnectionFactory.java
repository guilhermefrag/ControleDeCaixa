package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    private String host = "containers-us-west-44.railway.app";
    private String banco = "controle_caixa";
    private String usuario = "root";
    private String senha = "bsp6MU5DtQhH32DvayA9";
    private String url = "jdbc:mysql://" + host + "/" + banco;
    private Connection connection;

    public Connection getConexao() {
        try {
            connection = DriverManager.getConnection(url, usuario, senha);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (connection != null) {
            System.out.println("Conectado com sucesso");
        } else {
            System.out.println("Não foi possível conectar");
        }

        return connection;
    }
}
