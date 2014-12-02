/*
*Collin Graf
*cs110
*Final Project
*/
public class Card
{
   private String jpeg;
   private int value;
   public final int JACK = 11;
   public final int QUEEN = 12;
   public final int KING = 13;
   public final int ACE = 14;
   public Card(String j, int v)  
   {
      jpeg = j;
      value = v;
   }
   public void setJpeg(String j)
   {
      jpeg = j;
   }
   public String getJpeg()
   {
      return jpeg;
   }
   public void setValue(int v)
   {
      value = v;
   }
   public int getValue()
   {
      return value;
   }
}