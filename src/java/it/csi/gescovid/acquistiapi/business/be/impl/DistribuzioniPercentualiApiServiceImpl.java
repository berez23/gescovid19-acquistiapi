package it.csi.gescovid.acquistiapi.business.be.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.csi.gescovid.acquistiapi.business.be.DistribuzioniPercentualiApi;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacCPercDist;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.extended.PercDistExt;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.extended.DistribuzioniPercentualiServiceMapper;
import it.csi.gescovid.acquistiapi.business.mapping.ModelDistribuzionePercentualePercExtMapper;
import it.csi.gescovid.acquistiapi.dto.ModelDistribuzionePercentuale;
import it.csi.gescovid.acquistiapi.dto.Payload7;
import it.csi.gescovid.acquistiapi.exception.ErroreBuilder;
import it.csi.gescovid.acquistiapi.exception.RESTException;
import it.csi.gescovid.acquistiapi.util.CaConstants;
import it.csi.gescovid.acquistiapi.util.CaRet;
import it.csi.gescovid.acquistiapi.util.LogUtil;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class DistribuzioniPercentualiApiServiceImpl extends AcquistiService implements DistribuzioniPercentualiApi {
	@Autowired
	DistribuzioniPercentualiServiceMapper repositoryDistribuzioni; 
	
	LogUtil log = new LogUtil(DistribuzioniApiServiceImpl.class);

	@Override
	public Response distribuzioniPercentualiGet(String shibIdentitaCodiceFiscale, String xApplicazioneCodice,
			String xRequestId, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		String methodName = "distribuzioniPercentualiGet";
		log.info(methodName, "BEGIN");

		try {
			checkNotNull(shibIdentitaCodiceFiscale, CaConstants.IDENTITA_OBBLIGATORIA);
			checkNotNull(xApplicazioneCodice, CaConstants.APPLICATION_ID_OBBLIGATORIO);

			logAudit(httpHeaders, methodName);

			checkUtenteAutorizzato(shibIdentitaCodiceFiscale, xApplicazioneCodice);
			
			List<PercDistExt> listaPerc = repositoryDistribuzioni.selectAllPercDist();
			
			List<ModelDistribuzionePercentuale> elencoDistribuzioni = new ModelDistribuzionePercentualePercExtMapper().fromList(listaPerc);				

			return Response.ok(elencoDistribuzioni).build();

		} catch (RESTException e) {
			log.error(methodName, " distribuzioniPercentualiGet ERROR RESTException", e);
			return e.getResponse();
		} catch (Exception e) {
			log.error(methodName, "distribuzioniPercentualiGet ERROR Exception", e);
			return CaRet.internalServerErrResponse();
		}
	}

	@Override
	public Response distribuzioniPercentualiPercentualeIdPercentualeDistribuzionePut(String shibIdentitaCodiceFiscale,
			String xApplicazioneCodice, String xRequestId, String percentualeId, Payload7 payload,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		String methodName = "distribuzioniPercentualiPercentualeIdPercentualeDistribuzionePut";
		String identificativoOperazione = "distribuzioniPercPercIdPercDistribuzionePut";
		log.info(methodName, "BEGIN");
		try {
			checkNotNull(shibIdentitaCodiceFiscale, CaConstants.IDENTITA_OBBLIGATORIA);
			checkNotNull(xApplicazioneCodice, CaConstants.APPLICATION_ID_OBBLIGATORIO);
			logAudit(httpHeaders, identificativoOperazione);
			checkUtenteAutorizzato(shibIdentitaCodiceFiscale, xApplicazioneCodice);
			checkNotNull(payload, "Payload non presente");
			checkNotNull(payload.getPercentualeDistribuzione(), "Parametro percentuale distribuzione non inserito");
			
			checkCondition((payload.getPercentualeDistribuzione().doubleValue() > 0 && payload.getPercentualeDistribuzione().doubleValue() <100), "percentale distribuzione non corretta"); 
			
			Integer idPerc = null;
			idPerc = Integer.parseInt(percentualeId);
			
			CovidacCPercDist percDist = repositoryDistribuzioni.selectPercDistPerIdPerc(idPerc);
			
			checkNotNull(percDist, "Id Percentuale non valido");
			//inserisco data_fine al record precedente
			repositoryDistribuzioni.updateDataFineNow(percDist);
			
			CovidacCPercDist percDistNew = newCovidacCPercDist(percDist, payload, shibIdentitaCodiceFiscale);
			
			repositoryDistribuzioni.insertConDataInizioNow(percDistNew);
			
			PercDistExt percDistExtNew = repositoryDistribuzioni.selectPercDistPerIdPerc(percDistNew.getPdId());
			
			ModelDistribuzionePercentuale modelPercentuale = new ModelDistribuzionePercentualePercExtMapper().from(percDistExtNew);
			
			return Response.ok(modelPercentuale).build();
			
		} catch (RESTException e) {
			log.error(methodName, " distribuzioniPercentualiGet ERROR RESTException", e);
			return e.getResponse();
		} catch(ClassCastException e) {
			log.error(methodName, "distribuzioniPercentualiGet ERROR ClassCastException", e);
			return ErroreBuilder.from(Status.BAD_REQUEST).descrizione("Campo percentuale id non valido").exception().getResponseBuilder().build();
		}
		catch (Exception e) {
			log.error(methodName, "distribuzioniPercentualiGet ERROR Exception", e);
			return CaRet.internalServerErrResponse();
		}
			
		
	}
	
	private CovidacCPercDist newCovidacCPercDist(CovidacCPercDist percDistFrom, Payload7 payload, String shibIdentitaCodiceFiscale) {
		CovidacCPercDist percDistNew = new CovidacCPercDist();
		
		percDistNew.setIdEnte(percDistFrom.getIdEnte());
		percDistNew.setIdStruttura(percDistFrom.getIdStruttura());
		percDistNew.setPdAlgoritmoDistribuzione(percDistFrom.getPdAlgoritmoDistribuzione());
		percDistNew.setPdPercentualeDistribuzione(payload.getPercentualeDistribuzione());
		percDistNew.setUtenteOperazione(shibIdentitaCodiceFiscale);
		return percDistNew;
	}
}
