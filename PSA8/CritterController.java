/*
 * Name: Amit Nijjar
 * Date: March 11,2014
 * PSA8
 */

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class CritterController extends JApplet implements Runnable
{
  
  /** Some constants that can be used throughout the class */
  private static final int RUNNER = 0;
  private static final int CHASER = 1;
  private static final int RANDOM = 2;
  private static final int CUSTOM = 3;
  private static final int NoCritter = -1;
  public int addVariable;
  public boolean isCritterSelected;
  public Chaser cha;
  public Runner run;
  public Random ran;
  public Custom cus;
  
  /** How quickly the simulation runs.  The longer the delay, 
    * the slower the simulation */
  private static final int DELAY = 50;
  
  // Instance varaibles that maintain various parts of the simulation 
  // YOU CAN (AND PROBABLY WILL NEED TO) ADD MORE INSTANCE VARIABLES HERE
  private ArrayList<Critter> critterList;  /** The Critters in the world */
  private Interactor actor; /** The Interactor object that controls which Critters interact */
  private boolean running;  /** Whether or not the simulation is running */
  private CritterPanel world; /** The custom JPanel that displays the Critters */
  private JLabel stateLabel;  /** The label at the top of the panel */
  private JLabel critLabel; /** The label next to the Critter buttons */
   
  public void init()
  {
    // Initialize the instance variables
    critterList = new ArrayList<Critter>();
    actor = new Interactor();
    running = false;
    
    // The overall layout is a BorderLayout
    setLayout( new BorderLayout() );
    
    // Add the CritterPanel in the middle
    // You will need to add a MouseListener to this panel.
    world = new CritterPanel();
    add(world, BorderLayout.CENTER);
    
    // Call a helper method which adds the buttons and label at the top.
    // YOU WILL LIKELY NEED TO ADD TO THE HELPER METHOD, so be sure to check it out.
    JPanel topPanel = makeTopPanel();
    add( topPanel, BorderLayout.NORTH );
    
    // Call a helper method which adds the buttons and label at the bottom.
    // YOU WILL LIKELY NEED TO ADD TO THE HELPER METHOD, so be sure to check it out.
    JPanel botPanel = makeBottomPanel();
    add( botPanel, BorderLayout.SOUTH );
    
    // Start the simulation
    new Thread( this ).start();
  }
  

  
  // A helper method to arrange the buttons and label at the top 
  // (i.e., the ones that control the simulation).  You will probably
  // need to add to this method, e.g. to add listeners on the buttons.
  private JPanel makeTopPanel()
  {
    JPanel topPanel = new JPanel();
    topPanel.setLayout( new GridLayout( 1, 2 ) );
    JPanel topButPanel = new JPanel();
    JButton start = new JButton( "Start" );
    JButton stop = new JButton( "Stop" );
    JButton clear = new JButton( "Clear" );
    //new listeners for the buttons
    EventListener buttonPressed = new EventListener();
    start.addActionListener(buttonPressed);
    stop.addActionListener(buttonPressed);
    clear.addActionListener(buttonPressed);
    
    
    stateLabel = new JLabel( "Please add two or more Critters" );
    topPanel.add( stateLabel );
    topButPanel.add( start );
    topButPanel.add( stop );
    topButPanel.add( clear );
    topPanel.add( topButPanel );
    
    

    
    
    return topPanel;
  }
  
  // A helper method to arrange the buttons and label at the bottom 
  // (i.e., the ones that control adding critters).  You will probably
  // need to add to this method, e.g. to add listeners on the buttons.
  private JPanel makeBottomPanel()
  {
    JPanel botPanel = new JPanel();
    JPanel botButPanel = new JPanel();
    botButPanel.setLayout( new GridLayout( 1, 4 ) );
    botPanel.setLayout( new GridLayout( 2, 1 ) );
    
    // You will need to create and add action listeners to these buttons
    JButton chase = new JButton( "Chaser" );
    JButton runner = new JButton( "Runner" );
    JButton rand = new JButton( "Random" );
    JButton custom = new JButton( "Custom" );
    //new listeners for the buttons
    EventListener buttonPressed = new EventListener();
    chase.addActionListener(buttonPressed);
    runner.addActionListener(buttonPressed);
    rand.addActionListener(buttonPressed);
    custom.addActionListener(buttonPressed);

    

    botButPanel.add( chase );
    botButPanel.add( runner );
    botButPanel.add( rand );
    botButPanel.add( custom );
    
    critLabel = new JLabel( "Select which Critter to place" );
    botPanel.add( critLabel );
    botPanel.add( botButPanel );
    
    PanelListener panelClicked = new PanelListener();
    world.addMouseListener(panelClicked);
    
    return botPanel;
  }
  
  /**
   * Implemented from the Runnable class -- runs when a thread is started.
   */
  // Note: This method won't do anything until you implement functionality in 
  // the Interactor.
  public void run()
  {
    try {
      while( true ) {
        if ( running ) {
          Rectangle r = new Rectangle(world.getSize().width,world.getSize().height);
          for ( int x = 0; x < critterList.size(); x++ )
          {
            Critter crit = (Critter) critterList.get(x);
            actor.interact( crit, critterList, r );
          }
          repaint();
        }
        if (!running){
        }
        Thread.sleep( DELAY );
      }
    }
    catch (InterruptedException ex) {
    }
          
  }
  
  /**
   * Panel that knows how to paint Critters (if they can paint themselves)
   */
  class CritterPanel extends JPanel
  {
    protected void paintComponent( Graphics g )
    {
      super.paintComponent( g );
      for ( int x = 0; x<critterList.size(); x++ )
      {
        Critter critter = (Critter) critterList.get(x);
        critter.paint( g );
      }
    }
  }
  // a class for the Critter buttons and the start, stop, and clear buttons
  class EventListener implements ActionListener
  {
    /*a method that will take action when a button is pressed
     * @param ActionEvent e - taken when a button is pushed
     * */
    public void actionPerformed ( ActionEvent e ) {
      //finds what the source of the JButton pressed is
      JButton source = (JButton)e.getSource();
      
      //if the start button is pressed, check for 2 critters and start program or request more critters
      if (source.getText().equals("Start")) {
        if (critterList.size() < 2){
          stateLabel.setText("Please add two or more Critters.");
          repaint();
          
        }
        if (critterList.size() > 1){
          stateLabel.setText("Simulation is running.");
          running = true;  
        }
        
     }
      //if the stop button is pressed, set running to false
      if (source.getText().equals("Stop")) {
        if (critterList.size() < 2){
          stateLabel.setText("Please add two or more Critters.");
          running = false;
          repaint();
        }
        if (critterList.size() > 1){
          stateLabel.setText("Simulation is stopped.");
          running = false;
          repaint();  
       }
        
      }
      //if the clear button is pressed, set running to false, and request more critters to be added
      if (source.getText().equals("Clear")) {
        running = false;
        critterList.clear(); 
        stateLabel.setText( "Please add two or more Critters." );
        repaint();
      }
      
      //if the chaser button is pressed, prepare to add a chaser
      if (source.getText().equals("Chaser")) {
         addVariable = CHASER;
         isCritterSelected = true;
    }
      
      //if the runner button is pressed, prepare to add a runner
      if (source.getText().equals("Runner")) {
         addVariable = RUNNER;
         isCritterSelected = true;
  }
      
      //if the random button is pressed, prepare to add a random
      if (source.getText().equals("Random")) {
         addVariable = RANDOM;
         isCritterSelected = true;
}
      
      //if the custom button is pressed, prepare to add a custom
      if (source.getText().equals("Custom")) {
         addVariable = CUSTOM;
         isCritterSelected = true;
      }
      
    }
  } 
  class PanelListener implements MouseListener
  {
    /*a method that will take action when the world is clicked
     * @param MouseEvent e - taken when the mouse is clicked
     * */
    public void mouseClicked( MouseEvent e ) {
      //sees if a critter is selected, if so, adds the critter at the location of the mouse click
      if (isCritterSelected == true){
        //adds the critter depending on its type
        if (addVariable == CHASER){
          cha = new Chaser(e.getX(),e.getY());
          critterList.add(cha);
          repaint();
        }
        if (addVariable == RUNNER){
          run = new Runner(e.getX(),e.getY());
          critterList.add(run);
          repaint();
        }
        if (addVariable == RANDOM){
          ran = new Random(e.getX(),e.getY());
          critterList.add(ran);
          repaint();
        }
        if (addVariable == CUSTOM){
          cus = new Custom(e.getX(),e.getY());
          critterList.add(cus);
          repaint();
        }
        //makes it so that only one critter gets added
        addVariable = NoCritter;
        //sets critter selection to false
        isCritterSelected = false;
      }
      
    }
    public void mouseReleased( MouseEvent e ) {}
    public void mouseEntered( MouseEvent e ) {}
    public void mouseExited( MouseEvent e ) {}
    public void mousePressed( MouseEvent e) {}
  }

  }


