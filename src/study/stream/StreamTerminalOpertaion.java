package study.stream;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTerminalOpertaion {
    public static void main(String[] args){
        streamPrint();
        streamReduce();
        streamSearch();
        streamInspection();
        streamStatistics();
        streamCalculate();
        streamCollect();
    }

    public static void streamPrint(){
        /*
            forEach() 메소드는 스트림의 각 요소를 소모하여 명시된 동작을 수행한다.
            반환 타입이 void이므로 보통 스트림의 모든 요소를 출력하는데 사용된다
         */
        Stream<String> stream = Stream.of("넷", "셋", "둘", "하나");
        stream.forEach(System.out::println);
        System.out.println();
        System.out.println();
    }

    public static void streamReduce(){
        /*
            스트림의 최종 연산은 모두 스트림의 각 요소를 소모하여 연산한다.
            하지만 reduce() 메소드는 첫 번째와 두 번째 요소를 가지고 연산을 수행한 뒤 그 결과와 세번째 요소를 가지고 또 다시 연산을 수행한다
            이런 식으로 해당 스트림의 모든 요소를 소모하여 연산을 수행하고 그 결과를 반환한다
            인수로 초깃값을 전달하면 해당 스트림의 첫 번째 요소와 연산을 시작하며
         */

        Stream<String> stream1 = Stream.of("넷", "셋", "둘", "하나");
        Stream<String> stream2 = Stream.of("넷", "셋", "둘", "하나");

        Optional<String> result1 = stream1.reduce((s1, s2) -> s1+" "+s2);
        result1.ifPresent(System.out::println);

        // 인수로 초깃값을 전달하는 reduce()의 리턴 타입은 Optional<T> 가 아닌 T 타입이다.
        // 그 이유는 비어 있는 스트림과 reduce 연산을 할 경우 전달받은 초기값을 그대로 반환해야하기 때문이다
        String result2 = stream2.reduce("시작포인트", (s1, s2) -> s1+" "+s2);
        System.out.println(result2);
        System.out.println();
        System.out.println();
    }

    public static void streamSearch(){
        /*
            findFirst()와 findAny() 메소드는 해당 스트림에서 첫 번째 요소를 참조하는 Optional 객체를 반환한다
            두 메소드 모두 비어있는 스트림에서 비어있는 Optional 객체를 반환한다
         */
        IntStream stream1 = IntStream.of(4, 2, 7, 3, 5, 1, 6);
        IntStream stream2 = IntStream.of(4, 2, 7, 3, 5, 1, 6);

        OptionalInt result1 = stream1.findFirst();
        System.out.println(result1.getAsInt());

        OptionalInt result2 = stream2.findAny();
        System.out.println(result2.getAsInt());

        System.out.println();
        System.out.println();
    }

    public static void streamInspection(){
        /*
            해당 스트림의 요소 중에서 특정 조건을 만족하는 요소가 있는지, 아니면 모두 만족하거나 모두 만족하지 않는지를
            다음 메소드를 사용해 확인할 수 있다.
            anyMatch() : 해당 스트림의 일부 요소가 특정 조건을 만족할 때 true 반환
            allMatch() : 해당 스트림의 모든 요소가 특정 조건을 만족할 때 true 반환
            noneMatch() : 해당 스트림의 모든 요소가 특정 조건을 만족하지 않을 경우 true 반환

            세 메소드 모두 인수로 Predicate 객체를 전달받으며 요소의 검사 결과는 boolean 값으로 반환한다.
         */
        IntStream stream1 = IntStream.of(30, 90, 70, 10);
        IntStream stream2 = IntStream.of(30, 90, 70, 10);
        IntStream stream3 = IntStream.of(30, 90, 70, 10);
        System.out.println(stream1.anyMatch(n -> n>80));
        System.out.println(stream2.allMatch(n -> n>60));
        System.out.println(stream3.noneMatch(n -> n%2!=0));

        System.out.println();
        System.out.println();
    }

    public static void streamStatistics(){
        /*
            count() 메소드는 해당 스트림 요소의 총 개수를 long 타입의 값을 반환
            max(), min()은 해당 스트림 요소 중에서 가장 큰 값과 가장 작은 값을 가지는 요소를 참조하는 Optional객체를 얻을 수 있따.
         */

        IntStream stream1 = IntStream.of(30, 90, 70, 10);
        IntStream stream2 = IntStream.of(30, 90, 70, 10);
        System.out.println(stream1.count());
        System.out.println(stream2.max().getAsInt());

        System.out.println();
        System.out.println();
    }

    public static void streamCalculate(){
        /*
            IntStream이나 DoubleStream과 같ㅇ른 기본 타입 스트림에는 해당 스트림의 모든 요소에 대한 합과 평균을 구할 수 있는
            sum()과 average() 메소드가 각각 정의되어 있다.
            average() 메소드는 각 기본 타입으로 래핑 된 Optional 객체를 반환한다
         */
        IntStream stream1 = IntStream.of(30, 90, 70, 10);
        System.out.println(stream1.sum());

        DoubleStream stream2 = DoubleStream.of(30.3, 90.9, 70.7, 10.1);
        System.out.println(stream2.average().getAsDouble());

        System.out.println();
        System.out.println();
    }

    public static void streamCollect(){
        /*
            collect() 메소드는 인수로 전달되는 Collectors 객체에 구현된 방법대로 스트림의 요소를 수집한다
            또한 Collectors 클래스에는 미리 정의된 다양한 방법이 클래스 메소드로 정의되어 있다.
            이 외에도 사용자가 직접 Collector 인터페이스를 구현하여 자신만의 수집 방법을 정의할 수도 있다.
            1. 스트림을 배열이나 컬렉션으로 변환 : toArray(), toCollection(), toList(), toSet(), toMap()
            2. 요소의 통계와 연산 메소드와 같은 동작을 수행 : counting(), maxBy(), minBy(), summingInt(), averagingInt()
            3. 요소의 소모와 같은 동작을 수행 : reducing(), joining()
            4. 요소의 그룹화와 분할 : groupingBy(), partitioningBy()
         */

        // 스트림을 리스트로 변환하는 예제
        Stream<String> stream1 = Stream.of("넷", "둘", "하나", "셋");
        List<String> list1 = stream1.collect(Collectors.toList());

        IntStream stream2 = IntStream.of(30, 90, 70, 10);
        List<Integer> list2 = stream2.boxed().collect(Collectors.toList());

        // 글자 수를 홀,짝으로 나누어서 저장하는 예제
        Stream<String> stream3 = Stream.of("HTML", "CSS", "JAVA", "PHP");
        Map<Boolean, List<String>> map3 = stream3.collect(Collectors.groupingBy(e -> e.length()%2 == 0));
        List<String> oddLengthList = map3.get(false);
        List<String> evenLengthList = map3.get(true);
        System.out.println(oddLengthList.toString());
        System.out.println(evenLengthList.toString());
    }
}
