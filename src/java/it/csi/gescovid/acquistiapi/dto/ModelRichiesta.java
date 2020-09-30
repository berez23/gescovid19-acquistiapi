package it.csi.gescovid.acquistiapi.dto;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import io.swagger.annotations.ApiModel;
import it.csi.gescovid.acquistiapi.dto.ModelEnte;
import it.csi.gescovid.acquistiapi.dto.ModelRichiestaDettaglio;
import java.util.Date;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class ModelRichiesta   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String id = null;
  private Date dataRichiesta = null;
  private Date dataPeriodoDa = null;
  private Date dataPeriodoA = null;
  private String note = null;
  private ModelEnte ente = null;
  private List<ModelRichiestaDettaglio> dettagli = new ArrayList<ModelRichiestaDettaglio>();

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
   * La data di inserimento nel sistema della richiesta
   **/
  

  @JsonProperty("data_richiesta") 
 
  public Date getDataRichiesta() {
    return dataRichiesta;
  }
  public void setDataRichiesta(Date dataRichiesta) {
    this.dataRichiesta = dataRichiesta;
  }

  /**
   **/
  

  @JsonProperty("data_periodo_da") 
 
  public Date getDataPeriodoDa() {
    return dataPeriodoDa;
  }
  public void setDataPeriodoDa(Date dataPeriodoDa) {
    this.dataPeriodoDa = dataPeriodoDa;
  }

  /**
   **/
  

  @JsonProperty("data_periodo_a") 
 
  public Date getDataPeriodoA() {
    return dataPeriodoA;
  }
  public void setDataPeriodoA(Date dataPeriodoA) {
    this.dataPeriodoA = dataPeriodoA;
  }

  /**
   * Le note inserite dall&#39;ente
   **/
  

  @JsonProperty("note") 
 
  public String getNote() {
    return note;
  }
  public void setNote(String note) {
    this.note = note;
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
  

  @JsonProperty("dettagli") 
 
  public List<ModelRichiestaDettaglio> getDettagli() {
    return dettagli;
  }
  public void setDettagli(List<ModelRichiestaDettaglio> dettagli) {
    this.dettagli = dettagli;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelRichiesta modelRichiesta = (ModelRichiesta) o;
    return Objects.equals(id, modelRichiesta.id) &&
        Objects.equals(dataRichiesta, modelRichiesta.dataRichiesta) &&
        Objects.equals(dataPeriodoDa, modelRichiesta.dataPeriodoDa) &&
        Objects.equals(dataPeriodoA, modelRichiesta.dataPeriodoA) &&
        Objects.equals(note, modelRichiesta.note) &&
        Objects.equals(ente, modelRichiesta.ente) &&
        Objects.equals(dettagli, modelRichiesta.dettagli);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, dataRichiesta, dataPeriodoDa, dataPeriodoA, note, ente, dettagli);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelRichiesta {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    dataRichiesta: ").append(toIndentedString(dataRichiesta)).append("\n");
    sb.append("    dataPeriodoDa: ").append(toIndentedString(dataPeriodoDa)).append("\n");
    sb.append("    dataPeriodoA: ").append(toIndentedString(dataPeriodoA)).append("\n");
    sb.append("    note: ").append(toIndentedString(note)).append("\n");
    sb.append("    ente: ").append(toIndentedString(ente)).append("\n");
    sb.append("    dettagli: ").append(toIndentedString(dettagli)).append("\n");
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

