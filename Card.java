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
   /*
   *@param  String j String with the location of a jpg image
   *@param  int v    integer value of the card
   */
   public Card(String j, int v)  
   {
      jpeg = j;
      value = v;
   }
   /*
   *@param  String j String with the location of a jpg image
   */

   public void setJpeg(String j)
   {
      jpeg = j;
   }
   /*
   *@return  String  path to a .jpg file
   */
   public String getJpeg()
   {
      return jpeg;
   }
   /*
   *@param  int v integer value of the card
   */
   public void setValue(int v)
   {
      value = v;
   }
   /*
   *@return  int  integer value of the card
   */
   public int getValue()
   {
      return value;
   }
}