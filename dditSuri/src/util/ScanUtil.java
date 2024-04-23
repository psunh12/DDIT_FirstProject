package util;

import java.util.Scanner;

public class ScanUtil {
   // ½ºÄ³³Ê¸¦ ¼Õ½±°Ô »ç¿ëÇÒ ¼ö ÀÖ´Â static ¸Þ¼­µå¸¦ °¡Áö°íÀÖÀ½
   static Scanner sc = new Scanner(System.in);
   public static String nextLine() {
      return sc.nextLine();
   }
   public static int nextInt() {
      while(true) {
         try {
            int result = Integer.parseInt(sc.nextLine());
            return result;
         }catch (NumberFormatException e) {
            System.err.print("¼ýÀÚ¸¸ ÀÔ·ÂÇÒ ¼ö ÀÖ½À´Ï´Ù. \n´Ù½Ã ÀÔ·ÂÇØÁÖ¼¼¿ä. >>");
         }
      }
   }
   

   public static String nextIdPass(String input) {
      String patternNum = "^[a-zA-Z0-9]+$"; 
      while(true) {
         String idpass = sc.nextLine();
         if(idpass.matches(patternNum)) 
            return idpass;
         else {
            if (input.equals("id")) {
               System.err.print("ID´Â ¿µ¹®, ¼ýÀÚ¸¸ ÀÔ·Â °¡´ÉÇÕ´Ï´Ù. \n´Ù½Ã ÀÔ·ÂÇØÁÖ¼¼¿ä >>");
            }else {
               System.err.print("PW´Â ¿µ¹®, ¼ýÀÚ¸¸ ÀÔ·Â °¡´ÉÇÕ´Ï´Ù. \n´Ù½Ã ÀÔ·ÂÇØÁÖ¼¼¿ä >>");
            }
         }
      }
   }

   public static String nextHangeul() {
      while(true) {
         String name = sc.nextLine();
         if(name.matches("^[°¡-ÆR]+$") && name.length() > 1) 
            return name;
         else {         
            System.err.print("Á¤È®ÇÑ ÇÑ±Û Çü½Ä¸¸ ÀÔ·ÂÇÒ ¼ö ÀÖ½À´Ï´Ù. \n´Ù½Ã ÀÔ·ÂÇØÁÖ¼¼¿ä >>");
         }
      }
   }
   public static String nextPhone() {
      while(true) {
         String name = sc.nextLine();
         if(name.matches("\\d{3}-\\d{3,4}-\\d{4}") && name.length() <= 13) 
            return name;
         else {
            System.err.print("À¯È¿ÇÑ Çü½ÄÀÌ ¾Æ´Õ´Ï´Ù. \n´Ù½Ã ÀÔ·ÂÇØÁÖ¼¼¿ä >>");
         }
      }
   }
   
   public static String nextYN() {
      while(true) {
         String yesNo = sc.nextLine();
         if(yesNo.matches("^[y|Y|n|N]+$") && yesNo.length() < 2) 
            return yesNo;
         else {
            System.err.print("'Y/y' ¶Ç´Â 'n/N'¸¸ ÀÔ·Â °¡´ÉÇÕ´Ï´Ù. \n´Ù½Ã ÀÔ·ÂÇØÁÖ¼¼¿ä >>");
         }
      }
   }
   
   public static String nextDate() {
	      while(true) {
	         String name = sc.nextLine();
	         if(name.matches("\\d{4}-\\d{2}-\\d{2}") && name.length() == 10) 
	            return name;
	         else {
	            System.err.print("À¯È¿ÇÑ Çü½ÄÀÌ ¾Æ´Õ´Ï´Ù. \n´Ù½Ã ÀÔ·ÂÇØÁÖ¼¼¿ä >>");
	         }
	      }
	   }
}   
