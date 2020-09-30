package it.csi.gescovid.acquistiapi.dto;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class Payload5   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Integer quantitaDisponibile = null;
  private Integer quantitaDistribuita = null;

  /**
   * La quantita disponibile in magazzino
   **/
  

  @JsonProperty("quantita_disponibile") 
 
  public Integer getQuantitaDisponibile() {
    return quantitaDisponibile;
  }
  public void setQuantitaDisponibile(Integer quantitaDisponibile) {
    this.quantitaDisponibile = quantitaDisponibile;
  }

  /**
   * La quantit� distributa agli enti
   **/
  

  @JsonProperty("quantita_distribuita") 
 
  public Integer getQuantitaDistribuita() {
    return quantitaDistribuita;
  }
  public void setQuantitaDistribuita(Integer quantitaDistribuita) {
    this.quantitaDistribuita = quantitaDistribuita;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Payload5 payload5 = (Payload5) o;
    return Objects.equals(quantitaDisponibile, payload5.quantitaDisponibile) &&
        Objects.equals(quantitaDistribuita, payload5.quantitaDistribuita);
  }

  @Override
  public int hashCode() {
    return Objects.hash(quantitaDisponibile, quantitaDistribuita);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Payload5 {\n");
    
    sb.append("    quantitaDisponibile: ").append(toIndentedString(quantitaDisponibile)).append("\n");
    sb.append("    quantitaDistribuita: ").append(toIndentedString(quantitaDistribuita)).append("\n");
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

