package wemake.codingtest.svc;

import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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
		logger.info("HTML을 받기 위한 요청 URL : " + url);
		
		RestTemplate restTemplate = new RestTemplate(); 
		HttpHeaders headers = new HttpHeaders();
        Charset utf8 = Charset.forName("UTF-8");
        MediaType mediaType = new MediaType("text", "html", utf8);
        headers.setContentType(mediaType); 
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)" +
        		" AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        String result = responseEntity.getBody();
        logger.info("응답 받은 HTML : " + result);
        return result;

	}
	
	public ResponseDto doTest(RequestTestDto request) {
		logger.info("서비스 호출!!!");
		String html = getHtml(request.getUrl());
		HtmlParse parser = null;
		if(ParsingType.FULL_TEXT.getTypeString().equals(request.getOption())){
			parser = new DefaultHtmlParser();
		}else {
			parser = new HtmlParserWithoutTag();
		}
		// 정렬된 상태로 알파벳 문자 배열과 숫자 배열을 가지고 있음
		HtmlParsedResult result = parser.parseHtmlText(html);
		logger.debug("**************************** 알파벳 =     " + result.getAlphaStr() + " ****************************");
		logger.debug("숫자 = " + result.getNumberStr());
		logger.debug("mix = " + result.getMixStr());
		ResponseDto respDto = new ResponseDto();
		group(result,respDto,request.getGroupCount());
		logger.debug("결과값 = " + respDto.toString());
		return respDto;
	}
	
	private void group(HtmlParsedResult result, ResponseDto resp, int groupCount) {
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
		logger.debug("나머지 = " + remainerCount);
		String quotient = mixedStr.substring(0, totalCount - remainerCount);
		String remainer = mixedStr.substring(totalCount - remainerCount, totalCount);
		resp.setQuotient(quotient);
		resp.setRemainder(remainer);
	}
}
