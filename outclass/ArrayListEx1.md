## ArrayListEx1 : ArrayList를 사용해서 문자열 데이터를 저장하고 출력하는 기초 컬렉션 예제

```java
public class ArrayListEx1 {

	public static void main(String[] args) {
		ArrayList<String> aList = new ArrayList<String>();
		aList.add("홍길동");
		aList.add("이순신");
		aList.add("김제니");
		for (String item : aList) {
			System.out.print(item);
		}
	}
}
```
## (1)
```java
ArrayList<String> aList = new ArrayList<String>();
```
#### - ArrayList : 자바에서 자주 쓰는 가변 길이 배열
#### - String은 이 리스트에 문자열만 저장하겠다는 뜻

## (2)
```java
for (String item : aList) {...}
```
#### - 향상된 for문, 리스트에 있는 모든 요소를 하나씩 꺼내서 item에 저장하고 반복
