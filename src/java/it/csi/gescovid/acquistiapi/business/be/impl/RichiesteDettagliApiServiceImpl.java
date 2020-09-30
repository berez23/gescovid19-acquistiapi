package it.csi.gescovid.acquistiapi.business.be.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.jboss.resteasy.annotations.GZIP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.csi.gescovid.acquistiapi.business.be.RichiesteDettagliApi;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.extended.CovidacTRichiestaDetMapper;
import it.csi.gescovid.acquistiapi.dto.ModelRichiestaDettaglioStandalone;
import it.csi.gescovid.acquistiapi.dto.Payload1;
import it.csi.gescovid.acquistiapi.exception.RESTException;
import it.csi.gescovid.acquistiapi.util.CaConstants;
import it.csi.gescovid.acquistiapi.util.CaRet;
import it.csi.gescovid.acquistiapi.util.LogUtil;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class RichiesteDettagliApiServiceImpl extends AcquistiService implements RichiesteDettagliApi {

	@Autowired
	CovidacTRichiestaDetMapper covidacTRichiestaDetMapper;

	LogUtil log = new LogUtil(RichiesteDettagliApiServiceImpl.class);

	@Override
	public Response richiesteDettagliDettaglioIdPut(String shibIdentitaCodiceFiscale, String xApplicazioneCodice,
			String xRequestId, String dettaglioId, Payload1 payload, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		// TODO Attenzione! Da non implementare!!!
		return null;
	}

	@GZIP
	@Override
	public Response richiesteDettagliGet(String shibIdentitaCodiceFiscale, String xApplicazioneCodice,
			String xRequestId, String ente, String stato, Boolean ordinati, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		String methodName = "richiesteDettagliGet";
		log.info(methodName, "BEGIN");

		try {
			checkNotNull(shibIdentitaCodiceFiscale, CaConstants.IDENTITA_OBBLIGATORIA);
			checkNotNull(xApplicazioneCodice, CaConstants.APPLICATION_ID_OBBLIGATORIO);

			logAudit(httpHeaders, methodName);

			checkUtenteAutorizzato(shibIdentitaCodiceFiscale, xApplicazioneCodice);

			List<ModelRichiestaDettaglioStandalone> elencoDettagli = recuperaDettagli(ente, stato, ordinati);

			return Response.ok(elencoDettagli).build();

		} catch (RESTException e) {
			log.error(methodName, "richiesteDettagliGet ERROR RESTException", e);
			return e.getResponse();
		} catch (Exception e) {
			log.error(methodName, "richiesteDettagliGet ERROR Exception", e);
			return CaRet.internalServerErrResponse();
		}
	}

}
