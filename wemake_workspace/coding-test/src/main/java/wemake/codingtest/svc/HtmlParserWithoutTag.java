package wemake.codingtest.svc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HtmlParserWithoutTag extends DefaultHtmlParser {
	private Logger logger = LoggerFactory.getLogger(HtmlParserWithoutTag.class);
	
	private static final String tagPattern  ="<[^>]*>" ;  
	
	@Override
	protected String preProcess(String htmlText) {
		String temp = htmlText.replaceAll(tagPattern, "");
		logger.info("--------------------------------------------- 태그 제거 한 후 : ");
		logger.info(temp);
		logger.info("---------------------------------------------------------------");	
		return temp;
	}
}
