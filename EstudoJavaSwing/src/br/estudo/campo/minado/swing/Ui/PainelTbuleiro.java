package br.estudo.campo.minado.swing.Ui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import br.estudo.campo.minado.swing.modelo.Tabuleiro;

public class PainelTbuleiro extends JPanel{

	private Tabuleiro tabuleiro;
	
	public PainelTbuleiro(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		setLayout(new GridLayout(tabuleiro.getLinhas(),tabuleiro.getColunas()));
		
		
		tabuleiro.forEach(c -> add(new BotaoCampo(c)));
		tabuleiro.adicionarObservador(e -> {
			SwingUtilities.invokeLater(() -> {
				if(e.isGanhou()) {
					JOptionPane.showMessageDialog(this, "Você ganhou");
			
				}else {
					JOptionPane.showMessageDialog(this, "Você perdeu");
				
				}
				tabuleiro.reiniciarJogo();
			});
			
		});
	}
	
}
