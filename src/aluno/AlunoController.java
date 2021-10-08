package aluno;

import javafx.application.Platform;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import java.util.List;

public class AlunoController {

    private AlunoDAO fnd = new AlunoDAOImpl();


    public void adicionar(Aluno Al) {
        fnd.adicionar(Al);
    }

    public Aluno pesquisarPorCodigo(long codigo) {

        return fnd.pesquisarPorCodigo(codigo);
    }

    public void excluir(Aluno Al) {
        fnd.excluir(Al);
    }

    public void alterar(Aluno Al) {
        fnd.alterar(Al);
    }

    public boolean validarCampos(String txtRA, String txtNome, String txtEmail, String txtCelular, String cbSituacao, String txtPenalidade, String txtDescricao) {
        List<String> lista = new ArrayList<>();
        lista.add(0, txtRA);
        lista.add(1, txtNome);
        lista.add(2, txtEmail);
        lista.add(3, txtCelular);
        lista.add(4, cbSituacao);
        lista.add(5, txtPenalidade);
        lista.add(6, txtDescricao);
        for (String l : lista) {
            if (l == null || l.equals("")) {
                lista.clear();
                return false;
            }
        }
        lista.clear();
       /* return validarEmailSenha(txtEmail, txtConfEmail, txtSenha, txtConfSenha);*/
        return true ;
    }

    /*public boolean validarEmailSenha(String txtEmail, String txtConfEmail, String txtSenha, String txtConfSenha) {
        return txtSenha.equals(txtConfSenha) && txtEmail.equals(txtConfEmail);
    }*/

    /*public void admin() {
        fnd.admin();
    }*/

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
}
