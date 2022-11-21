package database.DAOs;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.Connection;

import database.ConnectionFactory;
import database.model.Usuario;
public class UsuarioDAO {
    private Connection connection;

    public String cadastraUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios(codigo, nome, sobrenome, cpf) VALUES (?,?,?,?)";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, usuario.getCodigo());
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getSobrenome());
            stmt.setString(4, usuario.getCpf());
            stmt.execute();
            stmt.close();
            return "Usuário cadastrado com sucesso!";
        }catch(SQLException u){
            throw new RuntimeException(u);
        }

    }

    public void getUsuario() {
        String sql = "SELECT * FROM usuario;";
        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                int codigo = rs.getInt("codigo");
                String nome = rs.getString("nome");
                String sobrenome = rs.getString("sobrenome");
                String cpf = rs.getString("cpf");
                System.out.println("Código: " + codigo + " Nome: " + nome + " Sobrenome: " + sobrenome + " CPF: " + cpf);
            }
        
        }catch(SQLException u){
            System.out.println("Erro: " + u.getMessage());
        }
        
    }

    public int getIdUsuario(int codigo) {
        String sql = "SELECT * FROM usuario WHERE codigo = " + codigo + ";";
        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            int id = rs.getInt("id");
            return id;
        
        }catch(SQLException u){
            throw new RuntimeException(u);
        }
        
    }
}
