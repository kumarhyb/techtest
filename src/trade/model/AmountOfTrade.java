package trade.model;

import java.math.BigDecimal;
import java.util.Currency;


public class AmountOfTrade
{

	private Currency currency;
	private BigDecimal agreedFx;
	private int units;
	private BigDecimal pricePerUnit;

	public AmountOfTrade(Currency currency, BigDecimal agreedFx, int units, BigDecimal pricePerUnit)
	{
		super();
		this.currency = currency;
		this.agreedFx = agreedFx;
		this.units = units;
		this.pricePerUnit = pricePerUnit;

	}

	private BigDecimal calculateAmount()
	{
		return getPricePerUnit().multiply(BigDecimal.valueOf(getUnits())).multiply(getAgreedFx());
	}

	public Currency getCurrency()
	{
		return currency;
	}

	public void setCurrency(Currency currency)
	{
		this.currency = currency;
	}

	public BigDecimal getAgreedFx()
	{
		return agreedFx;
	}

	public void setAgreedFx(BigDecimal agreedFx)
	{
		this.agreedFx = agreedFx;
	}

	public int getUnits()
	{
		return units;
	}

	public void setUnits(int units)
	{
		this.units = units;
	}

	public BigDecimal getPricePerUnit()
	{
		return pricePerUnit;
	}

	public void setPricePerUnit(BigDecimal pricePerUnit)
	{
		this.pricePerUnit = pricePerUnit;
	}

}
