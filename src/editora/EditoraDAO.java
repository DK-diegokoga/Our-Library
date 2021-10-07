package editora;

public interface EditoraDAO {
    void adicionar(Editora Ed);
    Editora pesquisarPorCodigo(int txtPesquisar);
    void excluir(Editora Ed);
    void alterar(Editora Ed);
}
