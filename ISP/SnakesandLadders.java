/* The "SnakesandLadders" class.
Esha Mahabir
Ms.Krasteva
01/10/2018
This program allows the user to play a game of snakes and ladders. There are two game levels and only two people can play.
 The user is able to view instruction for the game and save their scores in a file.  The file only stores the top 10 scores for each level.  T
 he user is also able to clear the file. The user can exit the game by pressing e and can choose to quit the program in main menu.
 A good bye screen will apear when they exit the program and a spalshscreen will apear at the begining of the program.

Variables

Name            Type                Description

 c              static              The output console
 TOTAL          static final int    constant variable that is assigned as 10
choice          int                  Saves the user input for main
player1Name     String              saves the first player's username
player2Name     String              saves the second player's username
difficulty      int                 saves the user input for difficulty level
numstr          String              used for errortraping
clearFile       int                 saves the user input for wheater they want to save the file
player1Moves    int                 saves the amount of moves player one makes
player2Moves    int                 saves the amount of moves player two makes
die1Num         int                 saves the first die number
die2Num         int                 saves the second die number
dieTotal        int                 saves the number of both dies added together
winCheck1       boolean             saves whether player 1 won or not
winCheck2       boolean             saves whether player 2 won or not

Arrays

Name            Type                Description

top10NamesEasy  String[]            Stores the top 10 players names for the easy level
top10ScoresEasy int[]               Stores the top 10 players scores for the easy level
top10NamesHard  String[]            Stores the top 10 players names for the hard level
top10ScoresHard int[]               Stores the top 10 players scores for the hard level
*/
import java.awt.image.*;
import java.awt.*;
import hsa.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class SnakesandLadders
{
    static Console c; // The output console
    static final int TOTAL = 10;
    int choice;
    String player1Name;
    String player2Name;
    int difficulty;
    String numstr;
    int clearFile;
    int player1Moves = 0;
    int player2Moves = 0;
    int die1Num;
    int die2Num;
    int dieTotal;
    boolean winCheck1;
    boolean winCheck2;
    String[] top10NamesEasy = {"", "", "", "", "", "", "", "", "", ""};
    int[] top10ScoresEasy = new int [TOTAL];
    String[] top10NamesHard = {"", "", "", "", "", "", "", "", "", ""};
    int[] top10ScoresHard = new int [TOTAL];
    String[] top10Levels;
    public SnakesandLadders ()
    {
	c = new Console ("Snakes and Ladders");
    }


    //this method calls the thread with an animation
    public void animation ()
    {
	Animation a = new Animation (c);
	a.run ();
    }


    //this method displays a brief description of the program with an animation.  It contains a thread that pauses the program then continues.
    public void splashScreen ()
    {
	drawTitle ();
	c.println ("This is a two player game of snakes and ladders.");
	animation ();
	try
	{
	    Thread.sleep (1000);
	}
	catch (Exception e)
	{
	}
    }


    // This method draws the title of the program
    private void drawTitle ()
    {
	c.clear ();
	c.print (' ', 32);
	c.println ("Snakes and Ladders");
	c.println (' ');
    }


    /* this program pauses the program and waits for the user to input a character
    name type description/purpose of variable
    key char reads the character the user inputs
    */
    private void pauseProgram ()
    {
	c.print ("Press any to continue...");
	char key = c.getChar ();
    }


    //this method displays the program instructions
    public void instructions ()
    {
	drawTitle ();
	c.println ("Instructions:");
	c.println ();
	c.println ("This is a two player game of snakes and ladders");
	c.println ();
	c.println ("1. Enter both player names.");
	c.println ();
	c.println ("2. Choose level difficulty");
	c.println ();
	c.println ("3.Play the game, press r to roll the dice.  To exit while playing press e.");
	c.println ();
	c.println ("4.Top 10 winners will be stored in a file called winners.txt");
	c.println ();
	c.println ("5.Your score will be stored if your go to the highscore option in mainMenu. If you do not go the highscores, your score will not be saved.");
	c.println ();
	c.println ("6.You can clear the file that stores the winners in the highScore option in mainMenu");
	c.println ();
	pauseProgram ();
    }


    /*this program reads the information in the winners file and then upates the file with the new score data.  It uses four arrays which store the winners name and their score.
    Loops are used to read and write each array.  If statments are used to determin which arrays needs to b accessed.

    Name        type        purpose

    line        string      stores a line from the file
    z           int         used as a counter for both name arrays
    i           int         used as a counter for both score arrays
    */
    private void fileWritter ()
    {
	String line = "";
	int z = 0;
	int i = 1;
	try
	{
	    BufferedReader reader = new BufferedReader (new FileReader ("winners.txt"));

	    //loop for as long as there is data in  the file
	    while (line != "" && line != null)
	    {
		for (int x = 0 ; x <= 40 ; x++)
		{
		    if (x == 1 || x == 3 || x == 5 || x == 7 || x == 9 || x == 11 || x == 13 || x == 15 || x == 17 || x == 19)
		    {
			z++;
			top10NamesEasy [x - z] = reader.readLine ();
		    }

		    else if (x == 2 || x == 4 || x == 6 || x == 8 || x == 10 || x == 12 || x == 14 || x == 16 || x == 18 || x == 20)
		    {
			line = reader.readLine ();
			if (line != "" && line != null)
			{
			    i++;
			    top10ScoresEasy [x - i] = Integer.parseInt (line);
			}
		    }
		    else if (x == 23 || x == 25 || x == 27 || x == 29 || x == 31 || x == 33 || x == 35 || x == 37 || x == 39)
		    {
			z++;
			top10NamesHard [x - z] = reader.readLine ();
		    }

		    else if (x == 24 || x == 26 || x == 28 || x == 30 || x == 32 || x == 34 || x == 36 || x == 38 || x == 40)
		    {
			line = reader.readLine ();
			if (line != "" && line != null)
			{
			    i++;
			    top10ScoresHard [x - i] = Integer.parseInt (line);
			}
		    }
		    else
			line = reader.readLine ();
		}
	    }
	    reader.close ();  //close file

	}
	catch (IOException e)   //catch any file related errors
	{
	    System.out.println (e);  //print error
	}
	//used if first player winnes easy game
	if (winCheck1 == true && difficulty == 1)
	{
	    for (int x = 0 ; x < 9 ; x++)
	    {
		if ((player1Moves < top10ScoresEasy [x]) || (top10ScoresEasy [x] == ' ') || (top10ScoresEasy [x] == 0))
		{
		    for (int y = 9 ; y > x ; y--)
		    {
			top10ScoresEasy [y] = top10ScoresEasy [y - 1];
			top10NamesEasy [y] = top10NamesEasy [y - 1];
		    }
		    top10ScoresEasy [x] = player1Moves;
		    top10NamesEasy [x] = player1Name;
		    break;
		}
	    }
	}
	//used if second player winnes easy game
	else if (winCheck2 == true && difficulty == 1)
	{
	    for (int x = 0 ; x < 9 ; x++)
	    {
		if ((player2Moves < top10ScoresEasy [x]) || (top10ScoresEasy [x] == ' ') || (top10ScoresEasy [x] == 0))
		{
		    for (int y = 9 ; y > x ; y--)
		    {
			top10ScoresEasy [y] = top10ScoresEasy [y - 1];
			top10NamesEasy [y] = top10NamesEasy [y - 1];
		    }
		    top10ScoresEasy [x] = player2Moves;
		    top10NamesEasy [x] = player2Name;
		    break;
		}
	    }
	}
	//used if first player winnes hard game
	else if (winCheck1 == true && difficulty == 2)
	{
	    for (int x = 0 ; x < 9 ; x++)
	    {
		if ((player1Moves < top10ScoresHard [x]) || (top10ScoresHard [x] == ' ') || (top10ScoresHard [x] == 0))
		{
		    for (int y = 9 ; y > x ; y--)
		    {
			top10ScoresHard [y] = top10ScoresHard [y - 1];
			top10NamesHard [y] = top10NamesHard [y - 1];
		    }
		    top10ScoresHard [x] = player1Moves;
		    top10NamesHard [x] = player1Name;
		    break;
		}
	    }
	}
	//used if second player winnes hard game
	else if (winCheck2 == true && difficulty == 2)
	{
	    for (int x = 0 ; x < 9 ; x++)
	    {
		if ((player2Moves < top10ScoresHard [x]) || (top10ScoresHard [x] == ' ') || (top10ScoresHard [x] == 0))
		{
		    for (int y = 9 ; y > x ; y--)
		    {
			top10ScoresHard [y] = top10ScoresHard [y - 1];
			top10NamesHard [y] = top10NamesHard [y - 1];
		    }
		    top10ScoresHard [x] = player2Moves;
		    top10NamesHard [x] = player2Name;
		    break;
		}
	    }
	}
    }


    /*this program gets userinput for wheater they want to clear the winners file.  THis program will clear the file if the user chooses to. And it writes the high scores to the winners file.
     Loops are used to write each array.  If statments are used to determin which arrays needs to be accessed.
     */
    public void highScore ()
    {
	drawTitle ();
	fileWritter ();
	//writes new scrores to file
	if (winCheck2 == true || winCheck1 == true)
	{
	    PrintWriter output;
	    try
	    {
		output = new PrintWriter (new FileWriter ("winners.txt"));
		output.println ("Top 10 Easy players");
		for (int x = 0 ; x < TOTAL ; x++)
		{
		    output.println (top10NamesEasy [x]);
		    if (top10ScoresEasy [x] != 0)
		    {
			output.println (top10ScoresEasy [x]);
		    }
		}
		output.println ();
		output.println ("Top 10 Hard players");
		for (int x = 0 ; x < TOTAL ; x++)
		{
		    output.println (top10NamesHard [x]);
		    if (top10ScoresHard [x] != 0)
		    {
			output.println (top10ScoresHard [x]);
		    }
		}
		output.close ();
	    }
	    catch (IOException e)
	    {
	    }
	}
	//gets user input
	c.println ("The top 10 players and their scores are saved in a file called winners.txt");
	c.println ("Would you like to clear the file?");
	c.println ();
	c.println ("1. yes");
	c.println ("2. no");
	c.println ();
	c.print ("Enter your choice:");
	while (true)
	{
	    try
	    {
		c.setCursor (10, 1);
		c.print (' ', 80);
		c.setCursor (10, 1);
		numstr = c.readLine ();
		clearFile = Integer.parseInt (numstr);
		if (clearFile < 1 || clearFile > 2)
		{
		    JOptionPane.showMessageDialog (null, "Please enter either 1 or 2", "Error", JOptionPane.ERROR_MESSAGE);
		    highScore ();
		}
		break;
	    }
	    catch (NumberFormatException e)
	    {
		JOptionPane.showMessageDialog (null, "Enter an integer. Try again", "Error", JOptionPane.ERROR_MESSAGE);
		highScore ();
	    }
	}
	//clears the file
	if (clearFile == 1)
	{
	    PrintWriter output;
	    try
	    {
		output = new PrintWriter (new FileWriter ("winners.txt"));
		output.println ("Top 10 Easy players");
		for (int x = 0 ; x < TOTAL ; x++)
		{
		    output.println ();
		    output.println ();
		}
		output.println ();
		output.println ("Top 10 Hard players");
		for (int x = 0 ; x < TOTAL ; x++)
		{
		    output.println ();
		    output.println ();
		}
		output.close ();
	    }
	    catch (IOException e)
	    {
	    }

	    pauseProgram ();
	}
    }


    //this method lets the user decide how they want to continue.The if statments are used for error trapping.
    public void mainMenu ()
    {
	drawTitle ();
	c.println ("1.Play");
	c.println ("2.Instructions.");
	c.println ("3.HighScores.");
	c.println ("4.Exit.");
	c.println ();
	c.println ("Choose how you would like to continue, enter the number of your choice: ");
	while (true)
	{
	    try
	    {
		c.setCursor (9, 1);
		c.print (' ', 80);
		c.setCursor (9, 1);
		numstr = c.readLine ();
		choice = Integer.parseInt (numstr);
		if (choice < 1 || choice > 4)
		{
		    while (true)
		    {
			JOptionPane.showMessageDialog (null, "Please Enter a number between 1 and 4", "Error", JOptionPane.ERROR_MESSAGE);
			mainMenu ();
		    }
		}
		break;
	    }
	    catch (NumberFormatException e)
	    {
		JOptionPane.showMessageDialog (null, "Enter an integer. Try again", "Error", JOptionPane.ERROR_MESSAGE);
		mainMenu ();
	    }
	}
    }


    //this program allows the user to decide what game level they would like to play.  If statments are used for error traps.
    public void difficultyLevel ()
    {
	drawTitle ();
	c.println ("1.Easy");
	c.println ("2.Hard");
	c.println ("Choose your difficulty level, Enter the number for your choice:");
	c.println ();
	while (true)
	{
	    try
	    {
		c.setCursor (6, 1);
		c.print (' ', 80);
		c.setCursor (6, 1);
		numstr = c.readLine ();
		difficulty = Integer.parseInt (numstr);
		if (difficulty < 1 || difficulty > 2)
		{
		    while (true)
		    {
			JOptionPane.showMessageDialog (null, "Please Enter either 1 or 2", "Error", JOptionPane.ERROR_MESSAGE);
			difficultyLevel ();
		    }
		}
		break;
	    }
	    catch (NumberFormatException e)
	    {
		JOptionPane.showMessageDialog (null, "Enter an integer. Try again", "Error", JOptionPane.ERROR_MESSAGE);
		difficultyLevel ();

	    }
	}
    }


    //this program gets and stores the names of both players
    public void players ()
    {
	drawTitle ();
	c.println ("Enter player1 name:");
	player1Name = c.readLine ();
	c.println ("Enter player2 name:");
	player2Name = c.readLine ();
    }


    /*this program allows the user to play the hard version of the game.

    Variables

    Name        Type        Description

    roll        char        This saves wheather the user wants to roll the dice.
    randomNum1  int         this stores different random numbers to show the first die rolling
    randomNum2  int         this stores different random numbers to show the second die rolling
    playerTurn  String      this stores which players turn it is
    winner      String      this stores who the winner is
    x1          int         this stores the x position of the first players pawn
    y1          int         this stores the y position of the first players pawn
    x2          int         this stores the x position of the second players pawn
    y2          int         this stores the y position of the second players pawn
    count1      int         this counts how many times the first players pawn has moved during one turn
    count2      int         this counts how many times the second players pawn has moved during one turn
    */
    public void playHardGame ()
    {
	char roll;
	int randomNum1;
	int randomNum2;
	String playerTurn = "";
	String winner = "";
	int x1 = 199;
	int y1 = 434;
	int x2 = 199;
	int y2 = 434;
	int count1 = 0;
	int count2 = 0;
	drawTitle ();
	c.setColor (Color.pink);
	c.fillOval (10, 360, 30, 30);
	c.setColor (Color.black);
	c.drawString ("Player one", 50, 380);
	c.setColor (Color.blue);
	c.fillOval (10, 400, 30, 30);
	c.setColor (Color.black);
	c.drawString ("Player two", 50, 420);
	while (winner == "")
	{
	    try
	    {
		BufferedImage boardPic = ImageIO.read (new File ("Hard.jpg"));
		c.drawImage (boardPic, 230, 80, null);
	    }
	    catch (IOException e)
	    {
	    }
	    c.setColor (Color.pink);
	    c.fillOval (x1, y1, 30, 30);
	    c.setColor (Color.blue);
	    c.fillOval (x2, y2, 30, 30);
	    c.setColor (Color.black);
	    c.drawOval (x1, y1, 30, 30);
	    c.drawOval (x2, y2, 30, 30);
	    count1 = 0;
	    count2 = 0;
	    if (player1Moves > player2Moves)
	    {
		playerTurn = player2Name;
	    }
	    else if (player1Moves == player2Moves)
	    {
		playerTurn = player1Name;
	    }
	    if ((x2 == 237) && (y2 == 83))
	    {
		count2 = dieTotal;
		winner = player2Name;
		winCheck2 = true;
		c.println ("    " + player2Name + " won !");
		break;
	    }
	    else if ((x1 == 237) && (y1 == 83))
	    {
		count1 = dieTotal;
		winner = player1Name;
		winCheck1 = true;
		c.println ("    " + player1Name + " won !");
		break;
	    }
	    c.setCursor (3, 1);
	    c.println ();
	    c.setCursor (3, 1);
	    c.print ("It is " + playerTurn + "'s turn.  Press r to roll or e to exit: ");
	    roll = c.readChar ();
	    if ((roll == 'r') && (playerTurn == player1Name))
	    {
		die1Num = ((int) ((6 - 1 + 1) * Math.random () + 1));
		die2Num = ((int) ((6 - 1 + 1) * Math.random () + 1));
		dieTotal = die1Num + die2Num;
		for (int x = 0 ; x < 40 ; x++)
		{
		    randomNum1 = ((int) ((6 - 1 + 1) * Math.random () + 1));
		    randomNum2 = ((int) ((6 - 1 + 1) * Math.random () + 1));
		    c.setCursor (4, 1);
		    c.println ();
		    c.setCursor (4, 1);
		    c.print ("Die 1: " + randomNum1 + " Die 2: " + randomNum2);
		    try
		    {
			Thread.sleep (18);
		    }
		    catch (Exception e)
		    {
		    }
		}
		c.setCursor (4, 1);
		c.print ("Die 1: " + die1Num + " Die 2: " + die2Num + " Total: " + dieTotal);
		while (count1 < dieTotal)
		{
		    while ((x1 <= 570) && ((y1 == 434) || (y1 == 356) || (y1 == 278) || (y1 == 200) || (y1 == 122)))
		    {
			x1 = x1 + 38;
			count1++;
			if (count1 == dieTotal)
			    break;
		    }
		    while ((x1 > 237) && ((y1 == 395) || (y1 == 317) || (y1 == 239) || (y1 == 161) || (y1 == 83)) && (count1 < dieTotal))
		    {
			x1 = x1 - 38;
			count1++;
			//winning place
			if ((x1 == 237) && (y1 == 83))
			{
			    break;
			}
			if (count1 == dieTotal)
			    break;
		    }
		    if ((x1 > 570) && (count1 < dieTotal))
		    {
			y1 = y1 - 39;
			count1++;
		    }
		    if ((x1 < 274) && (count1 < dieTotal) && (x1 > 230))
		    {
			if ((x1 == 237) && (y1 == 83))
			{
			    break;
			}
			y1 = y1 - 39;
			count1++;
		    }
		    //ladders
		    if ((x1 == 237) && (y1 == 434) && (count1 == dieTotal))
		    {
			y1 = 317;
			x1 = 313;
		    }
		    if ((x1 == 351) && (y1 == 434) && (count1 == dieTotal))
		    {
			x1 = 465;
			y1 = 395;
		    }
		    if ((x1 == 541) && (y1 == 434) && (count1 == dieTotal))
		    {
			y1 = 317;
			x1 = 579;
		    }
		    if ((x1 == 237) && (y1 == 356) && (count1 == dieTotal))
		    {
			y1 = 278;
			x1 = 275;
		    }
		    if ((x1 == 503) && (y1 == 356) && (count1 == dieTotal))
		    {
			y1 = 122;
			x1 = 351;
		    }
		    if ((x1 == 579) && (y1 == 239) && (count1 == dieTotal))
		    {
			y1 = 200;
			x1 = 465;
		    }
		    if ((x1 == 237) && (y1 == 161) && (count1 == dieTotal))
		    {
			y1 = 83;
		    }
		    if ((x1 == 579) && (y1 == 161) && (count1 == dieTotal))
		    {
			y1 = 83;
		    }
		    //snakes
		    if ((x1 == 351) && (y1 == 395) && (count1 == dieTotal))
		    {
			y1 = 434;
			x1 = 465;
		    }
		    if ((x1 == 275) && (y1 == 200) && (count1 == dieTotal))
		    {
			y1 = 395;
		    }
		    if ((x1 == 465) && (y1 == 122) && (count1 == dieTotal))
		    {
			y1 = 356;
			x1 = 351;
		    }
		    if ((x1 == 465) && (y1 == 239) && (count1 == dieTotal))
		    {
			y1 = 317;
		    }
		    if ((x1 == 351) && (y1 == 200) && (count1 == dieTotal))
		    {
			y1 = 239;
			x1 = 237;
		    }
		    if ((x1 == 503) && (y1 == 83) && (count1 == dieTotal))
		    {
			y1 = 161;
		    }
		    if ((x1 == 427) && (y1 == 83) && (count1 == dieTotal))
		    {
			y1 = 161;
		    }
		    if ((x1 == 313) && (y1 == 83) && (count1 == dieTotal))
		    {
			y1 = 161;
			x1 = 275;
		    }
		}
		player1Moves++;
	    }
	    if ((roll == 'r') && (playerTurn == player2Name))
	    {
		c.setColor (Color.white);
		c.fillOval (198, 433, 33, 33);
		die1Num = ((int) ((6 - 1 + 1) * Math.random () + 1));
		die2Num = ((int) ((6 - 1 + 1) * Math.random () + 1));
		dieTotal = die1Num + die2Num;
		for (int x = 0 ; x < 40 ; x++)
		{
		    randomNum1 = ((int) ((6 - 1 + 1) * Math.random () + 1));
		    randomNum2 = ((int) ((6 - 1 + 1) * Math.random () + 1));
		    c.setCursor (4, 1);
		    c.println ();
		    c.setCursor (4, 1);
		    c.print ("Die 1: " + randomNum1 + " Die 2: " + randomNum2);
		    try
		    {
			Thread.sleep (18);
		    }
		    catch (Exception e)
		    {
		    }
		}
		c.setCursor (4, 1);
		c.print ("Die 1: " + die1Num + " Die 2: " + die2Num + " Total: " + dieTotal);
		while (count2 < dieTotal)
		{
		    while ((x2 <= 570) && ((y2 == 434) || (y2 == 356) || (y2 == 278) || (y2 == 200) || (y2 == 122)))
		    {
			x2 = x2 + 38;
			count2++;
			if (count2 == dieTotal)
			    break;
		    }
		    while ((x2 > 237) && ((y2 == 395) || (y2 == 317) || (y2 == 239) || (y2 == 161) || (y2 == 83)) && (count2 < dieTotal))
		    {
			x2 = x2 - 38;
			count2++;
			//winning place
			if ((x2 == 237) && (y2 == 83))
			{
			    break;
			}
			if (count2 == dieTotal)
			    break;
		    }
		    if ((x2 > 570) && (count2 < dieTotal))
		    {
			y2 = y2 - 39;
			count2++;
		    }
		    if ((x2 < 274) && (count2 < dieTotal) && (x2 > 230))
		    {
			if ((x2 == 237) && (y2 == 83))
			{
			    break;
			}
			y2 = y2 - 39;
			count2++;
		    }
		    //ladders
		    if ((x2 == 237) && (y2 == 434) && (count2 == dieTotal))
		    {
			y2 = 317;
			x2 = 313;
		    }
		    if ((x2 == 351) && (y2 == 434) && (count2 == dieTotal))
		    {
			x2 = 465;
			y2 = 395;
		    }
		    if ((x2 == 541) && (y2 == 434) && (count2 == dieTotal))
		    {
			y2 = 317;
			x2 = 579;
		    }
		    if ((x2 == 237) && (y2 == 356) && (count2 == dieTotal))
		    {
			y2 = 278;
			x2 = 275;
		    }
		    if ((x2 == 503) && (y2 == 356) && (count2 == dieTotal))
		    {
			y2 = 122;
			x2 = 351;
		    }
		    if ((x2 == 579) && (y2 == 239) && (count2 == dieTotal))
		    {
			y2 = 200;
			x2 = 465;
		    }
		    if ((x2 == 237) && (y2 == 161) && (count2 == dieTotal))
		    {
			y2 = 83;
		    }
		    if ((x2 == 579) && (y2 == 161) && (count2 == dieTotal))
		    {
			y2 = 83;
		    }
		    //snakes
		    if ((x2 == 351) && (y2 == 395) && (count2 == dieTotal))
		    {
			y2 = 434;
			x2 = 465;
		    }
		    if ((x2 == 275) && (y2 == 200) && (count2 == dieTotal))
		    {
			y2 = 395;
		    }
		    if ((x2 == 465) && (y2 == 122) && (count2 == dieTotal))
		    {
			y2 = 356;
			x2 = 351;
		    }
		    if ((x2 == 465) && (y2 == 239) && (count2 == dieTotal))
		    {
			y2 = 317;
		    }
		    if ((x2 == 351) && (y2 == 200) && (count2 == dieTotal))
		    {
			y2 = 239;
			x2 = 237;
		    }
		    if ((x2 == 503) && (y2 == 83) && (count2 == dieTotal))
		    {
			y2 = 161;
		    }
		    if ((x2 == 427) && (y2 == 83) && (count2 == dieTotal))
		    {
			y2 = 161;
		    }
		    if ((x2 == 313) && (y2 == 83) && (count2 == dieTotal))
		    {
			y2 = 161;
			x2 = 275;
		    }
		}
		player2Moves++;
	    }
	    else if (roll == 'e')
	    {
		return;
	    }
	}
	pauseProgram ();
    }


    /*this program allows the user to play the easy version of the game.

	Variables

	Name        Type        Description

	roll        char        This saves wheather the user wants to roll the dice.
	randomNum1  int         this stores different random numbers to show the first die rolling
	randomNum2  int         this stores different random numbers to show the second die rolling
	playerTurn  String      this stores which players turn it is
	winner      String      this stores who the winner is
	x1          int         this stores the x position of the first players pawn
	y1          int         this stores the y position of the first players pawn
	x2          int         this stores the x position of the second players pawn
	y2          int         this stores the y position of the second players pawn
	count1      int         this counts how many times the first players pawn has moved during one turn
	count2      int         this counts how many times the second players pawn has moved during one turn
	*/
    public void playEasyGame ()
    {
	char roll;
	String playerTurn = "";
	String winner = "";
	int randomNum1;
	int randomNum2;
	int x1 = 175;
	int y1 = 420;
	int x2 = 175;
	int y2 = 420;
	int count1 = 0;
	int count2 = 0;
	drawTitle ();
	c.setColor (Color.pink);
	c.fillOval (10, 355, 40, 40);
	c.setColor (Color.black);
	c.drawString ("Player one", 50, 380);
	c.setColor (Color.blue);
	c.fillOval (10, 400, 40, 40);
	c.setColor (Color.black);
	c.drawString ("Player two", 55, 420);
	while (winner == "")
	{
	    try
	    {
		BufferedImage boardPic = ImageIO.read (new File ("Easy.jpg"));
		c.drawImage (boardPic, 230, 80, null);
	    }
	    catch (IOException e)
	    {
	    }
	    //c.drawLine(230,80,230,475);
	    //c.drawLine(230,80,635,80);
	    c.setColor (Color.pink);
	    c.fillOval (x1, y1, 40, 40);
	    c.setColor (Color.blue);
	    c.fillOval (x2, y2, 40, 40);
	    c.setColor (Color.black);
	    c.drawOval (x1, y1, 40, 40);
	    c.drawOval (x2, y2, 40, 40);
	    count1 = 0;
	    count2 = 0;
	    if (player1Moves > player2Moves)
	    {
		playerTurn = player2Name;
	    }
	    else if ((x1 == 583) && (y1 == 100))
	    {
		count1 = dieTotal;
		winner = player1Name;
	    }
	    else if (player1Moves == player2Moves)
	    {
		playerTurn = player1Name;
	    }
	    if ((x2 == 583) && (y2 == 100))
	    {
		count2 = dieTotal;
		winner = player2Name;
		winCheck2 = true;
		c.println ("    " + player2Name + " won !");
		break;
	    }
	    else if ((x1 == 583) && (y1 == 100))
	    {
		count1 = dieTotal;
		winner = player1Name;
		winCheck1 = true;
		c.println ("    " + player1Name + " won !");
		break;
	    }
	    c.setCursor (3, 1);
	    c.println ();
	    c.setCursor (3, 1);
	    c.print ("It is " + playerTurn + "'s turn.  Press r to roll: ");
	    roll = c.readChar ();
	    if ((roll == 'r') && (playerTurn == player1Name))
	    {
		c.setColor (Color.white);
		c.fillOval (174, 419, 43, 43);
		die1Num = ((int) ((6 - 1 + 1) * Math.random () + 1));
		die2Num = ((int) ((6 - 1 + 1) * Math.random () + 1));
		dieTotal = die1Num + die2Num;
		for (int x = 0 ; x < 40 ; x++)
		{
		    randomNum1 = ((int) ((6 - 1 + 1) * Math.random () + 1));
		    randomNum2 = ((int) ((6 - 1 + 1) * Math.random () + 1));
		    c.setCursor (4, 1);
		    c.println ();
		    c.setCursor (4, 1);
		    c.print ("Die 1: " + randomNum1 + " Die 2: " + randomNum2);
		    try
		    {
			Thread.sleep (18);
		    }
		    catch (Exception e)
		    {
		    }
		}
		c.setCursor (4, 1);
		c.print ("Die 1: " + die1Num + " Die 2: " + die2Num + " Total: " + dieTotal);
		while (count1 < dieTotal)
		{
		    while ((x1 <= 535) && ((y1 == 420) || (y1 == 260) || (y1 == 100)))
		    {
			x1 = x1 + 68;
			count1++;
			if ((x1 == 583) && (y1 == 100))
			{
			    break;
			}
			if (count1 == dieTotal)
			    break;
		    }
		    while ((x1 > 280) && ((y1 == 340) || (y1 == 180)) && (count1 < dieTotal))
		    {
			x1 = x1 - 68;
			count1++;
			if (count1 == dieTotal)
			    break;
		    }
		    if ((x1 > 565) && (count1 < dieTotal))
		    {
			if ((x1 == 583) && (y1 == 100))
			{
			    break;
			}
			y1 = y1 - 80;
			count1++;
		    }
		    if ((x1 < 293) && (count1 < dieTotal) && (x1 > 220))
		    {
			y1 = y1 - 80;
			count1++;
		    }
		    //ladders
		    if ((x1 == 379) && (y1 == 420) && (count1 == dieTotal))
		    {
			y1 = 180;
		    }
		    if ((x1 == 515) && (y1 == 420) && (count1 == dieTotal))
		    {
			y1 = 340;
		    }
		    if ((x1 == 311) && (y1 == 340) && (count1 == dieTotal))
		    {
			y1 = 100;
		    }
		    if ((x1 == 515) && (y1 == 180) && (count1 == dieTotal))
		    {
			y1 = 100;
		    }
		    //snakes
		    if ((x1 == 379) && (y1 == 100) && (count1 == dieTotal))
		    {
			y1 = 420;
			x1 = 243;
		    }
		    if ((x1 == 447) && (y1 == 180) && (count1 == dieTotal))
		    {
			y1 = 340;
		    }
		    if ((x1 == 583) && (y1 == 180) && (count1 == dieTotal))
		    {
			y1 = 340;
		    }
		    if ((x1 == 515) && (y1 == 260) && (count1 == dieTotal))
		    {
			y1 = 420;
			x1 = 447;
		    }
		}
		player1Moves++;
	    }
	    if ((roll == 'r') && (playerTurn == player2Name))
	    {
		c.setColor (Color.white);
		c.fillOval (174, 419, 43, 43);
		die1Num = ((int) ((6 - 1 + 1) * Math.random () + 1));
		die2Num = ((int) ((6 - 1 + 1) * Math.random () + 1));
		dieTotal = die1Num + die2Num;
		for (int x = 0 ; x < 40 ; x++)
		{
		    randomNum1 = ((int) ((6 - 1 + 1) * Math.random () + 1));
		    randomNum2 = ((int) ((6 - 1 + 1) * Math.random () + 1));
		    c.setCursor (4, 1);
		    c.println ();
		    c.setCursor (4, 1);
		    c.print ("Die 1: " + randomNum1 + " Die 2: " + randomNum2);
		    try
		    {
			Thread.sleep (18);
		    }
		    catch (Exception e)
		    {
		    }
		}
		c.setCursor (4, 1);
		c.print ("Die 1: " + die1Num + " Die 2: " + die2Num + " Total: " + dieTotal);
		while (count2 < dieTotal)
		{
		    while ((x2 <= 535) && ((y2 == 420) || (y2 == 260) || (y2 == 100)))
		    {
			x2 = x2 + 68;
			count2++;
			if ((x2 == 583) && (y2 == 100))
			{
			    break;
			}
			if (count2 == dieTotal)
			    break;
		    }
		    while ((x2 > 280) && ((y2 == 340) || (y2 == 180)) && (count2 < dieTotal))
		    {
			x2 = x2 - 68;
			count2++;
			if (count2 == dieTotal)
			    break;
		    }
		    if ((x2 > 565) && (count2 < dieTotal))
		    {
			if ((x2 == 583) && (y2 == 100))
			{
			    break;
			}
			y2 = y2 - 80;
			count2++;
		    }
		    if ((x2 < 293) && (count2 < dieTotal) && (x2 > 220))
		    {
			y2 = y2 - 80;
			count2++;
		    }
		    //ladders
		    if ((x2 == 379) && (y2 == 420) && (count2 == dieTotal))
		    {
			y2 = 180;
		    }
		    if ((x2 == 515) && (y2 == 420) && (count2 == dieTotal))
		    {
			y2 = 340;
		    }
		    if ((x2 == 311) && (y2 == 340) && (count2 == dieTotal))
		    {
			y2 = 100;
		    }
		    if ((x2 == 515) && (y2 == 180) && (count2 == dieTotal))
		    {
			y2 = 100;
		    }
		    //snakes
		    if ((x2 == 379) && (y2 == 100) && (count2 == dieTotal))
		    {
			y2 = 420;
			x2 = 243;
		    }
		    if ((x2 == 447) && (y2 == 180) && (count2 == dieTotal))
		    {
			y2 = 340;
		    }
		    if ((x2 == 583) && (y2 == 180) && (count2 == dieTotal))
		    {
			y2 = 340;
		    }
		    if ((x2 == 515) && (y2 == 260) && (count2 == dieTotal))
		    {
			y2 = 420;
			x2 = 447;
		    }
		}
		player2Moves++;
	    }
	    else if (roll == 'e')
	    {
		return;
	    }
	}
	pauseProgram ();
    }


    //this program displays a goodye message for the user when they exit the program
    public void goodBye ()
    {
	drawTitle ();
	c.println ("Thank you for using this snakes and ladder game program! ");
	c.println ("This program was written by Esha Mahabir.");
	c.println (' ');
	c.println ("Bye!");
	pauseProgram ();
	c.close ();
    }


    /*
       This method calls all other methods and allows them to run
       The if statment is used to determin how the user wants to continue.
     */
    public static void main (String[] args)
    {
	SnakesandLadders s = new SnakesandLadders ();
	s.splashScreen ();
	while (true)
	{
	    s.mainMenu ();
	    if (s.choice == 2)
	    {
		s.instructions ();
	    }
	    else if (s.choice == 3)
	    {
		s.highScore ();
	    }
	    else if (s.choice == 4)
	    {
		break;
	    }
	    else if (s.choice == 1)
	    {
		s.players ();
		s.difficultyLevel ();
		if (s.difficulty == 1)
		{
		    s.playEasyGame ();
		}
		else if (s.difficulty == 2)
		{
		    s.playHardGame ();
		}
	    }
	}
	s.goodBye ();
    }
} //main method
