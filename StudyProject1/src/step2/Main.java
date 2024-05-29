package step2;

public class Main {
    static Computer computer = new Computer();
    static Computer computer2 = new Computer(1, 1, 4);
    public static void main(String[] args) {
        computer.order(4, "copy");
        computer2.order(2, "copy");
    }
}
