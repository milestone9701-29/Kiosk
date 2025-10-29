package V3;

public class MenuItem {
    // n, p, d
    private final String name;
    private final double price;
    private final String desc;

    public MenuItem(String name, double price, String desc) {
        if (name==null||name.isBlank()) throw new IllegalArgumentException("올바른 명칭이 아닙니다.");
        if (price<0) throw new IllegalArgumentException("올바른 가격이 아닙니다.");
        this.name=name;
        this.price=price;
        this.desc=desc==null?"":desc;
    }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getDesc() { return desc; } // 만약 List 사용이라면, Collections.UnmodifiableList(desc);
}