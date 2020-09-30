package it.csi.gescovid.acquistiapi.dto;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class RichiesteDettagli   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Integer dotazioneAttuale = null;
  private Integer fabbisognoGiornaliero = null;
  private Integer fabbisognoSettimanale = null;
  private Integer quantita = null;
  private String prodotto = null;
  private String struttura = null;

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
   * L&#39;id della struttura. Se null significa che la richiesta � per l&#39;ente
   **/
  

  @JsonProperty("struttura") 
 
  public String getStruttura() {
    return struttura;
  }
  public void setStruttura(String struttura) {
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
    RichiesteDettagli richiesteDettagli = (RichiesteDettagli) o;
    return Objects.equals(dotazioneAttuale, richiesteDettagli.dotazioneAttuale) &&
        Objects.equals(fabbisognoGiornaliero, richiesteDettagli.fabbisognoGiornaliero) &&
        Objects.equals(fabbisognoSettimanale, richiesteDettagli.fabbisognoSettimanale) &&
        Objects.equals(quantita, richiesteDettagli.quantita) &&
        Objects.equals(prodotto, richiesteDettagli.prodotto) &&
        Objects.equals(struttura, richiesteDettagli.struttura);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dotazioneAttuale, fabbisognoGiornaliero, fabbisognoSettimanale, quantita, prodotto, struttura);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RichiesteDettagli {\n");
    
    sb.append("    dotazioneAttuale: ").append(toIndentedString(dotazioneAttuale)).append("\n");
    sb.append("    fabbisognoGiornaliero: ").append(toIndentedString(fabbisognoGiornaliero)).append("\n");
    sb.append("    fabbisognoSettimanale: ").append(toIndentedString(fabbisognoSettimanale)).append("\n");
    sb.append("    quantita: ").append(toIndentedString(quantita)).append("\n");
    sb.append("    prodotto: ").append(toIndentedString(prodotto)).append("\n");
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

