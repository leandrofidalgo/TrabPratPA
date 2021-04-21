package pt.isec.laf.jogo.logica.estados;

import java.util.Scanner;
import pt.isec.laf.jogo.logica.IEstado;
import pt.isec.laf.jogo.logica.MaquinaDeEstados;
import pt.isec.laf.jogo.logica.dados.DadosJogo;

/**
 *
 * @author leandro
 */
public class MenuPrincipal extends EstadoAdaptador {

    public MenuPrincipal(DadosJogo dadosJogo) {
        super(dadosJogo);
    }

    @Override
    public IEstado iniciarJogo() {
        //zerar dados do jogo tabuleiro
        getDadosJogo().zerarDadosJogo();
        return new EscolherModoJogo(getDadosJogo());
    }

    @Override
    public IEstado carregarJogo() {
        return super.carregarJogo(); //To change body of generated methods, choose Tools | Templates.
    }

}
