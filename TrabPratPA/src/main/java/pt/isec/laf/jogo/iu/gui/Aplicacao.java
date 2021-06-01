package pt.isec.laf.jogo.iu.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pt.isec.laf.jogo.logica.MaquinaDeEstados;
import pt.isec.laf.jogo.logica.MaquinaDeEstadosObservavel;

/**
 *
 * @author leandro
 */
public class Aplicacao extends Application {

    private MaquinaDeEstadosObservavel maquinaDeEstadosObservavel;
    private Scene scene;
    private Stage stage;
    private HashMap<String, Parent> fxmls;
    private String[] nomeFXMLS = {"EscolherModoJogo", "MenuJogada", "MenuJogarMiniJogo", "MenuPrincipal",
        "MiniJogoCalculos", "MiniJogoPalavras", "ModoCPUxCPU", "ModoPessoaXCPU", "ModoPessoaXPessoa", "Replay"};
    private static Aplicacao aplicacao;
    private static String replay; //Apenas para replay

    public MaquinaDeEstadosObservavel getMaquinaDeEstadosObservavel() {
        return maquinaDeEstadosObservavel;
    }

    public static Aplicacao getAplicacao() {
        return aplicacao;
    }

    public Stage getStage() {
        return stage;
    }

    public static String getReplay() {
        return replay;
    }

    public static void setReplay(String replay) {
        Aplicacao.replay = replay;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void setRoot(String fxml) {
        scene.setRoot(fxmls.get(fxml));
        stage.setTitle(fxml);
    }

    private Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    @Override
    public void start(Stage stage) throws Exception {
        aplicacao = this;
        maquinaDeEstadosObservavel = new MaquinaDeEstadosObservavel(new MaquinaDeEstados());
        fxmls = new HashMap<>();
        CarregarFXMLS();
        scene = new Scene(fxmls.get("MenuPrincipal"));
        this.stage = stage;
        stage.setTitle("Menu Principal");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setOnCloseRequest((t) -> {
            Platform.exit();
        });
        stage.show();
    }

    public void CarregarFXMLS() throws IOException {
        for (String s : nomeFXMLS) {
            fxmls.put(s, loadFXML(s));
        }
    }
}
