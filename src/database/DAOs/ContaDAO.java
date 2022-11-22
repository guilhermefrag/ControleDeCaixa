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
    private String insert = "INSERT into conta " + "(id_usuario, valor_total) VALUES (?, ?)";
    private String update = "UPDATE conta " + "SET valor_total = ? WHERE id_usuario = ?";

    private PreparedStatement pstSelectComClasula;
    private PreparedStatement pstInsert;
    private PreparedStatement pstUpdate;

    public ContaDAO(Connection conexao) throws SQLException {
        this.conexao = conexao;
        pstSelectComClasula = this.conexao.prepareStatement(selectComClausula);
        pstInsert = this.conexao.prepareStatement(insert);
        pstUpdate = this.conexao.prepareStatement(update);
    }

    public List<Conta> SelectComClausula(int idUsuario) throws SQLException {
        pstSelectComClasula.setInt(1, idUsuario);
        ResultSet resultado = pstSelectComClasula.executeQuery();
        List<Conta> arlConta = new ArrayList<>();

        while (resultado.next()) {
            Conta p = new Conta();
            p.setId(resultado.getInt("id"));
            p.setIdUsuario(resultado.getInt("id_usuario"));
            p.setValorTotal(resultado.getFloat("valor_total"));

            arlConta.add(p);
        }

        return arlConta;
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
