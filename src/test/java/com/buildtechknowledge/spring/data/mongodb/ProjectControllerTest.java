//package com.buildtechknowledge.spring.data.mongodb;
//
//
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.buildtechknowledge.spring.data.mongodb.controller.ProjectController;
//import com.buildtechknowledge.spring.data.mongodb.model.Project;
//import org.hamcrest.Matchers;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentMatchers;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//@SpringBootTest
//public class ProjectControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
////    @MockBean
////    private ProjectService projectService;
//
//    @MockBean
//    private ProjectController projectController;
//
//    private static ObjectMapper mapper = new ObjectMapper();
//
//    @Test
//    public void testGetExample() throws Exception {
//        List<Project> projects = new ArrayList<>();
//        Project project = new Project();
//        project.setProjectID("CanvasKnow1");
//        project.setProjectName("CanvasKnowledge");
//        project.setProjectDescription("Knowledge Fabric");
//        projects.add(project);
//        Mockito.when(projectController.getAllProjects()).thenReturn(projects);
//        mockMvc.perform(get("/projects")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)))
//                .andExpect(jsonPath("$[0].projectName", Matchers.equalTo("CanvasKnowledge")));
//    }
//
//    @Test
//    public void testPostExample() throws Exception {
//        Project project = new Project();
//        project.setProjectID("CanvasScout1");
//        project.setProjectName("CanvasScout");
//        project.setProjectDescription("ChatBot");
//
//        Mockito.when(projectController.createProjects(ArgumentMatchers.any())).thenReturn(new ResponseEntity<>(project , HttpStatus.CREATED));
//        String json = mapper.writeValueAsString(project);
//        mockMvc.perform(post("/projects").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
//                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
//                .andExpect(jsonPath("$.projectID", Matchers.equalTo("CanvasScout1")))
//                .andExpect(jsonPath("$.projectName", Matchers.equalTo("CanvasScout")));
//    }
//
//    @Test
//    public void testPutExample() throws Exception {
//        Project project = new Project();
//        project.setProjectID("CanvasScout1");
//        project.setProjectName("CanvasScout");
//        project.setProjectDescription("ChatBot");
//        Mockito.when(projectController.updateProject(project.getProjectID(),ArgumentMatchers.any())).thenReturn(new ResponseEntity<>(project , HttpStatus.CREATED));
//        String json = mapper.writeValueAsString(project);
//        mockMvc.perform(put("/putMapping").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
//                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", Matchers.equalTo(2)))
//                .andExpect(jsonPath("$.name", Matchers.equalTo("John")));
//    }
//
//    @Test
//    public void testDeleteExample() throws Exception {
//        Mockito.when(projectController.deleteProject(ArgumentMatchers.anyString())).thenReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT));
//        MvcResult requestResult = mockMvc.perform(delete("/deleteMapping").param("project-id", "1"))
//                .andExpect(status().isOk()).andExpect(status().isOk()).andReturn();
//        String result = requestResult.getResponse().getContentAsString();
//        assertEquals(result, "Project is deleted");
//    }
//}