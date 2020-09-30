package it.csi.gescovid.acquistiapi.dto.test;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import io.swagger.annotations.ApiModel;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class Widget   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String cod = null;
  private Boolean enabled = null;
  private Boolean visible = null;

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
   * true se il widget e&#39; abilitato
   **/
  

  @JsonProperty("enabled") 
 
  public Boolean isEnabled() {
    return enabled;
  }
  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  /**
   * true se il widget e&#39; visibile
   **/
  

  @JsonProperty("visible") 
 
  public Boolean isVisible() {
    return visible;
  }
  public void setVisible(Boolean visible) {
    this.visible = visible;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Widget widget = (Widget) o;
    return Objects.equals(cod, widget.cod) &&
        Objects.equals(enabled, widget.enabled) &&
        Objects.equals(visible, widget.visible);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cod, enabled, visible);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Widget {\n");
    
    sb.append("    cod: ").append(toIndentedString(cod)).append("\n");
    sb.append("    enabled: ").append(toIndentedString(enabled)).append("\n");
    sb.append("    visible: ").append(toIndentedString(visible)).append("\n");
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

