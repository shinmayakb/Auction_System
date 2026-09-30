package osminiproject;

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
