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
		 * 1. �÷���
		 * �ڹٿ��� �����ϴ� ��� �÷����� �ְ� ���� ������ Collection �������̽������� stream() �޼ҵ尡 ���ǵǾ� ����.
		 * ���� Collection �������̽��� ������ ��� List�� Set �÷��� Ŭ���������� stream() �޼ҵ�� ��Ʈ�� ���� ����.
		 * parallelStream()�� ����ϸ� ���� ó���� ������ ��Ʈ�� ���� ����
		 */
		ArrayList<Integer> al = new ArrayList<>();
		al.add(1);
		al.add(2);
		al.add(5);
		al.add(6);
		
		// �÷��ǿ��� ��Ʈ�� ����
		Stream<Integer> s = al.stream();
		s.forEach(System.out::println); // PrintStream
		// Stream Ŭ������ forEach()�� �ش� ��Ʈ���� ��Ҹ� �ϳ��� �Ҹ��ذ��� ���������� ��ҿ� �����ϴ� �޼ҵ��̴�.
		// ���� ���� ��Ʈ�����δ� forEach()�� �ѹ��� ȣ���� �� �ִ�.
		// �ٸ� ���� �������� ��Ҹ� �Ҹ��ϴ� ���� �ƴϹǷ� ���� �����Ϳ��� �� �ٸ� ��Ʈ���� �����Ͽ� forEach() �޼ҵ带 ȣ���ϴ� ���� ����
	}
	
	public static void makeArray() {
		/*
		 * 2. �迭
		 * �迭�� ���� ��Ʈ���� �����ϱ� ���ؼ� Arrays Ŭ������ �پ��� ������ stream() �޼ҵ尡 Ŭ���� �޼ҵ�� ���ǵǾ� ����.
		 * ���� �⺻ Ÿ���� int, long, double ���� ������ �� �ִ� �迭�� ���� ��Ʈ���� ������ ���ǵǾ� ����.
		 * �̷��� ��Ʈ���� java.util.stream ��Ű���� IntStream, LongStream, DoubleStream �������̽��� ���� �����ȴ�.
		 */
		String[] arr1 = new String[] {"�ϳ�", "��", "��"};
		
		Stream<String> stream1 = Arrays.stream(arr1);
		stream1.forEach(s -> System.out.print(s+" "));
		System.out.println("");
		//�迭�� Ư�� �κи��� �̿��� ��Ʈ�� ����
		Stream<String> stream2 = Arrays.stream(arr1, 1, 3);
		stream2.forEach(e -> System.out.print(e+" "));
	}

	public static void makeVariableParameter() {
		/*
		 * 3. ���� �Ű�����
		 * Stream Ŭ������ of() �޼ҵ带 �̿��ϸ� ���� �Ű�����(variable parameter)�� ���޹޾� ��Ʈ���� ������ �� �ֵ�.
		 */
		Stream<Integer> stream = Stream.of(4, 1, 3, 0);
		stream.forEach(System.out::println);
	}
	
	public static void makeRangeInteger() {
		/*
		 * 4. ������ ������ ���ӵ� ����
		 * ������ ������ ���ӵ� ������ ��Ʈ������ �����ϱ� ���� IntStream�̳� LongStream �������̽����� range()�� rangeClosed() �޼ҵ尡 ���ǵǾ� �ֵ�.
		 * range() : ��õ� ���� ������ ���������� ��õ� ������ ������ �������� �ʴ� ��Ʈ���� �����Ѵ�.
		 * rangeClosed() : ��õ� ���� ���� �Ӹ� �ƴ϶� ��õ� ������ ������ �����ϴ� ��Ʈ���� �����Ѵ�.
		 */
		IntStream iStream = IntStream.range(0, 3);
		iStream.forEach(System.out::println);
		
		LongStream lStream = LongStream.rangeClosed(0, 5);
		lStream.forEach(System.out::println);
	}

	public static void makeCertainTypeRandomNumber() {
		/*
		 * 5. Ư�� Ÿ���� ������
		 * Ư�� Ÿ���� ������ �̷���� ��Ʈ���� �����ϱ� ���� Random Ŭ�������� ints(), longs(), doubles() �� ���� �޼ҵ尡 �ִ�.
		 * �� �޼ҵ���� �Ű������� ���޹��� ������ ũ�Ⱑ �������� ���� ���� ��Ʈ��(infinite stream)�� ��ȯ�Ѵ�.
		 * �̶����� limit() �޼ҵ带 ����� ��Ʈ���� ũ�⸦ �����ؾ� ��
		 */
		IntStream iStream = new Random().ints(4);
		iStream.forEach(System.out::println);
	}

	public static void makeLambdaExpression() {
		/*
		 * 6. ���� ǥ����
		 * ���� ǥ������ �Ű������� �޾� �ش� ���� ǥ���Ŀ� ��ȯ�Ǵ� ���� ��ҷ� �ϴ� ���� ��Ʈ���� �����ϱ� ���� Stream Ŭ��������
		 * iterate() �� generate() �޼ҵ尡 ���ǵǾ� ����.
		 * iterate() �޼ҵ�� �õ�(seed)�� ��õ� ���� ���� ǥ���Ŀ� ����Ͽ� ��ȯ�� ���� �ٽ� �õ�� ����ϴ� ������� ���� ��Ʈ���� ������
		 * �ݸ� generate() �� �Ű������� ���� ���� ǥ������ ����� ��ȯ�� ������ ���� ��Ʈ���� ����
		 */
		// IntStream iStream = Stream.iterate(2, n -> n+2);
		// ���� �̷��� �������� �ȴ����
	}
	
	public static void makeFile() {
		/*
		 * 7. ����
		 * ������ �� ��(line)�� ��ҷ� �ϴ� ��Ʈ���� �����ϱ� ���� java.nio.file.Files Ŭ�������� lines() �޼ҵ尡 ���ǵǾ� ����
		 * ���� java.io.BufferedReader Ŭ������ lines() �޼ҵ带 ����ϸ� ���ϻӸ� �ƴ϶� �ٸ� �Էµ����ͷκ��͵� �����͸�
		 * ��(line) ������ �о�� �� �ֽ��ϴ�.
		 */
		// String<String> Stream = Files.lines("");
		// ����� ���� String<String> �̾ƴ϶� Stream<String>�ƴ�?
	}
	
	public static void makeEmptyStream() {
		/*
		 * 8. �� ��Ʈ��
		 * �ƹ� ��ҵ� ������ �ʴ� �� ��Ʈ���� Stream Ŭ������ empty() �޼ҵ带 ����� ������ �� ����
		 */
		Stream<Object> eStream = Stream.empty();
		System.out.println(eStream.count());
	}
}
