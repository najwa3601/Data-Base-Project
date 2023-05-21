package DataBase;


public class Product {
    private int Product_ID;
    private String name;
    private String type;
    private int cost_price;
    private int Market_Price;
    private int Net_profit;
    private String Expiry_Date;
    private int Company_ID;

    public Product(int Product_ID, String name, String type,
                   int cost_price, int Market_Price, int Net_profit, String Expiry_Date, int Company_ID) {

        this.Product_ID = Product_ID;
        this.name = name;
        this.type = type;
        this.cost_price = cost_price;
        this.Market_Price = Market_Price;
        this.Net_profit = Net_profit;
        this.Expiry_Date = Expiry_Date;
        this.Company_ID = Company_ID;
    }

    public int getProduct_ID() {
        return Product_ID;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getCost_price() {
        return cost_price;
    }

    public int getMarket_Price() {
        return Market_Price;
    }

    public int getNet_profit() {
        return Net_profit;
    }

    public String getExpiry_Date() {
        return Expiry_Date;
    }

    public int getCompany_ID() {
        return Company_ID;
    }
}
