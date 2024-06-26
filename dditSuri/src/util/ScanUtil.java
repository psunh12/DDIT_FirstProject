package util;

import java.util.Scanner;

public class ScanUtil {
   // 스캐너를 손쉽게 사용할 수 있는 static 메서드를 가지고있음
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
            System.err.print("숫자만 입력할 수 있습니다. \n다시 입력해주세요. >>");
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
               System.err.print("ID는 영문, 숫자만 입력 가능합니다. \n다시 입력해주세요 >>");
            }else {
               System.err.print("PW는 영문, 숫자만 입력 가능합니다. \n다시 입력해주세요 >>");
            }
         }
      }
   }

   public static String nextHangeul() {
      while(true) {
         String name = sc.nextLine();
         if(name.matches("^[가-힣]+$") && name.length() > 1) 
            return name;
         else {         
            System.err.print("정확한 한글 형식만 입력할 수 있습니다. \n다시 입력해주세요 >>");
         }
      }
   }
   public static String nextPhone() {
      while(true) {
         String name = sc.nextLine();
         if(name.matches("\\d{3}-\\d{3,4}-\\d{4}") && name.length() <= 13) 
            return name;
         else {
            System.err.print("유효한 형식이 아닙니다. \n다시 입력해주세요 >>");
         }
      }
   }
   
   public static String nextYN() {
      while(true) {
         String yesNo = sc.nextLine();
         if(yesNo.matches("^[y|Y|n|N]+$") && yesNo.length() < 2) 
            return yesNo;
         else {
            System.err.print("'Y/y' 또는 'n/N'만 입력 가능합니다. \n다시 입력해주세요 >>");
         }
      }
   }
   
   public static String nextDate() {
	      while(true) {
	         String name = sc.nextLine();
	         if(name.matches("\\d{4}-\\d{2}-\\d{2}") && name.length() == 10) 
	            return name;
	         else {
	            System.err.print("유효한 형식이 아닙니다. \n다시 입력해주세요 >>");
	         }
	      }
	   }
}   
