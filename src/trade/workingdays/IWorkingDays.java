package trade.workingdays;

import java.time.LocalDate;


public interface IWorkingDays
{
	LocalDate findFirstWorkingDate(LocalDate date);
}
