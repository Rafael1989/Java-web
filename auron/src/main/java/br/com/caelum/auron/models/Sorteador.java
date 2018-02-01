package br.com.caelum.auron.models;

import java.util.Collections;
import java.util.List;

import br.com.caelum.auron.exceptions.SorteioException;

public class Sorteador {
	
	private List<Participante> participantes;
	private int totalDeParticipantes;
	private Sorteio sorteio;
	
	public Sorteador(Sorteio sorteio,List<Participante> participantes) throws SorteioException {
		if(participantes == null)
			throw new SorteioException("A lista não pode ser nula");
		this.sorteio = sorteio;
		this.participantes = participantes;
		totalDeParticipantes = participantes.size();
	}
	
	public void sortear() throws SorteioException {

		verificaTamanhoDaLista();
		embaralhaParticipantes();
		
		for(int indiceAtual = 0;indiceAtual<totalDeParticipantes;indiceAtual++) {
			if(quandoForOUltimo(indiceAtual)) {
				adicionaUmPar(indiceAtual,0);
				break;
			}
			
			adicionaUmPar(indiceAtual, indiceAtual+1);
		}
		
	}

	private void embaralhaParticipantes() {
		Collections.shuffle(participantes);
	}

	private void verificaTamanhoDaLista() throws SorteioException {
		if(totalDeParticipantes < 2)
			throw new SorteioException("A lista deve ter no mínimo 2 participantes");
	}

	private boolean quandoForOUltimo(int indiceAtual) {
		return indiceAtual == totalDeParticipantes-1;
	}

	private void adicionaUmPar(int indiceAtual,int indiceFinal) {
		Par par = new Par(participantes.get(indiceAtual), participantes.get(indiceFinal),sorteio);
		sorteio.adicionaPar(par);
	}

}
