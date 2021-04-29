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
        //verificar se a coluna existe
        if (coluna < 0 && coluna > 7) {
            getDadosJogo().addMsgLog("Esta coluna nao existe!");
            return this;
        }
        if (coluna == 0) {
            //inciar a jogada do CPU
            //Efetuar a proxima jogada
            coluna = getDadosJogo().gerarColunaAleatoria();
            bool = iniciaProximaJogada(coluna);
            if (bool) {
                getDadosJogo().addMsgLog("Jogada efetuada com sucesso e a peca foi colocada na devida coluna!");
                //TODO guardar a jogada e verificar quantas jogadas existem pois so podem existir 
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
            bool = iniciaProximaJogada(coluna);
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
        return this;
    }

    public boolean iniciaProximaJogada(int coluna) {
        //fazer a logica de colocar uma peca
        for (int i = 6 - 1; i >= 0; i--) {
            if (getDadosJogo().getTabuleiro()[i][coluna] == 0) {
                Jogador jog = getDadosJogo().retornaJogadorAtual();
                if (jog == null) {
                    return false;
                } else {
                    int corPeca = jog.getCorDaPeca();
                    getDadosJogo().getTabuleiro()[i][coluna] = corPeca;
                    //incrementar num de jogadas do jogador e do jogo
                    jog.incrementaNumJogada();
                    getDadosJogo().incrementaNumJogadas();
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public IEstado jogarPecaEspecial(int coluna) {
        //efetuar a jogada da peca especial
        //verificar se o jogador tem a peca especial
        //verificar se a coluna existe
        if (coluna <= 0 && coluna > 7) {
            getDadosJogo().addMsgLog("Esta coluna nao existe!");
            return this;
        }
        coluna = coluna - 1;
        var j = getDadosJogo().retornaJogadorAtual();
        if (j.getNumPecasEspeciais() > 0) {
            //colucar na primeira posicao da coluna a contar de baixo e eliminar o que se encontra acima
            int corPeca = j.getCorDaPeca();
            getDadosJogo().getTabuleiro()[5][coluna] = corPeca;
            for (int i = 0; i < 5; i++) {
                getDadosJogo().getTabuleiro()[i][coluna] = 0;
            }
            j.decrementarNumPecasEspeciais(0);
            getDadosJogo().addMsgLog("A peca especial foi colocada na devida posicao!");
            return this;
        } else {
            getDadosJogo().addMsgLog("O jogador nao tem pecas especiais!");
            return this;
        }

    }

    @Override
    public IEstado voltarAtras(int iteracoes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
