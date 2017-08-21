package trade.report.generator;

import java.util.Set;

import trade.model.Entity;


public interface IReportGenerator
{
	String generateInstructionsReport(Set<Entity> amountOfTrades);
}
