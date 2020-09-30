/**********************************************
 * CSI PIEMONTE 
 **********************************************/
package it.csi.gescovid.acquistiapi.business.be;

import it.csi.gescovid.acquistiapi.dto.*;


import it.csi.gescovid.acquistiapi.dto.Errore;
import it.csi.gescovid.acquistiapi.dto.ModelRichiesta;
import it.csi.gescovid.acquistiapi.dto.Payload;

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

@Path("/richieste")




public interface RichiesteApi  {
   
    @GET
    
    
    @Produces({ "application/json" })

    public Response richiesteGet(@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,@HeaderParam("X-Applicazione-Codice") String xApplicazioneCodice,@HeaderParam("X-Request-Id") String xRequestId, @QueryParam("ente") String ente,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @POST
    
    
    @Produces({ "application/json" })

    public Response richiestePost(@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,@HeaderParam("X-Applicazione-Codice") String xApplicazioneCodice,@HeaderParam("X-Request-Id") String xRequestId, Payload payload,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
}
