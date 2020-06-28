package com.example.firstdemo.test;

import okio.*;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class TestOkIo {

    public static void main(String[] args) {
        TestOkIo a = new TestOkIo();
        a.test();
    }

    public void test() {
        File file = new File("C:\\Users\\yich\\Desktop\\test.txt");
        try {
            readLines(file);
            System.out.println("------------------>whrite");
            writeEnv(file);
            readLines(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void readLines(File file) throws IOException {
        // 1.构建 Source
        try (
                Source fileSource = Okio.source(file);
             // 2.构建 BufferedSource
             BufferedSource bufferedSource = Okio.buffer(fileSource)) {

            while (true) {
                // 3.按 utf8 的格式逐行读取字符
                String line = bufferedSource.readUtf8Line();
                if (line == null) break;
                    System.out.println(line);

            }

        }
    }


    public void writeEnv(File file) throws IOException {
        // 1.构建 Sink
        try (Sink fileSink = Okio.sink(file);
             // 2.构建 BufferedSink
             BufferedSink bufferedSink = Okio.buffer(fileSink)) {
            // 3.写入文本
            for (Map.Entry<String, String> entry : System.getenv().entrySet()) {
                bufferedSink.writeUtf8(entry.getKey());
                bufferedSink.writeUtf8("=");
                bufferedSink.writeUtf8(entry.getValue());
                bufferedSink.writeUtf8("\n");
            }

        }
    }

}
