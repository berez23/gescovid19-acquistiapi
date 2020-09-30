package it.csi.gescovid.acquistiapi.dto;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import io.swagger.annotations.ApiModel;
import it.csi.gescovid.acquistiapi.dto.ModelOrdineStato;
import java.util.Date;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class ModelOrdineDettaglioCambioStato   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Integer id = null;
  private Integer ordineId = null;
  private Date data = null;
  private Date dataConsegna = null;
  private Integer quantitaConsegnata = null;
  private ModelOrdineStato stato = null;

  /**
   * Un codice univoco
   **/
  

  @JsonProperty("id") 
 
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * Id dell&#39;ordine
   **/
  

  @JsonProperty("ordine_id") 
 
  public Integer getOrdineId() {
    return ordineId;
  }
  public void setOrdineId(Integer ordineId) {
    this.ordineId = ordineId;
  }

  /**
   * La datetime in cui � avvenuto il cambio di stato
   **/
  

  @JsonProperty("data") 
 
  public Date getData() {
    return data;
  }
  public void setData(Date data) {
    this.data = data;
  }

  /**
   * La datetime in cui � avvenuta la consegna
   **/
  

  @JsonProperty("data_consegna") 
 
  public Date getDataConsegna() {
    return dataConsegna;
  }
  public void setDataConsegna(Date dataConsegna) {
    this.dataConsegna = dataConsegna;
  }

  /**
   * La quantit� di merce consegnata
   **/
  

  @JsonProperty("quantita_consegnata") 
 
  public Integer getQuantitaConsegnata() {
    return quantitaConsegnata;
  }
  public void setQuantitaConsegnata(Integer quantitaConsegnata) {
    this.quantitaConsegnata = quantitaConsegnata;
  }

  /**
   **/
  

  @JsonProperty("stato") 
 
  public ModelOrdineStato getStato() {
    return stato;
  }
  public void setStato(ModelOrdineStato stato) {
    this.stato = stato;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelOrdineDettaglioCambioStato modelOrdineDettaglioCambioStato = (ModelOrdineDettaglioCambioStato) o;
    return Objects.equals(id, modelOrdineDettaglioCambioStato.id) &&
        Objects.equals(ordineId, modelOrdineDettaglioCambioStato.ordineId) &&
        Objects.equals(data, modelOrdineDettaglioCambioStato.data) &&
        Objects.equals(dataConsegna, modelOrdineDettaglioCambioStato.dataConsegna) &&
        Objects.equals(quantitaConsegnata, modelOrdineDettaglioCambioStato.quantitaConsegnata) &&
        Objects.equals(stato, modelOrdineDettaglioCambioStato.stato);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, ordineId, data, dataConsegna, quantitaConsegnata, stato);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelOrdineDettaglioCambioStato {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    ordineId: ").append(toIndentedString(ordineId)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    dataConsegna: ").append(toIndentedString(dataConsegna)).append("\n");
    sb.append("    quantitaConsegnata: ").append(toIndentedString(quantitaConsegnata)).append("\n");
    sb.append("    stato: ").append(toIndentedString(stato)).append("\n");
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

