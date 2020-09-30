/**********************************************
 * CSI PIEMONTE 
 **********************************************/
package it.csi.gescovid.acquistiapi.business.be;

import it.csi.gescovid.acquistiapi.dto.*;


import it.csi.gescovid.acquistiapi.dto.Errore;
import it.csi.gescovid.acquistiapi.dto.ModelRichiestaDettaglioStandalone;
import it.csi.gescovid.acquistiapi.dto.Payload1;

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

@Path("/richieste-dettagli")




public interface RichiesteDettagliApi  {
   
    @PUT
    @Path("/{dettaglio_id}")
    
    @Produces({ "application/json" })

    public Response richiesteDettagliDettaglioIdPut(@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,@HeaderParam("X-Applicazione-Codice") String xApplicazioneCodice,@HeaderParam("X-Request-Id") String xRequestId, @PathParam("dettaglio_id") String dettaglioId, Payload1 payload,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    
    
    @Produces({ "application/json" })

    public Response richiesteDettagliGet(@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,@HeaderParam("X-Applicazione-Codice") String xApplicazioneCodice,@HeaderParam("X-Request-Id") String xRequestId, @QueryParam("ente") String ente, @QueryParam("stato") String stato, @QueryParam("ordinati") Boolean ordinati,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
}
