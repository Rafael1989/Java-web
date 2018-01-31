package br.com.caelum.auron.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.auron.exceptions.SorteioException;

public class SorteadorTest {
	
	private Participante p1;
	private Participante p2;
	private Participante p3;
	
	private List<Participante> participantes;
	private Sorteio sorteio;
	private List<Participante> amigosOcultos;
	private Set<Participante> amigosOcultosSet;
	private List<Participante> amigos;
	private Set<Participante> amigosSet;

	@Before
	public void setUp() throws Exception {
		p1 = new Participante();
		p1.setNome("Leonardo");
		p2 = new Participante();
		p2.setNome("Nico");
		p3 = new Participante();
		p3.setNome("Fábio");
		sorteio = new Sorteio();
		participantes = Arrays.asList(p1,p2,p3);
		amigosOcultos = new ArrayList<>();
		amigos = new ArrayList<>();
	}

	@Test
	public void aQuantidadeDeParesEParticipantesDeveSerAMesma() throws SorteioException {
		
		int totalDeParticipantes = participantes.size();
		
		Sorteador sorteador = new Sorteador(sorteio, participantes);
		sorteador.sortear();
		
		int quantidadeDePares = sorteio.getQuantidadeDePares();
		
		assertTrue(quantidadeDePares == totalDeParticipantes);
	}
	
	@Test
	public void amigosOcultosNaoPodemSeRepetir() throws SorteioException {
		Sorteador sorteador = new Sorteador(sorteio, participantes);
		sorteador.sortear();
		for (Par par : sorteio.getPares()) {
			amigosOcultos.add(par.getAmigoOculto());
		}
		amigosOcultosSet = new HashSet<>(amigosOcultos);
		assertEquals(amigosOcultos.size(), amigosOcultosSet.size());
	}
	
	@Test(expected=SorteioException.class)
	public void naoDeveAceitarUmaListaComMenosDeDoisParticipantes() throws SorteioException {
		Sorteador sorteador = new Sorteador(sorteio, new ArrayList());
		sorteador.sortear();
	}
	
	@Test(expected=SorteioException.class)
	public void naoDeveAceitarUmaListaNula() throws SorteioException {
		Sorteador sorteador = new Sorteador(sorteio, null);
		sorteador.sortear();
	}
	
	@Test
	public void naoDeveRepetirUmAmigo() throws SorteioException {
		Sorteador sorteador = new Sorteador(sorteio, participantes);
		sorteador.sortear();
		for (Par par : sorteio.getPares()) {
			amigos.add(par.getAmigoOculto());
		}
		amigosSet = new HashSet<>(amigos);
		assertEquals(amigos.size(), amigosSet.size());
		
	}
	
	@Test
	public void verificarSeAmigoEIgualAAmigoOculto() throws SorteioException {
		Sorteador sorteador = new Sorteador(sorteio, participantes);
		sorteador.sortear();
		
		for(Par par : sorteio.getPares()) {
			assertFalse(par.getAmigo().equals(par.getAmigoOculto()));
		}
	}
	
	@Test
	public void verificarSeAmigoOcultoDoUltimoParEOAmigoDoPrimeiroPar() throws SorteioException {
		Sorteador sorteador = new Sorteador(sorteio, participantes);
		sorteador.sortear();
		
		List<Par> pares = new ArrayList<>(sorteio.getPares());
		
		assertEquals(pares.get(0).getAmigo(),pares.get(pares.size()-1).getAmigoOculto());
	}

}
