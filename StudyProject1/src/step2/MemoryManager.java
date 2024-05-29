package step2;

public class MemoryManager extends Memory{

    public MemoryManager() {
        super();
    }

    public MemoryManager(int cacheSize, int memorySize, int discSize){
        super(cacheSize, memorySize, discSize);
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

    void deleteFile(){
        cache[--cachePoint] = -1;
    }

    private void copyFile(int file){
        cache[cachePoint++] = file;
        System.out.println("파일 복사");
    }

    private int findFile(){
        return  cache[cachePoint -1];
    }

    private void installFile(int fileSize){
        for(int i = 0; i < fileSize-1; i++){
            memory[memoryPoint++] = 1;
        }
        // 파일의 끝은 0으로 저장
        memory[memoryPoint++] = 0;
    }



    private boolean checkCapacityCache() throws Exception {
        for(int i = 0; i < cache.length; i++){
            if(cache[i] == -1) {
                this.cachePoint = i;
                break;
            }
        }

        if(this.cachePoint == cache.length){
            throw new Exception("캐쉬 용량 부족");
        }

        return true;
    }

    private void cleanUpCache() throws Exception {
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

    private boolean checkCapacityMemory(int count) throws Exception {
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

    private void moveMemory(){
        for (int j : cache) {
            memory[memoryPoint++] = j;
        }
    }

    private void cleanUpMemory() throws Exception {
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

    private boolean checkCapacityDisc(int count) throws Exception{
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

    private void moveDisc(){
        for (int j : memory) {
            disc[discPoint++] = j;
        }
    }

}
