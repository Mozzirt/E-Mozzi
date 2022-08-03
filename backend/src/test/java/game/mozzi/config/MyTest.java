package game.mozzi.config;



import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@RunWith(SpringRunner.class)
public class MyTest {
    @Autowired MockMvc mockMvc;

//    비회원 로그인 로그아웃 테스트
    @Test
    public void signUp() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/auth/test/callback"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        MockHttpServletRequest request = mvcResult.getRequest();
        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(200,response.getStatus());

        String content = response.getContentAsString();

        JSONParser jsonParser = new JSONParser();

        Object obj = jsonParser.parse(content);

        JSONObject jsonObject = (JSONObject) obj;

//        System.out.printf(content);
        String socialId = (String) jsonObject.get("data");

//        System.out.printf("this is social======"+socialId);
        MvcResult mvcResultLogOut = mockMvc.perform(get("/guest/logout")
                        .sessionAttr("socialId",socialId)
                        .param("socialId",socialId)
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        MockHttpServletResponse responseLogOut = mvcResultLogOut.getResponse();
        assertEquals(200,responseLogOut.getStatus());

    }
}
