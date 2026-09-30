# Auction_System
A Java-based Auction Management System developed using Java Swing and MySQL. The system provides separate functionality for auctioneers and bidders, including user registration, login, auction management, and bid placement.

Features
User registration and login
Role-based access:
Auctioneer
Bidder
Auction creation and management
Real-time bid validation
Highest bid and highest bidder tracking
MySQL database integration
Java Swing graphical user interface
Synchronized auction operations for handling concurrent bids
Technologies Used
Java
Java Swing
MySQL
JDBC
Apache Ant / NetBeans
Project Structure
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
│   └── private/
│
├── build.xml
├── manifest.mf
└── README.md
Database Setup

Create a MySQL database:

CREATE DATABASE auction_db;

Create the users table:

USE auction_db;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
);

Update the database configuration in DBConnection.java:

private static final String URL =
        "jdbc:mysql://localhost:3306/auction_db";

private static final String USER = "root";

private static final String PASSWORD = "your_password";

Replace your_password with your local MySQL password.

How to Run
1. Clone the repository
git clone https://github.com/shinmayakb/Auction_System.git
2. Open the project

Open the project in NetBeans IDE.

3. Configure MySQL

Make sure:

MySQL Server is running
auction_db exists
The users table has been created
DBConnection.java contains the correct MySQL credentials
4. Run the project

Run the Main.java file from NetBeans.

User Roles
Auctioneer

An auctioneer can manage the auction and monitor the highest bid and bidder.

Bidder

A bidder can log in and participate in the auction by placing bids.

Auction Logic

A bid is accepted only when:

Auction is running
        AND
New bid > Current highest bid

When a valid bid is placed, the system updates:

Highest Bid
Highest Bidder

The auction management methods are synchronized to help maintain consistency when multiple operations occur concurrently.

Security Note

Database passwords and other sensitive credentials should not be committed to GitHub. Use local configuration or environment variables for production deployments.

Author

Shinmaya KB

B.Tech Information Technology

Mepco Schlenk Engineering College

License

This project is intended for academic and educational purposes.
