package pt.isec.laf.jogo.iu.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ControllerMiniJogoCalculos implements Initializable {

    private Aplicacao apli;
    private static int i;
    private Thread td;
    private boolean continuar;

    @FXML
    private Text idCalculo;
    @FXML
    private TextField tbCalculo;
    @FXML
    private Button btnConfirmar;
    @FXML
    private Text idTempo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        apli = Aplicacao.getAplicacao();
        continuar = true;
        //se o numero de jogadas do minijogo for 0 começar a thread do contador
        if (apli.getMaquinaDeEstadosObservavel().getNumJogadasMiniJogos() == 0) {
            i = 30;
        }
        td = new Thread(() -> {
            while (i-- > 0) {
                Platform.runLater(() -> {
                    idTempo.setText(String.valueOf(i));
                });
                try {
                    synchronized (this) {
                        wait(1000);
                        if(!continuar){
                            return;
                        }
                    }
                } catch (InterruptedException ex) {
                    return;
                }
            }
            Platform.runLater(() -> {
                btnConfirmar.fire();
            });
        });
        td.setName("TD");
        td.start();
        Platform.runLater(() -> {
            String str = apli.getMaquinaDeEstadosObservavel().getPerguntaCalculos();
            idCalculo.setText(str);
        });
    }

    @FXML
    private void confirmar(ActionEvent event) {
        synchronized (this) {
            continuar = false;
            notifyAll();
        }
        if (!tbCalculo.getText().isEmpty()) {
            try {
                int aux = Integer.parseInt(tbCalculo.getText());
                apli.getMaquinaDeEstadosObservavel().jogarMiniJogoCalculos(aux);
            } catch (Exception e) {
                Platform.runLater(() -> {
                    tbCalculo.setText("");
                });
            }
        }
    }

}
