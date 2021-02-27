package study.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamIntermediateOperation {
    public static void main(String[] args) {
        streamFiltering();
        streamTranform();
        streamLimit();
        streamSort();
        streamPeek();
    }

    public static void streamFiltering() {
        /*
            filter() 메소드는 해당 스트림에서 주어진 조건에 맞는 요소로만 구성된 새로운 스트림 반환
            distinct() 메소드는 해당 스트림에서 중복된 요소를 제거한 새로운 스트림 반환
            disintct() 는 내부적으로 Object 클래스의 equals() 메소드를 사용해서 요소의 중복을 비교
         */

        // 스트림에서 중복된 요소를 제거함
        IntStream s1 = IntStream.of(7, 5, 5, 2, 1, 2, 3, 5, 4, 6);
        s1.distinct().forEach(e -> System.out.print(e + " "));
        System.out.println();

        // 스트림에서 홀수만을 선택함
        IntStream s2 = IntStream.of(7, 5, 5, 2, 1, 2, 3, 5, 4, 6);
        s2.filter(n -> n % 2 != 0).forEach(e -> System.out.print(e + " "));
        System.out.println();
        System.out.println();
    }
    public static void streamTranform(){
        /*
            map() 메소드는 해당 스트림의 요소들을 주어진 함수에 인수로 전달하여 그 반환값들로 이루어진 새로운 스트림을 반환
            만약 해당 스트림의 요소가 배열이라면 flatMap() 메소드를 사용하여 각 배열의 각 요소의 반환값을 하나로 합친 새로운 스트림을 얻음
         */

        // 각 문자열의 길이로 이루어진 스트림으로 변환하는 예제
        Stream<String> s1 = Stream.of("HTML", "CSS", "JAVA", "JAVASCRIPT");
        s1.map(s -> s.length()).forEach(e -> System.out.print(e+" "));
        System.out.println();

        // 여러 문자열이 저장된 배열을 각 문자열에 포함된 단어로 이루어진 스트림으로 변환하는 예제
        String[] arr = new String[]{"I study hard", "You study JAVA", "I'm hungry"};
        Stream<String> s2 = Arrays.stream(arr);
        //s2.flatMap(s -> Stream.of(s.split(" "))).forEach(e -> System.out.print(e+" "));
        s2.flatMap(s -> Stream.of(s.split(" "))).forEach(System.out::println);
        System.out.println();
        System.out.println();
    }
    public static void streamLimit(){
        /*
            limit() 메소드는 해당 스트림의 첫 번째 요소부터 전달된 개수만큼 요소로만으로 이루어진 새로운 스트림을 반환
            skip() 메소드는 해당 스트림의 첫 번째 요소부터 전달된 개수만큼 요소를 제외한 나머지 요소만으로 이루어진 새로운 스트림을 반환
         */
        IntStream s1 = IntStream.range(0, 10);
        IntStream s2 = IntStream.range(0, 10);
        IntStream s3 = IntStream.range(0, 10);

        s1.skip(4).forEach(e -> System.out.print(e +" "));
        System.out.println();

        s2.limit(5).forEach(e -> System.out.print(e+" "));
        System.out.println();

        s3.limit(8).skip(3).forEach(e -> System.out.print(e+" "));
        System.out.println();
        System.out.println();
    }
    public static void streamSort(){
        /*
            sorted() 메소드는 해당 스트림을 주어진 비교자(comparator)을 이요하여 정렬한다
            이때 비교자를 전달하지 않으면 기본적으로 사전 편찬 순(natural order)으로 정렬하게 된다
         */
        Stream<String> s1 = Stream.of("JAVA", "HTML", "JAVASCRIPT", "CSS");
        Stream<String> s2 = Stream.of("JAVA", "HTML", "JAVASCRIPT", "CSS");
        s1.sorted().forEach(e -> System.out.print(e+" "));
        System.out.println();

        s2.sorted(Comparator.reverseOrder()).forEach(e -> System.out.print(e+" "));
        System.out.println();
        System.out.println();
    }
    public static void streamPeek(){
        /*
            peek() 메소드는 결과 스트림으로부터 요소를 소모하여 추가로 명시된 동작을 수행한다
            이 메소드는 원본 스트림에서 요소를 소모하지 않으므로 주로 연산과 연산 사이에 결과를 확인하고 싶을 때 사용한다
            따라서 개발자가 디버깅용으로 자주 사용한다
        */
        IntStream stream = IntStream.of(7, 5, 5, 2, 1, 2, 3, 5, 4, 6);
        stream.peek(s -> System.out.println("원본 스트림 : " + s))
                .skip(2)
                .peek(s -> System.out.println("skip(2) 실행 후 : " + s))
                .limit(5)
                .peek(s -> System.out.println("limit(5) 실행 후 : " + s))
                .sorted()
                .peek(s -> System.out.println("sorted() 실행 후 : " + s))
                .forEach(n -> System.out.println(n));
    }
}