package it.csi.gescovid.acquistiapi.business.mapping;

import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacTMagazzino;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.extended.CovidacTMagazExt;
import it.csi.gescovid.acquistiapi.dto.ModelMagazzinoDettaglio;

public class ModelMagazzinoDettaglioCovidacTMagazExtMapper extends BaseMapper<ModelMagazzinoDettaglio, CovidacTMagazzino> {

	@Override
	public CovidacTMagazzino to(ModelMagazzinoDettaglio source) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelMagazzinoDettaglio from(CovidacTMagazzino dest) {
		// TODO Auto-generated method stub
		if(dest == null) {
			return null;
		}
		ModelMagazzinoDettaglio dettaglio = new ModelMagazzinoDettaglio();
		
		dettaglio.setComune(dest.getMagazComuneCodice());
		dettaglio.setId(dest.getMagazId().toString());
		dettaglio.setNome(dest.getMagazNome());
		dettaglio.setIndirizzo(dest.getMagazIndirizzo());
		return dettaglio;
		
	}

}
