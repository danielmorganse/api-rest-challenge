package cl.tenpo.rest.api.challenge.dtos;

import java.util.Objects;
import cl.tenpo.rest.api.challenge.dtos.HistoryRecord;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import org.openapitools.jackson.nullable.JsonNullable;
import io.swagger.configuration.NotUndefined;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * PaginatedHistory
 */
@Validated
@NotUndefined



public class PaginatedHistory   {
  @JsonProperty("total")

  @JsonInclude(JsonInclude.Include.NON_ABSENT)  // Exclude from JSON if absent
  @JsonSetter(nulls = Nulls.FAIL)    // FAIL setting if the value is null
  private Integer total = null;

  @JsonProperty("records")
  @Valid
  private List<HistoryRecord> records = null;

  public PaginatedHistory total(Integer total) { 

    this.total = total;
    return this;
  }

  /**
   * Get total
   * @return total
   **/
  
  @Schema(description = "")
  
  public Integer getTotal() {  
    return total;
  }



  public void setTotal(Integer total) { 
    this.total = total;
  }

  public PaginatedHistory records(List<HistoryRecord> records) { 

    this.records = records;
    return this;
  }

  public PaginatedHistory addRecordsItem(HistoryRecord recordsItem) {
    if (this.records == null) {
      this.records = new ArrayList<HistoryRecord>();
    }
    this.records.add(recordsItem);
    return this;
  }

  /**
   * Get records
   * @return records
   **/
  
  @Schema(description = "")
  @Valid
  public List<HistoryRecord> getRecords() {  
    return records;
  }



  public void setRecords(List<HistoryRecord> records) { 
    this.records = records;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaginatedHistory paginatedHistory = (PaginatedHistory) o;
    return Objects.equals(this.total, paginatedHistory.total) &&
        Objects.equals(this.records, paginatedHistory.records);
  }

  @Override
  public int hashCode() {
    return Objects.hash(total, records);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaginatedHistory {\n");
    
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
    sb.append("    records: ").append(toIndentedString(records)).append("\n");
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
