package com.grao.data.response;

import java.math.BigDecimal;
import com.grao.data.request.PostInvestmentReturnSimulationRequestDTO;

public class PostInvestmentReturnSimulationResponseDTO{
	
	private PostInvestmentReturnSimulationRequestDTO simulationParms;
	private BigDecimal totalValueOnFinalDate;
	
	public PostInvestmentReturnSimulationResponseDTO(double totalValueOnFinalDate,PostInvestmentReturnSimulationRequestDTO simulationParms) {
		this.totalValueOnFinalDate = new BigDecimal(totalValueOnFinalDate).setScale(2, BigDecimal.ROUND_HALF_UP);
		this.simulationParms = simulationParms;
	}

	public BigDecimal getTotalValueOnFinalDate() {
		return totalValueOnFinalDate;
	}
	
	public PostInvestmentReturnSimulationRequestDTO getSimulationParms() {
		return simulationParms;
	}
}