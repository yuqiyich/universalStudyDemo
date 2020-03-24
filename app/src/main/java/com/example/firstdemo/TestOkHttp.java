package com.example.firstdemo;

import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class TestOkHttp {

    public static void main(String[] args) throws Exception {
        TestOkHttp a = new TestOkHttp();
        a.test();
        a.test();
    }

    public void test() {
        long start=System.currentTimeMillis();
        MediaType mediaType = MediaType.parse("text/x-markdown; charset=utf-8");
        String requestBody = "I am Jdqm.";
        OkHttpClient client=new OkHttpClient();
        final Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(RequestBody.create(mediaType, requestBody))
                .build();
        try {
           Response response= client.newCall(request).execute();
            System.out.println("respones:"+response.body().toString()+"allTime:"+(System.currentTimeMillis()-start));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
