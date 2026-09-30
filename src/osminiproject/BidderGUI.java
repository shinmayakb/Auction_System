package osminiproject;

import javax.swing.*;
import java.awt.*;

public class BidderGUI extends JFrame {
    private AuctionManager manager;
    private JTextField bidField;
    private JLabel statusLabel;
    private String bidderName;

    public BidderGUI(AuctionManager manager, String name) {
        this.manager = manager;
        this.bidderName = name;

        setTitle("Bidder: " + bidderName);
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        bidField = new JTextField();
        JButton bidButton = new JButton("Place Bid");
        statusLabel = new JLabel("Status: Waiting...");

        setLayout(new GridLayout(3, 1));
        add(bidField);
        add(bidButton);
        add(statusLabel);

        bidButton.addActionListener(e -> placeBid());

        setVisible(true);
    }

    private void placeBid() {
        try {
            int bid = Integer.parseInt(bidField.getText().trim());
            if (!manager.isAuctionRunning()) {
                statusLabel.setText("Auction not running!");
                return;
            }
            boolean success = manager.placeBid(bidderName, bid);
            if (success) {
                statusLabel.setText("Bid Placed Successfully!");
            } else {
                statusLabel.setText("Bid too low!");
            }
        } catch (NumberFormatException ex) {
            statusLabel.setText("Invalid input!");
        }
    }
}

