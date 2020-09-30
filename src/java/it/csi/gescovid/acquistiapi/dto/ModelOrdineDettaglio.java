package it.csi.gescovid.acquistiapi.dto;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import io.swagger.annotations.ApiModel;
import it.csi.gescovid.acquistiapi.dto.ModelOrdineDettaglioCambioStato;
import it.csi.gescovid.acquistiapi.dto.ModelOrdineStato;
import it.csi.gescovid.acquistiapi.dto.ModelProdotto;
import java.util.Date;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class ModelOrdineDettaglio   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Integer id = null;
  private String identificativoOrdine = null;
  private Integer quantitaOrdinata = null;
  private Date dataConsegnaPrevista = null;
  private Integer quantitaConsegnata = null;
  private Date dataConsegna = null;
  private Date dataOrdine = null;
  private Date dataCreazione = null;
  private ModelOrdineStato stato = null;
  private ModelProdotto prodotto = null;
  private List<ModelOrdineDettaglioCambioStato> cronologia = new ArrayList<ModelOrdineDettaglioCambioStato>();

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
   * La data di creazione del record nella tabella
   **/
  

  @JsonProperty("data_creazione") 
 
  public Date getDataCreazione() {
    return dataCreazione;
  }
  public void setDataCreazione(Date dataCreazione) {
    this.dataCreazione = dataCreazione;
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
  

  @JsonProperty("cronologia") 
 
  public List<ModelOrdineDettaglioCambioStato> getCronologia() {
    return cronologia;
  }
  public void setCronologia(List<ModelOrdineDettaglioCambioStato> cronologia) {
    this.cronologia = cronologia;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelOrdineDettaglio modelOrdineDettaglio = (ModelOrdineDettaglio) o;
    return Objects.equals(id, modelOrdineDettaglio.id) &&
        Objects.equals(identificativoOrdine, modelOrdineDettaglio.identificativoOrdine) &&
        Objects.equals(quantitaOrdinata, modelOrdineDettaglio.quantitaOrdinata) &&
        Objects.equals(dataConsegnaPrevista, modelOrdineDettaglio.dataConsegnaPrevista) &&
        Objects.equals(quantitaConsegnata, modelOrdineDettaglio.quantitaConsegnata) &&
        Objects.equals(dataConsegna, modelOrdineDettaglio.dataConsegna) &&
        Objects.equals(dataOrdine, modelOrdineDettaglio.dataOrdine) &&
        Objects.equals(dataCreazione, modelOrdineDettaglio.dataCreazione) &&
        Objects.equals(stato, modelOrdineDettaglio.stato) &&
        Objects.equals(prodotto, modelOrdineDettaglio.prodotto) &&
        Objects.equals(cronologia, modelOrdineDettaglio.cronologia);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, identificativoOrdine, quantitaOrdinata, dataConsegnaPrevista, quantitaConsegnata, dataConsegna, dataOrdine, dataCreazione, stato, prodotto, cronologia);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelOrdineDettaglio {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    identificativoOrdine: ").append(toIndentedString(identificativoOrdine)).append("\n");
    sb.append("    quantitaOrdinata: ").append(toIndentedString(quantitaOrdinata)).append("\n");
    sb.append("    dataConsegnaPrevista: ").append(toIndentedString(dataConsegnaPrevista)).append("\n");
    sb.append("    quantitaConsegnata: ").append(toIndentedString(quantitaConsegnata)).append("\n");
    sb.append("    dataConsegna: ").append(toIndentedString(dataConsegna)).append("\n");
    sb.append("    dataOrdine: ").append(toIndentedString(dataOrdine)).append("\n");
    sb.append("    dataCreazione: ").append(toIndentedString(dataCreazione)).append("\n");
    sb.append("    stato: ").append(toIndentedString(stato)).append("\n");
    sb.append("    prodotto: ").append(toIndentedString(prodotto)).append("\n");
    sb.append("    cronologia: ").append(toIndentedString(cronologia)).append("\n");
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

