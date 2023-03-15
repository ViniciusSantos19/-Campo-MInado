package br.estudo.campo.minado.swing.Ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public interface EventoClickMouse extends MouseListener{
	@Override
	default void mousePressed(MouseEvent e) {
		
	}
	@Override
	default void mouseClicked(MouseEvent e) {
		
	}
	
	@Override
	default void mouseEntered(MouseEvent e) {
		
	}
	
	@Override
	default void mouseExited(MouseEvent e) {
		
	}
	
	@Override
	default void mouseReleased(MouseEvent e) {
	
	}
}
