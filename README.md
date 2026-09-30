# Auction System

A Java-based **Auction Management System** developed using **Java Swing** and **MySQL**. The system provides separate functionality for auctioneers and bidders, including user registration, login, auction management, and bid placement.

## Features

- User registration and login
- Role-based access:
  - Auctioneer
  - Bidder
- Auction creation and management
- Real-time bid validation
- Highest bid and highest bidder tracking
- MySQL database integration
- Java Swing graphical user interface
- Synchronized auction operations for handling concurrent bids

## Technologies Used

- **Java**
- **Java Swing**
- **MySQL**
- **JDBC**
- **Apache Ant**
- **NetBeans IDE**

## Project Structure

```text
OSMiniProject/
│
├── src/
│   └── osminiproject/
│       ├── AuctionManager.java
│       ├── AuctioneerGUI.java
│       ├── BidderGUI.java
│       ├── DBConnection.java
│       ├── Main.java
│       └── OSMiniProject.java
│
├── nbproject/
│
├── build.xml
├── manifest.mf
└── README.md
```

## Database Setup

### 1. Create the Database

```sql
CREATE DATABASE auction_db;
```

### 2. Create the Users Table

```sql
USE auction_db;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
);
```

### 3. Configure Database Connection

Update the database configuration in `DBConnection.java`:

```java
private static final String URL =
        "jdbc:mysql://localhost:3306/auction_db";

private static final String USER = "root";

private static final String PASSWORD = "your_password";
```

Replace `your_password` with your local MySQL password.

## How to Run

### 1. Clone the Repository

```bash
git clone https://github.com/shinmayakb/Auction_System.git
```

### 2. Open the Project

Open the project in **NetBeans IDE**.

### 3. Configure MySQL

Make sure:

- MySQL Server is running
- `auction_db` database exists
- `users` table has been created
- `DBConnection.java` contains the correct MySQL credentials
- MySQL JDBC Driver is available in the project

### 4. Run the Application

Run:

```text
Main.java
```

from NetBeans.

## User Roles

### Auctioneer

The auctioneer can manage the auction and monitor the current highest bid and highest bidder.

### Bidder

The bidder can register, log in, and participate in the auction by placing bids.

## Auction Logic

A bid is accepted only when:

```text
Auction is running
        AND
New bid > Current highest bid
```

When a valid bid is placed, the system updates:

```text
Highest Bid
Highest Bidder
```

The auction management methods use Java's `synchronized` keyword to help maintain consistency when multiple operations access the auction simultaneously.

## Application Workflow

```text
Start Application
       │
       ▼
   Login Page
       │
       ├───────────────┐
       │               │
       ▼               ▼
  Auctioneer        Bidder
       │               │
       ▼               ▼
Manage Auction     Place Bid
       │               │
       └───────┬───────┘
               ▼
       Update Highest Bid
               │
               ▼
          End Auction
               │
               ▼
      Display Highest Bid
```

## Database Schema

The system uses a MySQL database named `auction_db`.

### Users Table

| Column | Type | Description |
|---|---|---|
| `id` | INT | Unique user ID |
| `username` | VARCHAR(100) | User login name |
| `password` | VARCHAR(255) | User password |
| `role` | VARCHAR(20) | User role |

Supported roles:

- `bidder`
- `auctioneer`

## Concurrency Handling

The `AuctionManager` class uses the Java `synchronized` keyword for auction operations.

This helps prevent inconsistent auction data when multiple users attempt to place bids at the same time.

For example:

```java
public synchronized boolean placeBid(String bidder, int amount)
```

The method checks whether the auction is active and whether the new bid is greater than the current highest bid before updating the auction state.

## Security Note

Do not commit real database passwords, API keys, or other sensitive credentials to GitHub.

For a production application, use environment variables or a secure configuration mechanism instead of storing credentials directly in the source code.

## Future Enhancements

- Password hashing and secure authentication
- Real-time multi-user bidding
- Auction timer
- Bid history
- Winner notification
- Auction item management
- Admin dashboard
- Improved user interface
- Email notifications
- Deployment as a web-based application

## Author

**Shinmaya KB**

B.Tech Information Technology  
Mepco Schlenk Engineering College

## License

This project is intended for academic and educational purposes.
