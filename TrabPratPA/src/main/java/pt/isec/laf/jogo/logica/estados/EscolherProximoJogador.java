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
        //TODO definir proximo jogador e ver o numero da jogada atual tambem por causa dos minijogos
        if (getDadosJogo().getNum_jogada() == 0) {
            //significa que é a primeira jogada
            int num = getDadosJogo().getRandom(0, 1);
            getDadosJogo().getJogadores().get(num).setVezDoJogador(false);
        } else if (getDadosJogo().getNum_jogada() == 3 || getDadosJogo().getNum_jogada() == 7) {
            //verificar quando pode jogar o minijogo
        }
        //verificar se a vez do jogdar esta a false e passar para true e colocar a false a do outro
        for (int i = 0; i < 2; i++) {
            if(getDadosJogo().getJogadores().get(i).isVezDoJogador() == false){
                getDadosJogo().getJogadores().get(i).setVezDoJogador(true);
            }else{
                getDadosJogo().getJogadores().get(i).setVezDoJogador(false);
            }
        }
        //quem estiver a true agora significa que é ele a jogar
        return new ProximaJogada4Linha(getDadosJogo());
    }

}
