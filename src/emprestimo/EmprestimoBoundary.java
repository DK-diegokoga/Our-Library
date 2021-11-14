package emprestimo;

import aluno.Aluno;
import aluno.AlunoBoundary;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import livro.Livro;

public class EmprestimoBoundary extends Application {

    private TextField txtNome = new TextField();
    private TextField txtCodEmprestimo = new TextField();
    private TextField txtCelular = new TextField();
    private TextField txtRA = new TextField();
    private TextField txtDataEmprestimo = new TextField();
    private TextField txtDataEntrega = new TextField();
    private TextField txtCodLivro = new TextField();
    private TextField txtTitulo = new TextField();
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
        /*control.colocarValores();
        control.Tabela();
        borderPane.setCenter(control.getTable());*/

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
        txtTitulo.relocate(300,80);

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
                borderPane, lblTextoPesqRA,txtTitulo);

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
        txtTitulo.setDisable(true);

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

            Limpar();

        });

        btnConcluir.setOnAction((e) -> {
       /*     control.adicionar(boundaryToEntity());
            this.entityToBoundary(new Emprestimo());
            this.entityToBoundaryEMPRESTIMO(new Emprestimo());
            alertWarn.setHeaderText("CADASTRADO COM SUCESSO!");
            alertWarn.showAndWait();*/
        try{
            control.adicionar(boundaryToEntity());
            //this.entityToBoundaryEMPRESTIMO(new Emprestimo());
            alertMess.setHeaderText("CADASTRADO COM SUCESSO!");
            alertMess.showAndWait();

            btnCancelar.fire();
        } catch (Exception e1) {
            alertWarn.setHeaderText("PREENCHA TODOS OS CAMPOS CORRETAMENTE!");
            alertWarn.showAndWait();
        }
        });

        btnAlterar.setOnAction((e) -> {
            txtDataEntrega.setDisable(false);
            cbSituacao.setDisable(false);
            btnAlterar.setVisible(false);

            btnConcluir.setVisible(true);
        });

        btnAddLivro.setOnAction((e) -> {
        try{
            Emprestimo Lv = control.pesquisarPorCodigoLivro(Integer.parseInt(txtCodLivro.getText()));
            this.entityToBoundaryLivro(Lv);
        }catch (Exception ee){
            alertWarn.setHeaderText("Não Existe");
            alertWarn.showAndWait();
        }
        });

        btnAdd.setOnAction((e) -> {

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
       //         btnConcluir.setVisible(true);
                Limpar();


        });

        btnPesq.setOnAction((e) -> {
            try{
                if(Vnovo){
                    System.out.println("pesquisando ra");
                    txtRA.setDisable(true);
                    btnConcluir.setVisible(true);
                    btnPesq.setVisible(false);

                    Emprestimo Al = control.pesquisarPorCodigo(Long.parseLong(txtRA.getText()));
                    this.entityToBoundary(Al);
                    btnCancelar.setVisible(true);
                }else{
                    System.out.println("pesquisando emprestimo");
                    btnAlterar.setVisible(true);
                    txtCodEmprestimo.setDisable(true);
                    btnPesq.setVisible(false);
                    btnAdd.setVisible(false);
                    Emprestimo Al = control.pesquisarPorCodigoEMPRESTIMO(Long.parseLong(txtCodEmprestimo.getText()));
                    this.entityToBoundaryEMPRESTIMO(Al);

                }
                lblTextoPesqRA.setVisible(false);
            }catch (Exception ee){
                alertWarn.setHeaderText("Não Existe");
                alertWarn.showAndWait();
                btnCancelar.fire();
            }
        });


        stage.setScene(scCeneEmprestimo);
        stage.setTitle("Emprestimo S2");
        stage.setResizable(false);
        stage.show();
    }

    public Emprestimo boundaryToEntity() {
        Emprestimo Al = new Emprestimo();
        if (valido) {
            Al.setNome(txtNome.getText());
            Al.setDataEmprestimo(txtDataEmprestimo.getText());
            Al.setDataEntrega(txtDataEntrega.getText());
            Al.setTitulo(txtTitulo.getText());
            try {
                Al.setCodigo(Integer.parseInt(txtCodEmprestimo.getText()));
                Al.setRA(Long.parseLong(txtRA.getText()));
                Al.setCelular(Long.parseLong(txtCelular.getText()));
                Al.setSituacao(cbSituacao.getValue());
                Al.setISBN(Integer.parseInt(txtCodLivro.getText()));

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            alertWarn.setHeaderText("PREENCHA TODOS OS CAMPOS CORRETAMENTE!");
            alertWarn.showAndWait();
        }
        return Al;
    }

    public void entityToBoundary(Emprestimo Al) {
        if (Al != null) {
            txtRA.setText(String.valueOf(Al.getRA()));
            txtNome.setText(Al.getNome());

            txtCelular.setText(String.valueOf(Al.getCelular()));
            cbSituacao.setValue(Al.getSituacao());

        } else {
            alertMess.setHeaderText("ALUNO NÃO EXISTE.");
            alertMess.showAndWait();
        }
    }

    public void entityToBoundaryEMPRESTIMO(Emprestimo Al) {
        if (Al != null) {
            txtRA.setText(String.valueOf(Al.getRA()));
            txtNome.setText(Al.getNome());

            txtCelular.setText(String.valueOf(Al.getCelular()));
            cbSituacao.setValue(Al.getSituacao());

           txtDataEmprestimo.setText(Al.getDataEmprestimo());
           txtDataEntrega.setText(Al.getDataEntrega());

            txtTitulo.setText(String.valueOf(Al.getISBN()));
            btnCancelar.setVisible(true);
        } else {
            alertMess.setHeaderText("EMPRESTIMO NÃO EXISTE.");
            alertMess.showAndWait();
            btnCancelar.fire();
            btnCancelar.setVisible(false);
        }
    }

    public void entityToBoundaryLivro(Emprestimo Lv) {
        if (Lv != null) {
            //txtTitulo.setText(String.valueOf(Lv.getISBN()));
            txtTitulo.setText(Lv.getTitulo());
            txtCodLivro.setDisable(true);
            btnAddLivro.setDisable(true);
        } else {

            txtCodLivro.clear();
            txtTitulo.clear();
            alertMess.setHeaderText("LIVRO NÃO EXISTE.");
            alertMess.showAndWait();
        }
    }

    public void Limpar(){
        txtNome.clear();
        txtCodEmprestimo.clear();
        txtDataEntrega.clear();
        txtDataEmprestimo.clear();
        txtCelular.clear();
        txtRA.clear();
        txtCodLivro.clear();
        txtTitulo.clear();
    }
}
