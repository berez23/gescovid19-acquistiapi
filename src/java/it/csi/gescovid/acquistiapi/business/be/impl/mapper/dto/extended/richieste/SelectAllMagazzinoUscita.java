package it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.extended.richieste;

import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacTMagazUscita;

public class SelectAllMagazzinoUscita extends CovidacTMagazUscita {

	private Integer prodId;
	private String prodCod;
	private String prodNome;
	private String prodDesc;
	private String idStruttura;
	private String nome;

	public Integer getProdId() {
		return prodId;
	}

	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}

	public String getProdCod() {
		return prodCod;
	}

	public void setProdCod(String prodCod) {
		this.prodCod = prodCod;
	}

	public String getProdNome() {
		return prodNome;
	}

	public void setProdNome(String prodNome) {
		this.prodNome = prodNome;
	}

	public String getProdDesc() {
		return prodDesc;
	}

	public void setProdDesc(String prodDesc) {
		this.prodDesc = prodDesc;
	}

	public String getIdStruttura() {
		return idStruttura;
	}

	public void setIdStruttura(String idStruttura) {
		this.idStruttura = idStruttura;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
