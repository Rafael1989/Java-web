package br.com.caelum.auron.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.caelum.auron.models.Sorteio;

@Named
@RequestScoped
public class SorteioBean {
	
	private Sorteio sorteio = new Sorteio();
	
	public Sorteio getSorteio() {
		return sorteio;
	}
	
	public void sortear() {
		System.out.println("sorteou" + sorteio.getNome());
	}
	

}
