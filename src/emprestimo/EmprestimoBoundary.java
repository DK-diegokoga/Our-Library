package emprestimo;

import aluno.Aluno;
import aluno.AlunoBoundary;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EmprestimoBoundary extends Application {

    private TextField txtNome = new TextField();
    private TextField txtCodEmprestimo = new TextField();
    private TextField txtCelular = new TextField();
    private TextField txtRA = new TextField();
    private TextField txtDataEmprestimo = new TextField();
    private TextField txtDataEntrega = new TextField();
    private TextField txtCodLivro = new TextField();
    private ComboBox<String> cbSituacao = new ComboBox<>();
    private String situacao[] = {"REGULAR", "ATRASADO"};
    private Button btnAdd = new Button("Novo");
    private Button btnAddLivro = new Button("Add");
    private Button btnPesq = new Button("Pesquisar");
    private Button btnExcluir = new Button("Excluir");
    private Button btnAlterar = new Button("Alterar");
    private Button btnConcluir = new Button("Concluir");
    private Button btnCancelar = new Button("Cancelar");

    private boolean valido = false;

    private boolean Vnovo = false;

    private Alert alertWarn = new Alert(Alert.AlertType.WARNING);
    private Alert alertMess = new Alert(Alert.AlertType.INFORMATION);

    private EmprestimoController control = new EmprestimoController();

    @Override
    public void start(Stage stage) {
        Pane pPane = new Pane();
        Scene scCeneEmprestimo = new Scene(pPane, 550, 350);
        pPane.getStyleClass().add("fundo");
        pPane.getStylesheets().add(AlunoBoundary.class.getResource("btnExcluirStyle.css").toExternalForm());
        BorderPane borderPane = new BorderPane();
        control.colocarValores();
        control.Tabela();
        borderPane.setCenter(control.getTable());

        if (cbSituacao.getItems().isEmpty()){
            cbSituacao.getItems().addAll(situacao);
        }

        Label lblRA = new Label("RA:");
        Label lblNome = new Label("Nome:");
        Label lblCodLivro = new Label("Codigo do Livro:");
        Label lblCodEmprestimo = new Label("Codigo do Emprestimo:");
        Label lblCelular = new Label("Celular:");
        Label lblSituacao = new Label("Situacao do Emprestimo");
        Label lblDataEmprestimo = new Label("Data do Emprestimo:");
        Label lblDataEntrega = new Label("Data da Entrega:");
        Label lblTextoPesq = new Label("*Para pesquisar, insira o codigo do Emprestimo.");
        Label lblTextoPesqRA = new Label("*Para pesquisar, insira o codigo do RA.");

        lblNome.relocate(40, 160);
        txtNome.relocate(40, 180);
        txtNome.getStylesheets().add(AlunoBoundary.class.getResource("Style.css").toExternalForm());

        lblCodEmprestimo.relocate(40, 20);
        txtCodEmprestimo.relocate(40, 40);
        txtCodEmprestimo.getStylesheets().add(AlunoBoundary.class.getResource("Style.css").toExternalForm());

        lblCodLivro.relocate(300, 20);
        txtCodLivro.relocate(300, 40);

        lblCelular.relocate(40, 210);
        txtCelular.relocate(40, 230);
        txtCelular.getStylesheets().add(AlunoBoundary.class.getResource("Style.css").toExternalForm());

        lblRA.relocate(40, 110);
        txtRA.relocate(40, 130);
        txtRA.getStylesheets().add(AlunoBoundary.class.getResource("Style.css").toExternalForm());

        lblTextoPesq.relocate(50, 70);
        lblTextoPesq.getStylesheets().add(AlunoBoundary.class.getResource("Style.css").toExternalForm());

        lblTextoPesqRA.relocate(70, 111);
        lblTextoPesqRA.getStylesheets().add(AlunoBoundary.class.getResource("Style.css").toExternalForm());

        lblDataEmprestimo.relocate(40, 260);
        txtDataEmprestimo.relocate(40, 280);

        lblDataEntrega.relocate(325, 150);
        txtDataEntrega.relocate(300, 170);

        lblSituacao.relocate(310, 220);
        cbSituacao.relocate(320, 240);

        borderPane.relocate(300, 80);

        btnAlterar.relocate(330, 280);
        btnAdd.relocate(180, 315);
        btnAddLivro.relocate(460, 40);
        btnPesq.relocate(250, 315);
        btnConcluir.relocate(220, 315);
        btnExcluir.relocate(430, 280);
        btnExcluir.getStylesheets().add(AlunoBoundary.class.getResource("Style.css").toExternalForm());
        btnCancelar.relocate(200, 280);

        pPane.getChildren().addAll(lblNome, txtNome, lblCodEmprestimo, txtCodEmprestimo, lblCelular, txtCelular, lblRA, txtRA,
                lblSituacao, cbSituacao, btnAdd, btnPesq, btnExcluir, btnAlterar, btnConcluir, btnCancelar, lblDataEmprestimo,
                txtDataEmprestimo, lblDataEntrega, txtDataEntrega,  lblTextoPesq, lblCodLivro, txtCodLivro, btnAddLivro,
                borderPane, lblTextoPesqRA);

        btnExcluir.setVisible(false);
        btnAlterar.setVisible(false);
        btnConcluir.setVisible(false);
        btnCancelar.setVisible(false);
        cbSituacao.setDisable(true);
        txtCodLivro.setDisable(true);
        btnAddLivro.setDisable(true);

        txtRA.setDisable(true);
        txtNome.setDisable(true);
        txtCelular.setDisable(true);
        txtDataEmprestimo.setDisable(true);
        txtDataEntrega.setDisable(true);
        lblTextoPesqRA.setVisible(false);

        btnCancelar.setOnAction((e) -> {
            btnCancelar.setVisible(false);
            btnAdd.setVisible(true);
            btnAlterar.setVisible(false);
            btnPesq.setVisible(true);
            btnAddLivro.setDisable(true);
            btnConcluir.setVisible(false);
            Vnovo = false;

            txtCodEmprestimo.setDisable(false);
            txtCodLivro.setDisable(true);
            txtRA.setDisable(true);
            txtDataEmprestimo.setDisable(true);
            txtDataEntrega.setDisable(true);
            cbSituacao.setDisable(true);

            lblTextoPesq.setVisible(true);
            lblTextoPesqRA.setVisible(false);

        });

        btnConcluir.setOnAction((e) -> {
            //salvar tudo
            btnCancelar.fire();

        });

        btnAlterar.setOnAction((e) -> {
            txtDataEntrega.setDisable(false);
            cbSituacao.setDisable(false);
            btnAlterar.setVisible(false);
            btnCancelar.setVisible(true);
            btnConcluir.setVisible(true);
        });

        btnAdd.setOnAction((e) -> {
            try {
                Vnovo = true;
                lblTextoPesqRA.setVisible(true);
                lblTextoPesq.setVisible(false);
                btnAdd.setVisible(false);
                txtRA.setDisable(false);
                txtDataEmprestimo.setDisable(false);
                txtCodLivro.setDisable(false);
                btnAddLivro.setDisable(false);
                txtDataEntrega.setDisable(false);
                btnCancelar.setVisible(true);
                cbSituacao.setDisable(false);

            } catch (Exception e1) {
                alertWarn.setHeaderText("PREENCHA TODOS OS CAMPOS CORRETAMENTE!");
                alertWarn.showAndWait();
            }
        });

        btnPesq.setOnAction((e) -> {
            try{
                if(Vnovo){
                    System.out.println("pesquisando ra");
                    txtRA.setDisable(true);
                    btnConcluir.setVisible(true);
                    btnPesq.setVisible(false);
                }else{
                    System.out.println("pesquisando emprestimo");
                    btnAlterar.setVisible(true);
                    txtCodEmprestimo.setDisable(true);
                    btnPesq.setVisible(false);
                    btnAdd.setVisible(false);
                }
                lblTextoPesqRA.setVisible(false);
            }catch (Exception ee){
                alertWarn.setHeaderText("Não Existe");
                alertWarn.showAndWait();
            }

        });

        stage.setScene(scCeneEmprestimo);
        stage.setTitle("Emprestimo S2");
        stage.setResizable(false);
        stage.show();
    }

    /*public Aluno boundaryToEntity() {
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
    }*/
}