# E-Commerce Site

A full-stack e-commerce web application built with Spring Boot, PostgreSQL, and Thymeleaf. Supports user registration, login, product browsing, and cart management.

[![Java](https://img.shields.io/badge/Java-8-ED8B00?style=flat-square&logo=openjdk&logoColor=white)](https://www.java.com/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.5.6-6DB33F?style=flat-square&logo=spring&logoColor=white)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=flat-square&logo=postgresql&logoColor=white)](https://www.postgresql.org/)

---

## Features

- User registration and login with email/password
- Product listing and detail pages
- Add to cart, update quantity, remove items
- Per-user cart persistence via PostgreSQL
- Input validation with Bean Validation (`@NotBlank`, `@Email`, `@Size`)
- Server-side rendering with Thymeleaf templates

---

## Architecture

```
com.example.odev/
├── controller/
│   ├── UserController.java      — POST /user/register, POST /user/login
│   ├── ProductController.java   — GET/POST /api/products
│   └── CartController.java      — GET/POST/DELETE /api/cart
├── service/
│   └── UserService.java         — registration and login logic
├── repo/
│   ├── UserRepository.java      — JPA repository for users
│   ├── ProductRepository.java   — JPA repository for products
│   └── CartItemRepository.java  — JPA repository with custom queries
└── Model/
    ├── User.java                 — JPA entity: id, name, surname, email, password, birthdate
    ├── Product.java              — JPA entity: id, title, price, imageUrl
    └── CartItem.java             — JPA entity: user, product, quantity
```

---

## Setup

**Requirements:** Java 8+, Maven, PostgreSQL

**1. Create a PostgreSQL database:**

```sql
CREATE DATABASE your_database;
```

**2. Configure the connection** in `Odev/src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
```

**3. Build and run:**

```bash
cd "Odev "
mvn spring-boot:run
```

The app starts at `http://localhost:8080`. Tables are created automatically on first run (`ddl-auto=update`).

---

## API Reference

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/user/register` | Register a new user |
| `POST` | `/user/login` | Login, returns `userId` on success |
| `GET` | `/api/products/list` | List all products |
| `POST` | `/api/products/add` | Add a new product (JSON body) |
| `POST` | `/api/cart/add` | Add item to cart |
| `POST` | `/api/cart/addOrUpdate` | Add or increment existing cart item |
| `GET` | `/api/cart/list/{userId}` | Get all cart items for a user |
| `DELETE` | `/api/cart/remove/{cartItemId}` | Remove item from cart |

---

## Tech Stack

| Layer | Technology |
|-------|-----------|
| Framework | Spring Boot 2.5.6 |
| ORM | Spring Data JPA / Hibernate |
| Database | PostgreSQL |
| Templates | Thymeleaf |
| Validation | Spring Boot Starter Validation |
| Build | Maven |

---

## License

MIT
