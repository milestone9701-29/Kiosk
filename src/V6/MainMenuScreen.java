package V6;

import java.util.Scanner;
import java.util.List;

public class MainMenuScreen implements Screen {
    private final Kiosk kiosk;
    public MainMenuScreen(Kiosk kiosk) {
        this.kiosk = kiosk;
    }

    @Override
    public void render() {
        System.out.println("[ MAIN MENU ]");
        List<Menu> menus = kiosk.getMenus();
        for (int i = 0; i<menus.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, menus.get(i).getCategoryName());
        }
        System.out.println("0. 종료      | 종료");
        System.out.println();

        if(!kiosk.getCart().isEmpty()) { // 장바구니가 비어 있지 않다면.
            System.out.println("[ ORDER MENU ]");
            System.out.println("4. Orders       | 장바구니를 확인 후 주문합니다.");
            System.out.println("5. Cancel       | 진행중인 주문을 취소합니다.");
        }
        System.out.print("> ");
    }
    @Override
    public Screen handleInput(Scanner s) { // 입력
        String input = s.nextLine().trim();
        // isEmpty() 문자열 길이 0 : true
        // isBlank() : 문자열이 비어있거나, 빈 공백으로 이루어진 경우. : true
        if(input.isBlank()) { // Main :
            return this;
        }
        // 끝내기
        if(input.equals("0")){
            return null;
        }
        // 주문 확인 : CartReviewScreen()
        if (input.equals("4")) {
            if (kiosk.getCart().isEmpty()) {
                System.out.println("장바구니가 비어있습니다. Enter를 눌러 계속...");
                s.nextLine();
                return this;
            }
            return new CartReviewScreen(kiosk);
        }
        // 주문 취소
        if(input.equals("5")) {
            if (kiosk.getCart().isEmpty()) { // 카트가 비어있는 경우.
                System.out.println("취소할 주문이 없습니다. Enter를 눌러 계속...");
                s.nextLine();
                return this;
            }
            kiosk.clearCart();
            System.out.println("진행중인 주문을 취소했습니다.");
            System.out.println("Enter를 누르면 메뉴판으로 돌아갑니다...");
            s.nextLine();

            return this;
        }
        // categorySelect
        Integer idx = toInt(input);
        List<Menu> menus = kiosk.getMenus();
        if (idx==null||idx<1||idx>menus.size()) {
            System.out.println("잘못된 입력입니다. Enter를 눌러 계속...");
            s.nextLine();
            return this;
        }
        Menu selected = menus.get(idx-1); // 0 based
        return new CategoryScreen(kiosk, selected);
    }
    // int 파싱
    private Integer toInt(String s){
        try { return Integer.parseInt(s); }
        catch (NumberFormatException e) { return null; }
    }
}