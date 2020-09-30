package it.csi.gescovid.acquistiapi.dto;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import io.swagger.annotations.ApiModel;
import it.csi.gescovid.acquistiapi.dto.ModelEnte;
import it.csi.gescovid.acquistiapi.dto.ModelProdotto;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class ModelAnalitica   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Integer totaleApprovato = null;
  private Integer totaleDistribuito = null;
  private ModelProdotto prodotto = null;
  private ModelEnte ente = null;

  /**
   * Il totale approvato del prodotto sull&#39;ente
   **/
  

  @JsonProperty("totale_approvato") 
 
  public Integer getTotaleApprovato() {
    return totaleApprovato;
  }
  public void setTotaleApprovato(Integer totaleApprovato) {
    this.totaleApprovato = totaleApprovato;
  }

  /**
   * Il totale distribuito del prodotto sull&#39;ente
   **/
  

  @JsonProperty("totale_distribuito") 
 
  public Integer getTotaleDistribuito() {
    return totaleDistribuito;
  }
  public void setTotaleDistribuito(Integer totaleDistribuito) {
    this.totaleDistribuito = totaleDistribuito;
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
  

  @JsonProperty("ente") 
 
  public ModelEnte getEnte() {
    return ente;
  }
  public void setEnte(ModelEnte ente) {
    this.ente = ente;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelAnalitica modelAnalitica = (ModelAnalitica) o;
    return Objects.equals(totaleApprovato, modelAnalitica.totaleApprovato) &&
        Objects.equals(totaleDistribuito, modelAnalitica.totaleDistribuito) &&
        Objects.equals(prodotto, modelAnalitica.prodotto) &&
        Objects.equals(ente, modelAnalitica.ente);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totaleApprovato, totaleDistribuito, prodotto, ente);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelAnalitica {\n");
    
    sb.append("    totaleApprovato: ").append(toIndentedString(totaleApprovato)).append("\n");
    sb.append("    totaleDistribuito: ").append(toIndentedString(totaleDistribuito)).append("\n");
    sb.append("    prodotto: ").append(toIndentedString(prodotto)).append("\n");
    sb.append("    ente: ").append(toIndentedString(ente)).append("\n");
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

