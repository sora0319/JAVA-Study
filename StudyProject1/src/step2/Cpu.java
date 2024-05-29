package step2;

public class Cpu {
    private  MemoryManager memoryManager;
    public Cpu (){
        memoryManager = new MemoryManager();
    }

    public Cpu (int cacheSize, int memorySize, int discSize){
        memoryManager = new MemoryManager(cacheSize, memorySize, discSize);
    }

    void requestInstall(int file) {
        excuteCopy(file);
        excuteInstall();
        excuteDelete();
    }

    // 파일 복사 실행 메소드
    void excuteCopy(int file){
        try {
            memoryManager.orderCopy(file);
            System.out.println("파일 복사 완료");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    void excuteInstall(){
        // 파일 설치 & 임시파일 삭제
        try {
            memoryManager. orderInstall();
            System.out.println("파일 설치 완료");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    void excuteDelete() {
        memoryManager.deleteFile();
        System.out.println("임시 파일 삭제 완료");
    }
}
