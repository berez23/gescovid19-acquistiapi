package it.csi.gescovid.acquistiapi.business.mapping;

import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.extended.CovidacTMagazExt;
import it.csi.gescovid.acquistiapi.dto.ModelMagazzinoDettaglio;
import it.csi.gescovid.acquistiapi.dto.ModelMagazzinoDettaglioProdotto;

public class ModelMagazzinoDettaglioProdottoCovidacTMagazExtMapper extends BaseMapper<ModelMagazzinoDettaglioProdotto, CovidacTMagazExt> {

	@Override
	public CovidacTMagazExt to(ModelMagazzinoDettaglioProdotto source) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelMagazzinoDettaglioProdotto from(CovidacTMagazExt dest) {
		if(dest == null) {
			return null;
		}
		ModelMagazzinoDettaglioProdotto source = new ModelMagazzinoDettaglioProdotto();
		source.setCodice(dest.getProdCod());
		source.setDescrizione(dest.getProdDesc());
		source.setNome(dest.getProdNome());
		source.setQuantitaDisponibile(dest.getQuantitaDisponibile());
		source.setQuantitaDistribuita(dest.getQuantitaDistribuita());
		source.setQuantitaRicevuta(dest.getQuantita());
		
		return source;
		
	}

}
