package step2;

public class Computer {
    Memory memory;
    Cpu cpu;

    public Computer(){
        memory = Memory.getInstance();
        cpu = new Cpu();
    }

    public Computer(int cacheSize, int memorySize, int discSize){
        memory = Memory.getInstanceWithValue(cacheSize, memorySize, discSize);
        cpu = new Cpu(cacheSize, memorySize, discSize);
    }

    public void order(int file, String order){
        if (order.equals("copy")) {
            cpu.requestInstall(file);
        }
    }


}