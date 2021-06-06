package pt.isec.laf.jogo.iu.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class ControllerMenuJogarMiniJogo implements Initializable {

    private Aplicacao apli;

    @FXML
    private Button btnSim;
    @FXML
    private Button btnNao;
    @FXML
    private TextArea idTexto;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        apli = Aplicacao.getAplicacao();
        apli.getStage().setMaxWidth(600);
        apli.getStage().setMaxHeight(500);
        apli.getStage().setMinWidth(600);
        apli.getStage().setMinHeight(500);
        idTexto.setText("Parabéns, o jogador '" + apli.getMaquinaDeEstadosObservavel().jogardorAtual() + "' tem a opção de jogar um mini jogo para ter a oportunidade de ganhar uma peça especial!!" + 
                            "Deseja jogar o mini jogo?");
    }

    @FXML
    private void sim(ActionEvent event) {
        Platform.runLater(() -> {
            apli.getMaquinaDeEstadosObservavel().jogarMiniJogo();
        });
    }

    @FXML
    private void nao(ActionEvent event) {
        Platform.runLater(() -> {
            apli.getMaquinaDeEstadosObservavel().naoJogarMiniJogo();
        });
    }

}
