package CodeAlpha;

import java.util.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Requirement: Use OOP to manage stocks, users, and transactions.
 * Requirement: Implement market data display and buy/sell operations.
 */
class Stock {
    private String symbol;
    private double currentPrice;

    public Stock(String symbol, double currentPrice) {
        this.symbol = symbol;
        this.currentPrice = currentPrice;
    }

    public String getSymbol() { return symbol; }
    public double getPrice() { return currentPrice; }

    @Override
    public String toString() {
        return String.format("%-10s | Price: $%.2f", symbol, currentPrice);
    }
}

/**
 * Requirement: Track transactions and portfolio performance.
 */
class Transaction {
    private String symbol;
    private int quantity;
    private double priceAtTransaction;
    private String type; // BUY or SELL
    private LocalDateTime timestamp;

    public Transaction(String symbol, int quantity, double price, String type) {
        this.symbol = symbol;
        this.quantity = quantity;
        this.priceAtTransaction = price;
        this.type = type;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("[%s] %-4s %d shares of %-5s @ $%.2f", 
                timestamp.format(dtf), type, quantity, symbol, priceAtTransaction);
    }
}

class User {
    private String name;
    private double cashBalance;
    private Map<String, Integer> portfolio = new HashMap<>();
    private List<Transaction> history = new ArrayList<>();

    public User(String name, double balance) {
        this.name = name;
        this.cashBalance = balance;
    }

    // Requirement: Buy Operation
    public void buyStock(Stock s, int qty) {
        double totalCost = s.getPrice() * qty;
        if (totalCost <= cashBalance) {
            cashBalance -= totalCost;
            portfolio.put(s.getSymbol(), portfolio.getOrDefault(s.getSymbol(), 0) + qty);
            history.add(new Transaction(s.getSymbol(), qty, s.getPrice(), "BUY"));
            System.out.println(">>> Purchase successful.");
        } else {
            System.out.println(">>> Error: Insufficient funds.");
        }
    }

    // Requirement: Sell Operation
    public void sellStock(Stock s, int qty) {
        int owned = portfolio.getOrDefault(s.getSymbol(), 0);
        if (owned >= qty) {
            cashBalance += s.getPrice() * qty;
            portfolio.put(s.getSymbol(), owned - qty);
            if(portfolio.get(s.getSymbol()) == 0) portfolio.remove(s.getSymbol());
            history.add(new Transaction(s.getSymbol(), qty, s.getPrice(), "SELL"));
            System.out.println(">>> Sale successful.");
        } else {
            System.out.println(">>> Error: Not enough shares owned.");
        }
    }

    /**
     * Requirement: Track portfolio performance over time.
     * This calculates the current market value of holdings.
     */
    public void showPerformance(StockMarket market) {
        System.out.println("\n===== PORTFOLIO PERFORMANCE =====");
        double totalMarketValue = 0;
        
        if (portfolio.isEmpty()) {
            System.out.println("No active holdings.");
        } else {
            System.out.printf("%-10s | %-8s | %-12s\n", "Symbol", "Shares", "Market Value");
            for (Map.Entry<String, Integer> entry : portfolio.entrySet()) {
                Stock s = market.findStock(entry.getKey());
                double value = s.getPrice() * entry.getValue();
                totalMarketValue += value;
                System.out.printf("%-10s | %-8d | $%.2f\n", entry.getKey(), entry.getValue(), value);
            }
        }
        
        System.out.println("---------------------------------");
        System.out.printf("Cash Balance:   $%.2f\n", cashBalance);
        System.out.printf("Assets Value:   $%.2f\n", totalMarketValue);
        System.out.printf("Total Equity:   $%.2f\n", (cashBalance + totalMarketValue));
        System.out.println("=================================");
    }

    // Requirement: File I/O to persist portfolio data.
    public void savePortfolioData() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("portfolio_data.txt"))) {
            pw.println("--- GUEST: " + name + " ---");
            pw.println("Balance," + cashBalance);
            pw.println("--- HOLDINGS ---");
            for (String key : portfolio.keySet()) {
                pw.println(key + "," + portfolio.get(key));
            }
            pw.println("--- TRANSACTION HISTORY ---");
            for (Transaction t : history) {
                pw.println(t.toString());
            }
            System.out.println(">>> Data persisted to portfolio_data.txt");
        } catch (IOException e) {
            System.out.println(">>> Error: Could not save data.");
        }
    }
}

class StockMarket {
    private List<Stock> stocks = new ArrayList<>();

    public StockMarket() {
        // Sample market data
        stocks.add(new Stock("AAPL", 175.50));
        stocks.add(new Stock("GOOGL", 142.10));
        stocks.add(new Stock("MSFT", 390.25));
        stocks.add(new Stock("TSLA", 210.05));
        stocks.add(new Stock("AMZN", 155.30));
    }

    // Requirement: Market Data Display
    public void displayMarket() {
        System.out.println("\n--- LIVE MARKET DATA ---");
        System.out.println("Symbol     | Current Price");
        System.out.println("-----------|--------------");
        for (Stock s : stocks) {
            System.out.println(s);
        }
    }

    public Stock findStock(String symbol) {
        for (Stock s : stocks) {
            if (s.getSymbol().equalsIgnoreCase(symbol)) return s;
        }
        return null;
    }
}

public class StockTradingPlatform {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StockMarket market = new StockMarket();
        User user = new User("Alpha Trader", 50000.00); // Start with $50k

        while (true) {
            System.out.println("\nSTOCK TRADING TERMINAL");
            System.out.println("1. Market Watch");
            System.out.println("2. Trade: Buy Stock");
            System.out.println("3. Trade: Sell Stock");
            System.out.println("4. My Portfolio & Performance");
            System.out.println("5. Save My Data");
            System.out.println("6. Logout");
            System.out.print("Action > ");

            int choice = 0;
            if(sc.hasNextInt()) {
                choice = sc.nextInt();
            } else {
                sc.next(); // Clear buffer
                continue;
            }

            switch (choice) {
                case 1:
                    market.displayMarket();
                    break;
                case 2:
                    System.out.print("Enter Symbol to BUY: ");
                    String bSym = sc.next().toUpperCase();
                    Stock bStock = market.findStock(bSym);
                    if (bStock != null) {
                        System.out.print("Quantity to purchase: ");
                        user.buyStock(bStock, sc.nextInt());
                    } else System.out.println("Invalid Symbol.");
                    break;
                case 3:
                    System.out.print("Enter Symbol to SELL: ");
                    String sSym = sc.next().toUpperCase();
                    Stock sStock = market.findStock(sSym);
                    if (sStock != null) {
                        System.out.print("Quantity to sell: ");
                        user.sellStock(sStock, sc.nextInt());
                    } else System.out.println("Invalid Symbol.");
                    break;
                case 4:
                    user.showPerformance(market);
                    break;
                case 5:
                    user.savePortfolioData();
                    break;
                case 6:
                    System.out.println("Closing connection... Goodbye.");
                    sc.close();
                    return;
                default:
                    System.out.println("Command not recognized.");
            }
        }
    }
}
