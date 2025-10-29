package V6;

public class CartItem {
    // 속성
    private final MenuItem item; // 종류
    private int quantity; // 수량
    // 생성자
    public CartItem(MenuItem item) {
        this.item = item;
        this.quantity = 1;
    }
    // 기능
    public MenuItem getItem() { return item; }
    public int getQuantity() { return quantity; }
    public void incrementQuantity() { quantity++; }
    public double getLineTotal() { return item.getPrice() * quantity; }
}