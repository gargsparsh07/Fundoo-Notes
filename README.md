# Fundoo Notes Backend

A robust, enterprise-grade Spring Boot backend for the Fundoo Notes application.

## Architecture & Development

This project is being developed using a strict Use-Case (UC) driven approach. It features a completely isolated `main` branch (for documentation only) and a rigidly managed `develop` branch for code integration.

All APIs are built adhering to the core layered architectural pattern:
`Client → Controller → DTO → Service → Repository → Database`

## Phase 1 Implemented Features (UC1 - UC10)

- UC1: Project setup and package structure
- UC2: Database configuration
- UC3: User entity and repository
- UC4: User Registration API with Global Exception Handling
- UC5: User Login and JWT Generation
- UC6: JWT Validation Flow and Security Filter
- UC7: Note Entity and Repository Setup
- UC8: Create Note API endpoint
- UC9: Get Notes API Dashboard Endpoint
- UC10: Pin, Archive, and Trash Toggle APIs

## Phase 2 Features (UC11+)

- UC11: AOP Logging for service layer