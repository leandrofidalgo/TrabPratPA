package pt.isec.laf.jogo.iu.gui;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ControllerMiniJogoPalavras implements Initializable {

    private Aplicacao apli;
    private String palavras;
    private int i;
    @FXML
    private Text idPalavras;
    @FXML
    private TextField tbPalavras;
    @FXML
    private Button btnConfirmar;
    @FXML
    private Text idTempo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        apli = Aplicacao.getAplicacao();
        palavras = apli.getMaquinaDeEstadosObservavel().getPerguntaPalavras();
        Platform.runLater(() -> {
            idPalavras.setText(palavras);
        });
        int tempo = palavras.length();
        i = tempo / 2;
        Thread td = new Thread(() -> {
            while (i-- > 0) {
                Platform.runLater(() -> {
                    idTempo.setText(String.valueOf(i));
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    return;
                }

            }
            Platform.runLater(() -> {
                btnConfirmar.fire();
            });
        });
        td.start();
    }

    @FXML
    private void confirmar(ActionEvent event) {
        if (!tbPalavras.getText().isEmpty()) {
            apli.getMaquinaDeEstadosObservavel().jogarMiniJogoPalavras(tbPalavras.getText(), palavras);
        }
    }

}
