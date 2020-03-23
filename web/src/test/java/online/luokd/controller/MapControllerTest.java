package online.luokd.controller;

import online.luokd.mapper.MapMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class MapControllerTest {
    private MockMvc mvc;

    //初始化执行
    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new MapController()).build();
    }

    //验证controller是否正常响应并打印返回结果
    @Test
    public void hello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/hi").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void getAll() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/all").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
//    @Test
//    public void insert() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get("/add")
//                .accept(MediaType.APPLICATION_JSON)
//                .params()
//        )
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//    }
    @Test
    public void delete() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/delete").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
    @Test
    public void update() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/update").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void deleteByKey() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/delete-by-key").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}