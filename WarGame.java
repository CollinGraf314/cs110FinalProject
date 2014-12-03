/*
*Collin Graf
*cs110
*Final Project
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Random.*;
import javax.swing.JOptionPane.*;//all import statements
public class WarGame extends JFrame//extends the JFrame class for inheritence purposes
{
   public ArrayList<Card> deck = new ArrayList<Card>();//creates an arraylist that will hold all 52 cards of a deck
   public ArrayList<Card> player1deck = new ArrayList<Card>();//arraylist that will hold all of player1's cards
   public ArrayList<Card> player2deck = new ArrayList<Card>();//arraylist that will hold all of player2's cards
   private JPanel imagePanel;//spot to show player1's cards
   private JPanel imagePanel2;//spot to show player2's cards
   private JPanel imagePanelPlayers;//a spot to hold a textbox indicating who won the last round
   private JPanel buttonPanel;//spot to hold the screen's buttons
   private JLabel imageLabel;//labels player1's deck
   private JLabel imageLabel2;//labels player2's deck
   private JButton newGame;//creates the "new game" button
   private JLabel imageLabelPlayers;//creates the textbox indicating who won the last round
   private JButton button;//creates the "flip next card" button
   private final int WINDOW_WIDTH = 800;//sets the size of the screen in pixels
   private final int WINDOW_HEIGHT = 300;
   public WarGame()//no arguments constructor
   {
      makeDeck();//calls the method that creates the deck
      setTitle("Game of War");//sets the title in the bar at the top of the screen
      setSize(WINDOW_WIDTH, WINDOW_HEIGHT);//creates the frame's size
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//tells the program to terminate when the "x" button is pressed
      setLayout(new BorderLayout());//defines the layout for the frame
      buildImagePanels();//calls the method that impliments all of the image panels
      buildButtonPanel();//calls the method that impliments all of the buttons
      add(imagePanel, BorderLayout.WEST);//set the location of each panel in reference to the frame's layout
      add(imagePanelPlayers, BorderLayout.CENTER);
      add(imagePanel2, BorderLayout.EAST);
      add(buttonPanel, BorderLayout.SOUTH);
      Color green = new Color(0,200,0);//sets the background color to green
      imagePanel.setBackground(green);
      imagePanel2.setBackground(green);
      imagePanelPlayers.setBackground(green);
      buttonPanel.setBackground(green);
      placeDecks();//loads the images for the two players' decks into their respective spots on the screen
      setVisible(true);//makes the screen visible
   }
   public void splitDeck()//method that splits the 52 card deck in half and loads each half into each of the players' decks
   {
      player1deck.clear();
      player2deck.clear();
      for(int k=0; k<26;k++)
      {
         player1deck.add(deck.get(k));
      }
      for(int z=26; z<52; z++)
      {
         player2deck.add(deck.get(z));
      }
   }
   public void shuffleDeck()//method that shuffles all of the cards in the 52 card deck
   {
      Random rand = new Random();//calls the random class for a random shuffle
      for(int x =0;x<1000;x++)//makes 1000 random swaps in the deck to "shuffle" it
      {
         int tempInt = rand.nextInt(52);
         int tempInt2 = rand.nextInt(52);
         Card tempCard = deck.get(tempInt);
         Card tempCard2 = deck.get(tempInt2);
         deck.set(tempInt,tempCard2);
         deck.set(tempInt2,tempCard);
      }
   }
   private void placeDecks()//loads the image of the back of a card to where each player deck goes
   {
      ImageIcon player1Deck = createImageIcon("cards\\back.jpg", "card back");
      imageLabel.setIcon(player1Deck);
      ImageIcon player2Deck = createImageIcon("cards\\back.jpg", "card back");
      imageLabel2.setIcon(player2Deck);
   }
   private void makeDeck()//method that creates 52 unique Card objects and loads them into the "deck" arraylist.
   //any time the deck is made, it is automatically shuffled and split as well.
   {
      Card temp;
      for(int i=2; i<11; i++)
      {
         temp = new Card("cards\\"+i+"c.jpg",i);
         deck.add(temp);
         temp = new Card("cards\\"+i+"h.jpg",i);
         deck.add(temp);
         temp = new Card("cards\\"+i+"d.jpg",i);
         deck.add(temp);
         temp = new Card("cards\\"+i+"s.jpg",i);
         deck.add(temp);
      }
      temp = new Card("cards\\jackc.jpg",11);
      deck.add(temp);
      temp = new Card("cards\\jackh.jpg",11);
      deck.add(temp);
      temp = new Card("cards\\jackd.jpg",11);
      deck.add(temp);
      temp = new Card("cards\\jacks.jpg",11);
      deck.add(temp);
      temp = new Card("cards\\queenc.jpg",12);
      deck.add(temp);
      temp = new Card("cards\\queenh.jpg",12);
      deck.add(temp);
      temp = new Card("cards\\queend.jpg",12);
      deck.add(temp);
      temp = new Card("cards\\queens.jpg",12);
      deck.add(temp);
      temp = new Card("cards\\kingc.jpg",13);
      deck.add(temp);
      temp = new Card("cards\\kingh.jpg",13);
      deck.add(temp);
      temp = new Card("cards\\kingd.jpg",13);
      deck.add(temp);
      temp = new Card("cards\\kings.jpg",13);
      deck.add(temp);
      temp = new Card("cards\\acec.jpg",14);
      deck.add(temp);
      temp = new Card("cards\\aceh.jpg",14);
      deck.add(temp);
      temp = new Card("cards\\aced.jpg",14);
      deck.add(temp);
      temp = new Card("cards\\aces.jpg",14);
      deck.add(temp);
      shuffleDeck();
      splitDeck();
   }
   private void buildImagePanels()//method that implements all three of the image panels and labels
   {
      imagePanel = new JPanel();
      imageLabel = new JLabel("Player 1's deck: "+player1deck.size()+" cards");
      imagePanel.add(imageLabel);
      imagePanel2 = new JPanel();
      imageLabel2 = new JLabel("Player 2's deck: "+player2deck.size()+" cards");
      imagePanel2.add(imageLabel2);
      imagePanelPlayers = new JPanel();
      imageLabelPlayers = new JLabel("Round by Round Breakdown");
      imagePanelPlayers.add(imageLabelPlayers);
   }
   private void buildButtonPanel()//method that implements the two buttons
   {
      buttonPanel = new JPanel();
      button = new JButton("Flip next card");
      button.addActionListener(new ButtonListener());
      buttonPanel.add(button); 
      newGame = new JButton("New Game");
      newGame.addActionListener(new ButtonListener2());
      buttonPanel.add(newGame); 
   }
   private class ButtonListener implements ActionListener//first ActionListener class, built to listen to the "flip next card" button
   {
      public void actionPerformed(ActionEvent e)
      {
         ImageIcon player1Card = createImageIcon((player1deck.get(0)).getJpeg(), "cards");//shows the next card in player1's deck
         imageLabel.setIcon(player1Card);
         imageLabel.setText("Player 1's deck: "+player1deck.size()+" cards");//tracks the amount of cards in player1's deck
         ImageIcon player2Card = createImageIcon((player2deck.get(0)).getJpeg(), "cards");//shows the next card in player2's deck
         imageLabel2.setIcon(player2Card);
         imageLabel2.setText("Player 2's deck: "+player2deck.size()+" cards");//tracks the amount of cards in player2's deck
         if(player1deck.size() < 1)//checks to make sure neither deck is empty before playing through a round
         {
            JOptionPane.showMessageDialog(null,"Player 2 won!");
         }
         else if(player2deck.size() < 1)
         {
            JOptionPane.showMessageDialog(null,"Player 1 won!");
         }
         else
         {
            whoWon(0,0,1);//method that plays a round
         }
      }
   }
   private class ButtonListener2 implements ActionListener//second ActionListener class, built to listen to the "new game" button
   {
      public void actionPerformed(ActionEvent e)
      {
         placeDecks();//defaults the top cards on each deck to be face down to start
         deck.clear();//clears the old deck
         makeDeck();//creates a new deck, shuffles it, and splits it into two equal players' decks
         imageLabel.setText("Player 1's deck: "+player1deck.size()+" cards");//tracks the amount of cards in each player's deck
         imageLabel2.setText("Player 2's deck: "+player2deck.size()+" cards");
      }
   }
   /*
   *@param int p1index  tracks the index of the card in player1's deck that is being compared
   *@param int p2index  tracks the index of the card in player2's deck that is being compared
   *@param int warpot   tracks the amount of cards that can be won in this round
   */
   public void whoWon(int p1index, int p2index, int warpot)//simulates a round of War
   {
      if(player2deck.size() > p2index && player1deck.size() > p1index)//makes sure neither deck runs out of cards in a war
      {
         if((player1deck.get(p1index)).getValue() > (player2deck.get(p2index)).getValue())//determines who won the war
            {
               for(int y=0; y<warpot; y++)//transfers all of the winnings from one player's deck to the other's
               {
                  if(player2deck.size() > 1 && player1deck.size() > 1)
                  {
                     player1deck.add(player2deck.get(0));
                     player2deck.remove(0);
                     Card tmp = player1deck.get(0);
                     player1deck.remove(0);
                     player1deck.add(tmp);
                  }
                  else
                  {
                     JOptionPane.showMessageDialog(null,"Player 1 won!");
                  }
               }
               imageLabelPlayers.setText("This round goes to player 1.");
            }
            else if((player1deck.get(p1index)).getValue() < (player2deck.get(p2index)).getValue())
            {
               for(int q=0; q<warpot; q++)
               {
                  if(player1deck.size() > 1 && player2deck.size() > 1)
                  {
                     player2deck.add(player1deck.get(0));
                     player1deck.remove(0);
                     Card tmp = player2deck.get(0);
                     player2deck.remove(0);
                     player2deck.add(tmp);
                  }
                  else
                  {
                     JOptionPane.showMessageDialog(null,"Player 2 won!");
                  }
               }
               imageLabelPlayers.setText("This round goes to player 2.");
            }
            else//if the round is a tie, then a war happens
            {
               whoWon(p1index+2,p2index+2,warpot+2);//recursive method that figures out the winner of the war and awards them.
            }
         }
         else//if a player runs out of cards and their deck is empty, the game stops and a pop-up with the victorious player appears
         {
            if(player1deck.size()>player2deck.size())
            {
               JOptionPane.showMessageDialog(null,"Player 1 won!");
            }
            else
            {
               JOptionPane.showMessageDialog(null,"Player 2 won!");
            }
         }
   }
   public static void main(String [] args)//makes the class runable and starts the game
   {
      new WarGame();
   }
   /*
   *@param String path  the URL of an image file
   *@param String description a description of the intended image
   *
   *@return ImageIcon   returns part of an image frame that is loadable onto a GUI
   */
   private ImageIcon createImageIcon(String path,String description)//easy way to display images on a GUI
   {
      java.net.URL imgURL = getClass().getResource(path);
      if (imgURL != null) 
      {
         return new ImageIcon(imgURL, description);
      } 
      else 
      {
        System.err.println("Couldn't find file: " + path);
        return null;
      }
   }
}
