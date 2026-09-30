package osminiproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AuctioneerGUI extends JFrame {
    private AuctionManager manager;
    private JLabel highestBidLabel, timeLabel;
    private JButton startButton;
    private Timer timer;
    private int timeLeft = 60;

    public AuctioneerGUI(AuctionManager manager) {
        this.manager = manager;

        setTitle("Auctioneer");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        highestBidLabel = new JLabel("Highest Bid: 0 by None");
        timeLabel = new JLabel("Time left: 60 seconds");
        startButton = new JButton("Start Auction");

        setLayout(new GridLayout(3, 1));
        add(highestBidLabel);
        add(timeLabel);
        add(startButton);

        startButton.addActionListener(e -> startAuction());

        setVisible(true);
    }

    private void startAuction() {
        manager.startAuction();
        startButton.setEnabled(false);
        timeLeft = 60;
        timer = new Timer(1000, e -> {
            timeLeft--;
            timeLabel.setText("Time left: " + timeLeft + " seconds");
            highestBidLabel.setText("Highest Bid: " + manager.getHighestBid() +
                                    " by " + manager.getHighestBidder());

            if (timeLeft <= 0) {
                ((Timer) e.getSource()).stop();
                manager.endAuction();
                JOptionPane.showMessageDialog(this, "Auction Ended!\nWinner: " +
                        manager.getHighestBidder() + " with ₹" + manager.getHighestBid());
            }
        });
        timer.start();
    }
}

