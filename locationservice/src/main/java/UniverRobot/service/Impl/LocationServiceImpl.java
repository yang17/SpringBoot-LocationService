package UniverRobot.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import UniverRobot.model.Location;
import UniverRobot.model.LocationRepository;
import UniverRobot.service.LocationService;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private LocationRepository locationRepository;//这里做的是locationrepository这个bean的创立和注入

    @Autowired// 声明这个是一个bean，这个是一个factory desing pattern
    public LocationServiceImpl (LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Location> saveCarLocations(List<Location> carLocations) {
        return locationRepository.save(carLocations);
    }

    @Override
    public void deleteAll() {//这些method在location repository里面都定义好的
        locationRepository.deleteAll();
    }

    @Override
    public Page<Location> findByMovementType(String movementType, Pageable pageable) {
        return locationRepository.findByVehicleMovementType(Location.vehicle);
    }

    @Override
    public Page<Location> findByVin(String vin, Pageable pageable) {
        return null;
    }
}
