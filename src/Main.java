import java.util.Set;

import trade.report.generator.IReportGenerator;
import trade.report.generator.ReportGenerator;
import trade.model.Entity;


public class Main
{

	public static void main(String[] args)
	{
		final Set<Entity> instructions = SampleData.getSampleInstructions();
		final IReportGenerator reportGenerator = new ReportGenerator();

		System.out.println(reportGenerator.generateInstructionsReport(instructions));
	}
}
