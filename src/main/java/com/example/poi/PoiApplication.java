package com.example.poi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class PoiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PoiApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		List<Poi> poisByName = poiRepository.findByName("清勉山");
//		System.out.println("Query Result Size: " + poisByName.size());
//		if (!poisByName.isEmpty()) {
//			Poi poi = poisByName.get(0);
//			System.out.println("First POI ID: " + poi.getId());
//			System.out.println("First POI Coordinates: " + poi.getGeometry().getCoordinates());
//			Poi.Properties properties = poi.getProperties();
//			if (properties != null) {
//				String name = properties.getName();
//				System.out.println("POI Name: " + name);
//			} else {
//				System.out.println("Properties object is null.");
//			}
//		} else {
//			System.out.println("No POIs found.");
//		}
//	}

}
