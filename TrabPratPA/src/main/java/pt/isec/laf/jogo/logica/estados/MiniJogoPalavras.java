package pt.isec.laf.jogo.logica.estados;

import pt.isec.laf.jogo.logica.IEstado;
import pt.isec.laf.jogo.logica.dados.DadosJogo;

/**
 *
 * @author leandro
 */
public class MiniJogoPalavras extends EstadoAdaptador {

    public MiniJogoPalavras(DadosJogo dadosJogo) {
        super(dadosJogo);
    }

    @Override
    public IEstado jogarMiniJogoPalavras(String palavras, String palavrasParaEscrever) {
        int tempo = palavrasParaEscrever.length();
        if (getDadosJogo().getMiniJogo().getStartTime() - System.currentTimeMillis() <= ((tempo / 2) * 1000)) {
            if (palavras.equals(palavrasParaEscrever)) {
                //as palavras do utilziador sao iguais ás que era suposto escrever
                getDadosJogo().retornaJogadorAtual().incrementaNumPecasEspeciais();
                getDadosJogo().addMsgLog("Parabéns ganhou o mini jogo das palavras e como recompensa foi lhe dada uma peça especial!");
                return new ProximaJogada4Linha(getDadosJogo());
            } else {
                //as palavras foram mal escritas
                getDadosJogo().addMsgLog("Perdeu o mini jogo das palavras e como consequencia perdeu a vez de jogar!");
                getDadosJogo().retornaJogadorAtual().incrementaNumJogada();
                return new EscolherProximoJogador(getDadosJogo());
            }
        } else {
            getDadosJogo().addMsgLog("Perdeu o mini jogo das palavras e como consequencia perdeu a vez de jogar!");
            getDadosJogo().retornaJogadorAtual().incrementaNumJogada();
            return new EscolherProximoJogador(getDadosJogo());
        }
    }

}
