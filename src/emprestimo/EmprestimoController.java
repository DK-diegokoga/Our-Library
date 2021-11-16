package emprestimo;

import aluno.Aluno;
import estoque.Estoque;
import estoque.EstoqueController;
import estoque.EstoqueDAOImpl;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import livro.Livro;

import java.util.ArrayList;
import java.util.List;

public class EmprestimoController {

    private EmprestimoDao empDao = new EmprestimoDaoImpl();

    public Emprestimo pesquisarPorCodigo(long codigo) {

        return empDao.pesquisarPorCodigo(codigo);
    }

    public Emprestimo pesquisarPorCodigoEMPRESTIMO(long codigo) {

        return empDao.pesquisarPorCodigoEMPRESTIMO(codigo);
    }

    public Emprestimo pesquisarPorCodigoLivro(int cod){
        return empDao.pesquisarPorCodigoLivro(cod);
    }


    public void adicionar(Emprestimo Al){
        empDao.adicionar(Al);
    }

    public void alterar(Emprestimo Al){
        empDao.alterar(Al);
    }

}
