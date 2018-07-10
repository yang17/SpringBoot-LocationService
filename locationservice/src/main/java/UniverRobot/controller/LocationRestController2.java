package UniverRobot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import UniverRobot.model.Location;
import UniverRobot.model.LocationRepository;

import java.util.List;

public class LocationRestController2 {

    @RestController
    public class LocationRestController{

        private LocationRepository respository;

        @Autowired
        public LocationRestController (LocationRepository respository) {
            this.respository = respository;
        }

        @RequestMapping(value = "/upload", method = RequestMethod.POST)
        public void upload(@RequestBody List<Location> locations) {
            this.respository.save(locations);
        }

        @RequestMapping(value = "/delete", method = RequestMethod.POST)
        public void delete() {
            this.respository.deleteAll();
        }

        @RequestMapping(value = "/fleet/{movementType}", method = RequestMethod.GET)
        public Page<Location> findByMovementType() {

        }

    }

}
