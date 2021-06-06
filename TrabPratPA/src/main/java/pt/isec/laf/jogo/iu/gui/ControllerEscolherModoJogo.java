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

/**
 * FXML Controller class
 *
 * @author leandro
 */
public class ControllerEscolherModoJogo implements Initializable {

    private Aplicacao apli;

    @FXML
    private Button btnCPUxCPU;
    @FXML
    private Button btnPessoaxCPU;
    @FXML
    private Button btnPessoaxPessoa;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        apli = Aplicacao.getAplicacao();
        apli.getStage().setMaxWidth(500);
        apli.getStage().setMaxHeight(400);
        apli.getStage().setMinWidth(500);
        apli.getStage().setMinHeight(400);
    }

    @FXML
    private void CPUxCPU(ActionEvent event) {
        apli.getMaquinaDeEstadosObservavel().escolherModoJogo(1);
    }

    @FXML
    private void PessoaxCPU(ActionEvent event) {
        apli.getMaquinaDeEstadosObservavel().escolherModoJogo(2);
    }

    @FXML
    private void PessoaxPessoa(ActionEvent event) {
        apli.getMaquinaDeEstadosObservavel().escolherModoJogo(3);
    }

}
