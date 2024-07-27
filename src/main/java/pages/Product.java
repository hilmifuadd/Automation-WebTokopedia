package pages;

public class Product {
    private String website;
    private String name;
    private int price;
    private String url;

    public Product(String website, String name, int price, String url) {
        this.website = website;
        this.name = name;
        this.price = price;
        this.url = url;
    }

    public String getWebsite() {
        return website;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Website: " + website + ", Product: " + name + ", Price: " + price + ", URL: " + url;
    }
}
