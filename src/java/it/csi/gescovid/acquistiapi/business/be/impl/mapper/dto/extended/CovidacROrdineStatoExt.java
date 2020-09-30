package it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.extended;

import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacROrdineStato;

public class CovidacROrdineStatoExt extends CovidacROrdineStato {

	private String ordStatoCod;
	private String ordStatoDesc;

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

}
