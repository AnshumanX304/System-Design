
from __future__ import annotations

from abc import ABC, abstractmethod


class ActionMediator(ABC):

    @abstractmethod
    def registerBidder(self, bidder: Bidder):
        pass

    @abstractmethod
    def placeBid(self, bidder: Bidder, bid_amount: int):
        pass


class AuctionHouse(ActionMediator):

    def __init__(self):
        self.bidders: list[Bidder] = []

    def registerBidder(self, bidder: Bidder):
        self.bidders.append(bidder)

    def placeBid(self, bidder: Bidder, bid_amount: int):
        for b in self.bidders:
            if b != bidder:
                b.receiveBid(bidder, bid_amount)


class Bidder:

    def __init__(self, name: str, auction_house: ActionMediator):
        self.name = name
        self.current_bid = 0
        self.auction_house = auction_house
        self.auction_house.registerBidder(self)

    def setBid(self, bid_amount: int):
        self.current_bid = bid_amount
        self.auction_house.placeBid(self, bid_amount)

    def receiveBid(self, bidder: Bidder, bid_amount: int):
        print(
            f"Bid of amount {bid_amount} is placed "
            f"by the bidder {bidder.name}"
        )


if __name__ == "__main__":

    auctionHouse = AuctionHouse()

    bunty = Bidder("Bunty", auctionHouse)
    shunty = Bidder("Shunty", auctionHouse)
    bubli = Bidder("Bubli", auctionHouse)

    bunty.setBid(500)
    shunty.setBid(600)
    bubli.setBid(1000)



# ============================================================
# MEDIATOR DESIGN PATTERN - KEY TAKEAWAYS
# ============================================================
#
# INTENT:
# Centralize communication between multiple objects through
# a mediator instead of allowing objects to communicate directly.
#
# COMPONENTS:
# 1. Mediator Interface  -> Defines the communication contract.
# 2. Concrete Mediator   -> Coordinates communication between objects.
# 3. Colleague           -> Objects that communicate through the mediator.
# 4. Client              -> Creates and connects the objects.
#
# IN THIS EXAMPLE:
# ActionMediator -> Mediator Interface
# AuctionHouse   -> Concrete Mediator
# Bidder         -> Colleague
# main()         -> Client
#
# EXECUTION FLOW:
# Bidder.setBid()
#       |
#       v
# AuctionHouse.placeBid()
#       |
#       v
# AuctionHouse notifies all other registered bidders
#       |
#       v
# Bidder.receiveBid()
#
# IMPORTANT:
# - Bidders do not maintain direct references to other bidders.
# - Bidders communicate only through the AuctionHouse.
# - The Mediator reduces direct dependencies between objects.
# - Adding a new bidder does not require modifying existing bidders.
#
# WHEN TO USE:
# When multiple objects need to communicate and their direct
# dependencies are becoming complex and difficult to maintain.
#
# REMEMBER:
# COMMAND PATTERN   -> Encapsulates a request as an object.
# STRATEGY PATTERN  -> Encapsulates interchangeable algorithms.
# MEDIATOR PATTERN  -> Centralizes communication between objects.
#
# GOLDEN RULE:
# Instead of objects talking to each other directly,
# let them talk through a central mediator.
# ============================================================
