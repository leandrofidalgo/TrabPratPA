package pt.isec.laf.jogo.logica.estados;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import pt.isec.laf.jogo.logica.IEstado;
import pt.isec.laf.jogo.logica.dados.DadosJogo;
import pt.isec.laf.jogo.logica.dados.Replay;

/**
 *
 * @author leandro
 */
public class FimDoJogo extends EstadoAdaptador {

    public FimDoJogo(DadosJogo dadosJogo) {
        super(dadosJogo);
    }

    @Override
    public IEstado iniciarJogo() {
        //zerar dados do jogo tabuleiro
        getDadosJogo().zerarDadosJogo();
        return new EscolherModoJogo(getDadosJogo());
    }

    @Override
    public IEstado retornarMenuPrincipal() {
        return new MenuPrincipal(getDadosJogo());
    }

    @Override
    public IEstado guardarDadosJogo() {
        var j = getDadosJogo().getIteracoes();
        var replay = getDadosJogo().getReplay();

        //primeiro ler o ficheiro e ir buscar o ArrayList serealizado
        /*try {
            
            if (ficheiro.exists()) {
                FileInputStream fIS = new FileInputStream(ficheiro);
                ObjectInputStream objInput = new ObjectInputStream(fIS);
                jogos = (ArrayList<ArrayList<Replay>>) objInput.readUnshared();
                objInput.close();
                fIS.close();
                //aqui ja tenho o ArrayList preenchido
            } else {
                getDadosJogo().addMsgLog("O ficheiro que indicou não existe e foi criado!");
                ficheiro.createNewFile();
                //new
                jogos = new ArrayList<>();
            }
        } catch (Exception ex) {
            getDadosJogo().addMsgLog("Erro ao carregar o ficheiro!");
            return this;
        }*/
        //segundo colocar mais um jogo ou retirar conforme necessario
        if (replay != null) {
            if (replay.size() >= 5) {
                //retirar o primeiro elemento e colocar no fim o ultimo
                replay.remove(0);
            }
        }
        replay.add(j);
        //terceiro serealizar o ArrayList e colocar outra vez no ficheiro
        try {
            File ficheiro = new File("Jogos");
            FileOutputStream fOS = new FileOutputStream(ficheiro);
            ObjectOutputStream objOutput = new ObjectOutputStream(fOS);
            objOutput.writeUnshared(replay);
            objOutput.close();
            fOS.close();
            getDadosJogo().addMsgLog("O estado do jogo foi gravado com sucesso no ficheiro 'Jogos'!");
            getDadosJogo().addMsgLog("Jogo adicionado com sucesso!");
            return this;
        } catch (Exception ex) {
            getDadosJogo().addMsgLog("Houve um problema ao gravar o estado do jogo!");
            return this;
        }
    }
}
