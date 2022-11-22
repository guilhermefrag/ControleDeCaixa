package database.DAOs;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import java.util.ArrayList;

import database.model.Usuario;
public class UsuarioDAO {
    private Connection conexao;
    private String select = "SELECT * from usuario order by id";
    private String selectComClausula = "SELECT * FROM usuario WHERE id = ?";
    private String insert = "INSERT into usuario " + "(codigo, nome, sobrenome, cpf) VALUES (?, ?, ?, ?)";
    private String delete = "DELETE FROM usuario WHERE id = ?";

    private PreparedStatement pstSelect;
    private PreparedStatement pstSelectComClasula;
    private PreparedStatement pstInsert;
    private PreparedStatement pstDelete;

    public UsuarioDAO(Connection conexao) throws SQLException {
        this.conexao = conexao;
        pstSelect = this.conexao.prepareStatement(select);
        pstSelectComClasula = this.conexao.prepareStatement(selectComClausula);
        pstInsert = this.conexao.prepareStatement(insert);
        pstDelete = this.conexao.prepareStatement(delete);
    }

    public List<Usuario> Select() throws SQLException {
        ResultSet resultado = pstSelect.executeQuery();
        List<Usuario> arlUsuario = new ArrayList<>();

        while (resultado.next()) {
            Usuario p = new Usuario();
            p.setId(resultado.getInt("id"));
            p.setCodigo(resultado.getInt("codigo"));
            p.setNome(resultado.getString("nome"));
            p.setSobrenome(resultado.getString("sobrenome"));
            p.setCpf(resultado.getString("cpf"));

            arlUsuario.add(p);
        }

        return arlUsuario;
    }

    public int Insert(Object param) throws SQLException {
        Usuario p = (Usuario) param;

        pstInsert.setInt(1, p.getCodigo());
        pstInsert.setString(2, p.getNome());
        pstInsert.setString(3, p.getSobrenome());
        pstInsert.setString(4, p.getCpf());

        pstInsert.execute();

        return pstInsert.getUpdateCount();
    }

    public List<Usuario> SelectComClausula(Object param) throws SQLException {
        Usuario p = (Usuario) param;
        pstSelectComClasula.setInt(1, p.getId());
        ResultSet resultado = pstSelectComClasula.executeQuery();
        List<Usuario> arlUsuario = new ArrayList<>();

        while (resultado.next()) {
            p = new Usuario();
            p.setId(resultado.getInt("id"));
            p.setCodigo(resultado.getInt("codigo"));
            p.setNome(resultado.getString("nome"));
            p.setSobrenome(resultado.getString("sobrenome"));
            p.setCpf(resultado.getString("cpf"));

            arlUsuario.add(p);
        }

        return arlUsuario;
    }

    public int Delete(Object param) throws SQLException {
        Usuario p = (Usuario) param;
        pstDelete.setInt(1, p.getId());
        pstDelete.execute();

        return pstDelete.getUpdateCount();
    }
}
