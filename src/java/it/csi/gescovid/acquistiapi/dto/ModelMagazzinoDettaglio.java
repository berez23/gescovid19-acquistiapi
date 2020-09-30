package it.csi.gescovid.acquistiapi.dto;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import io.swagger.annotations.ApiModel;
import it.csi.gescovid.acquistiapi.dto.ModelMagazzinoDettaglioProdotto;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class ModelMagazzinoDettaglio   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String id = null;
  private String nome = null;
  private String comune = null;
  private String indirizzo = null;
  private List<ModelMagazzinoDettaglioProdotto> prodotti = new ArrayList<ModelMagazzinoDettaglioProdotto>();

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
   * Il nome del magazzino
   **/
  

  @JsonProperty("nome") 
 
  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   * Il codice del comune
   **/
  

  @JsonProperty("comune") 
 
  public String getComune() {
    return comune;
  }
  public void setComune(String comune) {
    this.comune = comune;
  }

  /**
   * L&#39;indirizzo del magazzino
   **/
  

  @JsonProperty("indirizzo") 
 
  public String getIndirizzo() {
    return indirizzo;
  }
  public void setIndirizzo(String indirizzo) {
    this.indirizzo = indirizzo;
  }

  /**
   **/
  

  @JsonProperty("prodotti") 
 
  public List<ModelMagazzinoDettaglioProdotto> getProdotti() {
    return prodotti;
  }
  public void setProdotti(List<ModelMagazzinoDettaglioProdotto> prodotti) {
    this.prodotti = prodotti;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelMagazzinoDettaglio modelMagazzinoDettaglio = (ModelMagazzinoDettaglio) o;
    return Objects.equals(id, modelMagazzinoDettaglio.id) &&
        Objects.equals(nome, modelMagazzinoDettaglio.nome) &&
        Objects.equals(comune, modelMagazzinoDettaglio.comune) &&
        Objects.equals(indirizzo, modelMagazzinoDettaglio.indirizzo) &&
        Objects.equals(prodotti, modelMagazzinoDettaglio.prodotti);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nome, comune, indirizzo, prodotti);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelMagazzinoDettaglio {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
    sb.append("    comune: ").append(toIndentedString(comune)).append("\n");
    sb.append("    indirizzo: ").append(toIndentedString(indirizzo)).append("\n");
    sb.append("    prodotti: ").append(toIndentedString(prodotti)).append("\n");
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

