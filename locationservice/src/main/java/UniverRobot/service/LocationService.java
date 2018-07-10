package UniverRobot.service;

import org.springframework.data.domain.Page;
import UniverRobot.model.Location;

import java.awt.print.Pageable;
import java.util.List;

public interface LocationService {

        List<Location> saveCarLocations(List<Location> carLocations);

        void deleteAll();

        Page<Location> findByMovementType(String movementType, Pageable pageable);

        Page<Location> findByVin(String vin, Pageable pageable);

}
