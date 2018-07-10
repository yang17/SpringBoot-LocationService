package UniverRobot.model;

import java.util.Date;

import javax.persistence.*;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;



@JsonInclude(JsonInclude.Include.NON_NULL)//object是以一个json的形式传输的。如果有哪个field没有被赋值的话，到底要怎么传输呢？这个annotation
// 的含义是，如果生成的key是null的话，就不会再json文件中出现。所有的json文件都是非null的文件
@Data//这个是用于自动生成getter 和 setter，是lombox来的
@RequiredArgsConstructor
@Entity//JPA 里面来的，JPA是java persistence abstaction，是java用于数据永久化的
@Table(name = "Location")//告诉系统，在数据库中，这个table是叫什么名字
public class Location {

    enum GpsStatus {
        EXCELLENT, OK, UNRELIABLE, BAD, NOFIX, UNKNOWN;
    }

    enum VehicleMovementType {
        STOPPED, IN_MOTION;

        public boolean isMoving() {
            return this != STOPPED;
        }
    }

    @Id//这个是java persistence自带的，可以实现ID在数据库中不重复不遗漏，并且可以自动生成
    @GeneratedValue//这个可以保证id不需要穿进去，而是generate出来的
    private Long id;//数据库中的primary key
    @Embedded
    @AttributeOverride(name = "engineMake", column = @Column(name = "unit_engine_make"))//当数据库中的名字和class中的名字不一样时，这个可以建议一个对应关系
    private final UnitInfo unitInfo;
    @Embedded//这个是java JPA中跟数据库相关的package，用于告诉数据库这个一个object
    @AttributeOverrides({
            @AttributeOverride(name = "fmi", column = @Column(name = "unit_fmi")),
            @AttributeOverride(name = "spn", column = @Column(name = "unit_spn"))})
    private UnitFault unitFault;
    private double latitude;
    private double longitude;
    private String heading;
    private double gpsSpeed;
    private GpsStatus gpsStatus;
    private double odometer;
    private double totalEngineTime;
    private double totalIdleTime;
    private double totalFuelUsage;
    private String address;
    private Date timestamp = new Date();
    private String tspProvider;
    private VehicleMovementType vehicleMovementType = VehicleMovementType.STOPPED;
    private String serviceType;
    // TODO: why is this not part of UnitFault?
    @Embedded
    private FaultCode faultCode;

    @SuppressWarnings("unused")
    private Location() {
        this.unitInfo = null;
    }

    @JsonCreator
    private Location(@JsonProperty("vin") String vin) {
        this.unitInfo = new UnitInfo(vin);
    }

    public String getVin() {
        return this.unitInfo == null ? null : this.unitInfo.getUnitVin();
    }

}
