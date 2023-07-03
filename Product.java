package packageEx;
import java.util.Scanner;

public class Product {
    private String productName;
    private String name;
    private int price;
    private int stock;
    
    public Product(String productName, int price, int stock) {
        this.productName = productName;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
    
    //메소드추가
    public String getName() {
        return name;
    }
    public String getProductName() {
        return productName;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }
    
    public void setStock(int stock) {
        this.stock = stock;
    }
    
    public int getProductStock() {
        return stock;
    }
    
    public void decreaseStock(int quantity) {
        stock -= quantity;
    }
    
    public boolean sellProduct(int num) {
        if (stock >= num) {
            stock -= num;
            return true;
        } else {
            return false;
        }
    }
    
    public void showInfo() {
        System.out.println("제품명: " + productName);
        System.out.println("가격: " + price);
        System.out.println("재고: " + stock);
    }

	public void addStock(int stockToAdd) {
		// TODO Auto-generated method stub
		
	}
}
