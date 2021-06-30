package com.grao.data.response;

import com.grao.data.request.PostInvestmentReturnSimulationRequestDTO;

public class PostInvestmentReturnSimulationResponseDTO{
	
	private PostInvestmentReturnSimulationRequestDTO simulationParms;
	private double totalValueOnFinalDate;
	
	public PostInvestmentReturnSimulationResponseDTO(double totalValueOnFinalDate,PostInvestmentReturnSimulationRequestDTO simulationParms) {
		this.totalValueOnFinalDate = totalValueOnFinalDate;
		this.simulationParms = simulationParms;
	}

	public double getTotalValueOnFinalDate() {
		return totalValueOnFinalDate;
	}
	
	public PostInvestmentReturnSimulationRequestDTO getSimulationParms() {
		return simulationParms;
	}
}