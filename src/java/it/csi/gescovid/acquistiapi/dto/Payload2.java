package it.csi.gescovid.acquistiapi.dto;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class Payload2   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String identificativoOrdine = null;
  private Integer quantitaOrdinata = null;
  private Date dataConsegnaPrevista = null;
  private Date dataOrdine = null;
  private String prodotto = null;
  private List<String> richiestaDettagli = new ArrayList<String>();

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
   **/
  

  @JsonProperty("richiesta_dettagli") 
 
  public List<String> getRichiestaDettagli() {
    return richiestaDettagli;
  }
  public void setRichiestaDettagli(List<String> richiestaDettagli) {
    this.richiestaDettagli = richiestaDettagli;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Payload2 payload2 = (Payload2) o;
    return Objects.equals(identificativoOrdine, payload2.identificativoOrdine) &&
        Objects.equals(quantitaOrdinata, payload2.quantitaOrdinata) &&
        Objects.equals(dataConsegnaPrevista, payload2.dataConsegnaPrevista) &&
        Objects.equals(dataOrdine, payload2.dataOrdine) &&
        Objects.equals(prodotto, payload2.prodotto) &&
        Objects.equals(richiestaDettagli, payload2.richiestaDettagli);
  }

  @Override
  public int hashCode() {
    return Objects.hash(identificativoOrdine, quantitaOrdinata, dataConsegnaPrevista, dataOrdine, prodotto, richiestaDettagli);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Payload2 {\n");
    
    sb.append("    identificativoOrdine: ").append(toIndentedString(identificativoOrdine)).append("\n");
    sb.append("    quantitaOrdinata: ").append(toIndentedString(quantitaOrdinata)).append("\n");
    sb.append("    dataConsegnaPrevista: ").append(toIndentedString(dataConsegnaPrevista)).append("\n");
    sb.append("    dataOrdine: ").append(toIndentedString(dataOrdine)).append("\n");
    sb.append("    prodotto: ").append(toIndentedString(prodotto)).append("\n");
    sb.append("    richiestaDettagli: ").append(toIndentedString(richiestaDettagli)).append("\n");
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

