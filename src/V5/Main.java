package V5;

// import 파일 전부 적을 것.
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

// MAIN MENU에서 카테고리 번호를 입력 -> runCategoryLoop(카테고리 메뉴) 카테고리 안에서 아이템 선택 -> 상세 출력 후 끝
public class Main {
    public static void main(String[] args) {
        Menu burgers = new Menu("Burgers");
        burgers.addItem(new MenuItem("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        burgers.addItem(new MenuItem("SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        burgers.addItem(new MenuItem("Cheeseburger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        burgers.addItem(new MenuItem("Hamburger",   5.4, "비프패티를 기반으로 야채가 들어간 기본버거"));
        Menu drinks = new Menu("Drinks");
        drinks.addItem(new MenuItem("Orange Juice", 2.0, "시큼해요."));
        drinks.addItem(new MenuItem("Pineapple Juice", 2.0, "시큼해요. Ver 0.2"));
        drinks.addItem(new MenuItem("Grape Juice", 2.0, "시큼해요. Ver 0.3"));
        Menu desserts = new Menu("Desserts"); // shaved ice?? bingsu
        desserts.addItem(new MenuItem("Patbingsu", 4.0, "달콤해요."));
        desserts.addItem(new MenuItem("Frites", 2.5, "짜요."));
        desserts.addItem(new MenuItem("Fish and Chips", 4.2, "맛있겠다."));

        List<Menu> mainMenus = new ArrayList<>();
        mainMenus.add(burgers);
        mainMenus.add(drinks);
        mainMenus.add(desserts);

        try(Scanner s = new Scanner(System.in)) { // try with resources.
            new Kiosk(mainMenus).start(s);
        }
    }
}