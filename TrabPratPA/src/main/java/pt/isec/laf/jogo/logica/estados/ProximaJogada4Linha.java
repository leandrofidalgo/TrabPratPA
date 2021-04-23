package pt.isec.laf.jogo.logica.estados;

import pt.isec.laf.jogo.logica.IEstado;
import pt.isec.laf.jogo.logica.dados.DadosJogo;
import pt.isec.laf.jogo.logica.dados.Jogador;

/**
 *
 * @author leandro
 */
public class ProximaJogada4Linha extends EstadoAdaptador {

    public ProximaJogada4Linha(DadosJogo dadosJogo) {
        super(dadosJogo);
    }

    @Override
    public IEstado proximaJogada(int coluna) {
        boolean bool = false;
        if (coluna == 0) {
            //inciar a jogada do CPU
            //Efetuar a proxima jogada
            coluna = getDadosJogo().gerarColunaAleatoria();
            bool = getDadosJogo().iniciaProximaJogada(coluna);
            if (bool) {
                getDadosJogo().addMsgLog("Jogada efetuada com sucesso e a peca foi colocada na devida coluna!");
                //TODO guardar a jogada e verificar quantas jogadas existem pois so podem existir 5
                getDadosJogo().adicionaDadosJogaga(getDadosJogo());
                return new VerificarSeAcabou(getDadosJogo());
            } else {
                //getDadosJogo().addMsgLog("A coluna já se encontra completa por favor tente outra coluna!");
                proximaJogada(0);
            }
        } else {
            coluna = coluna - 1;
            //Efetuar a proxima jogada
            //incrementar jogadas (incrementar jogadaas do jogador e do jogo)
            bool = getDadosJogo().iniciaProximaJogada(coluna);
        }
        if (bool) {
            getDadosJogo().addMsgLog("Jogada efetuada com sucesso e a peca foi colocada na devida coluna!");
            //TODO guardar a jogada
            getDadosJogo().adicionaDadosJogaga(getDadosJogo());
            return new VerificarSeAcabou(getDadosJogo());
        } else {
            getDadosJogo().addMsgLog("A coluna já se encontra completa por favor tente outra coluna!");
            return this;
        }
    }

    //TODO criar proxima jogada do CPU
    @Override
    public IEstado jogarPecaEspecial() {
        //efetuar a jogada da peca especial
        //verificar se o jogador tem a peca especial
        //TODO isto esta tudo mal
        for (int i = 0; i < 2; i++) {
            var j = getDadosJogo().getJogadores().get(i);
            if (j.isVezDoJogador()) {
                if (j.getNumPecasEspeciais() > 0) {

                } else {
                    getDadosJogo().addMsgLog("O jogador nao tem pecas especiais!");
                }
            }
        }
        return super.jogarPecaEspecial(); //To change body of generated methods, choose Tools | Templates.
    }

}
