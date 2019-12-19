package com.slyang.test.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Description:
 * Created by slyang <slyang520@yeah.net>
 * Copyright (c) 2019, All Rights Reserved.
 */
public class FluxTest {

    private static List<String> words = Arrays.asList(
            "the",
            "quick",
            "brown",
            "fox",
            "jumped",
            "over",
            "the",
            "lazy",
            "dog"
    );

    @Test
    public void simpleCreation() {
        Flux.just("Hello", "World").subscribe(System.out::println);
        System.out.println();

        Flux<String> manyWords = Flux.fromIterable(words);
        manyWords.subscribe(System.out::println);
    }

    @Test
    public void findingMissingLetter() {
        Flux<String> manyLetters = Flux
                .just("hello")
                .flatMap(word -> Flux.fromArray(word.split("")))
                .distinct()
                .sort()
                .zipWith(Flux.range(1, Integer.MAX_VALUE),
                        (string, count) -> String.format("%2d. %s", count, string));

        manyLetters.subscribe(System.out::println);

        // zipWith todo
    }

    @Test
    public void restoringMissingLetter() {
        Mono<String> missing = Mono.just("s");
        Flux<String> allLetters = Flux
                .fromIterable(words)
                .flatMap(word -> Flux.fromArray(word.split("")))
                .concatWith(missing)
                .distinct()
                .sort()
                .zipWith(Flux.range(1, Integer.MAX_VALUE),
                        (string, count) -> String.format("%2d. %s", count, string));

        allLetters.subscribe(System.out::println);
    }

    @Test
    public void shortCircuit() {
        Flux<String> helloPauseWorld =
                Mono.just("Hello")
                        .concatWith(Mono.just("world")
                                .delaySubscription(Duration.ofMillis(500)));

        System.out.println("befroe: "+new SimpleDateFormat("YYYY-MM-dd ::hh:mm:ss::SSS").format(new Date()));
        helloPauseWorld.subscribe(System.out::println);
        System.out.println("end: "+new SimpleDateFormat("YYYY-MM-dd ::hh:mm:ss::SSS").format(new Date()));

    }

    


}
