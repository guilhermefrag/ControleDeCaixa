import database.ConnectionFactory;

public class App {
    public static void main(String[] args) throws Exception {
       ConnectionFactory conexao = new ConnectionFactory();

       conexao.getConexao();
    }
}
