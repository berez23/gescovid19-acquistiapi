package it.csi.gescovid.acquistiapi.business.be.impl;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.csi.gescovid.acquistiapi.business.be.MeApi;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.extended.UtentiExt;
import it.csi.gescovid.acquistiapi.dto.ModelEnte;
import it.csi.gescovid.acquistiapi.dto.ModelUtente;
import it.csi.gescovid.acquistiapi.exception.RESTException;
import it.csi.gescovid.acquistiapi.util.CaConstants;
import it.csi.gescovid.acquistiapi.util.CaRet;
import it.csi.gescovid.acquistiapi.util.CaUtils;
import it.csi.gescovid.acquistiapi.util.LogUtil;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class MeApiServiceImpl extends AcquistiService implements MeApi {

	LogUtil log = new LogUtil(MeApiServiceImpl.class);

	@Override
	public Response meGet(String shibIdentitaCodiceFiscale, String xApplicazioneCodice, String xRequestId,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		String methodName = "meGet";
		log.info(methodName, "BEGIN");

		try {
			checkNotNull(shibIdentitaCodiceFiscale, CaConstants.IDENTITA_OBBLIGATORIA);
			checkNotNull(xApplicazioneCodice, CaConstants.APPLICATION_ID_OBBLIGATORIO);

			logAudit(httpHeaders, methodName);

			checkUtenteAutorizzato(shibIdentitaCodiceFiscale, xApplicazioneCodice);

			UtentiExt utente = null;
			try {
				utente = utentiMapper.selectInfoEnte(shibIdentitaCodiceFiscale);
			} catch (Exception e) {
				log.info(methodName, "Nessun ente associato al codice fiscale s% ", shibIdentitaCodiceFiscale);
			}

			ModelUtente modelUtente = new ModelUtente();
			modelUtente.setCf(shibIdentitaCodiceFiscale);

			if (utente != null) {
				ModelEnte modelEnte = new ModelEnte();
				modelEnte.setId(CaUtils.toString(utente.getId()));
				modelEnte.setNome(utente.getNome());
				modelUtente.setEnte(modelEnte);
			}

			return Response.ok(modelUtente).build();

		} catch (RESTException e) {
			log.error(methodName, "meGet ERROR RESTException", e);
			return e.getResponse();
		} catch (Exception e) {
			log.error(methodName, "meGet ERROR Exception", e);
			return CaRet.internalServerErrResponse();
		}
	}

}
