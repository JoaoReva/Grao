package com.grao.business.investment;

import java.time.LocalDate;

/**
 * Interface that represent any investment
 */
public interface InvestmentInterface {
	
	/**
	 * Get interest rate yearly
	 */
	double GetInterestRate();
	
	/**
	 * Calculate the final value if you invest initialValue from today until finalDate
	 * @param initialValue: initial value invested
	 * @param finalDate: final date of investment
	 * @return initialValue + yield
	 */
	double GetSimulFinalValue(double initialValue,LocalDate finalDate);
	
	/**
	 * Calculate the final value if you invest initialValue from initialDate until finalDate
	 * @param initialValue: initial value invested
	 * @param finalDate: final date of investment
	 * @param initialDate: initial date of investment
	 * @return initialValue + yield
	 */
	double GetSimulFinalValue(double initialValue,LocalDate finalDate, LocalDate initialDate);
	
	/**
	 * Calculate the final value if you invest initialValue from initialDate until finalDate
	 * @param initialValue: initial value invested
	 * @param timeInBusinessDay: number of business days between initial date and final date
	 * @return initialValue + yield
	 */
	double GetSimulFinalValue(double initialValue,double timeInBusinessDay);
}
