package it.csi.gescovid.acquistiapi.dto;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import java.math.BigDecimal;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class ModelAnaliticaEnti   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private BigDecimal totaleApprovatoSettimana = null;
  private BigDecimal totaleDistribuito = null;

  /**
   * Il totale approvato nella settimana corrente per l&#39;ente
   **/
  

  @JsonProperty("totale_approvato_settimana") 
 
  public BigDecimal getTotaleApprovatoSettimana() {
    return totaleApprovatoSettimana;
  }
  public void setTotaleApprovatoSettimana(BigDecimal totaleApprovatoSettimana) {
    this.totaleApprovatoSettimana = totaleApprovatoSettimana;
  }

  /**
   * Il totale distribuito all&#39;ente
   **/
  

  @JsonProperty("totale_distribuito") 
 
  public BigDecimal getTotaleDistribuito() {
    return totaleDistribuito;
  }
  public void setTotaleDistribuito(BigDecimal totaleDistribuito) {
    this.totaleDistribuito = totaleDistribuito;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelAnaliticaEnti modelAnaliticaEnti = (ModelAnaliticaEnti) o;
    return Objects.equals(totaleApprovatoSettimana, modelAnaliticaEnti.totaleApprovatoSettimana) &&
        Objects.equals(totaleDistribuito, modelAnaliticaEnti.totaleDistribuito);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totaleApprovatoSettimana, totaleDistribuito);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelAnaliticaEnti {\n");
    
    sb.append("    totaleApprovatoSettimana: ").append(toIndentedString(totaleApprovatoSettimana)).append("\n");
    sb.append("    totaleDistribuito: ").append(toIndentedString(totaleDistribuito)).append("\n");
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

