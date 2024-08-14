package dev.westy;

public class Injector {
    private int processId;
    private byte[] dllBytez;

    public static native int getVersion();
    public static native int injectDll(int pid, byte[] bytes, int bytesLenght);

    static {
        System.loadLibrary("InternalDll");
    }

    public Injector(int i, byte[] dllbyes) {
        processId = i;
        dllBytez = dllbyes;
    }

    public void init() {
        System.out.println("Injector Version: " + getVersion());
        System.out.println("Process with Process ID " + processId + " sends a payload of length " + dllBytez.length + ".");

        int result = injectDll(processId, dllBytez, dllBytez.length);

        System.out.println("DLL result: " + result);
    }
}
