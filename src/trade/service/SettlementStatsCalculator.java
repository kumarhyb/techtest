package trade.service;

import trade.model.Entity;
import trade.model.TradeAction;
import trade.report.ranking.Rank;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

import static java.util.stream.Collectors.*;


/**
 * Describes a mapping between dates and the trade amount of those dates, based on instructions
 */
public class SettlementStatsCalculator
{

	// Create a predicate for outgoing
	private final static Predicate<Entity> outgoingInstructionsPredicate = instruction -> instruction.getAction().equals(TradeAction.BUY);

	// Create a predicate for incoming
	private final static Predicate<Entity> incomingInstructionsPredicate = instruction -> instruction.getAction().equals(TradeAction.SELL);

	/**
	 * Calculates the daily outgoing (BUY) trade amount in USD
	 *
	 * @param instructions the instruction to calculate the stats from
	 * @return a map from date to total amount
	 */
	public static Map<LocalDate, BigDecimal> calculateDailyOutgoingAmount(Set<Entity> instructions)
	{
		return calculateDailyAmount(instructions, outgoingInstructionsPredicate);
	}

	/**
	 * Calculates the daily incoming (SELL) trade amount in USD
	 *
	 * @param instructions the instruction to calculate the stats from
	 * @return a map from date to total amount
	 */
	public static Map<LocalDate, BigDecimal> calculateDailyIncomingAmount(Set<Entity> instructions)
	{
		return calculateDailyAmount(instructions, incomingInstructionsPredicate);
	}

	/**
	 * Ranks the daily outgoing (BUY) by trade amount in USD
	 *
	 * @param instructions the instruction to calculate the stats from
	 * @return a map from date to a list of ranks (ranking)
	 */
	public static Map<LocalDate, LinkedList<Rank>> calculateDailyOutgoingRanking(Set<Entity> instructions)
	{
		return calculateRanking(instructions, outgoingInstructionsPredicate);
	}

	/**
	 * Ranks the daily incoming (SELL) by trade amount in USD
	 *
	 * @param instructions the instruction to calculate the stats from
	 * @return a map from date to a list of ranks (ranking)
	 */
	public static Map<LocalDate, LinkedList<Rank>> calculateDailyIncomingRanking(Set<Entity> instructions)
	{
		return calculateRanking(instructions, incomingInstructionsPredicate);
	}

	private static Map<LocalDate, BigDecimal> calculateDailyAmount(Set<Entity> instructions, Predicate<Entity> predicate)
	{
		return instructions.stream().filter(predicate)
				.collect(groupingBy(Entity::getSettlementDate, mapping(Entity::getTradeAmount, reducing(BigDecimal.ZERO, BigDecimal::add))));

	}

	private static Map<LocalDate, LinkedList<Rank>> calculateRanking(Set<Entity> instructions, Predicate<Entity> predicate)
	{
		final Map<LocalDate, LinkedList<Rank>> ranking = new HashMap<>();

		instructions.stream().filter(predicate).collect(groupingBy(Entity::getSettlementDate, toSet())).forEach((date, dailyInstructionSet) -> {
			final AtomicInteger rank = new AtomicInteger(1);

			final LinkedList<Rank> ranks = dailyInstructionSet.stream().sorted((a, b) -> b.getTradeAmount().compareTo(a.getTradeAmount()))
					.map(instruction -> new Rank(rank.getAndIncrement(), instruction.getEntityName(), date)).collect(toCollection(LinkedList::new));

			ranking.put(date, ranks);
		});

		return ranking;
	}
}
