package DesignPatterns.Behavioral_Design_Patterns.Mediator_Design_Pattern;

import java.util.List;
import java.util.ArrayList;
class Bidder{
    String name;
    int currentBid;
    AuctionHouse auctionHouse;

    public Bidder(String name, AuctionHouse auctionHouse){
        this.name = name;
        this.auctionHouse = auctionHouse;
        this.currentBid = 0;
        auctionHouse.RegisterBidder(this); 
    }

    public void setBid(int bid_amount){
        this.currentBid=bid_amount;
        this.auctionHouse.PlaceBid(this, bid_amount);
    }
    public void receiveBid(Bidder bidder,int bidAmount){
        System.out.println("Bid of amount "+bidAmount+" is place by the bidder "+bidder.name);
    }

}

interface AuctionMediator{
    void RegisterBidder(Bidder bidder);
    void PlaceBid(Bidder bidder, int bidAmount);

}
class AuctionHouse implements AuctionMediator{
    List<Bidder> bidders= new ArrayList<>();

    @Override
    public void RegisterBidder(Bidder bidder){
        bidders.add(bidder);
    }

    @Override
    public void PlaceBid(Bidder bidder, int bidAmount){
        for(Bidder b:bidders){
            if(b!=bidder){
                b.receiveBid(bidder,bidAmount);
            }
        }
    }

}
public class valid {
    public static void main(String [] args){
        AuctionHouse auctionHouse = new AuctionHouse();
        Bidder bunty=new Bidder("Bunty",auctionHouse);
        Bidder shunty=new Bidder("Shunty",auctionHouse);
        Bidder bubli=new Bidder("bubli",auctionHouse);
        auctionHouse.RegisterBidder(bunty);
        auctionHouse.RegisterBidder(shunty);
        auctionHouse.RegisterBidder(bubli);
        auctionHouse.PlaceBid(bunty, 500);
        auctionHouse.PlaceBid(shunty,600);
        auctionHouse.PlaceBid(bubli, 1000);


    }
}
