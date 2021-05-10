package pt.isec.laf.jogo.logica.estados;

import pt.isec.laf.jogo.logica.IEstado;
import pt.isec.laf.jogo.logica.dados.DadosJogo;

/**
 *
 * @author leandro
 */
public class FimDoJogo extends EstadoAdaptador {

    public FimDoJogo(DadosJogo dadosJogo) {
        super(dadosJogo);
    }

    @Override
    public IEstado iniciarJogo() {
        //zerar dados do jogo tabuleiro
        //TODO guardar 1 jogo cheio de jogos
        getDadosJogo().zerarDadosJogo();
        return new EscolherModoJogo(getDadosJogo());
    }

    @Override
    public IEstado retornarMenuPrincipal() {
        //TODO guardar 1 jogo cheio de jogos
        return new MenuPrincipal(getDadosJogo());
    }

}
