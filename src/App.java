import java.util.Scanner;
import java.sql.Connection;
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

    public static void Transacoes(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("-----Escolha a operação você deseja realizar-----\n");
        System.out.println("1 - Saque\n");
        System.out.println("2 - Depósito\n");
        System.out.println("Digite a opção: ");
        int opcao = scanner.nextInt();
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);


        ConnectionFactory connection = new ConnectionFactory();

        connection.getConexao();


        System.out.println("\n--------------Bem Vindo--------------");
        System.out.println("----------Escolha uma opção----------\n");
        System.out.println("1 - Cadastrar Usuário");
        System.out.println("2 - Entrar com Usuário\n");
        System.out.println("Digite a opção: ");
        int opcao = scanner.nextInt();

        if (opcao == 1) {

        }
        else if (opcao == 2){
            System.out.println("Digite o seu código de usuario: ");
            Transacoes();
        }
    }
}
