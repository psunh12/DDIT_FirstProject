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
	//���� �ٹ����� �����Ͼ� ����Ʈ �ҷ���
	List<Map<String, Object>> engListInfo =engdao.getEngineerList(1);

	// ��������ȭ��
	public View reserve() {
		PrintUtil.printbarlong();
		System.out.println("�������������������������������� [��������ȭ��] ������������������������������������");
		System.out.println("��                                             ��");
		System.out.println("��             ���� �� ��ǰ�� �����ϼ���                            ��");
		System.out.println("��---------------------------------------------��");
		System.out.println("�� �������������������������������������������������������������������������������������� ��");
		System.out.println("�� �� 1.����Ʈ��  2.��Ʈ��  3.��ġ  4.�º�  0.�ڷΰ���    �� ��");
		System.out.println("�� �������������������������������������������������������������������������������������� ��");
		System.out.println("��                                             ��");
		System.out.println("����������������������������������������������������������������������������������������������");

		System.out.print("  �� ��ȣ �Է�(����) >> ");
		int gubun = ScanUtil.nextInt(); 
		System.out.println();
		PrintUtil.printbarlong();
		switch (gubun) {
		case 0:
			return View.MEMBER;
		case 1: case 2: case 3 : case 4 :
			return reserveProcess(gubun);
		default: 
			System.err.println("ȭ�鿡 ��µ� ��ȣ(����)�� �Է����ּ���.");
			return View.MEMBER_RESERVE;
		}
	}


	public View reserveProcess(int gubun) {
		List<Object> repairParam = new ArrayList<Object>();

		// �𵨸� ���
		List<Map<String, Object>> gubunList = dao.gubunList(gubun);
		List<Map<String, Object>> modelNum = new ArrayList<Map<String, Object>>();

		// "GUBUN" ����ȭ�� �Դϴ� ex)����Ʈ�� ����ȭ�� �Դϴ�
		System.out.println("�������������������������������� [��������ȭ��] ������������������������������������");
		System.out.println("��                                             ��");
		System.out.println("��            ��ǰ�� �𵨸��� �����ϼ���.             ��");
		System.out.println("��---------------------------------------------��");
		PrintUtil.printfln("��"+"��ȣ"+"|%-18o|", " ", "��ǰ�̸�\t\t\t\t"+"");

		for (Map<String, Object> item : gubunList) {
			System.out.print("�� "+item.get("ROWNUM")+" "+"��");
			PrintUtil.printf("%-18o", " ", item.get("ITEM_NM")+"\t      ��");
			System.out.println();
			modelNum.add(item);//����-:1 �ؾ���.(�Ķ���Ϳ� -1��!)
		}
		System.out.println("����������������������������������������������������������������������������������������������");
		System.out.print("  �� ��ȣ �Է�(����) >> ");
		// �𵨸� ����
		int itemIdNum=0;
		while(true) {
			itemIdNum = ScanUtil.nextInt();
			if(itemIdNum>modelNum.size()) {
				System.out.println("�ش��ȣ�� ��ġ�ϴ� �𵨸��� �����ϴ�. �ٽ��Է��ϼ��� >>");
			}else {
				modelNum.get(itemIdNum-1);// �Է¹��� ��°�� item���̵� ������ �´�//����
				break;
			}
		}
		System.out.println();
		PrintUtil.printbarlong();
		
		
		// *����: ����Ʈ��>>�𵨸�(�ٹж� �����غ���)
		// �������� ����Ʈ
		// 1.(100)���͸� ���� 2.(200)���� ���� 3.(300)��Ʈ��ũ �� ���Ṯ�� 4.(400)�������� 5.��Ÿ
		System.out.println("�������������������������������� [��������ȭ��] ������������������������������������");
		System.out.println("��                                             ��");
		System.out.println("��              ���� ������ �����ϼ���.              ��");
		System.out.println("��---------------------------------------------��");
		System.out.println("�� �������������������������������������������������������������������������������������� ��");
		System.out.println("�� ��                                         �� ��");
		System.out.println("�� �� 1.���͸� ����   2.��������   3.��Ʈ��ũ �� ���Ṯ��     �� ��");
		System.out.println("�� ��                                         �� ��");
		System.out.println("�� �� 4.��������       5.��Ÿ                                               �� ��");
		System.out.println("�� ��                                         �� ��");
		System.out.println("�� �������������������������������������������������������������������������������������� ��");
		System.out.println("����������������������������������������������������������������������������������������������");
		System.out.print("  �� ��ȣ �Է�(����) >> ");
		int contentsNum = ScanUtil.nextInt();
		System.out.println();
		PrintUtil.printbarlong();	

		List<Map<String, Object>> contentsList = dao.contentsList(contentsNum);
		List<Map<String, Object>> detailNum = new ArrayList<Map<String, Object>>();
		// �������� ����Ʈ ��� �ǵ���

		int detailno=0;

		//5�� ��Ÿ ������ ���������� ��� ������.
		if(contentsNum != 5) {
			System.out.println("���������������������������������� [��������ȭ��] ��������������������������������������");
			System.out.println("��                                               ��");
			System.out.println("��             ���� ���������� �����ϼ���.              ��");
			System.out.println("��-----------------------------------------------��");
			PrintUtil.printfln("��"+"��ȣ"+"|%-15o", " ", "���弼������                                    \t\t"+"��");
			
			for (Map<String, Object> contents : contentsList) {
				System.out.print("�� "+contents.get("ROWNUM")+" "+"��");
				contents.get("CONTENTS_CD");
				PrintUtil.printf("%-15o", " ", contents.get("CONTENTS_NM")+"                      \t��");
				System.out.println();

				detailNum.add(contents);//����:-1 �ؾ���.
			}
			//���弼������ ����
			System.out.println("��������������������������������������������������������������������������������������������������");
			System.out.print("  �� ��ȣ �Է�(����) >> ");
			
			while(true) {
				detailno = ScanUtil.nextInt();
				if(detailno>detailNum.size()) {
					System.out.println("�ش��ȣ�� ��ġ�ϴ� ������ �����ϴ�. �ٽ��Է��ϼ��� >>");
				}else {
					detailNum.get(detailno-1);// �Է¹��� ��°�� item���̵� ������ �´�//����
					break;
				}
			}

		}

		System.out.println();
		PrintUtil.printbarlong();
		
		//������ �Է�:null ����.
		System.out.println("���������������������������������� [��������ȭ��] ��������������������������������������");
		System.out.println("��                                               ��");
		System.out.println("��            ���� �������� �ۼ� ���ּ���.             ��");
		System.out.println("��-----------------------------------------------��");
		System.out.print("  �� ���� �Է�(����) >> ");
		String rpDetail = ScanUtil.nextLine();
		System.out.println("��������������������������������������������������������������������������������������������������");


		System.out.println();
		PrintUtil.printbarlong();
		System.out.println("���������������������������������� [��������ȭ��] ��������������������������������������");
		System.out.println("��                                               ��");
		System.out.println("��             ���� ��¥�� �ð��� �Է��ϼ���.            ��");
		System.out.println("��-----------------------------------------------��");
		// �����Ͼ� ENG002~ENG005 �� ���� ���� ��������.
		// �ð��� �δ� 3�� �������� ���� �����̰� 1�ð��� �ִ� 12�� ���డ��
		// �ٹ��ð� 8�ð� �������� 1�� �ִ� 96�� ���డ��(4*3*8)


		List<Object> param = new ArrayList<Object>();
		String rsTimestr="";
		int rsTime = 0;

		System.out.println("--���� ��� ��¥�� �Է��ϼ���.");
		System.out.print("  �� ���� ��� ��¥ �Է� (0000-00-00) >> ");
		String rsDate = ScanUtil.nextDate();
		param.add(rsDate);

		System.out.println("--���� ��� �ð��� �Է��ϼ��� \n  (�������ɽð�: 9��~17��/24�÷� �Է����ּ���)");
		while(true) {

			while(true) {
				System.out.print("  �� ���� ��� �ð� �Է�(00) >> ");
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
						System.out.println("�Է��� �ð��� ���� �����Ǿ����ϴ�. �ٸ� �ð��� �������ּ���.");
						param.remove(param.size()-1);
					}else {
						System.out.println();
						System.out.println("��-------------- ������ �Ϸ� �Ǿ����ϴ�.---------------��");
						System.out.println("��������������������������������������������������������������������������������������������������");
						System.out.println();
						break;
					}
				}else {
					System.out.println("  ���� ���� �ð��� �ƴմϴ�. \n  �ٽ� �Է����ּ���.");
				}
			}
			//		}
			
			System.out.print("  �� ���� �Ͻðڽ��ϱ�?(Y/N) >> ");
			String yesNo = ScanUtil.nextYN();
			PrintUtil.printbarlong();
			System.out.println();
			
			if (yesNo.equals("Y")||yesNo.equals("y")) {

				// �����Ͼ� ���� ���� �ڵ� ~
				String loginId = Controller.sessionStorage.get("loginID").toString();
				Map<String, Object> memNo = memdao.getMemberInfo(loginId);

				String engassign = null;

				ArrayList<String> totengList = new ArrayList<>(engListInfo.size());
				for (int i = 0; i < engListInfo.size(); i++) {
					totengList.add(engListInfo.get(i).get("ENG_NO").toString().trim()); 
				}

				//�������� �ٹ����� �����Ͼ� �� 1�� �ҷ���
				int selOneEng = selectOne(engListInfo.size());

				//�����ڵ� ����
				String rpNoLast=null;
				String rpNo=null;
				rpNoLast=rsDate.replace("-", "")+rsTimestr;

				// �ش� �Ͻÿ� ���� ������ ���� ������ȣ ������(rownum =1)
				Map<String, Object> rpNoList =dao.rpNoCheck(rpNoLast);//rpNoList=0�̸� ������ ���� ���°�. _001 �ο�.
				//nullpoint ������ ������.     
				if(rpNoList==null) {

					engassign = engListInfo.get(selOneEng).get("ENG_NO").toString().trim();//�ٹ��ϴ� �����Ͼ� �� ���� 1�� �ҷ����� �����Ͼ� ��ȣ String���� �ҷ���.��������
					rpNo=rpNoLast+"_"+"001";
				}
				else {
					String orderstr =rpNoList.get("RP_NO").toString().trim().substring(11);//������.substring        
					int ordernum=Integer.parseInt(orderstr);

					if(ordernum<engListInfo.size()*3) { ordernum+= 1;
					}
					rpNo=rpNoLast+"_"+String.format("%03d", ordernum);
					//int�� string���� ����
					rpNo = rpNo.trim();
				}

				//�����Ͼ� ����
				//�����Ͼ� �� �ش� ���ڽð��� �� �� �ο����� �������� ��ȸ���� 
				//�����Ͼ��� 3�� ������ �����Ͼ� ����� �����
				List<Map<String, Object>> engCountList =dao.engCount(rpNoLast);
				ArrayList<String> engList = null;

				// �ش� �Ͻÿ� ���� ������ ���� ���� �ž�.
				if (rpNoList != null) {//������ȣ ����
					if (engCountList == null) {
						engassign =engListInfo.get(selOneEng).get("ENG_NO").toString().trim();
					}else if (engCountList.size() > 0 && engCountList.size() < engListInfo.size()) {
						engList = new ArrayList<>(engCountList.size());
						//3����� �����Ͼ��� �����Ͼ� �ڵ带 list�� �־���

						for (int i = 0; i < engCountList.size(); i++) {//����� 0�̶� �Ȱɸ�..
							engList.add(engCountList.get(i).get("ENG_NO").toString().trim());
						}
						//3����� �����Ͼ��� �����Ͼ� �ڵ带 ���°�!
						for (int i = 0; i < engList.size(); i++) {
							totengList.remove(engList.get(i).toString());
						}
						int assignidx = selectOne(totengList.size());
						engassign =totengList.get(assignidx);
						//���� ��ȣ ����....
					}
				}

				//������,item_id,contents_cd
				repairParam.add(rpNo);//RP_NO (�ڹٷ� �����ϱ�)
				repairParam.add(memNo.get("MEM_NO").toString().trim());//MEM_NO (ȸ����ȣ)      
				repairParam.add(modelNum.get(itemIdNum-1).get("ITEM_ID").toString().trim());//ITEM_ID(�𵨾��̵�)
				//CONTENTS_CD (�����ڵ�)
				if (contentsNum == 5) {
					repairParam.add("500");
				}else {
					repairParam.add(detailNum.get(detailno-1).get("CONTENTS_CD").toString().trim());//CONTENTS_CD (�����ڵ�)
				}
				repairParam.add(rpDetail);//RP_DETAIL(�����󼼳���)
				repairParam.add(rsDate);//���೯¥
				repairParam.add(rsTimestr);//����ð�
				repairParam.add("1");//STATE_CD=1(������), data�� char�̹Ƿ� String���� ������.
				repairParam.add(engassign);//�����Ͼ����

				int insertres=dao.insertRepair(repairParam);
				if(insertres==1) {
					System.out.println("--���� �Ϸ��Դϴ�. �Ʒ� ������ Ȯ�����ּ���.--");
					System.out.println();
					printReceipt(repairParam);
					return View.MEMBER_LIST;
					
				}
				else {
					System.out.println("  ���� ���� �Դϴ�. �ٽ� �õ����ּ���.");
					return View.MEMBER_RESERVE;
				}
			}else {
				System.out.println("  ���� ����Ͽ����ϴ�.");
				return View.MEMBER;

			}
		}
	}
	
	
	public int selectOne(int size) {          
		Random r = new Random();                           
		int rnd = r.nextInt(size);   // 0 ~ 4 ������ ���� ����
		return rnd;
	}

	public void printReceipt(List<Object> param) {
		// ������ ����ϱ�1
		//ȸ����ȣ �ҷ�����!
		String rpNoReceipt=param.get(0).toString().trim();
		Map<String, Object> getReceipt = dao.getReceipt(rpNoReceipt);



		System.out.println("������������������������������������* �� �� �� *����������������������������������������");
		System.out.println("��                                             ��");
		System.out.printf("��   1) ������ȣ: %s                 *\n", param.get(0).toString().trim());
		System.out.printf("��   2) �̸�: %s\n",getReceipt.get("MEM_NAME").toString().trim());//�̸��� ���;���...
		System.out.printf("��   3) ������ǰ: %s\n", getReceipt.get("ITEM_NM").toString().trim());//��ǰ�� ����ϱ�
		System.out.printf("��   4) ����: %s\n", getReceipt.get("CONTENTS_NM").toString().trim());//�������� ����ϱ�
		System.out.printf("��   5) ������: %s\n", param.get(4));
		System.out.printf("��   6) ���೯¥: %s\n", param.get(5));
		System.out.printf("��   7) ����ð�: %s\n", param.get(6)+"��");
		System.out.printf("��   8) ���������: %s                                                  *\n", getReceipt.get("ENG_NAME").toString().trim());//�����Ͼ��..
		System.out.println("��                                             ��");
		System.out.println("����������������������������������������������������������������������������������������������");
		System.out.println();
		

		//���� ���ư���

	}

	//������ȸȭ��
	public View reservelist() {

		String loginId = Controller.sessionStorage.get("loginID").toString();
		Map<String, Object> memNo = memdao.getMemberInfo(loginId);


		String memStr=memNo.get("MEM_NO").toString();
		List<Map<String, Object>> reserveList =dao.reserveList(memStr);

		PrintUtil.printbarlong();
	    System.out.println("=====================================================[������ȸ/���]=====================================================");
		//null���� �� ä���ֱ�
	    if(reserveList==null) {
	    	System.out.println("���� ������ �����ϴ�. ���� ���� ���ּ���.");
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

		DateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");//���������¥
		DateFormat formatter1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//������ �Ϸ� �Ͻ�

		//������ȣ�����Ц���ǰ���������󦢰��弼�γ��������񽺿����Ϧ����񽺽ð���������¦����񽺴���ڦ����񽺽����Ϧ����������Ϧ�������������
		PrintUtil.printfln("|%-25o|%-11o|%-23o|%-47o|%-46o|%-24o|%-17o|%-8o|%-8o|%-8o|%-8o|%-50o|%-10o|", " ", 
				"������ȣ            ", "���� ", "��ǰ��\t\t", "��������", "���弼�γ���\t","�������","���񽺴����","���񽺿�����", "���񽺽ð�", "���񽺽�����", "����������", "�������", "���");
		PrintUtil.printbar2();
		for(Map<String, Object> item : reserveList) {

			PrintUtil.printf("|%-14o|"," ",item.get("RP_NO"));//������ȣ
			PrintUtil.printf("%-10o|"," ",item.get("GUBUN_NM"));//���и�
			PrintUtil.printf("%-4o|"," ",item.get("ITEM_NM"));//��ǰ��
			PrintUtil.printf("%-23o|"," ",item.get("CONTENTS_NM").toString());//��������
			PrintUtil.printf("%-50o|"," ",item.get("RP_DETAIL"));//���弼�γ���
			PrintUtil.printf("%-8o|"," ",item.get("STATE_NM"));//�������
			PrintUtil.printf("%-8o|"," ",item.get("ENG_NAME"));//���񽺴����			
			
			String date = formatter.format(item.get("RP_RESERVEDT"));
			PrintUtil.printf("%-8o|"," ",date);//���񽺿�����
			PrintUtil.printf("%-8o|"," ",item.get("RP_RESERVERM"));//���񽺽ð�

			//���񽺽�����
			if (item.get("RP_STARTDATE").equals("-")) {
				PrintUtil.printf("         %-8o|", " ", item.get("RP_STARTDATE"));
			}else {
				String startdate=formatter1.format(item.get("RP_STARTDATE"));
				PrintUtil.printf("%-8o|", " ", startdate);
			}
			//����������
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

		//�������� ���� �����ϵ��� �ϱ�
		PrintUtil.printbar2();
		PrintUtil.printbarlong();
		System.out.println("--���Ͻô� �޴��� �����ϼ���.");
		System.out.println("  1. �������      2. ����ȭ��");
		System.out.print("  �� ��ȣ �Է� >> ");
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

//������Ҹ���Ʈ
	public View reserveDelete() {
		String loginId = Controller.sessionStorage.get("loginID").toString();
		Map<String, Object> memNo = memdao.getMemberInfo(loginId);

		String mem =memNo.get("MEM_NO").toString().trim();
		Map<String,Object> selectReNo =null;

		while(true) {
			System.out.print("  �� ���� ��ȣ �Է�>> ");
			selectReNo=dao.reserveInfo(mem,ScanUtil.nextLine().trim());
			if(selectReNo == null) {
				System.out.print("   �ش� ������ȣ�� �����ϴ�. ������ȣ�� �ٽ� Ȯ�����ּ���.\n");
			}else if(!selectReNo.get("STATE_CD").equals("1") ) {
				System.out.print("   ������ ���¸� ���� ��� �����մϴ�.");
			}
			else {
				break;
			}
		}

		int result = 0;

		DateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");//���������¥
		System.out.println("=====================================================[������ȸ/���]=====================================================");
		
		PrintUtil.printfln("|%-25o|%-11o|%-23o|%-45o|%-20o|%-10o|%-8o|%-8o|", " ", 
				"������ȣ            ", "���� ", "��ǰ��\t\t", "��������", "���弼�γ���        \t\t","�������","���񽺿�����  ", "���񽺽ð�");
		PrintUtil.printbar2();
		PrintUtil.printf("|%-14o|"," ",selectReNo.get("RP_NO"));//������ȣ
		PrintUtil.printf("%-10o|"," ",selectReNo.get("GUBUN_NM"));//����
		PrintUtil.printf("%-4o|"," ",selectReNo.get("ITEM_NM"));//��ǰ��
		PrintUtil.printf("%-23o|"," ",selectReNo.get("CONTENTS_NM"));//��������
		PrintUtil.printf("%-50o|"," ",selectReNo.get("RP_DETAIL"));//���弼�γ���
		PrintUtil.printf("%-8o|"," ",selectReNo.get("STATE_NM"));//�������
		//���񽺽�����
		if (selectReNo.get("RP_RESERVEDT").equals("-")) {
			PrintUtil.printf("         %-8o|", " ", selectReNo.get("RP_RESERVEDT"));
		}else {
			String startdate=formatter.format(selectReNo.get("RP_RESERVEDT"));
			PrintUtil.printf("%-8o|", " ", startdate);
		}
		
		PrintUtil.printfln("%-8o|"," ",selectReNo.get("RP_RESERVERM"));//���񽺽ð�
		PrintUtil.printbar2();
		PrintUtil.printbarlong();

		System.out.print("--�����Ͻ� ���������� ������ Ȯ�����ֽʽÿ�.\n  �� ������Ҹ� �Ͻðڽ��ϱ�?(Y/N) >> ");
		String yesNo = ScanUtil.nextYN();
		if(yesNo.equals("Y")||yesNo.equals("y")) {
			String rpNo=selectReNo.get("RP_NO").toString().trim();

			result = dao.rpNoDelete(rpNo);
		}else {
			System.out.println(" ������ ��� �Ͽ����ϴ�.");
			PrintUtil.printbarlong();
			return View.MEMBER;
		}

		if(result>0) {
			System.out.println(" ������Ұ� �Ϸ� �Ǿ����ϴ�.");
		}else {
			System.out.println(" ������� ����");
		}
		PrintUtil.printbarlong();
		return View.MEMBER;
	}
}
