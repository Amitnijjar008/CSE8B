// Keep these two lines.  They are what tell Java to include the
// classes you need for working with files.
// You might get warnings about them at first.  That's OK, just
// ignore the warnings.  They should go away as you complete your code.
import java.io.*;
import java.util.*;
import java.*;

/** Your header comment goes here.
  * Be sure to include your name (both of you if you worked with a partner)
  * and the date 
  * 
  * Author: Alex Luu & Amit Nijjer
  * PSA: 2
  * Date: 01/10/14
  * 
  */
public class WordFun
{
  // Complete the methods below.  Be sure to add header (javadoc style)
  // comments for each. You may (and should) also write additional
  // helper methods.  Be sure to make the helper methods private and include
  // header comments for each.

  // Although you will not be graded on style this week, you should follow
  // these basic style guidelines nonetheless.   You will be graded on this
  // in weeks to come, so start to practice now.

  // Use proper indenting: Indent each block of code (e.g., method body,
  //   loop body.  Line up the lines in the block so that they are all
  //   indented to the same degree.  See examples of this in the book
  //   and in the code below.
  // Use descriptive variable names: The names of your variables should
  //   describe the data they hold.  Almost always, your variable names
  //   should be words (or abbreviations), not single letters.
  // Write short methods: Break your methods up into submethods if they
  //   are getting too complicated or long.  Generally your methods
  //   shouldn't get too much longer than about 20 lines of code (give or take)
  // Write short lines: Each line of code should be no longer than 80
  //   characters, so it can fit in a reasonable size window.  There's a
  //   column number in the lower-right corner of Dr Java (row:col).
  //
  // We'll start with these, as these are the most important.  We may add
  // to this list later in the term, but if you do all of the above you're
  // in good shape.

  /** Return the score of the letter in scrabble.
    * @param ch The letter in question
    * @return The scrabble value of the letter
    * */
  private int letterScore( char ch )
  {
    char lowerCase = Character.toLowerCase( ch );
    switch (lowerCase) {
      case 'a': return 1;
      case 'b': return 3;
      case 'c': return 3;
      case 'd': return 2;
      case 'e': return 1;
      case 'f': return 4;
      case 'g': return 2;
      case 'h': return 4;
      case 'i': return 1;
      case 'j': return 8;
      case 'k': return 5;
      case 'l': return 1;
      case 'm': return 3;
      case 'n': return 1;
      case 'o': return 1;
      case 'p': return 3;
      case 'q': return 10;
      case 'r': return 1;
      case 's': return 1;
      case 't': return 1;
      case 'u': return 1;
      case 'v': return 4;
      case 'w': return 4;
      case 'x': return 8;
      case 'y': return 4;
      case 'z': return 10;
      default: return 0;
    }
  }

  /** Returns the probability of each letter, in English
    * @param ch The character in question
    * @return The probability of that letter occurring in the English language
    * */
  private double letterProb( char ch )
  {
    char lowerCase = Character.toLowerCase( ch );
    switch (lowerCase) {
      case ' ': return 0.1904;
      case 'e': return 0.1017;
      case 't': return 0.0737;
      case 'a': return 0.0661;
      case 'o': return 0.0610;
      case 'i': return 0.0562;
      case 'n': return 0.0557;
      case 'h': return 0.0542;
      case 's': return 0.0508;
      case 'r': return 0.0458;
      case 'd': return 0.0369;
      case 'l': return 0.0325;
      case 'u': return 0.0228;
      case 'm': return 0.0205;
      case 'c': return 0.0192;
      case 'w': return 0.0190;
      case 'f': return 0.0175;
      case 'y': return 0.0165;
      case 'g': return 0.0161;
      case 'p': return 0.0131;
      case 'b': return 0.0115;
      case 'v': return 0.0088;
      case 'k': return 0.0066;
      case 'x': return 0.0014;
      case 'j': return 0.0008;
      case 'q': return 0.0008;
      case 'z': return 0.0005;
      default: return 0.0;
    }
  }
  /** Return the score of the word in scrabble.
    * @param word The word in question
    * @return The scrabble value of the word
    * */
  public int scrabbleScore( String word )
  {
    int total = 0; //declare the total score
    char[] wordArray = word.toCharArray(); //turns the String word into an array
    
    for(int i = 0; i< wordArray.length; i++) { //loops through the word array 
    int score = letterScore(wordArray[i]); //gets the score of the letter 
    total = total + score; //adds that letter score to the total
    }
    return total; //return the total score
  }

  /** Encrypts the message.
    * @param s The message in question
    * @param rotation The amount of letters looped
    * @return The encrypted message
    * */
  public String encrypt(String s, int rotation)
  {
    char[] sArray = s.toCharArray(); //turns the String s into an array
    char[] output = new char[sArray.length]; //makes a second char array of the same length as the first one
    char newChar = 'a'; //establishes the variable newChar
    for(int i = 0; i < sArray.length; i++) { //loops through the whole array
      if(((int)sArray[i] <= 122) && ((int)sArray[i] >= 97)){ //sets conditions for lower case letters
        newChar = lowerRot(sArray[i],rotation); //rotates lower case letters
        output[i] = newChar;} //sets the new char into the output array
      else if(((int)sArray[i] <= 90) && ((int)sArray[i] >= 65)){ //sets conditions for upper case letters
        newChar = upperRot(sArray[i],rotation); //rotates upper case letters
        output[i] = newChar;} //sets the new char into the output array
      else { //sets condition if the char isnt the letter
        output[i] = sArray[i]; //sets the same char into the output array
      }
    }
  
    String result = new String(output); //converts the char array to a string
    return result; //returns the string
  }
  
  /** Decrypts the message.
    * @param s The message in question
    * @param rotation The amount of letters looped
    * @return The decrypted message
    * */
  public String decrypt(String s, int rotation)
  {
    rotation = -rotation; //changes the direction of the rotation
    char[] sArray = s.toCharArray(); //turns the String s into an array
    char[] output = new char[sArray.length]; //makes a second char array of the same length as the first one
    char newChar = 'a'; //establishes the variable newChar
    for(int i = 0; i < sArray.length; i++) { //loops through the whole array
      if(((int)sArray[i] <= 122) && ((int)sArray[i] >= 97)){ //sets conditions for lower case letters
        newChar = lowerRot(sArray[i],rotation); //rotates lower case letters
        output[i] = newChar;} //sets the new char into the output array
      else if(((int)sArray[i] <= 90) && ((int)sArray[i] >= 65)){ //sets conditions for upper case letters
        newChar = upperRot(sArray[i],rotation); //rotates upper case letters
        output[i] = newChar;} //sets the new char into the output array
      else { //sets condition if the char isnt the letter
        output[i] = sArray[i]; //sets the same char into the output array
      }
    }
  
    String result = new String(output); //converts the char array to a string
    return result; //returns the string
  }
  
  /** Rotates lower cased letters.
    * @param letter The letter being rotated
    * @param rotation The amount of letters looped
    * @return The rotated letter 
    * */
  public static char lowerRot(char letter, int rotation)
  { 
    int value = (int)letter; //declaring objects
    int newValue = 0; //declaring objects
    int position = 0; //declarinig objects
 
    for(;rotation >= 26; rotation=rotation-26) { //making sure the rotation doesn't exceed 26
      if(rotation < 26) //checks if rotation is less than 26
        break; //stops the loop
    }
    
    for(;rotation <= -26; rotation=rotation+26) { //making sure the rotation doesn't go under 26
      if(rotation > -26) //checks if the rotation is greater than -26
        break; //stops the loop
    } 
    
    if(value <= 122 && value >= 97) { //making sure the rotated letter is lower cased
      position = (int) letter - (int) 'a'; //finds the change in position from letter to 'a'
      rotation = rotation + position; //sets rotation to target letter rotating from 'a'
    }
    
    for(;rotation >= 26; rotation=rotation-26) { //making sure the rotation doesn't exceed 26
      if(rotation<26) //checks if rotation is less than 26
        break; //stops the loop
    }
    
    for(;rotation <= -26; rotation=rotation+26) { //making sure the rotation doesn't go under 26
      if(rotation> -26) //checks if the rotation is greater than -26
        break; //stops the loop
    }
    
    newValue = (int)'a' + rotation; //value of the rotated letter
    
    if(newValue < 97) { //making sure the rotated letter doesn't go under the int for 'a'
      newValue = newValue + 26;
//      newValue = (int)'z';
//      rotation = -(26 + rotation);   
//      newValue = newValue + rotation;
    }
    
    char newLetter = (char) newValue; //casting the rotated value into a letter
    return newLetter; //returns the letter
  }
  
  /** Rotates upper cased letters.
    * @param letter The letter being rotated
    * @param rotation The amount of letters looped
    * @return The rotated letter 
    * */
  public static char upperRot(char letter,int rotation)
  { 
    int upperChar = (int) letter;
    if((int) letter >= 65 && (int) letter <= 90){
    int lower = (int)letter + 32; //converts the letter to lower case
    char temp = lowerRot((char) lower, rotation); //uses lowerRoot method to rotate lower case letter
    upperChar = (int)temp - 32; //converts rotated letter to uppercase
    }
    return (char) upperChar; //returns the letter
  }

  /** Encrypts the message in a file.
    * @param infile The file to read the message from
    * @param outfile The file to write to after the message is encrypted
    * @param rotation The amount of letters looped
    * */
  public void encryptFile(String infile, String outfile, int rotation) throws IOException
  {
    Scanner input = new Scanner(new File(infile)); //reads the message from the infile
    PrintWriter output = new PrintWriter(outfile); //writes the encrypted message to the outfile
    
    while(input.hasNext()) { //scans the lines for the message from the infile
      String s1 = input.nextLine(); //saves the line to the variable s1
      String s2 = encrypt(s1, rotation); //encrypts the lines from s1 by the rotation
      output.println(s2); //prints out the encrypted line
    }   
    input.close(); //closes the infile
    output.close(); //closes the outfile
  }

  /** Decrypts the message in a file.
    * @param infile The file to read the encrypted message from
    * @param outfile The file to write to after the message is decrypted
    * @param rotation The amount of letters looped
    * */
  public void decryptFile(String infile, String outfile, int rotation) throws IOException
  {
    Scanner input = new Scanner(new File(infile)); //reads the encrypted message from the infile
    PrintWriter output = new PrintWriter(outfile); //writes the decrypted message to the outfile
    
    while(input.hasNext()) { //scans the lines for the message from the infile
      String s1 = input.nextLine(); //saves the line to the variable s1
      String s2 = decrypt(s1, rotation); //decrypts the lines from s1 by the rotation
      output.println(s2); //prints out the encrypted line
    }
    input.close(); //closes the infile
    output.close(); //closes the outfile
  }

  public String decryptSolve(String s)
  {
    // Complete this method for extra credit
    return "";
  }

  public void decryptFileSolve(String infile, String outfile) throws IOException
  {
    // Complete this method for extra credit
  }

  public String pigLatinWord( String word )
  {
    // Complete this method for extra credit
    return "";
  }

  public String pigLatin( String text )
  {
    // Complete this method for extra credit
    return "";
  }

  /** The main method. THIS IS WHERE YOU SHOULD ADD MORE TESTS
    * FOR scrabbleScore, encrypt and decrypt methods AND ANY OTHER
    * METHOD THAT YOU CREATE. BE SURE TO TEST YOUR CODE THOROUGHLY
    * */
  public static void main( String[] args ) throws IOException
  {
    //Part 1. Scrabble Scoring Tests
    WordFun f = new WordFun();
    System.out.println("Score for 'Student' = "+f.scrabbleScore("Student") + " ...(should be 8)");
    System.out.println("Score for 'programming' = "+f.scrabbleScore("programming") + " ...(should be 19)");
    
    System.out.println(lowerRot('r',-29));

    
    //Own testing for Part 1
    System.out.println("\n" + "Time for my own tests!");
    System.out.println("Score for 'Derp' = "+f.scrabbleScore("Derp") + " ...(should be 7)");
    System.out.println("Score for 'Derpette' = "+f.scrabbleScore("Derpette") + " ...(should be 11)");
    System.out.println("Score for 'Herpina' = "+f.scrabbleScore("Herpina") + " ...(should be 12)");
    
    
    //Part 2. The Caesar Cipher Tests
    System.out.println("\n");
    System.out.println("Encrypting Text: Cse8b: Programming in Java, Part 2");
    System.out.println(f.encrypt("Cse8b: Programming in Java, Part 2",17));
    System.out.println("Tjv8s: Gifxirddzex ze Armr, Grik 2");
    System.out.println("Decrypting Text: Byffi yhwlsjncih qilfx");
    System.out.println(f.decrypt("Byffi yhwlsjncih qilfx",-58));
    System.out.println("Hello encryption world");
    
    //Part 2. Encrypting and Decrypting Files
    System.out.println("\n");
    System.out.println("Encrypting and decrypting files");
    String testEncrypt = new String("testEncrypt.txt");
    String encrypted = new String("encrypted.txt");
    String testEncryptAnswer42 = new String("testEncryptAnswer42.txt");
    String decrypted = new String("decrypted.txt");
    
    f.encryptFile(testEncrypt, encrypted, 10);
    f.decryptFile(testEncryptAnswer42, decrypted, 42);
    
  }
}