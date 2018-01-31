package br.com.caelum.auron.models;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class SorteadorTest {
	
	private Participante p1;
	private Participante p2;
	private Participante p3;
	
	private List<Participante> participantes;
	private Sorteio sorteio;
	private List<Participante> amigosOcultos;
	private Set<Participante> amigosOcultosSet;

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
	}

	@Test
	public void aQuantidadeDeParesEParticipantesDeveSerAMesma() {
		
		int totalDeParticipantes = participantes.size();
		
		Sorteador sorteador = new Sorteador(sorteio, participantes);
		sorteador.sortear();
		
		int quantidadeDePares = sorteio.getQuantidadeDePares();
		
		assertTrue(quantidadeDePares == totalDeParticipantes);
	}
	
	@Test
	public void amigosOcultosNaoPodemSeRepetir() {
		Sorteador sorteador = new Sorteador(sorteio, participantes);
		sorteador.sortear();
		for (Par par : sorteio.getPares()) {
			amigosOcultos.add(par.getAmigoOculto());
		}
		amigosOcultosSet = new HashSet<>(amigosOcultos);
		assertEquals(amigosOcultos.size(), amigosOcultosSet.size());
	}

}
