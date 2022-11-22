package database.DAOs;

import database.model.Conta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContaDAO {

  private Connection conexao;
  private String selectComClausula = "SELECT * from conta WHERE id_usuario = ?";
  private String insert =
    "INSERT into conta " + "(id_usuario, valor_total) VALUES (?, ?)";
  private String update =
    "UPDATE conta " + "SET valor_total = ? WHERE id_usuario = ?";

  private PreparedStatement pstSelectComClasula;
  private PreparedStatement pstInsert;
  private PreparedStatement pstUpdate;

  public ContaDAO(Connection conexao) throws SQLException {
    this.conexao = conexao;
    pstSelectComClasula = this.conexao.prepareStatement(selectComClausula);
    pstInsert = this.conexao.prepareStatement(insert);
    pstUpdate = this.conexao.prepareStatement(update);
  }

  public Conta SelectComClausula(int idUsuario) throws SQLException {
    pstSelectComClasula.setInt(1, idUsuario);

    ResultSet resultado = pstSelectComClasula.executeQuery();
    Conta c = new Conta();

    while (resultado.next()) {
      c.setId(resultado.getInt("id"));
      c.setIdUsuario(resultado.getInt("id_usuario"));
      c.setValorTotal(resultado.getFloat("valor_total"));
    }
    return c;
  }

  public int SelectIdByNome(String nome) throws SQLException {
    pstSelectComClasula.setString(1, nome);
    ResultSet resultado = pstSelectComClasula.executeQuery();
    int id = 0;

    while (resultado.next()) {
      id = resultado.getInt("id");
    }

    return id;
  }

  public int Insert(Object param) throws SQLException {
    Conta p = (Conta) param;

    pstInsert.setInt(1, p.getIdUsuario());
    pstInsert.setDouble(2, p.getValorTotal());
    return pstInsert.executeUpdate();
  }

  public int Update(Object param) throws SQLException {
    Conta p = (Conta) param;

    pstUpdate.setDouble(1, p.getValorTotal());
    pstUpdate.setInt(2, p.getIdUsuario());
    return pstUpdate.executeUpdate();
  }
}
