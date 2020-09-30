package it.csi.gescovid.acquistiapi.business.be.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;

import it.csi.gescovid.acquistiapi.business.be.StruttureApi;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.Struttura;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.extended.StrutturaServiceMapper;
import it.csi.gescovid.acquistiapi.dto.ModelStruttura;
import it.csi.gescovid.acquistiapi.exception.RESTException;
import it.csi.gescovid.acquistiapi.util.CaConstants;
import it.csi.gescovid.acquistiapi.util.CaRet;
import it.csi.gescovid.acquistiapi.util.CaUtils;
import it.csi.gescovid.acquistiapi.util.LogUtil;

public class StruttureApiServiceImpl extends RESTBaseService implements StruttureApi {

	@Autowired
	StrutturaServiceMapper strutturaServiceMapper;

	LogUtil log = new LogUtil(this.getClass());

	@Override
	public Response struttureGet(String shibIdentitaCodiceFiscale, String xApplicazioneCodice, String xRequestId,
			String ente, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		String methodName = "struttureGet";
		log.info(methodName, "BEGIN");

		try {
			checkNotNull(shibIdentitaCodiceFiscale, CaConstants.IDENTITA_OBBLIGATORIA);
			checkNotNull(xApplicazioneCodice, CaConstants.APPLICATION_ID_OBBLIGATORIO);

			logAudit(httpHeaders, methodName);

			checkUtenteAutorizzato(shibIdentitaCodiceFiscale, xApplicazioneCodice);

			List<Struttura> elencoStruttureDB = strutturaServiceMapper
					.selectElencoStruttureByIdEnte(CaUtils.toInteger(ente));

			Iterator<Struttura> iterator = elencoStruttureDB.iterator();

			ArrayList<ModelStruttura> elencoStrutture = new ArrayList<ModelStruttura>();
			while (iterator.hasNext()) {

				Struttura next = iterator.next();

				ModelStruttura modelStruttura = new ModelStruttura();
				modelStruttura.setId(next.getIdStruttura());
				modelStruttura.setNome(next.getNome());
				modelStruttura.setNatura(next.getNatura());
				modelStruttura.setEnteId(CaUtils.toString(next.getIdEnte()));

				elencoStrutture.add(modelStruttura);
			}

			return Response.ok(elencoStrutture).build();

		} catch (RESTException e) {
			log.error(methodName, "ERROR RESTException", e);
			return e.getResponse();
		} catch (Exception e) {
			log.error(methodName, "ERROR Exception", e);
			return CaRet.internalServerErrResponse();
		}

	}

}
