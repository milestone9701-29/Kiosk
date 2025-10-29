package V6;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Kiosk {
    private final List<Menu> menus;
    private final List<CartItem> cart = new ArrayList<>();

    public Kiosk(List<Menu> menus) {
        if(menus == null) throw new IllegalArgumentException("menus must not be null"); // Null
        this.menus = menus;
    }

    public void start(Scanner s) {
        Screen current = new MainMenuScreen(this); // 최초 화면
        while(true) {
            clear();

            current.render();
            current = current.handleInput(s);

            if(current==null){
                // null 반환 -> 종료
                System.out.println("프로그램 종료.");
                break;
            }
        }
    }
    // 로직, 상태 관리.
    // R
    public List<Menu> getMenus() {
        return menus;
    }
    public List<CartItem> getCart() {
        return cart;
    }
    // 추가
    public void addToCart(MenuItem chosen) {
        for (CartItem ci : cart) {
            if (ci.getItem().getName().equals(chosen.getName())) {
                ci.incrementQuantity(); // Quantity++
                return;
            }
        }
        cart.add(new CartItem(chosen)); // 같은 이름의 아이템이 없음 -> 등록
    }
    // 비우기
    public void clearCart() {
        cart.clear();
    }
    public double calcTotal() { // 총 합
        return cart.stream().mapToDouble(CartItem::getLineTotal).sum();
    }
    /*public void removeFromCartByName(String name) { // cart에서 특정 이름인 항목을 제외한 새 리스트로 교체
        // Java 16 미만 : collect(Collectors. toList());
        // import java.util.stream.Collectors;
        var filtered = cart.stream().filter(ci->!ci.getItem().getName().equals(name)).toList();
        cart.clear();
        cart.addAll(filtered);
    } */
    private void clear() {
        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
    }
}