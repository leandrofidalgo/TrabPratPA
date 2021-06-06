/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isec.laf.jogo.iu.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pt.isec.laf.jogo.logica.dados.CPU;
import static pt.isec.laf.jogo.logica.dados.DadosJogo.COLUNAS;
import static pt.isec.laf.jogo.logica.dados.DadosJogo.LINHAS;
import pt.isec.laf.jogo.logica.dados.Jogador;
import pt.isec.laf.jogo.logica.dados.Pessoa;

/**
 * FXML Controller class
 *
 * @author leandro
 */
public class ControllerMenuJogada {

    private Aplicacao apli;
    private GridPane gridPane;

    @FXML
    private Button idGuardar;
    @FXML
    private Button idPecaEspecial;
    @FXML
    private Button idRetroceder;
    @FXML
    private Button idTerminarJogo;
    @FXML
    private Text idJogador;
    @FXML
    private FlowPane idFlowPane;
    @FXML
    private Text idTextCreditos;
    @FXML
    private Text idCreditos;
    @FXML
    private Text idTextPecas;
    @FXML
    private Text idPecas;

    public void initialize() {
        apli = Aplicacao.getAplicacao();
        apli.getStage().setMaxWidth(1000);
        apli.getStage().setMaxHeight(850);
        apli.getStage().setMinWidth(1000);
        apli.getStage().setMinHeight(850);
        gridPane = new GridPane();
        if (apli.getMaquinaDeEstadosObservavel().numJogada() == 0) {
            apli.getMaquinaDeEstadosObservavel().adicionaJogada();
        }
        Platform.runLater(() -> {
            imprimirTabuleiro();
        });
        if (apli.getMaquinaDeEstadosObservavel().jogadorAtual() instanceof CPU) {
            Platform.runLater(() -> {
                idTextPecas.setVisible(false);
                idPecas.setVisible(false);
                idTextCreditos.setVisible(false);
                idCreditos.setVisible(false);
                idGuardar.setVisible(false);
                idRetroceder.setVisible(false);
                idPecaEspecial.setVisible(false);
            });
            Thread td = new Thread(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                }
                Platform.runLater(() -> {
                    gridPane.fireEvent(new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, MouseButton.PRIMARY, 1, false, false, false, false, true, false, false, false, false, false, null));
                    //apli.getMaquinaDeEstadosObservavel().jogaPeca(0);
                });
            });
            td.setName("ola");
            td.start();
        }
        gridPane.setOnMouseClicked((e) -> {
            if (apli.getMaquinaDeEstadosObservavel().jogadorAtual() instanceof CPU) {
                apli.getMaquinaDeEstadosObservavel().jogaPeca(0);
                return;
            }
            Integer i = GridPane.getColumnIndex((Node) e.getTarget());
            if (i != null) {
                if (apli.getMaquinaDeEstadosObservavel().jogadorAtual() instanceof Pessoa) {
                    apli.getMaquinaDeEstadosObservavel().jogaPeca(i + 1);
                }
            }
        });
    }

    @FXML
    private void guardarEstadoAtual(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Ficheiro");
        dialog.setGraphic(null);
        dialog.setHeaderText(null);
        dialog.setContentText("Introduza o nome do ficheiro:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            if (!result.get().isBlank()) {
                apli.getMaquinaDeEstadosObservavel().guardarJogo(result.get());
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Guardar jogo");
                alert.setHeaderText(null);
                alert.setContentText("Jogo guardado com sucesso!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erro no nome do ficheiro");
                alert.setHeaderText(null);
                alert.setContentText("O nome do ficheiro está vazio sendo que este não pode ser vazio!");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void efetuarJogadaPecaEspecial(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Peça especial");
        dialog.setGraphic(null);
        dialog.setHeaderText(null);
        dialog.setContentText("Introduza a coluna onde deseja colocar a peça especial:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            apli.getMaquinaDeEstadosObservavel().jogaPecaEspecial(Integer.parseInt(result.get()));
        }
    }

    @FXML
    private void retrocederJogada(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Iterações");
        dialog.setGraphic(null);
        dialog.setHeaderText(null);
        dialog.setContentText("Escolha o número de iterações que deseja recuar (máximo 5):");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            apli.getMaquinaDeEstadosObservavel().voltarAtras(Integer.parseInt(result.get()));
            //imprimirTabuleiro();
        }
    }

    @FXML
    private void terminarJogo(ActionEvent event) {
        apli.getMaquinaDeEstadosObservavel().terminarJogo();
    }

    public void imprimirTabuleiro() {
        int[][] tabuleiro = apli.getMaquinaDeEstadosObservavel().getTabuleiro();
        Jogador jog = apli.getMaquinaDeEstadosObservavel().jogadorAtual();
        gridPane.setAlignment(Pos.CENTER);
        idFlowPane.getChildren().clear();
        idFlowPane.getChildren().add(gridPane);
        gridPane.setStyle("-fx-background-color: gray;");
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(10));
        if (apli.getMaquinaDeEstadosObservavel().jogadorAtual() instanceof CPU) {
            idJogador.setText("É a vez do cpu: " + apli.getMaquinaDeEstadosObservavel().jogardorAtual());
        } else {
            idJogador.setText("É a vez do jogador: " + apli.getMaquinaDeEstadosObservavel().jogardorAtual());
            idCreditos.setText(String.valueOf(jog.getCreditos()));
            //peças especiais
            idPecas.setText(String.valueOf(jog.getNumPecasEspeciais()));
        }
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
