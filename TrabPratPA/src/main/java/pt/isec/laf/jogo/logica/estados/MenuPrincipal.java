package pt.isec.laf.jogo.logica.estados;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import pt.isec.laf.jogo.logica.IEstado;
import pt.isec.laf.jogo.logica.dados.DadosJogo;
import pt.isec.laf.jogo.logica.dados.Replay;

/**
 *
 * @author leandro
 */
public class MenuPrincipal extends EstadoAdaptador {

    public MenuPrincipal(DadosJogo dadosJogo) {
        super(dadosJogo);
    }

    @Override
    public IEstado iniciarJogo() {
        //zerar dados do jogo tabuleiro
        getDadosJogo().zerarDadosJogo();
        return new EscolherModoJogo(getDadosJogo());
    }

    @Override
    public IEstado carregarJogo(String nomeFicheiro) {
        DadosJogo dadosJogo;
        try {
            File ficheiro = new File(nomeFicheiro);
            if (ficheiro.exists()) {
                FileInputStream fIS = new FileInputStream(ficheiro);
                ObjectInputStream objInput = new ObjectInputStream(fIS);
                dadosJogo = (DadosJogo) objInput.readUnshared();
                objInput.close();
                fIS.close();
                setDadosJogo(dadosJogo);
                return new ProximaJogada4Linha(dadosJogo);
            } else {
                getDadosJogo().addMsgLog("O ficheiro que indicou não existe!");
                return this;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            getDadosJogo().addMsgLog("Erro ao carregar o ficheiro!");
            return this;
        }
    }

    @Override
    public IEstado replay() {
        //primeiro ler o ficheiro e ir buscar o ArrayList serealizado
        ArrayList<ArrayList<Replay>> jogos;
        try {
            File ficheiro = new File("Jogos");
            if (ficheiro.exists()) {
                FileInputStream fIS = new FileInputStream(ficheiro);
                ObjectInputStream objInput = new ObjectInputStream(fIS);
                jogos = (ArrayList<ArrayList<Replay>>) objInput.readUnshared();
                objInput.close();
                fIS.close();
                //aqui ja tenho o ArrayList preenchido
                getDadosJogo().setReplay(jogos);
            } else {
                getDadosJogo().addMsgLog("O ficheiro que indicou não existe e foi criado!");
                ficheiro.createNewFile();
                return this;
            }
        } catch (Exception ex) {
            getDadosJogo().addMsgLog("Erro ao carregar o ficheiro!");
            return this;
        }
        return this;
    }

}
