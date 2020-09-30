package it.csi.gescovid.acquistiapi.business.be.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.csi.gescovid.acquistiapi.business.be.DistribuzioniApi;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacRMagazProd;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacTMagazUscita;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacTProd;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.extended.EnteExt;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.extended.richieste.SelectAllMagazzinoUscita;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.extended.CovidacRMagazProdMapper;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.extended.CovidacTMagazUscitaMapper;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.extended.CovidacTMagazzinoMapper;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.extended.CovidacTProdMapper;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.extended.DistribuzioniServiceMapper;
import it.csi.gescovid.acquistiapi.dto.ModelDistribuzione;
import it.csi.gescovid.acquistiapi.dto.ModelEnte;
import it.csi.gescovid.acquistiapi.dto.ModelProdotto;
import it.csi.gescovid.acquistiapi.dto.ModelStruttura;
import it.csi.gescovid.acquistiapi.dto.Payload6;
import it.csi.gescovid.acquistiapi.exception.RESTException;
import it.csi.gescovid.acquistiapi.util.CaConstants;
import it.csi.gescovid.acquistiapi.util.CaRet;
import it.csi.gescovid.acquistiapi.util.CaUtils;
import it.csi.gescovid.acquistiapi.util.LogUtil;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class DistribuzioniApiServiceImpl extends AcquistiService implements DistribuzioniApi {

	@Autowired
	CovidacTMagazUscitaMapper covidacTMagazUscitaMapper;

	@Autowired
	CovidacTProdMapper covidacTProdMapper;

	@Autowired
	CovidacTMagazzinoMapper covidacTMagazzinoMapper;

	@Autowired
	CovidacRMagazProdMapper covidacRMagazProdMapper;

	@Autowired
	DistribuzioniServiceMapper distribuzioniServiceMapper;

	LogUtil log = new LogUtil(DistribuzioniApiServiceImpl.class);

	@Override
	public Response distribuzioniGet(String shibIdentitaCodiceFiscale, String xApplicazioneCodice, String xRequestId,
			String ente, Boolean inCorso, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		String methodName = "distribuzioniGet";
		log.info(methodName, "BEGIN");

		try {
			checkNotNull(shibIdentitaCodiceFiscale, CaConstants.IDENTITA_OBBLIGATORIA);
			checkNotNull(xApplicazioneCodice, CaConstants.APPLICATION_ID_OBBLIGATORIO);

			logAudit(httpHeaders, methodName);

			checkUtenteAutorizzato(shibIdentitaCodiceFiscale, xApplicazioneCodice);

			// ModelEnte modelEnte = recuperaEnte(ente, null);

			List<SelectAllMagazzinoUscita> elencoDistribuzioniDB = new ArrayList<SelectAllMagazzinoUscita>();

			if (inCorso == null) {
				inCorso = new Boolean(false);
			}

			if (CaUtils.isNotEmpty(ente) && inCorso.booleanValue()) {
				elencoDistribuzioniDB = distribuzioniServiceMapper
						.selectMagazzinoUscitaEnteInCorso(CaUtils.toInteger(ente));
			} else if (CaUtils.isNotEmpty(ente) && !inCorso.booleanValue()) {
				elencoDistribuzioniDB = distribuzioniServiceMapper.selectMagazzinoUscitaEnte(CaUtils.toInteger(ente));
			} else if (CaUtils.isEmpty(ente) && inCorso.booleanValue()) {
				elencoDistribuzioniDB = distribuzioniServiceMapper
						.selectMagazzinoUscitaIncorso(CaUtils.toInteger(ente));
			} else {
				elencoDistribuzioniDB = distribuzioniServiceMapper.selectMagazzinoUscitaAll();
			}

			Iterator<SelectAllMagazzinoUscita> iterator = elencoDistribuzioniDB.iterator();

			List<ModelDistribuzione> elencoDistribuzioni = new ArrayList<ModelDistribuzione>();
			while (iterator.hasNext()) {
				SelectAllMagazzinoUscita next = iterator.next();

				ModelDistribuzione distribuzione = new ModelDistribuzione();
				distribuzione.setId(CaUtils.toString(next.getMaguId()));
				distribuzione.setQuantita(next.getQuantita());
				distribuzione.setDataConsegna(next.getDataConsegna());
				distribuzione.setDataCreazione(next.getDataCreazione());
				distribuzione.setDataModifica(next.getDataModifica());
				distribuzione.setDataRegistrazione(next.getDataRegistrazione());

				EnteExt recuperaEnteStruttura = recuperaEnteStruttura(next.getIdEnte(), next.getIdStruttura());
				if (recuperaEnteStruttura != null) {

					// Setto l'ente per la singola distribuzione
					ModelEnte enteDB = new ModelEnte();
					enteDB.setId(CaUtils.toString(recuperaEnteStruttura.getIdEnte()));
					enteDB.setNome(recuperaEnteStruttura.getNome());
					distribuzione.setEnte(enteDB);

					// Setto la struttura per la singola distribuzione
					if (null != recuperaEnteStruttura.getIdStruttura()) {
						ModelStruttura strutturaDB = new ModelStruttura();
						strutturaDB.setEnteId(CaUtils.toString(recuperaEnteStruttura.getIdEnte()));
						strutturaDB.setId(recuperaEnteStruttura.getIdStruttura());
						strutturaDB.setNatura(recuperaEnteStruttura.getNaturaStruttura());
						strutturaDB.setNome(recuperaEnteStruttura.getNomeStruttura());
						distribuzione.setStruttura(strutturaDB);
					}
				}

				// Setto il magazzino per la singola distribuzione
				distribuzione.setMagazzino(recuperaMagazzino(next.getMagazId()));

				// Setto il prodotto per la singola distribuzione
				ModelProdotto modelProdotto = new ModelProdotto();
				modelProdotto.setCodice(next.getProdCod());
				modelProdotto.setDescrizione(next.getProdDesc());
				modelProdotto.setNome(next.getProdNome());
				distribuzione.setProdotto(modelProdotto);

				elencoDistribuzioni.add(distribuzione);
			}

			return Response.ok(elencoDistribuzioni).build();

		} catch (RESTException e) {
			log.error(methodName, " distribuzioniGet ERROR RESTException", e);
			return e.getResponse();
		} catch (Exception e) {
			log.error(methodName, "distribuzioniGet ERROR Exception", e);
			return CaRet.internalServerErrResponse();
		}
	}

	private CovidacRMagazProd recuperaRecordRMagazProdDaAggiornare(String methodName, Integer prodId) {
		CovidacRMagazProd covidacRMagazProdResDaAggiornare = richiesteServiceMapper.selectProdmagIdForUpdate(prodId,
				getIdMagazzino());
		log.info(methodName, "covidacRMagazProdResDaAggiornare => prodId: %s, getIdMagazzino(): %s", prodId,
				getIdMagazzino());
		return covidacRMagazProdResDaAggiornare;
	}

	private Integer getIdProdotto(String codiceProdotto) {
		CovidacTProd selectByCodiceProdotto = covidacTProdMapper.selectByCodiceProdotto(codiceProdotto);
		return selectByCodiceProdotto.getProdId();
	}

	@Override
	public Response distribuzioniPost(String shibIdentitaCodiceFiscale, String xApplicazioneCodice, String xRequestId,
			Payload6 payload, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		String methodName = "distribuzioniPost";
		log.info(methodName, "BEGIN");

		try {

			checkNotNull(shibIdentitaCodiceFiscale, CaConstants.IDENTITA_OBBLIGATORIA);
			checkNotNull(xApplicazioneCodice, CaConstants.APPLICATION_ID_OBBLIGATORIO);

			logAudit(httpHeaders, methodName);
			checkUtenteAutorizzato(shibIdentitaCodiceFiscale, xApplicazioneCodice);

			checkNotNull(payload, CaConstants.PAYLOAD_OBBLIGATORIO);
			checkNotNull(payload.getQuantita(), "Quantita non valorizzata");
			checkNotNull(payload.getDataConsegna(), "Data consegna non valorizzata");
			checkNotBlank(payload.getEnte(), "Ente non valorizzato");
			checkNotBlank(payload.getProdotto(), "Prodotto non valorizzato");
			checkNotNull(getIdProdotto(payload.getProdotto()), "Prodotto non valido");
			CovidacRMagazProd covidacRMagazProdResDaAggiornare = recuperaRecordRMagazProdDaAggiornare(methodName,
					(getIdProdotto(payload.getProdotto())));
			checkNotNull(covidacRMagazProdResDaAggiornare, "Codice prodotto non valido");
			Integer checkDisponibilita = CaUtils.differenza(covidacRMagazProdResDaAggiornare.getQuantitaDisponibile(),
					payload.getQuantita());
			checkCondition(checkDisponibilita >= 0,
					"Quantita richiesta inferiore alla quantita disponibile a magazzino");

			CovidacTMagazUscita covidacTMagazUscita = new CovidacTMagazUscita();
			covidacTMagazUscita.setDataConsegna(payload.getDataConsegna());
			covidacTMagazUscita.setIdEnte(CaUtils.toInteger(payload.getEnte()));
			covidacTMagazUscita.setIdStruttura(payload.getStruttura());
			covidacTMagazUscita.setMagazId(getIdMagazzino());

			CovidacTProd covidacTProd = covidacTProdMapper.selectByCodiceProdotto(payload.getProdotto());
			covidacTMagazUscita.setProdId(covidacTProd.getProdId());

			covidacTMagazUscita.setQuantita(payload.getQuantita());
			covidacTMagazUscita.setQuantitaDistribuibile(payload.getQuantitaDistribuibile());
			covidacTMagazUscita.setUtenteOperazione(shibIdentitaCodiceFiscale);

			richiesteServiceMapper.insertMagazUscita(covidacTMagazUscita);
			log.info(methodName, "covidacTMagazUscita => MaguId: %s", covidacTMagazUscita.getMaguId());

			CovidacRMagazProd covidacRMagazProdDaAgg = new CovidacRMagazProd();
			BeanUtils.copyProperties(covidacRMagazProdDaAgg, covidacRMagazProdResDaAggiornare);
			covidacRMagazProdDaAgg.setProdmagId(covidacRMagazProdResDaAggiornare.getProdmagId());
			covidacRMagazProdDaAgg.setUtenteOperazione(shibIdentitaCodiceFiscale);
			richiesteServiceMapper.updateForDistByPrimaryKey(covidacRMagazProdDaAgg);

			CovidacRMagazProd covidacRMagazProdIns = new CovidacRMagazProd();
			covidacRMagazProdIns.setMagazId(covidacRMagazProdResDaAggiornare.getMagazId());
			covidacRMagazProdIns.setProdId(covidacRMagazProdResDaAggiornare.getProdId());

			covidacRMagazProdIns.setQuantitaDisponibile(CaUtils
					.differenza(covidacRMagazProdResDaAggiornare.getQuantitaDisponibile(), payload.getQuantita()));
			covidacRMagazProdIns.setQuantitaDistribuita(
					CaUtils.somma(covidacRMagazProdResDaAggiornare.getQuantitaDistribuita(), payload.getQuantita()));
			covidacRMagazProdIns.setUtenteOperazione(shibIdentitaCodiceFiscale);
			richiesteServiceMapper.insertRMagazProd(covidacRMagazProdIns);
			log.info(methodName, "covidacRMagazProdIns => ProdmagId: %s", covidacRMagazProdIns.getProdmagId());

			ModelDistribuzione response = recuperaInfoSingolaDistribuzione(covidacTMagazUscita.getMaguId(),
					covidacTMagazUscita.getIdStruttura());

			return Response.ok(response).build();

		} catch (RESTException e) {
			log.error(methodName, "distribuzioniPost ERROR RESTException", e);
			return e.getResponse();
		} catch (Exception e) {
			log.error(methodName, "distribuzioniPost ERROR Exception", e);
			// throw new RuntimeException("Errore durante la chiamata", e);
			return CaRet.internalServerErrResponse();
		}

	}

}
