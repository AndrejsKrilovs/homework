package org.example.book;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class IndexPageTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void loadApplicationTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(SecurityMockMvcResultMatchers.unauthenticated());
    }

    @Test
    public void bookListTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(SecurityMockMvcResultMatchers.unauthenticated())
                .andExpect(MockMvcResultMatchers.xpath("//*[@id='bookList']/tr").nodeCount(50));
    }

    @Test
    public void goToNextPageTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/next"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(SecurityMockMvcResultMatchers.unauthenticated());
    }

    @Test
    public void goToPreviousPageTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/previous"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(SecurityMockMvcResultMatchers.unauthenticated());
    }

    @Test
    public void addNewBookTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/add"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(SecurityMockMvcResultMatchers.unauthenticated());
    }
}
