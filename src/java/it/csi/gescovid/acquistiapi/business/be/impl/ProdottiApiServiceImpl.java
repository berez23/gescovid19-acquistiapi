package it.csi.gescovid.acquistiapi.business.be.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.csi.gescovid.acquistiapi.business.be.ProdottiApi;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacTProd;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.generated.BaseCovidacTProdMapper;
import it.csi.gescovid.acquistiapi.dto.ModelProdotto;
import it.csi.gescovid.acquistiapi.exception.RESTException;
import it.csi.gescovid.acquistiapi.util.CaConstants;
import it.csi.gescovid.acquistiapi.util.CaRet;
import it.csi.gescovid.acquistiapi.util.LogUtil;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class ProdottiApiServiceImpl extends AcquistiService implements ProdottiApi {

	@Autowired
	BaseCovidacTProdMapper baseCovidacTProdMapper;

	LogUtil log = new LogUtil(ProdottiApiServiceImpl.class);

	@Override
	public Response prodottiGet(String shibIdentitaCodiceFiscale, String xApplicazioneCodice, String xRequestId,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		String methodName = "prodottiGet";
		log.info(methodName, "BEGIN");

		try {
			checkNotNull(shibIdentitaCodiceFiscale, CaConstants.IDENTITA_OBBLIGATORIA);
			checkNotNull(xApplicazioneCodice, CaConstants.APPLICATION_ID_OBBLIGATORIO);

			logAudit(httpHeaders, methodName);

			checkUtenteAutorizzato(shibIdentitaCodiceFiscale, xApplicazioneCodice);

			//List<CovidacTProd> selectAll = baseCovidacTProdMapper.selectAll();
			List<CovidacTProd> selectAll = richiesteServiceMapper.selectAllProducts();

			Iterator<CovidacTProd> iterator = selectAll.iterator();

			ArrayList<ModelProdotto> elencoProdotti = new ArrayList<ModelProdotto>();
			while (iterator.hasNext()) {

				CovidacTProd next = iterator.next();

				ModelProdotto prodotto = new ModelProdotto();
				prodotto.setCodice(next.getProdCod());
				prodotto.setDescrizione(next.getProdDesc());
				prodotto.setNome(next.getProdNome());

				elencoProdotti.add(prodotto);
			}

			return Response.ok(elencoProdotti).build();

		} catch (RESTException e) {
			log.error(methodName, "ERROR RESTException", e);
			return e.getResponse();
		} catch (Exception e) {
			log.error(methodName, "ERROR Exception", e);
			return CaRet.internalServerErrResponse();
		}

	}

}
