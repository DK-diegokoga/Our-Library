package livro;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import editora.Editora;
import editora.EditoraBoundary;
import editora.EditoraController;


public class LivroBoundary extends Application {

    private TextField txtTitulo = new TextField();
    private TextField txtISBN = new TextField();
    private TextField txtAutor = new TextField();

    private ComboBox<String> cbEditora = new ComboBox<>();

    private TextField txtQuantidade = new TextField();


    private Button btnAdicionar = new Button("Adicionar");
    private Button btnAlterar = new Button("Alterar");
    private Button btnExcluir = new Button("Excluir");
    private Button btnConcluir = new Button("Concluir");

    LivroController livroCont = new LivroController();
    private EditoraController edControl = new EditoraController();

    private String cbEditoraValue;

    private String cssBorda =   "-fx-border-color: grey;\n" +
            "-fx-border-insets: 3;\n" +
            "-fx-border-width: 1;\n" ;

    private Alert alertWarn = new Alert(Alert.AlertType.WARNING);
    private Alert alertMess = new Alert(Alert.AlertType.INFORMATION);

    private HBox addBox() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(20, 10, 10, 15));
        hbox.setSpacing(10);
        return hbox;
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane border = new BorderPane();
        HBox hbox = addBox();
        border.setTop(hbox);
        border.getStylesheets().add(EditoraBoundary.class.getResource("StyleText.css").toExternalForm());
        btnAdicionar.setVisible(true);
        txtTitulo.setEditable(true);
        txtISBN.setEditable(true);
        txtAutor.setEditable(true);
        txtQuantidade.setEditable(true);

        cbEditora.setDisable(false);
        btnConcluir.setVisible(false);
        btnAlterar.setVisible(false);
        btnExcluir.setVisible(false);

        carregarCombo();

        BorderPane bP = new BorderPane();
        GridPane gridPane = new GridPane();
        Scene scene = new Scene(border, 700, 450);
        gridPane.setVgap(20);
        gridPane.setHgap(5);
        bP.setCenter(gridPane);
        border.setCenter(bP);

        Label lblNome = new Label("Titulo:");
        Label lblCodigo = new Label("ISBN:");
        Label lblAutor = new Label("Autor:");
        Label lblEditora = new Label("Editora:");

        Label lblQuantidade = new Label("Quantidade em Estoque:");


        gridPane.add(lblNome,0,1);
        gridPane.add(txtTitulo,1,1);
        gridPane.add(lblCodigo,0,2);
        gridPane.add(txtISBN,1,2);
        gridPane.add(lblAutor,2,2);
        gridPane.add(txtAutor,3,2);
        gridPane.add(lblEditora,0,3);
        gridPane.add(cbEditora,1,3);
        gridPane.add(lblQuantidade,2,5);
        gridPane.add(txtQuantidade,3,5);



        BorderPane pane = new BorderPane();
        AnchorPane anchorPane = new AnchorPane();
        HBox box = new HBox();
        box.setPadding(new Insets(0,40,10,20));
        box.setSpacing(10);

        anchorPane.getChildren().addAll(box);
        HBox box2 = new HBox();
        box2.getChildren().addAll(btnAlterar,btnConcluir,btnExcluir, btnAdicionar);
        btnExcluir.getStylesheets().add(LivroBoundary.class.getResource("StylesLivro.css").toExternalForm());
        box2.setPadding(new Insets(0,0,5,250));
        box2.setSpacing(20);
        bP.setStyle(cssBorda);
        bP.setBottom(anchorPane);
        pane.setBottom(box2);
        border.setBottom(pane);

        btnAdicionar.setOnAction((event -> {
            livroCont.adicionar(boundaryToEntity());
            this.entityToBoundary(new Livro());
            alertMess.setHeaderText("CADASTRADO COM SUCESSO!");
            alertMess.showAndWait();
        }));

        btnExcluir.setOnAction((event -> {
            try{
                livroCont.excluir(Integer.parseInt(txtISBN.getText()));
            }catch (Exception e){

            }
            alertMess.setHeaderText("EXCLUIDO COM SUCESSO!");
            alertMess.showAndWait();
            btnAlterar.setVisible(false);
            btnExcluir.setVisible(false);
            stage.close();
        }));

        btnAlterar.setOnAction((event -> {
            txtTitulo.setEditable(true);
            txtAutor.setEditable(true);

            txtQuantidade.setEditable(true);

            cbEditora.setDisable(false);

            btnExcluir.setVisible(false);
            btnConcluir.setVisible(true);
            btnAlterar.setVisible(false);
        }));

        btnConcluir.setOnAction((event -> {
            livroCont.alterar(boundaryToEntity());
            alertMess.setHeaderText("ALTERADO COM SUCESSO!");
            alertMess.showAndWait();
            btnConcluir.setVisible(false);

            stage.close();
        }));

        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("Produto S2");
        stage.show();
    }

    public Livro boundaryToEntity(){
        Livro Lv = new Livro();
        Lv.setTITULO(txtTitulo.getText());
        Lv.setAUTOR(txtAutor.getText());

        cbEditoraValue = cbEditora.getValue();

        try {
            Lv.setISBN(Integer.parseInt(txtISBN.getText()));
            Lv.setQUANTIDADE_ESTOQUE(Integer.parseInt(txtQuantidade.getText()));

            Lv.setEDITORA(livroCont.buscarPorNomeEditora(cbEditoraValue));
        }catch (Exception e){

        }
        return Lv;
    }

    public boolean entityToBoundary(Livro Lv) {
        try {
            if (Lv != null) {
                Editora ed;

                txtISBN.setText(String.valueOf(Lv.getISBN()));
                txtTitulo.setText(Lv.getTITULO());

                txtAutor.setText(Lv.getAUTOR());
                txtQuantidade.setText(String.valueOf(Lv.getQUANTIDADE_ESTOQUE()));


                ed = edControl.pesquisarPorCodigo(Lv.getEDITORA());
                cbEditora.setValue(ed.getNOME_EDITORA());

                return true;
            } else {
                entityToBoundary(new Livro());
                alertMess.setHeaderText("Livro NÃO EXISTE.");
                alertMess.showAndWait();
            }
        }catch (Exception e){

        }
        return false;
    }

    public void carregarCombo(){
        cbEditora.getItems().removeAll(cbEditora.getItems());
        cbEditora.getItems().addAll(livroCont.carregarEditora());
    }

    public void pesquisar(int cod){
        txtTitulo.setEditable(false);
        txtISBN.setEditable(false);
        txtAutor.setEditable(false);

        txtQuantidade.setEditable(false);

        cbEditora.setDisable(true);
        btnConcluir.setVisible(false);
        btnAdicionar.setVisible(false);
        if(entityToBoundary(livroCont.pesquisarPorCodigo(cod))){
            btnAlterar.setVisible(true);
            btnExcluir.setVisible(true);
        }
    }
}
