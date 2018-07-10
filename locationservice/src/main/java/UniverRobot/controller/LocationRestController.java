package UniverRobot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import UniverRobot.model.Location;
import UniverRobot.model.LocationRepository;
import UniverRobot.service.Impl.LocationServiceImpl;


@RestController//这个来自于spring framework的web
public class LocationRestController {//这个模块是为了创建restful api

    private LocationRepository repository;
    private LocationServiceImpl locationService;

    @Autowired
    public LocationRestController(LocationRepository repository) {
        this.repository = repository;

    }

    //restful api 所需要的database的数据，可能和从database中反馈上来的数据不一致。所以需要一个中层，将database中来的数据wrap一下，以满足rest api中的要求


    @RequestMapping(value = "/upload", method = RequestMethod.POST)//实现location地理位置信息的upload. value对应的是url的信息
    @ResponseStatus(HttpStatus.CREATED)//返回http status，就是类似404，300，200之类的。可以manually改变status的状态
    public void upload(@RequestBody List<Location> cars) throws Exception {//第一个是input的数据,这个数据是来自request body里面,可以用@的方式告诉程序
        //save to database
        this.repository.save(cars);
    }

    //做的事情是，链接数据库，把数据库中不需要的东西删掉，要的东西存储起来. model中定义的是各种各样的数据类型
    //这里的location service是一个bean，以dependency injection的方式进入，所以是一个singleton
//    @RequestMapping(value = "/fleet", method = RequestMethod.POST)
//    @ResponseStatus(HttpStatus.CREATED)
//    public void upload(@RequestBody List<Location> locations) {
//        this.locationService.saveCarLocations(locations);
//    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void purge() {
        this.locationService.deleteAll();
    }

    @RequestMapping(value = "/fleet/{movementType}", method = RequestMethod.GET)
    public Page<Location> findByMovementType(@PathVariable String movementType, @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
        return this.locationService.findByMovementType(movementType, new PageRequest(page, size));
    }

    @RequestMapping(value = "/fleet/vin/{vin}", method = RequestMethod.GET)
    public Page<Location> findByVinNumber(@PathVariable String vin, @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
        return this.locationService.findByVin(vin, new PageRequest(page, size));
    }

}
