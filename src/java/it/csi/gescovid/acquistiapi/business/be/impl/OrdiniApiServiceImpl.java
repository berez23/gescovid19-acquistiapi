package it.csi.gescovid.acquistiapi.business.be.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.csi.gescovid.acquistiapi.business.be.OrdiniApi;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacDOrdineStato;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacRMagazProd;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacROrdineRicDet;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacROrdineStato;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacTMagazEntrata;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacTMagazzino;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacTOrdine;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacTProd;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.custom.CovidacTMagazEntrataCustom;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.extended.CountExt;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.extended.CovidacROrdineStatoExt;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.extended.CovidacTOrdineExt;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.extended.CovidacDOrdineStatoMapper;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.extended.CovidacRMagazProdMapper;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.extended.CovidacROrdineRicDetMapper;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.extended.CovidacROrdineStatoMapper;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.extended.CovidacTMagazEntrataMapper;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.extended.CovidacTOrdineMapper;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.extended.CovidacTProdMapper;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.extended.MagazzinoServiceMapper;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.extended.UtentiMapper;
import it.csi.gescovid.acquistiapi.business.mapping.ModelOrdineCovidacTOrdineExtMapper;
import it.csi.gescovid.acquistiapi.dto.ModelOrdine;
import it.csi.gescovid.acquistiapi.dto.ModelOrdineDettaglio;
import it.csi.gescovid.acquistiapi.dto.ModelOrdineDettaglioCambioStato;
import it.csi.gescovid.acquistiapi.dto.ModelOrdineStato;
import it.csi.gescovid.acquistiapi.dto.ModelProdotto;
import it.csi.gescovid.acquistiapi.dto.Payload2;
import it.csi.gescovid.acquistiapi.dto.Payload3;
import it.csi.gescovid.acquistiapi.dto.Payload4;
import it.csi.gescovid.acquistiapi.exception.ErroreBuilder;
import it.csi.gescovid.acquistiapi.exception.RESTException;
import it.csi.gescovid.acquistiapi.util.CaConstants;
import it.csi.gescovid.acquistiapi.util.CaUtils;
import it.csi.gescovid.acquistiapi.util.LogUtil;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class OrdiniApiServiceImpl extends RESTBaseService implements OrdiniApi {
	
	@Autowired
	CovidacTOrdineMapper repositoryOrdine;
	@Autowired
	CovidacTProdMapper  repositoryProd;
		
	@Autowired
	CovidacROrdineStatoMapper repositoryOrdiniStatoMapper;
	
	@Autowired
	CovidacROrdineRicDetMapper repositoryrOrdineRicDetmapper;
	
	@Autowired
	UtentiMapper utentiMapper;
	
	@Autowired
	CovidacRMagazProdMapper repositoryrMagazProd;
	
	@Autowired
	CovidacTMagazEntrataMapper repositoryRMagazEntrata;
		
	@Autowired
	MagazzinoServiceMapper repositoryMagazzino;
	
	@Autowired
	CovidacDOrdineStatoMapper ordineStatoMapper;
	
	LogUtil log = new LogUtil(this.getClass());

	@Override
	public Response ordiniGet(String shibIdentitaCodiceFiscale, String xApplicazioneCodice, String xRequestId,String stato,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		String methodName = "ordiniGet";
		log.info(methodName, "BEGIN");
		List<ModelOrdine> listaOrdini = null;
		try {
			checkNotNull(shibIdentitaCodiceFiscale, "Identità obbligatoria");
			checkNotNull(xApplicazioneCodice, "Application id obbligatoria");
			
			logAudit(httpHeaders, methodName);
			checkUtenteAutorizzato(shibIdentitaCodiceFiscale, xApplicazioneCodice);
			List<CovidacTOrdineExt> ordini = repositoryOrdine.selectOrdini(stato);
			listaOrdini = new ModelOrdineCovidacTOrdineExtMapper().fromList(ordini);

		} catch(RESTException e) {
			log.error(methodName, "ordiniGet ERROR RESTException", e);
			return e.getResponse();
		}
		catch(Exception e) {
			log.error(methodName, "ordiniGet ERROR RESTException", e);
			return ErroreBuilder.from(Status.INTERNAL_SERVER_ERROR).descrizione("Errore imprevisto").exception().getResponseBuilder().build();
		}
		return Response.ok().entity(listaOrdini).build() ;

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public Response ordiniOrdineIdPut(String shibIdentitaCodiceFiscale, String xApplicazioneCodice, String xRequestId,Integer ordineId,
			Payload3 payload, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = "ordiniOrdineIdPut";
		log.info(methodName, "BEGIN");
		ModelOrdine modelRit = null;
		try {
			Date dataFine = new Date();
			checkNotNull(shibIdentitaCodiceFiscale, "Identità obbligatoria");
			checkNotNull(xApplicazioneCodice, "Application id obbligatoria");
			
			logAudit(httpHeaders, methodName);
			checkUtenteAutorizzato(shibIdentitaCodiceFiscale, xApplicazioneCodice);
			checkNotNull(payload, "payload non valorizzato");
			checkNotNull(payload.getIdentificativoOrdine(), "Identificativo ordine non valorizzato");
			checkNotNull(payload.getStato(), "Stato ordine non valorizzato");
			checkNotNull(payload.getQuantitaConsegnata(), "Identificativo prodotto");
			checkNotNull(payload.getDataConsegna(), "Data consegna non valorizzata");
			checkNotNull(payload.getDataConsegnaPrevista(), "Data consegna prevista non valorizzata");
			checkNotNull(payload.getDataOrdine(), "Data Ordine non valorizzata");
			checkNotNull(payload.getQuantitaOrdinata(), "Quantita ordinata non valorizzata");
						
			checkNotBlank(payload.getIdentificativoOrdine(), "Identificativo ordine non valorizzato");
			checkNotBlank(payload.getStato(), "Stato ordine non valorizzato");
			
			
			//Tiro su il magazzino
			List<CovidacTMagazzino> magazzini = repositoryMagazzino.selectMagazzinoDataCancellazioneNull();
			checkNotNull(magazzini, "Magazzino non presente su database");
			checkCondition(magazzini.size() > 0, "Magazzino non presente su database");
			
			CovidacTMagazzino magazzino = magazzini.get(0);
			
			//primo step tiro su la covidac_t_ordine
			CovidacTOrdine covidadTOrdineNew = repositoryOrdine.selectByPrimaryKey(ordineId);
			checkNotNull(covidadTOrdineNew, "ordineId non corretto");
			
			log.info(methodName, "covidadTOrdineNew covidadTOrdineNew.getOrdId() %s, covidadTOrdineNew.getOrdIdentificativo() %s", 
					covidadTOrdineNew.getOrdId(), covidadTOrdineNew.getOrdIdentificativo());
			
			//secondo step tiro su tutti i dati di stato 
			CovidacDOrdineStato covidadDOrdineStatoNew = repositoryOrdine.selectStatoByCodiceStato(payload.getStato());
			checkNotNull(covidadDOrdineStatoNew, "Stato non corretto");
						
			log.info(methodName, "covidadDOrdineStatoNew covidadDOrdineStatoNew.getOrdStatoCod() %s, covidadDOrdineStatoNew.getOrdStatoDesc() %s", covidadDOrdineStatoNew.getOrdStatoCod(), 
					covidadDOrdineStatoNew.getOrdStatoDesc());			
			
			//terzo step tiro su i dati di prodotto
			CovidacTProd covidadTProdNew = getProdottoByCodice(payload.getProdotto());
			checkNotNull(covidadTProdNew, "Codice prodotto non valido");
			
			//TODO verificare che il prodotto non sia gia nell'ordine
			log.info(methodName, "covidadTProdNew covidadTProdNew.getProdId()%s, covidadTProdNew.getProdCod() %s, covidadTProdNew.getProdDesc() %s", 
					covidadTProdNew.getProdId(), covidadTProdNew.getProdCod(), covidadTProdNew.getProdDesc());
			
			//quarto step tiro su i dati di stato covidacROrdinestato per poi mettere  data fine validita = now()
			
			CovidacROrdineStato  rOrdinestatoDaModificare = repositoryOrdine.selectROrdineStatoByIdOrdIdStato(covidadTOrdineNew.getOrdId());
			rOrdinestatoDaModificare.setValiditaFine(dataFine);
			log.info(methodName, "rOrdinestatoDaModificare rOrdinestatoDaModificare.getOrdrstatoId() %s, rOrdinestatoDaModificare.getOrdStatoId() %s, rOrdinestatoDaModificare.getOrdId() %s", 
					rOrdinestatoDaModificare.getOrdrstatoId(), rOrdinestatoDaModificare.getOrdStatoId(), rOrdinestatoDaModificare.getOrdId() );			
			
			//quinto step creo un nuovo stato covidacROrdinestato
			CovidacROrdineStato rOrdinestatoDaInserire = 
					creaNuovoCovidacROrdineStato(rOrdinestatoDaModificare, covidadDOrdineStatoNew, payload, shibIdentitaCodiceFiscale); 
			
			log.info(methodName, "rOrdinestatoDaInserirere rOrdinestatoDaInserirere.getOrdrstatoId()  %s, rOrdinestatoDaInserirere.getOrdStatoId()  %s, rOrdinestatoDaInserirere.getOrdId()  %s", 
					rOrdinestatoDaInserire.getOrdrstatoId(), rOrdinestatoDaInserire.getOrdStatoId(), rOrdinestatoDaInserire.getOrdId()  );
	
			//sesto step costruisco ordine
			covidadTOrdineNew.setOrdIdentificativo(payload.getIdentificativoOrdine());
			covidadTOrdineNew.setQuantitaOrdinata(payload.getQuantitaOrdinata());
			covidadTOrdineNew.setOrdDataConsegnaPrevista(payload.getDataConsegnaPrevista());
			covidadTOrdineNew.setQuantitaConsegnata(// 02-05-2020 modifica per gestire lo stato di consegna parziale
					CaUtils.somma(covidadTOrdineNew.getQuantitaConsegnata(), payload.getQuantitaConsegnata()));
			covidadTOrdineNew.setOrdDataConsegna(payload.getDataConsegna());
			covidadTOrdineNew.setOrdData(payload.getDataOrdine());
			covidadTOrdineNew.setProdId(covidadTProdNew.getProdId());			
			
			//settimo step devo tirare su un record da covidac_r_magaz_prod
			CovidacRMagazProd relazioneMagazOld = repositoryOrdine.selectRMagazProdByIdProd(covidadTProdNew.getProdId());
			if(relazioneMagazOld != null){
				log.info(methodName, "relazioneMagazOld.getProdmagId() %s, relazioneMagazOld.getMagazId() %s, relazioneMagazOld.getProdId() %s", relazioneMagazOld.getProdmagId(), relazioneMagazOld.getMagazId(), relazioneMagazOld.getProdId());
				relazioneMagazOld.setValiditaFine(dataFine);
				
			}
			
			//ottavo costruisco TMagaEntrata
			CovidacTMagazEntrata magazzinoEntrata = creaNuovoMagazzinoEntrata(magazzino, covidadTProdNew.getProdId(), payload, shibIdentitaCodiceFiscale);
			
			log.info(methodName, "magazzinoEntrata.getMageId() %s, magazzinoEntrata.getProdId() %s, magazzinoEntrata.getMagazId() %s ", magazzinoEntrata.getMageId(), 
					magazzinoEntrata.getProdId(), magazzinoEntrata.getMagazId());
			
			//nono step costruisco nuovo record su tabella di relazione
			CovidacRMagazProd relazioneMagazNew = null;
			if(relazioneMagazOld != null){
				relazioneMagazNew = costruisciNuovoRMagazProd(magazzino, covidadTProdNew, relazioneMagazOld, payload, shibIdentitaCodiceFiscale);				
			}else {
				relazioneMagazNew = costruisciNuovoRMagazProd(magazzino, covidadTProdNew, null, payload, shibIdentitaCodiceFiscale);
			}
			
			
			log.info(methodName, "relazioneMagazNew.getProdmagId() %s, relazioneMagazNew.getMagazId() %s, relazioneMagazNew.getProdId() %s", relazioneMagazNew.getProdmagId(), 
					relazioneMagazNew.getMagazId(), relazioneMagazNew.getProdId());
			
			log.info(methodName, "relazioneMagazNew.getProdmagId() %s, relazioneMagazNew.getProdId() %s, relazioneMagazNew.getMagazId() %s", relazioneMagazNew.getProdmagId(), relazioneMagazNew.getProdId(), 
					relazioneMagazNew.getMagazId());
			
			
			//decimo step modifico ordine 
			repositoryOrdine.updateOrdineByPrimaryKey(covidadTOrdineNew);
			
			//undicesimo step devo fare update del vecchio record su covidacROrdinestato  
			//******* modifica bug now()  
			 //repositoryOrdiniStatoMapper.updateByPrimaryKey(rOrdinestatoDaModificare);
			 repositoryOrdine.updateROrdineStatoDataFineNow(rOrdinestatoDaModificare);
			 
			 
			 //dodicesimo step inserisco il nuovo record su covidacROrdinestato
			 //repositoryOrdiniStatoMapper.insert(rOrdinestatoDaInserirere);
			//******* modifica bug now()
			 repositoryOrdine.insertROrdineStatoValiditaInizioNow(rOrdinestatoDaInserire);
			 
			 //tredicesimo step inserisco nuovo record in TMagazEntrata
			 //repositoryRMagazEntrata.insert(magazzinoEntrata);
			// 02-05-2020 modifica per gestire lo stato di consegna parziale
			 CovidacTMagazEntrataCustom magazEntrataCustom = new CovidacTMagazEntrataCustom();
			 magazEntrataCustom.setMagazId(magazzinoEntrata.getMagazId());
			 magazEntrataCustom.setParDataRegistrazione(CaUtils.convertiData(magazzinoEntrata.getDataRegistrazione(), "yyyy-MM-dd"));
			 magazEntrataCustom.setProdId(magazzinoEntrata.getProdId());
			 magazEntrataCustom.setQuantita(magazzinoEntrata.getQuantita());
			 magazEntrataCustom.setUtenteOperazione(magazzinoEntrata.getUtenteOperazione());
			 repositoryMagazzino.insertMagazzino(magazEntrataCustom);
			 
			 //quattordicesimo step metto data fine nella tabella di relazione
			//******* modifica bug now()
			 if(relazioneMagazOld != null) {
				 //repositoryrMagazProd.updateByPrimaryKey(relazioneMagazOld);
				 repositoryOrdine.updateRMagazProdDataFineNow(relazioneMagazOld);
			 }
			 
			//quindicesimo step inserisco un nuovo record nella tabella di relazione
			 //repositoryrMagazProd.insert(relazioneMagazNew);
			//******* modifica bug now()
			 repositoryOrdine.insertRMagazProdValiditaInizioNow(relazioneMagazNew);
			 
			//la select sembra nno aggiornare il dato me lo devo  costruire
			 
			CovidacTOrdineExt ordine = repositoryOrdine.selectOrdiniById(ordineId);
			
			
			log.info(methodName, "ordine su cui faccio la select: %s", ordineId); 
			modelRit = new ModelOrdineCovidacTOrdineExtMapper().from(ordine);
			
			 
			//modelRit = costruisciModelDopoModifica(covidadTOrdineNew, covidadDOrdineStatoNew,  covidadTProdNew);
			log.info(methodName, "modelRit.getIdentificativoOrdine() %s, modelRit.getStato().getCodice() %s, modelRit.getStato().getDescrizione() %s", 
					modelRit.getIdentificativoOrdine(), modelRit.getStato().getCodice(), modelRit.getStato().getDescrizione());
			
			
		} catch(RESTException e) {
			log.error(methodName, "ordiniGet ERROR RESTException", e);
			return e.getResponse();
		} catch(org.springframework.dao.DataIntegrityViolationException e) {
			log.error(methodName, "ERROR ", e);;
			if(e.getMessage().contains("idx_covidac_t_magaz_entrat")) {
				return ErroreBuilder.from(Status.NOT_ACCEPTABLE).descrizione("prodotto con data consegna gia presente").exception().getResponseBuilder().build();
			}else {
				return ErroreBuilder.from(Status.NOT_ACCEPTABLE).descrizione("dati non congruenti").exception().getResponseBuilder().build();
			}
			
		}
		catch(Exception e) {
			log.error(methodName, "ordiniGet ERROR RESTException", e);
			throw new RuntimeException("Errore imprevisto", e);
			//return ErroreBuilder.from(Status.INTERNAL_SERVER_ERROR).descrizione("Errore imprevisto").exception().getResponseBuilder().build();
		}
		return Response.ok().entity(modelRit).build();
	}
	
	
	private ModelOrdine costruisciModelDopoModifica(CovidacTOrdine dest, CovidacDOrdineStato statonew, CovidacTProd prodNew) {
		
		ModelOrdine source = new ModelOrdine();
		source.setId( dest.getOrdId());
		source.setDataConsegna(dest.getOrdDataConsegna());
		source.setDataCreazione(dest.getDataCreazione());
		source.setDataOrdine(dest.getOrdData());
		source.setIdentificativoOrdine(dest.getOrdIdentificativo());
		source.setDataConsegnaPrevista(dest.getOrdDataConsegnaPrevista());
		source.setQuantitaConsegnata(dest.getQuantitaConsegnata());   
		
		ModelProdotto prodotto = new ModelProdotto();
		prodotto.setCodice(prodNew.getProdCod());
		prodotto.setDescrizione(prodNew.getProdDesc());
		prodotto.setNome(prodNew.getProdNome());
		source.setProdotto(prodotto);
		
		source.setQuantitaConsegnata(dest.getQuantitaConsegnata());
		source.setQuantitaOrdinata(dest.getQuantitaOrdinata());

		ModelOrdineStato stato = new ModelOrdineStato();
		stato.setCodice(statonew.getOrdStatoCod());
		stato.setDescrizione(statonew.getOrdStatoDesc());
		source.setStato(stato);
		
		return source;
		
	}
	
	private CovidacRMagazProd costruisciNuovoRMagazProd(CovidacTMagazzino magazzino, CovidacTProd covidadTProdNew, CovidacRMagazProd rMagazOld, Payload3 payload, String shiIdentitaCodiceFiscale) {

		CovidacRMagazProd covidacRMagazProdNew = new CovidacRMagazProd();
		covidacRMagazProdNew.setDataCreazione(new Date());
		covidacRMagazProdNew.setMagazId(magazzino.getMagazId());
		covidacRMagazProdNew.setProdId(covidadTProdNew.getProdId());
		if(rMagazOld != null) {
			covidacRMagazProdNew.setQuantitaDistribuita(rMagazOld.getQuantitaDistribuita());			
			covidacRMagazProdNew.setQuantitaDisponibile(new Integer(rMagazOld.getQuantitaDisponibile() + payload.getQuantitaConsegnata()));
		}else {
			covidacRMagazProdNew.setQuantitaDistribuita(new Integer(0));			
			covidacRMagazProdNew.setQuantitaDisponibile( payload.getQuantitaConsegnata());			
		}
		
		covidacRMagazProdNew.setUtenteOperazione(shiIdentitaCodiceFiscale);
		covidacRMagazProdNew.setValiditaInizio(new Date());
		covidacRMagazProdNew.setDataModifica(new Date());
		
		return covidacRMagazProdNew;
	}
	
	private CovidacTMagazEntrata creaNuovoMagazzinoEntrata(CovidacTMagazzino magazzino, Integer prodId, Payload3 payload, String shibIdentitaCodiceFiscale) {

		CovidacTMagazEntrata magazEntrata = new CovidacTMagazEntrata();
		magazEntrata.setMagazId(magazzino.getMagazId() );
		magazEntrata.setProdId(prodId);
		magazEntrata.setQuantita(payload.getQuantitaConsegnata());
		magazEntrata.setDataRegistrazione(payload.getDataConsegna());
		magazEntrata.setUtenteOperazione(shibIdentitaCodiceFiscale);
		//magazEntrata.setDataCreazione(new Date());
		//magazEntrata.setDataModifica(new Date());

		return magazEntrata;
	}
	
	private CovidacROrdineStato creaNuovoCovidacROrdineStato(
			CovidacROrdineStato old, CovidacDOrdineStato covidadDOrdineStatoNew, Payload3 payload, String shibIdentitaCodiceFiscale) {

		CovidacROrdineStato covidacROrdineStatoNew = new CovidacROrdineStato();

		//campi nuovi
		covidacROrdineStatoNew.setDataCreazione(new Date());
		covidacROrdineStatoNew.setDataModifica(new Date());
		covidacROrdineStatoNew.setOrdStatoId(covidadDOrdineStatoNew.getOrdStatoId());
		covidacROrdineStatoNew.setValiditaInizio(new Date());
		covidacROrdineStatoNew.setDataConsegna(payload.getDataConsegna()); // 02-05-2020 modifica per gestire lo stato di consegna parziale
		covidacROrdineStatoNew.setQuantitaConsegnata(payload.getQuantitaConsegnata()); // 02-05-2020 modifica per gestire lo stato di consegna parziale
		covidacROrdineStatoNew.setUtenteOperazione(shibIdentitaCodiceFiscale);
		//campi presi dal vecchio
		covidacROrdineStatoNew.setOrdId(old.getOrdId());

		return covidacROrdineStatoNew;
	}
	 
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public Response ordiniPost(String shibIdentitaCodiceFiscale, String xApplicazioneCodice, String xRequestId, Payload2 payload,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		String methodName = "ordiniPost";
		log.info(methodName, "BEGIN");
		ModelOrdine rit = null;
		try {
			//Prima cosa verifico che tutti i parametri siano impostati
			checkNotNull(shibIdentitaCodiceFiscale, "Identità obbligatoria");
			checkNotNull(xApplicazioneCodice, "Application id obbligatoria");
			logAudit(httpHeaders, methodName);
			checkUtenteAutorizzato(shibIdentitaCodiceFiscale, xApplicazioneCodice);
			checkNotNull(payload.getIdentificativoOrdine(), "Identificativo ordine non valorizzato");
			checkNotNull(payload.getDataConsegnaPrevista(), "Data consegna prevista non valorizzata");
			checkNotNull(payload.getDataOrdine(), "Data Ordine non valorizzata");
			checkNotNull(payload.getRichiestaDettagli(), "Dettagli non valorizzati");
			checkNotNull(payload.getProdotto(), "Prodotto non valorizzato");
			checkNotNull(payload.getQuantitaOrdinata(), "Quantita ordinata non valorizzata");
			CovidacTProd prodotto = getProdottoByCodice(payload.getProdotto()); 
			checkNotNull(prodotto, "Prodotto non valido");
			
			//prima cosa verifico che non ci sia gia un ordine con uguale identificativo e prodid
			
			CovidacTOrdine ordineEsistente = repositoryOrdine.selectOrdineByCodProdEIdentificativo(prodotto.getProdId(), payload.getIdentificativoOrdine());
			checkCondition(ordineEsistente== null, "Identificativo ordine associato al prodotto gia' esistente sul database");
			
			//seconda cosa verifico che el richieste non siano associate a un ordine
			if(payload.getRichiestaDettagli() != null && payload.getRichiestaDettagli().size() > 0) {
				Integer[] arrayInt = new Integer[payload.getRichiestaDettagli().size()];
				int count = 0;
				for(String ric : payload.getRichiestaDettagli()) {
					arrayInt[count] = Integer.parseInt(ric) ; 
					count++;
				}
				List<CovidacROrdineRicDet> listaOrdiniDettRit = repositoryOrdine.selecDettRic(arrayInt);
				
				if(listaOrdiniDettRit != null && listaOrdiniDettRit.size() >0) {
					return ErroreBuilder.from(Status.BAD_REQUEST).descrizione("Dettaglio richiesta associato a un ordine").exception().getResponseBuilder().build();
				}
				//aggiungere controllo che tutti i dett_richiesta siano associati a quel prodotto
				CountExt countRic = repositoryOrdine.selecCountDettRic(arrayInt, prodotto.getProdId());
				checkCondition((countRic.getCount().intValue() == payload.getRichiestaDettagli().size()), "Dettaglio richiesta non associato al prodotto");
			}
			
			CovidacTOrdine ordine =costruiscitOrdine(payload, prodotto, shibIdentitaCodiceFiscale);
			
			//primo step inserisco su covid_t_ordine
			repositoryOrdine.insert(ordine );
			
			//secondo step devo inserire su covidac_r_ordine_stato
			CovidacDOrdineStato stato = repositoryOrdine.selectStatoByCodiceStato("I");
			CovidacROrdineStato rOrdineStato = costruisciOrdineStato(stato, ordine, shibIdentitaCodiceFiscale);
			
			//******* modifica bug now()
			//repositoryOrdiniStatoMapper.insert(rOrdineStato);
			repositoryOrdine.insertROrdineStatoNew(rOrdineStato);
			
			//Terzo step inserisco l'array di richieste su covidac_r_ordine_ric_det (assumo che gli id dell richieste siano corretti)
			//Devo fare un ciclo per tutte le richieste passatemi solo nel caso in cui l'array sia maggior di 0
			if(payload.getRichiestaDettagli().size() > 0) {
				for(String ricDet : payload.getRichiestaDettagli()) {
					CovidacROrdineRicDet tmp = costruisciRRichiestadettaglio(ricDet, ordine.getOrdId(), shibIdentitaCodiceFiscale);
					repositoryrOrdineRicDetmapper.insert(tmp);
					
				}
			}
			
			CovidacTOrdineExt ordineExtRit = repositoryOrdine.selectOrdineById(ordine.getOrdId());
			rit =  new ModelOrdineCovidacTOrdineExtMapper().from(ordineExtRit);
		}catch(RESTException e) {
			log.error(methodName, "ERROR", e);
			return e.getResponse();
		}
		catch(NumberFormatException e) {
			log.error(methodName, "ERROR", e);
			return ErroreBuilder.from(Status.BAD_REQUEST).descrizione("Campo numero non valido").exception().getResponseBuilder().build();
		}
		catch(org.springframework.dao.DataIntegrityViolationException e) {
			log.error(methodName, "ERROR ", e);;
			return ErroreBuilder.from(Status.NOT_ACCEPTABLE).descrizione("dati non congruenti").exception().getResponseBuilder().build();
		}
		catch(Exception e) {
			log.error(methodName, "ERROR", e);
			return ErroreBuilder.from(Status.INTERNAL_SERVER_ERROR).descrizione("Errore imprevisto").exception().getResponseBuilder().build();
		}
		return Response.status(Status.CREATED).entity(rit).build();
	}
	
	@Override
	public Response ordiniOrdineIdQuantitaOrdinataPut(String shibIdentitaCodiceFiscale, String xApplicazioneCodice,
			String xRequestId, Integer ordineId, Payload4 payload, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		String methodName = "ordiniOrdineIdQuantitaOrdinataPut";
		log.info(methodName, "BEGIN");
		ModelOrdine rit = null;
		try {
			//Prima cosa verifico che tutti i parametri siano impostati
			checkNotNull(shibIdentitaCodiceFiscale, "Identità obbligatoria");
			checkNotNull(xApplicazioneCodice, "Application id obbligatoria");
			logAudit(httpHeaders, methodName);
			checkUtenteAutorizzato(shibIdentitaCodiceFiscale, xApplicazioneCodice);
			CovidacTOrdineExt ordine = repositoryOrdine.selectOrdiniById(ordineId);
			checkNotNull(ordine, "Id Ordine non corretto");
			
			CovidacROrdineStato rStatoOrdine = repositoryOrdine.selectROrdineStatoByIdOrdIdStato(ordine.getOrdId());
			CovidacDOrdineStato statoOrdine = ordineStatoMapper.selectByPrimaryKey(rStatoOrdine.getOrdStatoId());
			checkCondition(statoOrdine.getOrdStatoCod().equalsIgnoreCase("I"), "Ordine non modificabile");
			ordine.setQuantitaOrdinata(payload.getQuantitaOrdinata());
			ordine.setUtenteOperazione(shibIdentitaCodiceFiscale);
			
			repositoryOrdine.updateQuantitaOrdine(ordine);
			
			CovidacTOrdineExt ordineRit = repositoryOrdine.selectOrdiniById(ordineId);
			rit = new ModelOrdineCovidacTOrdineExtMapper().from(ordineRit);
			
		}catch(RESTException e) {
			log.error(methodName, "ERROR", e);
			return e.getResponse();
		}
		catch(Exception e) {
			log.error(methodName, "ERROR", e);
			return ErroreBuilder.from(Status.INTERNAL_SERVER_ERROR).descrizione("Errore imprevisto").exception().getResponseBuilder().build();
		}

		return Response.status(Status.OK).entity(rit).build();
	}
		
	//verifica del prodotto
	private CovidacTProd getProdottoByCodice(String codProd) {
		return repositoryProd.selectByCodiceProdotto(codProd);
	}
	
	
	private ModelOrdine costruisciRisposta(CovidacTOrdine ordine, CovidacDOrdineStato stato, CovidacTProd prodotto) {
		ModelOrdine mOrdine = new ModelOrdine();
		
		mOrdine.setId( ordine.getOrdId());
		mOrdine.setDataConsegna(ordine.getOrdDataConsegna());
		mOrdine.setDataCreazione(ordine.getDataCreazione());
		mOrdine.setDataOrdine(ordine.getOrdData());
		mOrdine.setIdentificativoOrdine(ordine.getOrdIdentificativo());
		
		//Creazione del prodotto
		ModelProdotto mProdotto = new ModelProdotto();
		mProdotto.setCodice(prodotto.getProdCod());
		mProdotto.setDescrizione(prodotto.getProdDesc());
		mProdotto.setNome(prodotto.getProdNome());
		mOrdine.setProdotto(mProdotto);
	
		ModelOrdineStato mStato = new ModelOrdineStato();
		mStato.setCodice(stato.getOrdStatoCod());
		mStato.setDescrizione(stato.getOrdStatoDesc());
		mOrdine.setStato(mStato);
		
		return mOrdine;
	}
	
	private CovidacTOrdine costruiscitOrdine(Payload2 payload, CovidacTProd prodotto, String shibIdentitaCodiceFiscale) {
		CovidacTOrdine ordine = new CovidacTOrdine();
		ordine.setOrdIdentificativo(payload.getIdentificativoOrdine() );
		ordine.setOrdDataConsegnaPrevista(payload.getDataConsegnaPrevista());
		ordine.setOrdData(payload.getDataOrdine());
		ordine.setProdId(prodotto.getProdId());
		ordine.setQuantitaOrdinata(payload.getQuantitaOrdinata());
		ordine.setDataCreazione(new Date());
		ordine.setDataModifica(new Date());
		ordine.setUtenteOperazione(shibIdentitaCodiceFiscale);
		
		return ordine;
	}
	
	private CovidacROrdineRicDet costruisciRRichiestadettaglio(String strRichDet, Integer ordId, String shibIdentitaCodiceFiscale) {
		CovidacROrdineRicDet ridchDet = new CovidacROrdineRicDet();
		
		ridchDet.setOrdId(ordId);
		ridchDet.setRicdetId(Integer.valueOf(strRichDet));
		ridchDet.setUtenteOperazione(shibIdentitaCodiceFiscale);
		ridchDet.setDataCreazione(new Date());
		ridchDet.setDataModifica(new Date());
		
		return ridchDet;
	}
	
	private CovidacROrdineStato costruisciOrdineStato(CovidacDOrdineStato stato, CovidacTOrdine ordineExt, String shibIdentitacodiceFiscale) {
		CovidacROrdineStato rOrdineStato = new CovidacROrdineStato();
		
		/*
		 * ord_id integer NOT NULL,
  		 ord_stato_id integer NOT NULL,
		 * */
		rOrdineStato.setOrdId(ordineExt.getOrdId());
		rOrdineStato.setOrdStatoId(stato.getOrdStatoId());
		rOrdineStato.setDataCreazione(new Date());
		rOrdineStato.setDataModifica(new Date());
		rOrdineStato.setUtenteOperazione(shibIdentitacodiceFiscale);
		rOrdineStato.setValiditaInizio(new Date());
		
		return rOrdineStato;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public Response ordiniOrdineIdDelete(String shibIdentitaCodiceFiscale, String xApplicazioneCodice,
			String xRequestId, Integer ordineId, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		String methodName = "ordiniOrdineIdDelete";
		log.info(methodName, "BEGIN");

		try {
			checkNotNull(shibIdentitaCodiceFiscale, CaConstants.IDENTITA_OBBLIGATORIA);
			checkNotNull(xApplicazioneCodice, CaConstants.APPLICATION_ID_OBBLIGATORIO);

			logAudit(httpHeaders, methodName);

			checkUtenteAutorizzato(shibIdentitaCodiceFiscale, xApplicazioneCodice);

			checkNotNull(ordineId, "ordineId non valorizzato");

			CovidacTOrdine ordineDaAggiornare = repositoryOrdine.selectByPrimaryKey(ordineId);
			if (ordineDaAggiornare == null) {
				return ErroreBuilder.from(Status.BAD_REQUEST).descrizione("Ordine non trovato").exception()
						.getResponseBuilder().build();
			}
			repositoryOrdine.deleteOrdine(ordineDaAggiornare);
			log.info(methodName, "cancellato ordine con id: %s", ordineId);

			CovidacTOrdineExt ordineCancellato = repositoryOrdine.selectOrdineById(ordineId);
			log.info(methodName, "ordine su cui faccio la select: %s", ordineId);

			ModelOrdine modelOrdine = new ModelOrdineCovidacTOrdineExtMapper().from(ordineCancellato);

			return Response.ok().entity(modelOrdine).build();

		} catch (RESTException e) {
			log.error(methodName, "ordiniOrdineIdDelete ERROR RESTException", e);
			return e.getResponse();
		} catch (Exception e) {
			log.error(methodName, "ordiniOrdineIdDelete ERROR RESTException", e);
			return ErroreBuilder.from(Status.INTERNAL_SERVER_ERROR).descrizione("Errore imprevisto").exception()
					.getResponseBuilder().build();
		}

	}

	@Override
	public Response ordiniOrdineIdGet(String shibIdentitaCodiceFiscale, String xApplicazioneCodice, String xRequestId,
			Integer ordineId, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		String methodName = "ordiniOrdineIdGet";
		log.info(methodName, "BEGIN");

		try {
			checkNotNull(shibIdentitaCodiceFiscale, CaConstants.IDENTITA_OBBLIGATORIA);
			checkNotNull(xApplicazioneCodice, CaConstants.APPLICATION_ID_OBBLIGATORIO);

			logAudit(httpHeaders, methodName);

			checkUtenteAutorizzato(shibIdentitaCodiceFiscale, xApplicazioneCodice);

			checkNotNull(ordineId, "ordineId non valorizzato");

			CovidacTOrdineExt ordine = repositoryOrdine.selectDettaglioOrdineById(ordineId);

			ModelOrdineDettaglio modelOrdineDettaglio = new ModelOrdineDettaglio();

			if (ordine != null) {
				modelOrdineDettaglio.setDataConsegna(ordine.getOrdDataConsegna());
				modelOrdineDettaglio.setDataConsegnaPrevista(ordine.getOrdDataConsegnaPrevista());
				modelOrdineDettaglio.setDataCreazione(ordine.getDataCreazione());
				modelOrdineDettaglio.setDataOrdine(ordine.getOrdData());
				modelOrdineDettaglio.setId(ordine.getOrdId());
				modelOrdineDettaglio.setIdentificativoOrdine(ordine.getOrdIdentificativo());
				modelOrdineDettaglio.setQuantitaConsegnata(ordine.getQuantitaConsegnata());
				modelOrdineDettaglio.setQuantitaOrdinata(ordine.getQuantitaOrdinata());

				ModelProdotto modelProdotto = new ModelProdotto();
				modelProdotto.setCodice(ordine.getProdCod());
				modelProdotto.setDescrizione(ordine.getProdDesc());
				modelProdotto.setNome(ordine.getProdNome());
				modelOrdineDettaglio.setProdotto(modelProdotto);

				ModelOrdineStato modelOrdineStato = new ModelOrdineStato();
				modelOrdineStato.setCodice(ordine.getOrdStatoCod());
				modelOrdineStato.setDescrizione(ordine.getOrdStatoDesc());
				modelOrdineDettaglio.setStato(modelOrdineStato);

				List<CovidacROrdineStatoExt> elencoStati = repositoryOrdine.selectCronologiaOrdineById(ordineId);
				Iterator<CovidacROrdineStatoExt> iterator = elencoStati.iterator();

				List<ModelOrdineDettaglioCambioStato> cronologiaStati = new ArrayList<ModelOrdineDettaglioCambioStato>();

				while (iterator.hasNext()) {
					CovidacROrdineStatoExt next = iterator.next();

					ModelOrdineDettaglioCambioStato modelOrdineDettaglioCambioStato = new ModelOrdineDettaglioCambioStato();
					modelOrdineDettaglioCambioStato.setId(next.getOrdrstatoId());
					modelOrdineDettaglioCambioStato.setOrdineId(next.getOrdId());
					modelOrdineDettaglioCambioStato.setData(next.getDataCreazione());
					modelOrdineDettaglioCambioStato.setDataConsegna(next.getDataConsegna());
					modelOrdineDettaglioCambioStato.setQuantitaConsegnata(next.getQuantitaConsegnata());
					ModelOrdineStato modelOrdineStatoCronologia = new ModelOrdineStato();
					modelOrdineStatoCronologia.setCodice(next.getOrdStatoCod());
					modelOrdineStatoCronologia.setDescrizione(next.getOrdStatoDesc());
					modelOrdineDettaglioCambioStato.setStato(modelOrdineStatoCronologia);

					cronologiaStati.add(modelOrdineDettaglioCambioStato);
				}

				modelOrdineDettaglio.setCronologia(cronologiaStati);
			}
			return Response.ok().entity(modelOrdineDettaglio).build();

		} catch (RESTException e) {
			log.error(methodName, "ordiniOrdineIdGet ERROR RESTException", e);
			return e.getResponse();
		} catch (Exception e) {
			log.error(methodName, "ordiniOrdineIdGet ERROR RESTException", e);
			return ErroreBuilder.from(Status.INTERNAL_SERVER_ERROR).descrizione("Errore imprevisto").exception()
					.getResponseBuilder().build();
		}

	}

}
