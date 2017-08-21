package trade.service;

import java.time.LocalDate;
import java.util.Currency;
import java.util.Set;

import trade.model.Entity;
import trade.workingdays.ArabiaWorkingDays;
import trade.workingdays.DefaultWorkingDays;
import trade.workingdays.IWorkingDays;


/**
 * A settlement date calculator
 */
public class SettlementDateCalculator
{
	public static void calculateSettlementDates(Set<Entity> amountOfTrades)
	{
		amountOfTrades.forEach(SettlementDateCalculator::calculateSettlementDate);
	}

	public static void calculateSettlementDate(Entity amountOfTrade)
	{
		// Select proper strategy based on the Currency
		final IWorkingDays workingDaysMechanism = getWorkingDaysStrategy(amountOfTrade.getAmountOfTrade().getCurrency());

		// find the correct settlement date
		final LocalDate newSettlementDate = workingDaysMechanism.findFirstWorkingDate(amountOfTrade.getSettlementDate());

		if (newSettlementDate != null)
		{
			// set the correct settlement date
			amountOfTrade.setSettlementDate(newSettlementDate);
		}
	}

	private static IWorkingDays getWorkingDaysStrategy(Currency currency)
	{
		if ((currency.getCurrencyCode().equals("AED")) || (currency.getCurrencyCode().equals("SAR")))
		{
			return ArabiaWorkingDays.getInstance();
		}
		return DefaultWorkingDays.getInstance();
	}
}
