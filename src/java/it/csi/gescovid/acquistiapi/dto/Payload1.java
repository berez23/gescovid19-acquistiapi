package it.csi.gescovid.acquistiapi.dto;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class Payload1   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Integer dotazioneAttuale = null;
  private Integer fabbisognoGiornaliero = null;
  private Integer fabbisognoSettimanale = null;
  private Integer quantita = null;
  private String prodotto = null;
  private String stato = null;
  private Integer ordine = null;

  /**
   * il numero di pezzi attuali a disposizione dell&#39;ente
   **/
  

  @JsonProperty("dotazione_attuale") 
 
  public Integer getDotazioneAttuale() {
    return dotazioneAttuale;
  }
  public void setDotazioneAttuale(Integer dotazioneAttuale) {
    this.dotazioneAttuale = dotazioneAttuale;
  }

  /**
   * il numero di pezzi necessari giornalmente all&#39;ente
   **/
  

  @JsonProperty("fabbisogno_giornaliero") 
 
  public Integer getFabbisognoGiornaliero() {
    return fabbisognoGiornaliero;
  }
  public void setFabbisognoGiornaliero(Integer fabbisognoGiornaliero) {
    this.fabbisognoGiornaliero = fabbisognoGiornaliero;
  }

  /**
   * il numero di pezzi necessari settimanalmente all&#39;ente
   **/
  

  @JsonProperty("fabbisogno_settimanale") 
 
  public Integer getFabbisognoSettimanale() {
    return fabbisognoSettimanale;
  }
  public void setFabbisognoSettimanale(Integer fabbisognoSettimanale) {
    this.fabbisognoSettimanale = fabbisognoSettimanale;
  }

  /**
   * il numero di pezzi richiesti dall&#39;ente
   **/
  

  @JsonProperty("quantita") 
 
  public Integer getQuantita() {
    return quantita;
  }
  public void setQuantita(Integer quantita) {
    this.quantita = quantita;
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

  /**
   * il codice dello stato
   **/
  

  @JsonProperty("stato") 
 
  public String getStato() {
    return stato;
  }
  public void setStato(String stato) {
    this.stato = stato;
  }

  /**
   * L&#39;id dell&#39;ordine
   **/
  

  @JsonProperty("ordine") 
 
  public Integer getOrdine() {
    return ordine;
  }
  public void setOrdine(Integer ordine) {
    this.ordine = ordine;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Payload1 payload1 = (Payload1) o;
    return Objects.equals(dotazioneAttuale, payload1.dotazioneAttuale) &&
        Objects.equals(fabbisognoGiornaliero, payload1.fabbisognoGiornaliero) &&
        Objects.equals(fabbisognoSettimanale, payload1.fabbisognoSettimanale) &&
        Objects.equals(quantita, payload1.quantita) &&
        Objects.equals(prodotto, payload1.prodotto) &&
        Objects.equals(stato, payload1.stato) &&
        Objects.equals(ordine, payload1.ordine);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dotazioneAttuale, fabbisognoGiornaliero, fabbisognoSettimanale, quantita, prodotto, stato, ordine);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Payload1 {\n");
    
    sb.append("    dotazioneAttuale: ").append(toIndentedString(dotazioneAttuale)).append("\n");
    sb.append("    fabbisognoGiornaliero: ").append(toIndentedString(fabbisognoGiornaliero)).append("\n");
    sb.append("    fabbisognoSettimanale: ").append(toIndentedString(fabbisognoSettimanale)).append("\n");
    sb.append("    quantita: ").append(toIndentedString(quantita)).append("\n");
    sb.append("    prodotto: ").append(toIndentedString(prodotto)).append("\n");
    sb.append("    stato: ").append(toIndentedString(stato)).append("\n");
    sb.append("    ordine: ").append(toIndentedString(ordine)).append("\n");
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

