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
    private Emprestimo emprestimo = new Emprestimo();
    private ObservableList<Emprestimo> lista = FXCollections.observableArrayList();
    private TableView<Emprestimo> tableLivroEmpr = new TableView<>();
    private EmprestimoDao emprestimoDao = new EmprestimoDaoImpl();

    public ObservableList<Emprestimo> coloca(){
        lista.clear();
        tableLivroEmpr.getItems().clear();
       // List<Emprestimo> emprestimo = emprestimoDao.colocarValores();
        lista.addAll(emprestimo);
        return lista;
    }

    public void colocarValores(){
        TableColumn<Emprestimo,Integer> colunaCodigo = new TableColumn<>("ISBN");
        TableColumn<Emprestimo, String> colunaTitulo = new TableColumn<>("Titulo");

        colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("CODIGO_LIVRO"));
        colunaTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo_LIVRO"));


        if (tableLivroEmpr.getItems().isEmpty()) {
            tableLivroEmpr.getColumns().addAll(colunaCodigo, colunaTitulo);
        }
        tableLivroEmpr.setMinSize(160, 50);
        tableLivroEmpr.setMaxSize(160, 50);
        tableLivroEmpr.setEditable(false);
        tableLivroEmpr.getItems().clear();
        tableLivroEmpr.getStylesheets().add(EstoqueController.class.getResource("StylesTabela.css").toExternalForm());

    }

    public void Tabela(){
        tableLivroEmpr.getItems().clear();
        tableLivroEmpr.setItems(coloca());
    }

    public TableView<Emprestimo> getTable(){
        return tableLivroEmpr;
    }

    private static void tamanhoCampo(TextField txtField, Integer tamanho){
        txtField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.length() > tamanho){
                txtField.setText(oldValue);
            }
        });
    }

    private static void posiciona(TextField txtField){
        Platform.runLater(() ->{
            if (txtField.getText().length() != 0){
                txtField.positionCaret(txtField.getText().length());
            }
        });
    }

    public static void valida(TextField txtField){
        tamanhoCampo(txtField, 20);
        txtField.lengthProperty().addListener((observable, oldValue, newValue) ->
        {
            String textoDigitado = txtField.getText();
            textoDigitado = textoDigitado.replaceAll("[^0-9]", "");
            txtField.setText(textoDigitado);
            posiciona(txtField);
        });
    }

    private EmprestimoDao empDao = new EmprestimoDaoImpl();

    public Emprestimo pesquisarPorCodigo(long codigo) {

        return empDao.pesquisarPorCodigo(codigo);
    }

    public Emprestimo pesquisarPorCodigoEMPRESTIMO(long codigo) {

        return empDao.pesquisarPorCodigoEMPRESTIMO(codigo);
    }

    public Livro pesquisarPorCodigoLivro(int cod){
        return empDao.pesquisar(cod);
    }


    public void adicionar(Emprestimo Al){
        emprestimoDao.adicionar(Al);
    }

}
