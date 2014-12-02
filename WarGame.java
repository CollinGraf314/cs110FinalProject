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
public class WarGame extends JFrame
{
   public ArrayList<Card> deck = new ArrayList<Card>();
   public ArrayList<Card> player1deck = new ArrayList<Card>();
   public ArrayList<Card> player2deck = new ArrayList<Card>();
   private JPanel imagePanel;
   private JPanel imagePanel2;
   private JPanel imagePanelPlayers;
   private JPanel buttonPanel;
   private JLabel imageLabel;
   private JLabel imageLabel2;
   private JLabel imageLabelPlayers;
   private JButton button;
   private final int WINDOW_WIDTH = 800;
   private final int WINDOW_HEIGHT = 800;
   public WarGame()
   {
      makeDeck();
      setTitle("Game of War");
      setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLayout(new GridLayout(3,5));
      buildImagePanels();
      buildButtonPanel();
      add(imagePanel, BorderLayout.WEST);
      add(imagePanelPlayers, BorderLayout.CENTER);
      add(imagePanel2, BorderLayout.EAST);
      add(buttonPanel, BorderLayout.SOUTH);
      placeDecks();
      //pack();
      setVisible(true);
   }
   public void splitDeck()
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
   public void shuffleDeck()
   {
      Random rand = new Random();
      for(int x =0;x<1000;x++)
      {
         int tempInt = rand.nextInt(52);
         int tempInt2 = rand.nextInt(52);
         Card tempCard = deck.get(tempInt);
         Card tempCard2 = deck.get(tempInt2);
         deck.set(tempInt,tempCard2);
         deck.set(tempInt2,tempCard);
      }
   }
   private void placeDecks()
   {
      ImageIcon player1Deck = createImageIcon("cards\\back.jpg", "card back");
      imageLabel.setIcon(player1Deck);
      ImageIcon player2Deck = createImageIcon("cards\\back.jpg", "card back");
      imageLabel2.setIcon(player2Deck);
   }
   private void makeDeck()
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
   private void buildImagePanels()
   {
      imagePanel = new JPanel();
      imageLabel = new JLabel("Player 1's deck: "+player1deck.size()+" cards");
      imagePanel.add(imageLabel);
      imagePanel2 = new JPanel();
      imageLabel2 = new JLabel("Player 2's deck: "+player2deck.size()+" cards");
      imagePanel2.add(imageLabel2);
      imagePanelPlayers = new JPanel();
      imageLabelPlayers = new JLabel("cards at war");
      imagePanelPlayers.add(imageLabelPlayers);
      imagePanelPlayers = new JPanel();
      imagePanelPlayers.add(imageLabelPlayers);
   }
   private void buildButtonPanel()
   {
      buttonPanel = new JPanel();
      button = new JButton("Flip next card");
      button.addActionListener(new ButtonListener());
      buttonPanel.add(button);  
   }
   private class ButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         ImageIcon player1Card = createImageIcon((player1deck.get(0)).getJpeg(), "cards");
         imageLabel.setIcon(player1Card);
         imageLabel.setText("Player 1's deck: "+player1deck.size()+" cards");
         ImageIcon player2Card = createImageIcon((player2deck.get(0)).getJpeg(), "cards");
         imageLabel2.setIcon(player2Card);
         imageLabel2.setText("Player 2's deck: "+player2deck.size()+" cards");
         whoWon(0,0,1);
      }
   }
   public void whoWon(int p1index, int p2index, int warpot)
   {
      if((player1deck.get(p1index)).getValue() > (player2deck.get(p2index)).getValue())
         {
            for(int y=0; y<warpot; y++)
            {
               player1deck.add(player2deck.get(0));
               player2deck.remove(0);
               Card tmp = player1deck.get(0);
               player1deck.remove(0);
               player1deck.add(tmp);
            }
         }
         else if((player1deck.get(p1index)).getValue() < (player2deck.get(p2index)).getValue())
         {
            for(int q=0; q<warpot; q++)
            {
               player2deck.add(player1deck.get(0));
               player1deck.remove(0);
               Card tmp = player2deck.get(0);
               player2deck.remove(0);
               player2deck.add(tmp);
            }
         }
         else
         {
            whoWon(p1index+2,p2index+2,warpot+2);
         }
   }
   public static void main(String [] args)
   {
      new WarGame();
   }
   private ImageIcon createImageIcon(String path,String description) 
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
