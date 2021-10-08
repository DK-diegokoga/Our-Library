package editora;

import funcionario.FuncionarioBoundary;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EditoraBoundary extends Application {

    private TextField txtCodigo = new TextField();
    private TextField txtNome = new TextField();
    private TextField txtPesquisar = new TextField();

    private Button btnPesquisar = new Button("Pesquisar");
    private Button btnAdd = new Button("Adicionar");
    private Button btnRmv = new Button("Remover");
    private Button btnAlt = new Button("Alterar");
    private Button btnCon = new Button("Concluir");
    private Button btnCan = new Button("Cancelar");

    private EditoraController editoraControl = new EditoraController();
    private boolean valido = false;

    private Alert alertWarn = new Alert(Alert.AlertType.WARNING);
    private Alert alertMess = new Alert(Alert.AlertType.INFORMATION);

    @Override
    public void start(Stage Stage) throws Exception {
        Pane pPane = new Pane();
        pPane.getStylesheets().add(EditoraBoundary.class.getResource("StyleText.css").toExternalForm());
        Scene scCeneEditora = new Scene(pPane, 400, 250);
        EditoraController.valida(txtCodigo,txtPesquisar);

        Label lblCodigo = new Label("Codigo:");
        Label lblNome = new Label("Nome:");
        Label lblQuadrado = new Label();

        lblQuadrado.relocate(35, 55);
        lblQuadrado.getStylesheets().add(EditoraBoundary.class.getResource("EditoraStyles.css").toExternalForm());

        txtPesquisar.relocate(65,10);
        txtPesquisar.getStylesheets().add(EditoraBoundary.class.getResource("EditoraStyles.css").toExternalForm());

        btnPesquisar.relocate(300, 10);

        lblCodigo.relocate(50, 70);
        txtCodigo.relocate(50, 90);

        lblNome.relocate(50, 120);
        txtNome.relocate(50, 140);

        btnAdd.relocate(100, 200);
        btnAlt.relocate(50,200);
        btnRmv.relocate(300, 200);
        btnCon.relocate(150, 200);
        btnCan.relocate(150, 200);

        btnAlt.setVisible(false);
        btnCon.setVisible(false);
        btnRmv.setVisible(false);
        btnCan.setVisible(false);

        btnRmv.getStylesheets().add(FuncionarioBoundary.class.getResource("btnExcluirStyle.css").toExternalForm());

        pPane.getChildren().addAll(lblQuadrado, lblCodigo, txtCodigo, lblNome, txtNome, btnAdd, btnPesquisar, btnAlt,
                btnRmv, txtPesquisar, btnCan, btnCon);

        btnAdd.setOnAction((e) -> {
            valido = editoraControl.ValidarCampos(txtCodigo.getText(), txtNome.getText());
            if(valido){
                editoraControl.adicionar(boundaryToEntity());
                alertMess.setHeaderText("CADASTRADO COM SUCESSO!");
                alertMess.showAndWait();
                this.entityToBoundary(new Editora());
            }else{
                alertWarn.setHeaderText("INSIRA OS CAMPOS CORRETAMENTE");
                alertWarn.showAndWait();
            }
        });

        btnPesquisar.setOnAction((e) ->{
            try{
                Editora Ed = editoraControl.pesquisarPorCodigo(Integer.parseInt(txtPesquisar.getText()));
                this.entityToBoundary(Ed);
                if(Ed!= null){
                    btnPesquisar.setVisible(false);
                    txtPesquisar.setEditable(false);
                    txtCodigo.setEditable(false);
                    txtNome.setEditable(false);
                    btnAdd.setVisible(false);
                    btnAlt.setVisible(true);
                    btnRmv.setVisible(true);
                    btnCan.setVisible(true);
                }
            }catch (Exception ee){
                alertWarn.setHeaderText("Não Existe");
                alertWarn.showAndWait();
            }
        });

        btnCan.setOnAction((event -> {
            this.entityToBoundary(new Editora());
            btnPesquisar.setVisible(true);
            txtPesquisar.setEditable(true);
            txtCodigo.setEditable(true);
            txtNome.setEditable(true);
            btnAdd.setVisible(true);
            btnAlt.setVisible(false);
            btnRmv.setVisible(false);
            btnCan.setVisible(false);
        }));

        btnAlt.setOnAction((event -> {
            btnAlt.setVisible(false);
            btnRmv.setVisible(false);
            btnCon.setVisible(true);
            btnCan.setVisible(false);
            txtNome.setEditable(true);
        }));

        btnRmv.setOnAction((event -> {
            try {
                editoraControl.excluir(boundaryToEntity());
            }catch (Exception e1) {
                e1.printStackTrace();
            }
            alertMess.setHeaderText("Marca excluida!");
            alertMess.showAndWait();
            btnCan.fire();
        }));

        btnCon.setOnAction((event -> {
            valido = editoraControl.ValidarCampos(txtCodigo.getText(), txtNome.getText());
            if (valido){
                editoraControl.alterar(boundaryToEntity());
                alertMess.setHeaderText("ALTERADO COM SUCESSO!");
                alertMess.showAndWait();
                btnCon.setVisible(false);
                btnCan.fire();
            } else {
                alertWarn.setHeaderText("Preencha os campos corretamente!");
                alertWarn.showAndWait();
            }
        }));

        Stage.setScene(scCeneEditora);
        Stage.setTitle("Editora S2");
        Stage.setResizable(false);
        Stage.show();
    }

    public Editora boundaryToEntity() {
        Editora Ed = new Editora();
        Ed.setNOME_EDITORA(txtNome.getText());
        try {
            Ed.setCODIGO_EDITORA(Integer.parseInt(txtCodigo.getText()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Ed;
    }

    public void entityToBoundary(Editora Ed) {
        if (Ed != null) {
            txtCodigo.setText(String.valueOf(Ed.getCODIGO_EDITORA()));
            txtNome.setText(Ed.getNOME_EDITORA());
        } else {
            alertMess.setHeaderText("EDITORA NÃO EXISTE.");
            alertMess.showAndWait();
        }
    }
}