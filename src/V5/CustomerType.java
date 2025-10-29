package V5;
public enum CustomerType {
    NATIONAL_HERO("국가유공자", 0.10), // 왜 0.10이지?
    SOLDIER("군인", 0.05),
    STUDENT("학생,", 0.03),
    REGULAR("일반", 0.00); // 문자열 들어가서 그런가?

    private final String label;
    private final double discountRate;

    CustomerType(String label, double discountRate) { // 생성자
        this.label = label;
        this.discountRate = discountRate;
    }
    public String getLabel() { // R
        return label;
    }
    public double applyDiscount(double total) { // 할인율 적용
        return total * (1.0 - discountRate);
    }

    public static CustomerType fromOption(String input) { // 정수 선택에 따라 매핑.
        switch (input) {
            case "1" : return NATIONAL_HERO;
            case "2" : return SOLDIER;
            case "3" : return STUDENT;
            case "4" : return REGULAR;
            default : return null;
        }
    }
}