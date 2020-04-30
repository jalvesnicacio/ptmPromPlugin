package ca.uqac.lif.cep.xes;

import org.joda.time.DateTime;

import ca.uqac.lif.cep.functions.UnaryFunction;

/**
 * Parses a {@link DateTime} object from a string.
 */
public class ToDateTime extends UnaryFunction<String,DateTime>
{
	/**
	 * A single visible instance of the function.
	 */
	public static final transient ToDateTime instance = new ToDateTime();
	
	/**
	 * Creates a new instance of the function
	 */
	protected ToDateTime()
	{
		super(String.class, DateTime.class);
	}
	
	@Override
	public DateTime getValue(String v)
	{
		return new DateTime(v);
	}
	
	@Override
	public ToDateTime duplicate(boolean with_state)
	{
		return this;
	}
}
