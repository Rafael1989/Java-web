package br.com.caelum.auron.beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.auron.daos.ParticipanteDao;
import br.com.caelum.auron.daos.SorteioDao;
import br.com.caelum.auron.exceptions.SorteioException;
import br.com.caelum.auron.models.Par;
import br.com.caelum.auron.models.Sorteador;
import br.com.caelum.auron.models.Sorteio;

@Named
@RequestScoped
public class SorteioBean {
	
	@Inject
	private ParticipanteDao participanteDao;
	
	@Inject
	private SorteioDao sorteioDao;
	
	private Sorteio sorteio = new Sorteio();
	
	public Sorteio getSorteio() {
		return sorteio;
	}
	
	public void sortear() {
		try {
			Sorteador sorteador = new Sorteador(sorteio, participanteDao.getParticipantes());
			sorteador.sortear();
			sorteioDao.insere(sorteio);
		} catch (SorteioException e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage()));
		}
	}
	
	public List<Par> getPares(){
		return sorteioDao.getPares();
	}
	

}
