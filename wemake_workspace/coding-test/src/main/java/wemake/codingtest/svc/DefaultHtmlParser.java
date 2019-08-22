package wemake.codingtest.svc;



import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultHtmlParser implements HtmlParse {
	private Logger logger = LoggerFactory.getLogger(DefaultHtmlParser.class);
	private static final String charPattern  = "^[a-zA-Z0-9]*$";  
	private static final String numberPattern  = "^[0-9]*$"; 
	
	protected String preProcess(String htmlText) {
		return htmlText;
	}
	protected void postProcess(HtmlParsedResult result) {
		int totalCount = result.getAlphaStr().length() + result.getNumberStr().length();
		char[] resultMixedStream = new char[totalCount];
		int minCount;
		char[] alphaChars = result.getAlphaStr().toCharArray();
		char[] numberChars = result.getNumberStr().toCharArray();
		char[] temp ;
		if(alphaChars.length <= numberChars.length ) {
			minCount = alphaChars.length;
			temp = numberChars;
		}else {
			minCount = numberChars.length;
			temp = alphaChars;
		}
		
		int alphaIndex=0;
		int numIndex=1;
		
		for( int i = 0 ;i < minCount ; i++ ,alphaIndex+=2) {
			resultMixedStream[alphaIndex] = alphaChars[i];
		}
		
		for( int i = 0 ;i < minCount ; i++, numIndex +=2 ) {
			resultMixedStream[numIndex] = numberChars[i];
		}
		for( int i = 0 ;i < totalCount - (minCount*2) ; i++) {
			resultMixedStream[minCount*2 + i] = temp[minCount+i];
		}
		
		
		result.setMixStr( new String(resultMixedStream, 0, resultMixedStream.length));
	}
	
	private HtmlParsedResult getParsedHtmlResult(char[] arrayWord){
		HtmlParsedResult result = new HtmlParsedResult();
		for(int i=0;i<arrayWord.length;i++){ 
			String c = Character.toString(arrayWord[i]);
			if( Pattern.matches(charPattern,c)  ){
				System.out.print(arrayWord[i]); //출력
				if(Pattern.matches(numberPattern,c)) {
					result.addNumberChar(arrayWord[i]);
				}else {
					result.addAlphaChar(arrayWord[i]);
				}
			}
		}
		return result;
	}
	
	@Override
	public HtmlParsedResult parseHtmlText(String htmlText) {
		String temp  = preProcess(htmlText);
		char[] arrayWord = temp.toCharArray();
		HtmlParsedResult result = getParsedHtmlResult(arrayWord);
		result.sort();
		postProcess(result);
		return result;
	}
}
