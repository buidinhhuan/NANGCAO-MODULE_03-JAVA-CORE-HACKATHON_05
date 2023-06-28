package ra.model;

public class CartItem {
    private String cartItemId;
    private String product;
    private double price;
    private int quantity;

    public CartItem() {
    }

    public CartItem(String cartItemId, String product, double price, int quantity) {
        this.cartItemId = cartItemId;
        this.product = product;
        this.price = price;
        this.quantity = quantity;
    }

    public String getCartItemId() {
        return cartItemId;
    }

    public String getProduct() {
        return product;
    }

    public double getPrice() {
        return price ;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setCartItemId(String cartItemId) {
        this.cartItemId = cartItemId;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setPrice(double price) {
        this.price = price * quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return "Giỏ hàng : " +
                "| cartItemId=" + cartItemId + "|"+
                ",| product='" + product + '\'' + "|"+
                ",| price=" + price +"|"+
                ",| quantity=" + quantity + "|"
                ;
    }
}
