package trade.model;

import java.math.BigDecimal;
import java.time.LocalDate;


public class Entity
{

	private int id;
	private String entityName;
	private TradeAction action;
	private LocalDate instructionDate;
	private LocalDate settlementDate;
	private AmountOfTrade amountOfTrade;
	private BigDecimal tradeAmount;


	public Entity(int id, String entityName, TradeAction action, LocalDate instructionDate, LocalDate settlementDate, AmountOfTrade amountOfTrade)
	{
		super();
		this.id = id;
		this.entityName = entityName;
		this.action = action;
		this.instructionDate = instructionDate;
		this.settlementDate = settlementDate;
		this.amountOfTrade = amountOfTrade;
		this.tradeAmount = calculateAmount(amountOfTrade);

	}


	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getEntityName()
	{
		return entityName;
	}

	public void setEntityName(String entityName)
	{
		this.entityName = entityName;
	}

	public TradeAction getAction()
	{
		return action;
	}

	public void setAction(TradeAction action)
	{
		this.action = action;
	}

	public LocalDate getInstructionDate()
	{
		return instructionDate;
	}

	public void setInstructionDate(LocalDate instructionDate)
	{
		this.instructionDate = instructionDate;
	}

	public LocalDate getSettlementDate()
	{
		return settlementDate;
	}

	public void setSettlementDate(LocalDate settlementDate)
	{
		this.settlementDate = settlementDate;
	}

	public AmountOfTrade getAmountOfTrade()
	{
		return amountOfTrade;
	}

	public void setAmountOfTrade(AmountOfTrade amountOfTrade)
	{
		this.amountOfTrade = amountOfTrade;
	}

	public BigDecimal getTradeAmount()
	{
		return tradeAmount;
	}

	public void setTradeAmount(BigDecimal tradeAmount)
	{
		this.tradeAmount = tradeAmount;
	}

	private BigDecimal calculateAmount(AmountOfTrade amountOfTrade)
	{
		return amountOfTrade.getPricePerUnit().multiply(BigDecimal.valueOf(amountOfTrade.getUnits())).multiply(amountOfTrade.getAgreedFx());
	}


}
