package V6;

import java.util.Scanner;

public class AddToCartConfirmScreen implements Screen {
    private final Kiosk kiosk;
    private final MenuItem chosen;
    private final Screen returnTo; // CategoryScreen으로 돌아가기.

    public AddToCartConfirmScreen(Kiosk kiosk, MenuItem chosen, Screen returnTo) {
        this.kiosk = kiosk;
        this.chosen = chosen;
        this.returnTo = returnTo;
    }

    @Override
    public void render() {
        System.out.printf("\"%s | W %3.1f | %s\"%n", chosen.getName(), chosen.getPrice(), chosen.getDesc());
        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인        2. 취소");
        System.out.print("> ");
    }

    @Override
    public Screen handleInput(Scanner s) {
        String input = s.nextLine().trim();
        if (input.equals("1")) {
            kiosk.addToCart(chosen);
            System.out.printf("%s 이(가) 장바구니에 추가되었습니다.%n", chosen.getName());
            System.out.println("Enter를 누르면 돌아갑니다...");
            s.nextLine();
            return returnTo;
        } else {
            System.out.println("취소되었습니다. Enter를 누르면 돌아갑니다...");
            s.nextLine();
            return returnTo;
        }
    }
}

