# MediBook_Backend

# 🚀 SPRING BOOT + MONGODB BACKEND IMPLEMENTATION

## 📂 COMPLETE BACKEND STRUCTURE

```
backend-springboot/
├── pom.xml                                    ✅ CREATED
├── src/
│   ├── main/
│   │   ├── java/com/medibook/
│   │   │   ├── MedicineBookingApplication.java      ⚠️ Main Application
│   │   │   │
│   │   │   ├── 📁 config/                           Configuration
│   │   │   │   ├── CorsConfig.java
│   │   │   │   ├── MongoConfig.java
│   │   │   │   └── SecurityConfig.java
│   │   │   │
│   │   │   ├── 📁 model/                            MongoDB Entities
│   │   │   │   ├── User.java
│   │   │   │   ├── Medicine.java
│   │   │   │   ├── Order.java
│   │   │   │   ├── Store.java
│   │   │   │   └── enums/
│   │   │   │       ├── Role.java
│   │   │   │       └── OrderStatus.java
│   │   │   │
│   │   │   ├── 📁 repository/                       MongoDB Repositories
│   │   │   │   ├── UserRepository.java
│   │   │   │   ├── MedicineRepository.java
│   │   │   │   ├── OrderRepository.java
│   │   │   │   └── StoreRepository.java
│   │   │   │
│   │   │   ├── 📁 service/                          Business Logic
│   │   │   │   ├── AuthService.java
│   │   │   │   ├── UserService.java
│   │   │   │   ├── MedicineService.java
│   │   │   │   ├── OrderService.java
│   │   │   │   ├── StoreService.java
│   │   │   │   └── impl/
│   │   │   │       ├── AuthServiceImpl.java
│   │   │   │       ├── UserServiceImpl.java
│   │   │   │       ├── MedicineServiceImpl.java
│   │   │   │       ├── OrderServiceImpl.java
│   │   │   │       └── StoreServiceImpl.java
│   │   │   │
│   │   │   ├── 📁 controller/                       REST Controllers
│   │   │   │   ├── AuthController.java
│   │   │   │   ├── UserController.java
│   │   │   │   ├── MedicineController.java
│   │   │   │   ├── OrderController.java
│   │   │   │   ├── StoreController.java
│   │   │   │   └── AdminController.java
│   │   │   │
│   │   │   ├── 📁 dto/                              Data Transfer Objects
│   │   │   │   ├── request/
│   │   │   │   │   ├── LoginRequest.java
│   │   │   │   │   ├── RegisterRequest.java
│   │   │   │   │   ├── MedicineRequest.java
│   │   │   │   │   └── OrderRequest.java
│   │   │   │   └── response/
│   │   │   │       ├── AuthResponse.java
│   │   │   │       ├── MessageResponse.java
│   │   │   │       └── PaginatedResponse.java
│   │   │   │
│   │   │   ├── 📁 security/                         Security Components
│   │   │   │   ├── JwtAuthenticationFilter.java
│   │   │   │   ├── JwtTokenProvider.java
│   │   │   │   ├── CustomUserDetails.java
│   │   │   │   └── CustomUserDetailsService.java
│   │   │   │
│   │   │   └── 📁 exception/                        Exception Handling
│   │   │       ├── GlobalExceptionHandler.java
│   │   │       ├── ResourceNotFoundException.java
│   │   │       └── BadRequestException.java
│   │   │
│   │   └── resources/
│   │       ├── application.properties               ✅ CREATED
│   │       └── application-prod.properties
│   │
│   └── test/
│       └── java/com/medibook/
│           └── MedicineBookingApplicationTests.java
│
└── README.md
```

---

## 🎯 COMPLETE CODE IMPLEMENTATION

### 1. Main Application Class

**File:** `src/main/java/com/medibook/MedicineBookingApplication.java`

```java
package com.medibook;

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
```

---

## 📦 MODEL CLASSES (MongoDB Entities)

### 1. User Entity

**File:** `src/main/java/com/medibook/model/User.java`

```java
package com.medibook.model;

import com.medibook.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {
    
    @Id
    private String id;
    
    @Indexed(unique = true)
    private String email;
    
    private String password;
    
    private String fullName;
    
    private String phoneNumber;
    
    private Role role = Role.USER;
    
    private boolean isActive = true;
    
    private String profileImage;
    
    @CreatedDate
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
```

### 2. Medicine Entity

**File:** `src/main/java/com/medibook/model/Medicine.java`

```java
package com.medibook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "medicines")
public class Medicine {
    
    @Id
    private String id;
    
    @Indexed
    private String storeId;
    
    private String name;
    
    private String description;
    
    private String category;
    
    private String manufacturer;
    
    private Double price;
    
    private Double discountPrice;
    
    private Integer stock;
    
    private boolean inStock = true;
    
    private boolean prescriptionRequired = false;
    
    private String dosage;
    
    private String sideEffects;
    
    private String imageUrl;
    
    private LocalDate expiryDate;
    
    private Double rating = 0.0;
    
    private Integer reviewCount = 0;
    
    @CreatedDate
    private LocalDateTime createdAt;
}
```

### 3. Order Entity

**File:** `src/main/java/com/medibook/model/Order.java`

```java
package com.medibook.model;

import com.medibook.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "orders")
public class Order {
    
    @Id
    private String id;
    
    @Indexed
    private String orderNumber;
    
    @Indexed
    private String userId;
    
    @Indexed
    private String storeId;
    
    private String storeName;
    
    private String storeAddress;
    
    private String customerName;
    
    private String customerPhone;
    
    private List<OrderItem> items;
    
    private Double subtotal;
    
    private Double discount;
    
    private Double total;
    
    private OrderStatus status = OrderStatus.PENDING;
    
    private LocalDate pickupDate;
    
    private String pickupTime;
    
    @CreatedDate
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    private LocalDateTime updatedAt;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderItem {
        private String medicineId;
        private String medicineName;
        private Integer quantity;
        private Double price;
    }
}
```

### 4. Store Entity

**File:** `src/main/java/com/medibook/model/Store.java`

```java
package com.medibook.model;

import com.medibook.model.enums.StoreStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "stores")
public class Store {
    
    @Id
    private String id;
    
    @Indexed(unique = true)
    private String storeIdCode;
    
    @Indexed
    private String userId;
    
    private String storeName;
    
    private String ownerName;
    
    private String email;
    
    private String phone;
    
    private String address;
    
    private String city;
    
    private String state;
    
    private String pincode;
    
    private String licenseNumber;
    
    private LocalTime openingTime;
    
    private LocalTime closingTime;
    
    private StoreStatus status = StoreStatus.PENDING;
    
    private boolean isActive = true;
    
    @CreatedDate
    private LocalDateTime createdAt;
}
```

---

## 🔧 ENUMS

### Role Enum

**File:** `src/main/java/com/medibook/model/enums/Role.java`

```java
package com.medibook.model.enums;

public enum Role {
    USER,
    STORE,
    ADMIN
}
```

### Order Status Enum

**File:** `src/main/java/com/medibook/model/enums/OrderStatus.java`

```java
package com.medibook.model.enums;

public enum OrderStatus {
    PENDING,
    ACCEPTED,
    REJECTED,
    READY,
    COMPLETED,
    CANCELLED
}
```

### Store Status Enum

**File:** `src/main/java/com/medibook/model/enums/StoreStatus.java`

```java
package com.medibook.model.enums;

public enum StoreStatus {
    PENDING,
    APPROVED,
    REJECTED
}
```

---

## 📚 REPOSITORY INTERFACES

### 1. User Repository

**File:** `src/main/java/com/medibook/repository/UserRepository.java`

```java
package com.medibook.repository;

import com.medibook.model.User;
import com.medibook.model.enums.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    
    Optional<User> findByEmail(String email);
    
    Boolean existsByEmail(String email);
    
    List<User> findByRole(Role role);
    
    List<User> findByIsActive(boolean isActive);
    
    long countByRole(Role role);
}
```

### 2. Medicine Repository

**File:** `src/main/java/com/medibook/repository/MedicineRepository.java`

```java
package com.medibook.repository;

import com.medibook.model.Medicine;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineRepository extends MongoRepository<Medicine, String> {
    
    List<Medicine> findByStoreId(String storeId);
    
    List<Medicine> findByCategory(String category);
    
    List<Medicine> findByNameContainingIgnoreCase(String name);
    
    List<Medicine> findByStoreIdAndInStock(String storeId, boolean inStock);
    
    @Query("{'$or': [{'name': {'$regex': ?0, '$options': 'i'}}, {'category': {'$regex': ?0, '$options': 'i'}}]}")
    List<Medicine> searchMedicines(String keyword);
    
    List<Medicine> findByStoreIdAndStockLessThan(String storeId, int stock);
    
    long countByStoreId(String storeId);
}
```

### 3. Order Repository

**File:** `src/main/java/com/medibook/repository/OrderRepository.java`

```java
package com.medibook.repository;

import com.medibook.model.Order;
import com.medibook.model.enums.OrderStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    
    List<Order> findByUserId(String userId);
    
    List<Order> findByStoreId(String storeId);
    
    List<Order> findByStatus(OrderStatus status);
    
    List<Order> findByStoreIdAndStatus(String storeId, OrderStatus status);
    
    Optional<Order> findByOrderNumber(String orderNumber);
    
    long countByStoreIdAndStatus(String storeId, OrderStatus status);
    
    List<Order> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}
```

### 4. Store Repository

**File:** `src/main/java/com/medibook/repository/StoreRepository.java`

```java
package com.medibook.repository;

import com.medibook.model.Store;
import com.medibook.model.enums.StoreStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends MongoRepository<Store, String> {
    
    Optional<Store> findByUserId(String userId);
    
    Optional<Store> findByStoreIdCode(String storeIdCode);
    
    List<Store> findByStatus(StoreStatus status);
    
    List<Store> findByCity(String city);
    
    Boolean existsByStoreIdCode(String storeIdCode);
    
    long countByStatus(StoreStatus status);
}
```

---

## 🎯 DTOs (Data Transfer Objects)

### Request DTOs

**File:** `src/main/java/com/medibook/dto/request/LoginRequest.java`

```java
package com.medibook.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    
    @NotBlank(message = "Password is required")
    private String password;
}
```

**File:** `src/main/java/com/medibook/dto/request/RegisterRequest.java`

```java
package com.medibook.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
    
    @NotBlank(message = "Full name is required")
    @Size(min = 3, message = "Name must be at least 3 characters")
    private String fullName;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    
    @NotBlank(message = "Phone number is required")
    @Size(min = 10, max = 10, message = "Phone must be 10 digits")
    private String phoneNumber;
    
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
}
```

**File:** `src/main/java/com/medibook/dto/request/MedicineRequest.java`

```java
package com.medibook.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MedicineRequest {
    
    @NotBlank(message = "Medicine name is required")
    private String name;
    
    private String description;
    
    @NotBlank(message = "Category is required")
    private String category;
    
    @NotBlank(message = "Manufacturer is required")
    private String manufacturer;
    
    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private Double price;
    
    private Double discountPrice;
    
    @NotNull(message = "Stock is required")
    private Integer stock;
    
    private boolean prescriptionRequired;
    
    private String dosage;
    
    private String imageUrl;
    
    @NotNull(message = "Expiry date is required")
    private LocalDate expiryDate;
}
```

### Response DTOs

**File:** `src/main/java/com/medibook/dto/response/AuthResponse.java`

```java
package com.medibook.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String type = "Bearer";
    private String id;
    private String email;
    private String fullName;
    private String role;
    
    public AuthResponse(String token, String id, String email, String fullName, String role) {
        this.token = token;
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.role = role;
    }
}
```

**File:** `src/main/java/com/medibook/dto/response/MessageResponse.java`

```java
package com.medibook.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageResponse {
    private boolean success;
    private String message;
    private Object data;
    
    public MessageResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
```

---

## 🔐 SECURITY CONFIGURATION

### JWT Token Provider

**File:** `src/main/java/com/medibook/security/JwtTokenProvider.java`

```java
package com.medibook.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {
    
    @Value("${jwt.secret}")
    private String jwtSecret;
    
    @Value("${jwt.expiration}")
    private long jwtExpiration;
    
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }
    
    public String generateToken(Authentication authentication) {
        CustomUserDetails userPrincipal = (CustomUserDetails) authentication.getPrincipal();
        
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiration);
        
        return Jwts.builder()
                .setSubject(userPrincipal.getId())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSigningKey())
                .compact();
    }
    
    public String getUserIdFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        
        return claims.getSubject();
    }
    
    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(authToken);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
```

### Security Configuration

**File:** `src/main/java/com/medibook/config/SecurityConfig.java`

```java
package com.medibook.config;

import com.medibook.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> 
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/store/**").hasRole("STORE")
                .requestMatchers("/user/**").hasAnyRole("USER", "STORE", "ADMIN")
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
```

---

## 🎮 CONTROLLERS

### Auth Controller

**File:** `src/main/java/com/medibook/controller/AuthController.java`

```java
package com.medibook.controller;

import com.medibook.dto.request.LoginRequest;
import com.medibook.dto.request.RegisterRequest;
import com.medibook.dto.response.AuthResponse;
import com.medibook.dto.response.MessageResponse;
import com.medibook.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "${cors.allowed-origins}")
public class AuthController {
    
    private final AuthService authService;
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
    
    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(@Valid @RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok(new MessageResponse(true, "Registration successful"));
    }
    
    @PostMapping("/logout")
    public ResponseEntity<MessageResponse> logout() {
        return ResponseEntity.ok(new MessageResponse(true, "Logged out successfully"));
    }
}
```

### Medicine Controller

**File:** `src/main/java/com/medibook/controller/MedicineController.java`

```java
package com.medibook.controller;

import com.medibook.dto.request.MedicineRequest;
import com.medibook.dto.response.MessageResponse;
import com.medibook.model.Medicine;
import com.medibook.service.MedicineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicines")
@RequiredArgsConstructor
@CrossOrigin(origins = "${cors.allowed-origins}")
public class MedicineController {
    
    private final MedicineService medicineService;
    
    @GetMapping
    public ResponseEntity<List<Medicine>> getAllMedicines() {
        return ResponseEntity.ok(medicineService.getAllMedicines());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Medicine> getMedicine(@PathVariable String id) {
        return ResponseEntity.ok(medicineService.getMedicineById(id));
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Medicine>> searchMedicines(@RequestParam String keyword) {
        return ResponseEntity.ok(medicineService.searchMedicines(keyword));
    }
    
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Medicine>> getMedicinesByCategory(@PathVariable String category) {
        return ResponseEntity.ok(medicineService.getMedicinesByCategory(category));
    }
    
    @PostMapping
    @PreAuthorize("hasRole('STORE')")
    public ResponseEntity<Medicine> addMedicine(@Valid @RequestBody MedicineRequest request) {
        return ResponseEntity.ok(medicineService.addMedicine(request));
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('STORE')")
    public ResponseEntity<Medicine> updateMedicine(
            @PathVariable String id,
            @Valid @RequestBody MedicineRequest request) {
        return ResponseEntity.ok(medicineService.updateMedicine(id, request));
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('STORE')")
    public ResponseEntity<MessageResponse> deleteMedicine(@PathVariable String id) {
        medicineService.deleteMedicine(id);
        return ResponseEntity.ok(new MessageResponse(true, "Medicine deleted successfully"));
    }
}
```

---

## 🚀 QUICK START COMMANDS

### 1. Setup MongoDB
```bash
# Install MongoDB (Ubuntu/Debian)
sudo apt-get install -y mongodb-org

# Start MongoDB
sudo systemctl start mongod
sudo systemctl enable mongod

# Or using Docker
docker run -d -p 27017:27017 --name mongodb mongo:latest
```

### 2. Build & Run Backend
```bash
cd backend-springboot

# Build with Maven
mvn clean install

# Run application
mvn spring-boot:run

# Or run JAR file
java -jar target/medicine-booking-backend-1.0.0.jar
```

### 3. Test API
```bash
# Health Check
curl http://localhost:8080/api/actuator/health

# Register User
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"fullName":"John Doe","email":"john@test.com","phoneNumber":"1234567890","password":"password123"}'

# Login
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"john@test.com","password":"password123"}'
```

---

## 📊 API ENDPOINTS

### Authentication
```
POST   /api/auth/register    - Register new user
POST   /api/auth/login       - Login user
POST   /api/auth/logout      - Logout user
```

### Medicines
```
GET    /api/medicines                      - Get all medicines
GET    /api/medicines/{id}                 - Get medicine by ID
GET    /api/medicines/search?keyword=para  - Search medicines
GET    /api/medicines/category/{category}  - Get by category
POST   /api/medicines                      - Add medicine (STORE)
PUT    /api/medicines/{id}                 - Update medicine (STORE)
DELETE /api/medicines/{id}                 - Delete medicine (STORE)
```

### Orders
```
GET    /api/orders           - Get user orders
GET    /api/orders/{id}      - Get order details
POST   /api/orders           - Create order
PUT    /api/orders/{id}      - Update order status (STORE)
DELETE /api/orders/{id}      - Cancel order
```

### Admin
```
GET    /api/admin/users      - Get all users
GET    /api/admin/stores     - Get all stores
POST   /api/admin/stores/{id}/approve    - Approve store
POST   /api/admin/stores/{id}/reject     - Reject store
GET    /api/admin/stats      - Get platform statistics
```

---

## 🔧 ANGULAR INTEGRATION

### Update Angular Service

**File:** `src/app/user/services/medicine.service.ts`

```typescript
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';

export class MedicineService {
  private apiUrl = `${environment.apiUrl}/medicines`;

  constructor(private http: HttpClient) {}

  getAllMedicines(): Observable<Medicine[]> {
    return this.http.get<Medicine[]>(this.apiUrl);
  }

  searchMedicines(keyword: string): Observable<Medicine[]> {
    return this.http.get<Medicine[]>(`${this.apiUrl}/search?keyword=${keyword}`);
  }
}
```

**File:** `src/environments/environment.ts`

```typescript
export const environment = {
  production: false,
  apiUrl: 'http://localhost:8080/api'
};
```

---
