package estoque;

//import categoria.CategoriaBoundary;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import editora.EditoraBoundary;
import livro.Livro;
import livro.LivroBoundary;
//import produto.Produto;
//import produto.ProdutoBoundary;
//import tamanho.TamanhoBoundary;

public class EstoqueBoundary extends Application {

    private Button btnEditora ;
    private Button btnAdicionar ;
    private Button btnPesquisar = new Button("Pesquisar");
    private Button btnFechar;

    private Label lblInfo = new Label("*Para pesquisar, insira o \n" +" codigo do produto.");

    private TextField txtBusca = new TextField();
    private String cssLayout =  "-fx-border-color: gray;\n" +
            "-fx-border-insets: 3;\n" +
            "-fx-border-width: 1;\n" ;
    private String cssBorda =  "-fx-border-color: green;\n" +
            "-fx-border-insets: 3;\n" +
            "-fx-border-width: 2;\n" ;

    private EditoraBoundary editoraTela = new EditoraBoundary();
    private Alert alertWarn = new Alert(Alert.AlertType.WARNING);
    LivroBoundary livroTela = new LivroBoundary();

    public int cod;

    private HBox addBox() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(30, 20, 20, 20));
        hbox.setSpacing(20);
        hbox.getChildren().addAll(lblInfo,txtBusca, btnPesquisar);
        lblInfo.getStylesheets().add(EstoqueBoundary.class.getResource("StylesEstoque.css").toExternalForm());
        txtBusca.getStylesheets().add(EstoqueBoundary.class.getResource("StylesEstoque.css").toExternalForm());
        return hbox;
    }

    private VBox addVbox(){
        VBox vbox = new VBox();
        VBox vbox1 = new VBox();
        VBox vbox2 = new VBox();
        VBox vbox3 = new VBox();

        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);

        Button btns[] = new Button[]{
                btnAdicionar = new Button("Adicionar novo livro"),
                btnEditora = new Button("Gerenciar Editoras"),
                btnFechar = new Button("Fechar"),
        };
        VBox.setMargin(btns[0], new Insets(8, 5, 8, 5));
        vbox1.getChildren().add(btns[0]);
        vbox1.setStyle(cssBorda);
        vbox1.setAlignment(Pos.CENTER);
        VBox.setMargin(btns[1], new Insets(8, 5, 8, 5));
        vbox2.getChildren().add(btns[1]);
        vbox2.setStyle(cssLayout);
        vbox2.setAlignment(Pos.CENTER);
        VBox.setMargin(btns[2], new Insets(8, 5, 8, 5));
        vbox3.getChildren().add(btns[2]);
        vbox3.setStyle(cssLayout);
        vbox3.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(vbox1,vbox2,vbox3);
        return vbox;
    }

    private EstoqueController control = new EstoqueController();

    @Override
    public void start(Stage stage) throws Exception {
        EstoqueController.valida(txtBusca);
        BorderPane borderPane = new BorderPane();
        HBox hbox = addBox();
        borderPane.setTop(hbox);
        control.colocarValores();
        control.Tabela();
        borderPane.setCenter(control.getTable());

        VBox vbox = addVbox();
        vbox.setAlignment(Pos.CENTER);
        BorderPane pane2 = new BorderPane();
        pane2.setTop(vbox);
        borderPane.setRight(pane2);

        Scene scCeneEstoque = new Scene(borderPane, 650, 400);
        btnAdicionar.getStylesheets().add(EstoqueBoundary.class.getResource("StylesEstoque.css").toExternalForm());

        btnEditora.setOnAction((event -> {
            Stage stageEditora = new Stage();
            try {
                System.out.println("Apertou aqui");
                editoraTela.start(stageEditora);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }));

        btnAdicionar.setOnAction((event -> {
            Stage stageLivro = new Stage();
            try {
                System.out.println("Apertou aqui");
                livroTela.start(stageLivro);
                livroTela.entityToBoundary(new Livro());
                stage.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }));

        btnPesquisar.setOnAction((event -> {
            Stage stageProdutoPesq = new Stage();
            try {
                cod = Integer.parseInt(txtBusca.getText());
                System.out.println("Apertou aqui");
                livroTela.start(stageProdutoPesq);
                livroTela.pesquisar(cod);
                stage.close();
            } catch (Exception exception) {
                alertWarn.setHeaderText("Não Existe");
                alertWarn.showAndWait();
            }

        }));

        btnFechar.setOnAction((event -> {
            stage.close();
        }));

        stage.setScene(scCeneEstoque);
        stage.setTitle("Estoque S2");
        stage.setResizable(false);
        stage.show();
    }
}
