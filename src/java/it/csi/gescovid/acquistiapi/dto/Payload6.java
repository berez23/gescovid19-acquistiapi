package it.csi.gescovid.acquistiapi.dto;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class Payload6   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Integer quantita = null;
  private Date dataConsegna = null;
  private Integer quantitaDistribuibile = null;
  private String ente = null;
  private String struttura = null;
  private String prodotto = null;

  /**
   * Il numero di pezzi distribuiti
   **/
  

  @JsonProperty("quantita") 
 
  public Integer getQuantita() {
    return quantita;
  }
  public void setQuantita(Integer quantita) {
    this.quantita = quantita;
  }

  /**
   * La data di consegna prevista
   **/
  

  @JsonProperty("data_consegna") 
 
  public Date getDataConsegna() {
    return dataConsegna;
  }
  public void setDataConsegna(Date dataConsegna) {
    this.dataConsegna = dataConsegna;
  }

  /**
   * La quantita distribuibile inserita dall&#39;utente
   **/
  

  @JsonProperty("quantita_distribuibile") 
 
  public Integer getQuantitaDistribuibile() {
    return quantitaDistribuibile;
  }
  public void setQuantitaDistribuibile(Integer quantitaDistribuibile) {
    this.quantitaDistribuibile = quantitaDistribuibile;
  }

  /**
   * L&#39;id dell&#39;ente
   **/
  

  @JsonProperty("ente") 
 
  public String getEnte() {
    return ente;
  }
  public void setEnte(String ente) {
    this.ente = ente;
  }

  /**
   * L&#39;id della struttura
   **/
  

  @JsonProperty("struttura") 
 
  public String getStruttura() {
    return struttura;
  }
  public void setStruttura(String struttura) {
    this.struttura = struttura;
  }

  /**
   * Il codice del prodotto
   **/
  

  @JsonProperty("prodotto") 
 
  public String getProdotto() {
    return prodotto;
  }
  public void setProdotto(String prodotto) {
    this.prodotto = prodotto;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Payload6 payload6 = (Payload6) o;
    return Objects.equals(quantita, payload6.quantita) &&
        Objects.equals(dataConsegna, payload6.dataConsegna) &&
        Objects.equals(quantitaDistribuibile, payload6.quantitaDistribuibile) &&
        Objects.equals(ente, payload6.ente) &&
        Objects.equals(struttura, payload6.struttura) &&
        Objects.equals(prodotto, payload6.prodotto);
  }

  @Override
  public int hashCode() {
    return Objects.hash(quantita, dataConsegna, quantitaDistribuibile, ente, struttura, prodotto);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Payload6 {\n");
    
    sb.append("    quantita: ").append(toIndentedString(quantita)).append("\n");
    sb.append("    dataConsegna: ").append(toIndentedString(dataConsegna)).append("\n");
    sb.append("    quantitaDistribuibile: ").append(toIndentedString(quantitaDistribuibile)).append("\n");
    sb.append("    ente: ").append(toIndentedString(ente)).append("\n");
    sb.append("    struttura: ").append(toIndentedString(struttura)).append("\n");
    sb.append("    prodotto: ").append(toIndentedString(prodotto)).append("\n");
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

