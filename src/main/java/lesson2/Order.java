package lesson2;

import java.util.Date;

public class Order {
    private long id;
    private String productName;
    private int price;
    private Date dateOrderes;
    private Date dateConfirmed;

    public Order(long id, String productName, int price, Date dateOrderes, Date dateConfirmed) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.dateOrderes = dateOrderes;
        this.dateConfirmed = dateConfirmed;
    }

    public long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public int getPrice() {
        return price;
    }

    public Date getDateOrderes() {
        return dateOrderes;
    }

    public Date getDateConfirmed() {
        return dateConfirmed;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", dateOrderes=" + dateOrderes +
                ", dateConfirmed=" + dateConfirmed +
                '}';
    }
}
