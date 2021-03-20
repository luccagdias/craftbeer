package com.beerhouse;

import com.beerhouse.controller.BeerController;
import com.beerhouse.model.Beer;
import com.beerhouse.service.BeerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
public class BeerControllerTest {

    @Autowired
    private BeerController beerController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BeerService beerService;

    @Test
    public void testGetBeer() throws Exception {
        Beer beerMock = new Beer(1L, "Name", "Ingredients", "Alcohol Content", 0.0, "Category");

        when(beerService.findById(anyLong())).thenReturn(beerMock);

        mockMvc.perform(get("/beers/1"))
                .andExpect(jsonPath("$.name").value("Name"))
                .andExpect(jsonPath("$.ingredients").value("Ingredients"))
                .andExpect(jsonPath("$.alcoholContent").value("Alcohol Content"))
                .andExpect(jsonPath("$.price").value(0.0))
                .andExpect(jsonPath("$.category").value("Category"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllBeers() throws Exception {
        List<Beer> beerList = Arrays.asList(new Beer(1L, "Name", "Ingredients", "Alcohol Content", 0.0, "Category"));

        when(beerService.findAll()).thenReturn(beerList);

        mockMvc.perform(get("/beers"))
                .andExpect(jsonPath("$[0].name").value("Name"))
                .andExpect(jsonPath("$[0].ingredients").value("Ingredients"))
                .andExpect(jsonPath("$[0].alcoholContent").value("Alcohol Content"))
                .andExpect(jsonPath("$[0].price").value(0.0))
                .andExpect(jsonPath("$[0].category").value("Category"))
                .andExpect(status().isOk());
    }


    @Test
    public void testInsertBeer() throws Exception {
        Beer beerMock = new Beer(1L, "Beer Name", "Beer Ingredients", "Alcohol Content", 0.0, "Category");

        when(beerService.insert(any(Beer.class))).thenReturn(beerMock);

        mockMvc.perform(post("/beers")
                .content(new ObjectMapper().writeValueAsString(beerMock))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.location").value("/beers/1"));
    }

    @Test
    public void testAlterBeer() throws Exception {
        Beer beerMock = new Beer(1L, "Beer Name", "Beer Ingredients", "Alcohol Content", 0.0, "Category");

        when(beerService.update(any(Beer.class))).thenReturn(beerMock);

        mockMvc.perform(put("/beers/1")
                .content(new ObjectMapper().writeValueAsString(beerMock))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteBeer() throws Exception {
        doNothing().when(beerService).delete(anyLong());

        mockMvc.perform(delete("/beers/1"))
                .andExpect(status().isNoContent());
    }
}
