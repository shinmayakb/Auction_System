package osminiproject;
// DBConnection.java
import java.sql.*;
// AuctionManager.java
public class AuctionManager {
    private int highestBid = 0;
    private String highestBidder = "None";
    private boolean isAuctionRunning = false;

    public synchronized boolean placeBid(String bidder, int amount) {
        if (!isAuctionRunning) return false;
        if (amount > highestBid) {
            highestBid = amount;
            highestBidder = bidder;
            return true;
        }
        return false;
    }

    public synchronized void startAuction() {
        isAuctionRunning = true;
        System.out.println("Auction started.");
    }

    public synchronized void endAuction() {
        isAuctionRunning = false;
        System.out.println("Auction ended. Highest bid: " + highestBid + " by " + highestBidder);
    }

    public synchronized int getHighestBid() {
        return highestBid;
    }

    public synchronized String getHighestBidder() {
        return highestBidder;
    }

    public synchronized boolean isAuctionRunning() {
        return isAuctionRunning;
    }
}



public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/auction_db";
    private static final String USER = "root";
    private static final String PASSWORD = "your_password"; // Replace with your MySQL password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

// Main.java
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}

// LoginFrame.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginFrame extends JFrame {
    public LoginFrame() {
        setTitle("Auction System - Login");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        JLabel userLabel = new JLabel("Username:");
        JTextField userField = new JTextField();
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        add(userLabel); add(userField);
        add(passLabel); add(passField);
        add(loginButton); add(registerButton);

        loginButton.addActionListener(e -> {
            try (Connection conn = DBConnection.getConnection()) {
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
                ps.setString(1, userField.getText());
                ps.setString(2, new String(passField.getPassword()));
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String role = rs.getString("role");
                    if (role.equals("auctioneer")) {
                        new AuctioneerFrame().setVisible(true);
                    } else {
                        new BidderFrame(userField.getText()).setVisible(true);
                    }
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid credentials");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        registerButton.addActionListener(e -> new RegisterFrame().setVisible(true));
    }
}

// RegisterFrame.java
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class RegisterFrame extends JFrame {
    public RegisterFrame() {
        setTitle("Register");
        setSize(400, 300);
        setLayout(new GridLayout(5, 2));

        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();
        JComboBox<String> roleBox = new JComboBox<>(new String[]{"bidder", "auctioneer"});
        JButton registerBtn = new JButton("Register");

        add(new JLabel("Username:")); add(userField);
        add(new JLabel("Password:")); add(passField);
        add(new JLabel("Role:")); add(roleBox);
        add(registerBtn);

        registerBtn.addActionListener(e -> {
            try (Connection conn = DBConnection.getConnection()) {
                PreparedStatement ps = conn.prepareStatement("INSERT INTO users (username, password, role) VALUES (?, ?, ?)");
                ps.setString(1, userField.getText());
                ps.setString(2, new String(passField.getPassword()));
                ps.setString(3, roleBox.getSelectedItem().toString());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Registered Successfully");
                dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}
