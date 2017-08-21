package trade.workingdays;

import java.time.DayOfWeek;


public class DefaultWorkingDays extends WorkingDays
{

	private static DefaultWorkingDays instance = null;

	private DefaultWorkingDays()
	{
		super();
	}

	public static DefaultWorkingDays getInstance()
	{
		if (instance == null)
		{
			instance = new DefaultWorkingDays();
		}
		return instance;
	}

	@Override
	protected void setupWorkingDays()
	{
		this.isWorkingDayMap.put(DayOfWeek.MONDAY, true);
		this.isWorkingDayMap.put(DayOfWeek.TUESDAY, true);
		this.isWorkingDayMap.put(DayOfWeek.WEDNESDAY, true);
		this.isWorkingDayMap.put(DayOfWeek.THURSDAY, true);
		this.isWorkingDayMap.put(DayOfWeek.FRIDAY, true);
		this.isWorkingDayMap.put(DayOfWeek.SATURDAY, false);
		this.isWorkingDayMap.put(DayOfWeek.SUNDAY, false);
	}
}
