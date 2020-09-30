package it.csi.gescovid.acquistiapi.dto;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import io.swagger.annotations.ApiModel;
import it.csi.gescovid.acquistiapi.dto.ModelEnte;
import it.csi.gescovid.acquistiapi.dto.ModelOrdine;
import it.csi.gescovid.acquistiapi.dto.ModelProdotto;
import it.csi.gescovid.acquistiapi.dto.ModelRichiestaDettaglioStato;
import it.csi.gescovid.acquistiapi.dto.ModelStruttura;
import java.util.Date;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class ModelRichiestaDettaglioStandalone   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String id = null;
  private Integer dotazioneAttuale = null;
  private Integer fabbisognoGiornaliero = null;
  private Integer fabbisognoSettimanale = null;
  private Integer quantita = null;
  private Date dataCreazione = null;
  private Date dataModifica = null;
  private ModelRichiestaDettaglioStato stato = null;
  private ModelProdotto prodotto = null;
  private ModelOrdine ordine = null;
  private ModelEnte ente = null;
  private ModelStruttura struttura = null;
  private String richiesta = null;
  private Date dataRichiesta = null;

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
   * La data di creazione
   **/
  

  @JsonProperty("data_creazione") 
 
  public Date getDataCreazione() {
    return dataCreazione;
  }
  public void setDataCreazione(Date dataCreazione) {
    this.dataCreazione = dataCreazione;
  }

  /**
   * La data di creazione
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
  

  @JsonProperty("stato") 
 
  public ModelRichiestaDettaglioStato getStato() {
    return stato;
  }
  public void setStato(ModelRichiestaDettaglioStato stato) {
    this.stato = stato;
  }

  /**
   **/
  

  @JsonProperty("prodotto") 
 
  public ModelProdotto getProdotto() {
    return prodotto;
  }
  public void setProdotto(ModelProdotto prodotto) {
    this.prodotto = prodotto;
  }

  /**
   **/
  

  @JsonProperty("ordine") 
 
  public ModelOrdine getOrdine() {
    return ordine;
  }
  public void setOrdine(ModelOrdine ordine) {
    this.ordine = ordine;
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

  /**
   * Id della richiesta
   **/
  

  @JsonProperty("richiesta") 
 
  public String getRichiesta() {
    return richiesta;
  }
  public void setRichiesta(String richiesta) {
    this.richiesta = richiesta;
  }

  /**
   * La data della richiesta
   **/
  

  @JsonProperty("data_richiesta") 
 
  public Date getDataRichiesta() {
    return dataRichiesta;
  }
  public void setDataRichiesta(Date dataRichiesta) {
    this.dataRichiesta = dataRichiesta;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelRichiestaDettaglioStandalone modelRichiestaDettaglioStandalone = (ModelRichiestaDettaglioStandalone) o;
    return Objects.equals(id, modelRichiestaDettaglioStandalone.id) &&
        Objects.equals(dotazioneAttuale, modelRichiestaDettaglioStandalone.dotazioneAttuale) &&
        Objects.equals(fabbisognoGiornaliero, modelRichiestaDettaglioStandalone.fabbisognoGiornaliero) &&
        Objects.equals(fabbisognoSettimanale, modelRichiestaDettaglioStandalone.fabbisognoSettimanale) &&
        Objects.equals(quantita, modelRichiestaDettaglioStandalone.quantita) &&
        Objects.equals(dataCreazione, modelRichiestaDettaglioStandalone.dataCreazione) &&
        Objects.equals(dataModifica, modelRichiestaDettaglioStandalone.dataModifica) &&
        Objects.equals(stato, modelRichiestaDettaglioStandalone.stato) &&
        Objects.equals(prodotto, modelRichiestaDettaglioStandalone.prodotto) &&
        Objects.equals(ordine, modelRichiestaDettaglioStandalone.ordine) &&
        Objects.equals(ente, modelRichiestaDettaglioStandalone.ente) &&
        Objects.equals(struttura, modelRichiestaDettaglioStandalone.struttura) &&
        Objects.equals(richiesta, modelRichiestaDettaglioStandalone.richiesta) &&
        Objects.equals(dataRichiesta, modelRichiestaDettaglioStandalone.dataRichiesta);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, dotazioneAttuale, fabbisognoGiornaliero, fabbisognoSettimanale, quantita, dataCreazione, dataModifica, stato, prodotto, ordine, ente, struttura, richiesta, dataRichiesta);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelRichiestaDettaglioStandalone {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    dotazioneAttuale: ").append(toIndentedString(dotazioneAttuale)).append("\n");
    sb.append("    fabbisognoGiornaliero: ").append(toIndentedString(fabbisognoGiornaliero)).append("\n");
    sb.append("    fabbisognoSettimanale: ").append(toIndentedString(fabbisognoSettimanale)).append("\n");
    sb.append("    quantita: ").append(toIndentedString(quantita)).append("\n");
    sb.append("    dataCreazione: ").append(toIndentedString(dataCreazione)).append("\n");
    sb.append("    dataModifica: ").append(toIndentedString(dataModifica)).append("\n");
    sb.append("    stato: ").append(toIndentedString(stato)).append("\n");
    sb.append("    prodotto: ").append(toIndentedString(prodotto)).append("\n");
    sb.append("    ordine: ").append(toIndentedString(ordine)).append("\n");
    sb.append("    ente: ").append(toIndentedString(ente)).append("\n");
    sb.append("    struttura: ").append(toIndentedString(struttura)).append("\n");
    sb.append("    richiesta: ").append(toIndentedString(richiesta)).append("\n");
    sb.append("    dataRichiesta: ").append(toIndentedString(dataRichiesta)).append("\n");
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

