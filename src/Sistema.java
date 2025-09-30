
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sistema {

    Scanner leitor = new Scanner(System.in);

    private List<Usuario> usuarios = new ArrayList<>();
    private List<Livro> livros = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();

    public void novoUsuario() {    

        System.out.println("1 - Professor");
        System.out.println("2 - Aluno");
        int tipo = Integer.parseInt(leitor.nextLine());

        System.out.println("Digite o nome");
        String nome = leitor.nextLine();
        System.out.println("Digite a matrícula");
        int matricula = Integer.parseInt(leitor.nextLine());
        System.out.println("Digite o nome do curso");
        String curso = leitor.nextLine();

        Usuario usuario;
        if (tipo == 1) {
            usuario = new Professor(matricula, nome, curso);
        } else {
            usuario = new Aluno(matricula, nome, curso);
        }

        usuarios.add(usuario);
        System.out.println("Usuário adicionado!");
    }

    public void listarUsuarios() {

        System.out.println();
        System.out.println("----------------------");

        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado");
        }

        for (Usuario usuario : usuarios) {
            System.out.println("Nome: %s".formatted(usuario.getNome()));
            System.out.println("Matrícula: %d".formatted(usuario.getMatricula()));
            System.out.println("Curso: %s".formatted(usuario.getCurso()));
        }  

        System.out.println("----------------------");
        System.out.println();
    }

    public void cadastrarLivro() {

        System.out.println("Digite o titulo");
        String titulo = leitor.nextLine();
        System.out.println("Digite o autor");
        String autor = leitor.nextLine();
        System.out.println("Digite o ano");
        int ano = Integer.parseInt(leitor.nextLine());
        System.out.println("Digite a quantidade");
        int quantidade = Integer.parseInt(leitor.nextLine());

        Livro livro = new Livro(titulo, autor, ano, quantidade);
        livros.add(livro);
        System.out.println("Livro adicionado!");
    }

    public void listarLivros() {

        System.out.println();
        System.out.println("----------------------");

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado");
        }

        for (Livro livro : livros) {
            System.out.println("Título: %s".formatted(livro.getTitulo()));
            System.out.println("Autor: %s".formatted(livro.getAutor()));
            System.out.println("Ano: %d".formatted(livro.getAno()));
            System.out.println("Quantidade: %d".formatted(livro.getQuantidade()));
        }  

        System.out.println("----------------------");
        System.out.println();
    }

    // Listar todos os empréstimos em andamento (sem dataDevolucao). - estou exibindo todos até os com devolução
    public void listarEmprestimos() {

        DateTimeFormatter formatePadraoBrasileiro = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println();
        System.out.println("----------------------");

        if (emprestimos.isEmpty()) {
            System.out.println("Nenhum empréstimo realizado");
        }

        for (Emprestimo emprestimo : emprestimos) {
            System.out.println("ID: %d".formatted(emprestimo.getId()));
            System.out.println("Livro: %s".formatted(emprestimo.getLivro().getTitulo()));
            System.out.println("Usuário: %s Matrícula: %d".formatted(emprestimo.getUsuario().getNome(), emprestimo.getUsuario().getMatricula()));
            System.out.println("Data do Empréstimo: %s".formatted(emprestimo.getDataEmprestimo().format(formatePadraoBrasileiro)));
            System.out.println("Data Prevista: %s".formatted(emprestimo.getDataPrevista().format(formatePadraoBrasileiro)));
            System.out.println("Data de Devolução: %s".formatted(
                emprestimo.getDataDevolucao() == null ? "Não devolvido ainda" : emprestimo.getDataDevolucao().format(formatePadraoBrasileiro)
            ));
            
        }  

        System.out.println("----------------------");
        System.out.println();
    }

    public void realizarEmprestimo() {

        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado, cadastre usuários antes de realizar um empréstimo.");
            return;
        }
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado, cadastre livros antes de realizar um empréstimo.");
            return;
        }

        System.out.println("Digite o nome do livro que você quer");
        String noString = leitor.nextLine();
        Livro livroEscolhido = null;

        // Procura o livro pelo título
        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(noString)) {
                livroEscolhido = livro;
                break;
            }
        }

        // Verifica se o livro foi encontrado e se está disponível
        if (livroEscolhido == null) {
            System.out.println("Livro não encontrado");
            return;
        }

        if (livroEscolhido.getQuantidade() <= 0) {
            System.out.println("Livro indisponível no momento");
            return;
        }

        System.out.println("Digite a matrícula do usuário");
        int matricula = Integer.parseInt(leitor.nextLine());
        Usuario usuarioEscolhido = null;

        for (Usuario usuario : usuarios) {
            if (usuario.getMatricula() == matricula) {
                usuarioEscolhido = usuario;
                break;
            }
        }

        if (usuarioEscolhido == null) {
            System.out.println("Usuário não encontrado");
            return;
        }

        // Atualizar estoque (decrementar ao emprestar; incrementar ao devolver).
        livroEscolhido.setQuantidade(livroEscolhido.getQuantidade() - 1);
        int idAtualEmprestimos = emprestimos.size() + 1;
        LocalDate hoje = LocalDate.now();
        // Será 14 dias depois para professor e 7 para aluno
        LocalDate dataPrevista = hoje.plusDays(usuarioEscolhido.getPrazoEmprestimo());

        Emprestimo novoEmprestimo = new Emprestimo(idAtualEmprestimos, livroEscolhido, usuarioEscolhido, hoje, dataPrevista, null);
        emprestimos.add(novoEmprestimo);

        System.out.println("Empréstimo realizado com sucesso!");

    }

    public void realizarDevolucao() {

        if (emprestimos.isEmpty()) {
            System.out.println("Nenhum empréstimo realizado, não há o que devolver.");
            return;
        }

        System.out.println("Digite o ID do empréstimo que você quer devolver");
        int id = Integer.parseInt(leitor.nextLine());
        Emprestimo emprestimoEscolhido = null;

        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getId() == id) {
                emprestimoEscolhido = emprestimo;
                break;
            }
        }

        if (emprestimoEscolhido == null) {
            System.out.println("Empréstimo não encontrado");
            return;
        }

        if (emprestimoEscolhido.getDataDevolucao() != null) {
            System.out.println("Este empréstimo já foi devolvido");
            return;
        }

        // Atualizar estoque (decrementar ao emprestar; incrementar ao devolver).
        Livro livroEmprestado = emprestimoEscolhido.getLivro();
        livroEmprestado.setQuantidade(livroEmprestado.getQuantidade() + 1);

        LocalDate hoje = LocalDate.now();
        emprestimoEscolhido.setDataDevolucao(hoje);

        System.out.println("Devolução realizada com sucesso!");
    }
}
