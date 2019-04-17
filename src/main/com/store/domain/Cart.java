package main.com.store.domain;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Cart {
    private Map<String, CartItem> map;
    private double total;

    public Map<String, CartItem> getMap() {
        return map;
    }

    public void setCartItems(Map<String, CartItem> cartItems) {
        this.map = cartItems;
    }

    public Cart() {
    }

    public double getTotal() {
        double sum = 0.0;
        for (Map.Entry<String, CartItem> entry : map.entrySet()) {
            sum += entry.getValue().getTotal();
        }
        total = sum;
        return total;
    }


    public Cart(Map<String, CartItem> cartItems) {
        this.map = cartItems;
    }

    public Collection<CartItem> getCartItems() {
        return map.values();
    }

    public void removeCartItem(String pid) {
        if (map.containsKey(pid)) {
            map.remove(pid);
        }
    }
}
