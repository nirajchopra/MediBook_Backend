package com.mbp.mediBook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class MedicineBookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedicineBookingApplication.class, args);
        System.out.println("🚀 Medicine Booking Backend Started Successfully!");
        System.out.println("📍 Server running on: http://localhost:8080/api");
        System.out.println("📊 MongoDB Database: medibook");
    }
}