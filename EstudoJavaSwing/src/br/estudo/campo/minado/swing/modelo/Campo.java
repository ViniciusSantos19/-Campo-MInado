package br.estudo.campo.minado.swing.modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;



public class Campo {
	private final int linha;
	private final int coluna;
	
	private boolean minado;
	private boolean aberto;
	private boolean marcado;
	
	private List<Campo> vizinhos = new ArrayList<>();
	private  List<CampoObservador> observadores = new ArrayList<>();
	//outra abordagem para resolver o problema
	//private List<BiConsumer<Campo, CampoEstado>> consimidor = new ArrayList<>();
	
	public Campo(int linha, int coluna) {
		super();
		this.linha = linha;
		this.coluna = coluna;
	}


	public void adicionarConsumer(CampoObservador campoObservador) {
		this.observadores.add(campoObservador);
	}
	
	private void notificaObservadores(CampoEstado estado) {
		observadores.stream().forEach(o-> o.eventoOcorrido(this, estado));
	}

	public boolean adicionarViziho(Campo vizinho) {
		boolean linhaDiferente = linha != vizinho.linha;
		boolean colunaDiferente = coluna != vizinho.coluna;
		boolean diagonal = colunaDiferente && linhaDiferente;
		
		int deltaLinha = Math.abs(linha - vizinho.linha);
		int deltaColuna = Math.abs(coluna - vizinho.coluna);
		int deltaGeral = deltaLinha + deltaColuna;
		
		if(deltaGeral == 1 && !diagonal) {
			vizinhos.add(vizinho);
			return true;
		}else if(deltaGeral == 2 && diagonal) {
			vizinhos.add(vizinho);
			return true;
		}
		return false;
	}
	
	public void alternarMarcacao() {
		if(!aberto) {
			marcado = !marcado;
			
			if(marcado) {
				
				notificaObservadores(CampoEstado.Marcar);
				return;
			}
			
			notificaObservadores(CampoEstado.Desmarcar);
			
		}
	}
	
	public boolean abrir() {
		if(!aberto && !marcado) {
			
			
			if(minado) {
				notificaObservadores(CampoEstado.Explosao);
				return true;
			}
			
			setAberto(true);
			
			if(vizinhancaSegura()) {
				Consumer<Campo> abre =  v -> v.abrir();
				vizinhos.forEach(abre);
			}
			return true;
		}
		return false;
	}
	
	public boolean vizinhancaSegura() {
		Predicate<Campo> ehMinado = c -> c.minado;
		return vizinhos.stream().noneMatch(ehMinado);
	}
	
	public boolean isMarcado() {
		return marcado;
	}
	
	public boolean isAberto() {
		return aberto;
	}	
	
	public boolean isMinado() {
		return minado;
	}
	
	public boolean isFechado() {
		return !isAberto();
	}
	
	void minar() {
		if(!minado) {			
			minado = true;
		}
	}
	
	boolean objetivoAlcancado() {
		boolean desvendado = aberto && !minado;
		boolean protegido = minado && marcado;
		return desvendado || protegido;
	}
	
	public int minasNaVizinhanca () {
		Predicate<Campo> ehMinado = c -> c.minado;
		return (int) vizinhos.stream().filter(ehMinado).count();
	}
	
	void reiniciar() {
		this.aberto = false;
		this.minado = false;
		this.marcado = false;
		notificaObservadores(CampoEstado.Reiniciar);
	}
	
	public int getLinha() {
		return linha;
	}

	void setAberto(boolean abrirFechar) {
		this.aberto = abrirFechar;
		
		if(aberto) {
			
			notificaObservadores(CampoEstado.Abrir);
			
		}
	}

	public int getColuna() {
		return coluna;
	}

}