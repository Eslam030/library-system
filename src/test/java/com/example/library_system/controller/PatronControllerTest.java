package com.example.library_system.controller;


import com.example.library_system.model.Patron;
import com.example.library_system.service.PatronService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(PatronController.class)
public class PatronControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatronService patronsService;

    @Test
    public void testGetAllPatrons() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/patrons")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void testGetPatronById() throws Exception {
        Long PatronId = 1L;
        Patron Patron = new Patron();
        Patron.setPatronId(PatronId);
        when(patronsService.getPatron(PatronId)).thenReturn(Patron);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/patrons/{id}", PatronId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("patron.patronId").value(PatronId));

        when(patronsService.getPatron(PatronId)).thenThrow() ;
        mockMvc.perform(MockMvcRequestBuilders.get("/api/patrons/{id}", PatronId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()) ;
    }

    @Test
    public void testAddPatron() throws Exception {
        Patron patron = new Patron( ) ;
        patron.setPatronId(1L);
        when(patronsService.save(patron)).thenThrow() ;
        mockMvc.perform(post("/api/patrons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\" : \"Eslam Sayed\" , \"contactInformation\" : \"01157228162\"}"))
                .andExpect(status().isBadRequest());

        when(patronsService.save(patron)).thenReturn(patron) ;
        mockMvc.perform(post("/api/patrons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isCreated());

    }

    @Test
    public void testUpdatePatron() throws Exception {
        Long PatronId = 1L;
        Patron Patron = new Patron();
        Patron.setPatronId(PatronId);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/patrons/{id}", PatronId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"id\": 1 }"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Updated Successfully"));
    }

    @Test
    public void testDeletePatron() throws Exception {
        Long PatronId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/patrons/{id}", PatronId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Deleted Successfully"));
    }
}