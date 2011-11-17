/**
 * MenuItem.java
 *
 * SENG 301: Assignment 4
 * Franky Cheung; Colin Williams
 */

package Menus;

class MenuItem
{
	String text;
	Menu menu;

	public MenuItem( String t )
	{
		text = t;
	}
	
	public MenuItem( String t, Menu m )
	{
		text = t;
		menu = m;
	}

	public void run() {}

	public String toString()
	{
		return text;
	}
}
