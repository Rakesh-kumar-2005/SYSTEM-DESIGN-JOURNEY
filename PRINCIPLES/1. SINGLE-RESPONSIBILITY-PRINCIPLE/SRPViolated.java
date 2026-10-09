
/**
 * SRPViolated
 * Here, we have violated the Single Responsibility Principle (SRP) by having multiple responsibilities in a single class.
 * The ShoppingCart class is responsible for managing the shopping cart, calculating the total price, printing the invoice, and saving to the database.
*/

import java.util.ArrayList;

// Product class is responsible for storing product details
class Product {

    public String name;
    public int price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

}

// ShoppingCart class is responsible for managing the shopping cart, calculating total price, printing invoice and saving to database
class ShoppingCart {

    public ArrayList<Product> products = new ArrayList<Product>();

    public void addProducts(Product p) {
        products.add(p);
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public int calculateTotalPrice() {
        int totalPice = 0;

        for (Product product : products) {
            totalPice += product.price;
        }

        return totalPice;
    }

    // =====================================================================================================
    //  Doing multiple responsibilities in a single class violates the Single Responsibility Principle (SRP)
    // =====================================================================================================

    // i. Invoice Printing and Database saving should be handled by separate classes
    public void printInvoice() {
        System.out.println("Shopping Cart invoice :- ");

        for (Product product : products) {
            System.out.println(product.name + " - Rs" + product.price);
        }

        System.out.println("Total Price of the shopping cart is : " + calculateTotalPrice());
    }

    // ii. Database saving should be handled by a separate class
    public void saveToDatabase(){
        System.out.println("Saving Shopping cart to database...");
    }
}

public class SRPViolated {

    public static void main(String[] args) {

        // Creating a shopping cart and adding products to it
        ShoppingCart shoppingCart = new ShoppingCart();

        // Adding products to the shopping cart
        shoppingCart.addProducts(new Product("Laptop", 67000));
        shoppingCart.addProducts(new Product("Iphone", 167000));
        shoppingCart.addProducts(new Product("Tablet", 45000));

        // Printing the invoice and saving to database using the same ShoppingCart class, which violates the Single Responsibility Principle (SRP)
        shoppingCart.printInvoice();
        shoppingCart.saveToDatabase();


    }

}