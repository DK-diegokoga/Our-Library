package aluno;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

public class AlunoBoundary extends Application {

    private TextField txtNome = new TextField();
    private TextField txtEmail = new TextField();
    private TextField txtCelular = new TextField();
    private TextField txtRA = new TextField();
    private TextField txtPenalidade = new TextField();
    private TextField txtDescricao = new TextField();

    private Label lblImg = new Label("Carregar imagem\n(ainda não funciona)");

    private ComboBox<String> cbSituacao = new ComboBox<>();
    private String situacao[] = {"REGULAR", "ATRASADO"};

    private Button btnCarregarImagem = new Button("Carregar imagem");
    private Button btnAdd = new Button("Adicionar");
    private Button btnPesq = new Button("Pesquisar");
    private Button btnExcluir = new Button("Excluir");
    private Button btnAlterar = new Button("Alterar");
    private Button btnConcluir = new Button("Concluir");
    private Button btnCancelar = new Button("Cancelar");

    private boolean valido = false;

    private Alert alertWarn = new Alert(Alert.AlertType.WARNING);
    private Alert alertMess = new Alert(Alert.AlertType.INFORMATION);

    private AlunoController control = new AlunoController();

    @Override
    public void start(Stage stage) throws Exception {
        Pane pPane = new Pane();
        Scene scCeneAluno = new Scene(pPane, 500, 330);
        pPane.getStyleClass().add("fundo");
        pPane.getStylesheets().add(AlunoBoundary.class.getResource("btnExcluirStyle.css").toExternalForm());
        AlunoController.valida(txtRA);
        if (cbSituacao.getItems().isEmpty()){
            cbSituacao.getItems().addAll(situacao);
        }


        Label lblRA = new Label("RA:");
        Label lblNome = new Label("Nome:");
        Label lblEmail = new Label("Email:");
        Label lblCelular = new Label("Celular:");
        Label lblSituacao = new Label("Situacao");
        Label lblPenalidade = new Label("Penalidade:");
        Label lblDescricao = new Label("Descricao:");
        Label lblTextoPesq = new Label("*Para pesquisar, insira o codigo do Aluno.");

        lblNome.relocate(140, 23);
        txtNome.relocate(250, 20);
        txtNome.getStylesheets().add(AlunoBoundary.class.getResource("Style.css").toExternalForm());

        lblEmail.relocate(140, 63);
        txtEmail.relocate(250, 60);
        txtEmail.getStylesheets().add(AlunoBoundary.class.getResource("Style.css").toExternalForm());

        lblCelular.relocate(140, 103);
        txtCelular.relocate(250, 100);
        txtCelular.getStylesheets().add(AlunoBoundary.class.getResource("Style.css").toExternalForm());

        lblRA.relocate(20, 153);
        txtRA.relocate(70, 150);

        lblTextoPesq.relocate(70, 180);
        lblTextoPesq.getStylesheets().add(AlunoBoundary.class.getResource("Style.css").toExternalForm());

        lblPenalidade.relocate(20, 223);
        txtPenalidade.relocate(70, 220);

        lblDescricao.relocate(230, 223);
        txtDescricao.relocate(340, 220);

        lblSituacao.relocate(230, 153);
        cbSituacao.relocate(340, 150);

        lblImg.relocate(23, 40);
        lblImg.getStylesheets().add(AlunoBoundary.class.getResource("Style.css").toExternalForm());
        btnCarregarImagem.relocate(18, 80);

        btnAlterar.relocate(250, 280);
        btnAdd.relocate(180, 280);
        btnPesq.relocate(250, 280);
        btnConcluir.relocate(220, 280);
        btnExcluir.relocate(430, 280);
        btnExcluir.getStylesheets().add(AlunoBoundary.class.getResource("Style.css").toExternalForm());
        btnCancelar.relocate(180, 280);

        pPane.getChildren().addAll(lblNome, txtNome, lblEmail, txtEmail, lblCelular, txtCelular, lblRA, txtRA,
                lblSituacao, cbSituacao, btnAdd, btnPesq, btnExcluir, btnAlterar, btnConcluir, btnCancelar, lblPenalidade,
                txtPenalidade, lblDescricao, txtDescricao, btnCarregarImagem, lblImg, lblTextoPesq);

        btnExcluir.setVisible(false);
        btnAlterar.setVisible(false);
        btnConcluir.setVisible(false);
        btnCancelar.setVisible(false);
        btnCarregarImagem.setDisable(true);
        lblImg.setDisable(true);

        btnCarregarImagem.setOnAction((e) -> {
            FileChooser fil_chooser = new FileChooser();
            File file = fil_chooser.showOpenDialog(stage);
            if (file != null) {
                lblImg.setText(file.getAbsolutePath());
            }
        });

        btnAdd.setOnAction((e) -> {
            try {
                valido = control.validarCampos(txtRA.getText(), txtNome.getText(), txtEmail.getText(), txtCelular.getText(),
                        cbSituacao.getValue(), txtPenalidade.getText(), txtDescricao.getText());
                if (valido) {
                    control.adicionar(boundaryToEntity());
                    this.entityToBoundary(new Aluno());
                    alertMess.setHeaderText("CADASTRADO COM SUCESSO!");
                    alertMess.showAndWait();
                }
            } catch (Exception e1) {
                alertWarn.setHeaderText("PREENCHA TODOS OS CAMPOS CORRETAMENTE!");
                alertWarn.showAndWait();
            }
        });

        btnPesq.setOnAction((e) -> {
            try{
                Aluno Al = control.pesquisarPorCodigo(Long.parseLong(txtRA.getText()));
                this.entityToBoundary(Al);
                if (Al != null && txtRA.getText().equals("0")) {
                    txtNome.setEditable(false);
                    txtEmail.setEditable(false);
                    txtCelular.setEditable(false);
                    cbSituacao.setDisable(true);
                    txtPenalidade.setEditable(false);
                    txtDescricao.setEditable(false);
                    btnAdd.setVisible(false);
                    btnPesq.setVisible(false);
                    txtRA.setEditable(false);
                    lblTextoPesq.setVisible(false);
                    btnCancelar.setVisible(true);
                } else if (Al != null) {
                   /* valido = control.validarEmailSenha(txtEmail.getText(), txtConfEmail.getText(), txtSenha.getText(), txtConfSenha.getText());*/
                    btnPesq.setVisible(false);
                    txtRA.setEditable(false);
                    txtNome.setEditable(false);
                    txtEmail.setEditable(false);
                    txtCelular.setEditable(false);
                    cbSituacao.setDisable(true);
                    txtPenalidade.setEditable(false);
                    txtDescricao.setEditable(false);
                    btnAdd.setVisible(false);
                    btnAlterar.setVisible(true);
                    btnExcluir.setVisible(true);
                    btnCancelar.setVisible(false);
                    lblTextoPesq.setVisible(false);
                    btnCancelar.setVisible(true);
                }
            }catch (Exception ee){
                alertWarn.setHeaderText("Não Existe");
                alertWarn.showAndWait();
            }

        });

        btnCancelar.setOnAction((e) -> {
            this.entityToBoundary(new Aluno());
            btnAdd.setVisible(true);
            btnPesq.setVisible(true);
            btnCancelar.setVisible(false);
            btnAlterar.setVisible(false);
            btnExcluir.setVisible(false);
            txtNome.setEditable(true);
            txtEmail.setEditable(true);
            txtCelular.setEditable(true);
            cbSituacao.setDisable(false);
            txtPenalidade.setEditable(true);
            txtDescricao.setEditable(true);
            txtRA.setEditable(true);
            lblTextoPesq.setVisible(true);
        });

        btnExcluir.setOnAction((e) -> {
            try {
                control.excluir(boundaryToEntity());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            alertMess.setHeaderText("Aluno excluido!");
            alertMess.showAndWait();
            this.entityToBoundary(new Aluno());
            btnAdd.setVisible(true);
            btnAlterar.setVisible(false);
            btnExcluir.setVisible(false);
            btnCancelar.setVisible(false);
            btnPesq.setVisible(true);
            txtNome.setEditable(true);
            txtEmail.setEditable(true);
            txtCelular.setEditable(true);
            cbSituacao.setDisable(false);
            txtPenalidade.setEditable(true);
            txtDescricao.setEditable(true);
            txtRA.setEditable(true);
            lblTextoPesq.setVisible(true);
        });

        btnAlterar.setOnAction((e) -> {
            lblTextoPesq.setVisible(false);
            btnConcluir.setVisible(true);
            btnExcluir.setVisible(false);
            btnAlterar.setVisible(false);
            txtRA.setEditable(false);
            txtNome.setEditable(true);
            txtEmail.setEditable(true);
            txtCelular.setEditable(true);
            cbSituacao.setDisable(false);
            txtPenalidade.setEditable(true);
            txtDescricao.setEditable(true);
            btnCancelar.setVisible(false);
        });

        btnConcluir.setOnAction((e) -> {
            valido = control.validarCampos(txtRA.getText(), txtNome.getText(), txtEmail.getText(), txtCelular.getText(),
                    cbSituacao.getValue(), txtPenalidade.getText(), txtDescricao.getText());
            if (valido) {
                control.alterar(boundaryToEntity());
                this.entityToBoundary(new Aluno());
                alertMess.setHeaderText("ALTERADO COM SUCESSO!");
                alertMess.showAndWait();
                btnConcluir.setVisible(false);
                btnExcluir.setVisible(false);
                btnAdd.setVisible(true);
                btnPesq.setVisible(true);
                txtRA.setEditable(true);
                lblTextoPesq.setVisible(true);
            } else {
                alertWarn.setHeaderText("Preencha os campos corretamente!");
                alertWarn.showAndWait();
            }
        });

        stage.setScene(scCeneAluno);
        stage.setTitle("Aluno S2");
        stage.setResizable(false);
        stage.show();
    }

    public Aluno boundaryToEntity() {
        Aluno Al = new Aluno();
        if (valido) {
            Al.setNome(txtNome.getText());
            Al.setEmail(txtEmail.getText());
            Al.setCelular(Long.parseLong(txtCelular.getText()));
            Al.setPenalidade(txtPenalidade.getText());
            Al.setDescricao(txtDescricao.getText());
            try {
                Al.setRA(Long.parseLong(txtRA.getText()));
                Al.setSituacao(cbSituacao.getValue());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            alertWarn.setHeaderText("PREENCHA TODOS OS CAMPOS CORRETAMENTE!");
            alertWarn.showAndWait();
        }
        return Al;
    }

    public void entityToBoundary(Aluno Al) {
        if (Al != null) {
            txtRA.setText(String.valueOf(Al.getRA()));
            txtNome.setText(Al.getNome());
            txtEmail.setText(Al.getEmail());
            txtCelular.setText(String.valueOf(Al.getCelular()));
            txtPenalidade.setText(Al.getPenalidade());
            txtDescricao.setText(Al.getDescricao());
            cbSituacao.setValue(Al.getSituacao());

        } else {
            alertMess.setHeaderText("ALUNO NÃO EXISTE.");
            alertMess.showAndWait();
        }
    }
}
