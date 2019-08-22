package wemake.codingtest.svc;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import wemake.codingtest.api.RequestTestDto;
import wemake.codingtest.api.ResponseDto;

@Service
public class TestService {
	private Logger logger = LoggerFactory.getLogger(TestService.class);
	
	
	private String getHtml(String url) {
		logger.info(url);
		//String testString = "나o는 k O이A창B하E다zab@0103차2F3*Zㄴ3ㅠGdab23iI!#^89";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_HTML); 
		//HttpEntity param= new HttpEntity(JSONInput, headers); 
		RestTemplate restTemplate = new RestTemplate(); 
		ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);
		logger.info(result.getBody());
		return result.getBody();
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
			parser = new HtmlParserWithoutTag();
		}
		// 정렬된 상태로 알파벳 문자 배열과 숫자 배열을 가지고 있음
		HtmlParsedResult result = parser.parseHtmlText(html);
		logger.info("**************************** 알파벳 =     " + result.getAlphaStr() + " ****************************");
		//logger.info("숫자 = " + result.getNumberStr());
		logger.info("mix = " + result.getMixStr());
		ResponseDto respDto = new ResponseDto();
		joinTwoStreamAndGroup(result,respDto,request.getGroupCount());
		logger.info("결과값 = " + respDto.toString());
		return respDto;
	}
	
	private void joinTwoStreamAndGroup(HtmlParsedResult result, ResponseDto resp, int groupCount) {
		String mixedStr = result.getMixStr();
		int totalCount = mixedStr.length();
		if( totalCount == groupCount || groupCount == 1) {
			resp.setQuotient(mixedStr);
			return ;
		}else if(groupCount > totalCount ) {
			resp.setRemainder(mixedStr);
			return;
		}
		int remainerCount = totalCount % groupCount;
		logger.info("나머지 = " + remainerCount);
		String quotient = mixedStr.substring(0, totalCount - remainerCount);
		String remainer = mixedStr.substring(totalCount - remainerCount, totalCount);
		resp.setQuotient(quotient);
		resp.setRemainder(remainer);
	}
}
