package test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Jaccard {
	public static void main(String[] args) {
		final String str1 = "E=M*C^2";
		final String str2 = "e=m*c^2";
		
		Process process = new Process();
		process.JaccardProcess(str1, str2, false);
	}
}

/*
 * 입력으로는 str1과 str2의 두 문자열이 들어온다.
 * 각 문자열의 길이는 2 이상, 1,000 이하이다.
 * 입력으로 들어온 문자열은 두 글자씩 끊어서 다중집합의 원소로 만든다.
 * 이때 영문자로 된 글자 쌍만 유효하고,
 * 기타 공백이나 숫자, 특수 문자가 들어있는 경우는 그 글자 쌍을 버린다.
 * 예를 들어 “ab+”가 입력으로 들어오면,
 * “ab”만 다중집합의 원소로 삼고, “b+”는 버린다.
 * 다중집합 원소 사이를 비교할 때, 대문자와 소문자의 차이는 무시한다.
 * “AB”와 “Ab”, “ab”는 같은 원소로 취급한다.
 */
class Process {
	public void JaccardProcess(String s1, String s2, boolean flg) {
		final String PATTERNMATCHSTR =  "^[a-zA-Z]*$";
		
		s1 = s1.toLowerCase();
		s2 = s2.toLowerCase();
		
		List<String> listStr1, listStr2, intersection, union;
		listStr1 = new ArrayList<String>();
		listStr2 = new ArrayList<String>();
		intersection = new ArrayList<String>(); //교집합
		union = new ArrayList<String>(); //합집합
		
		int s1Leng = s1.length(), s2Leng = s2.length();
		
		for(int s1i = 0; s1i < s1Leng-1; s1i++) {
			String s1iStr1 = String.valueOf(s1.charAt(s1i));
			String s1iStr2 = String.valueOf(s1.charAt(s1i+1));
			
			if(Pattern.matches(PATTERNMATCHSTR, s1iStr1) && Pattern.matches(PATTERNMATCHSTR, s1iStr2)) {
				listStr1.add(s1iStr1 + s1iStr2);
			}
		}

		for(int s2i = 0; s2i < s2Leng-1; s2i++) {
			String s2iStr1 = String.valueOf(s2.charAt(s2i));
			String s2iStr2 = String.valueOf(s2.charAt(s2i+1));
			
			if(Pattern.matches(PATTERNMATCHSTR, s2iStr1) && Pattern.matches(PATTERNMATCHSTR, s2iStr2)) {
				listStr2.add(s2iStr1 + s2iStr2);
			}
		}
		
		//교집합
		listStr1.forEach(v1 -> {
			if(v1.indexOf(" ") < 0) {
				if(listStr2.remove(v1)) {
					intersection.add(v1);
				}
				
				union.add(v1);	
			}
		});
		
		//합집합
		listStr2.forEach(v2 -> {
			if(v2.indexOf(" ") < 0) {
				union.add(v2);
			}
		});
		
		float intersectionSize = (float) intersection.size();
		float unionSize = union.size();
		
		float resultJaccrd = unionSize != 0? intersectionSize / unionSize : 1;
		
		System.out.println("resultJaccrd : " + (int) resultJaccrd);
	}
}