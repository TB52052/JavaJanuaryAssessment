/******************************************************************************************

 File:          Main.Java

 Date: 		    25/01/2024

 Author: 	    Taylor Bullard (frf22rzu)

 Description:   Main class used for testing in the main() method. Also includes the methods
                writeToFile() and readFromFile() which were used in the serialization tasks

                Need to implement a toString() method for better Agent readability
                Must run a few times as varied results on each

 Version: 	    24/01/2024 v1.00
                25/01/2021 v1.01 - implemented the simulation of an Auction

 *****************************************************************************************/

import java.awt.print.Book;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {

        //creating 3 Product objects using the information requested
        Product product1 = new Product(Product.ProductType.BOOK, 75,
                "The Hitchhikers Guide to the Galaxy");
        Product product2 = new Product(Product.ProductType.COIN, 35,
                "Taylor-Bullard");
        Product product3 = new Product(Product.ProductType.STAMP, 40,
                "Iceland");

        //serializing the objects to files
        writeToFile(product1, "product1.ser");
        writeToFile(product2, "product2.ser");
        writeToFile(product3, "product3.ser");

        //setting the salePrice of all the objects to the values requested
        product1.setSalePrice(100);
        product2.setSalePrice(50);
        product3.setSalePrice(200);

        //printing all the objects to the console
        System.out.println(product1);
        System.out.println(product2);
        System.out.println(product3);

        System.out.println();

        //reloading the objects from the saved files
        product1 = (Product) readFromFile("product1.ser");
        product2 = (Product) readFromFile("product2.ser");
        product3 = (Product)readFromFile("product3.ser");

        //printing all the objects to the console again
        // (values return to when they were serialized)
        System.out.println(product1);
        System.out.println(product2);
        System.out.println(product3);

        System.out.println();

        //creating a new Agent object
        Agent myAgent = new Agent(new ArrayList<Product>(), 0, 1000);

        //creating products for agent to buy
        Product book1 = new Product(Product.ProductType.BOOK,50,"Book1");
        Product book2 = new Product(Product.ProductType.BOOK,50,"Book2");
        Product coin1 = new Product(Product.ProductType.COIN,50,"Coin1");
        Product stamp1 = new Product(Product.ProductType.STAMP,50,"Stamp1");
        Product stamp2 = new Product(Product.ProductType.STAMP,50,"Stamp2");
        Product stamp3 = new Product(Product.ProductType.STAMP,50,"Stamp3");

        myAgent.buyProduct(50, book1);
        myAgent.buyProduct(50, book2);
        myAgent.buyProduct(50, coin1);
        myAgent.buyProduct(50, stamp1);
        myAgent.buyProduct(50, stamp2);
        myAgent.buyProduct(50, stamp3);

        //printing all of agent's BOOK's to console
        for (int i = 0; i < myAgent.getProducts().size(); i++) {
            if (myAgent.getProducts().get(i).getType() == Product.ProductType.BOOK) {
                System.out.println(myAgent.getProducts().get(i));
            }
        }

        //test to check that funds have been taken from agent (success - now 700)
        System.out.println(myAgent.getMoney());

        //must set current product before offering
        myAgent.setCurrentProduct(Product.ProductType.STAMP);
        myAgent.offerProduct();

        //selling the product
        myAgent.sellProduct(100,stamp1);

        //checking if stamp is gone from products (success - only 2 now)
        for (int i = 0; i < myAgent.getProducts().size(); i++) {
            if (myAgent.getProducts().get(i).getType() == Product.ProductType.STAMP) {
                System.out.println(myAgent.getProducts().get(i));
            }
        }

        //test to check that funds have been given to agent (success - now 800)
        System.out.println(myAgent.getMoney());

        System.out.println();

        //simulating an auction
        //the 4 agents are all initialised in new Auction()
        Auction newAuction = new Auction();

        //test to see if new Auction() had been generated correctly
        /*System.out.println(newAuction.getAgents());*/

        newAuction.multipleAuctionSimulation();


    }

    //implementing writeToFile and ReadFromFile
    public static void writeToFile(Serializable s, String filename){
        try{
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(s);
            out.close();
        }
        catch(IOException e) {
            System.err.println("IO exception: ERROR");
        }
    }

    //catch clauses provide information on the type of exception caught
    public static Object readFromFile(String path) {
        try {
            FileInputStream fis  = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fis);
            Object m = in.readObject();
            in.close();

            return m;

        } catch (FileNotFoundException e) {
            System.err.println("File has not been found!");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.err.println("IO exception: ERROR");
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.err.println("Class has not been found!");
            throw new RuntimeException(e);
        }

    }

}