package dev.westy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static byte[] readFileToByteArray(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return Files.readAllBytes(path);
    }

    public static void main(String[] args) throws IOException {
        byte[] dllbyes = readFileToByteArray("TestDll.dll");

        new Injector(22348, dllbyes).init();
    }
}