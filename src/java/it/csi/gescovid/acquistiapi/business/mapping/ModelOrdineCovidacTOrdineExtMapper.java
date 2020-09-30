package it.csi.gescovid.acquistiapi.business.mapping;

import org.apache.commons.lang3.StringUtils;

import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.extended.CovidacTOrdineExt;
import it.csi.gescovid.acquistiapi.dto.ModelOrdine;
import it.csi.gescovid.acquistiapi.dto.ModelOrdineStato;
import it.csi.gescovid.acquistiapi.dto.ModelProdotto;
import it.csi.gescovid.acquistiapi.util.CaUtils;

public class ModelOrdineCovidacTOrdineExtMapper extends BaseMapper<ModelOrdine, CovidacTOrdineExt> {

	@Override
	public CovidacTOrdineExt to(ModelOrdine source) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelOrdine from(CovidacTOrdineExt dest) {
		if(dest == null) {
			return null;
		}
		ModelOrdine source = new ModelOrdine();
		source.setId( dest.getOrdId());
		source.setDataConsegna(dest.getOrdDataConsegna());
		source.setDataCreazione(dest.getDataCreazione());
		source.setDataOrdine(dest.getOrdData());
		source.setIdentificativoOrdine(dest.getOrdIdentificativo());
		source.setDataConsegnaPrevista(dest.getOrdDataConsegnaPrevista());
		source.setQuantitaConsegnata(dest.getQuantitaConsegnata());   
		
		/*
		 *  ata_consegna_prevista, data_consegna, quantità consegnata
		 * 
		 * */
		//Creazione del prodotto
		if(CaUtils.isNotEmpty(dest.getProdCod()) ) {
			ModelProdotto prodotto = new ModelProdotto();
			prodotto.setCodice(dest.getProdCod());
			prodotto.setDescrizione(dest.getProdDesc());
			prodotto.setNome(dest.getProdNome());
			source.setProdotto(prodotto);
		}

		source.setQuantitaConsegnata(dest.getQuantitaConsegnata());
		source.setQuantitaOrdinata(dest.getQuantitaOrdinata());
		if(CaUtils.isNotEmpty(dest.getOrdStatoCod())) {
			ModelOrdineStato stato = new ModelOrdineStato();
			stato.setCodice(dest.getOrdStatoCod());
			stato.setDescrizione(dest.getOrdStatoDesc());
			source.setStato(stato);
		}
		
		return source;
	}

}
