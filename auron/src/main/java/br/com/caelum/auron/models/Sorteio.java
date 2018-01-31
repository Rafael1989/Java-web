package br.com.caelum.auron.models;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Sorteio {

	@Id
	@GeneratedValue
	private Integer id;

	private String nome;
	
	@OneToMany(mappedBy="sorteio")
	private Set<Par> pares = new LinkedHashSet<>();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<Par> getPares() {
		return Collections.unmodifiableSet(this.pares);
	}

	public void setPares(Set<Par> pares) {
		this.pares = pares;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void adicionaPar(Par par) {
		this.pares.add(par);
	}

	public int getQuantidadeDePares() {
		return this.pares.size();
	}

}
