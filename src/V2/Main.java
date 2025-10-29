package V2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    // name, price, descriptions
    public static void main(String[] args) {
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("ShackBurger", 6.9,"토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("Cheeseburger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("Hamburger",   5.4, "비프패티를 기반으로 야채가 들어간 기본버거"));

        Scanner s = new Scanner(System.in);

        while(true) {
            clear();
            System.out.println("[ SHAKESHACK MENU ]\n");

            for(int i = 0; i<menuItems.size(); i++) { // Read 호출.
                MenuItem m = menuItems.get(i);
                System.out.printf("%d. %-12s | W %3.1f | %s%n", i + 1, m.getName(), m.getPrice(), m.getDesc());
            }
            System.out.println();
            System.out.println("0. 종료");
            System.out.print("> ");

            String input = s.nextLine().trim();
            if (input.isEmpty()) continue;

            if (input.equals("0")) {
                System.out.println("프로그램 종료.");
                break;
            }

            Integer idx = toInt(input); // int parsing.
            if (idx == null || idx < 1 || idx > menuItems.size()) {
                System.out.println("잘못된 입력입니다. Enter를 눌러 계속...");
                s.nextLine();
                continue;
            }

            MenuItem chosen = menuItems.get(idx-1);
            System.out.printf("%n선택: %s (W %3.1f)%n", chosen.getName(), chosen.getPrice());
            System.out.println(chosen.getDesc());

            System.out.println("\n엔터를 누르면 메뉴로 돌아갑니다.");
            s.nextLine();
        }
        s.close();
    }
    static void clear() {
        for (int i = 0; i<30; i++) {
            System.out.println();
        }
    }
    static Integer toInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e){
            return null;
        }
    }
}
