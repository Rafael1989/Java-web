package br.com.caelum.auron.beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import br.com.caelum.auron.daos.ParticipanteDao;
import br.com.caelum.auron.models.Participante;

@Named
@RequestScoped
public class ParticipanteBean {
	
	private Participante participante = new Participante();
	
	private List<Participante> participantes;
	
	@Inject
	private Subject subject;
	
	@Inject
	private FacesContext facesContext;
	
	@Inject
	private ParticipanteDao participanteDao;
	
	public Participante getParticipante() {
		return participante;
	}
	
	public void cadastrar() {
		participanteDao.inserir(participante);
	}
	
	public List<Participante> getParticipantes(){
		if(participantes == null)
			participantes = participanteDao.getParticipantes();
		return participantes;
	}
	
	public String login() {
		try {
			UsernamePasswordToken token = new UsernamePasswordToken(participante.getEmail(),participante.getSenha());
			subject.login(token);
			
			return "sorteio?faces-redirect=true";
		}catch(AuthenticationException e) {
			facesContext.addMessage(null, new FacesMessage("Usuário ou senha inválidos"));
		}
		return null;
	}

}
