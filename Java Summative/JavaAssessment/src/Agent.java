/******************************************************************************************

 File:          Agent.Java

 Date: 		    24/01/2024

 Author: 	    Taylor Bullard (frf22rzu)

 Description:   Implementation of the Agent object, including constructor and accessor
                methods. Class specific methods include: addProduct(), sellProduct(),
                buyProduct(), setCurrentProduct(), offerProduct(), countProduct(),
                makeBid()

 Version: 	    24/01/2024 v1.00

 Improvements:  Make makeBid() more efficient as currently multiple uses of conditional
                statements, making for inefficient code

 *****************************************************************************************/

import java.util.ArrayList;

public class Agent {

    //private variables - Agent
    private ArrayList<Product> products;
    private int currentSize;
    private static final int MAX_SIZE = 100;
    private int money;

    //constructor
    public Agent(ArrayList<Product> products, int currentSize, int money) {
        this.products = products;
        this.currentSize = currentSize;
        this.money = money;
    }

    //getters and setters
    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public void setCurrentSize(int currentSize) {
        this.currentSize = currentSize;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    //class specific methods
    public void addProducts(ArrayList<Product> a) {

        int psize = products.size();

        for (int i = 0; i < products.size(); i++) {
            //copying the arraylist a into the end of the products arraylist
            products.set((psize + i), a.get(i));
        }

        //setting the new size of the arraylist
        setCurrentSize(getProducts().size());
    }

    public boolean sellProduct(int price, Product p) {

        boolean canSell = false;

        //finding the matching product and removing when it matches
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i) == p) {
                products.remove(products.get(i));
                money = money + price;
                canSell = true;
                break;
            }
        }

        setCurrentSize(products.size());

        return canSell;
    }

    public boolean buyProduct(int price, Product p) {

        boolean canBuy = false;

        //if enough money and products < 100
        if (((money - price) >= 0) && (products.size() < MAX_SIZE)) {
            canBuy = true;
            products.add(p);
            money = money - price;
        }

        setCurrentSize(products.size());

        return canBuy;
    }

    //setting currentProduct to have a default of BOOK
    private static Product.ProductType currentProduct = Product.ProductType.BOOK;

    public static void setCurrentProduct(Product.ProductType pT) {
        // Check if the provided product type is valid
        if ((pT == Product.ProductType.BOOK) || (pT == Product.ProductType.COIN)
                                             || (pT == Product.ProductType.STAMP)) {
            currentProduct = pT;
        } else {
            System.err.println(pT + " is an invalid Product type");
        }
    }

    public Product.ProductType offerProduct() {

        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getType() == currentProduct) {
                return products.get(i).getType();
            }
                System.out.println("Offering " + currentProduct);
                return currentProduct;
            }

        //if nothing found then null returned
        return null;
    }

    public int[] countProducts() {

        //initialising an array of ints of size 3
        int[] count = new int[3];

        //initialsing the counts of each type
        int bookCount = 0;
        int coinCount = 0;
        int stampCount = 0;

        //going through the arraylist and counting each type
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getType() == Product.ProductType.BOOK){
                bookCount++;
            } else if (products.get(i).getType() == Product.ProductType.COIN){
                coinCount++;
            } else if (products.get(i).getType() == Product.ProductType.STAMP){
                stampCount++;
            }
        }

        //adding values to array to then return
        count[0] = bookCount;
        count[1] = coinCount;
        count[2] = stampCount;

        return count;
    }

    public int makeBid(Product p) {

        int bid = 0;

        //first getting the type of product
        if (p.getType() == Product.ProductType.BOOK) {

            //then seeing amount of that type to calculate bid
            if (countProducts()[0] == 0) {
                bid = money/4;
            } else if (countProducts()[0] == 1) {
                bid = money/5;
            } else if (countProducts()[0] == 2) {
                bid = money/6;
            } else {
                bid = money/7;
            }

        } else if (p.getType() == Product.ProductType.COIN) {

            if (countProducts()[1] == 0) {
                bid = money/4;
            } else if (countProducts()[1] == 1) {
                bid = money/5;
            } else if (countProducts()[1] == 2) {
                bid = money/6;
            } else {
                bid = money/7;
            }

        } else if (p.getType() == Product.ProductType.STAMP) {

            if (countProducts()[2] == 0) {
                bid = money/4;
            } else if (countProducts()[2] == 1) {
                bid = money/5;
            } else if (countProducts()[2] == 2) {
                bid = money/6;
            } else {
                bid = money/7;
            }

        }

        return bid;
    }

}