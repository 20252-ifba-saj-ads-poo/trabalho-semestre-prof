package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.model.Autor;
import br.edu.ifba.saj.fwads.servico.ValidarAutor;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class CadAutorController {

    @FXML
    private TextField txCPF;

    @FXML
    private TextField txEmail;

    @FXML
    private TextField txNome;

    private ValidarAutor validarAutor = new ValidarAutor();

    @FXML
    private void salvarAutor() {
        Autor novoAutor = new Autor(txNome.getText(),
                txEmail.getText(),
                txCPF.getText());
        if (!validarAutor.salvar(novoAutor)) {
            new Alert(AlertType.WARNING,
                    "Autor não pode ser cadastrado pois não preencheu todas as informações obrigatórios").showAndWait();
        } else {
            new Alert(AlertType.INFORMATION, "Cadastrando Autor:" + novoAutor.getNome()).showAndWait();
        }
        limparTela();
    }

    @FXML
    private void limparTela() {
        txNome.setText("");
        txEmail.setText("");
        txCPF.setText("");
    }

}
