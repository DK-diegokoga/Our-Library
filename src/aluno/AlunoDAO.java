package aluno;

public interface AlunoDAO {

    void adicionar(Aluno Al);
    Aluno pesquisarPorCodigo(long RA);
    void excluir(Aluno Al);
    void alterar(Aluno Al);
}
