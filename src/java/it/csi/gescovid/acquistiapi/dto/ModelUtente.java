package it.csi.gescovid.acquistiapi.dto;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import io.swagger.annotations.ApiModel;
import it.csi.gescovid.acquistiapi.dto.ModelEnte;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class ModelUtente   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String cf = null;
  private ModelEnte ente = null;

  /**
   * Il codice fiscale dell&#39;utente
   **/
  

  @JsonProperty("cf") 
 
  public String getCf() {
    return cf;
  }
  public void setCf(String cf) {
    this.cf = cf;
  }

  /**
   **/
  

  @JsonProperty("ente") 
 
  public ModelEnte getEnte() {
    return ente;
  }
  public void setEnte(ModelEnte ente) {
    this.ente = ente;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelUtente modelUtente = (ModelUtente) o;
    return Objects.equals(cf, modelUtente.cf) &&
        Objects.equals(ente, modelUtente.ente);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cf, ente);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelUtente {\n");
    
    sb.append("    cf: ").append(toIndentedString(cf)).append("\n");
    sb.append("    ente: ").append(toIndentedString(ente)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

