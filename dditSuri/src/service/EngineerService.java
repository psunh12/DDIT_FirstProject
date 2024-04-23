package service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import controller.Controller;
import dao.EngineerDAO;
import dao.ReserveServiceDAO;
import util.ScanUtil;
import util.PrintUtil;
import util.View;

public class EngineerService {
   // 싱글톤 패턴을 만든다.
   private static EngineerService instance = null;
   private EngineerService() {}
   public static EngineerService getInstance() {
      if(instance == null) 
         instance = new EngineerService();
      return instance;
   }
   // 로그인한 엔지니어의 tb_engineer 정보가 담겨있음
   public Map<String, Object> engInfo;

   EngineerDAO dao = EngineerDAO.getInstance();
   ReserveServiceDAO reserdao = ReserveServiceDAO.getInstance();

   int engorsys;

   public View login(){      
      System.out.print("  ● 관리자/enginner ID 입력 >> ");
      String id = ScanUtil.nextLine();
      Map<String, Object> engIdcheck = dao.getEngineerInfo("ENG_ID",id);
      if (engIdcheck == null) { 
         System.out.println("   등록된 정보가 없습니다. 관리자에게 문의하세요.");
         return View.ENGANDSYS;
      }
      while(true) {
         System.out.print("  ● 비밀번호(PW) 입력 >> ");
         String pass = ScanUtil.nextLine();

         // 입력 받은 로그인 정보로 회원 정보 맞는지 조회
         Map<String, Object> result = dao.login(id,pass);
         if(result != null && result.get("ENG_ID").equals(id)){
            Controller.sessionStorage.put("login", true);
            Controller.sessionStorage.put("loginInfo", result.get("ENG_ID"));

            if (result.get("ENG_ADMIN").equals("N")) {
               PrintUtil.printbarlong();
               System.out.println("-- "+result.get("ENG_NAME") + " 엔지니어님! 반갑습니다!");
               System.out.println();
               return View.ENGSYSMAIN;
            }else  {
               PrintUtil.printbarlong();
               System.out.println("-- "+result.get("ENG_NAME") + " 관리자님! 반갑습니다!");
               System.out.println();
               return View.ENGINEER_LIST;
            }
         }   
         else{
            System.out.println("ID와 PW가 일치하지 않습니다. PW를 다시 입력해주세요.");
         }
      }
   }

   public View engsys_main(){      
      this.engInfo = dao.getEngineerInfo("ENG_ID", (String)Controller.sessionStorage.get("loginInfo"));
      if (engInfo.get("ENG_ADMIN").toString().equals("Y")) {
      	   System.out.println("┌─────────────────[ 관 리 자 홈 ]─────────────────┐");
       	   System.out.println("│                                             │");
       	   System.out.println("│            원하시는 메뉴를 선택하세요.             │");
       	   System.out.println("│---------------------------------------------│");
       	   System.out.println("│ ┌─────────────────────────────────────────┐ │");
       	   System.out.println("│ │     1.엔지니어/관리자 조회       999.로그아웃           │ │");
       	   System.out.println("│ └─────────────────────────────────────────┘ │");
       	   System.out.println("│                                             │");
       	   System.out.println("└─────────────────────────────────────────────┘");
       	   System.out.print("  ● 번호 입력(숫자) >> ");

//         System.out.println("===========================================");
//         System.out.println("                     1.엔지니어 관리 999.로그아웃");
//         System.out.println("===========================================");
//         System.out.print("번호 입력 >> ");
         switch (ScanUtil.nextInt()) {
         case 1: return View.ENGINEER_MY;
         case 999: 
            Controller.sessionStorage.clear();
            return View.HOME;
         default: 
            System.err.println("화면에 출력된 번호(숫자)를 입력해주세요.");
            return View.ENGSYSMAIN;
         }
      }else {
    	  
   	   System.out.println("┌─────────────── [엔지니어 홈  ] ─────────────────┐");
   	   System.out.println("│                                             │");
   	   System.out.println("│            원하시는 메뉴를 선택하세요.             │");
   	   System.out.println("│---------------------------------------------│");
   	   System.out.println("│ ┌─────────────────────────────────────────┐ │");
   	   System.out.println("│ │ 1.마이페이지  2.엔지니어/관리자 조회  999.로그아웃   │ │");
   	   System.out.println("│ └─────────────────────────────────────────┘ │");
   	   System.out.println("│                                             │");
   	   System.out.println("└─────────────────────────────────────────────┘");
   	   System.out.print("  ● 번호 입력(숫자) >> ");
   	   
         switch (ScanUtil.nextInt()) {
         case 1: return View.ENGINEER_MY;
         case 2: return View.ENGINEER_LIST;
         case 999: 
            Controller.sessionStorage.clear();
            return View.HOME;
         default: 
            System.err.println("화면에 출력된 번호(숫자)를 입력해주세요.");
            return View.ENGSYSMAIN;
         }
      }
   }

   public View eng_list() {
      // 전체 리스트 조회
      // 관리자 /엔지니어 둘다 출력 하자 관리자인지 아닌지 표시하고
      List<Map<String, Object>> engList = dao.getEngineerAll();

      System.out.println("=================================================");
      System.out.println("=================== 엔지니어 LIST =================");
      PrintUtil.printfln("|%-10o|%-10o|%-7o|%-20o|%-3o|", " ", "엔지니어번호", "이름", "직급", "내선번호", "관리자");
      System.out.println("------------------------------------------------");
      for(Map<String, Object> item : engList) {
         PrintUtil.printf("|%-8o|", " ",  item.get("ENG_NO"));
         PrintUtil.printf("%-9o|", " ",  item.get("ENG_NAME"));
         PrintUtil.printf("%-7o|",  " ", item.get("ENG_POSITION"));
         PrintUtil.printf("%-12o|",  " ", item.get("ENG_TEL"));
         PrintUtil.printf("  %-3o|",  " ", item.get("ENG_ADMIN"));
         System.out.println();
      }
      Map<String, Object> whoeng = dao.getEngOrSys(2, (String)Controller.sessionStorage.get("loginInfo"));;
      if (whoeng == null) {
         System.out.println("=================================================");
         PrintUtil.printfln("%54o", " ", "0. 돌아가기");
         System.out.println("=================================================");
      }else {
         System.out.println("================================================");
         PrintUtil.printfln("%58o", " ", "1. 엔지니어 신규 등록 2. 엔지니어 삭제 0. 돌아가기");
         System.out.println("================================================");
      }
      System.out.print("  ● 번호 입력 >> ");
      switch (ScanUtil.nextInt()) {
      case 1: return View.ENGINEER_ADD;
      case 2: return View.ENGINEER_MODIFY;
      //로그인 정보가 엔지니어냐 관리자냐에 따라서 돌아가기 단계가 다름
      case 0: return View.ENGSYSMAIN;   
      default: 
         System.err.println("화면에 출력된 번호(숫자)를 입력해주세요.");
         return View.ENGINEER_LIST;
      }
   }

   public View eng_add() {

		System.out.println("┌─────────────── [ 엔지니어 등록 ] ─────────────────┐");
		System.out.println("│                                               │");
		System.out.println("│               해당 항목을 입력하세요.               │");
		System.out.println("│-----------------------------------------------│");
//      System.out.println("[[ 엔지니어 등록 ]]");
//      System.out.println("========================");
      String id = null;
      String pass = null;
      while(true) {
         System.out.print("  ● 아이디(ID) >> ");
         id = ScanUtil.nextIdPass("id").trim();
         //아이디 중복체크            
         Map<String, Object> res = dao.getEngineerInfo("ENG_ID",id);
         if (res != null ) {
            System.out.println("이미 사용되고 있는 ID입니다. \n다른 ID를 입력하세요.");
         } else if (id.length() >10){
            System.out.println("ID는 영문, 숫자를 포함하여 최대 10자까지 가능합니다. \n다른 ID를 입력하세요.");
         }else break;
      }

      while(true) {
         System.out.print("  ● 비밀번호(PW) >> ");
         pass = ScanUtil.nextIdPass("pass").trim();
         if (pass.length() >20) {
            System.out.println("비밀번호는 최대 20자까지 가능합니다. \n다시 입력해주세요.");
         }else break;
      }
      System.out.print("  ● 이름 >> ");
      String name = ScanUtil.nextHangeul().trim();
      System.out.print("  ● 내선번호(000-000-0000) >> ");
      String tel = ScanUtil.nextPhone().trim();
      System.out.print("  ● 직급 >> ");
      String postn = ScanUtil.nextHangeul().trim();
      System.out.print("  ● 관리자 계정으로 등록하시겠습니까?(Y/N) >> ");
      String admin = ScanUtil.nextYN().trim();
      List<Object> param = new ArrayList<Object>();
      param.add(id);
      param.add(pass);
      param.add(name);
      param.add(tel);
      param.add(postn);
      param.add("Y");
      if (admin.equals("Y")||admin.equals("y")) param.add("Y");
      else  param.add("N");

      int result = dao.add(param);
      if (result > 0) {
         System.out.println("--엔지니어가 정상적으로 등록되었습니다.");
      }else {
         System.out.println("--엔지니어 등록 실패");
      }
      System.out.println("└───────────────────────────────────────────────┘");
      return View.ENGINEER_LIST;
   }

   public View eng_delete() {
      Map<String, Object> selectEngNo =null;
      while(true) {
         System.out.print("  ● 엔지니어번호 >>  ");
         //아이디 유무 체크
         selectEngNo = dao.getEngineerInfo("ENG_NO", ScanUtil.nextLine().trim());
         if (selectEngNo == null ) {
            System.out.println("--등록된 정보가 없습니다. 엔지니어 번호를 다시 확인해주세요.\n");
         }else {
            break;
         }
      }
      List<Object> param = new ArrayList<Object>();
      int result = 0;
      PrintUtil.printfln("|%-13o|%-10o|%-7o|%-22o|", " ", "엔지니어번호", "이름", "직급", "내선번호");
      PrintUtil.printf("|%-14o|", " ",  selectEngNo.get("ENG_NO"));
      PrintUtil.printf("%-9o|", " ",  selectEngNo.get("ENG_NAME"));
      PrintUtil.printf("%-7o|",  " ", selectEngNo.get("ENG_POSITION"));
      PrintUtil.printfln("%-16o|",  " ", selectEngNo.get("ENG_TEL"));
      System.out.print("선택하신 엔지니어의 정보를 확인해주십시오.\n 엔지니어 정보를 삭제하시겠습니까?(Y/N) >> ");
      String yesNo = ScanUtil.nextLine();
      if (yesNo.equals("Y")||yesNo.equals("y")) {
         param.add("N");
         String engno = selectEngNo.get("ENG_NO").toString();
         param.add(engno);

         result = dao.delete(param);
      }else {
         System.out.println("삭제를 취소하였습니다.");
         return View.ENGINEER_LIST;
      }
      if (result > 0) {
         System.out.println("엔지니어가 정상적으로 삭제되었습니다.");
      }else {
         System.out.println("엔지니어 삭제 실패");
      }
      return View.ENGINEER_LIST;
   }   

   public View eng_mypage() {      
	   List<Map<String, Object>> engList = dao.getEngineerMyPage(engInfo.get("ENG_ID").toString());
      System.out.println("======================================================================================================================");
      PrintUtil.printfln("%120o", " ", "   엔지니어 : "+ engInfo.get("ENG_NAME").toString());
      System.out.println("======================================================================================================================");


      //HashMap 널체크
      for (int i = 0; i < engList.size(); i++) {
         for (int j = 0; j < engList.get(i).size(); j++) {
            if ((engList.get(i).get("RP_RESULT")==null)) engList.get(i).put("RP_RESULT", "-");
            if ((engList.get(i).get("RP_STARTDATE")==null)) engList.get(i).put("RP_STARTDATE", "-");
            if ((engList.get(i).get("RP_ENDDATE")==null)) engList.get(i).put("RP_ENDDATE", "-");
            if ((engList.get(i).get("RP_COSTYN")==null)) engList.get(i).put("RP_COSTYN", "-");
            if ((engList.get(i).get("RP_DETAIL")==null)) engList.get(i).put("RP_DETAIL", "-");
         }
      }
      DateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
      DateFormat formatter1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

      PrintUtil.printfln("|%-24o  |%-10o|%-8o|%-11o|%-28o |%-47o|%-46o|%-50o", " ", 
            "접수번호", "회원번호", "회원명", "제품구분", "모델명", "제품명", "고장증상", "고장세부내용");
      for (int i = 0; i < engList.size(); i++) {
         Map<String, Object> map = engList.get(i);
         PrintUtil.printf("|%-12o |", " ", map.get("RP_NO"));
         PrintUtil.printf("%-7o|", " ",map.get("MEM_NO").toString().trim());
         PrintUtil.printf("%-8o|", " ",map.get("MEM_NAME"));
         PrintUtil.printf("%-10o|", " ",map.get("GUBUN"));
         PrintUtil.printf("%-16o|", " ",map.get("IT_ID"));
         PrintUtil.printf("%-25o|", " ",map.get("IT_NM").toString().trim());
         PrintUtil.printf("%-1o|", " ", map.get("CONTENTS_NM").toString());
         PrintUtil.printfln("%-50o", " ",map.get("RP_DETAIL"));
      }
      System.out.println("\n");

      PrintUtil.printfln("|%-16o|%-8o|%-10o|%-34o|%-32o|%-50o|%-10o|", " ", "예약날짜", "예약시간", "진행상태", "접수일시", "수리완료일시", "수리결과", "비용");   
      for (int i = 0; i < engList.size(); i++) {
         Map<String, Object> map = engList.get(i);
         String date=formatter.format(map.get("RP_RESERVEDT"));
         PrintUtil.printf("|%-10o|", " ", date);
         PrintUtil.printf("%-9o|", " ", map.get("RP_RESERVERM"));
         PrintUtil.printf("%-11o|", " ", map.get("STATE_NM"));

         if (map.get("RP_STARTDATE").equals("-")) {
            PrintUtil.printf("         %-10o|", " ", map.get("RP_STARTDATE"));
         }else {
            String startdate=formatter1.format(map.get("RP_STARTDATE"));
            PrintUtil.printf("%-6o|", " ", startdate);
         }
         if (map.get("RP_ENDDATE").equals("-")) {
            PrintUtil.printf("         %-10o|", " ", map.get("RP_ENDDATE"));
         }else {
            String enddate=formatter1.format(map.get("RP_ENDDATE"));
            PrintUtil.printf("%-6o|", " ", enddate);
         }
         if (map.get("RP_RESULT").equals("-")) {
            PrintUtil.printf("\t\t%-14o|", " ", map.get("RP_RESULT"));
         }else {
            String enddate=formatter1.format(map.get("RP_RESULT"));
            PrintUtil.printf("%-6o|", " ", enddate);
         }
         PrintUtil.printf("%-6o|", " ", map.get("RP_COSTYN"));
         System.out.println();
      }

      System.out.println("======================================================================================================================");
      PrintUtil.printfln("%123o", " ", "   1.접수 완료 2. 수리 완료 ");
      System.out.println("======================================================================================================================");

      String rpNo = null;
      while(true) {
         while(true) {
            System.out.print("  ● 접수번호 입력 >> ");
            rpNo = ScanUtil.nextLine().trim();

            //접수번호 제대로 입력했는지 확인
            Map<String, Object> res = reserdao.getEngRpList("ENG002".toString(),rpNo);         
            if (res == null ) {
               System.out.println("접수 번호가 유효하지 않습니다. 다시 입력해주세요.");
            } else{ break;}
         }
         System.out.print("  ● 번호 입력 >> ");
         int upstate = ScanUtil.nextInt();
         Map<String, Object> statecheck = reserdao.getstateCheck(rpNo, upstate);   
         // 접수 완료 처리
         if (upstate == 1 && statecheck == null) {
            System.out.println("접수중 상태만 접수 완료 가능합니다.");
         }else {
            System.out.println("선택하신 예약 건 '"+rpNo+"'를 접수 완료 처리 하시겠습니까?(Y/N)");

            String yesNo = ScanUtil.nextLine();
            //접수 완료 상태 업데이트(db단)
            if (yesNo.equals("Y")||yesNo.equals("y")) {
               int updateres = reserdao.updateState(rpNo, 1, null);
               if (updateres > 0) {
                  System.out.println("접수 완료 처리 되었습니다.");
               }else {
                  System.out.println("접수 완료 처리 실패");
                  return View.ENGINEER_MY;
               }
            }else {
               System.out.println("접수 완료 처리가 취소 되었습니다.");
               return View.ENGINEER_MY;
            }
         }
      // 수리 완료 처리
      if (upstate == 2 && statecheck == null) {
         System.out.println("수리중 상태만 수리 완료 가능합니다.");
      }else {
         System.out.println("비용 처리를 선택해주세요.(Y:유상/N:무상)");
         String cost = ScanUtil.nextLine().trim();
         System.out.println("선택하신 예약 건 '"+rpNo+"'를 접수 완료 처리 하시겠습니까?(Y/N)");
         String yesNo = ScanUtil.nextLine();
         // 수리 완료 상태 업데이트(db단)
         if (yesNo.equals("Y")||yesNo.equals("y")) {
            int updateres = reserdao.updateState(rpNo, 3, cost);
            if (updateres > 0) {
               System.out.println("수리 완료 처리 되었습니다.");
            }else {
               System.out.println("수리 완료 처리 실패");
               return View.ENGINEER_MY;
            }
         }else {
            System.out.println("수리 완료 처리가 취소 되었습니다.");
            return View.ENGINEER_MY;
         }
      }      
      //1. 접수 완료
      // 접수 번호로 조회했을 때 상태값이 1번이 아니면, 접수중인 예약 건만 접수 완료 가능합니다. 출력
      // 접수번호  입력으로 돌아감 
      // 접수 번호로 조회했을 때 상태값이 1번이면, 수리중 및 접수일시(자동) 업데이트 치기
      //2. 수리 완료 
      // 수리중 상태인 건인지 쿼리 조회 > 수리중 상태인 예약 건만 수리 완료 가능합니다. 출력
      // 접수 번호 입력으로 돌아감
      // 수리 결과 기입 받기 , 수리 완료 일시(자동) 업데이트 치기
   }
}
}
