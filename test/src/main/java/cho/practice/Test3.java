package cho.practice;

import java.util.ArrayList;

public class Test3 {
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		list.add("a");
		list.add(null);
		list.add("b");
		list.add("c");
		list.add(null);
		list.add("d");
		list.add(null);
		list.add("e");
		System.out.println("remove 전 list : " + list);
		int i = 0;
		
		//오케이 읍면동 제거하는 코드
		for (String string : list) {
			System.out.println( i + "번쨰 list : " + string );
			if(string == null) {
				list.set(i, "읍면동 없음");
			}
			i++;
		}
		System.out.println("null 대체를 한 list : " + list);
		
	}
}
