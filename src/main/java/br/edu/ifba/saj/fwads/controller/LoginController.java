/**
 * Sample Skeleton for 'Login.fxml' Controller Class
 */

package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.App;
import br.edu.ifba.saj.fwads.exception.AutenticacaoInvalidaException;
import br.edu.ifba.saj.fwads.model.Usuario;
import br.edu.ifba.saj.fwads.servico.ValidarUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class LoginController {

    @FXML // fx:id="txSenha"
    private PasswordField txSenha; // Value injected by FXMLLoader

    @FXML // fx:id="txUsuario"
    private TextField txUsuario; // Value injected by FXMLLoader

    ValidarUsuario validarUsuario = new ValidarUsuario();

    @FXML
    void entrar(ActionEvent event) {
        Usuario usuario = new Usuario(txUsuario.getText(), txSenha.getText());
        try {
            validarUsuario.logar(usuario);
            new Alert(AlertType.INFORMATION, "Usuário e senha corretos").showAndWait();
            App.setRoot("controller/Master.fxml");
        } catch (AutenticacaoInvalidaException e) {
            new Alert(AlertType.ERROR, "Usuário ou senha inválidos").show();
        }

    }

    @FXML
    void limparCampos(ActionEvent event) {
        txUsuario.setText("");
        txSenha.setText("");
    }

}
