package br.com.caelum.auron.daos;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.caelum.auron.models.Participante;

@Stateless
public class ParticipanteDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public void inserir(Participante participante) {
		em.persist(participante);
	}
	
	

}
