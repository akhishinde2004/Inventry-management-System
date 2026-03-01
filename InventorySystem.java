import java.util.ArrayList;
import java.util.Scanner;

class Product {

    private int id;
    private String name;
    private int quantity;

    public Product(int id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addStock(int qty) {
        if (qty <= 0) {
            System.out.println("Quantity must be greater than 0.");
            return;
        }
        quantity += qty;
        System.out.println("Stock updated successfully.");
    }

    public void sell(int qty) {
        if (qty <= 0) {
            System.out.println("Quantity must be greater than 0.");
        } else if (qty > quantity) {
            System.out.println("Insufficient stock.");
        } else {
            quantity -= qty;
            System.out.println("Product sold successfully.");
        }
    }

    public void display() {
        System.out.println("ID: " + id + " | Name: " + name + " | Stock: " + quantity);
    }
}

public class InventorySystem {

    private static ArrayList<Product> products = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        do {
            showMenu();
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    updateStock();
                    break;
                case 3:
                    sellProduct();
                    break;
                case 4:
                    viewInventory();
                    break;
                case 5:
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 5);
    }

    private static void showMenu() {
        System.out.println("\n===== Inventory Management System =====");
        System.out.println("1. Add Product");
        System.out.println("2. Update Stock");
        System.out.println("3. Sell Product");
        System.out.println("4. View Inventory");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static Product findProduct(int id) {
        for (Product p : products) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    private static void addProduct() {

        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        if (findProduct(id) != null) {
            System.out.println("Product with this ID already exists.");
            return;
        }

        System.out.print("Enter Product Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Quantity: ");
        int quantity = sc.nextInt();

        if (quantity < 0) {
            System.out.println("Quantity cannot be negative.");
            return;
        }

        products.add(new Product(id, name, quantity));
        System.out.println("Product added successfully.");
    }

    private static void updateStock() {

        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();

        Product product = findProduct(id);

        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        System.out.print("Enter quantity to add: ");
        int qty = sc.nextInt();

        product.addStock(qty);
    }

    private static void sellProduct() {

        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();

        Product product = findProduct(id);

        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        System.out.print("Enter quantity to sell: ");
        int qty = sc.nextInt();

        product.sell(qty);
    }

    private static void viewInventory() {

        if (products.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        System.out.println("\n----- Current Inventory -----");
        for (Product p : products) {
            p.display();
        }
    }
}