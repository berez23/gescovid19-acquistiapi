package it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.extended;

public class UtentiExt extends it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.Utenti {

	private Long id;
	private String nome;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
