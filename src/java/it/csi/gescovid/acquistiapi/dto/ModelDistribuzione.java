package it.csi.gescovid.acquistiapi.dto;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import io.swagger.annotations.ApiModel;
import it.csi.gescovid.acquistiapi.dto.ModelEnte;
import it.csi.gescovid.acquistiapi.dto.ModelMagazzino;
import it.csi.gescovid.acquistiapi.dto.ModelProdotto;
import it.csi.gescovid.acquistiapi.dto.ModelStruttura;
import java.util.Date;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class ModelDistribuzione   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String id = null;
  private Integer quantita = null;
  private Date dataConsegna = null;
  private Date dataRegistrazione = null;
  private Date dataCreazione = null;
  private Date dataModifica = null;
  private ModelEnte ente = null;
  private ModelStruttura struttura = null;
  private ModelProdotto prodotto = null;
  private ModelMagazzino magazzino = null;

  /**
   * L&#39;id del magazzino
   **/
  

  @JsonProperty("id") 
 
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

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
   * La data in cui � stata presa in carico la consegna
   **/
  

  @JsonProperty("data_registrazione") 
 
  public Date getDataRegistrazione() {
    return dataRegistrazione;
  }
  public void setDataRegistrazione(Date dataRegistrazione) {
    this.dataRegistrazione = dataRegistrazione;
  }

  /**
   * La data in cui � stato inserito il record sul DB
   **/
  

  @JsonProperty("data_creazione") 
 
  public Date getDataCreazione() {
    return dataCreazione;
  }
  public void setDataCreazione(Date dataCreazione) {
    this.dataCreazione = dataCreazione;
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
  

  @JsonProperty("magazzino") 
 
  public ModelMagazzino getMagazzino() {
    return magazzino;
  }
  public void setMagazzino(ModelMagazzino magazzino) {
    this.magazzino = magazzino;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelDistribuzione modelDistribuzione = (ModelDistribuzione) o;
    return Objects.equals(id, modelDistribuzione.id) &&
        Objects.equals(quantita, modelDistribuzione.quantita) &&
        Objects.equals(dataConsegna, modelDistribuzione.dataConsegna) &&
        Objects.equals(dataRegistrazione, modelDistribuzione.dataRegistrazione) &&
        Objects.equals(dataCreazione, modelDistribuzione.dataCreazione) &&
        Objects.equals(dataModifica, modelDistribuzione.dataModifica) &&
        Objects.equals(ente, modelDistribuzione.ente) &&
        Objects.equals(struttura, modelDistribuzione.struttura) &&
        Objects.equals(prodotto, modelDistribuzione.prodotto) &&
        Objects.equals(magazzino, modelDistribuzione.magazzino);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, quantita, dataConsegna, dataRegistrazione, dataCreazione, dataModifica, ente, struttura, prodotto, magazzino);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelDistribuzione {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    quantita: ").append(toIndentedString(quantita)).append("\n");
    sb.append("    dataConsegna: ").append(toIndentedString(dataConsegna)).append("\n");
    sb.append("    dataRegistrazione: ").append(toIndentedString(dataRegistrazione)).append("\n");
    sb.append("    dataCreazione: ").append(toIndentedString(dataCreazione)).append("\n");
    sb.append("    dataModifica: ").append(toIndentedString(dataModifica)).append("\n");
    sb.append("    ente: ").append(toIndentedString(ente)).append("\n");
    sb.append("    struttura: ").append(toIndentedString(struttura)).append("\n");
    sb.append("    prodotto: ").append(toIndentedString(prodotto)).append("\n");
    sb.append("    magazzino: ").append(toIndentedString(magazzino)).append("\n");
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

