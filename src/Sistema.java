
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sistema {

    Scanner leitor = new Scanner(System.in);

    private List<Usuario> usuarios = new ArrayList<>();

    public void novoUsuario() {    

        System.out.println("Digite o nome");
        String nome = leitor.nextLine();
        System.out.println("Digite a matrícula");
        int matricula = Integer.parseInt(leitor.nextLine());
        System.out.println("Digite o nome do curso");
        String curso = leitor.nextLine();

        Usuario usuario = new Usuario(matricula, nome, curso);
        usuarios.add(usuario);
        System.out.println("Usuário adicionado!");
    }

    public void listarUsuarios() {

        System.out.println();
        System.out.println("----------------------");

        for (Usuario usuario : usuarios) {
            System.out.println("Nome: %s".formatted(usuario.getNome()));
            System.out.println("Matrícula: %d".formatted(usuario.getMatricula()));
            System.out.println("Curso: %s".formatted(usuario.getCurso()));
        }  

        System.out.println("----------------------");
        System.out.println();
    }

}
