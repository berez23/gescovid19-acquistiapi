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

import it.csi.gescovid.acquistiapi.business.be.RichiesteApi;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacDRichiestaStato;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacRRichiestaDetStato;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacTProd;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacTRichiesta;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacTRichiestaDet;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.extended.CovidacDRichiestaStatoMapper;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.extended.CovidacRRichiestaDetStatoMapper;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.extended.CovidacTRichiestaMapper;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.extended.RichiesteServiceMapper;
import it.csi.gescovid.acquistiapi.dto.ModelEnte;
import it.csi.gescovid.acquistiapi.dto.ModelRichiesta;
import it.csi.gescovid.acquistiapi.dto.Payload;
import it.csi.gescovid.acquistiapi.dto.RichiesteDettagli;
import it.csi.gescovid.acquistiapi.exception.RESTException;
import it.csi.gescovid.acquistiapi.util.CaConstants;
import it.csi.gescovid.acquistiapi.util.CaRet;
import it.csi.gescovid.acquistiapi.util.CaUtils;
import it.csi.gescovid.acquistiapi.util.LogUtil;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class RichiesteApiServiceImpl extends AcquistiService implements RichiesteApi {

	@Autowired
	CovidacTRichiestaMapper covidacTRichiestaMapper;

	@Autowired
	CovidacDRichiestaStatoMapper covidacDRichiestaStatoMapper;

	@Autowired
	CovidacRRichiestaDetStatoMapper covidacRRichiestaDetStatoMapper;

	@Autowired
	RichiesteServiceMapper richiesteServiceMapper;

	LogUtil log = new LogUtil(RichiesteApiServiceImpl.class);

	@Override
	public Response richiesteGet(String shibIdentitaCodiceFiscale, String xApplicazioneCodice, String xRequestId,
			String ente, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		String methodName = "richiesteGet";
		log.info(methodName, "BEGIN");

		try {
			checkNotNull(shibIdentitaCodiceFiscale, CaConstants.IDENTITA_OBBLIGATORIA);
			checkNotNull(xApplicazioneCodice, CaConstants.APPLICATION_ID_OBBLIGATORIO);

			logAudit(httpHeaders, methodName);

			checkUtenteAutorizzato(shibIdentitaCodiceFiscale, xApplicazioneCodice);

			ModelEnte modelEnte = recuperaEnte(ente);

			List<CovidacTRichiesta> selectByIdEnte = richiesteServiceMapper
					.selectRichiestaByIdEnte(CaUtils.toInteger(ente));

			Iterator<CovidacTRichiesta> iterator = selectByIdEnte.iterator();

			ArrayList<ModelRichiesta> elencoRichieste = new ArrayList<ModelRichiesta>();
			while (iterator.hasNext()) {
				CovidacTRichiesta next = iterator.next();

				ModelRichiesta richiesta = new ModelRichiesta();
				richiesta.setDataPeriodoA(next.getRichPeriodoA());
				richiesta.setDataPeriodoDa(next.getRichPeriodoDa());
				richiesta.setDataRichiesta(next.getRichData());
				richiesta.setEnte(modelEnte);
				richiesta.setId(CaUtils.toString(next.getRichId()));
				richiesta.setNote(next.getRichNote());

				richiesta.setDettagli(recuperaDettagli(next.getRichId()));

				elencoRichieste.add(richiesta);
			}

			return Response.ok(elencoRichieste).build();

		} catch (RESTException e) {
			log.error(methodName, "richiesteGet ERROR RESTException", e);
			return e.getResponse();
		} catch (Exception e) {
			log.error(methodName, "richiesteGet ERROR Exception", e);
			return CaRet.internalServerErrResponse();
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public Response richiestePost(String shibIdentitaCodiceFiscale, String xApplicazioneCodice, String xRequestId,
			Payload payload, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		String methodName = "richiestePost";
		log.info(methodName, "BEGIN");

		try {
			checkNotNull(shibIdentitaCodiceFiscale, CaConstants.IDENTITA_OBBLIGATORIA);
			checkNotNull(xApplicazioneCodice, CaConstants.APPLICATION_ID_OBBLIGATORIO);

			logAudit(httpHeaders, methodName);
			checkUtenteAutorizzato(shibIdentitaCodiceFiscale, xApplicazioneCodice);

			checkNotNull(payload, CaConstants.PAYLOAD_OBBLIGATORIO);
			checkNotBlank(payload.getEnte(), "Ente non valorizzato");
			checkNotNull(payload.getDettagli(), "Dettagli non valorizzati");

			List<RichiesteDettagli> dettagli = payload.getDettagli();
			checkDettagli(dettagli);

			Iterator<RichiesteDettagli> iterator = dettagli.iterator();

			Integer richId = null;
			if (iterator.hasNext()) {

				CovidacTRichiesta covidacTRichiesta = new CovidacTRichiesta();
				covidacTRichiesta.setIdEnte(CaUtils.toInteger(payload.getEnte()));
				covidacTRichiesta.setRichNote(payload.getNote());
				covidacTRichiesta.setUtenteOperazione(shibIdentitaCodiceFiscale);
				richiesteServiceMapper.insertRic(covidacTRichiesta); // FIXATO
				log.info(methodName, "covidacTRichiesta => RichId: %s", covidacTRichiesta.getRichId());

				richId = covidacTRichiesta.getRichId();
			}

			while (iterator.hasNext()) {
				RichiesteDettagli next = iterator.next();

				CovidacTRichiestaDet covidacTRichiestaDet = new CovidacTRichiestaDet();
				covidacTRichiestaDet.setFabbisognoGiornaliero(next.getFabbisognoGiornaliero());
				covidacTRichiestaDet.setFabbisognoSettimanale(next.getFabbisognoSettimanale());
				covidacTRichiestaDet.setQuantita(next.getQuantita());
				covidacTRichiestaDet.setDotazioneAttuale(next.getDotazioneAttuale());
				covidacTRichiestaDet.setProdId(getIdProdotto(next.getProdotto()));
				covidacTRichiestaDet.setRichId(richId);
				covidacTRichiestaDet.setUtenteOperazione(shibIdentitaCodiceFiscale);
				covidacTRichiestaDet.setIdStruttura(next.getStruttura());
				richiesteServiceMapper.insertRicDet(covidacTRichiestaDet);
				log.info(methodName, "covidacTRichiestaDet => RicdetId: %s", covidacTRichiestaDet.getRicdetId());

				CovidacDRichiestaStato covidacDRichiestaStato = richiesteServiceMapper
						.selectStatoRichiestaByStatoCod("I");

				CovidacRRichiestaDetStato covidacRRichiestaDetStato = new CovidacRRichiestaDetStato();
				covidacRRichiestaDetStato.setRicdetId(covidacTRichiestaDet.getRicdetId());
				covidacRRichiestaDetStato.setRicStatoId(covidacDRichiestaStato.getRicStatoId());
				covidacRRichiestaDetStato.setUtenteOperazione(shibIdentitaCodiceFiscale);
				richiesteServiceMapper.insertRicDetStato(covidacRRichiestaDetStato);
				log.info(methodName, "covidacRRichiestaDetStato => RicdetStatoId: %s",
						covidacRRichiestaDetStato.getRicdetStatoId());

			}

			ModelRichiesta richiestaInserita = getRichiestaInserita(richId);

			return Response.ok(richiestaInserita).build();

		} catch (RESTException e) {
			log.error(methodName, "richiestePost ERROR RESTException", e);
			return e.getResponse();
		} catch (Exception e) {
			log.error(methodName, "richiestePost ERROR Exception", e);
			// throw new RuntimeException("Errore durante la chiamata", e);
			return CaRet.internalServerErrResponse();
		}
	}

	private void checkDettagli(List<RichiesteDettagli> dettagli) {
		Iterator<RichiesteDettagli> iterator = dettagli.iterator();

		while (iterator.hasNext()) {
			RichiesteDettagli next = iterator.next();

			checkNotNull(next.getDotazioneAttuale(), "Dotazione attuale non valorizzata");
			checkNotNull(next.getFabbisognoGiornaliero(), "Fabbisogno giornaliero non valorizzato");
			checkNotNull(next.getFabbisognoSettimanale(), "Fabbisogno settimanale non valorizzato");
			checkNotNull(next.getQuantita(), "Quantita non valorizzata");
			checkNotBlank(next.getProdotto(), "Prodotto non valorizzato");
		}
	}

	private Integer getIdProdotto(String codiceProdotto) {
		CovidacTProd selectByCodiceProdotto = covidacTProdMapper.selectByCodiceProdotto(codiceProdotto);
		return selectByCodiceProdotto.getProdId();
	}

	private ModelRichiesta getRichiestaInserita(Integer idRichiesta) {
		ModelRichiesta richiesta = new ModelRichiesta();

		CovidacTRichiesta richiestaDB = covidacTRichiestaMapper.selectByPrimaryKey(idRichiesta);

		richiesta.setDataPeriodoA(richiestaDB.getRichPeriodoA());
		richiesta.setDataPeriodoDa(richiestaDB.getRichPeriodoDa());
		richiesta.setDataRichiesta(richiestaDB.getRichData());
		richiesta.setDettagli(recuperaDettagli(idRichiesta));
		richiesta.setEnte(recuperaEnte(richiestaDB.getIdEnte()));
		richiesta.setId(CaUtils.toString(richiestaDB.getRichId()));
		richiesta.setNote(richiestaDB.getRichNote());

		return richiesta;
	}

}
