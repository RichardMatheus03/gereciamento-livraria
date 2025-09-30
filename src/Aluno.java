
public class Aluno extends Usuario {

    public Aluno(int matricula, String nome, String curso) {
        super(matricula, nome, curso);
    }

    @Override
    public int getPrazoEmprestimo() {
        return 7;
    }

}
