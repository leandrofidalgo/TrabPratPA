package pt.isec.laf.jogo.logica.estados;

import pt.isec.laf.jogo.logica.IEstado;
import pt.isec.laf.jogo.logica.dados.DadosJogo;

/**
 *
 * @author leandro
 */
public class MiniJogoCalculos extends EstadoAdaptador {

    public MiniJogoCalculos(DadosJogo dadosJogo) {
        super(dadosJogo);
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
            //TODO verificar se ganhou
            //setar o num vezes que ganhou a 0
            //adicionar pecas especiais caso ganhe
            if (j.getNumVezesQueGanhou() == 5) {
                //ganhou
                getDadosJogo().retornaJogadorAtual().incrementaNumPecasEspeciais();
                getDadosJogo().addMsgLog("Parabéns ganhou o mini jogo dos calculos e como recompensa foi lhe dada uma peça especial!");
                return new ProximaJogada4Linha(getDadosJogo());
            } else {
                //perdeu
                //getDadosJogo().retornaJogadorAtual().setVezDoJogador(false);
                //incrementar a vez do jogador e a jogdada do jogo
                getDadosJogo().addMsgLog("Perdeu o mini jogo dos calculos e como consequencia perdeu a vez de jogar!");
                getDadosJogo().retornaJogadorAtual().incrementaNumJogada();
                getDadosJogo().incrementaNumJogadas();
                return new EscolherProximoJogador(getDadosJogo());
            }
        }

    }
}
