package com.grao.data.request;

import java.time.LocalDate;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class PostInvestmentReturnSimulationRequestDTO{
	
	private LocalDate startDate;
	
	@NotNull
	private LocalDate endDate;
	
	@NotNull
	@Min(1)
	private double initialValue;
	
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	
	public LocalDate getStartDate() {
		return startDate;
	}
	
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	public LocalDate getEndDate() {
		return endDate;
	}
	
	public void setInitialValue(double initialValue) {
		this.initialValue = initialValue;
	}
	
	public double getInitialValue() {
		return initialValue;
	}
}