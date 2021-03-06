package eu.ha3.mc.convenience;

/*
            DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE 
                    Version 2, December 2004 

 Copyright (C) 2004 Sam Hocevar <sam@hocevar.net> 

 Everyone is permitted to copy and distribute verbatim or modified 
 copies of this license document, and changing it is allowed as long 
 as the name is changed. 

            DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE 
   TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION 

  0. You just DO WHAT THE FUCK YOU WANT TO. 
*/

public class Ha3StaticUtilities
{
	/**
	 * Checks if a certain class name exists in a certain object context's class
	 * loader.
	 * 
	 * @param className
	 * @param context
	 * @return
	 */
	public static boolean classExists(String className, Object context)
	{
		boolean canWork = false;
		try
		{
			canWork = Class.forName(className, false, context.getClass().getClassLoader()) != null;
			
		}
		//catch (ClassNotFoundException e)
		//{
		//}
		catch (Exception e)
		{
			// Normally throws checked ClassNotFoundException
			// This also throws unckecked security exceptions
		}
		
		return canWork;
		
	}
	
}
