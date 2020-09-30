package it.csi.gescovid.acquistiapi.business.be.impl;

import java.util.Date;
import java.util.List;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.soap.providers.com.Log;
import org.springframework.beans.factory.annotation.Autowired;

import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacLogAudit;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.Profilo;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.extended.CovidacLogAuditMapper;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.extended.UtentiMapper;
import it.csi.gescovid.acquistiapi.dto.util.UtilitiStream;
import it.csi.gescovid.acquistiapi.exception.ErroreBuilder;
import it.csi.gescovid.acquistiapi.exception.RESTException;
import it.csi.gescovid.acquistiapi.util.SpringSupportedResource;

public class RESTBaseService extends SpringSupportedResource {
	
	@Autowired
	UtentiMapper utentiMapper;
	
	@Autowired
	CovidacLogAuditMapper repositoryLogAudit;
	
	protected void checkUtenteAutorizzato(String shibIdentitaCodiceFiscale, String xApplicazioneCodice) {
		List<Profilo> elencoProfiliDB = utentiMapper.selectElencoProfili(shibIdentitaCodiceFiscale);
		boolean isPresenteProfilo = UtilitiStream.verificaProfili(elencoProfiliDB, xApplicazioneCodice);
		checkCondition(isPresenteProfilo, ErroreBuilder.from(Status.UNAUTHORIZED).descrizione("Utente non autorizzato").exception());
	}
	
	
	protected void logAudit(HttpHeaders headers, String operazione) {
		String shibIdentitaCf = getHeaderParam(headers, "Shib-Identita-CodiceFiscale");
		String xApplicationcodice = getHeaderParam(headers, "X-Applicazione-Codice");
		String xRequestId = getHeaderParam(headers, "X-Request-Id");
		
		CovidacLogAudit logAudit = new CovidacLogAudit();
		logAudit.setDataOra(new Date());
		logAudit.setIdApp(xApplicationcodice);
		logAudit.setOggOper(operazione);
		logAudit.setIpAddress("");
		logAudit.setKeyOper(xRequestId);
		logAudit.setUtente(shibIdentitaCf);
		logAudit.setOperazione(operazione);
		
		repositoryLogAudit.insert(logAudit);
	}
	
	protected String getHeaderParam(HttpHeaders httpHeaders, String headerParam) throws RESTException{
		List<String> values = httpHeaders.getRequestHeader(headerParam);
		if (values == null) {
			throw ErroreBuilder.from(Status.BAD_REQUEST).descrizione("Parametro mancante: " + headerParam).exception();
		}else if(values.size() == 0) {
			throw ErroreBuilder.from(Status.BAD_REQUEST).descrizione("Parametro mancante: " + headerParam).exception();
		}
		return values.get(0);
	}
	
	/**
	 * Controlla che il parametro obj sia valorizzato;
	 * diversamente solleva una {@link RESTException} con Status.BAD_REQUEST e con il messaggio passato come parametro.
	 * 
	 * @param obj
	 * @param message
	 */
	protected void checkNotNull(Object obj, String message) {
		checkNotNull(obj, ErroreBuilder.from(Status.BAD_REQUEST).descrizione(message).exception());
	}
	
	/**
	 * Controlla che il parametro obj sia valorizzato;
	 * diversamente solleva l'eccezione passata come parametro.
	 * 
	 * @param obj
	 * @param re
	 */
	protected void checkNotNull(Object obj, RESTException re) {
		checkCondition(obj!=null, re);
	}
	
	/**
	 * Controlla che il parametro str sia valorizzato;
	 * diversamente solleva una {@link RESTException} con Status.BAD_REQUEST e con il messaggio passato come parametro.
	 * 
	 * @param obj
	 * @param re
	 */
	protected void checkNotBlank(String str, String message) {
		checkNotBlank(str, ErroreBuilder.from(Status.BAD_REQUEST).descrizione(message).exception());
	}
	
	
	/**
	 * Controlla che il parametro str sia valorizzato;
	 * diversamente solleva l'eccezione passata come parametro.
	 * 
	 * @param obj
	 * @param re
	 */
	protected void checkNotBlank(String str, RESTException re) {
		checkCondition(StringUtils.isNotBlank(str), re);
	}
	
	/**
	 * Controlla che la condizione isOk sia uguale a <code>true</code>;
	 * diversamente solleva l'eccezione passata come parametro.
	 * 
	 * @param isOk
	 * @param re
	 */
	protected void checkCondition(boolean isOk, RESTException re) {
		if(!isOk) {
			throw re;
		}
	}
	

	/**
	 * Controlla che la condizione isOk sia uguale a <code>true</code>;
	 * diversamente solleva una {@link RESTException} con Status.BAD_REQUEST e con il messaggio passato come parametro.
	 * 
	 * @param isOk
	 * @param re
	 */
	protected void checkCondition(boolean isOk, String message) {
		checkCondition(isOk, ErroreBuilder.from(Status.BAD_REQUEST).descrizione(message).exception());
	}
}
