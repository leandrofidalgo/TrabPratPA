package pt.isec.laf.jogo.iu.gui;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import pt.isec.laf.jogo.logica.dados.CPU;
import static pt.isec.laf.jogo.logica.dados.DadosJogo.COLUNAS;
import static pt.isec.laf.jogo.logica.dados.DadosJogo.LINHAS;

public class ControllerMenuFimDoJogo implements Initializable {

    private Aplicacao apli;
    private FlowPane flowPane;
    private GridPane gridPane;

    @FXML
    private Text idTexto;
    @FXML
    private Button btnIniciarNovoJogo;
    @FXML
    private Button btnMenuPrincipal;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        apli = Aplicacao.getAplicacao();
        //mostrar vencedor e tabuleiro final
        Platform.runLater(() -> {
            apli.getStage().setMaxWidth(600);
            apli.getStage().setMaxHeight(500);
            apli.getStage().setMinWidth(600);
            apli.getStage().setMinHeight(500);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Vencedor");
            alert.setGraphic(null);
            alert.setHeaderText(null);
            flowPane = new FlowPane();
            gridPane = new GridPane();
            imprimirTabuleiro();
            alert.setGraphic(flowPane);
            if (apli.getMaquinaDeEstadosObservavel().vencedor() == null) {
                alert.setContentText("Não existiu nenhum vencedor!");
            } else {
                alert.setContentText("Parabéns, " + apli.getMaquinaDeEstadosObservavel().vencedor() + " acabou de ganhar o jogo do 4 em linha!");
            }
            alert.showAndWait();
        });
        //perguntar se quer guardar para replay
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Replay");
            alert.setGraphic(null);
            alert.setHeaderText(null);
            alert.setContentText("Deseja guardar o Jogo para mais tarde fazer replay?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                apli.getMaquinaDeEstadosObservavel().guardarDadosJogoReplay();
            } else {
                
            }
        });
    }

    @FXML
    private void iniciarNovoJogo(ActionEvent event) {
        apli.getMaquinaDeEstadosObservavel().iniciarNovoJogo();
    }

    @FXML
    private void menuPrincipal(ActionEvent event) {
        apli.getMaquinaDeEstadosObservavel().retornarMenuPrincipal();
    }

    public void imprimirTabuleiro() {
        int[][] tabuleiro = apli.getMaquinaDeEstadosObservavel().getTabuleiro();
        flowPane = new FlowPane();
        gridPane.setAlignment(Pos.CENTER);
        flowPane.getChildren().clear();
        flowPane.getChildren().add(gridPane);
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
