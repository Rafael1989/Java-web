package br.com.caelum.auron.daos;

import java.util.List;

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

	public List<Participante> getParticipantes() {
		return em.createQuery("from Participante", Participante.class).getResultList();
	}

	public Participante getParticipante(String email, String senha) {
		return em.createQuery("from Participante p where p.email = ? and p.senha = ?",Participante.class)
				.setParameter(1, email)
				.setParameter(2, senha)
				.getSingleResult();
	}
	
	

}
