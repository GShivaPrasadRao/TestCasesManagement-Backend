package com.buildtechknowledge.spring.data.mongodb;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.buildtechknowledge.spring.data.mongodb.controller.TutorialController;
import com.buildtechknowledge.spring.data.mongodb.model.Tutorial;
import com.buildtechknowledge.spring.data.mongodb.repository.TutorialRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(TutorialController.class)
public class TutorialControllerTest {

    @MockBean
    private TutorialRepository tutorialRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldCreateTutorial() throws Exception {

        Tutorial tutorial = new Tutorial("123", "Spring Boot @WebMvcTest", "Description", true);

//        //Method 1
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/tutorials");
//        ResultActions perform = mockMvc.perform(requestBuilder);
//        MvcResult mvcResult = perform.andReturn();
//        MockHttpServletResponse response = mvcResult.getResponse();
//        int status = response.getStatus();
//        Assert.assertEquals(200,status);

        //Method 2
        mockMvc.perform(post("/api/tutorials").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tutorial)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void shouldReturnTutorial() throws Exception {
        // long id = 1L;
        String id = "6318758ada2b9a38afc99485";
        Tutorial tutorial = new Tutorial(id, "Spring Boot @WebMvcTest", "Description", true);

        when(tutorialRepository.findById(id)).thenReturn(Optional.of(tutorial));
        mockMvc.perform(get("/api/tutorials/{id}", id)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.title").value(tutorial.getTitle()))
                .andExpect(jsonPath("$.description").value(tutorial.getDescription()))
                .andExpect(jsonPath("$.published").value(tutorial.isPublished()))
                .andDo(print());
    }

    @Test
    public void shouldReturnNotFoundTutorial() throws Exception {
        //long id = 1L;
        String id = "1";
        when(tutorialRepository.findById(id)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/tutorials/{id}", id))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void shouldReturnListOfTutorials() throws Exception {
        List<Tutorial> tutorials = new ArrayList<>(
                Arrays.asList(new Tutorial("1", "Spring Boot @WebMvcTest 1", "Description 1", true),
                        new Tutorial("2", "Spring Boot @WebMvcTest 2", "Description 2", true),
                        new Tutorial("3", "Spring Boot @WebMvcTest 3", "Description 3", true)));

        when(tutorialRepository.findAll()).thenReturn(tutorials);
        mockMvc.perform(get("/api/tutorials"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(tutorials.size()))
                .andDo(print());
    }

    @Test
    public void shouldReturnListOfTutorialsWithFilter() throws Exception {
        List<Tutorial> tutorials = new ArrayList<>(
                Arrays.asList(new Tutorial("1", "Spring Boot @WebMvcTest", "Description 1", true),
                        new Tutorial("3", "Spring Boot Web MVC", "Description 3", true)));

        String title = "Boot";
        MultiValueMap<String, String> paramsMap = new LinkedMultiValueMap<>();
        paramsMap.add("title", title);

        when(tutorialRepository.findByTitleContaining(title)).thenReturn(tutorials);
        mockMvc.perform(get("/api/tutorials").params(paramsMap))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(tutorials.size()))
                .andDo(print());

        tutorials = Collections.emptyList();

        when(tutorialRepository.findByTitleContaining(title)).thenReturn(tutorials);
        mockMvc.perform(get("/api/tutorials").params(paramsMap))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    public void shouldReturnNoContentWhenFilter() throws Exception {
        String title = "BuildTechKnowledge";
        MultiValueMap<String, String> paramsMap = new LinkedMultiValueMap<>();
        paramsMap.add("title", title);

        List<Tutorial> tutorials = Collections.emptyList();

        when(tutorialRepository.findByTitleContaining(title)).thenReturn(tutorials);
        mockMvc.perform(get("/api/tutorials").params(paramsMap))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    public void shouldUpdateTutorial() throws Exception {
//        long id = 1L;
        String id = "1";
        Tutorial tutorial = new Tutorial(id, "Spring Boot @WebMvcTest", "Description", false);
        Tutorial updatedtutorial = new Tutorial(id, "Updated", "Updated", true);

        when(tutorialRepository.findById(id)).thenReturn(Optional.of(tutorial));
        when(tutorialRepository.save(any(Tutorial.class))).thenReturn(updatedtutorial);

        mockMvc.perform(put("/api/tutorials/{id}", id).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedtutorial)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(updatedtutorial.getTitle()))
                .andExpect(jsonPath("$.description").value(updatedtutorial.getDescription()))
                .andExpect(jsonPath("$.published").value(updatedtutorial.isPublished()))
                .andDo(print());
    }

    @Test
    public void shouldReturnNotFoundUpdateTutorial() throws Exception {
        //long id = 1L;
        String id = "dfdf";

        Tutorial updatedtutorial = new Tutorial(id, "Updated", "Updated", true);

        when(tutorialRepository.findById(id)).thenReturn(Optional.empty());
        when(tutorialRepository.save(any(Tutorial.class))).thenReturn(updatedtutorial);

        mockMvc.perform(put("/api/tutorials/{id}", id).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedtutorial)))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void shouldDeleteTutorial() throws Exception {
//        long id = 1L;
        String id = "dfdf";
        doNothing().when(tutorialRepository).deleteById(id);
        mockMvc.perform(delete("/api/tutorials/{id}", id))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    public void shouldDeleteAllTutorials() throws Exception {
        doNothing().when(tutorialRepository).deleteAll();
        mockMvc.perform(delete("/api/tutorials"))
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}