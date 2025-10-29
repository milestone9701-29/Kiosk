package V6;
import java.util.Scanner;

public interface Screen { // vs abstract class : enum ~ MOD('%') @Override ~~~
    // 현재 화면 출력
    void render();
    // 사용자 입력 받은 후, 다음 화면 결정.
    // null : 프로그램 종료.
    Screen handleInput(Scanner s);
}