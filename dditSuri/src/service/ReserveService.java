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
	//褒薯 斬鼠醞檣 縛雖棲橫 葬蝶お 碳楝褥
	List<Map<String, Object>> engListInfo =engdao.getEngineerList(1);

	// 熱葬蕨擒�飛�
	public View reserve() {
		PrintUtil.printbarlong();
		System.out.println("忙式式式式式式式式式式式式式式式 [熱葬蕨擒�飛寯 式式式式式式式式式式式式式式式式式忖");
		System.out.println("弛                                             弛");
		System.out.println("弛             熱葬 й 薯ヶ擊 摹鷗ж撮蹂                            弛");
		System.out.println("弛---------------------------------------------弛");
		System.out.println("弛 忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖 弛");
		System.out.println("弛 弛 1.蝶葆おア  2.喻お磁  3.錶纂  4.鷓綰葩  0.菴煎陛晦    弛 弛");
		System.out.println("弛 戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎 弛");
		System.out.println("弛                                             弛");
		System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");

		System.out.print("  ≒ 廓�� 殮溘(璋濠) >> ");
		int gubun = ScanUtil.nextInt(); 
		System.out.println();
		PrintUtil.printbarlong();
		switch (gubun) {
		case 0:
			return View.MEMBER;
		case 1: case 2: case 3 : case 4 :
			return reserveProcess(gubun);
		default: 
			System.err.println("�飛橦� 轎溘脹 廓��(璋濠)蒂 殮溘п輿撮蹂.");
			return View.MEMBER_RESERVE;
		}
	}


	public View reserveProcess(int gubun) {
		List<Object> repairParam = new ArrayList<Object>();

		// 賅筐貲 轎溘
		List<Map<String, Object>> gubunList = dao.gubunList(gubun);
		List<Map<String, Object>> modelNum = new ArrayList<Map<String, Object>>();

		// "GUBUN" 摹鷗�飛� 殮棲棻 ex)蝶葆おア 摹鷗�飛� 殮棲棻
		System.out.println("忙式式式式式式式式式式式式式式式 [熱葬蕨擒�飛寯 式式式式式式式式式式式式式式式式式忖");
		System.out.println("弛                                             弛");
		System.out.println("弛            薯ヶ曖 賅筐貲擊 摹鷗ж撮蹂.             弛");
		System.out.println("弛---------------------------------------------弛");
		PrintUtil.printfln("弛"+"廓��"+"|%-18o|", " ", "薯ヶ檜葷\t\t\t\t"+"");

		for (Map<String, Object> item : gubunList) {
			System.out.print("弛 "+item.get("ROWNUM")+" "+"弛");
			PrintUtil.printf("%-18o", " ", item.get("ITEM_NM")+"\t      弛");
			System.out.println();
			modelNum.add(item);//螃盟-:1 п撿л.(だ塭嘐攪縑 -1л!)
		}
		System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
		System.out.print("  ≒ 廓�� 殮溘(璋濠) >> ");
		// 賅筐貲 摹鷗
		int itemIdNum=0;
		while(true) {
			itemIdNum = ScanUtil.nextInt();
			if(itemIdNum>modelNum.size()) {
				System.out.println("п渡廓�ˋ� 橾纂ж朝 賅筐貲檜 橈蝗棲棻. 棻衛殮溘ж撮蹂 >>");
			}else {
				modelNum.get(itemIdNum-1);// 殮溘嫡擎 廓簞曖 item嬴檜蛤蒂 陛雖堅 螞棻//熱薑
				break;
			}
		}
		System.out.println();
		PrintUtil.printbarlong();
		
		
		// *摹鷗: 蝶葆おア>>賅筐貲(紱塵陽 儅陝п爾晦)
		// 堅濰隸鼻 Щ萼お
		// 1.(100)寡攪葬 僥薯 2.(200)擋薑 僥薯 3.(300)啻お錶觼 塽 翱唸僥薯 4.(400)醱瞪僥薯 5.晦顫
		System.out.println("忙式式式式式式式式式式式式式式式 [熱葬蕨擒�飛寯 式式式式式式式式式式式式式式式式式忖");
		System.out.println("弛                                             弛");
		System.out.println("弛              堅濰 隸鼻擊 摹鷗ж撮蹂.              弛");
		System.out.println("弛---------------------------------------------弛");
		System.out.println("弛 忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖 弛");
		System.out.println("弛 弛                                         弛 弛");
		System.out.println("弛 弛 1.寡攪葬 僥薯   2.擋薑僥薯   3.啻お錶觼 塽 翱唸僥薯     弛 弛");
		System.out.println("弛 弛                                         弛 弛");
		System.out.println("弛 弛 4.醱瞪僥薯       5.晦顫                                               弛 弛");
		System.out.println("弛 弛                                         弛 弛");
		System.out.println("弛 戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎 弛");
		System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
		System.out.print("  ≒ 廓�� 殮溘(璋濠) >> ");
		int contentsNum = ScanUtil.nextInt();
		System.out.println();
		PrintUtil.printbarlong();	

		List<Map<String, Object>> contentsList = dao.contentsList(contentsNum);
		List<Map<String, Object>> detailNum = new ArrayList<Map<String, Object>>();
		// 堅濰隸鼻 葬蝶お 轎溘 腎紫煙

		int detailno=0;

		//5廓 晦顫 援腦賊 撮睡隸鼻檜 橈橫憮 螃盟陴.
		if(contentsNum != 5) {
			System.out.println("忙式式式式式式式式式式式式式式式式 [熱葬蕨擒�飛寯 式式式式式式式式式式式式式式式式式式忖");
			System.out.println("弛                                               弛");
			System.out.println("弛             堅濰 撮睡隸鼻擊 摹鷗ж撮蹂.              弛");
			System.out.println("弛-----------------------------------------------弛");
			PrintUtil.printfln("弛"+"廓��"+"|%-15o", " ", "堅濰撮睡隸鼻                                    \t\t"+"弛");
			
			for (Map<String, Object> contents : contentsList) {
				System.out.print("弛 "+contents.get("ROWNUM")+" "+"弛");
				contents.get("CONTENTS_CD");
				PrintUtil.printf("%-15o", " ", contents.get("CONTENTS_NM")+"                      \t弛");
				System.out.println();

				detailNum.add(contents);//螃盟:-1 п撿л.
			}
			//堅濰撮睡隸鼻 摹鷗
			System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
			System.out.print("  ≒ 廓�� 殮溘(璋濠) >> ");
			
			while(true) {
				detailno = ScanUtil.nextInt();
				if(detailno>detailNum.size()) {
					System.out.println("п渡廓�ˋ� 橾纂ж朝 隸鼻檜 橈蝗棲棻. 棻衛殮溘ж撮蹂 >>");
				}else {
					detailNum.get(detailno-1);// 殮溘嫡擎 廓簞曖 item嬴檜蛤蒂 陛雖堅 螞棻//熱薑
					break;
				}
			}

		}

		System.out.println();
		PrintUtil.printbarlong();
		
		//鼻撮隸鼻 殮溘:null 陛棟.
		System.out.println("忙式式式式式式式式式式式式式式式式 [熱葬蕨擒�飛寯 式式式式式式式式式式式式式式式式式式忖");
		System.out.println("弛                                               弛");
		System.out.println("弛            堅濰 鼻撮隸鼻擊 濛撩 п輿撮蹂.             弛");
		System.out.println("弛-----------------------------------------------弛");
		System.out.print("  ≒ 隸鼻 殮溘(摹鷗) >> ");
		String rpDetail = ScanUtil.nextLine();
		System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");


		System.out.println();
		PrintUtil.printbarlong();
		System.out.println("忙式式式式式式式式式式式式式式式式 [熱葬蕨擒�飛寯 式式式式式式式式式式式式式式式式式式忖");
		System.out.println("弛                                               弛");
		System.out.println("弛             蕨擒 陳瞼諦 衛除擊 殮溘ж撮蹂.            弛");
		System.out.println("弛-----------------------------------------------弛");
		// 縛雖棲橫 ENG002~ENG005 虜 蕨擒 蕾熱 嫡擊蕨薑.
		// 衛除渡 檣渡 3貲 蕨擒蕾熱 嫡擊 蕨薑檜堅 1衛除渡 譆渠 12貲 蕨擒陛棟
		// 斬鼠衛除 8衛除 晦遽戲煎 1橾 譆渠 96貲 蕨擒陛棟(4*3*8)


		List<Object> param = new ArrayList<Object>();
		String rsTimestr="";
		int rsTime = 0;

		System.out.println("--蕨擒 �騆� 陳瞼蒂 殮溘ж撮蹂.");
		System.out.print("  ≒ 蕨擒 �騆� 陳瞼 殮溘 (0000-00-00) >> ");
		String rsDate = ScanUtil.nextDate();
		param.add(rsDate);

		System.out.println("--蕨擒 �騆� 衛除擊 殮溘ж撮蹂 \n  (熱葬陛棟衛除: 9衛~17衛/24衛煎 殮溘п輿撮蹂)");
		while(true) {

			while(true) {
				System.out.print("  ≒ 蕨擒 �騆� 衛除 殮溘(00) >> ");
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
						System.out.println("殮溘и 衛除擎 蕨擒 葆馬腎歷蝗棲棻. 棻艇 衛除擊 摹鷗п輿撮蹂.");
						param.remove(param.size()-1);
					}else {
						System.out.println();
						System.out.println("弛-------------- 摹鷗檜 諫猿 腎歷蝗棲棻.---------------弛");
						System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
						System.out.println();
						break;
					}
				}else {
					System.out.println("  蕨擒 陛棟 衛除檜 嬴椎棲棻. \n  棻衛 殮溘п輿撮蹂.");
				}
			}
			//		}
			
			System.out.print("  ≒ 蕾熱 ж衛啊蝗棲梱?(Y/N) >> ");
			String yesNo = ScanUtil.nextYN();
			PrintUtil.printbarlong();
			System.out.println();
			
			if (yesNo.equals("Y")||yesNo.equals("y")) {

				// 縛雖棲橫 寡薑 嬪и 囀萄 ~
				String loginId = Controller.sessionStorage.get("loginID").toString();
				Map<String, Object> memNo = memdao.getMemberInfo(loginId);

				String engassign = null;

				ArrayList<String> totengList = new ArrayList<>(engListInfo.size());
				for (int i = 0; i < engListInfo.size(); i++) {
					totengList.add(engListInfo.get(i).get("ENG_NO").toString().trim()); 
				}

				//楠渾戲煎 斬鼠醞檣 縛雖棲橫 醞 1貲 碳楝褥
				int selOneEng = selectOne(engListInfo.size());

				//蕾熱囀萄 儅撩
				String rpNoLast=null;
				String rpNo=null;
				rpNoLast=rsDate.replace("-", "")+rsTimestr;

				// п渡 橾衛縑 薯橾 葆雖虞 牖憮 蕾熱廓�� 陛螳褥(rownum =1)
				Map<String, Object> rpNoList =dao.rpNoCheck(rpNoLast);//rpNoList=0檜賊 蕾熱脹 勒檜 橈朝匙. _001 睡罹.
				//nullpoint 陽僥縑 熱薑л.     
				if(rpNoList==null) {

					engassign = engListInfo.get(selOneEng).get("ENG_NO").toString().trim();//斬鼠ж朝 縛雖棲橫 醞 楠渾 1貲 碳楝螃堅 縛雖棲橫 廓�� String戲煎 碳楝褥.奢寥薯剪
					rpNo=rpNoLast+"_"+"001";
				}
				else {
					String orderstr =rpNoList.get("RP_NO").toString().trim().substring(11);//熱薑л.substring        
					int ordernum=Integer.parseInt(orderstr);

					if(ordernum<engListInfo.size()*3) { ordernum+= 1;
					}
					rpNo=rpNoLast+"_"+String.format("%03d", ordernum);
					//int蒂 string戲煎 滲唳
					rpNo = rpNo.trim();
				}

				//縛雖棲橫 寡薑
				//縛雖棲橫 滌 п渡 橾濠衛除縑 賃 貲噶 睡罹嫡擎 鼻鷓檣雖 褻�裔媮� 
				//縛雖棲橫醞 3貲噶 寡薑脹 縛雖棲橫 跡煙擊 轎溘л
				List<Map<String, Object>> engCountList =dao.engCount(rpNoLast);
				ArrayList<String> engList = null;

				// п渡 橾衛縑 嬴霜 蕾熱脹 勒檜 橈朝 剪撿.
				if (rpNoList != null) {//蕾熱廓�� 襄營
					if (engCountList == null) {
						engassign =engListInfo.get(selOneEng).get("ENG_NO").toString().trim();
					}else if (engCountList.size() > 0 && engCountList.size() < engListInfo.size()) {
						engList = new ArrayList<>(engCountList.size());
						//3貲棻雙 縛雖棲橫曖 縛雖棲橫 囀萄蒂 list縑 厥橫邀

						for (int i = 0; i < engCountList.size(); i++) {//餌檜鍔陛 0檜塭憮 寰勘葡..
							engList.add(engCountList.get(i).get("ENG_NO").toString().trim());
						}
						//3貲棻雙 縛雖棲橫曖 縛雖棲橫 囀萄蒂 貍朝匙!
						for (int i = 0; i < engList.size(); i++) {
							totengList.remove(engList.get(i).toString());
						}
						int assignidx = selectOne(totengList.size());
						engassign =totengList.get(assignidx);
						//蕾熱 廓�� 儅撩....
					}
				}

				//熱薑л,item_id,contents_cd
				repairParam.add(rpNo);//RP_NO (濠夥煎 儅撩ж晦)
				repairParam.add(memNo.get("MEM_NO").toString().trim());//MEM_NO (�蛾纗醽�)      
				repairParam.add(modelNum.get(itemIdNum-1).get("ITEM_ID").toString().trim());//ITEM_ID(賅筐嬴檜蛤)
				//CONTENTS_CD (堅濰囀萄)
				if (contentsNum == 5) {
					repairParam.add("500");
				}else {
					repairParam.add(detailNum.get(detailno-1).get("CONTENTS_CD").toString().trim());//CONTENTS_CD (堅濰囀萄)
				}
				repairParam.add(rpDetail);//RP_DETAIL(熱葬鼻撮頂羲)
				repairParam.add(rsDate);//蕨擒陳瞼
				repairParam.add(rsTimestr);//蕨擒衛除
				repairParam.add("1");//STATE_CD=1(蕾熱醞), data鼻 char檜嘎煎 String戲煎 雖薑л.
				repairParam.add(engassign);//縛雖棲橫寡薑

				int insertres=dao.insertRepair(repairParam);
				if(insertres==1) {
					System.out.println("--蕾熱 諫猿殮棲棻. 嬴楚 薑爾蒂 �挫恉媮祤撚�.--");
					System.out.println();
					printReceipt(repairParam);
					return View.MEMBER_LIST;
					
				}
				else {
					System.out.println("  蕾熱 褒ぬ 殮棲棻. 棻衛 衛紫п輿撮蹂.");
					return View.MEMBER_RESERVE;
				}
			}else {
				System.out.println("  蕾熱 鏃模ж艘蝗棲棻.");
				return View.MEMBER;

			}
		}
	}
	
	
	public int selectOne(int size) {          
		Random r = new Random();                           
		int rnd = r.nextInt(size);   // 0 ~ 4 薑熱⑽ 陪熱 儅撩
		return rnd;
	}

	public void printReceipt(List<Object> param) {
		// 蕾熱隸 轎溘ж晦1
		//�蛾纗醽� 碳楝螃晦!
		String rpNoReceipt=param.get(0).toString().trim();
		Map<String, Object> getReceipt = dao.getReceipt(rpNoReceipt);



		System.out.println("忙式式式式式式式式式式式式式式式式式* 蕾 熱 隸 *式式式式式式式式式式式式式式式式式式式忖");
		System.out.println("弛                                             弛");
		System.out.printf("弛   1) 蕾熱廓��: %s                 *\n", param.get(0).toString().trim());
		System.out.printf("弛   2) 檜葷: %s\n",getReceipt.get("MEM_NAME").toString().trim());//檜葷檜 釭諦撿л...
		System.out.printf("弛   3) 熱葬薯ヶ: %s\n", getReceipt.get("ITEM_NM").toString().trim());//薯ヶ貲 轎溘ж晦
		System.out.printf("弛   4) 隸鼻: %s\n", getReceipt.get("CONTENTS_NM").toString().trim());//堅濰隸鼻 轎溘ж晦
		System.out.printf("弛   5) 鼻撮隸鼻: %s\n", param.get(4));
		System.out.printf("弛   6) 蕨擒陳瞼: %s\n", param.get(5));
		System.out.printf("弛   7) 蕨擒衛除: %s\n", param.get(6)+"衛");
		System.out.printf("弛   8) 熱葬氬渡濠: %s                                                  *\n", getReceipt.get("ENG_NAME").toString().trim());//縛雖棲橫貲冗..
		System.out.println("弛                                             弛");
		System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
		System.out.println();
		

		//詭檣 給嬴陛晦

	}

	//蕨擒褻�裙飛�
	public View reservelist() {

		String loginId = Controller.sessionStorage.get("loginID").toString();
		Map<String, Object> memNo = memdao.getMemberInfo(loginId);


		String memStr=memNo.get("MEM_NO").toString();
		List<Map<String, Object>> reserveList =dao.reserveList(memStr);

		PrintUtil.printbarlong();
	    System.out.println("=====================================================[蕨擒褻��/鏃模]=====================================================");
		//null高縑 高 瓣錶厥晦
	    if(reserveList==null) {
	    	System.out.println("蕨擒 薑爾陛 橈蝗棲棻. 蕨擒 蕾熱 п輿撮蹂.");
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

		DateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");//蕨擒�騆螺純�
		DateFormat formatter1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//蕾熱嫌 諫猿 橾衛

		//蕾熱廓�ㄕ２蛹虷Ⅸ灰偶磽１簎斲齠韟１簎撘撩帠遛爬Ⅳ首鬅瑪嗾鈶浀Ⅳ首鬅瑤簸ㄕⅨ醾鉬鬌礎Ⅳ首鬅煽蒮蝩琣Ⅳ首鬅瑤藝衈浀Ⅳ首鬅瑭噸彯浀Ⅳ鷏扇嵹�弛綠辨
		PrintUtil.printfln("|%-25o|%-11o|%-23o|%-47o|%-46o|%-24o|%-17o|%-8o|%-8o|%-8o|%-8o|%-50o|%-10o|", " ", 
				"蕾熱廓��            ", "掘碟 ", "薯ヶ貲\t\t", "堅濰隸鼻", "堅濰撮睡頂羲\t","霞ч鼻鷓","憮綠蝶氬渡濠","憮綠蝶蕨擒橾", "憮綠蝶衛除", "憮綠蝶衛濛橾", "憮綠蝶謙猿橾", "熱葬唸婁", "綠辨");
		PrintUtil.printbar2();
		for(Map<String, Object> item : reserveList) {

			PrintUtil.printf("|%-14o|"," ",item.get("RP_NO"));//蕾熱廓��
			PrintUtil.printf("%-10o|"," ",item.get("GUBUN_NM"));//掘碟貲
			PrintUtil.printf("%-4o|"," ",item.get("ITEM_NM"));//薯ヶ貲
			PrintUtil.printf("%-23o|"," ",item.get("CONTENTS_NM").toString());//堅濰隸鼻
			PrintUtil.printf("%-50o|"," ",item.get("RP_DETAIL"));//堅濰撮睡頂羲
			PrintUtil.printf("%-8o|"," ",item.get("STATE_NM"));//霞ч鼻鷓
			PrintUtil.printf("%-8o|"," ",item.get("ENG_NAME"));//憮綠蝶氬渡濠			
			
			String date = formatter.format(item.get("RP_RESERVEDT"));
			PrintUtil.printf("%-8o|"," ",date);//憮綠蝶蕨擒橾
			PrintUtil.printf("%-8o|"," ",item.get("RP_RESERVERM"));//憮綠蝶衛除

			//憮綠蝶衛濛橾
			if (item.get("RP_STARTDATE").equals("-")) {
				PrintUtil.printf("         %-8o|", " ", item.get("RP_STARTDATE"));
			}else {
				String startdate=formatter1.format(item.get("RP_STARTDATE"));
				PrintUtil.printf("%-8o|", " ", startdate);
			}
			//憮綠蝶謙猿橾
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

		//蕾熱頂羲 堅腦賊 餉薯ж紫煙 ж晦
		PrintUtil.printbar2();
		PrintUtil.printbarlong();
		System.out.println("--錳ж衛朝 詭景蒂 摹鷗ж撮蹂.");
		System.out.println("  1. 蕾熱鏃模      2. 詭檣�飛�");
		System.out.print("  ≒ 廓�� 殮溘 >> ");
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

//蕾熱鏃模葬蝶お
	public View reserveDelete() {
		String loginId = Controller.sessionStorage.get("loginID").toString();
		Map<String, Object> memNo = memdao.getMemberInfo(loginId);

		String mem =memNo.get("MEM_NO").toString().trim();
		Map<String,Object> selectReNo =null;

		while(true) {
			System.out.print("  ≒ 蕾熱 廓�� 殮溘>> ");
			selectReNo=dao.reserveInfo(mem,ScanUtil.nextLine().trim());
			if(selectReNo == null) {
				System.out.print("   п渡 蕾熱廓�ㄟ� 橈蝗棲棻. 蕾熱廓�ㄧ� 棻衛 �挫恉媮祤撚�.\n");
			}else if(!selectReNo.get("STATE_CD").equals("1") ) {
				System.out.print("   蕾熱醞 鼻鷓虜 蕨擒 鏃模 陛棟м棲棻.");
			}
			else {
				break;
			}
		}

		int result = 0;

		DateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");//蕨擒�騆螺純�
		System.out.println("=====================================================[蕨擒褻��/鏃模]=====================================================");
		
		PrintUtil.printfln("|%-25o|%-11o|%-23o|%-45o|%-20o|%-10o|%-8o|%-8o|", " ", 
				"蕾熱廓��            ", "掘碟 ", "薯ヶ貲\t\t", "堅濰隸鼻", "堅濰撮睡頂羲        \t\t","霞ч鼻鷓","憮綠蝶蕨擒橾  ", "憮綠蝶衛除");
		PrintUtil.printbar2();
		PrintUtil.printf("|%-14o|"," ",selectReNo.get("RP_NO"));//蕾熱廓��
		PrintUtil.printf("%-10o|"," ",selectReNo.get("GUBUN_NM"));//掘碟
		PrintUtil.printf("%-4o|"," ",selectReNo.get("ITEM_NM"));//薯ヶ貲
		PrintUtil.printf("%-23o|"," ",selectReNo.get("CONTENTS_NM"));//堅濰隸鼻
		PrintUtil.printf("%-50o|"," ",selectReNo.get("RP_DETAIL"));//堅濰撮睡頂羲
		PrintUtil.printf("%-8o|"," ",selectReNo.get("STATE_NM"));//霞ч鼻鷓
		//憮綠蝶衛濛橾
		if (selectReNo.get("RP_RESERVEDT").equals("-")) {
			PrintUtil.printf("         %-8o|", " ", selectReNo.get("RP_RESERVEDT"));
		}else {
			String startdate=formatter.format(selectReNo.get("RP_RESERVEDT"));
			PrintUtil.printf("%-8o|", " ", startdate);
		}
		
		PrintUtil.printfln("%-8o|"," ",selectReNo.get("RP_RESERVERM"));//憮綠蝶衛除
		PrintUtil.printbar2();
		PrintUtil.printbarlong();

		System.out.print("--摹鷗ж褐 蕾熱頂羲曖 薑爾蒂 �挫恉媮祧宒藩�.\n  ≒ 蕾熱鏃模蒂 ж衛啊蝗棲梱?(Y/N) >> ");
		String yesNo = ScanUtil.nextYN();
		if(yesNo.equals("Y")||yesNo.equals("y")) {
			String rpNo=selectReNo.get("RP_NO").toString().trim();

			result = dao.rpNoDelete(rpNo);
		}else {
			System.out.println(" 餉薯蒂 鏃模 ж艘蝗棲棻.");
			PrintUtil.printbarlong();
			return View.MEMBER;
		}

		if(result>0) {
			System.out.println(" 蕾熱鏃模陛 諫猿 腎歷蝗棲棻.");
		}else {
			System.out.println(" 蕾熱鏃模 褒ぬ");
		}
		PrintUtil.printbarlong();
		return View.MEMBER;
	}
}
