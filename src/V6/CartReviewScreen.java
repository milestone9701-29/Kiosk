package V6;

import java.util.Scanner;
import java.util.List;


// showOrderAndMaybeCheckout -> CartReviewScreen
public class CartReviewScreen implements Screen { // 재확인.
    private final Kiosk kiosk;
    public CartReviewScreen(Kiosk kiosk) {
        this.kiosk=kiosk;
    }

    @Override
    public void render() {
        System.out.println("아래와 같이 주문 하시겠습니까?\n");
        System.out.println("[ Orders ]");

        List<CartItem> cart = kiosk.getCart();
        cart.stream().forEach(ci -> { // forEach.
            MenuItem item = ci.getItem();
            System.out.printf("%s | W %3.1f | %s | 수량: %d%n", item.getName(), item.getPrice(), item.getDesc(), ci.getQuantity());
        });

        double total = kiosk.calcTotal();
        System.out.println();
        System.out.println("[ Total ]");
        System.out.printf("W %3.1f%n", total);

        System.out.println();
        System.out.println("1. 주문      2. 메뉴판");
        System.out.print("> ");
    }


    @Override
    public Screen handleInput(Scanner s){
        String input = s.nextLine().trim();
        if(input.equals("2")) {
            // return to 메인화면
            return new MainMenuScreen(kiosk);
        }
        if (input.equals("1")) {
            // discount
            return new DiscountScreen(kiosk);
        }

        System.out.println("잘못된 입력입니다. Enter를 눌러 계속...");
        s.nextLine();
        return this;
    }
}