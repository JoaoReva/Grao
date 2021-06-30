package com.grao.business.investment;

import com.grao.data.request.PostInvestmentReturnSimulationRequestDTO;

public class Investment{
	
	private final double interestRate = 4.25;
	
	/**
	 * Calculate the final value if you invest initialValue from param
	 * @param PostInvestmentReturnSimulationRequestDTO: params where especify initialDate, endDate and initialValue
	 * @return initialValue + yield
	 */
	public double SimulateValue(PostInvestmentReturnSimulationRequestDTO param){
		InvestmentInterface investment = new GovernmentBonds(interestRate);
		
		if(param.getStartDate() != null){
			return investment.GetSimulFinalValue(param.getInitialValue(),param.getEndDate(),param.getStartDate());
		}else{
			return investment.GetSimulFinalValue(param.getInitialValue(),param.getEndDate());
		}
	}
	
}
