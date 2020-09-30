package it.csi.gescovid.acquistiapi.dto.util;

import java.util.List;

import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.Profilo;

public class UtilitiStream {
	
	
	public static boolean verificaProfili(List<Profilo> elencoProfiliDB, String xApplicazioneCodice) {
		return elencoProfiliDB.stream().anyMatch(c -> (c != null && xApplicazioneCodice.equalsIgnoreCase(c.getNomeProfilo())));
	}
}
