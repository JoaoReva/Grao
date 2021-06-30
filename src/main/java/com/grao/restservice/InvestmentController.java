package com.grao.restservice;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.grao.data.request.PostInvestmentReturnSimulationRequestDTO;
import com.grao.data.response.PostInvestmentReturnSimulationResponseDTO;
import com.grao.business.investment.Investment;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;

/**
 * Controller for investment
 */
@RestController
public class InvestmentController extends BasicController {
		
	/**
	 * Api /Investment/Simulation
	 * @param PostInvestmentReturnSimulationRequestDTO: params where especify initialDate, endDate and initialValue
	 * @return PostInvestmentReturnSimulationResponseDTO: 
	 *         		totalValueOnFinalDate: initialValue + yield
	 *         		SimulationParms: paramsUsedInSimulation
	 */
	@PostMapping(value = "/Investment/Simulation")
	public PostInvestmentReturnSimulationResponseDTO InvestmentSimulation(@Valid @RequestBody PostInvestmentReturnSimulationRequestDTO params) {		
		return new PostInvestmentReturnSimulationResponseDTO(new Investment().SimulateValue(params));
	}
}