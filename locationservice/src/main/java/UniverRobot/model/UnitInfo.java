package UniverRobot.model;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@Embeddable//表示这个东西可以embeddable的
@RequiredArgsConstructor
public class UnitInfo {

    private final String unitVin;
    private String engineMake;
    private String customerName;
    private String unitNumber;

    @SuppressWarnings("unused")
    UnitInfo(String vin) {
        this.unitVin = "";
    }

    public String getUnitVin() {
        return this.unitVin;
    }
}
