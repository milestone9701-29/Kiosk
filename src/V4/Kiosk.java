package V4;
import java.util.List;
import java.util.Scanner;


public class Kiosk {
    private final List<Menu> menus;

    public Kiosk(List<Menu> menus) {
        if (menus == null) throw new IllegalArgumentException("menus must not be null");
        this.menus = menus;
    }

    public void start(Scanner s) {
        while (true) {
            clear();

            printMenu();
            System.out.print("> ");
            String input = s.nextLine().trim();
            if (input.isBlank()) continue;

            if (input.equals("0")) {
                System.out.println("프로그램 종료.");
                break;
            }

            Integer categoryIndex = toInt(input);
            if (categoryIndex == null || categoryIndex < 1 || categoryIndex > menus.size()) {
                System.out.println("잘못된 입력입니다. Enter를 눌러 계속...");
                s.nextLine();
                continue;
            }
            Menu selected = menus.get(categoryIndex - 1);
            runCategoryLoop(s, selected);

        }
    }

    private void runCategoryLoop(Scanner s, Menu menu) {
        while (true) {
            clear();
            System.out.printf("[ %s MENU ]%n%n", menu.getCategoryName().toUpperCase()); // 대문자 고정.

            List<MenuItem> items = menu.getItems();
            for (int i = 0; i < items.size(); i++) {
                MenuItem m = items.get(i);
                System.out.printf("%d. %-14s | W %3.1f | %s%n", i + 1, m.getName(), m.getPrice(), m.getDesc());
            }
            System.out.println("0. 뒤로가기");
            System.out.print("> ");

            String input = s.nextLine().trim();
            if (input.isBlank()) continue;
            if (input.equals("0")) return;

            Integer idx = toInt(input);
            if (idx == null || idx < 1 || idx > items.size()) {
                System.out.println("잘못된 입력입니다. Enter를 누르면 계속...");
                s.nextLine();
                continue;
            }

            MenuItem chosen = items.get(idx - 1);
            System.out.printf("선택한 메뉴: %s | W %3.1f | %s%n",
                    chosen.getName(), chosen.getPrice(), chosen.getDesc());
            System.out.println("\n엔터를 누르면 카테고리 메뉴로 돌아갑니다...");
            s.nextLine();
        }
    }

    // 메뉴
    private void printMenu() {
        System.out.println("[ MAIN MENU ]");
        for (int i=0;i<menus.size();i++) {
            System.out.printf("%d. %s%n", i + 1, menus.get(i).getCategoryName());
        }
        System.out.println("0. 종료      | 종료");
    }
    // 파싱
    private Integer toInt (String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }
    private void clear() {
        for (int i = 0; i<30; i++) {
            System.out.println();
        }
    }
}