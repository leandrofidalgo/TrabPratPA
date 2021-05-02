package pt.isec.laf.jogo.logica.estados;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import pt.isec.laf.jogo.logica.IEstado;
import pt.isec.laf.jogo.logica.dados.DadosJogo;

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
                getDadosJogo().addMsgLog("O ficheiro que indicou nao existe!");
                return this;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            getDadosJogo().addMsgLog("Erro ao carregar o ficheiro!");
            return this;
        }
    }

}
