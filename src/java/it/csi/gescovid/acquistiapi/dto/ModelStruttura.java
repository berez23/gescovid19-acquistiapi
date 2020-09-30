package it.csi.gescovid.acquistiapi.dto;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import io.swagger.annotations.ApiModel;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class ModelStruttura   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String id = null;
  private String nome = null;
  private String natura = null;
  private String enteId = null;

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
   * La natura della struttura
   **/
  

  @JsonProperty("natura") 
 
  public String getNatura() {
    return natura;
  }
  public void setNatura(String natura) {
    this.natura = natura;
  }

  /**
   * L&#39;id dell&#39;ente a cui appartiene la struttura
   **/
  

  @JsonProperty("ente_id") 
 
  public String getEnteId() {
    return enteId;
  }
  public void setEnteId(String enteId) {
    this.enteId = enteId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelStruttura modelStruttura = (ModelStruttura) o;
    return Objects.equals(id, modelStruttura.id) &&
        Objects.equals(nome, modelStruttura.nome) &&
        Objects.equals(natura, modelStruttura.natura) &&
        Objects.equals(enteId, modelStruttura.enteId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nome, natura, enteId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelStruttura {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
    sb.append("    natura: ").append(toIndentedString(natura)).append("\n");
    sb.append("    enteId: ").append(toIndentedString(enteId)).append("\n");
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

