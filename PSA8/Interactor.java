/*
 * Name: Amit Nijjar
 * Date: March 11,2014
 * PSA8
 */

import java.util.*;
import java.awt.*;
import java.lang.*;

/** Don't forget your header comment. */
public class Interactor
{
  public Critter closeCritter;
  /** This method takes a Critter and a list of Critters.  It finds the 
    * Critter from the list of Critters that c will interact with.  Usually
    * this is the closest Critter in the list, but you might vary this slightly
    * (See the PSA instructions).  Then it asks the Critter c to reactTo
    * that Critter.  The Rectangle r is the bounds in which 
    * the Critters must stay, and should be passed to the reactTo method.
    */
  public void interact(Critter c, ArrayList<Critter> cList, Rectangle r)
  {//ends the method if there are less than two critters
    if (cList.size() < 2){
      return;
    }
    //makes variables for the method
      double xOne = c.getX();
      double yOne = c.getY();
      double xTwo;
      double yTwo;
      double xDistance;
      double yDistance;
      double critterDistance;
      double smallestDistance;
      Critter check;
      
      //makes sure there is no null pointer exception
      //makes sure the critter closest is not the same critter
        check = cList.get(0);
        closeCritter = check;
        if(c == check){
        check = cList.get(1);
        closeCritter = check;
        }
        //sets up variables for the distance formula
        xTwo = check.getX();
        yTwo = check.getY();
        xDistance = xTwo - xOne;
        yDistance = yTwo - yOne;
        //uses distance formula to check closest critters
        critterDistance = (xDistance*xDistance)+(yDistance*yDistance);
        
          smallestDistance = critterDistance;
      
    //loops through all the critters to find the closest critter  
    for(int x = 0; x < cList.size(); x++){
      if (c == cList.get(x)){
        continue;
      }
        check = cList.get(x);
        //sets up variables for the distance formula
        xTwo = check.getX();
        yTwo = check.getY();
        xDistance = xTwo - xOne;
        yDistance = yTwo - yOne;
        //uses distance formula to find closest critter
        critterDistance = (xDistance*xDistance)+(yDistance*yDistance);
        //sets the closest critter to closeCritter
        if (critterDistance < smallestDistance){
          closeCritter = check;
          smallestDistance = critterDistance;
        }
    
    }
    //tells critter c to react to other critters if there are two or more critters 
    if (cList.size() > 1 ){
    c.reactTo(closeCritter,r);
    }
  }
}