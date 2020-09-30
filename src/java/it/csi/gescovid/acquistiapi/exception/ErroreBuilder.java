package it.csi.gescovid.acquistiapi.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;


import it.csi.gescovid.acquistiapi.dto.Errore;


public class ErroreBuilder {
	private Errore errore;
	private int status;

	public static ErroreBuilder from(Status s) {
		return new ErroreBuilder()
				.status(s.getStatusCode())
				.code(s.name());
	}

	public static ErroreBuilder from(int status, String code) {
		return new ErroreBuilder()
				.status(status)
				.code(code);
	}

	private ErroreBuilder() {
		this.errore = new Errore();
	}

	public ErroreBuilder status(int status) {
		errore.setCodice("" + status);
		this.status = status;
		return this;
	}

	public ErroreBuilder code(String code) {
		errore.setCodice(code);
		return this;
	}

	public ErroreBuilder descrizione(String title) {
		errore.setDescrizione(title);
		return this;
	}



	public Errore build() {
		return this.errore;
	}

	public RESTException exception() {
		return new RESTException(this.status,  MediaType.APPLICATION_JSON_TYPE, this.errore, errore.toString());
	}
	
	public RESTException exception(String message) {
		return new RESTException(this.status,  MediaType.APPLICATION_JSON_TYPE, this.errore, message);
	}

}
