package br.com.estudo.padrao.observer;

import java.util.Date;

public class EventoChegadaAniversariante {
	
	
	private final Date data;

	public EventoChegadaAniversariante(Date data) {
		this.data = data;
	}

	public Date getData() {
		return data;
	}
	
	
	
}
