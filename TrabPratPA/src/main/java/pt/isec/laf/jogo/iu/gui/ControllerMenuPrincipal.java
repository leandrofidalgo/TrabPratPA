package pt.isec.laf.jogo.iu.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import pt.isec.laf.jogo.logica.MaquinaDeEstadosObservavel;
import pt.isec.laf.jogo.logica.dados.CPU;
import pt.isec.laf.jogo.logica.dados.Replay;

/**
 *
 * @author leandro
 */
public class ControllerMenuPrincipal {

    private Aplicacao apli;

    @FXML
    private Button idIniciarJogo;
    @FXML
    private Button idCarregarJogo;
    @FXML
    private Button idReplay;
    @FXML
    private Button idTerminarJogo;

    @FXML
    private void iniciarJogo(ActionEvent event) {
        apli.getMaquinaDeEstadosObservavel().iniciarNovoJogo();
    }

    @FXML
    private void carregarJogo(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Ficheiro");
        dialog.setGraphic(null);
        dialog.setHeaderText(null);
        dialog.setContentText("Introduza o nome do ficheiro:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            if (!result.get().isBlank()) {
                apli.getMaquinaDeEstadosObservavel().carregarJogo(result.get());
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
    private void replay(ActionEvent event) {
        apli.getMaquinaDeEstadosObservavel().replayJogo();
        var replay = apli.getMaquinaDeEstadosObservavel().getReplay();
        String[] arr = new String[replay.keySet().size()];
        System.arraycopy(replay.keySet().toArray(), 0, arr, 0, replay.keySet().size());
        Arrays.sort(arr);
        List<String> choices = new ArrayList<>();
        for (int i = 0; i < replay.keySet().size(); i++) {
            choices.add(arr[i]);
        }
        ChoiceDialog<String> dialog = new ChoiceDialog<>("", choices);
        dialog.setTitle("Replay");
        dialog.setGraphic(null);
        dialog.setHeaderText(null);
        dialog.setContentText("Escolha o jogo que deseja fazer replay:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            apli.setReplay(result.get());
            Stage st = apli.getStage();
            st.setMaxHeight(850);
            st.setMaxWidth(1000);
            st.setMinHeight(850);
            st.setMinWidth(1000);
            apli.setRoot("Replay");
        }
    }

    @FXML
    private void terminarJogo(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    public void initialize() {
        apli = Aplicacao.getAplicacao();
        apli.getMaquinaDeEstadosObservavel().addPropertyChangeListener("MenuPrincipal", (evt) -> {
            apli.setRoot("EscolherModoJogo");
        });
        apli.getMaquinaDeEstadosObservavel().addPropertyChangeListener("MenuPrincipalCarregar", (evt) -> {
            if (!apli.getMaquinaDeEstadosObservavel().isMenuPrincipal()) {
                Stage st = apli.getStage();
                st.setMaxHeight(850);
                st.setMaxWidth(1000);
                st.setMinHeight(850);
                st.setMinWidth(1000);
                apli.setRoot("MenuJogada");
            }
        });
        /*apli.getMaquinaDeEstadosObservavel().addPropertyChangeListener("MenuPrincipalReplay", (evt) -> {
            apli.setRoot("EscolherModoJogo");
        });*/
    }
    

}
