package it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.extended;

import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacTMagazzino;

public class CovidacTMagazExt extends CovidacTMagazzino {

	//dati del prodotto
	private String prodCod;
	private String prodNome;
	private String prodDesc;
	//dati di covidacrmagazprod
	private Integer quantitaDisponibile;
	private Integer quantitaDistribuita;
	//dati di ovidactmagazentrata
	private Integer quantita;
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
	public Integer getQuantitaDisponibile() {
		return quantitaDisponibile;
	}
	public void setQuantitaDisponibile(Integer quantitaDisponibile) {
		this.quantitaDisponibile = quantitaDisponibile;
	}
	public Integer getQuantitaDistribuita() {
		return quantitaDistribuita;
	}
	public void setQuantitaDistribuita(Integer quantitaDistribuita) {
		this.quantitaDistribuita = quantitaDistribuita;
	}
	public Integer getQuantita() {
		return quantita;
	}
	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}
	
	
	
}
