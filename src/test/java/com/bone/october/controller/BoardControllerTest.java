package com.bone.october.controller;

import com.bone.october.JunitDocument;

import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @JunitDocument("Board - 게시물 생성")
    void create() throws Exception {
        MultiValueMap<String, String> info = new LinkedMultiValueMap<>();

        info.add("title", "title");
        info.add("name", "summer");
        info.add("contents", "contents");

        mockMvc.perform(post("/v1/board/create")
                .params(info))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @JunitDocument("Board - 게시물 상세 조회")
    void read() throws Exception {
        mockMvc.perform(get("/v1/board/read/1")).andExpect(status().isOk()).andDo(print());
    }

    @Test
    @JunitDocument("Board - 게시물 목록 조회")
    void readAll() throws Exception {
        mockMvc.perform(get("/v1/board/read")).andExpect(status().isOk()).andDo(print());
    }

    @Test
    @JunitDocument("Board - 게시물 업데이트")
    void update() throws Exception {

        MultiValueMap<String, String> info = new LinkedMultiValueMap<>();
        info.add("id", "1");
        info.add("title", "updated_v2");
        info.add("contents", "updated_V2");

        mockMvc.perform(post("/v1/board/update").params(info))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @JunitDocument("Board - 게시물 삭제")
    void delete() throws Exception {

        MultiValueMap<String, String> info = new LinkedMultiValueMap<>();
        info.add("id", "4");

        mockMvc.perform(post("/v1/board/delete").params(info))
                .andExpect(status().isOk())
                .andDo(print());

    }
}