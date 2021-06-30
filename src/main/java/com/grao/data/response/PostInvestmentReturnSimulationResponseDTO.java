package com.grao.data.response;

import java.math.BigDecimal;
import com.grao.data.request.PostInvestmentReturnSimulationRequestDTO;

public class PostInvestmentReturnSimulationResponseDTO{
	
	private BigDecimal totalValueOnFinalDate;
	
	public PostInvestmentReturnSimulationResponseDTO(double totalValueOnFinalDate) {
		this.totalValueOnFinalDate = new BigDecimal(totalValueOnFinalDate).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public BigDecimal getTotalValueOnFinalDate() {
		return totalValueOnFinalDate;
	}
}