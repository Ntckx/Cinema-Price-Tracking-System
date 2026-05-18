# Cinema Ticket Pricing System
A backend system for cinema booking management and dynamic ticket pricing, built with Java Spring Boot.

## Features
- **Booking lifecycle management**: State Pattern enforces valid transitions: `PENDING → CONFIRMED → PAID → REFUNDED / CANCELLED`
- **Dynamic pricing engine**: Strategy Pattern applies discount layers in order: Coupon → On-Top (Member/Points) → Seasonal
- **RFC 7807 error responses**: Consistent problem detail contracts across all endpoints
- **Data seeding**: 4 movies and 10 movie rounds auto-seeded on startup

## Tech Stack
- Java 21, Spring Boot, Spring Data JPA
- MySQL, UUID primary keys
- JUnit, Mockito, MockMvc
- Jakarta Bean Validation

## Design Patterns

| Pattern  | Applied To                         |
|----------|------------------------------------|
| State    | Booking lifecycle transitions      |
| Strategy | Pluggable discount algorithms      |
| Factory  | State and strategy object creation |

## API Endpoints
### Bookings
| Method  | Endpoint                     | Description    |
|---------|------------------------------|----------------|
| `POST`  | `/api/bookings`              | Create booking |
| `GET`   | `/api/bookings/{id}`         | Get booking    |
| `PATCH` | `/api/bookings/{id}/confirm` | Confirm        |
| `PATCH` | `/api/bookings/{id}/pay`     | Pay            |
| `PATCH` | `/api/bookings/{id}/cancel`  | Cancel         |
| `PATCH` | `/api/bookings/{id}/refund`  | Refund         |

### Pricing
| Method | Endpoint                 | Description                          |
|--------|--------------------------|--------------------------------------|
| `POST` | `/api/pricing/calculate` | Calculate final price with discounts |

## Project Structure

```
src/main/java/com/ntck/cinematickettrackingsystem/
├── models/     # Domain entities + State Pattern
│   └── State/      # PendingState, ConfirmedState, PaidState, RefundedState, CancelledState
├── Bookings/       # Booking CRUD + lifecycle management
│   ├── Controller/
│   ├── Service/
│   └── dto/
├── Pricing/        # Strategy pricing engine
│   ├── Controller/
│   ├── Service/
│   ├── discountStrategy/       # Coupon, OnTop, Seasonal strategies
│   ├── Context/        # PricingContext
│   └── DiscountStrategyFactory.java
├── GlobalException/        # RFC 7807 centralized error handling
└── Config/     # DataSeeder
```


## Getting Started

```bash
git clone https://github.com/Ntckx/Cinema-Price-Tracking-System.git
cd Cinema-Price-Tracking-System
```

Configure `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/cinema_db
spring.datasource.username= your_username
spring.datasource.password= your_password
```

```bash
.\mvnw spring-boot:run
```
## Running Tests

```bash
.\mvnw test
```

