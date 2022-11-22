package database.DAOs;

import database.model.Movimentacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MovimentacaoDAO {

  private Connection conexao;
  private String select = "SELECT * from movimentacao order by id";
  private String selectComClausula =
    "SELECT * from movimentacao WHERE id_usuario = ?";
  private String insert =
    "INSERT into movimentacao " + "(id_usuario, tipo, valor) VALUES (?, ?, ?)";
  private String update = "UPDATE  movimentacao " + "SET valor=? WHERE id = ?";
  private String delete = "DELETE from movimentacao WHERE id = ?";

  private PreparedStatement pstSelect;
  private PreparedStatement pstSelectComClasula;
  private PreparedStatement pstInsert;
  private PreparedStatement pstUpdate;
  private PreparedStatement pstDelete;

  public MovimentacaoDAO(Connection conexao) throws SQLException {
    this.conexao = conexao;
    pstSelect = this.conexao.prepareStatement(select);
    pstSelectComClasula = this.conexao.prepareStatement(selectComClausula);
    pstInsert = this.conexao.prepareStatement(insert);
    pstUpdate = this.conexao.prepareStatement(update);
    pstDelete = this.conexao.prepareStatement(delete);
  }

  public List<Movimentacao> Select() throws SQLException {
    ResultSet resultado = pstSelect.executeQuery();
    List<Movimentacao> arlMovimentacao = new ArrayList<>();

    while (resultado.next()) {
      Movimentacao p = new Movimentacao();
      p.setIdUsuario(resultado.getInt("id_usuario"));
      p.setId(resultado.getInt("id"));
      p.setTipo(resultado.getString("tipo"));
      p.setValor(resultado.getDouble("valor"));

      arlMovimentacao.add(p);
    }

    return arlMovimentacao;
  }

  public int Insert(Object param) throws SQLException {
    Movimentacao p = (Movimentacao) param;

    pstInsert.setInt(1, p.getIdUsuario());
    pstInsert.setString(2, p.getTipo());
    pstInsert.setDouble(3, p.getValor());

    pstInsert.execute();

    return pstInsert.getUpdateCount();
  }

  public long Delete(Object param) {
    Movimentacao p = (Movimentacao) param;
    try {
      pstDelete.setInt(1, p.getId());
      pstDelete.execute();
      return pstDelete.getUpdateCount();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return 0;
  }

  public long Insert(Object param, Object param2) throws SQLException {
    Movimentacao p = (Movimentacao) param;

    try {
      pstUpdate.setInt(1, p.getIdUsuario());
      pstUpdate.setString(2, p.getTipo());
      pstUpdate.setDouble(3, p.getValor());

      pstUpdate.execute();

      return pstUpdate.getUpdateCount();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 0;
  }

  public List<Movimentacao> SelectComClausula(Object param)
    throws SQLException {
    Movimentacao p = (Movimentacao) param;
    pstSelectComClasula.setInt(1, p.getIdUsuario());
    ResultSet resultado = pstSelectComClasula.executeQuery();
    List<Movimentacao> arlMovimentacao = new ArrayList<>();

    while (resultado.next()) {
      Movimentacao p2 = new Movimentacao();
      p2.setIdUsuario(resultado.getInt("id_usuario"));
      p2.setId(resultado.getInt("id"));
      p2.setTipo(resultado.getString("tipo"));
      p2.setValor(resultado.getDouble("valor"));

      arlMovimentacao.add(p2);
    }

    return arlMovimentacao;
  }
}
