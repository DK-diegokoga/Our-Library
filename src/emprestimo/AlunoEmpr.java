package emprestimo;

public class AlunoEmpr {

    private String nome;
    private long RA;
    private long Celular;
    private String DataEmprestimo;

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

    @Override
    public String toString() {
        return   "nome='" + nome + '\'' +
                ", RA=" + RA +
                ", Celular=" + Celular +
                ", Data do emprestimo='" + DataEmprestimo ;
    }
}
