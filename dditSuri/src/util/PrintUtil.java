package util;

public class PrintUtil {

   public static void main(String[] args) {
      printf("|%8o|%10o|%6o|%-8o|", " ", 1000000, "��", "��", "abc");
   }
   /**
    * System.out.printf()�� �̿��ϸ鼭 �ѱ� ����ó���� ���� �������� ������ �޼���
    * 
    * @param format : System.out.printf() �� �Է��ϴ� format�� ����� ���. ��, %{int}o �� �Է��Ͽ� ����, ���ڿ��� �������� �ʴ´�. 
    * ������ �Է��ϸ� ��������, ����� �Է��ϸ� ���������̴�.
    * @param spaceString : �� ������ ä������ ���ڿ� �Է�. null�� �Է��ϸ� �⺻�� " "�� ����.
    * @param args : format���� %{int}o �κ��� ä���� ���� ������� �Է��ؾ��Ѵ�.
    */
   public static void printfln(String format, String spaceString, Object ... args) {
      if(spaceString == null) spaceString = " ";
      String str = "";
      int idx = 0;
      for(int i = 0; i < format.length(); i++) {
         char c = format.charAt(i);
         String f = "";
         if(c == '%') {
            c = format.charAt(++i);
            while(c != 'o' && i < format.length()-1) {
               f += c;
               c = format.charAt(++i);
            }
            str += formater(args[idx++].toString(), Integer.parseInt(f), spaceString);
         }else {
            str += c;
         }
      }
      System.out.println(str);
   }
   

   public static void printhome() {

	   System.out.println("������������������������������������������������������������������������������������������������������������");
	   System.out.println("��    ��------*   ��------*   ��-------�� ��-------��       ��");
	   System.out.println("��    | ��---\\ \\  | ��---\\ \\  ��--�� ��--�� ��--�� ��--��       ��");
	   System.out.println("��    | |    | | | |    | |    | |       | |          ��");
	   System.out.println("��    | |    / / | |    / /    | |       | |          ��");
	   System.out.println("��    | ��---/ /  | ��---/ /  ��--��-��--��    | |          ��");
	   System.out.println("��    ��------/   ��------/   ��-------��    ��-��          ��");
	   System.out.println("��    ��-------��  ��-��   ��-��  ��------*   ��-------��      ��");
	   System.out.println("��    | ��-----��  | |   | |  | ��----�� \\ ��--�� ��--��      ��");
	   System.out.println("��    | ��-----��  | |   | |  | ��----/ /    | |         ��");
	   System.out.println("��    ��-----�� |  | |   | |  | ��---�� /     | |         ��");
	   System.out.println("��    ��-----�� |  | ��---�� |  | |   \\ \\  ��--��-��--��      ��");
	   System.out.println("��    ��-------��  ��-------��  ��-��    ��-�� ��-------��      ��");
	   System.out.println("��   - �ȳ��ϼ���! �����������ý��� �Դϴ�. �޴��� �����ϼ��� -   ��");
	   System.out.println("�� ���������������������������������������������������������������������������������������������������� ��");
	   System.out.println("�� ��  1. �α���     ��  2. ȸ������    �� 3.������/�����Ͼ� �α���   �� ��");
	   System.out.println("�� ���������������������������������������������������������������������������������������������������� ��");
	   System.out.println("������������������������������������������������������������������������������������������������������������");
	   System.out.println("======================================================================================================================");
	
} 
   
   
   
   public static void printLine(int n) {
	   for(int i = 0; i<=n; i++) {
		   System.out.println();
	   }
   }
   
   public static void printbar() {
	   System.out.println("===================================================");   
	      }

   public static void printbar2() {
	   System.out.println("������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������"); 
   }
   public static void printbarlong() {
   System.out.println("======================================================================================================================");
   }
   
   
   public static void printf(String format, String spaceString, Object ... args) {
      if(spaceString == null) spaceString = " ";
      String str = "";
      int idx = 0;
      for(int i = 0; i < format.length(); i++) {
         char c = format.charAt(i);
         String f = "";
         if(c == '%') {
            c = format.charAt(++i);
            while(c != 'o' && i < format.length()-1) {
               f += c;
               c = format.charAt(++i);
            }
            str += formater(args[idx++].toString(), Integer.parseInt(f), spaceString);
         }else {
            str += c;
         }
      }
      System.out.print(str);
   }
   
   private static String formater(String str, int length, String spaceString) {
      String result = "";
      int s = Math.abs(length);
      for(int i = 0; i < str.length(); i++) {
         if(str.charAt(i) >= '��' && str.charAt(i) <= 'Ş') {
            s -= 2;
         }else {
            s--;
         }
      }
      if(length < 0) {
         result += str;
         result = setSpace(result, s, spaceString);
      }else {
         result = setSpace(result, s, spaceString);
         result += str;
      }
      return result;
   }
   
   private static String setSpace(String str, int count, String spaceString) {
      for(int i = 0; i < count; i++) {
         str += spaceString;
      }
      return str;
   }
}