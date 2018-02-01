package br.com.caelum.auron.shiro;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

import br.com.caelum.auron.daos.ParticipanteDao;
import br.com.caelum.auron.models.Participante;

public class Autenticador implements Realm{
	
	private ParticipanteDao participanteDao;
	
	public Autenticador() {
		participanteDao = getParticipanteDao();
	}
	
	public ParticipanteDao getParticipanteDao() {
		Properties properties = new Properties();
		properties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		try {
			InitialContext initialContext = new InitialContext(properties);
			ParticipanteDao participanteDao = (ParticipanteDao) initialContext.lookup("java:module/ParticipanteDao");
			return participanteDao;
		}catch(NamingException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		
		String email = usernamePasswordToken.getUsername();
		String senha = new String(usernamePasswordToken.getPassword());
		
		Participante participante = participanteDao.getParticipante(email,senha);
		
		if(participante != null) {
			AuthenticationInfo info = new SimpleAuthenticationInfo(email,senha,getName());
			return info;
		}
		throw new AuthenticationException();
	}

	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}

	@Override
	public boolean supports(AuthenticationToken arg0) {
		return true;
	}

}
