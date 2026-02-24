# Parking Lot Management System (Hibernate + PostgreSQL)

## Overview
A database-driven Parking Lot Management System built using Java, Hibernate ORM, and PostgreSQL.

This project demonstrates real-world backend design principles including:
- Object-Oriented Design (LLD)
- Hibernate ORM with entity relationships
- Transaction management
- HQL queries
- Clean layered architecture (Service + Repository)

---

## Architecture

The project follows a layered structure:


com.parkinglot
├── model (JPA Entities)
├── repository (Database access layer)
├── service (Business logic layer)
├── util (Hibernate configuration)
├── exception (Custom exceptions)
└── Main (Application entry point)


### Flow:
Main → Service → Repository → Hibernate Session → PostgreSQL

---

## Core Features

- Multi-floor parking structure
- Vehicle persistence using inheritance mapping
- Spot allocation and occupancy tracking
- Ticket generation with entry & exit timestamps
- Billing calculation at exit
- Revenue reporting using HQL
- Transaction management with commit/rollback
- Dirty checking via Hibernate

---

## Database Design

### Tables:
- `parking_lot`
- `parking_floor`
- `parking_spot`
- `vehicle`
- `ticket`

### Relationships:
- ParkingLot → OneToMany → ParkingFloor
- ParkingFloor → OneToMany → ParkingSpot
- Ticket → ManyToOne → Vehicle
- Ticket → ManyToOne → ParkingSpot

Inheritance Strategy:
- `Vehicle` and `ParkingSpot` use SINGLE_TABLE strategy with discriminator columns.

---

## Technologies Used

- Java 17+
- Hibernate ORM (JPA)
- PostgreSQL
- Maven
- HQL (Hibernate Query Language)

---

## How to Run

1. Create PostgreSQL database:

CREATE DATABASE parking_lot_db;


2. Update database credentials in:

hibernate.cfg.xml


3. Run the `Main` class.

Hibernate will auto-create tables using:

hibernate.hbm2ddl.auto=update


---

## Example Capabilities

- Park a vehicle
- Generate ticket
- Unpark vehicle
- Calculate bill
- Track total revenue
- Persist complete parking history

---

## Concepts Demonstrated

- JPA Entity Mapping
- @OneToMany / @ManyToOne relationships
- Inheritance Mapping (@Inheritance, @DiscriminatorColumn)
- Cascade Operations
- Lazy Loading
- Transaction Management
- HQL Aggregation Queries
- Dirty Checking
- Clean Repository Pattern

---

## Future Enhancements

- Convert to Spring Boot REST API
- Add authentication & admin dashboard
- Implement vehicle-type-aware allocation using advanced HQL
- Add pessimistic locking for concurrency handling
- Introduce DTO layer and REST validation

---

## Author

Samrat Dudgundi  
GitHub: https://github.com/d-samrat

---

## 🏁 Status

✔ Fully functional Hibernate-based backend system  
✔ Ready for Spring Boot conversion  
