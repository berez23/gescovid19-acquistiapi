/**********************************************
 * CSI PIEMONTE 
 **********************************************/
package it.csi.gescovid.acquistiapi.business.be;

import java.util.List;
import java.util.Map;

import java.io.InputStream;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.HttpHeaders;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.SecurityContext;

import it.csi.gescovid.acquistiapi.dto.*;

import javax.ws.rs.*;
import javax.validation.constraints.*;

@Path("/magazzini")




public interface MagazziniApi  {
   
    @GET
    @Path("/{magazzino_id}")
    
    

    public Response magazziniMagazzinoIdGet(@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale, @PathParam("magazzino_id") String magazzinoId,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
}
