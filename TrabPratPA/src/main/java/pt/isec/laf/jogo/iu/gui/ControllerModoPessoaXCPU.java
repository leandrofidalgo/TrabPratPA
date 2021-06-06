/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isec.laf.jogo.iu.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author leandro
 */
public class ControllerModoPessoaXCPU implements Initializable {

    private Aplicacao apli;

    @FXML
    private TextField nome1;
    @FXML
    private TextField nome2;
    @FXML
    private Button btnConfirmar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        apli = Aplicacao.getAplicacao();
    }

    @FXML
    private void confirmar(ActionEvent event) {
        String str = nome1.getText();
        String str2 = nome2.getText();
        apli.getMaquinaDeEstadosObservavel().definirNomes(str, str2);
    }

}
