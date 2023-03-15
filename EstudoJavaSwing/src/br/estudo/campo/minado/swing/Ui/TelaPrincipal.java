package br.estudo.campo.minado.swing.Ui;

import javax.swing.JFrame;

import br.estudo.campo.minado.swing.modelo.Tabuleiro;

public class TelaPrincipal extends JFrame{

	private static final long serialVersionUID = 1L;
	
	
	public TelaPrincipal() {
		Tabuleiro tabuleiro = new Tabuleiro(16, 30, 50);
		add(new PainelTbuleiro(tabuleiro));
		setResizable(false);
		setVisible(true);
		setTitle("Campo Minado");
		setSize(690, 438);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	
	public static void main(String[] args) {
		 new TelaPrincipal();
	}

}
