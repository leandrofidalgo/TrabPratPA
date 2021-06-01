/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isec.laf.jogo.iu.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author leandro
 */
public class ControllerMenuJogada implements Initializable {
    private Aplicacao apli;

    @FXML
    private Button idGuardar;
    @FXML
    private Button idPecaEspecial;
    @FXML
    private Button idRetroceder;
    @FXML
    private Button idTerminarJogo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        apli = Aplicacao.getAplicacao();
    }

    @FXML
    private void guardarEstadoAtual(ActionEvent event) {
    }

    @FXML
    private void efetuarJogadaPecaEspecial(ActionEvent event) {
    }

    @FXML
    private void retrocederJogada(ActionEvent event) {
    }

    @FXML
    private void terminarJogo(ActionEvent event) {
        Platform.exit();
    }

}
