package trade.report.generator;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import trade.model.Entity;
import trade.report.ranking.Rank;
import trade.service.SettlementDateCalculator;
import trade.service.SettlementStatsCalculator;


public class ReportGenerator implements IReportGenerator
{
	private StringBuilder stringBuilder = new StringBuilder();

	private static StringBuilder generateDailyOutgoingAmount(Set<Entity> instructions, StringBuilder stringBuilder)
	{
		final Map<LocalDate, BigDecimal> dailyOutgoingAmount = SettlementStatsCalculator.calculateDailyOutgoingAmount(instructions);

		stringBuilder.append("\n----------------------------------------\n").append("         Outgoing Daily Amount          \n")
				.append("----------------------------------------\n").append("      Date       |    Trade Amount      \n")
				.append("----------------------------------------\n");

		for (LocalDate date : dailyOutgoingAmount.keySet())
		{
			stringBuilder.append(date).append("       |      ").append(dailyOutgoingAmount.get(date)).append("\n");
		}

		return stringBuilder;
	}

	private static StringBuilder generateDailyIncomingAmount(Set<Entity> instructions, StringBuilder stringBuilder)
	{
		final Map<LocalDate, BigDecimal> dailyOutgoingAmount = SettlementStatsCalculator.calculateDailyIncomingAmount(instructions);

		stringBuilder.append("\n----------------------------------------\n").append("         Incoming Daily Amount          \n")
				.append("----------------------------------------\n").append("      Date       |    Trade Amount      \n")
				.append("----------------------------------------\n");

		for (LocalDate date : dailyOutgoingAmount.keySet())
		{
			stringBuilder.append(date).append("       |      ").append(dailyOutgoingAmount.get(date)).append("\n");
		}

		return stringBuilder;
	}

	private static StringBuilder generateDailyOutgoingRanking(Set<Entity> instructions, StringBuilder stringBuilder)
	{
		final Map<LocalDate, LinkedList<Rank>> dailyOutgoingRanking = SettlementStatsCalculator.calculateDailyOutgoingRanking(instructions);

		stringBuilder.append("\n----------------------------------------\n").append("         Outgoing Daily Ranking          \n")
				.append("----------------------------------------\n").append("     Date    |     Rank   |   Entity     \n")
				.append("----------------------------------------\n");

		for (LocalDate date : dailyOutgoingRanking.keySet())
		{
			for (Rank rank : dailyOutgoingRanking.get(date))
			{
				stringBuilder.append(date).append("   |      ").append(rank.getRank()).append("     |    ").append(rank.getEntity()).append("\n");
			}
		}

		return stringBuilder;
	}

	private static StringBuilder generateDailyIncomingRanking(Set<Entity> instructions, StringBuilder stringBuilder)
	{
		final Map<LocalDate, LinkedList<Rank>> dailyIncomingRanking = SettlementStatsCalculator.calculateDailyIncomingRanking(instructions);

		stringBuilder.append("\n----------------------------------------\n").append("         Incoming Daily Ranking          \n")
				.append("----------------------------------------\n").append("     Date    |     Rank   |   Entity     \n")
				.append("----------------------------------------\n");

		for (LocalDate date : dailyIncomingRanking.keySet())
		{
			for (Rank rank : dailyIncomingRanking.get(date))
			{
				stringBuilder.append(date).append("   |      ").append(rank.getRank()).append("     |    ").append(rank.getEntity()).append("\n");
			}
		}

		return stringBuilder;
	}

	@Override
	public String generateInstructionsReport(Set<Entity> instructions)
	{
		// first calculate the correct settlement dates
		SettlementDateCalculator.calculateSettlementDates(instructions);

		// Build the report string
		return generateDailyOutgoingRanking(instructions, generateDailyIncomingRanking(instructions,
				generateDailyIncomingAmount(instructions, generateDailyOutgoingAmount(instructions, stringBuilder)))).toString();
	}

}
