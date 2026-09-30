# Auction System

A Java-based Auction Management System developed using **Java Swing** and **MySQL**. The system provides separate functionality for auctioneers and bidders, including user registration, login, auction management, and bid placement.

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

- Java
- Java Swing
- MySQL
- JDBC
- Apache Ant
- NetBeans IDE

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
