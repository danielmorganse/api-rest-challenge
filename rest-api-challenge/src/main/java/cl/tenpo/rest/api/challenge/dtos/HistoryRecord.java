package cl.tenpo.rest.api.challenge.dtos;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import org.springframework.validation.annotation.Validated;
import io.swagger.configuration.NotUndefined;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import jakarta.validation.Valid;

/**
 * HistoryRecord
 */
@Validated
@NotUndefined



public class HistoryRecord   {
  @JsonProperty("date")

  @JsonInclude(JsonInclude.Include.NON_ABSENT)  // Exclude from JSON if absent
  @JsonSetter(nulls = Nulls.FAIL)    // FAIL setting if the value is null
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSz", timezone = "America/Santiago")
  private Date date = null;

  @JsonProperty("requestMethod")

  @JsonInclude(JsonInclude.Include.NON_ABSENT)  // Exclude from JSON if absent
  @JsonSetter(nulls = Nulls.FAIL)    // FAIL setting if the value is null
  private String requestMethod = null;

  @JsonProperty("requestEndpoint")

  @JsonInclude(JsonInclude.Include.NON_ABSENT)  // Exclude from JSON if absent
  @JsonSetter(nulls = Nulls.FAIL)    // FAIL setting if the value is null
  private String requestEndpoint = null;

  @JsonProperty("requestParams")

  @JsonInclude(JsonInclude.Include.NON_ABSENT)  // Exclude from JSON if absent
  @JsonSetter(nulls = Nulls.FAIL)    // FAIL setting if the value is null
  private String requestParams = null;

  @JsonProperty("requestBody")

  @JsonInclude(JsonInclude.Include.NON_ABSENT)  // Exclude from JSON if absent
  @JsonSetter(nulls = Nulls.FAIL)    // FAIL setting if the value is null
  private String requestBody = null;

  @JsonProperty("responseStatus")

  @JsonInclude(JsonInclude.Include.NON_ABSENT)  // Exclude from JSON if absent
  @JsonSetter(nulls = Nulls.FAIL)    // FAIL setting if the value is null
  private Integer responseStatus = null;

  @JsonProperty("responseBody")

  @JsonInclude(JsonInclude.Include.NON_ABSENT)  // Exclude from JSON if absent
  @JsonSetter(nulls = Nulls.FAIL)    // FAIL setting if the value is null
  private String responseBody = null;


  public HistoryRecord date(Date date) {

    this.date = date;
    return this;
  }

  /**
   * Get date
   * @return date
   **/

  @Schema(description = "")

  @Valid
  public Date getDate() {
    return date;
  }



  public void setDate(Date date) {
    this.date = date;
  }

  public HistoryRecord requestMethod(String requestMethod) {

    this.requestMethod = requestMethod;
    return this;
  }

  /**
   * Get requestMethod
   * @return requestMethod
   **/

  @Schema(description = "")

  public String getRequestMethod() {
    return requestMethod;
  }



  public void setRequestMethod(String requestMethod) {
    this.requestMethod = requestMethod;
  }

  public HistoryRecord requestEndpoint(String requestEndpoint) {

    this.requestEndpoint = requestEndpoint;
    return this;
  }

  /**
   * Get requestEndpoint
   * @return requestEndpoint
   **/

  @Schema(description = "")

  public String getRequestEndpoint() {
    return requestEndpoint;
  }



  public void setRequestEndpoint(String requestEndpoint) {
    this.requestEndpoint = requestEndpoint;
  }

  public HistoryRecord requestParams(String requestParams) {

    this.requestParams = requestParams;
    return this;
  }

  /**
   * Get requestParams
   * @return requestParams
   **/

  @Schema(description = "")

  public String getRequestParams() {
    return requestParams;
  }



  public void setRequestParams(String requestParams) {
    this.requestParams = requestParams;
  }

  public HistoryRecord requestBody(String requestBody) {

    this.requestBody = requestBody;
    return this;
  }

  /**
   * cuerpo de petición serializada como string
   * @return requestBody
   **/

  @Schema(description = "cuerpo de petición serializada como string")

  public String getRequestBody() {
    return requestBody;
  }



  public void setRequestBody(String requestBody) {
    this.requestBody = requestBody;
  }

  public HistoryRecord responseStatus(Integer responseStatus) {

    this.responseStatus = responseStatus;
    return this;
  }

  /**
   * Get responseStatus
   * @return responseStatus
   **/

  @Schema(description = "")

  public Integer getResponseStatus() {
    return responseStatus;
  }



  public void setResponseStatus(Integer responseStatus) {
    this.responseStatus = responseStatus;
  }

  public HistoryRecord responseBody(String responseBody) {

    this.responseBody = responseBody;
    return this;
  }

  /**
   * cuerpo de respuesta serializada como string
   * @return responseBody
   **/

  @Schema(description = "cuerpo de respuesta serializada como string")

  public String getResponseBody() {
    return responseBody;
  }



  public void setResponseBody(String responseBody) {
    this.responseBody = responseBody;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HistoryRecord historyRecord = (HistoryRecord) o;
    return Objects.equals(this.date, historyRecord.date) &&
            Objects.equals(this.requestMethod, historyRecord.requestMethod) &&
            Objects.equals(this.requestEndpoint, historyRecord.requestEndpoint) &&
            Objects.equals(this.requestParams, historyRecord.requestParams) &&
            Objects.equals(this.requestBody, historyRecord.requestBody) &&
            Objects.equals(this.responseStatus, historyRecord.responseStatus) &&
            Objects.equals(this.responseBody, historyRecord.responseBody);
  }

  @Override
  public int hashCode() {
    return Objects.hash(date, requestMethod, requestEndpoint, requestParams, requestBody, responseStatus, responseBody);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class HistoryRecord {\n");

    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    requestMethod: ").append(toIndentedString(requestMethod)).append("\n");
    sb.append("    requestEndpoint: ").append(toIndentedString(requestEndpoint)).append("\n");
    sb.append("    requestParams: ").append(toIndentedString(requestParams)).append("\n");
    sb.append("    requestBody: ").append(toIndentedString(requestBody)).append("\n");
    sb.append("    responseStatus: ").append(toIndentedString(responseStatus)).append("\n");
    sb.append("    responseBody: ").append(toIndentedString(responseBody)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
