package V6;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;


public class Menu {
    private final List<MenuItem> items = new ArrayList<>();
    private final String categoryName;

    // 생성자 Menu : categoryName Null 또는 공백
    public Menu(String categoryName) {
        if (categoryName == null || categoryName.isBlank()) {
            throw new IllegalArgumentException("categoryName must not be null");
        }
        this.categoryName = categoryName;
    }

    // R
    public String getCategoryName() {
        return categoryName;
    }

    public List<MenuItem> getItems() {
        return Collections.unmodifiableList(items); // 불변 : Update가 바로 반영
    }

    public void addItem(MenuItem m) {
        if (m == null) throw new IllegalArgumentException();
        items.add(m);
    }
}