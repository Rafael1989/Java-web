package br.com.caelum.auron.producers;

import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class ProducerUtil {

	@Produces
	public FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}
	
	@Produces
	public Subject getSubject() {
		return SecurityUtils.getSubject();
	}
}
