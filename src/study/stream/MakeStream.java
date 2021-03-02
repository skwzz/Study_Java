package study.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class MakeStream {

	public static void main(String[] args) {
		makeCollection();
		makeArray();
		makeVariableParameter();
		makeRangeInteger();
		makeCertainTypeRandomNumber();
		makeLambdaExpression();
		makeFile();
		makeEmptyStream();
	}

	public static void makeCollection() {
		/*
		 * 1. 컬렉션
		 * 자바에서 제공하는 모든 컬렉션의 최고 상위 조상인 Collection 인터페이스에서는 stream() 메소드가 정의되어 있음.
		 * 따라서 Collection 인터페이스를 구현한 모든 List와 Set 컬렉션 클래스에서도 stream() 메소드로 스트림 생성 가능.
		 * parallelStream()을 사용하면 병렬 처리가 가능한 스트림 생성 가능
		 */
		ArrayList<Integer> al = new ArrayList<>();
		al.add(1);
		al.add(2);
		al.add(5);
		al.add(6);
		
		// 컬렉션에서 스트림 생성
		Stream<Integer> s = al.stream();
		s.forEach(System.out::println); // PrintStream
		// Stream 클래스의 forEach()는 해당 스트림의 요소를 하나씩 소모해가며 순차적으로 요소에 접근하는 메소드이다.
		// 따라서 같은 스트림으로는 forEach()를 한번만 호출할 수 있다.
		// 다만 원본 데이터의 요소를 소모하는 것은 아니므로 같은 데이터에서 또 다른 스트림을 생성하여 forEach() 메소드를 호출하는 것은 가능
	}
	
	public static void makeArray() {
		/*
		 * 2. 배열
		 * 배열에 관한 스트림을 생성하기 위해선 Arrays 클래스에 다양한 형태의 stream() 메소드가 클래스 메소드로 정의되어 있음.
		 * 또한 기본 타입인 int, long, double 형을 저장할 수 있는 배열에 관한 스트림이 별도로 정의되어 있음.
		 * 이러한 스트림은 java.util.stream 패키지의 IntStream, LongStream, DoubleStream 인터페이스로 각각 제공된다.
		 */
		String[] arr1 = new String[] {"하나", "둘", "셋"};
		
		Stream<String> stream1 = Arrays.stream(arr1);
		stream1.forEach(s -> System.out.print(s+" "));
		System.out.println("");
		//배열의 특정 부분만을 이용한 스트림 생성
		Stream<String> stream2 = Arrays.stream(arr1, 1, 3);
		stream2.forEach(e -> System.out.print(e+" "));
	}

	public static void makeVariableParameter() {
		/*
		 * 3. 가변 매개변수
		 * Stream 클래스의 of() 메소드를 이용하면 가변 매개변수(variable parameter)를 전달받아 스트림을 생성할 수 있따.
		 */
		Stream<Integer> stream = Stream.of(4, 1, 3, 0);
		stream.forEach(System.out::println);
	}
	
	public static void makeRangeInteger() {
		/*
		 * 4. 지정된 범위의 연속된 정수
		 * 지정된 범위의 연속된 정수를 스트림으로 생성하기 위해 IntStream이나 LongStream 인터페이스에는 range()나 rangeClosed() 메소드가 정의되어 있따.
		 * range() : 명시된 시작 정수를 포함하지만 명시된 마지막 정수는 포함하지 않는 스트림을 생성한다.
		 * rangeClosed() : 명시된 시작 정수 뿐만 아니라 명시된 마지막 정수를 포함하는 스트림을 생성한다.
		 */
		IntStream iStream = IntStream.range(0, 3);
		iStream.forEach(System.out::println);
		
		LongStream lStream = LongStream.rangeClosed(0, 5);
		lStream.forEach(System.out::println);
	}

	public static void makeCertainTypeRandomNumber() {
		/*
		 * 5. 특정 타입의 난수들
		 * 특정 타입의 난수로 이루어진 스트림을 생성하기 위해 Random 클래스에는 ints(), longs(), doubles() 와 같은 메소드가 있다.
		 * 이 메소드들은 매개변수를 전달받지 않으면 크기가 정해지지 않은 무한 스트림(infinite stream)을 반환한다.
		 * 이때에는 limit() 메소드를 사용해 스트림의 크기를 제한해야 함
		 */
		IntStream iStream = new Random().ints(4);
		iStream.forEach(System.out::println);
	}

	public static void makeLambdaExpression() {
		/*
		 * 6. 람다 표현식
		 * 람다 표현식을 매개변수로 받아 해당 람다 표현식에 반환되는 값을 요소로 하는 무한 스트림을 생성하기 위해 Stream 클래스에는
		 * iterate() 와 generate() 메소드가 정의되어 있음.
		 * iterate() 메소드는 시드(seed)로 명시된 값을 람다 표현식에 사용하여 반환된 값을 다시 시드로 사용하는 방식으로 무한 스트림을 생성함
		 * 반면 generate() 는 매개변수가 없는 람다 표현식을 사용해 반환된 값으로 무한 스트림을 생성
		 */
		// IntStream iStream = Stream.iterate(2, n -> n+2);
		// 설명엔 이런데 버전때매 안대느듯
	}
	
	public static void makeFile() {
		/*
		 * 7. 파일
		 * 파일의 한 행(line)을 요소로 하는 스트림을 생성하기 위해 java.nio.file.Files 클래스에는 lines() 메소드가 정의되어 있음
		 * 또한 java.io.BufferedReader 클래스의 lines() 메소드를 사용하면 파일뿐만 아니라 다른 입력데이터로부터도 데이터를
		 * 행(line) 단위로 읽어올 수 있습니다.
		 */
		// String<String> Stream = Files.lines("");
		// 무어ㅑ 씨발 String<String> 이아니라 Stream<String>아님?
	}
	
	public static void makeEmptyStream() {
		/*
		 * 8. 빈 스트림
		 * 아무 요소도 가지지 않는 빈 스트림은 Stream 클래스의 empty() 메소드를 사용해 생성할 수 있음
		 */
		Stream<Object> eStream = Stream.empty();
		System.out.println(eStream.count());
	}
}
