package com.reactive;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;

@SpringBootTest
class SpringReactiveApplicationTests {

    @Test
    void contextLoads() {
    }

  /*  @Test
    void workingWithMono() {

        //MONO --> publisher that have 0 to 1 items
        System.out.println("Testing");

        Mono<String> stringMono = Mono
				.just("testing mono")
				.log();

        stringMono.subscribe(s -> System.out.println("test with lambda:- " + s));
    }*/

}
