package br.estudo.campo.minado.swing.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Predicate;



public class Tabuleiro implements CampoObservador{
	private final int linhas;
	private final int colunas;
	private int minas;
	
	private final List<Campo> campos = new ArrayList<>();
	private final List<Consumer<ResultadoEvento>> observadores = new ArrayList<>();

	public Tabuleiro(int linhas, int colunas, int minas) {
		super();
		this.linhas = linhas;
		this.colunas = colunas;
		this.minas = minas;
		
		gerarCampos();
		associarVizinhos();
		sortearMinas();	
	}
	
	public void forEach(Consumer<Campo> funcao) {
		this.campos.stream().forEach(funcao);
	}

	public void adicionarObservador(Consumer<ResultadoEvento> observador) {
		this.observadores.add(observador);
	}
	
	private void notificaObservadores(Boolean resultado) {
		observadores.stream().forEach(o-> o.accept(new ResultadoEvento(resultado)));
	}
	
	private void gerarCampos() {
		for(int i = 0; i < linhas; i++) {
			for(int j = 0;  j < colunas; j++) {
				Campo campo = new Campo(i, j);
				campo.adicionarConsumer(this);
				campos.add(campo);
			}
		}
	}
	
	private void associarVizinhos() {
		for(Campo c1: campos) {
			for(Campo c2: campos) {
				c1.adicionarViziho(c2);
			}
		}
	}
	
	private void sortearMinas() {
		int minasArmadas = 0;
 		Predicate<Campo> isMinado = c -> c.isMinado();
		Random aleatorio = new Random();
		do {
			campos.stream().filter(isMinado).count();
			int num = aleatorio.nextInt(campos.size());
			campos.get(num).minar();
			minasArmadas++;
		}while(minasArmadas < minas);
		
	}
	
	public boolean objetivoAlcancado() {
		return campos.stream().allMatch(c -> c.objetivoAlcancado());
	}
	
	public void reiniciarJogo() {
		campos.stream().forEach(c -> c.reiniciar());
		sortearMinas();
	}

	
	
	public void abrir(int linha, int coluna) {
			
		Predicate<Campo> acharCampo = 
				c -> c.getLinha() == linha && c.getColuna() == coluna;
		campos.stream().filter(acharCampo).findFirst().ifPresent(c -> c.abrir());
			
	}
	
	public void abrirTodosCamposMinados() {
		
		campos.stream().filter(c -> c.isMinado()).filter(c -> !c.isMarcado()).forEach(c -> c.setAberto(true));
		
	}
	
	public void marcar(int linha, int coluna) {
		Predicate<Campo> acharCampo = 
				c -> c.getLinha() == linha && c.getColuna() == coluna;
		campos.stream().filter(acharCampo).findFirst().ifPresent(c -> c.alternarMarcacao());
	}

	@Override
	public void eventoOcorrido(Campo campo, CampoEstado estado) {
		
		if(estado == CampoEstado.Explosao) {
			
			abrirTodosCamposMinados();
			notificaObservadores(false);
			
		}else if(objetivoAlcancado()) {
			
			notificaObservadores(true);
			
		}
		
	}

	public int getLinhas() {
		return linhas;
	}

	public int getColunas() {
		return colunas;
	}

	public int getMinas() {
		return minas;
	}

	public List<Campo> getCampos() {
		return campos;
	}

	public List<Consumer<ResultadoEvento>> getObservadores() {
		return observadores;
	}
	
	
	
}
