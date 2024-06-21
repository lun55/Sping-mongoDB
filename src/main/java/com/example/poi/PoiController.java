package com.example.poi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(produces = "application/json")
public class PoiController {
    @Autowired // 自动注入
    private PoiServer poiServer;

    // Get 方法
    // 获取全部的poi
    @GetMapping("/all")
    public List<Poi> findAllPoi() {
        return poiServer.findAllPoi();
    }

    // 根据id查找poi
    @GetMapping("/{id}")
    public Poi findPoiById(@PathVariable String id) {
        return poiServer.findPoiById(id);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity findPoiByName(@PathVariable String name) {
        if (name == null || name.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Poi> pois = poiServer.findPoiByName(name);
        if (pois == null || pois.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pois, HttpStatus.OK);
    }

    @GetMapping("/area_cat/{area}/{cat}")
    public ResponseEntity findPoiByAreaAndCat(@PathVariable String area, @PathVariable String cat) {

        List<Poi> pois = poiServer.findPoiByAreaAndCat(area, cat);
        if (pois == null || pois.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pois, HttpStatus.OK);
    }

    @GetMapping("/near/{location}/{maxDistance}")
    public ResponseEntity findByLocationNear(@PathVariable String location, @PathVariable double maxDistance) {
        // 解析 location 字符串为经度和纬度
        String[] coordinates = location.split(",");
        if (coordinates.length != 2) {
            return new ResponseEntity<>("Invalid location format. Expected format: {longitude},{latitude}", HttpStatus.BAD_REQUEST);
        }
        double longitude = Double.parseDouble(coordinates[0]);
        double latitude = Double.parseDouble(coordinates[1]);
        System.out.println(longitude);
        System.out.println(latitude);

        List<Poi> pois = poiServer.findByLocationNear(longitude, latitude, maxDistance);
        if (pois == null || pois.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pois, HttpStatus.OK);
    }


}