import java.util.Scanner;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.model.Conta;
import database.model.Movimentacao;
import database.model.Usuario;
import database.ConnectionFactory;
import database.DAOs.ContaDAO;
import database.DAOs.MovimentacaoDAO;
import database.DAOs.UsuarioDAO;

public class App {
    private static Usuario usuario = new Usuario();
    private static Conta conta = new Conta();
    private static Movimentacao movimentacao = new Movimentacao();
    private static Connection conexao;
    private static UsuarioDAO usuarioDAO;
    private static ContaDAO contaDAO;
    private static MovimentacaoDAO movimentacaoDAO;
    private static ConnectionFactory connection = new ConnectionFactory();
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws Exception {

        ConnectionFactory connection = new ConnectionFactory();

        connection.getConexao();

        System.out.println("\n--------------Bem Vindo--------------");
        System.out.println("----------Escolha uma opção----------\n");
        System.out.println("1 - Cadastrar Usuário");
        System.out.println("2 - Entrar com Usuário\n");
        System.out.println("Digite a opção: ");
        int opcao = scanner.nextInt();

        if (opcao == 1) {

            System.out.println("Digite o codigo do usuário: ");
            usuario.setCodigo(scanner.nextInt());
            System.out.println("Digite o nome do usuário:");
            usuario.setNome(scanner.next());
            System.out.println("Digite o sobrenome do usuário:");
            usuario.setSobrenome(scanner.next());
            System.out.println("Digite o cpf do usuário:");
            usuario.setCpf(scanner.next());
            System.out.println("Digite a senha do usuário:");
            usuario.setSenha(scanner.next());
            UsuarioDAO usuarioDAO = new UsuarioDAO(connection.getConexao());
            usuarioDAO.Insert(usuario);
            usuario.setId(usuarioDAO.SelectIdByNome(usuario.getNome()));
            ContaDAO contaDAO = new ContaDAO(connection.getConexao());
            conta.setIdUsuario(usuario.getId());
            conta.setValorTotal(0);
            contaDAO.Insert(conta);
        }
        else if (opcao == 2){
            System.out.println("Digite o seu nome: ");
            String nome = scanner.next();
            System.out.println("Digite sua senha: ");
            String senha = scanner.next();

            UsuarioDAO usuarioDAO = new UsuarioDAO(connection.getConexao());
            if (!usuarioDAO.Login(nome, senha)) {
                System.out.println("Usuário ou senha incorretos!");
                return;
            }
            System.out.println("Login efetuado com sucesso!");
            Transacoes();
            
        }
    }

    public static void Transacoes() throws SQLException{
        
        connection.getConexao();
        System.out.println("-----Escolha a operação você deseja realizar-----\n");
        System.out.println("1 - Saque\n");
        System.out.println("2 - Depósito\n");
        System.out.println("Digite a opção: ");
        int opcao = scanner.nextInt();
        if (opcao == 1) {
            System.out.println("Digite o valor do saque: ");
            double valor = scanner.nextDouble();
            System.out.println("Digite o id da conta: ");
            int numero = scanner.nextInt();
            Movimentacao movimentacao = new Movimentacao();
            MovimentacaoDAO movimentacaoDAO = new MovimentacaoDAO(connection.getConexao());
            
        }
        else if (opcao == 2) {
            System.out.println("Digite o valor do depósito: ");
            double valor = scanner.nextDouble();
            System.out.println("Digite o número da conta: ");
            int numero = scanner.nextInt();
            System.out.println("Digite o código do usuário: ");
            int codigo = scanner.nextInt();
            Movimentacao movimentacao = new Movimentacao();
        }
    }

}
