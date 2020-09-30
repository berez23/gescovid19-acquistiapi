package it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.extended;

import java.math.BigDecimal;

import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.Ente;

public class EnteExt extends Ente {

	private BigDecimal pdPercentualeDistribuzione;
	private String pdAlgoritmoDistribuzione;

	private String idStruttura;
	private String nomeStruttura;
	private String naturaStruttura;

	public BigDecimal getPdPercentualeDistribuzione() {
		return pdPercentualeDistribuzione;
	}

	public void setPdPercentualeDistribuzione(BigDecimal pdPercentualeDistribuzione) {
		this.pdPercentualeDistribuzione = pdPercentualeDistribuzione;
	}

	public String getPdAlgoritmoDistribuzione() {
		return pdAlgoritmoDistribuzione;
	}

	public void setPdAlgoritmoDistribuzione(String pdAlgoritmoDistribuzione) {
		this.pdAlgoritmoDistribuzione = pdAlgoritmoDistribuzione;
	}

	public String getIdStruttura() {
		return idStruttura;
	}

	public void setIdStruttura(String idStruttura) {
		this.idStruttura = idStruttura;
	}

	public String getNomeStruttura() {
		return nomeStruttura;
	}

	public void setNomeStruttura(String nomeStruttura) {
		this.nomeStruttura = nomeStruttura;
	}

	public String getNaturaStruttura() {
		return naturaStruttura;
	}

	public void setNaturaStruttura(String naturaStruttura) {
		this.naturaStruttura = naturaStruttura;
	}

}
