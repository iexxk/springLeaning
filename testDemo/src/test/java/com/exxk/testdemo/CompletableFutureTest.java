package com.exxk.testdemo;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest {

    private static final Logger log = LoggerFactory.getLogger(CompletableFutureTest.class);

    public static void main(String[] args) throws IOException, InterruptedException {

    }

    @Test
    public void thenCombine() throws Exception {
        CompletableFuture<String> result = CompletableFuture.supplyAsync(() -> {
           delaySec(10);
           log.info("第一个CF");
            return "hello1";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            log.info("第二个CF");
            delaySec(13);
            return "hello2";
        }), (t, u) -> {
            log.info("1+2="+t+u);
            return t + " " + u;
        });
        log.info("bbbb");
        log.info(result.get());
        log.info("aaaa");

    }

    public void delaySec(int s){
        try {
            Thread.sleep(1000*s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
