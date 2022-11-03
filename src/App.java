import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import models.Usuario;

public class App {

    public static void Transacoes(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o código do usuário: ");
        int codigo = scanner.nextInt();
        // Usuario usuario = UsuarioDAO.getUsuarioByCodigo(codigo);
        // if(usuario != null){
        //     System.out.println("");
    }

    public static void main(String[] args) throws Exception {
        System.out.println("--------------Bem Vindo--------------");
        System.out.println("----------Escolha uma opção----------");
        System.out.println("1 - Cadastrar Usuário");
        System.out.println("2 - Entrar com Usuário");
    }
}
