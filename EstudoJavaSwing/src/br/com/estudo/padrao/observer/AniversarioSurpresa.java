package br.com.estudo.padrao.observer;

public class AniversarioSurpresa {
	public static void main(String[] args) {
		Namorada namorada = new Namorada();
		Porteiro porteiro = new Porteiro();
		porteiro.registraObservador(namorada);
		porteiro.registraObservador(e -> System.out.println("Evento com lambda"
				+ " ocorreu em "+e.getData()
				));
		porteiro.monitorar();
	}
}
