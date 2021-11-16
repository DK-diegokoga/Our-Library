package emprestimo;

import livro.Livro;

import java.util.List;

public interface EmprestimoDao {

    //List<Emprestimo> colocarValores();

    void adicionar(Emprestimo Al);

    void alterar(Emprestimo Al);

    Emprestimo pesquisarPorCodigo(long codigo);

    Emprestimo pesquisarPorCodigoEMPRESTIMO(long codigo);

    Emprestimo pesquisarPorCodigoLivro(int codigo);
}

