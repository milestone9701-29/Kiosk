package V6;

// import 파일 전부 적을 것.
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

// MAIN MENU에서 카테고리 번호를 입력 -> runCategoryLoop(카테고리 메뉴) 카테고리 안에서 아이템 선택 -> 상세 출력 후 끝
public class Main {
    public static void main(String[] args) {
        Menu burgers = new Menu("Burgers");
        MenuItem b1 = new MenuItem("Burger-001", "ShackBurger",    6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거");
        MenuItem b2 = new MenuItem("Burger-002", "SmokeShack",     8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거");
        MenuItem b3 = new MenuItem("Burger-003", "Cheeseburger",   6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거");
        MenuItem b4 = new MenuItem("Burger-004", "Hamburger",      5.4, "비프패티를 기반으로 야채가 들어간 기본버거");
        burgers.addItem(b1);
        burgers.addItem(b2);
        burgers.addItem(b3);
        burgers.addItem(b4);

        Menu drinks = new Menu("Drinks");
        MenuItem d1 = new MenuItem("Drink-001", "Orange Juice",     2.0, "시큼해요.");
        MenuItem d2 = new MenuItem("Drink-002", "Pineapple Juice",  2.0, "시큼해요. Ver 0.2");
        MenuItem d3 = new MenuItem("Drink-003", "Grape Juice",      2.0, "시큼해요. Ver 0.3");
        drinks.addItem(d1);
        drinks.addItem(d2);
        drinks.addItem(d3);

        Menu desserts = new Menu("Desserts");
        MenuItem ds1 = new MenuItem("Dessert-001", "Patbingsu",       4.0, "달콤해요.");
        MenuItem ds2 = new MenuItem("Dessert-002", "Frites",          2.5, "짜요.");
        MenuItem ds3 = new MenuItem("Dessert-003", "Fish and Chips",  4.2, "맛있겠다.");
        desserts.addItem(ds1);
        desserts.addItem(ds2);
        desserts.addItem(ds3);

        // 데모용 품절
        d1.setAvailable(false);

        List<Menu> mainMenus = new ArrayList<>();
        mainMenus.add(burgers);
        mainMenus.add(drinks);
        mainMenus.add(desserts);

        try(Scanner s = new Scanner(System.in)) { // try with resources.
            new Kiosk(mainMenus).start(s);
        }
    }
}