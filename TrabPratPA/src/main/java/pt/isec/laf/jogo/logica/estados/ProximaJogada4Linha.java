package pt.isec.laf.jogo.logica.estados;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
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
                //adicionar o tabuleiro
                getDadosJogo().adicionaJogada();
                //TODO adicionar os jogos para depois fazer o replay
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
                //adicionar o tabuleiro
                getDadosJogo().adicionaJogada();
                //TODO adicionar os jogos para depois fazer o replay
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
            j.decrementarNumPecasEspeciais();
            getDadosJogo().addMsgLog("A peca especial foi colocada na devida posicao!");
            return new VerificarSeAcabou(getDadosJogo());
        } else {
            getDadosJogo().addMsgLog("O jogador nao tem pecas especiais!");
            return this;
        }
    }

    @Override
    public IEstado voltarAtras(int iteracoes) {
        int[][] tabuleiroAnterior = getDadosJogo().getJogadaAnterior(iteracoes);
        if (tabuleiroAnterior == null) {
            getDadosJogo().addMsgLog("O numero de iteracoes que deseja recuar sao demasiadas para o numero de jogadas guardadas!");
            return this;
        } else {
            //se for par continuo a ser eu se n é o outro
            Jogador jogadorAtual = getDadosJogo().retornaJogadorAtual();
            boolean decrementarCreditos = jogadorAtual.decrementarCreditos(iteracoes);
            if (decrementarCreditos) {
                jogadorAtual.setNum_jogada(0);
                getDadosJogo().setTabuleiro(tabuleiroAnterior);
                if (iteracoes % 2 != 0) {
                    //é o jogador atual
                    Jogador outroJogador = getDadosJogo().retornarOOutroJogador();
                    jogadorAtual.setVezDoJogador(false);
                    outroJogador.setVezDoJogador(true);
                }
                return this;
            } else {
                getDadosJogo().addMsgLog("Nao tem creditos suficientes para recuar nas jogadas!");
                return this;
            }

        }

    }

    @Override
    public IEstado guardarJogo(String nomeFicheiro) {
        // serialização do objeto DadosJogo num ficheiro
        File ficheiro = new File(nomeFicheiro);
        try {
            FileOutputStream fOS = new FileOutputStream(ficheiro);
            ObjectOutputStream objOutput = new ObjectOutputStream(fOS);
            objOutput.writeUnshared(getDadosJogo());
            objOutput.close();
            fOS.close();
            getDadosJogo().addMsgLog("O estado do jogo foi gravado com sucesso com o nome: " + nomeFicheiro + "!");
            return this;
        } catch (Exception ex) {
            ex.printStackTrace();
            getDadosJogo().addMsgLog("Houve um problema ao gravar o estado do jogo!");
            return this;
        }

    }

}
