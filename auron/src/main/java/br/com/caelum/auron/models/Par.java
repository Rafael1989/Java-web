package br.com.caelum.auron.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Par {

	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne
	private Participante amigo;
	
	@ManyToOne
	private Participante amigoOculto;
	
	@ManyToOne
	private Sorteio sorteio;

	public Par(Participante amigo, Participante amigoOculto, Sorteio sorteio) {
		this.amigo = amigo;
		this.amigoOculto = amigoOculto;
		this.sorteio = sorteio;
	}
	
	Par(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Participante getAmigoOculto() {
		return amigoOculto;
	}
	
	public Participante getAmigo() {
		return amigo;
	}
	
	public Sorteio getSorteio() {
		return sorteio;
	}

}
