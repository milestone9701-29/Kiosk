package V6;

import java.util.Scanner;

// askDiscountAndApply -> DiscountScreen
public class DiscountScreen implements Screen {

    private final Kiosk kiosk;

	public DiscountScreen(Kiosk kiosk) {
        this.kiosk=kiosk;
    }

    @Override
    public void render() {
        System.out.println("할인 정보를 입력해주세요.");
        System.out.println("1. 국가유공자 : 10%");
        System.out.println("2. 군인     :  5%");
        System.out.println("3. 학생     :  3%");
        System.out.println("4. 일반     :  0%");
        System.out.print("> ");
    }

    @Override
    public Screen handleInput(Scanner s) {
        String input = s.nextLine().trim();
        CustomerType type = CustomerType.fromOption(input);
        if(type==null) {
            System.out.println("잘못된 입력입니다. Enter를 눌러 다시 선택...");
            s.nextLine();
            return this;
        }
        double total = kiosk.calcTotal();
        double discounted = type.applyDiscount(total);

        System.out.printf("주문이 완료되었습니다. 금액은 W %3.1f 입니다.%n", discounted);
        kiosk.clearCart();
        System.out.println("Enter를 누르면 메뉴판으로 돌아갑니다...");
        s.nextLine();

        return new MainMenuScreen(kiosk);
    }
}