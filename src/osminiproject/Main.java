package osminiproject;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        AuctionManager manager = new AuctionManager();

        // Launch Auctioneer GUI
        SwingUtilities.invokeLater(() -> new AuctioneerGUI(manager));

        // Launch 3 Bidder GUIs (you can launch more)
        SwingUtilities.invokeLater(() -> new BidderGUI(manager, "Bidder 1"));
        SwingUtilities.invokeLater(() -> new BidderGUI(manager, "Bidder 2"));
        SwingUtilities.invokeLater(() -> new BidderGUI(manager, "Bidder 3"));
    }
}

