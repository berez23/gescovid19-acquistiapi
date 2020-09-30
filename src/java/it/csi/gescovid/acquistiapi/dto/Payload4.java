package it.csi.gescovid.acquistiapi.dto;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class Payload4   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Integer quantitaOrdinata = null;

  /**
   * il nuovo numero di pezzi ordinati dall&#39;utente
   **/
  

  @JsonProperty("quantita_ordinata") 
 
  public Integer getQuantitaOrdinata() {
    return quantitaOrdinata;
  }
  public void setQuantitaOrdinata(Integer quantitaOrdinata) {
    this.quantitaOrdinata = quantitaOrdinata;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Payload4 payload4 = (Payload4) o;
    return Objects.equals(quantitaOrdinata, payload4.quantitaOrdinata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(quantitaOrdinata);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Payload4 {\n");
    
    sb.append("    quantitaOrdinata: ").append(toIndentedString(quantitaOrdinata)).append("\n");
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

