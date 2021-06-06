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
    private String[] nomeFXMLS = {"EscolherModoJogo", "MenuJogada", "MenuJogarMiniJogo", "MenuPrincipal",
        "MiniJogoCalculos", "MiniJogoPalavras", "ModoCPUxCPU", "ModoPessoaXCPU", "ModoPessoaXPessoa", "Replay"};
    private static Aplicacao aplicacao;
    private String replay; //Apenas para replay

    public MaquinaDeEstadosObservavel getMaquinaDeEstadosObservavel() {
        return maquinaDeEstadosObservavel;
    }

    public static Aplicacao getAplicacao() {
        return aplicacao;
    }

    public Stage getStage() {
        return stage;
    }

    public String getReplay() {
        return replay;
    }

    public void setReplay(String replayAlterar) {
       replay = replayAlterar;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void setAndLoadFXMl(String fxml) {
        try {
            scene.setRoot(loadFXML(fxml));
            stage.setTitle(fxml);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    @Override
    public void start(Stage stage) throws Exception {
        aplicacao = this;
        maquinaDeEstadosObservavel = new MaquinaDeEstadosObservavel(new MaquinaDeEstados());
        maquinaDeEstadosObservavel.addPropertyChangeListener("estado", (e) -> {
            if (maquinaDeEstadosObservavel.isMenuPrincipal()) {
                setAndLoadFXMl("MenuPrincipal");
            } else if (maquinaDeEstadosObservavel.isEscolherModoJogo()) {
                setAndLoadFXMl("EscolherModoJogo");
            } else if (maquinaDeEstadosObservavel.isModoCPUXCPU()) {
                setAndLoadFXMl("ModoCPUxCPU");
            } else if (maquinaDeEstadosObservavel.isModoPessoaXCPU()) {
                setAndLoadFXMl("ModoPessoaxCPU");
            } else if (maquinaDeEstadosObservavel.isModoPessoaXPessoa()) {
                setAndLoadFXMl("ModoPessoaxPessoa");
            } else if (maquinaDeEstadosObservavel.isEscolherProximoJogador()) {
                maquinaDeEstadosObservavel.escolheProximoJogador();
            } else if (maquinaDeEstadosObservavel.isProximaJogada4Linha()) {
                setAndLoadFXMl("MenuJogada");
            } else if (maquinaDeEstadosObservavel.isVerificarSeAcabou()) {
                maquinaDeEstadosObservavel.verificaSeAcabou();
            } else if (maquinaDeEstadosObservavel.isEscolherJogarMiniJogo()) {
                setAndLoadFXMl("MenuJogarMiniJogo");
            } else if (maquinaDeEstadosObservavel.isMiniJogoCalculos()) {
                setAndLoadFXMl("MiniJogoCalculos");
            } else if (maquinaDeEstadosObservavel.isMiniJogoPalavras()) {
                setAndLoadFXMl("MiniJogoPalavras");
            } else if (maquinaDeEstadosObservavel.isFimDoJogo()) {
                setAndLoadFXMl("MenuFimDoJogo");
            }
        });
        this.stage = stage;
        scene = new Scene(loadFXML("MenuPrincipal"));
        stage.setTitle("Menu Principal");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setOnCloseRequest((t) -> {
            Platform.exit();
        });
        stage.show();
    }

}
