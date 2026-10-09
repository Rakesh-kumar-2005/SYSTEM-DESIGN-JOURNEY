
/**
 * SRPFollowed
 * Here, we have followed the Single Responsibility Principle (SRP) by separating the responsibilities of different classes...
 * The ShoppingCart class is responsible for managing the shopping cart and calculating the total price...
*/

import java.util.ArrayList;

public class SRPFollowed {
    
    // Product class is responsible for storing product details...
    static class Product {
    
        public String name;
        public int price;
    
        public Product(String name, int price) {
            this.name = name;
            this.price = price;
        }
    
    }
    
    // ShoppingCart class is responsible for managing the shopping cart and calculating total price...
    static class ShoppingCart {
    
        public static ArrayList<Product> products = new ArrayList<Product>();
    
        public void addProducts(Product p) {
            products.add(p);
        }
    
        public static ArrayList<Product> getProducts() {
            return products;
        }
    
        // ===============================================================================================================
        //  Following the Single Responsibility Principle (SRP) by separating the responsibilities of different classes...
        // ===============================================================================================================
    
        // Only responsible for calculating the total price of the shopping cart...
        public int calculateTotalPrice() {
            int totalPice = 0;
    
            for (Product product : products) {
                totalPice += product.price;
            }
    
            return totalPice;
        }
    
    }
    
    // ShoppingCartPrinter class is responsible for printing the invoice of the shopping cart...
    static class ShoppingCartPrinter {
    
        private ShoppingCart shoppingCart;
    
        public ShoppingCartPrinter(ShoppingCart shoppingCart) {
            this.shoppingCart = shoppingCart;
        }
    
        // Only responsible for printing the invoice of the shopping cart and fetch the total price from the ShoppingCart class...
        public void printInvoice() {
            System.out.println("Shopping Cart invoice :- ");
    
            for (Product product : shoppingCart.getProducts()) {
                System.out.println(product.name + " - Rs" + product.price);
            }
    
            System.out.println("Total Price of the shopping cart is : " + shoppingCart.calculateTotalPrice());
        }
    
    }
    
    // ShoppingCartDatabaseSaver class is responsible for saving the shopping cart to the database...
    static class ShoppingCartDatabaseSaver {
    
        private ShoppingCartPrinter cart;
    
        public ShoppingCartDatabaseSaver(ShoppingCartPrinter cart) {
            this.cart = cart;
        }
    
        // Only responsible for saving the shopping cart to the database and fetch the invoice from the ShoppingCartPrinter class...
        public void saveToDatabase() {
            cart.printInvoice();
            System.out.println("Saving to Database...");
        }
    
    }

    public static void main(String[] args) {

        // Creating a shopping cart and adding products to it...
        ShoppingCart cart = new ShoppingCart();

        cart.addProducts(new Product("Laptop", 67000));
        cart.addProducts(new Product("Iphone", 167000));
        cart.addProducts(new Product("Tablet", 45000));

        System.out.println("Total Price of the cart is : " + cart.calculateTotalPrice());

        // Creating a shopping cart printer and printing the invoice...
        ShoppingCartPrinter invoice = new ShoppingCartPrinter(cart);
        invoice.printInvoice();

        // Creating a shopping cart database saver and saving the shopping cart to the database...
        ShoppingCartDatabaseSaver dbSaver = new ShoppingCartDatabaseSaver(invoice);
        dbSaver.saveToDatabase();

    }

}