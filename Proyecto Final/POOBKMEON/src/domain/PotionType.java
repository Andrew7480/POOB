package domain;

public enum PotionType {
    HYPER_DEFENSE(50),
    SUPER_DEFENSE(30),
    HYPER_ATTACK(50),
    SUPER_ATTACK(30),
    HYPER_PS(50),
    SUPER_PS(30),
    HYPER(50),
    SUPER(30);

    private final int value;

    PotionType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
