
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws Exception {

        Scanner leitor = new Scanner(System.in);
        Sistema sistema = new Sistema();
        int option = 0;

        while (option != 9) {

            System.out.println();
            System.out.println("-------------------------");
            System.out.println("Sistema de emprestimos");
            System.out.println("--------------------------");
            System.out.println("Escolha o que você gostaria de fazer");
            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - Lista usuários");
            System.out.println("3 - Cadastrar livro");
            System.out.println("4 - Listar livros");
            System.out.println("5 - Realizar empréstimo");
            System.out.println("6 - Listar empréstimos");

            System.out.println("9 - Sair");
            option = leitor.nextInt();

            switch (option) {
                case 1:
                    sistema.novoUsuario();
                    break;
                case 2:
                    sistema.listarUsuarios();
                    break;
                case 3:
                    sistema.cadastrarLivro();
                    break;
                case 4:
                    sistema.listarLivros();
                    break;
                case 5:
                    sistema.realizarEmprestimo();
                    break;
                default:
                    break;
            }

        }
    }
}