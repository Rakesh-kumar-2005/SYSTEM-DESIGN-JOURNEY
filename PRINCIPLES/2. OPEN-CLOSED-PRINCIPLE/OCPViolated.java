import java.util.ArrayList;

/**
 * This class violates the Open/Closed Principle (OCP) because it is not open for extension and closed for modification...
 * Any changes to the behavior of this class would require modifying its existing code, which can lead to issues in maintainability and scalability...
*/

class Product{

    String name;
    int price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }
}

// 1. ShoppingCart :- Only Responsible for adding products and calculating total price...
class ShoppingCart {
    ArrayList<Product> products = new ArrayList<Product>();

    void addProduct(Product product){
        products.add(product);
    }

    int calculateTotalPrice(){
        int totalPrice = 0;

        for(Product product : products){
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

    void printInvoice(){
        
        System.out.println("==============================");
        System.out.println("---------Invoice--------------");
        System.out.println("==============================");

        for(Product product : shoppingCart.products){
            System.out.println(product.name + " - " + product.price);
        }
        System.out.println("==============================");
        System.out.println("Total Price: " + shoppingCart.calculateTotalPrice());
        System.out.println("==============================");
    }
}

// 3. ShoppingCartDatabaseSaver :- Only Responsible for saving the invoice to the database...
// We are saving the invoice to different types of databases like MySQL, MongoDB, and File database...
// But in future we may need to save the invoice to other types of databases like PostgreSQL, Oracle, etc and more...
// And we might wanna add some more functionality to the existing methods like saveToDatabaseMySQL, saveToDatabaseMongoDB, and saveToFileDatabase...
// So, we will have to modify the existing methods to add the new functionality which violates the Open/Closed Principle (OCP) because we are modifying the existing code instead of extending it...
class ShoppingCartDatabaseSaver{
    ShoppingCart shoppingCart;

    public ShoppingCartDatabaseSaver(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    void saveToDatabaseMySQL(ShoppingCartPrinter cart){
        cart.printInvoice();

        System.out.println("=======================================");
        System.out.println("Data saved MySQL to database...");
        System.out.println("=======================================");
    }
    
    void saveToDatabaseMongoDB(ShoppingCartPrinter cart){
        cart.printInvoice();

        System.out.println("=======================================");
        System.out.println("Data saved to MongoDB database...");
        System.out.println("=======================================");
    }
    void saveToFileDatabase(ShoppingCartPrinter cart){
        cart.printInvoice();

        System.out.println("=======================================");
        System.out.println("Data saved to File database...");
        System.out.println("=======================================");
    }
}

public class OCPViolated {
    
    public static void main(String[] args) {

        // Creating a shopping cart and adding products to it
        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(new Product("Laptop", 50000));
        cart.addProduct(new Product("Mouse", 2000));

        ShoppingCartPrinter printer = new ShoppingCartPrinter(cart);
        printer.printInvoice();

        ShoppingCartDatabaseSaver db = new ShoppingCartDatabaseSaver(cart);
        db.saveToDatabaseMySQL(printer);
        db.saveToDatabaseMongoDB(printer);
        db.saveToFileDatabase(printer);
        
    }

}
