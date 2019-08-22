package wemake.codingtest.svc;

import java.util.Arrays;
import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HtmlParsedResult implements Comparator<Character> {
	private Logger logger = LoggerFactory.getLogger(HtmlParsedResult.class);
	private String mixStr;
	
	private StringBuffer alphaStrBuf = new StringBuffer();
	private StringBuffer numberStrBuf = new StringBuffer();
	
	public void sort() {
		//String[] tempStringArry = new String[alphaStrBuf.length()];
		logger.info("알파벳 변환전 : " + alphaStrBuf.toString() + "  카운트 : " +alphaStrBuf.length());
		char[] temp = alphaStrBuf.toString().toCharArray();
		Character[] charArray = new Character[alphaStrBuf.length()];
		for( int i = 0 ; i < alphaStrBuf.length();i++) {
			charArray[i] = new Character(temp[i]);
		}
		Arrays.sort(charArray,this);
		alphaStrBuf = new StringBuffer();
		for( int i = 0 ; i < temp.length;i++) {
			alphaStrBuf.append(charArray[i].charValue());
		}
		
		logger.info("숫자 변환전 : " + numberStrBuf.toString());
		temp = numberStrBuf.toString().toCharArray();
		Arrays.sort(temp);
		numberStrBuf = new StringBuffer();
		numberStrBuf.append(temp);
	}
	
	public String getAlphaStr() {
		return alphaStrBuf.toString();
	}
	
	public String getNumberStr() {
		return numberStrBuf.toString();
	}
	
	public void addAlphaChar(char c) {
		alphaStrBuf.append(c);
	}
	
	public void addNumberChar(char c) {
		numberStrBuf.append(c);
	}
	public String getMixStr() {
		return mixStr;
	}
	public void setMixStr(String mixStr) {
		this.mixStr = mixStr;
	}

	@Override
	public int compare(Character o1, Character o2) {
		//logger.info("비교 시작");
		
		//logger.info("o1 = " + o1.toString());
		//logger.info("o2 = " + o2.toString());
		if(o1.charValue() == o2.charValue()) {   // 같은지 검사
			//logger.info("같다");
			return 0;
		}
		boolean left = true;
		char o1_1 = o1.charValue();
		if(Character.isLowerCase(o1.charValue())) {   // 왼쪽이 소문자
			o1_1 = Character.toUpperCase(o1.charValue());
		}
		
		char o2_1 = o2.charValue();
		if(Character.isLowerCase(o2.charValue())) {   // 오른쪽이 소문자
			o2_1 = Character.toUpperCase(o2.charValue());
			left = false;
		}
		//logger.info("대문자로 변환 " + Character.toString(o1_1) +" , "+ Character.toString(o2_1));
		if(o1_1 == o2_1) {   // 대소 문자만 다른 같은 알파벳
			if( left == true) {   // 왼쪽이 소문자이므로 큰값
				//logger.info("대소 문자만 다른 같은 알파벳   오른쪽이 크다");
				return 1;
			}else {
				//logger.info("대소 문자만 다른 같은 알파벳   왼쪽이 작다");
				return -1;
			}
		}
		// 완전 다른 알파벳
		if( o1_1 < o2_1) {
			//logger.info("완전 다른 알파벳  왼쪽이 작다");
			return -1;
		}else {
			//logger.info("완전 다른 알파벳 오른쪽이 크다");
			return 1;
		}
	}
}
