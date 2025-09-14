import java.util.*;

// product class 
class product {
    private String name;
    private double price;
    private int quantity;

    public product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String get_name() {
        return name;
    }

    public double get_price() {
        return price;
    }

    public int get_quantity() {
        return quantity;
    }

    public double get_total_price() {
        return price * quantity;
    }
}

// discount system 
abstract class discount {
    public abstract double apply(double total, ArrayList<product> products);
}

// festive discount
class festivediscount extends discount {
    @Override
    public double apply(double total, ArrayList<product> products) {
        return total * 0.9; // 10% off
    }
}

// bulk discount
class bulkdiscount extends discount {
    @Override
    public double apply(double total, ArrayList<product> products) {
        for (product p : products) {
            if (p.get_quantity() > 5) {
                return total * 0.8; // 20% off
            }
        }
        return total;
    }
}

// payment system
interface payment {
    void pay(double amount);
}

// cash payment
class cashpayment implements payment {
    @Override
    public void pay(double amount) {
        System.out.println("total amount payable: ₹" + amount);
    }
}

// main app
public class shoppingcart {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {

            System.out.print("enter number of products: ");
            int n = Integer.parseInt(sc.nextLine());

            ArrayList<product> cart = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                System.out.print("enter product (name price quantity): ");
                String[] input = sc.nextLine().split(" ");

                String name = input[0];
                double price = Double.parseDouble(input[1]);
                int qty = Integer.parseInt(input[2]);

                cart.add(new product(name, price, qty));
            }

            System.out.print("enter discount type (festive/bulk): ");
            String discountType = sc.nextLine().trim().toLowerCase();

            double total = 0;
            System.out.println("\n--- cart details ---");
            for (product p : cart) {
                System.out.println("product: " + p.get_name() +
                                   ", price: " + p.get_price() +
                                   ", quantity: " + p.get_quantity());
                total += p.get_total_price();
            }

            discount d;
            if (discountType.equals("festive")) {
                d = new festivediscount();
            } else {
                d = new bulkdiscount();
            }

            double final_amount = d.apply(total, cart);

            payment paymethod = new cashpayment();
            paymethod.pay(final_amount);
        }
    }
}
