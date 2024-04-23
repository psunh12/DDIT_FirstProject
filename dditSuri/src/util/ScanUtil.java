package util;

import java.util.Scanner;

public class ScanUtil {
   // ��ĳ�ʸ� �ս��� ����� �� �ִ� static �޼��带 ����������
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
            System.err.print("���ڸ� �Է��� �� �ֽ��ϴ�. \n�ٽ� �Է����ּ���. >>");
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
               System.err.print("ID�� ����, ���ڸ� �Է� �����մϴ�. \n�ٽ� �Է����ּ��� >>");
            }else {
               System.err.print("PW�� ����, ���ڸ� �Է� �����մϴ�. \n�ٽ� �Է����ּ��� >>");
            }
         }
      }
   }

   public static String nextHangeul() {
      while(true) {
         String name = sc.nextLine();
         if(name.matches("^[��-�R]+$") && name.length() > 1) 
            return name;
         else {         
            System.err.print("��Ȯ�� �ѱ� ���ĸ� �Է��� �� �ֽ��ϴ�. \n�ٽ� �Է����ּ��� >>");
         }
      }
   }
   public static String nextPhone() {
      while(true) {
         String name = sc.nextLine();
         if(name.matches("\\d{3}-\\d{3,4}-\\d{4}") && name.length() <= 13) 
            return name;
         else {
            System.err.print("��ȿ�� ������ �ƴմϴ�. \n�ٽ� �Է����ּ��� >>");
         }
      }
   }
   
   public static String nextYN() {
      while(true) {
         String yesNo = sc.nextLine();
         if(yesNo.matches("^[y|Y|n|N]+$") && yesNo.length() < 2) 
            return yesNo;
         else {
            System.err.print("'Y/y' �Ǵ� 'n/N'�� �Է� �����մϴ�. \n�ٽ� �Է����ּ��� >>");
         }
      }
   }
   
   public static String nextDate() {
	      while(true) {
	         String name = sc.nextLine();
	         if(name.matches("\\d{4}-\\d{2}-\\d{2}") && name.length() == 10) 
	            return name;
	         else {
	            System.err.print("��ȿ�� ������ �ƴմϴ�. \n�ٽ� �Է����ּ��� >>");
	         }
	      }
	   }
}   
