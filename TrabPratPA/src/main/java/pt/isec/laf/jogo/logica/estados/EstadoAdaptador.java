package pt.isec.laf.jogo.logica.estados;

import pt.isec.laf.jogo.logica.IEstado;
import pt.isec.laf.jogo.logica.dados.DadosJogo;

/**
 *
 * @author leandro
 */
public abstract class EstadoAdaptador implements IEstado{
    private DadosJogo dadosJogo;
    
    public EstadoAdaptador(DadosJogo dadosJogo)
    {
        this.dadosJogo = dadosJogo;
    }

    public DadosJogo getDadosJogo()
    {
        return dadosJogo;
    }

    public void setDadosJogo(DadosJogo dadosJogo)
    {
        this.dadosJogo = dadosJogo;
    }

    @Override
    public IEstado proximaJogada(){return this;}
    
    
}
