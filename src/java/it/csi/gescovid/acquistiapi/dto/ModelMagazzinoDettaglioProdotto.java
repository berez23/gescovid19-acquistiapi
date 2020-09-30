package it.csi.gescovid.acquistiapi.dto;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import io.swagger.annotations.ApiModel;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class ModelMagazzinoDettaglioProdotto   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String codice = null;
  private String nome = null;
  private String descrizione = null;
  private Integer quantitaDisponibile = null;
  private Integer quantitaDistribuita = null;
  private Integer quantitaRicevuta = null;

  /**
   * Un codice univoco
   **/
  

  @JsonProperty("codice") 
 
  public String getCodice() {
    return codice;
  }
  public void setCodice(String codice) {
    this.codice = codice;
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
   * La descrizione del prodotto
   **/
  

  @JsonProperty("descrizione") 
 
  public String getDescrizione() {
    return descrizione;
  }
  public void setDescrizione(String descrizione) {
    this.descrizione = descrizione;
  }

  /**
   * La quantita disponibile in magazzino
   **/
  

  @JsonProperty("quantita_disponibile") 
 
  public Integer getQuantitaDisponibile() {
    return quantitaDisponibile;
  }
  public void setQuantitaDisponibile(Integer quantitaDisponibile) {
    this.quantitaDisponibile = quantitaDisponibile;
  }

  /**
   * La quantit� distributa agli enti
   **/
  

  @JsonProperty("quantita_distribuita") 
 
  public Integer getQuantitaDistribuita() {
    return quantitaDistribuita;
  }
  public void setQuantitaDistribuita(Integer quantitaDistribuita) {
    this.quantitaDistribuita = quantitaDistribuita;
  }

  /**
   * La quantit� ricevuta dai fornitori
   **/
  

  @JsonProperty("quantita_ricevuta") 
 
  public Integer getQuantitaRicevuta() {
    return quantitaRicevuta;
  }
  public void setQuantitaRicevuta(Integer quantitaRicevuta) {
    this.quantitaRicevuta = quantitaRicevuta;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelMagazzinoDettaglioProdotto modelMagazzinoDettaglioProdotto = (ModelMagazzinoDettaglioProdotto) o;
    return Objects.equals(codice, modelMagazzinoDettaglioProdotto.codice) &&
        Objects.equals(nome, modelMagazzinoDettaglioProdotto.nome) &&
        Objects.equals(descrizione, modelMagazzinoDettaglioProdotto.descrizione) &&
        Objects.equals(quantitaDisponibile, modelMagazzinoDettaglioProdotto.quantitaDisponibile) &&
        Objects.equals(quantitaDistribuita, modelMagazzinoDettaglioProdotto.quantitaDistribuita) &&
        Objects.equals(quantitaRicevuta, modelMagazzinoDettaglioProdotto.quantitaRicevuta);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codice, nome, descrizione, quantitaDisponibile, quantitaDistribuita, quantitaRicevuta);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelMagazzinoDettaglioProdotto {\n");
    
    sb.append("    codice: ").append(toIndentedString(codice)).append("\n");
    sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
    sb.append("    descrizione: ").append(toIndentedString(descrizione)).append("\n");
    sb.append("    quantitaDisponibile: ").append(toIndentedString(quantitaDisponibile)).append("\n");
    sb.append("    quantitaDistribuita: ").append(toIndentedString(quantitaDistribuita)).append("\n");
    sb.append("    quantitaRicevuta: ").append(toIndentedString(quantitaRicevuta)).append("\n");
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

