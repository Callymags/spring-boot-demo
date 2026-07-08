# Java Backend Warm-Up

## Overview

This project is a daily backend warm-up inspired by the way musicians practise scales.

The goal is to repeatedly build the same Spring Boot application while introducing one new concept at a time. Each level focuses on mastering a specific part of a typical Java backend application before introducing the next architectural layer.

The aim is not to build a production application, but to develop muscle memory and confidence with common Spring Boot patterns.

---

# Level 1 - Controller & Model Fundamentals

## Goal

Understand how a basic REST controller works and how simple Java models are created.

### Packages

- controller
- model

### Concepts

- POJOs
- Constructors
- Getters & Setters
- toString()
- CRUD endpoints
- GET
- POST
- PUT
- PATCH
- DELETE
- Path Variables
- RequestBody
- In-memory collections

### Testing

- MockMvc
- Basic controller tests
- Endpoint behaviour

---

# Level 2 - Collections & Stream API

## Goal

Learn how to query and manipulate collections using Java Streams.

### Packages

- controller
- model

### Concepts

- stream()
- filter()
- map()
- sorted()
- count()
- anyMatch()
- findFirst()
- toList()

### Example Endpoints

- Search by name
- Get category names
- Count categories
- Exists by id
- Sort by name

### Testing

- Controller tests for stream-based endpoints

---

# Level 3 - Service Layer

## Goal

Separate business logic from the controller.

### Packages

- controller
- service
- model

### Concepts

- @Service
- Constructor Injection
- Delegation
- Thin Controllers

### Testing

- Controller tests
- Service unit tests

---

# Level 4 - Repository Layer

## Goal

Introduce the repository pattern before adding a database.

### Packages

- controller
- service
- repository
- model

### Concepts

- Repository pattern
- Dependency Injection chain
- Separation of responsibilities

### Testing

- Repository tests
- Service tests
- Controller tests

---

# Level 5 - DTOs

## Goal

Separate API models from domain models.

### Packages

- controller
- service
- repository
- dto
- model

### Concepts

- Request DTOs
- Response DTOs
- Manual mapping
- API boundaries

### Testing

- DTO mapping tests
- Controller tests

---

# Level 6 - Persistence

## Goal

Replace the in-memory repository with a real database.

### Packages

- controller
- service
- repository
- entity
- dto

### Concepts

- Spring Data JPA
- @Entity
- JpaRepository
- H2
- PostgreSQL

### Testing

- Repository integration tests

---

# Level 7 - Validation & Exception Handling

## Goal

Handle invalid requests correctly.

### Concepts

- @Valid
- Bean Validation
- ResponseEntity
- ControllerAdvice
- Custom Exceptions

---

# Level 8 - Integration Testing

## Goal

Test the complete application from HTTP request through to persistence.

### Concepts

- @SpringBootTest
- Integration Tests
- Test Database
- Full request lifecycle

---

# Level 9 - Production Features

## Goal

Introduce common production practices.

### Concepts

- Logging
- Profiles
- Configuration
- Pagination
- Sorting
- Filtering
- Lombok

---

## Philosophy

Each level should only introduce **one new architectural concept**.

Everything learned in previous levels should continue to be practised in the next level until it becomes second nature.

The project is designed to be completed repeatedly rather than once.