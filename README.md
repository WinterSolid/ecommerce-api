# E-Commerce API

A backend e-commerce system built with Java and Spring Boot.  

---

## Features

### Products
- Create products
- View products
- Update products
- Delete products
- Track inventory

### Users
- Create users
- View users
- Delete users

### Orders
- Create orders
- Link orders to users
- Add multiple products per order
- Calculate total price automatically
- Transactional order creation

---

## Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Docker
- Maven

---

## Database Structure

User → Orders → OrderItems → Products

---

## Project Structure

src  
├── controller  
├── dto  
├── entity  
├── repository  
├── service  

---

## Run Locally

### 1. Start PostgreSQL (Docker)

```bash
docker run --name ecommerce_postgres \
-e POSTGRES_PASSWORD=password \
-e POSTGRES_DB=ecommerce \
-p 5433:5432 \
-d postgres:16`
```
---

## Api 

### Create User

```bash
docker run --name ecommerce_postgres \
-e POSTGRES_PASSWORD=password \
-e POSTGRES_DB=ecommerce \
-p 5433:5432 \
-d postgres:16`
```

### Create Product

```bash
curl -X POST http://localhost:8080/api/products \
-H "Content-Type: application/json" \
-d '{"name":"Keyboard","description":"Mechanical","price":99.99,"stockQuantity":10}'
```

### Create Order

```bash
curl -X POST http://localhost:8080/api/orders \
-H "Content-Type: application/json" \
-d '{
"userId": 1,
"items": [
  {
    "productId": 1,
    "quantity": 2
  }
]
}'
```

---
