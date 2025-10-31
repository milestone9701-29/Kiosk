package V6;

import java.util.Scanner;

public class SoldOutScreen implements Screen {
    private final Kiosk kiosk;
    private final MenuItem chosenItem;
    private final Screen returnTo;

    public SoldOutScreen(Kiosk kiosk, MenuItem chosenItem, Screen returnTo) {
        this.kiosk = kiosk;
        this.chosenItem = chosenItem;
        this.returnTo = returnTo;
    }
    @Override
    public void render() {
        System.out.printf("[ 품절 안내 ]%n%s 메뉴는 현재 판매할 수 없습니다.%n", chosenItem.getName());
        System.out.println("Enter를 누르면 이전 화면으로 돌아갑니다...");
        System.out.print("> ");
    }
    @Override
    public Screen handleInput(Scanner s) {
        s.nextLine();
        return returnTo;
    }
}