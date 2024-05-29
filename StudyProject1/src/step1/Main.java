package step1;

public class Main {
    public static void main(String[] args) {
        rawCode raw1 = new rawCode();
        raw1.requestInstall(2);

        System.out.println("\n새로운 컴퓨터");
        rawCode raw2 = new rawCode(1,1,2);
        raw2.requestInstall(4);
    }
}