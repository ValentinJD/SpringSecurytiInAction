package ru.reactive.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
//@AutoConfigureMockMvc
class HelloControllerTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    public void helloUnauthenticated() throws Exception {
//        mockMvc.perform(get("/hello"))
//                .andExpect(status().isUnauthorized());
//    }
//
//    @Test
//    @WithMockUser
//    public void helloAuthenticated() throws Exception {
//        mockMvc.perform(get("/hello"))
//                .andExpect(content().string("Hello, user!"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    @WithMockUser(username = "mary")
//    public void helloAuthenticatedMary() throws Exception {
//        mockMvc.perform(get("/hello"))
//                .andExpect(content().string("Hello, mary!"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void helloAuthenticatedWithUser() throws Exception {
//        mockMvc.perform(get("/hello")
//                        .with(user("mary")))
//                .andExpect(content().string("Hello, mary!"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    @WithUserDetails("john")
//    public void helloAuthenticatedJohn() throws Exception {
//        mockMvc.perform(get("/hello"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    @WithCustomUser(username = "mary")
//    public void helloAuthenticated1() throws Exception {
//        mockMvc.perform(get("/hello"))
//                .andExpect(status().isOk());
//    }
}