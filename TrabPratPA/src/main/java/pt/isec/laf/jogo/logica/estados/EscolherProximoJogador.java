package pt.isec.laf.jogo.logica.estados;

import pt.isec.laf.jogo.logica.IEstado;
import pt.isec.laf.jogo.logica.dados.DadosJogo;
import pt.isec.laf.jogo.logica.dados.Jogador;
import pt.isec.laf.jogo.logica.dados.Pessoa;

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
        }
        proximoJogador();
        if (getDadosJogo().retornaJogadorAtual().getNum_jogada() % 4 == 0 && getDadosJogo().retornaJogadorAtual().getNum_jogada() != 0 && getDadosJogo().retornaJogadorAtual() instanceof Pessoa) {
            //quando pode jogar o minijogo e fazer return do estado do mini jogo
            //quem estiver a true agora significa que é ele a jogar
            return new EscolherJogarMiniJogo(getDadosJogo());
        }

        //quem estiver a true agora significa que é ele a jogar
        return new ProximaJogada4Linha(getDadosJogo());
    }

    public void proximoJogador() {
        //se a vez do jogdar esta a false e passar para true e colocar a false a do outro
        for (int i = 0; i < 2; i++) {
            var j = getDadosJogo().getJogadores().get(i);
            if (j.isVezDoJogador() == false) {
                j.setVezDoJogador(true);
                getDadosJogo().addMsgLog("É a vez do jogador: '" + j.getNome() + "' que tem a peça " + ((j.getCorDaPeca() == 1) ? "'O'" : "'X'") + ",que tem " + j.getCreditos() 
                        + " créditos e tem " + j.getNumPecasEspeciais() + " peças especiais!");
            } else {
                j.setVezDoJogador(false);
            }
            //j.setVezDoJogador(!j.isVezDoJogador());

        }
    }

}
