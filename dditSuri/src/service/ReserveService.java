package service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import controller.Controller;
import dao.EngineerDAO;
import dao.MemberDAO;
import dao.ReserveServiceDAO;
import util.PrintUtil;
import util.ScanUtil;
import util.View;

public class ReserveService {

	private static ReserveService instance = null;

	private ReserveService() {
	}
	public static ReserveService getInstance() {
		if (instance == null)
			instance = new ReserveService();
		return instance;
	}

	ReserveServiceDAO dao = ReserveServiceDAO.getInstance();
	MemberDAO memdao = MemberDAO.getInstance();
	EngineerDAO engdao = EngineerDAO.getInstance();
	//실제 근무중인 엔지니어 리스트 불러옴
	List<Map<String, Object>> engListInfo =engdao.getEngineerList(1);

	// 수리예약화면
	public View reserve() {
		PrintUtil.printbarlong();
		System.out.println("┌─────────────── [수리예약화면] ─────────────────┐");
		System.out.println("│                                             │");
		System.out.println("│             수리 할 제품을 선택하세요                            │");
		System.out.println("│---------------------------------------------│");
		System.out.println("│ ┌─────────────────────────────────────────┐ │");
		System.out.println("│ │ 1.스마트폰  2.노트북  3.워치  4.태블릿  0.뒤로가기    │ │");
		System.out.println("│ └─────────────────────────────────────────┘ │");
		System.out.println("│                                             │");
		System.out.println("└─────────────────────────────────────────────┘");

		System.out.print("  ● 번호 입력(숫자) >> ");
		int gubun = ScanUtil.nextInt(); 
		System.out.println();
		PrintUtil.printbarlong();
		switch (gubun) {
		case 0:
			return View.MEMBER;
		case 1: case 2: case 3 : case 4 :
			return reserveProcess(gubun);
		default: 
			System.err.println("화면에 출력된 번호(숫자)를 입력해주세요.");
			return View.MEMBER_RESERVE;
		}
	}


	public View reserveProcess(int gubun) {
		List<Object> repairParam = new ArrayList<Object>();

		// 모델명 출력
		List<Map<String, Object>> gubunList = dao.gubunList(gubun);
		List<Map<String, Object>> modelNum = new ArrayList<Map<String, Object>>();

		// "GUBUN" 선택화면 입니다 ex)스마트폰 선택화면 입니다
		System.out.println("┌─────────────── [수리예약화면] ─────────────────┐");
		System.out.println("│                                             │");
		System.out.println("│            제품의 모델명을 선택하세요.             │");
		System.out.println("│---------------------------------------------│");
		PrintUtil.printfln("│"+"번호"+"|%-18o|", " ", "제품이름\t\t\t\t"+"");

		for (Map<String, Object> item : gubunList) {
			System.out.print("│ "+item.get("ROWNUM")+" "+"│");
			PrintUtil.printf("%-18o", " ", item.get("ITEM_NM")+"\t      │");
			System.out.println();
			modelNum.add(item);//오류-:1 해야함.(파라미터에 -1함!)
		}
		System.out.println("└─────────────────────────────────────────────┘");
		System.out.print("  ● 번호 입력(숫자) >> ");
		// 모델명 선택
		int itemIdNum=0;
		while(true) {
			itemIdNum = ScanUtil.nextInt();
			if(itemIdNum>modelNum.size()) {
				System.out.println("해당번호와 일치하는 모델명이 없습니다. 다시입력하세요 >>");
			}else {
				modelNum.get(itemIdNum-1);// 입력받은 번째의 item아이디를 가지고 온다//수정
				break;
			}
		}
		System.out.println();
		PrintUtil.printbarlong();
		
		
		// *선택: 스마트폰>>모델명(꾸밀때 생각해보기)
		// 고장증상 프린트
		// 1.(100)배터리 문제 2.(200)액정 문제 3.(300)네트워크 및 연결문제 4.(400)충전문제 5.기타
		System.out.println("┌─────────────── [수리예약화면] ─────────────────┐");
		System.out.println("│                                             │");
		System.out.println("│              고장 증상을 선택하세요.              │");
		System.out.println("│---------------------------------------------│");
		System.out.println("│ ┌─────────────────────────────────────────┐ │");
		System.out.println("│ │                                         │ │");
		System.out.println("│ │ 1.배터리 문제   2.액정문제   3.네트워크 및 연결문제     │ │");
		System.out.println("│ │                                         │ │");
		System.out.println("│ │ 4.충전문제       5.기타                                               │ │");
		System.out.println("│ │                                         │ │");
		System.out.println("│ └─────────────────────────────────────────┘ │");
		System.out.println("└─────────────────────────────────────────────┘");
		System.out.print("  ● 번호 입력(숫자) >> ");
		int contentsNum = ScanUtil.nextInt();
		System.out.println();
		PrintUtil.printbarlong();	

		List<Map<String, Object>> contentsList = dao.contentsList(contentsNum);
		List<Map<String, Object>> detailNum = new ArrayList<Map<String, Object>>();
		// 고장증상 리스트 출력 되도록

		int detailno=0;

		//5번 기타 누르면 세부증상이 없어서 오류남.
		if(contentsNum != 5) {
			System.out.println("┌──────────────── [수리예약화면] ──────────────────┐");
			System.out.println("│                                               │");
			System.out.println("│             고장 세부증상을 선택하세요.              │");
			System.out.println("│-----------------------------------------------│");
			PrintUtil.printfln("│"+"번호"+"|%-15o", " ", "고장세부증상                                    \t\t"+"│");
			
			for (Map<String, Object> contents : contentsList) {
				System.out.print("│ "+contents.get("ROWNUM")+" "+"│");
				contents.get("CONTENTS_CD");
				PrintUtil.printf("%-15o", " ", contents.get("CONTENTS_NM")+"                      \t│");
				System.out.println();

				detailNum.add(contents);//오류:-1 해야함.
			}
			//고장세부증상 선택
			System.out.println("└───────────────────────────────────────────────┘");
			System.out.print("  ● 번호 입력(숫자) >> ");
			
			while(true) {
				detailno = ScanUtil.nextInt();
				if(detailno>detailNum.size()) {
					System.out.println("해당번호와 일치하는 증상이 없습니다. 다시입력하세요 >>");
				}else {
					detailNum.get(detailno-1);// 입력받은 번째의 item아이디를 가지고 온다//수정
					break;
				}
			}

		}

		System.out.println();
		PrintUtil.printbarlong();
		
		//상세증상 입력:null 가능.
		System.out.println("┌──────────────── [수리예약화면] ──────────────────┐");
		System.out.println("│                                               │");
		System.out.println("│            고장 상세증상을 작성 해주세요.             │");
		System.out.println("│-----------------------------------------------│");
		System.out.print("  ● 증상 입력(선택) >> ");
		String rpDetail = ScanUtil.nextLine();
		System.out.println("└───────────────────────────────────────────────┘");


		System.out.println();
		PrintUtil.printbarlong();
		System.out.println("┌──────────────── [수리예약화면] ──────────────────┐");
		System.out.println("│                                               │");
		System.out.println("│             예약 날짜와 시간을 입력하세요.            │");
		System.out.println("│-----------------------------------------------│");
		// 엔지니어 ENG002~ENG005 만 예약 접수 받을예정.
		// 시간당 인당 3명 예약접수 받을 예정이고 1시간당 최대 12명 예약가능
		// 근무시간 8시간 기준으로 1일 최대 96명 예약가능(4*3*8)


		List<Object> param = new ArrayList<Object>();
		String rsTimestr="";
		int rsTime = 0;

		System.out.println("--예약 희망 날짜를 입력하세요.");
		System.out.print("  ● 예약 희망 날짜 입력 (0000-00-00) >> ");
		String rsDate = ScanUtil.nextDate();
		param.add(rsDate);

		System.out.println("--예약 희망 시간을 입력하세요 \n  (수리가능시간: 9시~17시/24시로 입력해주세요)");
		while(true) {

			while(true) {
				System.out.print("  ● 예약 희망 시간 입력(00) >> ");
				rsTime = ScanUtil.nextInt();

				if (rsTime == 9 || rsTime > 9 && rsTime < 18) {
					if (rsTime == 9) {
						rsTimestr = "09"; 
						param.add(rsTimestr);

					}else if(rsTime > 9 && rsTime < 18) {
						rsTimestr = Integer.toString(rsTime);
						param.add(rsTimestr);

					}
					Map<String, Object> reserveTime = dao.reserveTime(param);
					int timeCount = ((BigDecimal)reserveTime.get("COUNT(*)")).intValue();
					if(timeCount>=engListInfo.size()*3) {
						System.out.println("입력한 시간은 예약 마감되었습니다. 다른 시간을 선택해주세요.");
						param.remove(param.size()-1);
					}else {
						System.out.println();
						System.out.println("│-------------- 선택이 완료 되었습니다.---------------│");
						System.out.println("└───────────────────────────────────────────────┘");
						System.out.println();
						break;
					}
				}else {
					System.out.println("  예약 가능 시간이 아닙니다. \n  다시 입력해주세요.");
				}
			}
			//		}
			
			System.out.print("  ● 접수 하시겠습니까?(Y/N) >> ");
			String yesNo = ScanUtil.nextYN();
			PrintUtil.printbarlong();
			System.out.println();
			
			if (yesNo.equals("Y")||yesNo.equals("y")) {

				// 엔지니어 배정 위한 코드 ~
				String loginId = Controller.sessionStorage.get("loginID").toString();
				Map<String, Object> memNo = memdao.getMemberInfo(loginId);

				String engassign = null;

				ArrayList<String> totengList = new ArrayList<>(engListInfo.size());
				for (int i = 0; i < engListInfo.size(); i++) {
					totengList.add(engListInfo.get(i).get("ENG_NO").toString().trim()); 
				}

				//랜덤으로 근무중인 엔지니어 중 1명 불러옴
				int selOneEng = selectOne(engListInfo.size());

				//접수코드 생성
				String rpNoLast=null;
				String rpNo=null;
				rpNoLast=rsDate.replace("-", "")+rsTimestr;

				// 해당 일시에 제일 마지막 순서 접수번호 가져옴(rownum =1)
				Map<String, Object> rpNoList =dao.rpNoCheck(rpNoLast);//rpNoList=0이면 접수된 건이 없는것. _001 부여.
				//nullpoint 때문에 수정함.     
				if(rpNoList==null) {

					engassign = engListInfo.get(selOneEng).get("ENG_NO").toString().trim();//근무하는 엔지니어 중 랜덤 1명 불러오고 엔지니어 번호 String으로 불러옴.공백제거
					rpNo=rpNoLast+"_"+"001";
				}
				else {
					String orderstr =rpNoList.get("RP_NO").toString().trim().substring(11);//수정함.substring        
					int ordernum=Integer.parseInt(orderstr);

					if(ordernum<engListInfo.size()*3) { ordernum+= 1;
					}
					rpNo=rpNoLast+"_"+String.format("%03d", ordernum);
					//int를 string으로 변경
					rpNo = rpNo.trim();
				}

				//엔지니어 배정
				//엔지니어 별 해당 일자시간에 몇 명씩 부여받은 상태인지 조회해줌 
				//엔지니어중 3명씩 배정된 엔지니어 목록을 출력함
				List<Map<String, Object>> engCountList =dao.engCount(rpNoLast);
				ArrayList<String> engList = null;

				// 해당 일시에 아직 접수된 건이 없는 거야.
				if (rpNoList != null) {//접수번호 존재
					if (engCountList == null) {
						engassign =engListInfo.get(selOneEng).get("ENG_NO").toString().trim();
					}else if (engCountList.size() > 0 && engCountList.size() < engListInfo.size()) {
						engList = new ArrayList<>(engCountList.size());
						//3명다찬 엔지니어의 엔지니어 코드를 list에 넣어줌

						for (int i = 0; i < engCountList.size(); i++) {//사이즈가 0이라서 안걸림..
							engList.add(engCountList.get(i).get("ENG_NO").toString().trim());
						}
						//3명다찬 엔지니어의 엔지니어 코드를 빼는것!
						for (int i = 0; i < engList.size(); i++) {
							totengList.remove(engList.get(i).toString());
						}
						int assignidx = selectOne(totengList.size());
						engassign =totengList.get(assignidx);
						//접수 번호 생성....
					}
				}

				//수정함,item_id,contents_cd
				repairParam.add(rpNo);//RP_NO (자바로 생성하기)
				repairParam.add(memNo.get("MEM_NO").toString().trim());//MEM_NO (회원번호)      
				repairParam.add(modelNum.get(itemIdNum-1).get("ITEM_ID").toString().trim());//ITEM_ID(모델아이디)
				//CONTENTS_CD (고장코드)
				if (contentsNum == 5) {
					repairParam.add("500");
				}else {
					repairParam.add(detailNum.get(detailno-1).get("CONTENTS_CD").toString().trim());//CONTENTS_CD (고장코드)
				}
				repairParam.add(rpDetail);//RP_DETAIL(수리상세내역)
				repairParam.add(rsDate);//예약날짜
				repairParam.add(rsTimestr);//예약시간
				repairParam.add("1");//STATE_CD=1(접수중), data상 char이므로 String으로 지정함.
				repairParam.add(engassign);//엔지니어배정

				int insertres=dao.insertRepair(repairParam);
				if(insertres==1) {
					System.out.println("--접수 완료입니다. 아래 정보를 확인해주세요.--");
					System.out.println();
					printReceipt(repairParam);
					return View.MEMBER_LIST;
					
				}
				else {
					System.out.println("  접수 실패 입니다. 다시 시도해주세요.");
					return View.MEMBER_RESERVE;
				}
			}else {
				System.out.println("  접수 취소하였습니다.");
				return View.MEMBER;

			}
		}
	}
	
	
	public int selectOne(int size) {          
		Random r = new Random();                           
		int rnd = r.nextInt(size);   // 0 ~ 4 정수형 난수 생성
		return rnd;
	}

	public void printReceipt(List<Object> param) {
		// 접수증 출력하기1
		//회원번호 불러오기!
		String rpNoReceipt=param.get(0).toString().trim();
		Map<String, Object> getReceipt = dao.getReceipt(rpNoReceipt);



		System.out.println("┌─────────────────* 접 수 증 *───────────────────┐");
		System.out.println("│                                             │");
		System.out.printf("│   1) 접수번호: %s                 *\n", param.get(0).toString().trim());
		System.out.printf("│   2) 이름: %s\n",getReceipt.get("MEM_NAME").toString().trim());//이름이 나와야함...
		System.out.printf("│   3) 수리제품: %s\n", getReceipt.get("ITEM_NM").toString().trim());//제품명 출력하기
		System.out.printf("│   4) 증상: %s\n", getReceipt.get("CONTENTS_NM").toString().trim());//고장증상 출력하기
		System.out.printf("│   5) 상세증상: %s\n", param.get(4));
		System.out.printf("│   6) 예약날짜: %s\n", param.get(5));
		System.out.printf("│   7) 예약시간: %s\n", param.get(6)+"시");
		System.out.printf("│   8) 수리담당자: %s                                                  *\n", getReceipt.get("ENG_NAME").toString().trim());//엔지니어명ㅎ..
		System.out.println("│                                             │");
		System.out.println("└─────────────────────────────────────────────┘");
		System.out.println();
		

		//메인 돌아가기

	}

	//예약조회화면
	public View reservelist() {

		String loginId = Controller.sessionStorage.get("loginID").toString();
		Map<String, Object> memNo = memdao.getMemberInfo(loginId);


		String memStr=memNo.get("MEM_NO").toString();
		List<Map<String, Object>> reserveList =dao.reserveList(memStr);

		PrintUtil.printbarlong();
	    System.out.println("=====================================================[예약조회/취소]=====================================================");
		//null값에 값 채워넣기
	    if(reserveList==null) {
	    	System.out.println("예약 정보가 없습니다. 예약 접수 해주세요.");
	    	PrintUtil.printbarlong();
	    	return View.MEMBER;
	    }else {
		for (int i = 0; i < reserveList.size(); i++) {
			for (int j = 0; j < reserveList.get(i).size(); j++) {
				if((reserveList.get(i).get("RP_DETAIL")==null)) 	reserveList.get(i).put("RP_DETAIL","-");
				if((reserveList.get(i).get("RP_RESULT")==null))	reserveList.get(i).put("RP_RESULT","-");
				if((reserveList.get(i).get("RP_STARTDATE")==null))	reserveList.get(i).put("RP_STARTDATE","-");
				if((reserveList.get(i).get("RP_ENDDATE")==null))	reserveList.get(i).put("RP_ENDDATE","-");
				if((reserveList.get(i).get("RP_COSTYN")==null)) 	reserveList.get(i).put("RP_COSTYN","-");

			}

		}

		DateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");//예약희망날짜
		DateFormat formatter1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//접수랑 완료 일시

		//접수번호│구분│제품명│고장증상│고장세부내역│서비스예약일│서비스시간│진행상태│서비스담당자│서비스시작일│서비스종료일│수리결과│비용
		PrintUtil.printfln("|%-25o|%-11o|%-23o|%-47o|%-46o|%-24o|%-17o|%-8o|%-8o|%-8o|%-8o|%-50o|%-10o|", " ", 
				"접수번호            ", "구분 ", "제품명\t\t", "고장증상", "고장세부내역\t","진행상태","서비스담당자","서비스예약일", "서비스시간", "서비스시작일", "서비스종료일", "수리결과", "비용");
		PrintUtil.printbar2();
		for(Map<String, Object> item : reserveList) {

			PrintUtil.printf("|%-14o|"," ",item.get("RP_NO"));//접수번호
			PrintUtil.printf("%-10o|"," ",item.get("GUBUN_NM"));//구분명
			PrintUtil.printf("%-4o|"," ",item.get("ITEM_NM"));//제품명
			PrintUtil.printf("%-23o|"," ",item.get("CONTENTS_NM").toString());//고장증상
			PrintUtil.printf("%-50o|"," ",item.get("RP_DETAIL"));//고장세부내역
			PrintUtil.printf("%-8o|"," ",item.get("STATE_NM"));//진행상태
			PrintUtil.printf("%-8o|"," ",item.get("ENG_NAME"));//서비스담당자			
			
			String date = formatter.format(item.get("RP_RESERVEDT"));
			PrintUtil.printf("%-8o|"," ",date);//서비스예약일
			PrintUtil.printf("%-8o|"," ",item.get("RP_RESERVERM"));//서비스시간

			//서비스시작일
			if (item.get("RP_STARTDATE").equals("-")) {
				PrintUtil.printf("         %-8o|", " ", item.get("RP_STARTDATE"));
			}else {
				String startdate=formatter1.format(item.get("RP_STARTDATE"));
				PrintUtil.printf("%-8o|", " ", startdate);
			}
			//서비스종료일
			if (item.get("RP_ENDDATE").equals("-")) {
				PrintUtil.printf("         %-8o|", " ", item.get("RP_ENDDATE"));
			}else {
				String enddate=formatter1.format(item.get("RP_ENDDATE"));
				PrintUtil.printf("%-6o|", " ", enddate);
			}



//			String startdate= formatter.format(item.get("RP_STARTDATE"));
//			PrintUtil.printf("%-8o|"," ",startdate);
//			String enddate= formatter1.format(item.get("RP_ENDDATE"));
//			PrintUtil.printf("%-8o|"," ",enddate);
			PrintUtil.printf("%-50o|"," ",item.get("RP_RESULT"));
			PrintUtil.printfln("%-10o|"," ",item.get("RP_COSTYN"));

		}

		//접수내역 고르면 삭제하도록 하기
		PrintUtil.printbar2();
		PrintUtil.printbarlong();
		System.out.println("--원하시는 메뉴를 선택하세요.");
		System.out.println("  1. 접수취소      2. 메인화면");
		System.out.print("  ● 번호 입력 >> ");
		int reserveDel=ScanUtil.nextInt();
		if(reserveDel==1) {
			PrintUtil.printbarlong();
			return View.MEMBER_LISTDEL;

		}else if(reserveDel==2) {
			return View.MEMBER;
			
		}
		else {
			return View.MEMBER_LIST;
		}
	}
}

//접수취소리스트
	public View reserveDelete() {
		String loginId = Controller.sessionStorage.get("loginID").toString();
		Map<String, Object> memNo = memdao.getMemberInfo(loginId);

		String mem =memNo.get("MEM_NO").toString().trim();
		Map<String,Object> selectReNo =null;

		while(true) {
			System.out.print("  ● 접수 번호 입력>> ");
			selectReNo=dao.reserveInfo(mem,ScanUtil.nextLine().trim());
			if(selectReNo == null) {
				System.out.print("   해당 접수번호가 없습니다. 접수번호를 다시 확인해주세요.\n");
			}else if(!selectReNo.get("STATE_CD").equals("1") ) {
				System.out.print("   접수중 상태만 예약 취소 가능합니다.");
			}
			else {
				break;
			}
		}

		int result = 0;

		DateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");//예약희망날짜
		System.out.println("=====================================================[예약조회/취소]=====================================================");
		
		PrintUtil.printfln("|%-25o|%-11o|%-23o|%-45o|%-20o|%-10o|%-8o|%-8o|", " ", 
				"접수번호            ", "구분 ", "제품명\t\t", "고장증상", "고장세부내역        \t\t","진행상태","서비스예약일  ", "서비스시간");
		PrintUtil.printbar2();
		PrintUtil.printf("|%-14o|"," ",selectReNo.get("RP_NO"));//접수번호
		PrintUtil.printf("%-10o|"," ",selectReNo.get("GUBUN_NM"));//구분
		PrintUtil.printf("%-4o|"," ",selectReNo.get("ITEM_NM"));//제품명
		PrintUtil.printf("%-23o|"," ",selectReNo.get("CONTENTS_NM"));//고장증상
		PrintUtil.printf("%-50o|"," ",selectReNo.get("RP_DETAIL"));//고장세부내역
		PrintUtil.printf("%-8o|"," ",selectReNo.get("STATE_NM"));//진행상태
		//서비스시작일
		if (selectReNo.get("RP_RESERVEDT").equals("-")) {
			PrintUtil.printf("         %-8o|", " ", selectReNo.get("RP_RESERVEDT"));
		}else {
			String startdate=formatter.format(selectReNo.get("RP_RESERVEDT"));
			PrintUtil.printf("%-8o|", " ", startdate);
		}
		
		PrintUtil.printfln("%-8o|"," ",selectReNo.get("RP_RESERVERM"));//서비스시간
		PrintUtil.printbar2();
		PrintUtil.printbarlong();

		System.out.print("--선택하신 접수내역의 정보를 확인해주십시오.\n  ● 접수취소를 하시겠습니까?(Y/N) >> ");
		String yesNo = ScanUtil.nextYN();
		if(yesNo.equals("Y")||yesNo.equals("y")) {
			String rpNo=selectReNo.get("RP_NO").toString().trim();

			result = dao.rpNoDelete(rpNo);
		}else {
			System.out.println(" 삭제를 취소 하였습니다.");
			PrintUtil.printbarlong();
			return View.MEMBER;
		}

		if(result>0) {
			System.out.println(" 접수취소가 완료 되었습니다.");
		}else {
			System.out.println(" 접수취소 실패");
		}
		PrintUtil.printbarlong();
		return View.MEMBER;
	}
}
