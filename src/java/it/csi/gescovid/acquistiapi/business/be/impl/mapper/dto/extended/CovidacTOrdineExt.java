package it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.extended;

import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacTOrdine;

public class CovidacTOrdineExt extends CovidacTOrdine{
	
	private String ordStatoCod;
	private String ordStatoDesc;
	
	private String prodCod;
	private String prodNome;
	private String prodDesc;
	public String getOrdStatoCod() {
		return ordStatoCod;
	}
	public void setOrdStatoCod(String ordStatoCod) {
		this.ordStatoCod = ordStatoCod;
	}
	public String getOrdStatoDesc() {
		return ordStatoDesc;
	}
	public void setOrdStatoDesc(String ordStatoDesc) {
		this.ordStatoDesc = ordStatoDesc;
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
	
	
	
}
