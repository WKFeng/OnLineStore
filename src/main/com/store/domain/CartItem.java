package main.com.store.domain;

public class CartItem {
    private Product product;
    private int num;
    private double total;

    public CartItem() {
    }

    public CartItem(Product product, int num) {
        this.product = product;
        this.num = num;
        this.total = num * product.getShop_price();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getTotal() {
        return total;
    }


}
