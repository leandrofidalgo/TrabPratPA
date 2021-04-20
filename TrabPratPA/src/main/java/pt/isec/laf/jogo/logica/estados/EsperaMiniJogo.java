package pt.isec.laf.jogo.logica.estados;

import java.util.Random;
import pt.isec.laf.jogo.iu.texto.IUTexto;
import pt.isec.laf.jogo.logica.IEstado;
import pt.isec.laf.jogo.logica.dados.DadosJogo;

/**
 *
 * @author leandro
 */
public class EsperaMiniJogo extends EstadoAdaptador{
    
    public EsperaMiniJogo(DadosJogo dadosJogo) {
        super(dadosJogo);
    }
    
    @Override
    public IEstado miniJogoCalculos(DadosJogo dadosJogo, int valor, int randomValor1, int randomValor2, String sinal) {
        
        return proximaJogada(dadosJogo);
    }

    @Override
    public IEstado miniJogoPalavras() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
