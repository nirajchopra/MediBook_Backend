package com.mbp.mediBook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.mbp.mediBook.repository")
public class MedicineBookingApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(MedicineBookingApplication.class, args);
        System.out.println("🚀 Medicine Booking Backend Started Successfully!");
        System.out.println("📍 Server running on: http://localhost:8080/api");
        System.out.println("📊 MongoDB Database: medibook");
    }
}