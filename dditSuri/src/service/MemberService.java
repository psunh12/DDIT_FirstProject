package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import controller.Controller;
import dao.MemberDAO;
import util.PrintUtil;
import util.ScanUtil;
import util.View;

public class MemberService {
   // 싱글톤 패턴 처리 위한 부분
   private static MemberService instance = null;
   private MemberService() {}
   public static MemberService getInstance() {
      if(instance == null) instance = new MemberService();
      return instance;
   }

   MemberDAO dao = MemberDAO.getInstance();

   //회원 등록
   public View signUp() {
	   PrintUtil.printbarlong();
		System.out.println("┌───────────────── [ 회원가입  ]───────────────────┐");
		System.out.println("│                                               │");
		System.out.println("│               해당 항목을 입력하세요.               │");
		System.out.println("│-----------------------------------------------│");

	   
	  String id = null;
      String pass = null;
      // 아이디 특수문자 입력했는지 체크 부분
      while(true) {
         System.out.print("  ● 아이디(ID) >> ");
         id = ScanUtil.nextIdPass("id").trim();
         //아이디 중복체크            
         Map<String, Object> res = dao.getMemberInfo(id);
         if (res != null ) {
            System.out.println("이미 사용되고 있는 ID입니다. 다른 ID를 입력하세요 ");
         } else if (id.length() >10){
            System.out.println("ID는 최대 10자까지 가능합니다. 다시 입력하세요.");
         }else break;
      }

      System.out.print("  ● 이름 >> ");
      String name = ScanUtil.nextHangeul().trim();
      System.out.print("  ● 전화번호(000-0000-0000) >> ");
      String phone = ScanUtil.nextPhone().trim();

      while(true) {
         System.out.print("  ● 비밀번호(PW) >> ");
         pass = ScanUtil.nextIdPass("pass").trim();
         if (pass.length() >20) {
            System.out.println("비밀번호는 최대 20자까지 가능합니다. \n다시 입력해주세요.");
         }else break;
      }

      List<Object> param = new ArrayList<Object>();
      param.add(id);
      param.add(pass);
      param.add(name);
      param.add(phone);

      int result = dao.signUp(param);
      if (result > 0) {
    	  
         System.out.println(" --회원 가입 성공! 로그인 해주세요.");
      }else {
         System.out.println(" --회원가입 실패");
      }
      System.out.println("└───────────────────────────────────────────────┘");
      PrintUtil.printbarlong();
      return View.HOME;
   }
   public View home() {

	   //수정함! 홈화면, 로그아웃
	   System.out.println("┌─────────────── [ 마이페이지 ] ─────────────────┐");
	   System.out.println("│                                             │");
	   System.out.println("│            원하시는 메뉴를 선택하세요.             │");
	   System.out.println("│---------------------------------------------│");
	   System.out.println("│ ┌─────────────────────────────────────────┐ │");
	   System.out.println("│ │   1.예약접수     2.예약조회/취소     999.로그아웃      │ │");
	   System.out.println("│ └─────────────────────────────────────────┘ │");
	   System.out.println("│                                             │");
	   System.out.println("└─────────────────────────────────────────────┘");

	   System.out.print("  ● 번호 입력(숫자) >> ");
	   switch (ScanUtil.nextInt()) {
	   
	   case 1: PrintUtil.printLine(0); return View.MEMBER_RESERVE;
	   case 2: PrintUtil.printLine(0); return View.MEMBER_LIST;
	   case 999: Controller.sessionStorage.clear();
	   return View.HOME;

	   default: 
		   System.out.println("화면에 출력된 번호(숫자)를 입력해주세요.");
		   return View.MEMBER;
	   }
   }
  

}