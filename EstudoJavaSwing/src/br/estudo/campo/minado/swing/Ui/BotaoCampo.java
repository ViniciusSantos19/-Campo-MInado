package br.estudo.campo.minado.swing.Ui;

import java.awt.Color;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import org.w3c.dom.events.MouseEvent;

import br.estudo.campo.minado.swing.modelo.Campo;
import br.estudo.campo.minado.swing.modelo.CampoEstado;
import br.estudo.campo.minado.swing.modelo.CampoObservador;

public class BotaoCampo extends JButton implements CampoObservador, EventoClickMouse{

	private Campo campo;
	private Color BG_PADRAO = new Color(184, 184, 184);
	private Color BG_MARCADO = new Color(8, 179, 247);
	private Color BG_EXPLODIDO = new Color(189, 66, 69);
	private Color TEXTO_VERDE = new Color(0, 100, 0);

	
	public BotaoCampo(Campo campo) {
		this.campo = campo;
		campo.adicionarConsumer(this);
		addMouseListener(this);
		setBorder(BorderFactory.createBevelBorder(0));
		setBackground(BG_PADRAO);
		
	}

	@Override
	public void eventoOcorrido(Campo campo, CampoEstado estado) {
		
		switch(estado) {
		
		case Abrir:
			aplicarEstiloAbrir();
			break;
		case Marcar:
			aplicarEstiloMarcar();
			break;
		case Explosao:
			aplicarEstiloExplosao();
			break;
		default:
			aplicarEstiloPadrao();
			//break;
		}
		
		SwingUtilities.invokeLater(() -> {
			repaint();
			validate();
		});
		
	}

	private void aplicarEstiloPadrao() {
		setBorder(BorderFactory.createBevelBorder(0));
		setBackground(BG_PADRAO);
		setText("");
		
	}

	private void aplicarEstiloExplosao() {
		setBackground(BG_EXPLODIDO);
		setText("X");
	}

	private void aplicarEstiloMarcar() {
		setForeground(Color.WHITE);
		setBackground(BG_MARCADO);
		setText("M");
	}

	private void aplicarEstiloAbrir() {
		
		setBackground(BG_PADRAO);
		setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		if(campo.isMinado()) {
			setForeground(Color.WHITE);
			setBackground(Color.BLACK);
			setText("O");
			return;
		}
		
		switch(campo.minasNaVizinhanca()) {
		case 1:
			setForeground(TEXTO_VERDE);
			break;
		case 2:
			setForeground(Color.BLUE);
			break;
		case 3:
			setForeground(Color.YELLOW);
			setText("3");
			break;
		case 4:
		case 5:
		case 6:
			setForeground(Color.RED);
			break;
		default:
		setForeground(Color.PINK);
		//break;
		}
		
		String valor = !campo.vizinhancaSegura() ? campo.minasNaVizinhanca() + "" : "";
		setText(valor);
	}
	
	public void mousePressed(java.awt.event.MouseEvent e) {
		if(e.getButton() == 1) {
			campo.abrir();
			return;
		}
		campo.alternarMarcacao();
	}
	
}
