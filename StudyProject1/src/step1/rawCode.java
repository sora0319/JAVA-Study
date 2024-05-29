package step1;

import java.util.Arrays;

public class rawCode {
    private static int[] disc;
    private int[] memory;
    private int[] cache;

    int cachePoint;
    int memoryPoint;
    int discPoint;

    public rawCode() {
        cache = initializeMemory(4);
        memory = initializeMemory(8);
        disc = initializeMemory(16);

        cachePoint = 4;
        memoryPoint = 8;
        discPoint = 16;
    }

    // 테스트용 생성자
    public rawCode(int cacheSize, int memorySize, int discSize){
        cache = initializeMemory(cacheSize);
        memory = initializeMemory(memorySize);
        disc = initializeMemory(discSize);

        cachePoint = cacheSize;
        memoryPoint = memorySize;
        discPoint = discSize;
    }

    int[] initializeMemory(int size){
        int[] tmpMemory = new int[size];
        Arrays.fill(tmpMemory, -1);
        return tmpMemory;
    }

    void requestInstall(int file) {
        // 파일 복사
        try {
            orderCopy(file);
            System.out.println("파일 복사 완료");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        // 파일 설치 & 임시파일 삭제
       try {
           orderInstall();
           System.out.println("파일 설치 완료");
           deleteFile();
           System.out.println("임시 파일 삭제 완료");
       } catch (Exception e) {
            System.out.println(e.getMessage());
       }

    }

    void orderCopy(int file) throws Exception {
        // 파일 복사
        try {
            if(checkCapacityCache()){
                copyFile(file);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            cleanUpCache();
            copyFile(file);
        }
    }

    void orderInstall() throws Exception {
        // 파일 설치
        try {
            int storedFile = findFile();
            if(checkCapacityMemory(storedFile)){
                installFile(storedFile);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            cleanUpMemory();
        }
    }

    void copyFile(int file){
        cache[cachePoint++] = file;
        System.out.println("파일 복사");
    }

    int findFile(){
        return  cache[cachePoint -1];
    }

    void installFile(int fileSize){
        for(int i = 0; i < fileSize-1; i++){
            memory[memoryPoint++] = 1;
        }
        // 파일의 끝은 0으로 저장
        memory[memoryPoint++] = 0;
    }

    void deleteFile(){
        cache[--cachePoint] = -1;
    }

    boolean checkCapacityCache() throws Exception {
        for(int i = 0; i < cache.length; i++){
            if(cache[i] == -1) {
                cachePoint = i;
                break;
            }
        }

        if(cachePoint == cache.length){
            throw new Exception("캐쉬 용량 부족");
        }

        return true;
    }

    void cleanUpCache() throws Exception {
        try {
            if(checkCapacityMemory(cachePoint)){
                moveMemory();
                System.out.println("캐쉬 용량 메모리로 비우기 완료");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            cleanUpMemory();
        }
    }

    boolean checkCapacityMemory(int count) throws Exception {
        for(int i = 0; i < memory.length; i++){
            if(memory[i] == -1) {
                memoryPoint = i;
                break;
            }
        }

        if(memoryPoint == memory.length){
            throw new Exception("메모리 용량 가득참");
        }

        if(memory.length - memoryPoint < count){
            throw new Exception("메모리 남은 용량 부족");
        }

        return true;
    }

    void moveMemory(){
        for (int j : cache) {
            memory[memoryPoint++] = j;
        }
    }

    void cleanUpMemory() throws Exception {
        try {
            if(checkCapacityDisc(memoryPoint)){
                moveDisc();
                System.out.println("메모리 용량 디스크로 비우기 완료");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("종료");
            // 더이상 할 수 없음
        }
    }

    boolean checkCapacityDisc(int count) throws Exception{
        for(int i = 0; i < disc.length; i++){
            if(disc[i] == -1) {
                discPoint = i;
                break;
            }
        }

        if(discPoint == disc.length){
            throw new Exception("디스크 용량 가득참");
        }

        if(disc.length - discPoint < count){
            throw new Exception("디스크 남은 용량 부족");
        }

        return true;
    }
    
    void moveDisc(){
        for (int j : memory) {
            disc[discPoint++] = j;
        }
    }

}
