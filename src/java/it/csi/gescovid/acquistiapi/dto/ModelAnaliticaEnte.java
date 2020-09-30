package it.csi.gescovid.acquistiapi.dto;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import io.swagger.annotations.ApiModel;
import java.math.BigDecimal;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class ModelAnaliticaEnte   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String id = null;
  private String nome = null;
  private BigDecimal percentualeDistribuzione = null;
  private BigDecimal totaleApprovato = null;
  private BigDecimal totaleDistribuito = null;

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
   * Una nome testuale da mostrare sulla UI
   **/
  

  @JsonProperty("nome") 
 
  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   * la percentuale di distribuzione sull&#39;asr
   **/
  

  @JsonProperty("percentuale_distribuzione") 
 
  public BigDecimal getPercentualeDistribuzione() {
    return percentualeDistribuzione;
  }
  public void setPercentualeDistribuzione(BigDecimal percentualeDistribuzione) {
    this.percentualeDistribuzione = percentualeDistribuzione;
  }

  /**
   * Il totale approvato all&#39;ente dall&#39;inizio ad oggi
   **/
  

  @JsonProperty("totale_approvato") 
 
  public BigDecimal getTotaleApprovato() {
    return totaleApprovato;
  }
  public void setTotaleApprovato(BigDecimal totaleApprovato) {
    this.totaleApprovato = totaleApprovato;
  }

  /**
   * Il totale distribuito all&#39;ente dall&#39;inizio ad oggi
   **/
  

  @JsonProperty("totale_distribuito") 
 
  public BigDecimal getTotaleDistribuito() {
    return totaleDistribuito;
  }
  public void setTotaleDistribuito(BigDecimal totaleDistribuito) {
    this.totaleDistribuito = totaleDistribuito;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelAnaliticaEnte modelAnaliticaEnte = (ModelAnaliticaEnte) o;
    return Objects.equals(id, modelAnaliticaEnte.id) &&
        Objects.equals(nome, modelAnaliticaEnte.nome) &&
        Objects.equals(percentualeDistribuzione, modelAnaliticaEnte.percentualeDistribuzione) &&
        Objects.equals(totaleApprovato, modelAnaliticaEnte.totaleApprovato) &&
        Objects.equals(totaleDistribuito, modelAnaliticaEnte.totaleDistribuito);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nome, percentualeDistribuzione, totaleApprovato, totaleDistribuito);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelAnaliticaEnte {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
    sb.append("    percentualeDistribuzione: ").append(toIndentedString(percentualeDistribuzione)).append("\n");
    sb.append("    totaleApprovato: ").append(toIndentedString(totaleApprovato)).append("\n");
    sb.append("    totaleDistribuito: ").append(toIndentedString(totaleDistribuito)).append("\n");
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

