package it.csi.gescovid.acquistiapi.dto.test;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import io.swagger.annotations.ApiModel;
import it.csi.gescovid.acquistiapi.dto.test.Widget;

import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class Screen   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String cod = null;
  private List<Widget> widgets = new ArrayList<Widget>();

  /**
   * codice univoco dell&#39;elemento
   **/
  

  @JsonProperty("cod") 
 
  public String getCod() {
    return cod;
  }
  public void setCod(String cod) {
    this.cod = cod;
  }

  /**
   * elenco dei widget della schermata, con abilitazioni
   **/
  

  @JsonProperty("widgets") 
 
  public List<Widget> getWidgets() {
    return widgets;
  }
  public void setWidgets(List<Widget> widgets) {
    this.widgets = widgets;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Screen screen = (Screen) o;
    return Objects.equals(cod, screen.cod) &&
        Objects.equals(widgets, screen.widgets);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cod, widgets);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Screen {\n");
    
    sb.append("    cod: ").append(toIndentedString(cod)).append("\n");
    sb.append("    widgets: ").append(toIndentedString(widgets)).append("\n");
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

