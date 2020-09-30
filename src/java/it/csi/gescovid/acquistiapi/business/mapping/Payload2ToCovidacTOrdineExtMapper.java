package it.csi.gescovid.acquistiapi.business.mapping;

import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.extended.CovidacTOrdineExt;
import it.csi.gescovid.acquistiapi.dto.Payload2;

public class Payload2ToCovidacTOrdineExtMapper extends BaseMapper<Payload2, CovidacTOrdineExt> {

	@Override
	public CovidacTOrdineExt to(Payload2 source) {
		if(source == null) {
			return null;
		}
		CovidacTOrdineExt dest = new CovidacTOrdineExt();
		dest.setOrdId(Integer.valueOf(source.getIdentificativoOrdine()) );
		dest.setOrdDataConsegnaPrevista(source.getDataConsegnaPrevista());
		dest.setOrdData(source.getDataOrdine());
		dest.setProdCod(source.getProdotto());
		dest.setQuantitaOrdinata(source.getQuantitaOrdinata());

		return dest;
		
	}

	@Override
	public Payload2 from(CovidacTOrdineExt dest) {
		// TODO Auto-generated method stub
		return null;
	}

}
