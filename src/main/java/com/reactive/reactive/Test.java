package com.reactive.reactive;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class Test {
    /*public static void main(String[] args) {
        //we can create mono from scratch using MONO.just()
        var just = Mono.defer(() -> Mono.just(UUID.randomUUID().toString()));


        //onDOsuccess() we can do whatever we want to print
        //subscribe() method will be used to initialize the execution of the methods
        Mono<String> stringMono = just.doOnSuccess(uuid -> System.out.println("UUID is :- " + uuid)).log();
        stringMono.subscribe();


        Mono<String> stringMono1 = just.doOnSuccess(uuid -> System.out.println("another UUID is :- " + uuid)).log("printing this at the time of subscription");
        stringMono1.subscribe();


        Flux<String> stringFlux = Flux.just("billy", "ellis", "omayo", "tomb", "raider");

        stringFlux.log()
                .map(s -> s.toUpperCase())
                .doOnNext(System.out::println)
                .subscribe();


//        create empty mono
        Mono<String> emptyMono = Mono.empty();


        Flux<Integer> integerFlux = Flux.range(5, 5)
                .map(integer -> {
                    if (integer <= 8) return integer;
                    throw new RuntimeException("Got to 9");
                });

        integerFlux.subscribe(integer -> System.out.println(integer), error -> System.out.println("Error:- " + error));


        integerFlux
                .doOnNext(print -> System.out.println("printing counts:- " + print))
                .subscribe(print -> System.out.println("printing counts:- " + print));


        Test test = new Test();
        test.vegFluxConcatOnDoOnError()
                .subscribe(s -> System.out.println("Fruits are:- " + s));


    }*/

    public Flux<String> fruitsFlux() {
        return Flux.fromIterable(List.of("Mango", "Orange", "Banana", "Grapes", "Honey")).log();
    }

    public Flux<String> fruitsFluxMap() {
        return Flux.fromIterable(List.of("Mango", "Orange", "Banana", "Grapes", "Honey"))
//                .log()
//                .map(String::toUpperCase)
                .filter(letter -> letter.contains("a") && letter.length() > 5)
                .map(String::toUpperCase);
    }

    public Flux<String> fruitsFluxFlatMap() {
        return Flux.fromIterable(List.of("Mango", "Orange", "Banana", "Grapes", "Honey"))
//                .log()
                .filter(letter -> letter.contains("a") && letter.length() > 5)
                .map(String::toUpperCase)
                .flatMap(letter -> Flux.just(letter.split("")));
    }


    //concatMap operator - This will preserve order of the elements
    public Flux<String> fruitsFluxConcatMap() {
        return Flux.fromIterable(List.of("Mango", "Orange", "Banana", "Grapes", "Honey"))
                .log()
//                .filter(letter -> letter.contains("a") && letter.length() > 5)
//                .map(String::toUpperCase)
                .concatMap(letter -> Flux.just(letter.split("")));
    }

    //flatMapMany operator - when you want to perform operation on the Mono but want Flux in return
    public Flux<String> fruitMonoFlatMapMany() {
        return Mono.just("Pineapple")
                .flatMapMany(s -> Flux.just(s.split("")))
                .log();
    }

    //transform operator

    public Flux<String> fluxTransform(int number) {

        Function<Flux<String>, Flux<String>> fluxFunction = data -> data.filter(letter -> letter.contains("a") && letter.length() > number);


        return Flux.fromIterable(List.of("Mango", "Orange", "Banana", "Grapes", "Honey"))
                .transform(fluxFunction);
    }

    //defaultIfEmpty operator
    public Flux<String> fluxTransformDefaultIfEmpty(int number) {

        Function<Flux<String>, Flux<String>> fluxFunction = data -> data.filter(letter -> letter.contains("a") && letter.length() > number);


        return Flux.fromIterable(List.of("Mango", "Orange", "Banana", "Grapes", "Honey"))
                .transform(fluxFunction)
                .defaultIfEmpty(String.valueOf(0))
                .log();

    }

    //switchIfEmpty operator
    public Flux<String> fluxTransformSwitchIfEmpty(int number) {

        Function<Flux<String>, Flux<String>> fluxFunction = data -> data.filter(letter -> letter.contains("a") && letter.length() > number);


        return Flux.fromIterable(List.of("Mango", "Orange", "Banana", "Grapes", "Honey"))
                .transform(fluxFunction)
                .switchIfEmpty(Mono.error(new Exception("There is no fruits available with given filter.")))
                .log();
    }

    //concat && concatWith operator :- helps to combine 2 FLUXs or MONOs

    // TODO: 28/11/2023 concat() is a static method  where as concatWith() is an instance method
    // TODO: 28/11/2023 whenever we merge 2 FLUX/MONO it will subscribe the first and after then it will subscribe another.
    // TODO: 28/11/2023 also we can concat MONO with FLUX and vice versa 
    public Flux<String> vegFluxConcat() {

        var anything = Flux.just("Egg", "Fish", "Samsung", "PS5");
        var veg = Flux.just("Tomato", "Lemon", "Onion");

        return Flux.concat(anything, veg);


    }

    public Flux<String> vegFluxConcatWith() {

        var anything = Mono.just("Egg");
        var veg = Flux.just("Tomato", "Lemon", "Onion");

        return anything.concatWith(veg);
    }

    //merge and mergeWith operator
    // TODO: 28/11/2023 this will get the daya asynchronously by subscribing  the publisher simultaneously
    // TODO: 28/11/2023 merge() is a static method  where as mergeWith() is an instance method

    public Flux<String> vegFluxMerge() {

        var anything = Flux.just("Egg", "Fish", "Samsung", "PS5");
        var veg = Flux.just("Tomato", "Lemon", "Onion");

        return Flux.merge(anything, veg);

    }

    public Flux<String> vegFluxMergeWith() {

        var anything = Mono.just("Egg")
                .delayElement(Duration.ofMillis(10));
        var veg = Flux.just("Tomato", "Lemon", "Onion")
                .delayElements(Duration.ofMillis(15));

        return anything.mergeWith(veg);
    }

    //mergeSequential operator
    // TODO: 28/11/2023 this will subscribe the publisher at the same time but will return the elements in sequence.
    public Flux<String> vegFluxMergeSequential() {

        var anything = Mono.just("Egg");
        var veg = Flux.just("Tomato", "Lemon", "Onion");

        return Flux.mergeSequential(veg);
    }


    //zip and zipWith operator
    // TODO: 28/11/2023 we can go upto 8 publishers over here.

    public Flux<String> fluxZip() {
        var anything = Flux.just("Egg", "Samsung");
        var veg = Flux.just("Tomato", "Lemon", "Onion");


        return Flux.zip(anything, veg, (first, second) -> first + second);

    }

    public Flux<String> fluxZipWith() {
        var anything = Flux.just("Egg", "Samsung");
        var veg = Flux.just("Tomato", "Lemon", "Onion");


        return anything.zipWith(anything, (first, second) -> first + second);
    }

    //onErrorReturn operator
    public Flux<String> vegFluxConcatOnErrorReturn() {

        var anything = Flux.just("Egg", "Fish", "Samsung", "PS5");
        var veg = Flux.just("Tomato", "Lemon", "Onion");

        return Flux.concat(anything, veg)
                .concatWith(Flux.error(new RuntimeException("Exception occurred")))
                .onErrorReturn("Error");
    }

    //onErrorContinueOperator :- This will continue the operation and inbetween it'll drop the element that is causing the issue


    public Flux<String> vegFluxConcatOnErrorContinue() {

        return Flux.just("T1", "T2", "T3")
                .map(s -> {
                    if (s.equalsIgnoreCase("T2"))
                        throw new RuntimeException("Exception Occurred");
                    return s.toUpperCase();
                })
                .onErrorContinue((e, f) -> {
                    System.out.println("e = " + e);
                    System.out.println("f = " + f);
                });
    }

    //onErrorMap operator
    public Flux<String> vegFluxConcatOnErrorMap() {

        return Flux.just("T1", "T2", "T3")
                .map(s -> {
                    if (s.equalsIgnoreCase("T2"))
                        throw new RuntimeException("Exception Occurred");
                    return s.toUpperCase();
                })
                .onErrorMap(throwable -> {
                    System.out.println("throwable = " + throwable);
                    return new IllegalStateException("From onErrorMap");
                });
    }

    //doOnError operator

    public Flux<String> vegFluxConcatOnDoOnError() {

        return Flux.just("T1", "T2", "T3")
                .map(s -> {
                    if (s.equalsIgnoreCase("T2"))
                        throw new RuntimeException("Exception Occurred");
                    return s.toUpperCase();
                })
                .doOnError(throwable -> {
                    System.out.println("throwable = " + throwable);
                });
    }


    // TODO: 01/12/2023 returns only boolean
//    Predicate<Author> authorPredicate = author -> author.getName().startsWith("Ronak");

    public static void main(String[] args) {
        Function<Integer, Integer> integerFunction = integer -> integer * 2;
        Function<Integer, Integer> integerIntegerFunction = i -> i * i * i;


        System.out.println(integerFunction.andThen(integerIntegerFunction).apply(2));
        System.out.println(integerFunction.compose(integerIntegerFunction).apply(2));
    }
}
