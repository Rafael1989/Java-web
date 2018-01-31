package br.com.caelum.auron.models;

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
	}
	
	public void sortear() throws SorteioException {
		int indiceAtual = 0;
		totalDeParticipantes = participantes.size();
		
		if(totalDeParticipantes < 2)
			throw new SorteioException("A lista deve ter no mínimo 2 participantes");
		
		while(indiceAtual < totalDeParticipantes) {
			if(indiceAtual == totalDeParticipantes-1) {
				Par par = new Par(participantes.get(indiceAtual), participantes.get(0),sorteio);
				sorteio.adicionaPar(par);
				break;
			}
			
			Par par = new Par(participantes.get(indiceAtual), participantes.get(indiceAtual+1), sorteio);
			sorteio.adicionaPar(par);
			
			indiceAtual++;
		}
	}

}
