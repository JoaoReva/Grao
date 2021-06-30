package com.grao.business.date;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class TimeSpan {
	
	private LocalDate initialDate;
	private LocalDate finalDate;
	
	public TimeSpan(LocalDate initialDate, LocalDate finalDate){
		this.initialDate = initialDate;
		this.finalDate = finalDate;
	}
	
	/**
	 * Get the number of business day between two dates
	 * @return number of business day between two dates
	 */
	public double GetBusinessDaysSpan(){
		int startDayOfWeek = initialDate.getDayOfWeek().getValue();
	    int endDayOfWeek = finalDate.getDayOfWeek().getValue();

	    double numberOfDaysBetween = ChronoUnit.DAYS.between(initialDate, finalDate);
	    return numberOfDaysBetween - GetNumberOfWeekendsDays(numberOfDaysBetween, startDayOfWeek, endDayOfWeek);
	}
	
	/**
	 * Get the number of weekends between two dates
	 * @return number of weekends between two dates
	 */
	private double GetNumberOfWeekendsDays(double numberOfDaysBetween, int startDayOfWeek, int endDayOfWeek){
		double numberOfWholeWeekendsDays = 2 * GetNumberOfWeeks(numberOfDaysBetween);
		double incompleteWeekDays = 0;
		
		//If has incomplete week
		if (numberOfDaysBetween % 7 != 0) {
	        if (startDayOfWeek == 7) { // If start at sunday remove that day
	        	incompleteWeekDays = 1;
	        } else if (endDayOfWeek == 7) {  // If end at sunday remove that day
	        	incompleteWeekDays = 1;
	        } else if (endDayOfWeek < startDayOfWeek) { //if end day of week is greater of start day remove the whole weekend
	        	incompleteWeekDays = 2;
	        }
	    }
		
		//Numer of weekends minus weekends day of incomplete weeks
		return numberOfWholeWeekendsDays - incompleteWeekDays;
	}
	
	/**
	 * Get the number of weeks from the number of days
	 * @param numberOfDaysBetween
	 * @return Number of weeks from the number of days
	 */
	private double GetNumberOfWeeks(double numberOfDaysBetween){
		return (numberOfDaysBetween / 7);
	}
}
