package com.grao.restservice;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.grao.data.request.PostInvestmentReturnSimulationRequestDTO;
import com.grao.data.response.PostInvestmentReturnSimulationResponseDTO;
import com.grao.business.investment.Investment;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import com.grao.exception.ErrorException;
import java.time.LocalDate;

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
	public PostInvestmentReturnSimulationResponseDTO InvestmentSimulation(@Valid @RequestBody PostInvestmentReturnSimulationRequestDTO params) throws ErrorException{	
		ValidatePostInvestmentReturnSimulationRequestDTO(params);
		return new PostInvestmentReturnSimulationResponseDTO(new Investment().SimulateValue(params));
	}
	
	private void ValidatePostInvestmentReturnSimulationRequestDTO(PostInvestmentReturnSimulationRequestDTO param) throws ErrorException{
		LocalDate startDate = param.getStartDate() == null ? LocalDate.now() : param.getStartDate();
		
		if(param.getEndDate().isBefore(startDate)
			|| param.getEndDate().isEqual(startDate)){
			throw new ErrorException("Data final nao pode ser menor ou igual que a data inicial. Valores: StartDate: " + param.getStartDate() + " EndDate: " + param.getEndDate());
		}
	}
}