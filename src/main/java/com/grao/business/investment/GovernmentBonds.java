package com.grao.business.investment;

import java.time.LocalDate;
import com.grao.business.date.TimeSpan;

public class GovernmentBonds implements InvestmentInterface{
	
	private final int bussinessDayInOneYear = 252;
	
	private double interestRate;
	
	/**
	 * 
	 * @param interestRate interest rate in % Ex: 4%, interestRate = 4
	 */
	public GovernmentBonds(double interestRate){
		this.interestRate = interestRate/100;
	}
	
	/**
	 * Calculate the final value if you invest initialValue from today until finalDate
	 * @param initialValue: initial value invested
	 * @param finalDate: final date of investment
	 * @return initialValue + yield
	 */
	public double GetSimulFinalValue(double initialValue,LocalDate finalDate){
		return GetSimulFinalValue(initialValue,finalDate,LocalDate.now());
	}
	
	/**
	 * Calculate the final value if you invest initialValue from initialDate until finalDate
	 * @param initialValue: initial value invested
	 * @param finalDate: final date of investment
	 * @param initialDate: initial date of investment
	 * @return initialValue + yield
	 */
	public double GetSimulFinalValue(double initialValue,LocalDate finalDate, LocalDate initialDate){
		return GetSimulFinalValue(initialValue, GetBusinessDays(initialDate,finalDate));
	}

	/**
	 * Calculate the final value if you invest initialValue from initialDate until finalDate
	 * @param initialValue: initial value invested
	 * @param timeInBusinessDay: number of business days between initial date and final date
	 * @return initialValue + yield
	 */
	public double GetSimulFinalValue(double initialValue,double timeInBusinessDay){
		return initialValue * GetCompoundInterest(timeInBusinessDay);
	}
	
	/**
	 * Get number of business days between initial date and final date
	 * @param initialDate
	 * @param finalDate
	 * @return number of business days between initial date and final date
	 */
	private double GetBusinessDays(LocalDate initialDate, LocalDate finalDate) {
		return new TimeSpan(initialDate,finalDate).GetBusinessDaysSpan();
	}
	
	/**
	 * Get total interest rate for the period
	 */
	private double GetCompoundInterest(double timeInBusinessDay){
		return Math.pow((1 + interestRate),timeInBusinessDay/bussinessDayInOneYear);
	}
	
	/**
	 * Get interest rate yearly
	 */
	public double GetInterestRate(){
		return interestRate;
	}
}
