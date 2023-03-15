package br.com.estudos.swing.observer;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Observador {
	public static void main(String[] args) {
		
		JFrame janela = new JFrame("Observador");
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		janela.setSize(600, 200);
		janela.setLayout(new FlowLayout());
		janela.setLocationRelativeTo(null);
		
		JButton botao = new JButton("Botao");
		janela.add(botao);
		botao.addActionListener(e -> {
			System.out.println("Olá mundo");
		});
		
		janela.setVisible(true);
		
	}
}
