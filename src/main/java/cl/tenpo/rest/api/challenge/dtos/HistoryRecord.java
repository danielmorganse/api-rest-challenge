package cl.tenpo.rest.api.challenge.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import io.swagger.configuration.NotUndefined;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * HistoryRecord
 */
@Validated
@NotUndefined



public class HistoryRecord   {
  @JsonProperty("timestamp")

  @JsonInclude(JsonInclude.Include.NON_ABSENT)  // Exclude from JSON if absent
  @JsonSetter(nulls = Nulls.FAIL)    // FAIL setting if the value is null
  private Date timestamp = null;

  @JsonProperty("endpoint")

  @JsonInclude(JsonInclude.Include.NON_ABSENT)  // Exclude from JSON if absent
  @JsonSetter(nulls = Nulls.FAIL)    // FAIL setting if the value is null
  private String endpoint = null;

  @JsonProperty("urlParameters")
  @Valid
  private Map<String, String> urlParameters = null;
  @JsonProperty("requestBody")

  @JsonInclude(JsonInclude.Include.NON_ABSENT)  // Exclude from JSON if absent
  @JsonSetter(nulls = Nulls.FAIL)    // FAIL setting if the value is null
  private String requestBody = null;

  @JsonProperty("response")

  @JsonInclude(JsonInclude.Include.NON_ABSENT)  // Exclude from JSON if absent
  @JsonSetter(nulls = Nulls.FAIL)    // FAIL setting if the value is null
  private String response = null;


  public HistoryRecord timestamp(Date timestamp) { 

    this.timestamp = timestamp;
    return this;
  }

  /**
   * Get timestamp
   * @return timestamp
   **/
  
  @Schema(description = "")
  
@Valid
  public Date getTimestamp() {  
    return timestamp;
  }



  public void setTimestamp(Date timestamp) { 
    this.timestamp = timestamp;
  }

  public HistoryRecord endpoint(String endpoint) { 

    this.endpoint = endpoint;
    return this;
  }

  /**
   * Get endpoint
   * @return endpoint
   **/
  
  @Schema(description = "")
  
  public String getEndpoint() {  
    return endpoint;
  }



  public void setEndpoint(String endpoint) { 
    this.endpoint = endpoint;
  }

  public HistoryRecord urlParameters(Map<String, String> urlParameters) { 

    this.urlParameters = urlParameters;
    return this;
  }

  public HistoryRecord putUrlParametersItem(String key, String urlParametersItem) {
    if (this.urlParameters == null) {
      this.urlParameters = new HashMap<String, String>();
    }
    this.urlParameters.put(key, urlParametersItem);
    return this;
  }

  /**
   * Get urlParameters
   * @return urlParameters
   **/
  
  @Schema(description = "")
  
  public Map<String, String> getUrlParameters() {  
    return urlParameters;
  }



  public void setUrlParameters(Map<String, String> urlParameters) { 
    this.urlParameters = urlParameters;
  }

  public HistoryRecord requestBody(String requestBody) { 

    this.requestBody = requestBody;
    return this;
  }

  /**
   * cuerpo de respuesta serializada como string
   * @return requestBody
   **/
  
  @Schema(description = "cuerpo de respuesta serializada como string")
  
  public String getRequestBody() {  
    return requestBody;
  }



  public void setRequestBody(String requestBody) { 
    this.requestBody = requestBody;
  }

  public HistoryRecord response(String response) { 

    this.response = response;
    return this;
  }

  /**
   * cuerpo de respuesta serializada como string
   * @return response
   **/
  
  @Schema(description = "cuerpo de respuesta serializada como string")
  
  public String getResponse() {  
    return response;
  }



  public void setResponse(String response) { 
    this.response = response;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HistoryRecord historyRecord = (HistoryRecord) o;
    return Objects.equals(this.timestamp, historyRecord.timestamp) &&
        Objects.equals(this.endpoint, historyRecord.endpoint) &&
        Objects.equals(this.urlParameters, historyRecord.urlParameters) &&
        Objects.equals(this.requestBody, historyRecord.requestBody) &&
        Objects.equals(this.response, historyRecord.response);
  }

  @Override
  public int hashCode() {
    return Objects.hash(timestamp, endpoint, urlParameters, requestBody, response);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class HistoryRecord {\n");
    
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
    sb.append("    endpoint: ").append(toIndentedString(endpoint)).append("\n");
    sb.append("    urlParameters: ").append(toIndentedString(urlParameters)).append("\n");
    sb.append("    requestBody: ").append(toIndentedString(requestBody)).append("\n");
    sb.append("    response: ").append(toIndentedString(response)).append("\n");
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
