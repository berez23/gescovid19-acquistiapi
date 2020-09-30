package it.csi.gescovid.acquistiapi.dto;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class Payload3   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String identificativoOrdine = null;
  private Integer quantitaOrdinata = null;
  private Date dataConsegnaPrevista = null;
  private Integer quantitaConsegnata = null;
  private Date dataConsegna = null;
  private Date dataOrdine = null;
  private String stato = null;
  private String prodotto = null;

  /**
   * L&#39;identificativo dell&#39;ordine inserito dall&#39;utente
   **/
  

  @JsonProperty("identificativo_ordine") 
 
  public String getIdentificativoOrdine() {
    return identificativoOrdine;
  }
  public void setIdentificativoOrdine(String identificativoOrdine) {
    this.identificativoOrdine = identificativoOrdine;
  }

  /**
   * il numero di pezzi ordinati dall&#39;utente
   **/
  

  @JsonProperty("quantita_ordinata") 
 
  public Integer getQuantitaOrdinata() {
    return quantitaOrdinata;
  }
  public void setQuantitaOrdinata(Integer quantitaOrdinata) {
    this.quantitaOrdinata = quantitaOrdinata;
  }

  /**
   * La data di consegna prevista
   **/
  

  @JsonProperty("data_consegna_prevista") 
 
  public Date getDataConsegnaPrevista() {
    return dataConsegnaPrevista;
  }
  public void setDataConsegnaPrevista(Date dataConsegnaPrevista) {
    this.dataConsegnaPrevista = dataConsegnaPrevista;
  }

  /**
   * il numero di pezzi effettivamente consegnati
   **/
  

  @JsonProperty("quantita_consegnata") 
 
  public Integer getQuantitaConsegnata() {
    return quantitaConsegnata;
  }
  public void setQuantitaConsegnata(Integer quantitaConsegnata) {
    this.quantitaConsegnata = quantitaConsegnata;
  }

  /**
   * La data di consegna effettiva
   **/
  

  @JsonProperty("data_consegna") 
 
  public Date getDataConsegna() {
    return dataConsegna;
  }
  public void setDataConsegna(Date dataConsegna) {
    this.dataConsegna = dataConsegna;
  }

  /**
   * La data in cui � stato effettuato l&#39;ordine. Inserito dall&#39;utente
   **/
  

  @JsonProperty("data_ordine") 
 
  public Date getDataOrdine() {
    return dataOrdine;
  }
  public void setDataOrdine(Date dataOrdine) {
    this.dataOrdine = dataOrdine;
  }

  /**
   * Il codice dello stato
   **/
  

  @JsonProperty("stato") 
 
  public String getStato() {
    return stato;
  }
  public void setStato(String stato) {
    this.stato = stato;
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
    Payload3 payload3 = (Payload3) o;
    return Objects.equals(identificativoOrdine, payload3.identificativoOrdine) &&
        Objects.equals(quantitaOrdinata, payload3.quantitaOrdinata) &&
        Objects.equals(dataConsegnaPrevista, payload3.dataConsegnaPrevista) &&
        Objects.equals(quantitaConsegnata, payload3.quantitaConsegnata) &&
        Objects.equals(dataConsegna, payload3.dataConsegna) &&
        Objects.equals(dataOrdine, payload3.dataOrdine) &&
        Objects.equals(stato, payload3.stato) &&
        Objects.equals(prodotto, payload3.prodotto);
  }

  @Override
  public int hashCode() {
    return Objects.hash(identificativoOrdine, quantitaOrdinata, dataConsegnaPrevista, quantitaConsegnata, dataConsegna, dataOrdine, stato, prodotto);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Payload3 {\n");
    
    sb.append("    identificativoOrdine: ").append(toIndentedString(identificativoOrdine)).append("\n");
    sb.append("    quantitaOrdinata: ").append(toIndentedString(quantitaOrdinata)).append("\n");
    sb.append("    dataConsegnaPrevista: ").append(toIndentedString(dataConsegnaPrevista)).append("\n");
    sb.append("    quantitaConsegnata: ").append(toIndentedString(quantitaConsegnata)).append("\n");
    sb.append("    dataConsegna: ").append(toIndentedString(dataConsegna)).append("\n");
    sb.append("    dataOrdine: ").append(toIndentedString(dataOrdine)).append("\n");
    sb.append("    stato: ").append(toIndentedString(stato)).append("\n");
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

