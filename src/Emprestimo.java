
import java.util.Date;

public class Emprestimo {
    int id;

    Livro livro;

    Usuario usuario;

    Date dataEmprestimo; // Vou usar o tipo Date
    Date dataPrevista;
    Date Datadevolucao;

    public Emprestimo(int id, Livro livro, Usuario usuario, Date dataEmprestimo, Date dataPrevista, Date datadevolucao) {
        this.id = id;
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevista = dataPrevista;
        this.Datadevolucao = datadevolucao;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }
    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }
    
    public Date getDataPrevista() {
        return dataPrevista;
    }
    public void setDataPrevista(Date dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    public Date getDatadevolucao() {
        return Datadevolucao;
    }
    public void setDatadevolucao(Date datadevolucao) {
        Datadevolucao = datadevolucao;
    }

    public Livro getLivro() {
        return livro;
    }
    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
