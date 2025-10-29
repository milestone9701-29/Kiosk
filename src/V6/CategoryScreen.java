package V6;

import java.util.List;
import java.util.Scanner;

public class CategoryScreen implements Screen {
    private final Kiosk kiosk;
    private final Menu menu; // Burgers, Desserts etc.

    public CategoryScreen(Kiosk kiosk, Menu menu) {
        this.kiosk = kiosk;
        this.menu = menu;
    }

    @Override
    public void render() {
        System.out.printf("[ %s MENU ]%n%n", menu.getCategoryName().toUpperCase()); // 대문자.
        List<MenuItem> items = menu.getItems();
        for (int i=0;i<items.size();i++) {
            MenuItem m= items.get(i);
            System.out.printf("%d. %-14s | W %3.1f | %s%n", i + 1, m.getName(), m.getPrice(), m.getDesc());
        }
        System.out.println("0. 뒤로가기");
        System.out.print("> ");
    }

    @Override
    public Screen handleInput(Scanner s) {
        String input = s.nextLine().trim();
        if(input.isBlank()) {
            return this;
        }
        if(input.equals("0")) {
            // 메인 메뉴로..
            return new MainMenuScreen(kiosk);
        }

        Integer idx = toInt(input);
        List<MenuItem> items = menu.getItems();
        if (idx==null||idx<1||idx>items.size()) {
            System.out.println("잘못된 입력입니다. Enter를 누르면 계속...");
            s.nextLine();
            return this;
        }

        MenuItem chosen = items.get(idx - 1); // 추가
        // 다음 화면 AddToCartComfirmScreen으로.
        return new AddToCartConfirmScreen(kiosk, chosen, this);
    }
    private Integer toInt(String s) {
        try { return Integer.parseInt(s); }
        catch (NumberFormatException e) { return null; }
    }
}