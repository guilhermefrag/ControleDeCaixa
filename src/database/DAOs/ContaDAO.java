package database.DAOs;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class ContaDAO {
    private Connection conexao;
    private String select = "SELECT * from conta order by id";
    private String selectComClausula = "SELECT * from conta WHERE id = ?";
    private String insert =
      "INSERT into conta " + "(idUsuario, valor_total) VALUES (?, ?)";
    private String update = "UPDATE conta " + "SET valor_total=? WHERE id = ?";
    private String delete = "DELETE from conta WHERE id = ?";
  
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
        p.setValor(resultado.getFloat("valor"));
        p.setDataMovimentacao(resultado.getDate("data_movimentacao"));
  
        arlMovimentacao.add(p);
      }
  
      return arlMovimentacao;
    }
  
    public int Insert(Object param) throws SQLException {
      Movimentacao p = (Movimentacao) param;
  
      pstInsert.setInt(1, p.getIdUsuario());
      pstInsert.setString(2, p.getTipo());
      pstInsert.setDouble(3, p.getValor());
      pstInsert.setTimestamp(4, new Timestamp(p.getDataMovimentacao().getTime()));
  
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
        pstUpdate.setTimestamp(
          4,
          new Timestamp(p.getDataMovimentacao().getTime())
        );
  
        pstUpdate.execute();
  
        return pstUpdate.getUpdateCount();
      } catch (Exception e) {
        e.printStackTrace();
      }
      return 0;
    }
}
