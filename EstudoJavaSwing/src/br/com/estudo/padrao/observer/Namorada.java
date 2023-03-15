package br.com.estudo.padrao.observer;

public class Namorada implements ChegadaAniversarianteObservador{

	
	public void eventoChegadaAniversariante(EventoChegadaAniversariante evento) {
		System.out.println("Avisar convidados ...");
		System.out.println("Apagar as luzes ...");
		System.out.println("Esperar um pouco ...");
		System.out.println("E... SURPRESA!!!");

	}
	
	
	
}
