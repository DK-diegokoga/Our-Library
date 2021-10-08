package livro;

import java.util.List;

public interface LivroDAO {
    List<String> carregarEditora();
    int buscarPorNomeEditora(String cbEditoraValue);
    void adicionar(Livro Lv);
    void excluir(int cod);
    void alterar(Livro Lv);
    Livro pesquisarPorCodigo(int cod);
}
