package aluno;

public class Aluno {

    private String nome;
    private String email;
    private long RA;
    private long Celular;
    private String Situacao;
    private String Penalidade;
    private String Descricao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getSituacao() {
        return Situacao;
    }

    public void setSituacao(String situacao) {
        Situacao = situacao;
    }

    public String getPenalidade() {
        return Penalidade;
    }

    public void setPenalidade(String penalidade) {
        Penalidade = penalidade;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    @Override
    public String toString() {
        return  "Aluno{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", RA=" + RA +
                ", Celular=" + Celular +
                ", Situacao='" + Situacao + '\'' +
                ", Penalidade='" + Penalidade + '\'' +
                ", Descricao='" + Descricao + '\'' +
                '}';
    }
}
