package wemake.codingtest.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DefaultController {
	private Logger logger = LoggerFactory.getLogger(DefaultController.class);
	
	@RequestMapping(value="/test-jsp",method=RequestMethod.GET)
	public String getPage() {
		logger.info("getPage !!!!!");
		return "test";
	}
}
