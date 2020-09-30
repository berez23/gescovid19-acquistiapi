package it.csi.gescovid.acquistiapi.business.be.impl;

import java.util.Date;
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

import it.csi.gescovid.acquistiapi.business.be.MagazzinoApi;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacRMagazProd;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacTMagazzino;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacTProd;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.extended.CovidacTMagazExt;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.extended.CovidacRMagazProdMapper;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.extended.CovidacTMagazzinoMapper;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.extended.CovidacTProdMapper;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.extended.MagazzinoServiceMapper;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.generated.BaseCovidacRMagazProdMapper;
import it.csi.gescovid.acquistiapi.business.mapping.ModelMagazzinoDettaglioCovidacTMagazExtMapper;
import it.csi.gescovid.acquistiapi.business.mapping.ModelMagazzinoDettaglioProdottoCovidacTMagazExtMapper;
import it.csi.gescovid.acquistiapi.dto.ModelMagazzino;
import it.csi.gescovid.acquistiapi.dto.ModelMagazzinoDettaglio;
import it.csi.gescovid.acquistiapi.dto.ModelMagazzinoDettaglioProdotto;
import it.csi.gescovid.acquistiapi.dto.Payload4;
import it.csi.gescovid.acquistiapi.dto.Payload5;
import it.csi.gescovid.acquistiapi.exception.ErroreBuilder;
import it.csi.gescovid.acquistiapi.exception.RESTException;
import it.csi.gescovid.acquistiapi.util.LogUtil;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class MagazzinoApiServiceImpl extends RESTBaseService implements MagazzinoApi {
	
	LogUtil log = new LogUtil(MagazzinoApiServiceImpl.class);
	@Autowired
	MagazzinoServiceMapper repositoryMagazzinoMapper;
	
	@Autowired
	CovidacRMagazProdMapper repositoryRMagazProd;
	
	@Autowired
	CovidacTProdMapper  repositoryProd;
		
	
	@Override
	public Response magazzinoGet(String shibIdentitaCodiceFiscale, String xApplicazioneCodice, String xRequestId,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		String methodName = "magazzinoGet";
		log.debug(methodName, "BEGIN");
		ModelMagazzinoDettaglio modelMagazzino = null;
		
		try {
			checkNotNull(shibIdentitaCodiceFiscale, "Identità obbligatoria");
			checkNotNull(xApplicazioneCodice, "Application id obbligatoria");
			logAudit(httpHeaders, methodName);
			checkUtenteAutorizzato(shibIdentitaCodiceFiscale, xApplicazioneCodice);
			
			//tiro su il magazzino
			List<CovidacTMagazzino> magazzini = repositoryMagazzinoMapper.selectMagazzinoDataCancellazioneNull();
			checkNotNull(magazzini, "Magazzino non presente su database");
			checkCondition(magazzini.size() > 0, "Magazzino non presente su database");
			
			CovidacTMagazzino magazzino = magazzini.get(0);
			
			List<CovidacTMagazExt> lista = repositoryMagazzinoMapper.selectDisponibilitaMagazzino();
			if(lista == null) {
				return ErroreBuilder.from(Status.NOT_FOUND).descrizione("Nessun magazzino trovato").exception().getResponseBuilder().build();
			}
			if(lista.size() == 0) {
				return ErroreBuilder.from(Status.NOT_FOUND).descrizione("Nessun magazzino trovato").exception().getResponseBuilder().build();
			}
			modelMagazzino = new ModelMagazzinoDettaglioCovidacTMagazExtMapper().from(magazzino);
			modelMagazzino.setProdotti(new ModelMagazzinoDettaglioProdottoCovidacTMagazExtMapper().fromList(lista));
			
		} catch(RESTException e) {
			log.error(methodName,"ERROR", e);
			return e.getResponse();
		}
		catch(Exception e) {
			log.error(methodName,"ERROR", e);
			return ErroreBuilder.from(Status.INTERNAL_SERVER_ERROR).descrizione("Errore imprevisto").exception().getResponseBuilder().build();
		}
		return Response.ok().entity(modelMagazzino).build();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public Response magazzinoProdottiProdottoCodicePut(String shibIdentitaCodiceFiscale, String xApplicazioneCodice, String xRequestId,
			String prodottoCodice, Payload5 payload, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = "magazzinoProdottiProdottoCodicePut";
		log.debug(methodName, "BEGIN");
		ModelMagazzinoDettaglioProdotto dettProd = null;
		try {
			Date dataFine = new Date();
			checkNotNull(shibIdentitaCodiceFiscale, "Identità obbligatoria");
			checkNotNull(xApplicazioneCodice, "Application id obbligatoria");
			logAudit(httpHeaders, methodName);
			checkUtenteAutorizzato(shibIdentitaCodiceFiscale, xApplicazioneCodice);
			checkNotNull(payload, "payload non valorizzato");
			
			//Tiro su il magazzino
			List<CovidacTMagazzino> magazzini = repositoryMagazzinoMapper.selectMagazzinoDataCancellazioneNull();
			checkNotNull(magazzini, "Magazzino non presente su database");
			checkCondition(magazzini.size() > 0, "Magazzino non presente su database");
			
			CovidacTMagazzino magazzino = magazzini.get(0);
			
			CovidacTProd covidadTProdNew = getProdottoByCodice(prodottoCodice);
			checkNotNull(covidadTProdNew, "Codice prodotto non valido");
			
			
			CovidacRMagazProd rmagazProd = repositoryMagazzinoMapper.selectRMagazFromCodiceProdotto(prodottoCodice);
			//secondo step  faccio update inserendo la data fine
			if(rmagazProd != null) {
				repositoryRMagazProd.updateDataValiditaFineByPrimaryKey(rmagazProd);
			}
			
			//terzo step inserisco un nuovo record
			CovidacRMagazProd rmagazNew = costruisciRecorddaInserire(magazzino, covidadTProdNew.getProdId(), payload, shibIdentitaCodiceFiscale);
			
			//repositoryRMagazProd.insert(rmagazNew);
			repositoryRMagazProd.insertValiditaInizioNow(rmagazNew);
			
			//quarto step ritiro su il record non lo tira su.. devo usarlo per ricostruirmi l'oggetto			
			List<CovidacTMagazExt> lista = repositoryMagazzinoMapper.selectDisponibilitaMagazzinoPerProdId(rmagazNew.getProdId());
			dettProd = new ModelMagazzinoDettaglioProdottoCovidacTMagazExtMapper().fromList(lista).get(0);	
			
		} catch(RESTException e) {
			log.error(methodName,"ERROR", e);
			return e.getResponse();
		}
		catch(Exception e) {
			log.error(methodName,"ERROR", e);
			return ErroreBuilder.from(Status.INTERNAL_SERVER_ERROR).descrizione("Errore imprevisto").exception().getResponseBuilder().build();
		}
		return Response.ok().entity(dettProd).build();
	}
	
	
	
	//verifica del prodotto
		private CovidacTProd getProdottoByCodice(String codProd) {
			return repositoryProd.selectByCodiceProdotto(codProd);
		}
	
	CovidacRMagazProd costruisciRecorddaInserire(CovidacTMagazzino rMagazProd, Integer prodId, Payload5 payload, String shiIdentitaCodiceFiscale) {
		CovidacRMagazProd rMagNew = new CovidacRMagazProd();
		
		rMagNew.setMagazId(rMagazProd.getMagazId());
		rMagNew.setProdId(prodId);
		rMagNew.setQuantitaDisponibile(payload.getQuantitaDisponibile());
		rMagNew.setQuantitaDistribuita(payload.getQuantitaDistribuita());
		rMagNew.setUtenteOperazione(shiIdentitaCodiceFiscale);
		
		return rMagNew;
	}
}
