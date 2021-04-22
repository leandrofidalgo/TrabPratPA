package pt.isec.laf.jogo.logica.estados;

import pt.isec.laf.jogo.logica.IEstado;
import pt.isec.laf.jogo.logica.dados.DadosJogo;

/**
 *
 * @author leandro
 */
public class VerificarSeAcabou extends EstadoAdaptador {

    public VerificarSeAcabou(DadosJogo dadosJogo) {
        super(dadosJogo);
    }

    @Override
    public IEstado verificarSeAcabou() {
        getDadosJogo().verificaSeJogoAcabou();
        //alterar a vez do jogador para false
        
        return super.verificarSeAcabou(); //To change body of generated methods, choose Tools | Templates.
    }

    
}
