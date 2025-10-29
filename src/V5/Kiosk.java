package V5;

// import 할 때 정확한 명칭 표기.
import java.util.List;
import java.util.ArrayList; // (?s)import java.*
import java.util.Scanner;
// import java.util.stream.Collectors;

public class Kiosk {
    // 리스트, ArrayList
    private final List<Menu> menus;
    private final List<CartItem> cart = new ArrayList<>();
    // 생성자 Null
    public Kiosk(List<Menu> menus) {
        if (menus == null) throw new IllegalArgumentException(" menus must not be null");
        this.menus = menus;
    }
    // 시작
    public void start(Scanner s) {
        while(true) {
            clear();
            printMainMenu(); // 장바구니 옵션 표시
            System.out.print("> ");

            String input = s.nextLine().trim();
            if (input.isBlank()) continue;
            if (input.equals("0")) {
                System.out.println("프로그램 종료.");
                break;
            }
            // 4 : 주문 / 확인, 5 : 취소
            if (input.equals("4")) {
                if (cart.isEmpty()) { // 이런거 묶어도 되지 않나?
                    System.out.println("장바구니가 비어있습니다. Enter를 눌러 계속...");
                    s.nextLine();
                    continue;
                }
                CartReviewScreen(s);
                continue;
            }
            if (input.equals("5")) {
                if (cart.isEmpty()) { // 이런거 묶어도 되지 않나? 검증단위랑 메시지 단위로 쪼개버린다거나? 모르겠네
                    System.out.println("장바구니가 비어있습니다. Enter를 눌러 계속...");
                    s.nextLine();
                    continue;
                }
                cancelOrder(s);
                continue;
            }
            // 일반 메뉴 : 카테고리 선택.
            Integer categoryIndex = toInt(input);
            if (categoryIndex == null || categoryIndex < 1 || categoryIndex > menus.size()) {
                System.out.println("잘못된 입력입니다. Enter를 눌러 계속...");
                s.nextLine();
                continue;
            }

            Menu selected = menus.get(categoryIndex - 1); // 0 based
            runCategoryLoop(s, selected);
        }
    }
    // 루프
    private void runCategoryLoop(Scanner s, Menu menu) {
        while(true) {
            clear();
            System.out.printf("[ %s MENU ]%n%n", menu.getCategoryName().toUpperCase()); // 대문자.
            List<MenuItem> items = menu.getItems();
            for (int i = 0; i<items.size(); i++) {
                MenuItem m = items.get(i);
                System.out.printf("%d. %-14s | W %3.1f | %s%n", i + 1, m.getName(), m.getPrice(), m.getDesc());
            }
            System.out.println("0. 뒤로가기");
            System.out.print("> ");

            String input = s.nextLine().trim();
            if (input.isBlank()) continue; // loop
            if (input.equals("0")) return;

            Integer idx = toInt(input);
            if (idx==null||idx<1||idx>items.size()) {
                System.out.println("잘못된 입력입니다. Enter를 누르면 계속...");
                s.nextLine();
                continue;
            }
            MenuItem chosen = items.get(idx - 1);
            askAddToCart(s, chosen); // 장바구니에 넣을 것인지 묻는 메서드
        }
    }
    // 장바구니 추가 확인.
    private void askAddToCart(Scanner s, MenuItem chosen) {
        clear();
        System.out.printf("\"%s | W %3.1f | %s\"%n", chosen.getName(), chosen.getPrice(), chosen.getDesc());
        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인        2. 취소");
        System.out.print("> ");

        String input = s.nextLine().trim();
        if(!input.equals("1")) { // 1이 아니라면 -> 2 외의 나머지.
            System.out.println("취소되었습니다. Enter를 누르면 돌아갑니다...");
            s.nextLine();
            return;
        }
        addToCart(chosen); // 장바구니 등록
        System.out.printf("%s 이(가) 장바구니에 추가되었습니다.%n", chosen.getName());
        System.out.println("Enter를 누르면 메뉴로 돌아갑니다...");
        s.nextLine();
    }
    // 카트 추가
    private void addToCart(MenuItem chosen) {
        // 같은 이름의 경우, 수량만 ++
        for (CartItem ci : cart) {
            if (ci.getItem().getName().equals(chosen.getName())) {
                ci.incrementQuantity(); // quantity++;
                return;
            }
        }
        // 같은 이름이 없는 경우.
        cart.add(new CartItem(chosen));
    }

    // 메인 메뉴
    // 장바구니 X -> Order Menu 또한 출력.
    private void printMainMenu() {
        System.out.println("[ MAIN MENU ]");
        for (int i=0; i<menus.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, menus.get(i).getCategoryName());
        }
        System.out.println("0. 종료      | 종료");
        System.out.println();
        if (!cart.isEmpty()){ // cart가 비어 있지 않은 경우.
            System.out.println("[ ORDER MENU ]");
            System.out.println("4. Orders       | 장바구니를 확인 후 주문합니다.");
            System.out.println("5. Cancel       | 진행중인 주문을 취소합니다.");
        }
    }
    // 주문확인
    private void CartReviewScreen(Scanner s) {
        while(true) {
            clear();
            System.out.println("아래와 같이 주문 하시겠습니까?\n");
            System.out.println("[ Orders ]");

            // cart.Stream() : cart(List<CartItem>)에서 Stream 시작.
            // .mapToDouble(CartItem::getLineTotal) : 각 항목(CartItem)을 총액(double)로 바꿈.
            // .sum : 더함.
            double total = cart.stream().mapToDouble(CartItem::getLineTotal).sum();

            /*
            double total = 0.0;
            for(CartItem ci : cart) {
                MenuItem item = ci.getItem();
                System.out.printf("%s | W %3.1f | %s | 수량: %d%n", item.getName(), item.getPrice(), item.getDesc(), ci.getQuantity());
                total+=ci.getLineTotal(); total = total+ci.getLineTotal();
            } */

            // stream forEach
            /* cart.stream().forEach(ci->{
                MenuItem item = ci.getItem();
                System.out.printf("%s | W %3.1f | %s | 수량: %d%n", item.getName(), item.getPrice(), item.getDesc(),ci.getQuantity());
            */

            System.out.println();
            System.out.println("[ Total ]");
            System.out.printf("W %3.1f%n", total);

            System.out.println();
            System.out.println("1. 주문      2. 메뉴판");
            System.out.print("> ");

            String input = s.nextLine().trim();
            if(input.equals("2")) { // 메인 복귀
                return;
            }
            if(input.equals("1")) {
                total = askDiscountAndApply(s, total); // 할인 여부 묻기
                checkout(total);
                System.out.println("Enter를 누르면 메뉴판으로 돌아갑니다...");
                s.nextLine();
                return;
            }
            System.out.println("잘못된 입력입니다. Enter를 눌러 계속...");
            s.nextLine();
        }
    }

    // 주문 완료
    private void checkout(double total) {
        System.out.printf("주문이 완료되었습니다. 금액은 W %3.1f 입니다.%n", total);
        cart.clear();
    }

    // 주문 취소
    private void cancelOrder(Scanner s) {
        clear();
        cart.clear();
        System.out.println("진행중인 주문을 취소했습니다.");
        System.out.println("Enter를 누르면 메뉴판으로 돌아갑니다...");
        s.nextLine();
    }

    // 할인 적용 여부
    private double askDiscountAndApply(Scanner s, double total) {
        while(true) {
            clear();
            System.out.println("할인 정보를 입력해주세요.");
            System.out.println("1. 국가유공자 : 10%");
            System.out.println("2. 군인     :  5%");
            System.out.println("3. 학생     :  3%");
            System.out.println("4. 일반     :  0%");
            System.out.print("> ");

            String input = s.nextLine().trim();
            CustomerType type = CustomerType.fromOption(input); // 매핑
            if (type == null ) {
                System.out.println("잘못된 입력입니다. Enter를 눌러 다시 선택...");
                s.nextLine();
                continue;
            }
            double discounted = type.applyDiscount(total);
            System.out.printf("적용된 금액: W %3.1f (%s 할인 적용)%n", discounted, type.getLabel());
            System.out.println("Enter를 누르면 주문을 완료합니다...");
            s.nextLine();
            return discounted;
        }
    }

    /* private void removeFromCartByName(String name) {
        // cart List 필터
        List<CartItem> filtered = cart.stream().filter(ci->!ci.getItem().getName().equals(name)).collect(Collectors.toList()); // !아이템, 이름, equals(name) -> 리스트.
        cart.clear();
        cart.addAll(filtered);
        // add() : 맨 뒤에서 새 노드 연결 후, 뒤쪽으로 데이터가 쌓이고, 순서를 가짐.
        // addAll() : 데이터 통째로 붙이는 메서드 : 인자로 Collection 객체를 받고 그 Collection에 있는 item들을 List에 모두 추가한다.
        //.toList() : Java + 16
    } */

    // int 파싱
    private Integer toInt (String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    // 개행
    private void clear() {
        for (int i=0; i<30; i++) {
            System.out.println();
        }
    }
}