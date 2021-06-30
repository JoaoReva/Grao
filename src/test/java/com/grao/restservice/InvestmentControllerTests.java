package com.grao.restservice;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import com.grao.data.request.PostInvestmentReturnSimulationRequestDTO;
import java.time.LocalDate;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class InvestmentControllerTests {
	
	@Autowired
	private InvestmentController controller;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void contextLoads() {
		assertThat(controller).isNotNull();
	}
	
	@Test
	public void InvestmentSimulationErrorParamsEmpty()  throws Exception {		
		this.mockMvc.perform(post("/Investment/Simulation")
							  .contentType(MediaType.APPLICATION_JSON)
					          .content("{}"))
		.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$", hasSize(2)))
		.andExpect(jsonPath("$.[*].fieldName",containsInAnyOrder("endDate","initialValue")));
	}
	
	@Test
	public void InvestmentSimulationErrorParamsOnlyInitialValue() throws Exception {		
		this.mockMvc.perform(post("/Investment/Simulation")
							  .contentType(MediaType.APPLICATION_JSON)
					          .content("{\"startDate\": null,\"endDate\": null,\"initialValue\":100.0}"))
		.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$", hasSize(1)))
		.andExpect(jsonPath("$.[0].fieldName",is("endDate")));
	}

	@Test
	public void InvestmentSimulationErrorParamsOnlyStartDate() throws Exception {		
		this.mockMvc.perform(post("/Investment/Simulation")
							  .contentType(MediaType.APPLICATION_JSON)
					          .content("{\"startDate\": \"2021-06-29\",\"endDate\": null,\"initialValue\": null}"))
		.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$", hasSize(2)))
		.andExpect(jsonPath("$.[*].fieldName",containsInAnyOrder("endDate","initialValue")));
	}
	
	@Test
	public void InvestmentSimulationErrorParamsOnlyEndDate() throws Exception {
		this.mockMvc.perform(post("/Investment/Simulation")
							  .contentType(MediaType.APPLICATION_JSON)
					          .content("{\"startDate\": null,\"endDate\": \"2021-06-29\",\"initialValue\": null}"))
		.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$", hasSize(1)))
		.andExpect(jsonPath("$.[0].fieldName",is("initialValue")));
	}
	
	@Test
	public void InvestmentSimulationErrorParamsOnlyStartDateAndEndDate() throws Exception {
		this.mockMvc.perform(post("/Investment/Simulation")
							  .contentType(MediaType.APPLICATION_JSON)
					          .content("{\"startDate\": \"2021-06-29\",\"endDate\": \"2021-06-29\",\"initialValue\": null}"))
		.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$", hasSize(1)))
		.andExpect(jsonPath("$.[0].fieldName",is("initialValue")));
	}
	
	@Test
	public void InvestmentSimulationErrorParamsOnlyStartDateAndInitialValue() throws Exception {		
		this.mockMvc.perform(post("/Investment/Simulation")
							  .contentType(MediaType.APPLICATION_JSON)
					          .content("{\"startDate\": \"2021-06-29\",\"endDate\": null,\"initialValue\": 100.0}"))
		.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$", hasSize(1)))
		.andExpect(jsonPath("$.[0].fieldName",is("endDate")));
	}
	
	@Test
	public void InvestmentSimulationErrorParamsStartDateLessThanEndDate() throws Exception {
		this.mockMvc.perform(post("/Investment/Simulation")
							  .contentType(MediaType.APPLICATION_JSON)
					          .content("{\"startDate\": \"2021-06-29\",\"endDate\": \"2021-01-01\",\"initialValue\": 100.0}"))
		.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.messageError", containsString("Data final nao pode ser menor ou igual que a data inicial")));
	}
	
	@Test
	public void InvestmentSimulationErrorParamsStartDateEqualsThanEndDate() throws Exception {
		this.mockMvc.perform(post("/Investment/Simulation")
							  .contentType(MediaType.APPLICATION_JSON)
					          .content("{\"startDate\": \"2021-06-29\",\"endDate\": \"2021-06-29\",\"initialValue\": 100.0}"))
		.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.messageError", containsString("Data final nao pode ser menor ou igual que a data inicial")));
	}
	
	@Test
	public void InvestmentSimulationErrorParamsInitialValueLessThanZero() throws Exception {
		this.mockMvc.perform(post("/Investment/Simulation")
							  .contentType(MediaType.APPLICATION_JSON)
					          .content("{\"startDate\": \"2021-06-29\",\"endDate\": \"2021-12-29\",\"initialValue\": -100.0}"))
		.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$", hasSize(1)))
		.andExpect(jsonPath("$.[0].fieldName",is("initialValue")));
	}
	
	@Test
	public void InvestmentSimulationSucessParamsOnlyEndDateAndInitialValue() throws Exception {
		this.mockMvc.perform(post("/Investment/Simulation")
							  .contentType(MediaType.APPLICATION_JSON)
					          .content("{\"startDate\": null,\"endDate\": \"9999-12-31\",\"initialValue\": 100.0}"))
		.andDo(print())
		.andExpect(status().isOk());
	}
	
	@Test
	public void InvestmentSimulationSucessParams() throws Exception {
		this.mockMvc.perform(post("/Investment/Simulation")
							  .contentType(MediaType.APPLICATION_JSON)
					          .content("{\"startDate\": \"2021-06-29\",\"endDate\": \"2021-12-29\",\"initialValue\": 100.0}"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.totalValueOnFinalDate",is(102.18)));
	}
}
