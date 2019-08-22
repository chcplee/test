package wemake.codingtest.svc;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import wemake.codingtest.api.RequestTestDto;
import wemake.codingtest.api.ResponseDto;

@Service
public class TestService {
	private Logger logger = LoggerFactory.getLogger(TestService.class);
	
	
	private String getHtml(String url) {
		
		String testString = "나o는 k O이A창B하E다zab@0103차2F3*Zㄴ3ㅠGdab23iI!#^89";
		return testString;
	}
	
	public ResponseDto doTest(RequestTestDto request) {
		logger.info("서비스 호출!!!");
		logger.info(request.toString());
		String html = getHtml(request.getUrl());
		HtmlParse parser = null;
		ParsingType type;
		if(ParsingType.FULL_TEXT.getTypeString().equals(request.getOption())){
			parser = new DefaultHtmlParser();
		}else {
			parser = new HtmlParserWithTag();
		}
		// 정렬된 상태로 알파벳 문자 배열과 숫자 배열을 가지고 있음
		HtmlParsedResult result = parser.parseHtmlText(html);
		logger.info("알파벳 = " + result.getAlphaStr());
		logger.info("숫자 = " + result.getNumberStr());
		logger.info("mix = " + result.getMixStr());
		ResponseDto respDto = new ResponseDto();
		joinTwoStreamAndGroup(result,respDto,request.getGroupCount());
		return respDto;
	}
	
	private void test111() {
		String test = "나는 이A창B하E다ab@13차2F3ㄴ3ㅠGdab23!#^89";		
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
	private void joinTwoStreamAndGroup(HtmlParsedResult result, ResponseDto resp, int groupCount) {
		
	}
}
