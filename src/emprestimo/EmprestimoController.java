package emprestimo;

import aluno.Aluno;
import estoque.Estoque;
import estoque.EstoqueController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class EmprestimoController {

    private ObservableList<LivroEmpr> lista = FXCollections.observableArrayList();
    private TableView<LivroEmpr> tableLivroEmpr = new TableView<>();

    public ObservableList<LivroEmpr> coloca(){
        lista.clear();
        tableLivroEmpr.getItems().clear();
        //List<LivroEmpr> LivroEmpr = emprestimoDAO.colocarValores();
        //lista.addAll(LivroEmpr);
        return lista;
    }

    public void colocarValores(){
        TableColumn<LivroEmpr,Integer> colunaCodigo = new TableColumn<>("ISBN");
        TableColumn<LivroEmpr, String> colunaTitulo = new TableColumn<>("Titulo");

        colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        colunaTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));


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

    public TableView<LivroEmpr> getTable(){
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
}
