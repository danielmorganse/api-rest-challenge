package cl.tenpo.rest.api.challenge.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class ApiCallRecord {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(length = Integer.MAX_VALUE)
    private String requestMethod;

    @Column(length = Integer.MAX_VALUE)
    private String requestEndpoint;

    @Column(length = Integer.MAX_VALUE)
    private String requestParams;

    @Column(length = Integer.MAX_VALUE)
    private String requestBody;

    private int responseStatus;

    @Column(length = Integer.MAX_VALUE)
    private String responseBody;

    private Date date;

}
