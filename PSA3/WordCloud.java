/**
 * Name: Alex Luu A11632514; Amit Nijjar A1489111
 * Date: 1/23/14
 * PSA3
 * 
 **/
// Don't remove these lines.  They tell java where to find various classes
// you will use.
import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.*;

/** Don't forget your comments */

public class WordCloud
{
  // These are the two instance variables that you will use to keep 
  // track of the unique words in the file and their counts
  
  /** An array of unique strings, to appear in the word cloud */
  public String[] uniqueWords;
  
  
  /** counts corresponding to the number of times each word in the 
    * uniqueStrings array appeard in the original file.  These counts
    * will be used to size the words in the word cloud.  */
  public int[] counts;
  
  /**
 * returns a string of words from a file
 * @param filename: the file we are reading
 * @return: a string array of words from the file
 */
  public String[] getWordsFromFile( String filename ) throws IOException
  {
    File sourceFile = new File(filename); //declaring variable for file
    Scanner input = new Scanner (sourceFile);
    String space = ""; //creating a target string for method
    while ( input.hasNext()) //inputing words from file into target string
    {
      String temp = input.nextLine();
      space = space.concat(temp + " ");
    }
    
    input.close();
    String[] splitted = space.split(" "); //splitting the string into a string array
    Arrays.sort(splitted); //sorting the array
    return splitted;
  }
  
  /**
 * assigns each unique word with a unique number value
 * @param: string array of words to be assigned numbers
 * @returh: returns the number array for each word
 */
  public static int[] givesWordsNumValue(String[] stringArr) { //helper
    int wordOrder = 0; //declaring variables
    int[] intCount = new int[stringArr.length];
    
    for(int i = 0; i < stringArr.length-1; i++){ //looping through the string array
      intCount[i] = wordOrder;
      if ((stringArr[i].equals(stringArr[i+1])) == false){ //checking if two consecurive words are the same
        wordOrder++;
      }
      intCount[stringArr.length-1] = wordOrder; //assigns number value to the last word in the array
    }
    return intCount;
  }
  
  /**
 * gives us the number of unique words
 * @param: string array of words to be counted for unique words
 * @returh: returns the number of unique words
 */
  public static int givesUniqueWordsLength(String[] stringArr) {  //helper
    int wordOrder = 0; //declaring variables
    int[] intCount = new int[stringArr.length];
    int uniqueCount = 1;
     
    for(int i = 0; i < stringArr.length-1; i++){ //looping thourhg the string array
      intCount[i] = wordOrder;
      if ((stringArr[i].equals(stringArr[i+1])) == false){ //chetcking if two consecutive words are the same
        wordOrder++;
        uniqueCount++; //adds to the unique count if they are not the same
      }
      intCount[stringArr.length-1] = wordOrder;
    }
    return uniqueCount;
  }
  
  /**
 * gives us the count for each unique word
 * @param intArr: int array of words to be counted for unique words
 * @param stringLength: the length to make the target array
 * @returh: returns the count for each unique word
 */
  public static int[] countingUniqueWords(int[] intArr, int stringLength) {  //helper
    int[] stringCounter = new int[stringLength]; //declaring variables
    int intCountCounter = 0; //number of unique words
    int intRepetition = 0; //how many times a number count comes up
    int countArrayLocation = 0; //location in the array
    
    for (int i = 0; i < intArr.length; i++) { //looping through the int array
      if(intArr[i] == intCountCounter){ //if the consecutive words are the same, increase the counter
        intRepetition++;  
      }
      else if (intArr[i] != intCountCounter){ //sets the counter to be the number of times a word is repeated
        stringCounter[countArrayLocation] = intRepetition+1; 
        intRepetition=0;
        countArrayLocation++;
        intCountCounter++;
      }
    }
    if (intArr[0] == intArr[1]){ //for dealing with the first element in the int array
      stringCounter[0] = stringCounter[0]-1;
    }
    stringCounter[stringCounter.length-1] = stringCounter[stringCounter.length-1]+1;  //for dealing with the last element in the int array
    return stringCounter;
  }
  
  /**
 * gives us the string of unique words
 * @param wordArr: string array of words to find unique words
 * @param stringLength: the length of the target array
 * @returh: returns the string array of unique words
 */
  public static String[] givesUniqueString( String[] wordArr, int stringLength) { //helper
    String[] uniqueString = new String[stringLength];
    int j = 0;
    uniqueString[uniqueString.length-1] = wordArr[wordArr.length-1]; //for dealing with the last element in the string array
    
    for (int i = 0; i < stringLength-1; ) //loops through the string array
    { 
      if((wordArr[j].equals(wordArr[j+1])) == true) { //checks if the consecutive words are the same and then adds to a counter
          j++;
        }
        else { 
          uniqueString[i] = wordArr[j];
          i++;
          j++;
        }      
      
    }
    return uniqueString;
  }
    
  
  /**
 * Converts the calling string array into the uniqueWords array and the counts for each word
 * @param words: string array of words to be converted into a uniqueWords array
 */
  public void setUniqueAndCounts( String[] words )
  {
    int[] numberOfWords = givesWordsNumValue(words);
    int numberOfUniques = givesUniqueWordsLength(words);
    int[] countResult = countingUniqueWords(numberOfWords, numberOfUniques);
    String[] stringResult = givesUniqueString(words, numberOfUniques);
  
    this.uniqueWords = stringResult;
    this.counts = countResult;
  }
 
  
  /**
 * Converts the uniqueWords array and the counts for each word and puts it into a word cloud
 * @param arr: string array of unique words to put into the word cloud
 * @param count: the counts for each unique words that will determine their size in the word cloud
 */
  public void displayWords(String[] arr, int[] count)
  {
   JFrame frame = new JFrame("KnockKnock"); //declaring variables
   frame.setSize(200,200);
   frame.setVisible(true);
   FlowLayout flow = new FlowLayout ( FlowLayout.CENTER );
   frame.setLayout(flow);
   
   for (int i = 0; i < arr.length; i++){ //loops through the string array
     JLabel label = new JLabel(arr[i]); //makes a new label for each word in the string array
     Font labelFont = new Font(arr[i], Font.PLAIN, count[i]*10); //makes a font object
     label.setFont(labelFont); //sets the font
     frame.add(label);
  }
   frame.pack();
   frame.setVisible(true);
  }
  
  
  /**
 * Makes a word cloud
 * @param filename: the file to read that converts those words into a word cloud
 */
  public void makeCloud( String filename ) throws IOException
  {
    String[] theString = this.getWordsFromFile(filename);
    this.setUniqueAndCounts(theString);
    this.displayWords(this.uniqueWords, this.counts);  
  }
  
  public static void main(String [] args) throws IOException {
    
    WordCloud derp = new WordCloud();
    String[] test = derp.getWordsFromFile("KnockKnock.txt");
    derp.setUniqueAndCounts(test);
    System.out.println(Arrays.toString(derp.counts));
    System.out.println(Arrays.toString(derp.uniqueWords));

    // derp.displayWords(derp.uniqueWords, derp.counts);
    derp.makeCloud("KnockKnock.txt");
    
}
}

