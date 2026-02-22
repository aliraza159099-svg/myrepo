package CodeAlpha;

import java.util.*;
import java.io.*;
import java.time.LocalDateTime;

class Stock {
    private String symbol;
    private double price;

    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    public String toString() {
        return symbol + " : $" + price;
    }
}

class Transaction {
    String stock;
    int quantity;
    double price;
    String type;
    LocalDateTime time;

    public Transaction(String stock, int quantity, double price, String type) {
        this.stock = stock;
        this.quantity = quantity;
        this.price = price;
        this.type = type;
        this.time = LocalDateTime.now();
    }

    public String toString() {
        return type + " " + quantity + " " + stock + " @ $" + price + " on " + time;
    }
}

class User {
    private String name;
    private double balance;
    private Map<String, Integer> portfolio = new HashMap<>();
    private List<Transaction> history = new ArrayList<>();

    public User(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public void buyStock(Stock s, int qty) {
        double cost = s.getPrice() * qty;
        if (cost <= balance) {
            balance -= cost;
            portfolio.put(s.getSymbol(),
                    portfolio.getOrDefault(s.getSymbol(), 0) + qty);
            history.add(new Transaction(s.getSymbol(), qty, s.getPrice(), "BUY"));
            System.out.println("Stock bought successfully.");
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void sellStock(Stock s, int qty) {
        int owned = portfolio.getOrDefault(s.getSymbol(), 0);
        if (owned >= qty) {
            balance += s.getPrice() * qty;
            portfolio.put(s.getSymbol(), owned - qty);
            history.add(new Transaction(s.getSymbol(), qty, s.getPrice(), "SELL"));
            System.out.println("Stock sold successfully.");
        } else {
            System.out.println("Not enough shares.");
        }
    }

    public void showPortfolio() {
        System.out.println("\n--- Portfolio ---");
        for (String key : portfolio.keySet()) {
            System.out.println(key + " : " + portfolio.get(key) + " shares");
        }
        System.out.println("Balance: $" + balance);
    }

    public void saveToFile() {
        try (PrintWriter pw = new PrintWriter("portfolio.txt")) {
            pw.println("User: " + name);
            pw.println("Balance: " + balance);
            for (String key : portfolio.keySet()) {
                pw.println(key + "," + portfolio.get(key));
            }
            System.out.println("Portfolio saved to file.");
        } catch (Exception e) {
            System.out.println("File error.");
        }
    }
}

class StockMarket {
    List<Stock> stocks = new ArrayList<>();

    public StockMarket() {
        stocks.add(new Stock("AAPL", 150));
        stocks.add(new Stock("GOOG", 2800));
        stocks.add(new Stock("MSFT", 300));
    }

    public void showMarket() {
        System.out.println("\n--- Market Data ---");
        for (Stock s : stocks) {
            System.out.println(s);
        }
    }

    public Stock findStock(String symbol) {
        for (Stock s : stocks) {
            if (s.getSymbol().equalsIgnoreCase(symbol))
                return s;
        }
        return null;
    }
}

public class StockTradingPlatform {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StockMarket market = new StockMarket();
        User user = new User("Ali", 10000);

        while (true) {
            System.out.println("\n1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Save Portfolio");
            System.out.println("6. Exit");
            System.out.print("Choose: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    market.showMarket();
                    break;
                case 2:
                    System.out.print("Stock symbol: ");
                    String bSym = sc.next();
                    System.out.print("Quantity: ");
                    int bQty = sc.nextInt();
                    Stock bStock = market.findStock(bSym);
                    if (bStock != null)
                        user.buyStock(bStock, bQty);
                    else
                        System.out.println("Stock not found.");
                    break;
                case 3:
                    System.out.print("Stock symbol: ");
                    String sSym = sc.next();
                    System.out.print("Quantity: ");
                    int sQty = sc.nextInt();
                    Stock sStock = market.findStock(sSym);
                    if (sStock != null)
                        user.sellStock(sStock, sQty);
                    else
                        System.out.println("Stock not found.");
                    break;
                case 4:
                    user.showPortfolio();
                    break;
                case 5:
                    user.saveToFile();
                    break;
                case 6:
                    System.out.println("Exiting system. Thank you!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
