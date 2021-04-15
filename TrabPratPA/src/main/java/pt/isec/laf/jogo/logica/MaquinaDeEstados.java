package pt.isec.laf.jogo.logica;

import pt.isec.laf.jogo.logica.dados.DadosJogo;
import pt.isec.laf.jogo.logica.dados.Jogador;
import pt.isec.laf.jogo.logica.estados.EsperaJogada4Linha;

/**
 *
 * @author leandro
 */
public class MaquinaDeEstados {
    private DadosJogo dadosJogo;
    private Jogador j1, j2;
    private IEstado estado;
    
    public MaquinaDeEstados(){
        dadosJogo = new DadosJogo();
        estado = new EsperaJogada4Linha(dadosJogo);
    }
    
    public void proximaJogada(Jogador j1, Jogador j2){
        this.j1 = j1;
        this.j2 = j2;
        estado = estado.proximaJogada();
    }
    

    

    
}
