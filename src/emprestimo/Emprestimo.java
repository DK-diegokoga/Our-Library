package emprestimo;

public class Emprestimo {

    private String nome;
    private long RA;
    private long Celular;
    private String DataEmprestimo;
    private String Situacao;

    private int ISBN;
    private String Titulo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getRA() {
        return RA;
    }

    public void setRA(long RA) {
        this.RA = RA;
    }

    public long getCelular() {
        return Celular;
    }

    public void setCelular(long celular) {
        Celular = celular;
    }

    public String getDataEmprestimo() {
        return DataEmprestimo;
    }

    public void setDataEmprestimo(String dataEmprestimo) {
        DataEmprestimo = dataEmprestimo;
    }

    public String getSituacao() {
        return Situacao;
    }

    public void setSituacao(String situacao) {
        Situacao = situacao;
    }

    public int getISBN() { return ISBN; }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }


    public String toString() {
        return "ISBN=" + ISBN +
                ", Titulo='" + Titulo ;
    }


    public String toStringAluno() {
        return   "nome='" + nome + '\'' +
                ", RA=" + RA +
                ", Celular=" + Celular +
                ", Data do emprestimo='" + DataEmprestimo ;
    }
}