/**
 * This class demonstrates the Open/Closed Principle (OCP) by separating the responsibilities of different classes and allowing for easy extension without modifying existing code...
 * The ShoppingCart class is responsible for adding products and calculating the total price, while the ShoppingCartPrinter class is responsible for printing the invoice...
 * The ShoppingCartDatabaseSaver interface allows for different implementations of saving the shopping cart data to various databases without modifying the existing code...
 */

import java.util.ArrayList;

class Product {

    String name;
    int price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }
}


// 1. ShoppingCart :- Only Responsible for adding products and calculating total* price...
class ShoppingCart {
    ArrayList<Product> products = new ArrayList<Product>();

    void addProduct(Product product) {
        products.add(product);
    }

    int calculateTotalPrice() {
        int totalPrice = 0;

        for (Product product : products) {
            totalPrice += product.price;
        }

        return totalPrice;
    }
}

// 2. ShoppingCartPrinter :- Only Responsible for printing the invoice...
class ShoppingCartPrinter {

    ShoppingCart shoppingCart;

    public ShoppingCartPrinter(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    void printInvoice() {

        System.out.println("==============================");
        System.out.println("---------Invoice--------------");
        System.out.println("==============================");

        for (Product product : shoppingCart.products) {
            System.out.println(product.name + " - " + product.price);
        }
        System.out.println("==============================");
        System.out.println("Total Price: " + shoppingCart.calculateTotalPrice());
        System.out.println("==============================");
    }
}

// Here, we have created an interface ShoppingCartDatabaseSaver that defines a method save() for saving the shopping cart data to a database...
// We have also created three classes that implement this interface: ShoppingCartDatabaseSaverMySQL, ShoppingCartDatabaseSaverMongoDB, and ShoppingCartDatabaseSaverFile.
//  Each of these classes has its own implementation of the save() method, which saves the shopping cart data to a different type of database (MySQL, MongoDB, or File)...
interface ShoppingCartDatabaseSaver {
    void save(ShoppingCartPrinter printer);
}

class ShoppingCartDatabaseSaverMySQL implements ShoppingCartDatabaseSaver {

    public void save(ShoppingCartPrinter printer) {
        printer.printInvoice();

        System.out.println("=======================================");
        System.out.println("Data saved to MySQL database...");
        System.out.println("=======================================");

    }
}

class ShoppingCartDatabaseSaverMongoDB implements ShoppingCartDatabaseSaver {

    public void save(ShoppingCartPrinter printer) {
        printer.printInvoice();

        System.out.println("=======================================");
        System.out.println("Data saved to MongoDB database...");
        System.out.println("=======================================");

    }
}

class ShoppingCartDatabaseSaverFile implements ShoppingCartDatabaseSaver {

    public void save(ShoppingCartPrinter printer) {
        printer.printInvoice();

        System.out.println("=======================================");
        System.out.println("Data saved to File database...");
        System.out.println("=======================================");

    }
}

public class OCPFollowed {

    public static void main(String[] args) {

        // Creating a shopping cart and adding products to it
        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(new Product("Laptop", 50000));
        cart.addProduct(new Product("Mouse", 2000));

        ShoppingCartPrinter printer = new ShoppingCartPrinter(cart);
        printer.printInvoice();

        // Saving the shopping cart data to different types of databases...
        // MySQL...
        ShoppingCartDatabaseSaver dbMySQL = new ShoppingCartDatabaseSaverMySQL();
        dbMySQL.save(printer);

        // MongoDB...
        ShoppingCartDatabaseSaver dbMongoDB = new ShoppingCartDatabaseSaverMongoDB();
        dbMongoDB.save(printer);

        // File...
        ShoppingCartDatabaseSaver dbFile = new ShoppingCartDatabaseSaverFile();
        dbFile.save(printer);

    }

}
