package pt.isec.laf.jogo.logica.estados;

import pt.isec.laf.jogo.logica.IEstado;
import pt.isec.laf.jogo.logica.dados.DadosJogo;

/**
 *
 * @author leandro
 */
public class EsperaJogada4Linha extends EstadoAdaptador {

    public EsperaJogada4Linha(DadosJogo dadosJogo) {
        super(dadosJogo);
    }

    @Override //proxima jogada
    public IEstado proximaJogada(DadosJogo dadosJogo) {
        
        return new EsperaMiniJogo(dadosJogo); //To change body of generated methods, choose Tools | Templates.
    }

}
