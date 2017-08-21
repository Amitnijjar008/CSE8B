/* 
 * Name: Amit Nijjar A11489111
 * PSA4
 * 1/29/14
 * 
 * This class creates a Connect Four board and plays the game. 
 **/

import java.util.*;

public class ConnectFourBoard {
  
  private char[][] board; //creating fields
  private int numRows;
  private int numColumns; 
  
  /*@param columns: the amount of columns the board will have
   *@param rows: the amount of rows the board will have
   * method sets the demensions for the game board and makes an empty board
   **/
  public ConnectFourBoard(int columns, int rows){
    this.numRows = rows;
    this.numColumns = columns;
    this.board = new char[numRows][numColumns];
    
    for (int arrHorizPosition = 0; arrHorizPosition < numRows; arrHorizPosition++){ //loops through the double array
      for (int arrVertPosition = 0; arrVertPosition < numColumns; arrVertPosition++){
        board[arrHorizPosition][arrVertPosition] =' '; //sets each char to an empty space
        
      }
    }    
  }
  /*
   * makes a default sixed connect four board
   */
  public ConnectFourBoard(){
    this(7,6);
  }
  
  /*
   * @return: returns the string that makes up the game board
   * method makes the game board
   **/
  public String toString(){
    String toReturn = new String();
    
    // Create the Spaces that make up the game board
    for (int arrHorizPosition = 0; arrHorizPosition < numRows; arrHorizPosition++){ 
      for (int arrVertPosition = 0; arrVertPosition < numColumns; arrVertPosition++){
        toReturn += "|" + board[arrHorizPosition][arrVertPosition];        
      }
      toReturn += "|\n";
    }
    
    // Create the line at the bottom of the board
    for ( int rnum = 0; rnum < this.numColumns; rnum++ )
    {
      toReturn += "--";
    }
    toReturn += "-\n";
    
    // Create the column numbers  
    for ( int rnum = 0; rnum < this. numColumns; rnum++ )
    {
      toReturn += " " + rnum % 10;
    }
    toReturn += "\n";
    return toReturn;
  }
  
  /*
   *allows a move to be added in the game
   * @param columns: specifies what column to add the game piece too
   * @param checker: Shows whos move it is, if it is 'X' or 'O' and places their piece
   */
  public void addMove(int columns, char checker){
    int arrVertPosition = columns; //find the column to use
    for (int arrHorizPosition = numRows-1; arrHorizPosition >= 0; arrHorizPosition--){ //loops through the layers of the column
      
      if ( this.board[arrHorizPosition][arrVertPosition] == ' ' ){ //finds an empty spot
        this.board[arrHorizPosition][arrVertPosition] = checker; //replaces empty spot with game piece
        break;
      }
    }  
  }
  
  /*
   * Clears the game board
   **/
  public void clear(){
    for (int arrHorizPosition = 0; arrHorizPosition < numRows; arrHorizPosition++){ //loops through the board
      for (int arrVertPosition = 0; arrVertPosition < numColumns; arrVertPosition++){
        board[arrHorizPosition][arrVertPosition] = ' '; //clears the board
      }
    }
  }
  
  /** Takes in a string of columns and places
    * alternating checkers in those columns,
    * starting with 'X'
    * @param moveString A string of integers. 
    *    Note that this method will only play 
    *    in the first 10 columns of a board.
    */   
  public void setBoard( String moveString )
  {
    char nextCh = 'X';   // start by playing 'X'
    for ( int i = 0; i < moveString.length(); i++ )
    {
      int col = Character.getNumericValue( moveString.charAt( i ) );
      if ( 0 <= col && col < numColumns )
        addMove(col, nextCh);
      if ( nextCh == 'X' )
        nextCh = 'O';
      else
        nextCh = 'X';
    }
  }
  
  /*
   * checks to see if the column exists and if has at least one spot free
   * @param column: specifies what column to check
   **/
  public boolean allowsMove(int column){
    boolean result = false;
    if((column >=0 && column < numColumns)){ //checks if the column exists on the board
      if (this.board[0][column] == ' '){ //checks if the top slot of the board is empty
        result = true;
      }
    }
    return result;
  }
  
  /*
   * checks to see if the whole game board is full
   */
  public boolean isFull(){
    boolean result = true;
    
    for (int arrHorizPosition = 0; arrHorizPosition < numRows; arrHorizPosition++){ //loops through the board
      for (int arrVertPosition = 0; arrVertPosition < numColumns; arrVertPosition++){
        if(this.board[0][arrVertPosition] == ' '){
          result = false;
          break;
        }
      }
    }
    
    return result;
  }  
  /*
   * this method checks if there is any combination of 4 in a row on the board
   * @param checker: specifies what char to check for
   */
  public boolean winsFor( char checker ){
    boolean result = false;
    //checks for a horizontal win
    for (int arrHorizPosition = 0; arrHorizPosition < numRows; arrHorizPosition++){ //loops through the board
      for (int arrVertPosition = 0; arrVertPosition < numColumns-3; arrVertPosition++){
        if ( board[arrHorizPosition][arrVertPosition] == checker && //checks for a horizontal win
            board[arrHorizPosition][arrVertPosition+1] == checker && 
            board[arrHorizPosition][arrVertPosition+2] == checker && 
            board[arrHorizPosition][arrVertPosition+3] == checker ){
          result = true;
          return result;
        }
      }
    }
    //checks for a vertical win
    for (int arrHorizPosition = 0; arrHorizPosition < numRows-3; arrHorizPosition++){ //loops through the board
      for (int arrVertPosition = 0; arrVertPosition < numColumns; arrVertPosition++){
        if ( board[arrHorizPosition][arrVertPosition] == checker && //checks for a vertical win
            board[arrHorizPosition+1][arrVertPosition] == checker && 
            board[arrHorizPosition+2][arrVertPosition] == checker && 
            board[arrHorizPosition+3][arrVertPosition] == checker ){
          result = true;
          return result;
        }
      }
    }
    //checks win for a diagonal of slope 1 or in / direction
    for (int arrHorizPosition = numRows-1; arrHorizPosition > 2; arrHorizPosition--){ //loops through the board
      for (int arrVertPosition = 0; arrVertPosition < numColumns-3; arrVertPosition++){
        if ( board[arrHorizPosition][arrVertPosition] == checker && //checks for a diagonal(\) win
            board[arrHorizPosition-1][arrVertPosition+1] == checker && 
            board[arrHorizPosition-2][arrVertPosition+2] == checker && 
            board[arrHorizPosition-3][arrVertPosition+3] == checker ){
          result = true;
          return result;
        }
      }
    }
    
    //checks win for a diagonal of slope -1 or in \ direction
    for (int arrHorizPosition = 0; arrHorizPosition < numRows-3; arrHorizPosition++){ //loops through the board
      for (int arrVertPosition = 0; arrVertPosition < numColumns-3; arrVertPosition++){
        if ( board[arrHorizPosition][arrVertPosition] == checker && //checks for a diagonal(/) win
            board[arrHorizPosition+1][arrVertPosition+1] == checker && 
            board[arrHorizPosition+2][arrVertPosition+2] == checker && 
            board[arrHorizPosition+3][arrVertPosition+3] == checker ){
          result = true;
          return result;
        }
      }
      
    }
    return result;
  }
  /*
   * this method makes a new game when it is called
   * it combines above methods
   */
  public void hostGame(){
    this.clear(); //clears the board
    System.out.println("Welcome to Connect Four!\n");
    char gameChecker = 'X'; //sets X as the first player
    
    for(int x = 0; x >=0; x++){ //is an infinite loop that breaks when someone wins the game or there is a tie
      System.out.println(this.toString()); //prints out the board
      System.out.println("Player " + gameChecker + "'s turn."); 
      System.out.println("Which column? ");
      Scanner input = new Scanner(System.in);
      int columnPlace = input.nextInt();
      if(columnPlace >= 0 && columnPlace < numColumns && (this.allowsMove(columnPlace) == true)){ 
        //checks if the input is in the board's domain and if the move is allowed
        this.addMove(columnPlace,gameChecker);
          if(gameChecker == 'X'){//changes game pieces
            gameChecker = 'O';
          }
          else if(gameChecker == 'O'){
            gameChecker = 'X';
          }
        }
        
        if(this.winsFor('X') == true){//tests if X won
          System.out.println(this.toString());
          System.out.println("Player X Won!");
          break;                    
        }
        
        if(this.winsFor('O') == true){//tests if O won
          System.out.println(this.toString());
          System.out.println("Player O Won!");
          break;                    
        }
        if(this.isFull() == true){//tests if the game is a tie
          System.out.println(this.toString());
          System.out.println("The game is a tie!");
          break;
        }
      }
    } 
  
  
  public static void main(String [] args){
    ConnectFourBoard test = new ConnectFourBoard();
/* // THESE ARE THE TESTS I USED TO TEST MY METHODS
    test.toString();
    test.addMove(0, 'X');
    test.addMove(0, 'O');
    test.addMove(0, 'X');
    test.addMove(0, 'X');
    test.addMove(0, 'X');
    test.addMove(0, 'O');
    test.addMove(3, 'O');
    test.addMove(4, 'O');
    test.addMove(5, 'O');
    test.addMove(6, 'O');
    System.out.println( test );
    test.clear();
    System.out.println( test );
    test.setBoard("012345");
    test.setBoard("000000");
    System.out.println( test );
    
    test.allowsMove(0);
    test.allowsMove(-1);
    test.allowsMove(1);
    test.allowsMove(2);
    test.allowsMove(3);
    test.allowsMove(4);
    test.allowsMove(5);
    test.allowsMove(6);
    test.allowsMove(7);
    test.setBoard( "000111222" );
    test.isFull();
    System.out.println( test );
    test.setBoard( "641513216315316230653" );
    System.out.println(test);
    System.out.println(test.winsFor('X'));
    System.out.println(test.winsFor('O'));
  */  
    test.hostGame();

  }
}  