package util;

public class PrintUtil {

   public static void main(String[] args) {
      printf("|%8o|%10o|%6o|%-8o|", " ", 1000000, "성", "룡", "abc");
   }
   /**
    * System.out.printf()을 이용하면서 한글 공백처리에 대한 불편함을 개선한 메서드
    * 
    * @param format : System.out.printf() 에 입력하는 format과 비슷한 모양. 단, %{int}o 로 입력하여 정수, 문자열을 구분하지 않는다. 
    * 음수를 입력하면 좌측정렬, 양수를 입력하면 우측정렬이다.
    * @param spaceString : 빈 공간을 채워넣을 문자열 입력. null을 입력하면 기본값 " "이 들어간다.
    * @param args : format에서 %{int}o 부분을 채워줄 값을 순서대로 입력해야한다.
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

	   System.out.println("┌────────────────────────────────────────────────────┐");
	   System.out.println("│    ┌------*   ┌------*   ┌-------┐ ┌-------┐       │");
	   System.out.println("│    | ┌---\\ \\  | ┌---\\ \\  └--┐ ┌--┘ └--┐ ┌--┘       │");
	   System.out.println("│    | |    | | | |    | |    | |       | |          │");
	   System.out.println("│    | |    / / | |    / /    | |       | |          │");
	   System.out.println("│    | └---/ /  | └---/ /  ┌--┘-└--┐    | |          │");
	   System.out.println("│    └------/   └------/   └-------┘    └-┘          │");
	   System.out.println("│    ┌-------┐  ┌-┐   ┌-┐  ┌------*   ┌-------┐      │");
	   System.out.println("│    | ┌-----┘  | |   | |  | ┌----┐ \\ └--┐ ┌--┘      │");
	   System.out.println("│    | └-----┐  | |   | |  | └----/ /    | |         │");
	   System.out.println("│    └-----┐ |  | |   | |  | ┌---┐ /     | |         │");
	   System.out.println("│    ┌-----┘ |  | └---┘ |  | |   \\ \\  ┌--┘-└--┐      │");
	   System.out.println("│    └-------┘  └-------┘  └-┘    └-┘ └-------┘      │");
	   System.out.println("│   - 안녕하세요! 대덕수리예약시스템 입니다. 메뉴를 선택하세요 -   │");
	   System.out.println("│ ┌────────────────────────────────────────────────┐ │");
	   System.out.println("│ │  1. 로그인     │  2. 회원가입    │ 3.관리자/엔지니어 로그인   │ │");
	   System.out.println("│ └────────────────────────────────────────────────┘ │");
	   System.out.println("└────────────────────────────────────────────────────┘");
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
	   System.out.println("─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────"); 
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
         if(str.charAt(i) >= 'ㄱ' && str.charAt(i) <= '힇') {
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