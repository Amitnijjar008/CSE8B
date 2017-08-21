/*
 * Name: Amit Nijjar
 * Date: March 11,2014
 * PSA8
 */

import java.awt.*;
import javax.swing.*;
import java.util.*;


public class Custom extends Critter
{
  
//instance variables 
public int xRelative;
public int yRelative;
 

//sets the variables the x and y positions for the Custom critter
  
public Custom(int x, int y) {
super(x,y);
}

/**
 * a method to make the chaser move to a random location when another critter touches its location 
 * @param Critter c - the critter that goes directly over the Custom Critter
 * @param Rectangle r - the rectangle world that all the Critters are in
 */
public void reactTo( Critter c, Rectangle r ){

 //sets xRelative and yRelative
  xRelative = (c.getX() - this.getX());
  yRelative = (c.getY() - this.getY());
  //restricts the critter to inside the boundary
  int xMax = (int) r.getWidth() - 15;
  int yMax = (int) r.getHeight() - 15;
  
  //makes a random number to move the critter to
 java.util.Random randomMovement = new java.util.Random();
 int randomNumX = randomMovement.nextInt(xMax);
 int randomNumY = randomMovement.nextInt(yMax); 
  
    //moves the critter to a random location
 if ((c.getX() == this.getX()) && (c.getY() == this.getY())){
   this.x = randomNumX + 7;
   this.y = randomNumY + 7;
 }
}


//makes a yellow circle for the Chaser
//@param graphics G - the graphic object
public void paint(Graphics g) {
Color yellow = new Color(255,255,0);
  g.setColor(yellow);
  g.fillOval(x-7,y-7,15,15);
}

}