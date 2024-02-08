/******************************************************************************************

 File:          Product.Java

 Date: 		    24/01/2024

 Author: 	    Taylor Bullard (frf22rzu)

 Description:   A serializable class, contains enum class of ProductType as well as methods
                used to create a Product object including a toString() method. the main()
                method has been created and used as a test harness so that testing could be
                done throughout the development process. randomProductType()

 Version: 	    24/01/2024 v1.00

 *****************************************************************************************/

import java.io.Serializable;
import java.util.Random;

public class Product implements Serializable {

    //enum for ProductType
    public enum ProductType {
        BOOK(0,0),
        COIN(0,0),
        STAMP(0,0);

        //variables associated with each product type
        private int lastSalePrice;
        private int maxSalePrice;

        //constructor for Product, initialising both variables to 0
        ProductType(int lastSalePrice, int maxSalePrice) {
            this.lastSalePrice = 0;
            this.maxSalePrice = 0;
        }

        //getters and setters
        public int getLastSalePrice() {
            return lastSalePrice;
        }

        //modified so that if salePrice is greater than the maxSalePrice,
        // it will be updated to represent the new value
        public void setLastSalePrice(int lastSalePrice) {
            this.lastSalePrice = lastSalePrice;

            if (lastSalePrice > maxSalePrice) {
                maxSalePrice = lastSalePrice;
            }

        }

        public int getMaxSalePrice() {
            return maxSalePrice;
        }

        public void setMaxSalePrice(int maxSalePrice) {
            this.maxSalePrice = maxSalePrice;
        }
    }

    //private variables - Product
    private ProductType type;
    private int salePrice;
    private String name;

    //constructor for Product
    public Product(ProductType type, int salePrice, String name) {
        this.type = type;
        this.salePrice = salePrice;
        this.name = name;
    }

    //getters and setters
    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public int getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //class specific methods

    //toString method
    public String toString(){
        return name + " is a " + type + " which as a sale price of: " + salePrice;
    }

    public static ProductType randomProductType() {

        Random rand = new Random();

        int randomInt = rand.nextInt(1000);

        if (randomInt%3 == 0) {
            return ProductType.BOOK;
        } else if (randomInt%3 == 0) {
            return ProductType.COIN;
        } else {
            return ProductType.STAMP;
        }
    }

    //main method (used as a test harness)
    public static void main(String[] args) {

        Product magnaCarta = new Product(ProductType.BOOK, 100, "Magna Carta");
        Product queenHead = new Product(ProductType.STAMP, 86, "Queen Head");

        System.out.println(magnaCarta.toString());
        System.out.println(queenHead.toString());

        queenHead.setSalePrice(186);
        System.out.println(queenHead.toString());

    }

}
