package V3;

import java.util.List;
import java.util.Scanner;

public class Kiosk { //출력, 생성자, null
    private final List<MenuItem> menuItems;
    public Kiosk(List<MenuItem> menuItems) {
        if (menuItems==null) throw new IllegalArgumentException("menuItems must not be null");
        this.menuItems = menuItems;
    }


    // 입력, 반복, 분기
    public void start(Scanner s) {
        while(true) {
            clear();
            System.out.println("[ SHAKESHACK MENU ]\n");
            printMenu();

            System.out.println();
            System.out.println("0. 종료      | 종료");
            System.out.print("> ");

            String input = s.nextLine().trim();
            if (input.isBlank()) continue;
            if (input.equals("0")) {
                System.out.println("프로그램 종료.");
                break;
            }

            Integer idx = toInt(input);
            if (idx==null||idx>menuItems.size()||idx<1) { // NPE 조심.
                System.out.println("잘못된 입력입니다. Enter를 눌러 계속...");
                s.nextLine();
                continue;
            }
            MenuItem chosen =menuItems.get(idx-1);
            showItem(chosen);
            System.out.println("\n엔터 입력 시 메뉴로 돌아갑니다.");
            s.nextLine();

        }
    }
    private void printMenu() {
        for(int i=0;i<menuItems.size(); i++) {
            MenuItem m = menuItems.get(i);
            System.out.printf("%d. %-12s | W %3.1f | %s%n", i + 1, m.getName(), m.getPrice(), m.getDesc());
        }
    }
    private void showItem(MenuItem m) {
        System.out.printf("%n선택: %s (W %3.1f)%n", m.getName(), m.getPrice());
        System.out.println(m.getDesc());
    }
    private Integer toInt(String s) {
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