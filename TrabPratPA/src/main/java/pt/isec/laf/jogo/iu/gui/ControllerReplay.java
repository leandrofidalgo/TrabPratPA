/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isec.laf.jogo.iu.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import pt.isec.laf.jogo.iu.gui.Aplicacao;
import pt.isec.laf.jogo.logica.dados.CPU;
import static pt.isec.laf.jogo.logica.dados.DadosJogo.COLUNAS;
import static pt.isec.laf.jogo.logica.dados.DadosJogo.LINHAS;
import pt.isec.laf.jogo.logica.dados.Replay;

/**
 * FXML Controller class
 *
 * @author leandro
 */
public class ControllerReplay implements Initializable {

    private Aplicacao apli;
    private Circle[][] gridPaneArray = null;
    private int i;

    @FXML
    private Button idTerminarJogo;
    @FXML
    private Text jogadorGanhou;
    @FXML
    private FlowPane idFlowPane;
    @FXML
    private Button btnAvancarReplay;
    @FXML
    private Text idMiniJogo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        apli = Aplicacao.getAplicacao();
        i = 0;
        apli.getStage().setMaxWidth(1000);
        apli.getStage().setMaxHeight(850);
        apli.getStage().setMinWidth(1000);
        apli.getStage().setMinHeight(850);
        Thread td;
        ArrayList<Replay> replay = apli.getMaquinaDeEstadosObservavel().getReplayKey(apli.getReplay());
        td = new Thread(new Runnable() {
            @Override
            public void run() {
                while (i != replay.size()) {
                    try {
                        Thread.sleep(500);
                        Platform.runLater(() -> {
                            btnAvancarReplay.fire();
                        });
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        td.setDaemon(true);
        td.start();
    }

    @FXML
    private void terminarReplay(ActionEvent event) {
        apli.setAndLoadFXMl("MenuPrincipal");
    }

    @FXML
    private void avancarReplay(ActionEvent event) {
        Platform.runLater(() -> {
            replayParaImprimir(apli.getReplay());
        });
    }

    public void replayParaImprimir(String nomeKey) {
        ArrayList<Replay> replay = apli.getMaquinaDeEstadosObservavel().getReplayKey(nomeKey);
        while (i != replay.size()) {
            if (replay.get(i).getTipoReplay().equals(Replay.JOGADA)) {
                if (replay.get(i).getJogador() instanceof CPU) {
                    jogadorGanhou.setText("Jogada efetuada pelo CPU: " + replay.get(i).getJogador().getNome());
                } else {
                    jogadorGanhou.setText("Jogada efetuada pelo jogador: " + replay.get(i).getJogador().getNome());
                }
                imprimirTabuleiro(replay.get(i).getTabuleiro());
            } else if (replay.get(i).getTipoReplay().equals(Replay.MINIJOGO)) {
                idMiniJogo.setText("O jogador " + replay.get(i).getJogador().getNome() + " ganhou o mini jogo!");
            } else if (replay.get(i).getTipoReplay().equals(Replay.MINIJOGOPERDEU)) {
                idMiniJogo.setText("O jogador " + replay.get(i).getJogador().getNome() + " perdeu o mini jogo!");
            }
            i++;
            return;
        }
    }

    public void imprimirTabuleiro(int[][] tabuleiro) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        idFlowPane.getChildren().clear();
        idFlowPane.getChildren().add(gridPane);
        gridPane.setStyle("-fx-background-color: gray;");
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(10));
        for (int k = 0; k < LINHAS; k++) {
            for (int j = 0; j < COLUNAS; j++) {
                Circle circle = new Circle(25);
                if (tabuleiro[k][j] == 0) {
                    circle.setFill(Color.WHITE);
                }
                if (tabuleiro[k][j] == 1) {
                    circle.setFill(Color.RED);
                }
                if (tabuleiro[k][j] == 2) {
                    circle.setFill(Color.YELLOW);
                }
                gridPane.add(circle, j, k);
            }
        }
    }

}
