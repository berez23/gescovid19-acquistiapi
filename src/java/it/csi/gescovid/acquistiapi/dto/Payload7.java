package it.csi.gescovid.acquistiapi.dto;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import java.math.BigDecimal;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class Payload7   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private BigDecimal percentualeDistribuzione = null;

  /**
   * la nuova percentuale di distribuzione
   **/
  

  @JsonProperty("percentuale_distribuzione") 
 
  public BigDecimal getPercentualeDistribuzione() {
    return percentualeDistribuzione;
  }
  public void setPercentualeDistribuzione(BigDecimal percentualeDistribuzione) {
    this.percentualeDistribuzione = percentualeDistribuzione;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Payload7 payload7 = (Payload7) o;
    return Objects.equals(percentualeDistribuzione, payload7.percentualeDistribuzione);
  }

  @Override
  public int hashCode() {
    return Objects.hash(percentualeDistribuzione);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Payload7 {\n");
    
    sb.append("    percentualeDistribuzione: ").append(toIndentedString(percentualeDistribuzione)).append("\n");
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

