package pt.isec.laf.jogo.logica.estados;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
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
        HashMap<String, ArrayList<Replay>> jogos;
        //primeiro ler o ficheiro e ir buscar o ArrayList serealizado
        try {
            File ficheiro = new File("Jogos");
            if (ficheiro.exists()) {
                FileInputStream fIS = new FileInputStream(ficheiro);
                ObjectInputStream objInput = new ObjectInputStream(fIS);
                jogos = (HashMap<String, ArrayList<Replay>>) objInput.readUnshared();
                objInput.close();
                fIS.close();
                //aqui ja tenho o ArrayList preenchido
                getDadosJogo().setReplay(jogos);
            } else {
                getDadosJogo().addMsgLog("O ficheiro de leitura dos replays não existe e foi criado!");
                ficheiro.createNewFile();
            }
        } catch(EOFException exe){
            
        }catch (Exception ex) {
            getDadosJogo().addMsgLog("Erro ao carregar o ficheiro!");
            return this;
        }
        var replay = getDadosJogo().getReplay();
        //segundo colocar mais um jogo ou retirar conforme necessario
        if (replay.size() >= 5) {
            //retirar o primeiro elemento e colocar no fim o ultimo
            String[] arr = new String[replay.keySet().size()];
            System.arraycopy(replay.keySet().toArray(), 0, arr, 0, replay.keySet().size());
            Arrays.sort(arr);
            replay.remove(arr[0]);
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        replay.put(formatter.format(new Date()), j);
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
