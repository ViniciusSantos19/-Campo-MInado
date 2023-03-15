package br.com.estudo.padrao.observer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Porteiro {
	
	private List<ChegadaAniversarianteObservador> observadores = new ArrayList<>();
	
	public void registraObservador(ChegadaAniversarianteObservador o) {
		this.observadores.add(o);
	}
	
	public void monitorar() {
		Scanner entrada = new Scanner(System.in);
		
		String valor = "";
		
		while(!"sair".equalsIgnoreCase(valor)) {
			
			System.out.println("O aniversariante chegou?");
			valor = entrada.nextLine();
			
			if("sim".equalsIgnoreCase(valor)) {
				
				
				EventoChegadaAniversariante evento = new EventoChegadaAniversariante(new Date());
				observadores.stream().forEach(o-> o.eventoChegadaAniversariante(evento));
				
			}else {
				
				System.out.println("Alarme false!!!");
				
			}
			
		}
		
		System.out.println("Saiu");
		entrada.close();
	}
	
}
