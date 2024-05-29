package step2;

import java.util.Arrays;

class Memory {
    private static Memory memoryInstance;
    protected static int[] disc;
    protected int[] memory;
    protected int[] cache;

    protected int cachePoint;
    protected int memoryPoint;
    protected int discPoint;

    public Memory() {
        cache = initializeMemory(4);
        memory = initializeMemory(8);
        disc = initializeMemory(16);

        cachePoint = 4;
        memoryPoint = 8;
        discPoint = 16;
    }

    // 테스트용 생성자
    public Memory(int cacheSize, int memorySize, int discSize){
        cache = initializeMemory(cacheSize);
        memory = initializeMemory(memorySize);
        disc = initializeMemory(discSize);

        cachePoint = cacheSize;
        memoryPoint = memorySize;
        discPoint = discSize;
    }

    // 싱글톤 패턴 적용
    public static Memory getInstance() {
        if (memoryInstance == null) {
            memoryInstance = new Memory();
        }
        return memoryInstance;
    }

    public static Memory getInstanceWithValue(int cacheSize, int memorySize, int discSize) {
        if (memoryInstance == null) {
            memoryInstance = new Memory (cacheSize, memorySize, discSize);
        }
        return memoryInstance;
    }

    private int[] initializeMemory(int size){
        int[] tmpMemory = new int[size];
        Arrays.fill(tmpMemory, -1);
        return tmpMemory;
    }

}
