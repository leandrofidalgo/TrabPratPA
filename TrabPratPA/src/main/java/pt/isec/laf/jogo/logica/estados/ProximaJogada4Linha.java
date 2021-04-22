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
        //Efetuar a proxima jogada
        boolean bool = getDadosJogo().iniciaProximaJogada(coluna);
        if (bool) {
            getDadosJogo().addMsgLog("Jogada efetuada com sucesso e a peca foi colocada na devida coluna!");
            //TODO guardar a jogada e incrementar jogadas (incrementar jogadaas do jogador e do jogo)
            
            return new VerificarSeAcabou(getDadosJogo());
        } else {
            getDadosJogo().addMsgLog("A coluna já se encontra completa por favor tente outra coluna!");
            return this;
        }
    }

    @Override
    public IEstado jogarPecaEspecial() {
        //efetuar a jogada da peca especial
        //verificar se o jogador tem a peca especial
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
