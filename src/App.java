import database.ConnectionFactory;
import database.DAOs.ContaDAO;
import database.DAOs.MovimentacaoDAO;
import database.DAOs.UsuarioDAO;
import database.model.Conta;
import database.model.Movimentacao;
import database.model.Usuario;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

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

    conexao = connection.getConexao();

    System.out.println("\n--------------Bem Vindo--------------");
    System.out.println("----------Escolha uma opção----------\n");
    System.out.println("1 - Cadastrar Usuário");
    System.out.println("2 - Entrar com Usuário\n");
    System.out.println("Digite a opção: ");
    int opcao = scanner.nextInt();
    contaDAO = new ContaDAO(conexao);
    movimentacaoDAO = new MovimentacaoDAO(conexao);

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
      usuarioDAO = new UsuarioDAO(conexao);
      usuarioDAO.Insert(usuario);
      usuario.setId(usuarioDAO.SelectIdByNome(usuario.getNome()));
      conta.setIdUsuario(usuario.getId());
      conta.setValorTotal(0);
      contaDAO.Insert(conta);
    } else if (opcao == 2) {
      System.out.println("Digite o seu nome: ");
      usuario.setNome(scanner.next());
      System.out.println("Digite sua senha: ");
      usuario.setSenha(scanner.next());

      usuarioDAO = new UsuarioDAO(conexao);
      if (!usuarioDAO.Login(usuario.getNome(), usuario.getSenha())) {
        System.out.println("Usuário ou senha incorretos!");
        return;
      }
      System.out.println("Login efetuado com sucesso!");
      usuario.setId(usuarioDAO.SelectIdByNome(usuario.getNome()));
      conta.setIdUsuario(usuario.getId());
      conta = contaDAO.SelectComClausula(usuario.getId());
      Transacoes();
    }
  }

  public static void Transacoes() throws SQLException {
    System.out.println("-----Escolha a operação você deseja realizar-----\n");
    System.out.println("1 - Saque\n");
    System.out.println("2 - Depósito\n");
    System.out.println("3 - Sair\n");
    System.out.println("Digite a opção: ");
    int opcao = scanner.nextInt();
    if (opcao == 1) {
      System.out.println("Digite o valor do saque: ");
      double valor = scanner.nextDouble();

      conta.setValorTotal(
        contaDAO.SelectComClausula(usuario.getId()).getValorTotal()
      );

      if (conta.getValorTotal() <= 0 || conta.getValorTotal() < valor) {
        System.out.println("Sem valor disponível para esse saque");
      } else {
        conta.setValorTotal(conta.getValorTotal() - valor);
        movimentacao.setIdUsuario(usuario.getId());
        movimentacao.setTipo("Saque");
        movimentacao.setValor(valor);

        contaDAO.Update(conta);
        movimentacaoDAO.Insert(movimentacao);

        System.out.println("Valor sacado com sucesso!");
        Transacoes();
      }
    } else if (opcao == 2) {
      System.out.println("Digite o valor do depoósito: ");
      double valor = scanner.nextDouble();

      conta.setValorTotal(
        contaDAO.SelectComClausula(usuario.getId()).getValorTotal()
      );

      conta.setValorTotal(conta.getValorTotal() + valor);
      movimentacao.setIdUsuario(usuario.getId());
      movimentacao.setTipo("Deposito");
      movimentacao.setValor(valor);

      contaDAO.Update(conta);
      movimentacaoDAO.Insert(movimentacao);

      System.out.println("Valor depositado com sucesso!");
      Transacoes();
    } else {
      System.out.println("Saindo...");
      return;
    }
  }
}
