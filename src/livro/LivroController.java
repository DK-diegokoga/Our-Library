package livro;

import java.util.List;

public class LivroController {
    private LivroDAO livroDAO = new LivroDAOImpl();

    public List<String> carregarEditora(){
        List<String> lista = livroDAO.carregarEditora();
        return lista;
    }


    public int buscarPorNomeEditora(String cbEditoraValue){
        return livroDAO.buscarPorNomeEditora(cbEditoraValue);
    }

    public void adicionar(Livro Lv){
        livroDAO.adicionar(Lv);
    }

    public void excluir(int cod){
        livroDAO.excluir(cod);
    }

    public void alterar(Livro Lv){
        livroDAO.alterar(Lv);
    }

    public Livro pesquisarPorCodigo(int cod){
        return livroDAO.pesquisarPorCodigo(cod);
    }
}
