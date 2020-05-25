package com.hdgi.app;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(Application.class)
@RunWith(SpringRunner.class)
class ApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testWelcome() throws Exception {
        mockMvc.perform(get("/gesture")).andExpect(status().isOk());
    }
//	@Test
//	public void testWelcomeFailure() throws Exception {
//		mockMvc.perform(get("/")).andExpect(status().isOk());
//	}

}
