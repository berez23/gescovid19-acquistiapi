package it.csi.gescovid.acquistiapi.dto;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.gescovid.acquistiapi.dto.RichiesteDettagli;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class Payload   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String note = null;
  private String ente = null;
  private List<RichiesteDettagli> dettagli = new ArrayList<RichiesteDettagli>();

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
   * l&#39;id dell&#39;ente
   **/
  

  @JsonProperty("ente") 
 
  public String getEnte() {
    return ente;
  }
  public void setEnte(String ente) {
    this.ente = ente;
  }

  /**
   **/
  

  @JsonProperty("dettagli") 
 
  public List<RichiesteDettagli> getDettagli() {
    return dettagli;
  }
  public void setDettagli(List<RichiesteDettagli> dettagli) {
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
    Payload payload = (Payload) o;
    return Objects.equals(note, payload.note) &&
        Objects.equals(ente, payload.ente) &&
        Objects.equals(dettagli, payload.dettagli);
  }

  @Override
  public int hashCode() {
    return Objects.hash(note, ente, dettagli);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Payload {\n");
    
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

