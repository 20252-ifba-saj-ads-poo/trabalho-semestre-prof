package br.edu.ifba.saj.fwads.controller;

import java.util.UUID;

import br.edu.ifba.saj.fwads.model.Autor;
import br.edu.ifba.saj.fwads.service.AutorService;
import br.edu.ifba.saj.fwads.service.Service;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class CadAutorController {
    @FXML
    private TextField txNome;
    @FXML
    private TextField txEmail;
    @FXML
    private TextField txCPF;

    private MasterController masterController;
    private ListAutorController listAutorController;

    private AutorService serviceAutor = new AutorService();

    public void setMasterController(MasterController masterController) {
        this.masterController = masterController;
    }

    public void setListAutorController(ListAutorController listAutorController) {
        this.listAutorController = listAutorController;
    }

    @FXML
    private void salvarAutor() {
        Autor novoAutor = new Autor(txNome.getText(),
                txEmail.getText(),
                txCPF.getText());
        if (serviceAutor.salvar(novoAutor) != null) {
            new Alert(AlertType.INFORMATION,
                    "Autor:" + novoAutor.getNome() + " cadastrado com sucesso").showAndWait();
            limparTela();
            if (listAutorController != null) {
                listAutorController.loadAutorList();
            }
        } else {
            new Alert(AlertType.ERROR, "Erro ao cadastrar autor").showAndWait();
        }
    }

    @FXML
    private void limparTela() {
        txNome.setText("");
        txEmail.setText("");
        txCPF.setText("");
        // masterController.showFXMLFile("ListAutor.fxml");
        // new Alert(AlertType.INFORMATION,
        // serviceAutor.findAll().toString()).showAndWait();
    }

}
