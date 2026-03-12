# Smart Billing Inventory System - Spring Boot

## Project Overview

Smart Billing Inventory System is a **Spring Boot REST API application** designed to manage product inventory, process customer orders, and automate billing and notifications.
The system allows users to place orders for products, automatically calculates GST, updates product stock, and sends notifications to both customers and sellers.

It also includes **inventory monitoring and scheduled reporting**, ensuring sellers are notified when stock reaches a threshold limit and receive a daily report of all products.

---

## Key Features

### User Management

* Register users with **name and mobile number**
* User information stored in the database

### Product Management

* Store product details including:

  * Product Name
  * Price
  * GST
  * Available Stock
  * Threshold Limit

### Order Management

* Users can place orders for products
* The system calculates **total price including GST**
* Product stock is automatically **updated after each order**

### Customer Notification

* When an order is placed:

  * Customer receives **SMS notification**
  * Customer receives **WhatsApp message**
* Implemented using **Twilio third-party API**

### Inventory Monitoring

* The system checks if product stock reaches the **threshold limit**
* If the threshold is reached, a **notification SMS is sent to the seller**

### Automated Daily Reports

* A **scheduled task runs every day at 6 PM**
* Generates a **CSV report of all products**
* Sends the report to the seller via **email**

---

## Technologies Used

* Java
* Spring Boot
* Spring Data JPA
* MySQL
* Maven
* Twilio API (SMS & WhatsApp notifications)
* Spring Mail (Email Service)
* Scheduled Tasks (`@Scheduled`)

---

## Project Architecture

The application follows a **layered architecture**:

Controller → Service → Repository → Database

* **Controller Layer** – Handles REST API requests
* **Service Layer** – Contains business logic such as GST calculation and stock update
* **Repository Layer** – Handles database operations using Spring Data JPA
* **Database Layer** – MySQL database

---

## Database Design

### User Table

Stores customer information.

Fields:

* id
* name
* mobile_number

### Product Table

Stores product details.

Fields:

* id
* name
* price
* gst
* stock
* threshold

### Order Table

Stores order details.

Fields:

* id
* customer_id
* product_id
* quantity
* total_amount

---

## API Endpoints

### Create User

POST /users

Adds a new user to the system.

---

### Add Product

POST /products

Adds a new product with stock, price, and GST details.

---

### Place Order

POST /orders

When an order is placed:

* GST is calculated
* Total amount is generated
* Product stock is updated
* Customer receives SMS and WhatsApp notification

---

## Automated Features

### Stock Threshold Alert

If product stock falls below the threshold:

* Seller receives an **SMS alert**

### Daily Inventory Report

Every day at **6 PM**:

* A scheduled job runs using `@Scheduled`
* Generates **CSV file of all products**
* Sends report to seller via **email**

---

## How to Run the Project

1. Clone the repository

git clone https://github.com/yourusername/smart-billing-inventory-system.git

2. Open the project in **IntelliJ / Eclipse**

3. Configure database in `application.properties`

4. Add Twilio credentials for SMS and WhatsApp service

5. Configure email settings for mail service

6. Run the Spring Boot application

---

## Future Improvements

* Add authentication and authorization
* Add product categories
* Create admin dashboard
* Implement order history APIs
* Add invoice generation

---

## Author
Sahil Sakariya
