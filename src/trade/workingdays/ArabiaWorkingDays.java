package trade.workingdays;

import java.time.DayOfWeek;


public class ArabiaWorkingDays extends WorkingDays
{

	private static ArabiaWorkingDays instance = null;

	private ArabiaWorkingDays()
	{
		super();
	}

	public static ArabiaWorkingDays getInstance()
	{
		if (instance == null)
		{
			instance = new ArabiaWorkingDays();
		}
		return instance;
	}

	@Override
	protected void setupWorkingDays()
	{
		this.isWorkingDayMap.put(DayOfWeek.SUNDAY, true);
		this.isWorkingDayMap.put(DayOfWeek.MONDAY, true);
		this.isWorkingDayMap.put(DayOfWeek.TUESDAY, true);
		this.isWorkingDayMap.put(DayOfWeek.WEDNESDAY, true);
		this.isWorkingDayMap.put(DayOfWeek.THURSDAY, true);
		this.isWorkingDayMap.put(DayOfWeek.FRIDAY, false);
		this.isWorkingDayMap.put(DayOfWeek.SATURDAY, false);
	}
}
