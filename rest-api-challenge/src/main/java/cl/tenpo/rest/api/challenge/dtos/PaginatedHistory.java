package cl.tenpo.rest.api.challenge.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import io.swagger.configuration.NotUndefined;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.Builder;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * PaginatedHistory
 */
@Validated
@NotUndefined


@Builder
public class PaginatedHistory   {
  @JsonProperty("total")

  @JsonInclude(JsonInclude.Include.NON_ABSENT)  // Exclude from JSON if absent
  @JsonSetter(nulls = Nulls.FAIL)    // FAIL setting if the value is null
  private Long total = null;

  @JsonProperty("records")
  @Valid
  private List<HistoryRecord> records = null;

  public PaginatedHistory total(Long total) {

    this.total = total;
    return this;
  }

  /**
   * Get total
   * @return total
   **/
  
  @Schema(description = "")
  
  public Long getTotal() {
    return total;
  }



  public void setTotal(Long total) {
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
  public boolean equals(Object o) {
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
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
