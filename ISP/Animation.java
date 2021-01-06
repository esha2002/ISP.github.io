/*
  Esha Mahabir
  Ms.Krasteva
  Animation.java
  12/12/2017

*/
import java.awt.*;
import hsa.Console;
import java.lang.*;     // to access Thread class

public class Animation implements Runnable
{
    private Console c;

    private void fillOval (int xCenter, int yCenter, int xDiameter, int yDiameter)
    {
	xCenter = xCenter - (xDiameter / 2);
	yCenter = yCenter - (yDiameter / 2);
	c.fillOval (xCenter, yCenter, xDiameter, yDiameter);
    }


    private void drawArc (int xCenter, int yCenter, int xDiameter, int yDiameter, int startAngle, int angleSize)
    {
	xCenter = xCenter - (xDiameter / 2);
	yCenter = yCenter - (yDiameter / 2);
	c.drawArc (xCenter, yCenter, xDiameter, yDiameter, startAngle, angleSize);
    }


    private void fillArc (int xCenter, int yCenter, int xDiameter, int yDiameter, int startAngle, int angleSize)
    {
	xCenter = xCenter - (xDiameter / 2);
	yCenter = yCenter - (yDiameter / 2);
	c.fillArc (xCenter, yCenter, xDiameter, yDiameter, startAngle, angleSize);
    }


    public void animation ()
    {
	Color black = new Color (0, 0, 0);
	Color purple = new Color (128, 128, 255);
	Color yellow = new Color (239, 235, 69);
	Color skyBlue = new Color (135, 206, 250);
	Color sandBrown = new Color (237, 200, 85);
	Color lizardGreen = new Color (0, 191, 0);
	Color forestGreen = new Color (0, 91, 0);

	for (int x = 0 ; x < 1160 ; x++)
	{
	    //erase
	    c.setColor (Color.white);
	    c.fillRect (-580 + x, 430, 582, 70);

	    //head
	    c.setColor (lizardGreen);

	    fillOval (-13 + x, 450, 40, 13);
	    //tail
	    fillOval (-510 + x, 450, 20, 5);

	    //body
	    fillArc (-265 + x, 450, 170, 40, 0, 180);
	    c.setColor (Color.white);
	    fillArc (-265 + x, 450, 150, 20, 0, 180);

	    c.setColor (lizardGreen);
	    fillArc (-105 + x, 450, 170, 40, 180, 180);
	    c.setColor (Color.white);
	    fillArc (-105 + x, 450, 150, 20, 180, 180);

	    c.setColor (lizardGreen);
	    fillArc (-425 + x, 450, 170, 40, 180, 180);
	    c.setColor (Color.white);
	    fillArc (-425 + x, 450, 150, 20, 180, 180);


	    c.setColor (black);
	    //eye
	    fillOval (-5 + x, 450, 5, 5);

	    //teeth
	    c.setColor (Color.white);
	    int[] tooth1X = { - 140 + x, -138 + x, -136 + x};
	    int[] tooth1Y = {455, 460, 455};
	    c.fillPolygon (tooth1X, tooth1Y, 3);
	    int[] tooth2X = { - 134 + x, -132 + x, -130 + x};
	    int[] tooth2Y = {455, 460, 455};
	    c.fillPolygon (tooth2X, tooth2Y, 3);
	    try
	    {
		Thread.sleep (8);
	    }
	    catch (Exception e)
	    {
	    }
	}
    }


    public Animation (Console con)
    {
	c = con;
    }


    public void run ()
    {
	animation ();
    }
}
