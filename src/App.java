import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import database.model.Conta;
import database.model.Movimentacao;
import database.model.Usuario;
import database.DAOs.ContaDAO;
import database.DAOs.MovimentacaoDAO;
import database.DAOs.UsuarioDAO;

public class App {

    public static void Transacoes(){
        System.out.println("2 - Entrar com Usuário\n");
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n--------------Bem Vindo--------------");
        System.out.println("----------Escolha uma opção----------\n");
        System.out.println("1 - Cadastrar Usuário");
        System.out.println("2 - Entrar com Usuário\n");
        System.out.println("Digite a opção: ");
        int opcao = scanner.nextInt();
    }
}
