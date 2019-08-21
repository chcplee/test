package wemake.codingtest.svc;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import wemake.codingtest.api.RequestTestDto;
import wemake.codingtest.api.ResponseDto;

@Service
public class TestService {
	
	
	public ResponseDto doTest(RequestTestDto request) {
		String cBuf;
		String nBuf;
		
		return null;
	}

	private void test111() {
		String test = "나는 이창하다ab@13차23ㄴ3ㅠdab23!#^89";		
		char[] array_word = new char[test.length()]; // 스트링을 담을 배열

		char[] arrayWord = test.toCharArray();
		
		for(int i=0;i<array_word.length;i++){ 
			array_word[i]=(test.charAt(i));//스트링을 한글자씩 끊어 배열에 저장
			System.out.print(array_word[i]); //출력
		}
		System.out.println(" ==================== ");
		System.out.println(arrayWord); //출력
		
		String test2 = new String(arrayWord, 0, arrayWord.length);
		System.out.println(test2); //출력
		System.out.println(" ==================== ");
		
		String pattern  = "^[a-zA-Z0-9]*$";  
		for(int i=0;i<array_word.length;i++){ 
			String c = Character.toString(arrayWord[i]);
			if( Pattern.matches(pattern,c)  ){
				System.out.print(array_word[i]); //출력
			}
			//System.out.print(array_word[i]); //출력
		}
		//String test2 = new Character(array_word).toString();
		//System.out.println(" ==================== " + test2);
		//System.out.println(test.length() + "  싸이즈 " + array_word.length);
	}
}
