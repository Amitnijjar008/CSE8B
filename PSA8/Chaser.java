/*
 * Name: Amit Nijjar
 * Date: March 11,2014
 * PSA8
 */

import java.awt.*;
import javax.swing.*;


public class Chaser extends Critter
{
  
 //instance variables 
public int xRelative;
public int yRelative;
  
  
//sets the variables the x and y positions for the Chaser critter
public Chaser(int x, int y) {
super(x,y);
}

/**
 * a method to make the chaser move toward the closest Critter 
 * @param Critter c - the closest critter
 * @param Rectangle r - the rectangle world that all the Critters are in
 */
public void reactTo( Critter c, Rectangle r ){

  //sets instance variables
  xRelative = (c.getX() - this.getX());
  yRelative = (c.getY() - this.getY());
  
  //moves the critter toward another critter based on the ints xRelative and yRelative
  //moves the critter based on if xRelative and yRelative are posivie, negative, or zero
  if (xRelative > 0){
    if(yRelative > 0) {
    this.x = x+1;
    this.y = y+1;
    }
    if(yRelative < 0) {
    this.x = x+1;
    this.y = y-1;
    }
    if(yRelative == 0) {
    this.x = x+1;
    this.y = y;
    }
  }
  
  if (xRelative < 0){
    if(yRelative > 0) {
    this.x = x-1;
    this.y = y+1;
    }
    if(yRelative < 0) {
    this.x = x-1;
    this.y = y-1;
    }
    if(yRelative == 0) {
    this.x = x-1;
    this.y = y;
    }
  }
  
  if (xRelative == 0){
    if(yRelative > 0) {
    this.x = x;
    this.y = y+1;
    }
    if(yRelative < 0) {
    this.x = x;
    this.y = y-1;
    }
    if(yRelative == 0) {
    this.x = x;
    this.y = y;
    }
  }
}

//makes a red circle for the Chaser
//@param graphics G - the graphic object
public void paint(Graphics g) {

  Color red = new Color(255,0,0);
  g.setColor(red);
  g.fillOval(x-7,y-7,15,15);

}
  }
