/*
 * Name: Amit Nijjar
 * Date: March 11,2014
 * PSA8
 */

import java.awt.*;
import javax.swing.*;


public class Runner extends Critter
{

 //instance variables 
public int xRelative;
public int yRelative;

//sets the variables the x and y positions for the Runner critter

public Runner(int x, int y) {
super(x,y);
}

/**
 * a method to make the runner move away from the closest Critter 
 * @param Critter c - the closest critter
 * @param Rectangle r - the rectangle world that all the Critters are in
 */
public void reactTo( Critter c, Rectangle r ){

  //moves the critter away from another critter based on the ints xRelative and yRelative
  //moves the critter based on if xRelative and yRelative are posivie, negative, or zero
  xRelative = (c.getX() - this.getX());
  yRelative = (c.getY() - this.getY());
  
  if (xRelative > 0){
    if (this.x < 7){
    this.x = x+1;
    }
    
    if(yRelative > 0) {
      if(this.y < 7){
      this.y = y +1;
      }
    this.x = x-1;
    this.y = y-1;
    }
    if(yRelative < 0) {
      if(this.y > r.getHeight() - 7){
        this.y = y-1;
      }
    this.x = x-1;
    this.y = y+1;
    }
    if(yRelative == 0) {
    this.x = x-1;
    this.y = y;
    }
  }
  
  if (xRelative < 0){
    if (this.x > r.getWidth() - 7){
    this.x = x-1;
    }
    
    if(yRelative > 0) {
      if(this.y < 7){
        this.y = y +1;
      }
    this.x = x+1;
    this.y = y-1;
    }
    if(yRelative < 0) {
      if(this.y > r.getHeight() - 7){
        this.y = y-1;
      }
    this.x = x+1;
    this.y = y+1;
    }
    if(yRelative == 0) {
    this.x = x+1;
    this.y = y;
    }
  }
  
  if (xRelative == 0){
    if(yRelative > 0) {
      if(this.y < 7){
        this.y = y +1;
      }
    this.x = x;
    this.y = y-1;
    }
    if(yRelative < 0) {
      if(this.y > r.getHeight() - 7){
        this.y = y-1;
      }
    this.x = x;
    this.y = y+1;
    }
    if(yRelative == 0) {
    this.x = x;
    this.y = y;
    }
  }

}

//makes a green square for the Runner
//@param graphics G - the graphic object
public void paint(Graphics g) { 
Color green = new Color(0,255,0);
  g.setColor(green);
  g.fillRect(x-7,y-7,15,15);

}
}



  
