package pt.isec.laf.jogo.logica.estados;

import pt.isec.laf.jogo.logica.IEstado;
import pt.isec.laf.jogo.logica.dados.DadosJogo;


/**
 *
 * @author leandro
 */
public class EsperaJogada4Linha extends EstadoAdaptador{

    public EsperaJogada4Linha(DadosJogo dadosJogo) {
        super(dadosJogo);
    }

    @Override
    public IEstado verificarSeAlguemGanhou() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
