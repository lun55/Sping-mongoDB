package com.example.poi;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "poi") // 用于指定当前文档属于集合
public class Poi {

    @Id // 注解@Id表示当前id字段属于主键类型。
    private String id;

    // 数据类型，在Geojson中，该值固定
    private String type = "Feature";

    // 要素的属性
    private Properties properties;

    // 要素的几何形状
    private Geometry geometry;

    // 以下信息会被序列化输出
    // 获取id
    public String getId() {
        return id; // id
    }

    // 获取属性
    public Properties getProperties() {
        return properties; // name
    }

    // 获取几何形状
    public Geometry getGeometry() {
        return geometry; // coordinates
    }

    public static class Properties {

        @Field("名称") // 数据库中的键为“名称”
        private String name;

        @Field("区域")
        private String address;

        @Field("中类")
        private String cat;

        public String getName() {
            return name;
        }

        public String getAddress() {
            return address;
        }

        public String getCat() {
            return cat;
        }

        @Override
        public String toString() {
            return "Properties{name ='" + name + "'" +
                    "address = '"+address+"'" +"'}";
        }
    }
    public static class Geometry {
        @Field("type")
        private String geometryType = "Point";

        @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
        private double[] coordinates;

        // 获取点的坐标
        public double[] getCoordinates() {
            return coordinates;
        }

        @Override
        public String toString() {
            return "{coordinates ='" + coordinates + "'}";
        }
        // 省略了其他字段和 getter/setter 方法
    }
}