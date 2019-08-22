package wemake.codingtest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.google.gson.Gson;

import wemake.codingtest.api.RequestTestDto;
import wemake.codingtest.api.ResponseDto;
import wemake.codingtest.svc.ParsingType;

@RunWith(SpringRunner.class)
@SpringBootTest//(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class TestController  {
	 @Autowired
	 private MockMvc mockMvc;
	 
	 @Test
	 public void doTest() throws Exception {
		 RequestTestDto testDto = new RequestTestDto();
		 testDto.setUrl("www.naver.com");
		 testDto.setOption(ParsingType.FULL_TEXT.getTypeString());
		 testDto.setGroupCount(5);
		 Gson gson = new Gson();
		 String json = gson.toJson(testDto);
		 
         this.mockMvc.perform(
        		 MockMvcRequestBuilders.post("/test")
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(json)).andReturn();
		 //..andExpect(jsonPath("$.deployDate").exists());
		 //andExpect(MockMvcResutMatchers.status().is(HttpStatus.OK.value()));
		/* RequestTestDto testDto = new RequestTestDto();
		 testDto.setUrl("www.naver.com");
		 testDto.setOption("ttt");
		 testDto.setGroupCount(5);
		 ResponseDto respDto = template.postForObject("/test", testDto, ResponseDto.class);*/
		 //this.mockMvc.perform(get("/test"));
		 //this.mockMvc.perform(get("/test")).a
	        /* .andDo(print())
	         .andExpect(status().is(HttpStatus.OK.value()))
	         .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	         .andExpect(jsonPath("$.deployDate").exists())
	         .andExpect(jsonPath("$.deployVersion").exists())
	         .andExpect(jsonPath("$.distributor").exists());*/
	 }
}
