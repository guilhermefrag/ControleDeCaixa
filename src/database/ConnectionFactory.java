package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

  private String host = "localhost";
  private String banco = "controle_caixa";
  private String usuario = "root";
  private String senha = "mysql";
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
