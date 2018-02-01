package br.com.caelum.auron.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.caelum.auron.models.Par;
import br.com.caelum.auron.models.Sorteio;

@Stateless
public class SorteioDao {

	@PersistenceContext
	private EntityManager em;
	
	public void insere(Sorteio sorteio) {
		em.persist(sorteio);
	}

	public List<Par> getPares() {
		return em.createQuery("from Par",Par.class).getResultList();
	}
}
