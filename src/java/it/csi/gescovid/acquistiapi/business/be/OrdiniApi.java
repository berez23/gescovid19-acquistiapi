/**********************************************
 * CSI PIEMONTE 
 **********************************************/
package it.csi.gescovid.acquistiapi.business.be;

import it.csi.gescovid.acquistiapi.dto.*;


import it.csi.gescovid.acquistiapi.dto.Errore;
import it.csi.gescovid.acquistiapi.dto.ModelOrdine;
import it.csi.gescovid.acquistiapi.dto.ModelOrdineDettaglio;
import it.csi.gescovid.acquistiapi.dto.Payload2;
import it.csi.gescovid.acquistiapi.dto.Payload3;
import it.csi.gescovid.acquistiapi.dto.Payload4;

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

@Path("/ordini")




public interface OrdiniApi  {
   
    @GET
    
    
    @Produces({ "application/json" })

    public Response ordiniGet(@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,@HeaderParam("X-Applicazione-Codice") String xApplicazioneCodice,@HeaderParam("X-Request-Id") String xRequestId, @QueryParam("stato") String stato,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @DELETE
    @Path("/{ordine_id}")
    
    @Produces({ "application/json" })

    public Response ordiniOrdineIdDelete(@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,@HeaderParam("X-Applicazione-Codice") String xApplicazioneCodice,@HeaderParam("X-Request-Id") String xRequestId, @PathParam("ordine_id") Integer ordineId,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{ordine_id}")
    
    @Produces({ "application/json" })

    public Response ordiniOrdineIdGet(@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,@HeaderParam("X-Applicazione-Codice") String xApplicazioneCodice,@HeaderParam("X-Request-Id") String xRequestId, @PathParam("ordine_id") Integer ordineId,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @PUT
    @Path("/{ordine_id}")
    
    @Produces({ "application/json" })

    public Response ordiniOrdineIdPut(@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,@HeaderParam("X-Applicazione-Codice") String xApplicazioneCodice,@HeaderParam("X-Request-Id") String xRequestId, @PathParam("ordine_id") Integer ordineId, Payload3 payload,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @PUT
    @Path("/{ordine_id}/quantita-ordinata")
    
    @Produces({ "application/json" })

    public Response ordiniOrdineIdQuantitaOrdinataPut(@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,@HeaderParam("X-Applicazione-Codice") String xApplicazioneCodice,@HeaderParam("X-Request-Id") String xRequestId, @PathParam("ordine_id") Integer ordineId, Payload4 payload,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @POST
    
    
    @Produces({ "application/json" })

    public Response ordiniPost(@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,@HeaderParam("X-Applicazione-Codice") String xApplicazioneCodice,@HeaderParam("X-Request-Id") String xRequestId, Payload2 payload,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
}
