import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;

import trade.model.AmountOfTrade;
import trade.model.Entity;
import trade.model.TradeAction;


public class SampleData
{

	public static Set<Entity> getSampleInstructions()
	{
		return new HashSet<>(Arrays.asList(

				new Entity(1, "foo", TradeAction.BUY, LocalDate.of(2017, 8, 10), LocalDate.of(2017, 8, 20),
						new AmountOfTrade(Currency.getInstance("SGD"), BigDecimal.valueOf(0.50), 200, BigDecimal.valueOf(100))),

				new Entity(2, "bar", TradeAction.BUY, LocalDate.of(2017, 8, 10), LocalDate.of(2017, 8, 19),
						new AmountOfTrade(Currency.getInstance("AED"), BigDecimal.valueOf(0.10), 400, BigDecimal.valueOf(200))),

				new Entity(3, "foo1", TradeAction.SELL, LocalDate.of(2017, 8, 10), LocalDate.of(2017, 8, 18),
						new AmountOfTrade(Currency.getInstance("SAR"), BigDecimal.valueOf(0.50), 150, BigDecimal.valueOf(400))),

				new Entity(4, "foo2", TradeAction.SELL, LocalDate.of(2017, 8, 10), LocalDate.of(2017, 8, 21),
						new AmountOfTrade(Currency.getInstance("GBP"), BigDecimal.valueOf(0.75), 50, BigDecimal.valueOf(500))),

				new Entity(5, "foo3", TradeAction.BUY, LocalDate.of(2017, 8, 10), LocalDate.of(2017, 8, 21),
						new AmountOfTrade(Currency.getInstance("GBP"), BigDecimal.valueOf(0.50), 20, BigDecimal.valueOf(40))),

				new Entity(6, "foo4", TradeAction.BUY, LocalDate.of(2017, 8, 10), LocalDate.of(2017, 8, 21),
						new AmountOfTrade(Currency.getInstance("EUR"), BigDecimal.valueOf(0.10), 20, BigDecimal.valueOf(40))),

				new Entity(7, "foo5", TradeAction.SELL, LocalDate.of(2017, 8, 10), LocalDate.of(2017, 8, 21),
						new AmountOfTrade(Currency.getInstance("EUR"), BigDecimal.valueOf(0.25), 1000, BigDecimal.valueOf(40))),

				new Entity(8, "bar1", TradeAction.SELL, LocalDate.of(2017, 3, 10), LocalDate.of(2017, 3, 21),
						new AmountOfTrade(Currency.getInstance("GBP"), BigDecimal.valueOf(0.20), 100, BigDecimal.valueOf(500)))));
	}

}
