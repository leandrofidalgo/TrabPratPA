package pt.isec.laf.jogo.logica.estados;

import pt.isec.laf.jogo.logica.IEstado;
import pt.isec.laf.jogo.logica.dados.DadosJogo;
import pt.isec.laf.jogo.logica.dados.Replay;

/**
 *
 * @author leandro
 */
public class MiniJogoCalculos extends EstadoAdaptador {

    public MiniJogoCalculos(DadosJogo dadosJogo) {
        super(dadosJogo);
        dadosJogo.comecarMiniJogo();
    }

    @Override
    public IEstado jogarMiniJogoCalculos(int valor) {
        var j = getDadosJogo().getMiniJogo();
        if (System.currentTimeMillis() - j.getStartTime() <= 30000 && j.getNumVezesQueGanhou() != 5) {
            switch (j.getRandomSinal()) {
                case 1:
                    if (valor == (j.getRandomValor1() + j.getRandomValor2())) {
                        getDadosJogo().addMsgLog("Acertou!");
                        j.incrementaNumVezesQueGanhou();
                    } else {
                        getDadosJogo().addMsgLog("Errou!");
                    }
                    j.incrementaNumVezesQueJogouMiniJogo();
                    return this;
                case 2:
                    if (valor == (j.getRandomValor1() - j.getRandomValor2())) {
                        getDadosJogo().addMsgLog("Acertou!");
                        j.incrementaNumVezesQueGanhou();
                    } else {
                        getDadosJogo().addMsgLog("Errou!");
                    }
                    j.incrementaNumVezesQueJogouMiniJogo();
                    return this;
                case 3:
                    if (valor == (j.getRandomValor1() / j.getRandomValor2())) {
                        getDadosJogo().addMsgLog("Acertou!");
                        j.incrementaNumVezesQueGanhou();
                    } else {
                        getDadosJogo().addMsgLog("Errou!");
                    }

                    j.incrementaNumVezesQueJogouMiniJogo();
                    return this;
                case 4:
                    if (valor == (j.getRandomValor1() * j.getRandomValor2())) {
                        getDadosJogo().addMsgLog("Acertou!");
                        j.incrementaNumVezesQueGanhou();
                    } else {
                        getDadosJogo().addMsgLog("Errou!");
                    }
                    j.incrementaNumVezesQueJogouMiniJogo();
                    return this;
                default:
                    return this;
            }
        } else {
            //setar o num vezes que ganhou a 0
            //adicionar pecas especiais caso ganhe
            if (j.getNumVezesQueGanhou() == 5) {
                //ganhou
                j.setNumVezesQueJogouMiniJogo(0);
                getDadosJogo().retornaJogadorAtual().incrementaNumPecasEspeciais();
                getDadosJogo().addMsgLog("Parabéns ganhou o mini jogo dos cálculos e como recompensa foi lhe dada uma peça especial!");
                getDadosJogo().adicionaMiniJogo(Replay.MINIJOGO);
                return new ProximaJogada4Linha(getDadosJogo());
            } else {
                //perdeu
                //getDadosJogo().retornaJogadorAtual().setVezDoJogador(false);
                //incrementar a vez do jogador e a jogdada do jogo
                j.setNumVezesQueJogouMiniJogo(0);
                getDadosJogo().addMsgLog("Perdeu o mini jogo dos calculos e como consequencia perdeu a vez de jogar!");
                getDadosJogo().retornaJogadorAtual().incrementaNumJogada();
                getDadosJogo().incrementaNumJogadas();
                getDadosJogo().adicionaMiniJogo(Replay.MINIJOGOPERDEU);
                return new EscolherProximoJogador(getDadosJogo());
            }
        }

    }
}
