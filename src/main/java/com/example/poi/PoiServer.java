package com.example.poi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.data.geo.Distance; // 距离
import org.springframework.data.geo.Point; // 点
import org.springframework.data.geo.Metrics;

@Service // 这是Spring的注解，表明这个类是一个服务类（Service）。Spring会将这个类作为一个Bean进行管理。
public class PoiServer {

    @Autowired // 将Spring容器中定义的Bean自动注入到需要它的类中
    private PoiRepository poiRepository;

    // 获取全部的poi
    public List<Poi> findAllPoi() {
        return poiRepository.findAll();
    }

    // 按id查找poi
    public Poi findPoiById(String id) {
        return poiRepository.findById(id).orElse(null);
    }

    // 按名称查找poi
    public List<Poi> findPoiByName(String name) {

        return poiRepository.findByName(name);
    }

    // 根据区域和类型查找poi
    public List<Poi> findPoiByAreaAndCat(String area, String cat) {
        return poiRepository.findPoiByAreaAndCat(area, cat);
    }

    // 根据坐标查询周边的poi
    public List<Poi> findByLocationNear(double longitude, double latitude, double maxDistance) {
        Point location = new Point(longitude, latitude);
        Distance distance = new Distance(maxDistance, Metrics.KILOMETERS);
        return poiRepository.findByGeometryNear(location, distance);
    }
}
