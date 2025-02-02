package cl.tenpo.rest.api.challenge.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import io.swagger.configuration.NotUndefined;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CalculateBody
 */
@Validated
@NotUndefined



public class CalculateBody   {
  @JsonProperty("num1")

  @JsonInclude(JsonInclude.Include.NON_ABSENT)  // Exclude from JSON if absent
  @JsonSetter(nulls = Nulls.FAIL)    // FAIL setting if the value is null
  private Double num1 = null;

  @JsonProperty("num2")

  @JsonInclude(JsonInclude.Include.NON_ABSENT)  // Exclude from JSON if absent
  @JsonSetter(nulls = Nulls.FAIL)    // FAIL setting if the value is null
  private Double num2 = null;


  public CalculateBody num1(Double num1) { 

    this.num1 = num1;
    return this;
  }

  /**
   * Get num1
   * @return num1
   **/
  
  @Schema(example = "5", description = "")
  
  public Double getNum1() {  
    return num1;
  }



  public void setNum1(Double num1) { 
    this.num1 = num1;
  }

  public CalculateBody num2(Double num2) { 

    this.num2 = num2;
    return this;
  }

  /**
   * Get num2
   * @return num2
   **/
  
  @Schema(example = "5", description = "")
  
  public Double getNum2() {  
    return num2;
  }



  public void setNum2(Double num2) { 
    this.num2 = num2;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CalculateBody calculateBody = (CalculateBody) o;
    return Objects.equals(this.num1, calculateBody.num1) &&
        Objects.equals(this.num2, calculateBody.num2);
  }

  @Override
  public int hashCode() {
    return Objects.hash(num1, num2);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CalculateBody {\n");
    
    sb.append("    num1: ").append(toIndentedString(num1)).append("\n");
    sb.append("    num2: ").append(toIndentedString(num2)).append("\n");
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
