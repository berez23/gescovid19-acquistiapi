package it.csi.gescovid.acquistiapi.dto;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import io.swagger.annotations.ApiModel;
import it.csi.gescovid.acquistiapi.dto.ModelEnte;
import it.csi.gescovid.acquistiapi.dto.ModelStruttura;
import java.math.BigDecimal;
import java.util.Date;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class ModelDistribuzionePercentuale   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String id = null;
  private BigDecimal percentualeDistribuzione = null;
  private Date dataModifica = null;
  private ModelEnte ente = null;
  private ModelStruttura struttura = null;

  /**
   * Un codice univoco
   **/
  

  @JsonProperty("id") 
 
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  /**
   * la percentuale di distribuzione sull&#39;asr
   **/
  

  @JsonProperty("percentuale_distribuzione") 
 
  public BigDecimal getPercentualeDistribuzione() {
    return percentualeDistribuzione;
  }
  public void setPercentualeDistribuzione(BigDecimal percentualeDistribuzione) {
    this.percentualeDistribuzione = percentualeDistribuzione;
  }

  /**
   * La data dell&#39;ultima modifica
   **/
  

  @JsonProperty("data_modifica") 
 
  public Date getDataModifica() {
    return dataModifica;
  }
  public void setDataModifica(Date dataModifica) {
    this.dataModifica = dataModifica;
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

  /**
   **/
  

  @JsonProperty("struttura") 
 
  public ModelStruttura getStruttura() {
    return struttura;
  }
  public void setStruttura(ModelStruttura struttura) {
    this.struttura = struttura;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelDistribuzionePercentuale modelDistribuzionePercentuale = (ModelDistribuzionePercentuale) o;
    return Objects.equals(id, modelDistribuzionePercentuale.id) &&
        Objects.equals(percentualeDistribuzione, modelDistribuzionePercentuale.percentualeDistribuzione) &&
        Objects.equals(dataModifica, modelDistribuzionePercentuale.dataModifica) &&
        Objects.equals(ente, modelDistribuzionePercentuale.ente) &&
        Objects.equals(struttura, modelDistribuzionePercentuale.struttura);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, percentualeDistribuzione, dataModifica, ente, struttura);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelDistribuzionePercentuale {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    percentualeDistribuzione: ").append(toIndentedString(percentualeDistribuzione)).append("\n");
    sb.append("    dataModifica: ").append(toIndentedString(dataModifica)).append("\n");
    sb.append("    ente: ").append(toIndentedString(ente)).append("\n");
    sb.append("    struttura: ").append(toIndentedString(struttura)).append("\n");
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

