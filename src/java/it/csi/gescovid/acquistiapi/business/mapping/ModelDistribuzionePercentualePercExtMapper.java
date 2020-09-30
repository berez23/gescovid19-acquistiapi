package it.csi.gescovid.acquistiapi.business.mapping;

import org.apache.commons.lang3.StringUtils;

import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.extended.PercDistExt;
import it.csi.gescovid.acquistiapi.dto.ModelDistribuzionePercentuale;
import it.csi.gescovid.acquistiapi.dto.ModelEnte;
import it.csi.gescovid.acquistiapi.dto.ModelStruttura;

public class ModelDistribuzionePercentualePercExtMapper extends BaseMapper<ModelDistribuzionePercentuale, PercDistExt> {

	@Override
	public PercDistExt to(ModelDistribuzionePercentuale source) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelDistribuzionePercentuale from(PercDistExt dest) {
		ModelDistribuzionePercentuale source = new ModelDistribuzionePercentuale();
		source.setId(dest.getPdId().toString());
		source.setPercentualeDistribuzione(dest.getPdPercentualeDistribuzione());
		source.setDataModifica(dest.getDataModifica());
		ModelEnte ente = new ModelEnte();
		ente.setId(dest.getIdEnte().toString());
		ente.setNome(dest.getNomeEnte());
		source.setEnte(ente);
		if(StringUtils.isNotEmpty(dest.getIdStruttura())) {
			ModelStruttura strut = new ModelStruttura();
			strut.setId(dest.getIdStruttura());
			strut.setEnteId(dest.getIdEnte().toString());
			strut.setNatura(dest.getNatura());
			strut.setNome(dest.getNomeStruttura());
			source.setStruttura(strut);
		}
		
		return source;
	}

}
