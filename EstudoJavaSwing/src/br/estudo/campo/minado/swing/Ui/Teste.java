package br.estudo.campo.minado.swing.Ui;

import br.estudo.campo.minado.swing.modelo.Tabuleiro;

public class Teste {

	public static void main(String[] args) {
		Tabuleiro tabuleiro = new Tabuleiro(1, 1, 3);
		tabuleiro.adicionarObservador(e -> {
			if(e.isGanhou()) {
				System.out.println("Ganhou!!");
				return;
			}
			System.out.println("Perdeu!!");
		});
		tabuleiro.marcar(0, 0);
	}

}
