package V6;

public class MenuItem {
    private final String id;
    private final String name;
    private final double price;
    private final String desc;
    private boolean available;

    public MenuItem(String id, String name, double price, String desc) {
        // 입력 필터
        if (id == null||id.isBlank()) throw new IllegalArgumentException("올바른 ID가 아닙니다.");
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("올바른 명칭이 아닙니다.");
        }
        if (price < 0) throw new IllegalArgumentException("올바른 가격이 아닙니다.");
        this.id = id;
        this.name = name;
        this.price = price;
        this.desc = desc == null ? "" : desc;
        this.available = true; // 기본값 1
    }


    // R
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public String getDesc() {
        return desc;
    }
    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean value) {
        this.available = value;
    }
}