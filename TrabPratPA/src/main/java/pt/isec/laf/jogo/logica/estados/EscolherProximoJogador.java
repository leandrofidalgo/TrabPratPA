package pt.isec.laf.jogo.logica.estados;

import pt.isec.laf.jogo.logica.IEstado;
import pt.isec.laf.jogo.logica.dados.DadosJogo;

/**
 *
 * @author leandro
 */
public class EscolherProximoJogador extends EstadoAdaptador {

    public EscolherProximoJogador(DadosJogo dadosJogo) {
        super(dadosJogo);
    }

    @Override
    public IEstado definirProximoJogador() {
        //definir proximo jogador  tambem por causa dos minijogos
        //TODO  ver o numero da jogada atual de cada jogador
        if (getDadosJogo().getNum_jogada() == 0) {
            //significa que é a primeira jogada
            int num = getDadosJogo().getRandom(0, 1);
            getDadosJogo().getJogadores().get(num).setVezDoJogador(false);
        } else if (getDadosJogo().retornaJogadorAtual().getNum_jogada() % 4 == 0 && getDadosJogo().retornaJogadorAtual().getNum_jogada() != 0) {
            //quando pode jogar o minijogo e fazer return do estado do mini jogo
            return new EscolherJogarMiniJogo(getDadosJogo());
        }
        //se a vez do jogdar esta a false e passar para true e colocar a false a do outro
        for (int i = 0; i < 2; i++) {
            var j = getDadosJogo().getJogadores().get(i);
            if (j.isVezDoJogador() == false) {
                j.setVezDoJogador(true);
                getDadosJogo().addMsgLog("O jogador escolhido foi: " + j.getNome());
            } else {
                j.setVezDoJogador(false);
            }
            //j.setVezDoJogador(!j.isVezDoJogador());

        }
        //quem estiver a true agora significa que é ele a jogar

        return new ProximaJogada4Linha(getDadosJogo());
    }

}
