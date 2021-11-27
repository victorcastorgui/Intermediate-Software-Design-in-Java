import javax.swing.*;
import java.awt.*;
   
public class FoothillPhase1
{
   // static for the 57 icons and their corresponding labels
   // normally we would not have a separate label for each card, but
   // if we want to display all at once using labels, we need to.
   
   static int NUM_CARD_IMAGES = 57; // 52 + 4 jokers + 1 back-of-card image
   static Icon[] icon = new ImageIcon[NUM_CARD_IMAGES];
   static JLabel[] labels = new JLabel[NUM_CARD_IMAGES];

   // for assisting with conversions:
   static String cardlValsConvertAssist = "23456789TJQKAX";
   static String suitValsConvertAssist = "CDHS";
   
   static void loadIcons()
   {
      String imageFileName;
      int intSuit, intVal;

      for (intSuit = 0; intSuit < 4; intSuit++)
         for (intVal = 0; intVal < 14; intVal++ )
         {
            // card image files stored in Foothill/images folder with names like
            // "AC.gif", "3H.gif","XD.gif", etc.
            imageFileName = "images/"
                  + turnIntIntoCardValueChar(intVal) 
                  + turnIntIntoCardSuitChar(intSuit)
                  + ".gif";
            icon[intSuit*14 + intVal] = new ImageIcon(imageFileName);
         }
      imageFileName = "images/BK.gif";
      icon[icon.length - 1] = new ImageIcon(imageFileName);
   }
   
   // turns 0 - 13 into 'A', '2', '3', ... 'Q', 'K', 'X'
   static char turnIntIntoCardValueChar(int k)
   {
   
      if ( k < 0 || k > 13)
         return '?'; 
      return cardlValsConvertAssist.charAt(k);
   }
   
   // turns 0 - 3 into 'C', 'D', 'H', 'S'
   static char turnIntIntoCardSuitChar(int k)
   {
      if ( k < 0 || k > 3)
         return '?'; 
      return suitValsConvertAssist.charAt(k);
   }
   
   public static void main(String[] args)
   {
      int k;
      
      // prepare the image icon array
      loadIcons();
      
      // establish main frame in which program will run
      JFrame frmMyWindow = new JFrame("Card Room");
      frmMyWindow.setSize(1150, 650);
      frmMyWindow.setLocationRelativeTo(null);
      frmMyWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      // set up layout which will control placement of buttons, etc.
      FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 5, 20);   
      frmMyWindow.setLayout(layout);
      
      // prepare the image label array
      JLabel[] labels = new JLabel[NUM_CARD_IMAGES];
      for (k = 0; k < NUM_CARD_IMAGES; k++)
         labels[k] = new JLabel(icon[k]);
      
      // place your 3 controls into frame
      for (k = 0; k < NUM_CARD_IMAGES; k++)
         frmMyWindow.add(labels[k]);

      // show everything to the user
	      frmMyWindow.setVisible(true);
   }
}
