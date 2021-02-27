package study.stream;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class OptionalClass {
    public static void main(String[] args){
        makeOptionalObject();
        makeOptionalPrimitive();
    }

    public static void makeOptionalObject(){
        /*
            of() 메소드나 ofNullable() 메소드를 사용해 Optional 객체를 생성할 수 있다.
            of() 메소드는 null이 아닌 명시된 값을 가지는 Optional 객체를 반환한다
            만약 of() 메소드를 통해 생성된 Optional 객체에 null이 저장되면 NPE 발생.

            따라서 만약 참조 변수의 값이 만에 하나 null 이 될 가능성이 있다면 ofNullable() 메소드를 사용해 Optional객체를 생성하는 것이 좋다.
            ofNullable() 메소드는 명시된 값이 null이 아니면 명시된 값을 가지는 Optional 객체를 반환하면
            명시된 갑싱 null이면 비어있는 Optional 객체를 반환한다.
         */
        Optional<String> opt1 = Optional.ofNullable("자바 Optional 객체");
        Optional<String> opt2 = Optional.ofNullable(null);
        // get() 메소드를 사용하면 Optional 객체에 저장된 값에 접근 가능
        // 만약 Optional 객체에 저장된 값이 null이면 NPE 발생
        // 따라서 get() 메소드를 호출 전에 isPresent() 를 사용해 Optional 객체에 저장된 값이 null인지 아닌지 확인후 호출하는 것이 좋다.
        System.out.println(opt1.get());
        System.out.println(opt2.isPresent());
        System.out.println(opt2.isEmpty());

        /*
            또한 다음과 같은 메소드를 통해 null 대신에 대체할 값을 지정할 수도 있다.
            1. orElse() : 저장된 값이 존재하면 그 값을 반환, 존재하지 않으면 인수로 전달된 값을 반환
            2. orElseGet() : 저장된 값이 존재하면 그 값을 반환, 존재하지 않으면 인수로 전달된 람다 표현식의 결괏값을 반환
            3. orElseThrow() : 저장된 값이 존재하면 그 값을 반환, 존재하지 않으면 인수로 전달된 예외를 발생시킴킴
        */
        Optional<String> opt3 = Optional.empty();
        System.out.println(opt3.orElse("Empty Optional Object"));
        System.out.println(opt3.orElseGet(String::new));
        System.out.println(opt3.orElseThrow(() -> new NoSuchElementException()));
    }

    public static void makeOptionalPrimitive(){
        /*
            자바에서는 IntStream 클래스와 같이 기본 타입 스트림을 위한 별도의 Optional 클래스를 제공한다
            OptionalInt
            OptionalLong
            OptionalDouble
            이러한 클래스는 반환 타입이 Optional<T> 타입이 아니라 기본 타입이라는 점만 제외하면 거의 모든 면에서 비슷하다
         */
        IntStream stream = IntStream.of(4, 2, 1, 3);
        OptionalInt oInt = stream.findFirst();
        System.out.println(oInt.getAsInt());
    }
}
