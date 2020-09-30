package it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.extended;

import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacCPercDist;

public class PercDistExt extends CovidacCPercDist {
	
	String nomeEnte;
	String nomeStruttura;
	String natura;
	public String getNomeEnte() {
		return nomeEnte;
	}
	public void setNomeEnte(String nomeEnte) {
		this.nomeEnte = nomeEnte;
	}
	public String getNomeStruttura() {
		return nomeStruttura;
	}
	public void setNomeStruttura(String nomeStruttura) {
		this.nomeStruttura = nomeStruttura;
	}
	public String getNatura() {
		return natura;
	}
	public void setNatura(String natura) {
		this.natura = natura;
	}
	
	
}
