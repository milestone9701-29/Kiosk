package V1;
import java.util.Scanner;

public class Main {
    // 배열 상수 선언
    static final String[] NAMES = { "ShackBurger", "SmokeShack", "Cheeseburger", "Hamburger" };
    static final double[] PRICES = { 6.9, 8.9, 6.9, 5.4 };
    static final String[] DESC = {
            "토마토, 양상추, 쉑소스가 토핑된 치즈버거",
            "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거",
            "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거",
            "비프패티를 기반으로 야채가 들어간 기본버거"
    };

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        while (true) {
            clear();
            System.out.println("[ SHAKESHACK MENU ]");
            System.out.println();

            // 레이아웃
            for (int i = 0; i < NAMES.length; i++) {
                System.out.printf("%d. %-12s | W %3.1f | %s%n", i + 1, NAMES[i], PRICES[i], DESC[i]);
            }
            System.out.println();
            System.out.println("0. 종료");
            System.out.print("> ");
            // 문자열 처리
            String input = s.nextLine().trim();
            if (input.equals("0")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }

            Integer idx = toInt(input);
            if (idx == null || idx < 1 || idx > NAMES.length) {
                System.out.println("잘못된 입력입니다. 엔터를 누르면 계속...");
                s.nextLine();
                continue;
            }

            // 메시지 출력
            int i = idx - 1;
            System.out.printf("%n선택: %s (W %.1f)%n", NAMES[i], PRICES[i]);
            System.out.println(DESC[i]);
            System.out.println("\n엔터를 누르면 메뉴로 돌아갑니다...");
            s.nextLine();
        }
        s.close();
    }

    // 파싱, 공백
    static void clear() {
        for (int i = 0; i<30; i++) {
                System.out.println();
        }
    }
    static Integer toInt(String s) { try { return Integer.parseInt(s); } catch (NumberFormatException e) { return null; } }
}