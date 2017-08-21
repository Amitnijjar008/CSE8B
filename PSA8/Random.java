/*
 * Name: Amit Nijjar
 * Date: March 11,2014
 * PSA8
 */

import java.awt.*;
import javax.swing.*;
import java.util.*;


public class Random extends Critter
{

//instance variables 
public int xRelative;
public int yRelative;
 
//sets the variables the x and y positions for the Random critter

public Random(int x, int y) {
super(x,y);
}

/**
 * a method to make the chaser move randomly 
 * @param Critter c - the closest critter
 * @param Rectangle r - the rectangle world that all the Critters are bound in
 */
public void reactTo( Critter c, Rectangle r ){

 //declairs the xRelative and yRelative variables
  xRelative = (c.getX() - this.getX());
  yRelative = (c.getY() - this.getY());
  //makes random numbers for the critter to move
 java.util.Random randomMovement = new java.util.Random();
 int randomNumXPos = randomMovement.nextInt(5);
 int randomNumYPos = randomMovement.nextInt(5); 
 int randomNumXNeg = randomMovement.nextInt(5);
 int randomNumYNeg = randomMovement.nextInt(5); 
  
 //the critter moves randomly depending on the sum of the random ints made above
 if (this.x < r.getWidth() - 11){
    this.x = x+randomNumXPos; 
 }
 if (this.y < r.getHeight() - 11){
    this.y = y+randomNumYPos;
 }
 if (this.x > 11){
    this.x = x-randomNumXNeg; 
 }
 if (this.y > 11){
    this.y = y-randomNumYNeg;
 }
}

//makes a blue X for the Random Critter
//@param graphics G - the graphic object
public void paint(Graphics g) {
 
Color blue = new Color(0,0,255);
  g.setColor(blue);
  g.drawLine(x-7,y-7,x+7,y+7);
  g.drawLine(x-7,y+7,x+7,y-7);
}

}

  
