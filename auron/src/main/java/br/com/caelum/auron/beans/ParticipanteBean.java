package br.com.caelum.auron.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.auron.daos.ParticipanteDao;
import br.com.caelum.auron.models.Participante;

@Named
@RequestScoped
public class ParticipanteBean {
	
	private Participante participante = new Participante();
	
	@Inject
	private ParticipanteDao participanteDao;
	
	public Participante getParticipante() {
		return participante;
	}
	
	public void cadastrar() {
		participanteDao.inserir(participante);
	}

}