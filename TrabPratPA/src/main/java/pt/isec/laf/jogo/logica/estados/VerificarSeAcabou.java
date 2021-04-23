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
        boolean ganhou = getDadosJogo().verificaSeJogoAcabou();
        if(ganhou == true){
            return new FimDoJogo(getDadosJogo());
        }else{
            return new EscolherProximoJogador(getDadosJogo());
        }
    }

    
}
