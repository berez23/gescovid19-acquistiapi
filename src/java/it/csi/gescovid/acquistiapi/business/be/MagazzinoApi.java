/**********************************************
 * CSI PIEMONTE 
 **********************************************/
package it.csi.gescovid.acquistiapi.business.be;

import it.csi.gescovid.acquistiapi.dto.*;


import it.csi.gescovid.acquistiapi.dto.Errore;
import it.csi.gescovid.acquistiapi.dto.ModelMagazzinoDettaglio;
import it.csi.gescovid.acquistiapi.dto.ModelMagazzinoDettaglioProdotto;
import it.csi.gescovid.acquistiapi.dto.Payload5;

import java.util.List;
import java.util.Map;

import java.io.InputStream;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.HttpHeaders;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;
import javax.validation.constraints.*;

@Path("/magazzino")




public interface MagazzinoApi  {
   
    @GET
    
    
    @Produces({ "application/json" })

    public Response magazzinoGet(@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,@HeaderParam("X-Applicazione-Codice") String xApplicazioneCodice,@HeaderParam("X-Request-Id") String xRequestId,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @PUT
    @Path("/prodotti/{prodotto_codice}")
    
    @Produces({ "application/json" })

    public Response magazzinoProdottiProdottoCodicePut(@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,@HeaderParam("X-Applicazione-Codice") String xApplicazioneCodice,@HeaderParam("X-Request-Id") String xRequestId, @PathParam("prodotto_codice") String prodottoCodice, Payload5 payload,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
}
