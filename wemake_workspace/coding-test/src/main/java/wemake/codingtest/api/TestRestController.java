package wemake.codingtest.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import wemake.codingtest.svc.TestService;


@RestController
public class TestRestController {
	private Logger logger = LoggerFactory.getLogger(TestRestController.class);
	
	@Autowired
	private TestService service;
	

	@RequestMapping(value="/test/1",method=RequestMethod.GET)
	public ResponseDto getResult2() {
		logger.info("테스트 함수 시작 11111");
		ResponseDto res = new ResponseDto();
		return res;
	}
	
	@RequestMapping(value="/test",method=RequestMethod.POST,produces="application/json")
	public ResponseDto getResult(@RequestBody RequestTestDto request) {
		logger.info("테스트 함수 시작");
		logger.info(request.toString());
		
		ResponseDto res = service.doTest(request);
		return res;
	}
}
