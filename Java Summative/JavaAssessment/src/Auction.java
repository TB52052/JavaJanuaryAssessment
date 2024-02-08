/******************************************************************************************

 File:          Auction.Java

 Date: 		    25/01/2024

 Author: 	    Taylor Bullard (frf22rzu)

 Description:   Contains inner class Triple which includes generics. singleAuction() and
                multipleAuctions() are used to simulate and auction with the objects used
                in that auction initialised in Auction()

 Version: 	    24/01/2024 v1.00
                25/01/2024 v1.01 - completed singleAuction() and multipleAuctions()

 *****************************************************************************************/

import java.util.ArrayList;
import java.util.HashSet;

public class Auction {

    //nested inner class Triple with 3 generic values
    public class Triple<first, second, third> {

        //private variables for Triple using generic types
        private first seller;
        private second product;
        private third money;

        //constructor
        public Triple(first seller, second product, third money) {
            this.seller = seller;
            this.product = product;
            this.money = money;
        }

        //getters and setters


        public first getSeller() {
            return seller;
        }

        public void setSeller(first seller) {
            this.seller = seller;
        }

        public second getProduct() {
            return product;
        }

        public void setProduct(second product) {
            this.product = product;
        }

        public third getMoney() {
            return money;
        }

        public void setMoney(third money) {
            this.money = money;
        }

    }

    //private variables for auction
    private int numAgents;

    //initialised to have size 0 so not null
    private HashSet<Agent> agents = HashSet.newHashSet(0);

    //initialised to have size 0 so not null
    private HashSet<Triple> bids = HashSet.newHashSet(0);
    private Triple highestBid;

    //getters and setter
    public int getNumAgents() {
        return numAgents;
    }

    public void setNumAgents(int numAgents) {
        this.numAgents = numAgents;
    }

    public HashSet<Agent> getAgents() {
        return agents;
    }

    public void setAgents(HashSet<Agent> agents) {
        this.agents = agents;
    }

    public HashSet<Triple> getBids() {
        return bids;
    }

    public void setBids(HashSet<Triple> bids) {
        this.bids = bids;
    }

    public Triple getHighestBid() {
        return highestBid;
    }

    public void setHighestBid(Triple highestBid) {
        this.highestBid = highestBid;
    }

    //constructor
    public Auction() {

        //task 3 creating products for agents

        numAgents = 4;

        //generating products
        Product product1 = new Product(Product.ProductType.BOOK, 75,
                "The Hitchhikers Guide to the Galaxy");
        Product product2 = new Product(Product.ProductType.COIN, 35,
                "Taylor-Bullard");
        Product product3 = new Product(Product.ProductType.STAMP, 40,
                "Iceland");
        Product product4 = new Product(Product.ProductType.STAMP, 25,
                "Queen");
        Product product5 = new Product(Product.ProductType.COIN, 30,
                "King");
        Product product6 = new Product(Product.ProductType.BOOK, 80,
                "Harry Potter");
        Product product7 = new Product(Product.ProductType.BOOK, 80,
                "Lord of the Rings");

        //new product arrayLists
        ArrayList<Product> agent1Products = new ArrayList<Product>();
        ArrayList<Product> agent2Products = new ArrayList<Product>();
        ArrayList<Product> agent3Products = new ArrayList<Product>();
        ArrayList<Product> agent4Products = new ArrayList<Product>();

        //adding to the arrayLists
        agent1Products.add(product1);
        agent1Products.add(product2);
        agent2Products.add(product3);
        agent2Products.add(product4);
        agent2Products.add(product5);
        agent3Products.add(product6);
        agent4Products.add(product7);

        //randomly assigning money value
        Agent agent1 = new Agent(agent1Products,agent1Products.size(),
                (500 + (int)(Math.random() * 501)));
        Agent agent2 = new Agent(agent2Products,agent2Products.size(),
                (500 + (int)(Math.random() * 501)));
        Agent agent3 = new Agent(agent3Products,agent3Products.size(),
                (500 + (int)(Math.random() * 501)));
        Agent agent4 = new Agent(agent4Products,agent4Products.size(),
                (500 + (int)(Math.random() * 501)));

        //adding all the agents to the agents hashSet
        agents.add(agent1);
        agents.add(agent2);
        agents.add(agent3);
        agents.add(agent4);

    }

    public void singleAuction(Agent seller) {

        //setting current product to a random type
        Agent.setCurrentProduct(Product.randomProductType());

        //requesting a product from the seller by calling offerProduct()
        Product.ProductType p;

        p = seller.offerProduct();

        Product m = new Product(p,0,null);

        //for all agents in the agents HashSet
        for (Agent a : agents) {

            //creating a bid on the offered product
            Triple<Agent,Product, Integer> b = new Triple<>(seller,m,seller.makeBid(m));

            //adding the bid (stored as a Triple<>) into bids
            bids.add(b);

        }

        //calculating the highest bidder
        int highest = 0;
        Triple<Agent, Product, Integer> winningBid = null;

        for (Triple<Agent, Product, Integer> bid : bids) {
            if (bid.getMoney() > highest) {
                highest = bid.getMoney();
                winningBid = bid;
            }
        }

        if (winningBid != null) {
            highestBid = winningBid;
            System.out.println("The highest bidder is: " + highestBid.getSeller()
                    + " with a bid of " + highestBid.getMoney());
        } else {
            System.out.println("There were no bids");
        }
    }

    public void multipleAuctionSimulation() {

        System.out.println("----------------------------------------------------------");
        System.out.println("Simulating Auction...");
        System.out.println();

        //calling all agents in the agents hashSet
        for (Agent a : agents) {
            singleAuction(a);
        }

        //outputted information from the auction is printed to console at the end of singleAuction()

    }


}
