/* Name: Amit Nijjar
 * PSA7
 * Date: 2/27/14
 */

public class TurtleRecursion {
   
  /*Method spiral:
    * this method will draw a spiral shape
    * @param turtle - the turtle that will draw the spira
    * @param initialLength - the starting length and later how far the turtle will move forward
    * @param angle - how much the turtle will turn
    * @param multiplier - how much the starting length is changing
    * */  
  public void spiral(Turtle turtle, double initialLength, int angle, double multiplier){

    if( (initialLength >= 1) && (initialLength <= 200)){ //sets up the base case,
      //the code will run if the base case isn't met
      turtle.forward((int)(initialLength));
      turtle.turn(angle);
      spiral(turtle,(initialLength*multiplier),angle,multiplier); //recursion step 
    }
  }
  /*Method tree:
   * the method will draw the far left side of the tree first, then it will fill in the right side
   * @param turtle - the turtle being moved
   * @param trunkLength - how far the turtle is being moved
   * @param height - how many more times the method will be called again
   * */
  public void tree(Turtle turtle, int trunkLength, int height){
    
    if(height == 0){//the base case
      turtle.forward(trunkLength);
      turtle.backward(trunkLength);
    }
    else {
      turtle.forward(trunkLength); //makes the left side of the branch
      turtle.turn(-30);
      tree(turtle,((int)(trunkLength*.75)),(height -1)); 
      //continues to make the left side until the base case is met
      turtle.turn(60);
      tree(turtle,((int)(trunkLength*.75)),(height -1));
      //fills in the right side once the base case is met for the left side
      turtle.turn(-30);
      turtle.backward(trunkLength);
    }
    
  }
  
  public static void main(String[] args){
    
    TurtleRecursion test1 = new TurtleRecursion(); //tests out the spiral method
    World w1 = new World();
    Turtle t1 = new Turtle(w1);
    test1.spiral(t1,1,-45,1.1);
    
    TurtleRecursion test2 = new TurtleRecursion(); //tests out the tree method
    World w2 = new World(1200,900);
    Turtle t2 = new Turtle(w2);
    t2.penUp();
    t2.moveTo( w2.getWidth()/2,w2.getHeight() -1); 
    //moves the turtle to the bottom center of the world
    t2.penDown();
    test2.tree(t2,256,7);
  }
}