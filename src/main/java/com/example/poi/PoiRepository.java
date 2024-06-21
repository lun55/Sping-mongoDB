package com.example.poi;
import org.springframework.data.geo.Distance; // 距离
import org.springframework.data.geo.Point; // 点
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
/*
    API 实现发生在存储库中。它充当模型和数据库之间的链接，并具有 CRUD 操作的所有方法。
 */

@Repository
public interface PoiRepository extends MongoRepository<Poi, String> {

    // 用注释@Query指定需要查询的参数，即用于筛选查询的字段
    @Query("{ 'properties.名称': ?0 }") // properties.名称是在数据库中的键结构
    List<Poi> findByName(String name);

    // 获取全部的poi
    List<Poi> findAll();

    // 根据区域和类型查找poi
    @Query("{'properties.区域': ?0, 'properties.中类': ?1 }")
    List<Poi> findPoiByAreaAndCat(String area, String cat);

    //  根据坐标查询周边的poi
    List<Poi> findByGeometryNear(Point location, Distance distance);
}