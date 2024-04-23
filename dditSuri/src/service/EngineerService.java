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
   // �̱��� ������ �����.
   private static EngineerService instance = null;
   private EngineerService() {}
   public static EngineerService getInstance() {
      if(instance == null) 
         instance = new EngineerService();
      return instance;
   }
   // �α����� �����Ͼ��� tb_engineer ������ �������
   public Map<String, Object> engInfo;

   EngineerDAO dao = EngineerDAO.getInstance();
   ReserveServiceDAO reserdao = ReserveServiceDAO.getInstance();

   int engorsys;

   public View login(){      
      System.out.print("  �� ������/enginner ID �Է� >> ");
      String id = ScanUtil.nextLine();
      Map<String, Object> engIdcheck = dao.getEngineerInfo("ENG_ID",id);
      if (engIdcheck == null) { 
         System.out.println("   ��ϵ� ������ �����ϴ�. �����ڿ��� �����ϼ���.");
         return View.ENGANDSYS;
      }
      while(true) {
         System.out.print("  �� ��й�ȣ(PW) �Է� >> ");
         String pass = ScanUtil.nextLine();

         // �Է� ���� �α��� ������ ȸ�� ���� �´��� ��ȸ
         Map<String, Object> result = dao.login(id,pass);
         if(result != null && result.get("ENG_ID").equals(id)){
            Controller.sessionStorage.put("login", true);
            Controller.sessionStorage.put("loginInfo", result.get("ENG_ID"));

            if (result.get("ENG_ADMIN").equals("N")) {
               PrintUtil.printbarlong();
               System.out.println("-- "+result.get("ENG_NAME") + " �����Ͼ��! �ݰ����ϴ�!");
               System.out.println();
               return View.ENGSYSMAIN;
            }else  {
               PrintUtil.printbarlong();
               System.out.println("-- "+result.get("ENG_NAME") + " �����ڴ�! �ݰ����ϴ�!");
               System.out.println();
               return View.ENGINEER_LIST;
            }
         }   
         else{
            System.out.println("ID�� PW�� ��ġ���� �ʽ��ϴ�. PW�� �ٽ� �Է����ּ���.");
         }
      }
   }

   public View engsys_main(){      
      this.engInfo = dao.getEngineerInfo("ENG_ID", (String)Controller.sessionStorage.get("loginInfo"));
      if (engInfo.get("ENG_ADMIN").toString().equals("Y")) {
      	   System.out.println("������������������������������������[ �� �� �� Ȩ ]������������������������������������");
       	   System.out.println("��                                             ��");
       	   System.out.println("��            ���Ͻô� �޴��� �����ϼ���.             ��");
       	   System.out.println("��---------------------------------------------��");
       	   System.out.println("�� �������������������������������������������������������������������������������������� ��");
       	   System.out.println("�� ��     1.�����Ͼ�/������ ��ȸ       999.�α׾ƿ�           �� ��");
       	   System.out.println("�� �������������������������������������������������������������������������������������� ��");
       	   System.out.println("��                                             ��");
       	   System.out.println("����������������������������������������������������������������������������������������������");
       	   System.out.print("  �� ��ȣ �Է�(����) >> ");

//         System.out.println("===========================================");
//         System.out.println("                     1.�����Ͼ� ���� 999.�α׾ƿ�");
//         System.out.println("===========================================");
//         System.out.print("��ȣ �Է� >> ");
         switch (ScanUtil.nextInt()) {
         case 1: return View.ENGINEER_MY;
         case 999: 
            Controller.sessionStorage.clear();
            return View.HOME;
         default: 
            System.err.println("ȭ�鿡 ��µ� ��ȣ(����)�� �Է����ּ���.");
            return View.ENGSYSMAIN;
         }
      }else {
    	  
   	   System.out.println("�������������������������������� [�����Ͼ� Ȩ  ] ������������������������������������");
   	   System.out.println("��                                             ��");
   	   System.out.println("��            ���Ͻô� �޴��� �����ϼ���.             ��");
   	   System.out.println("��---------------------------------------------��");
   	   System.out.println("�� �������������������������������������������������������������������������������������� ��");
   	   System.out.println("�� �� 1.����������  2.�����Ͼ�/������ ��ȸ  999.�α׾ƿ�   �� ��");
   	   System.out.println("�� �������������������������������������������������������������������������������������� ��");
   	   System.out.println("��                                             ��");
   	   System.out.println("����������������������������������������������������������������������������������������������");
   	   System.out.print("  �� ��ȣ �Է�(����) >> ");
   	   
         switch (ScanUtil.nextInt()) {
         case 1: return View.ENGINEER_MY;
         case 2: return View.ENGINEER_LIST;
         case 999: 
            Controller.sessionStorage.clear();
            return View.HOME;
         default: 
            System.err.println("ȭ�鿡 ��µ� ��ȣ(����)�� �Է����ּ���.");
            return View.ENGSYSMAIN;
         }
      }
   }

   public View eng_list() {
      // ��ü ����Ʈ ��ȸ
      // ������ /�����Ͼ� �Ѵ� ��� ���� ���������� �ƴ��� ǥ���ϰ�
      List<Map<String, Object>> engList = dao.getEngineerAll();

      System.out.println("=================================================");
      System.out.println("=================== �����Ͼ� LIST =================");
      PrintUtil.printfln("|%-10o|%-10o|%-7o|%-20o|%-3o|", " ", "�����Ͼ��ȣ", "�̸�", "����", "������ȣ", "������");
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
         PrintUtil.printfln("%54o", " ", "0. ���ư���");
         System.out.println("=================================================");
      }else {
         System.out.println("================================================");
         PrintUtil.printfln("%58o", " ", "1. �����Ͼ� �ű� ��� 2. �����Ͼ� ���� 0. ���ư���");
         System.out.println("================================================");
      }
      System.out.print("  �� ��ȣ �Է� >> ");
      switch (ScanUtil.nextInt()) {
      case 1: return View.ENGINEER_ADD;
      case 2: return View.ENGINEER_MODIFY;
      //�α��� ������ �����Ͼ�� �����ڳĿ� ���� ���ư��� �ܰ谡 �ٸ�
      case 0: return View.ENGSYSMAIN;   
      default: 
         System.err.println("ȭ�鿡 ��µ� ��ȣ(����)�� �Է����ּ���.");
         return View.ENGINEER_LIST;
      }
   }

   public View eng_add() {

		System.out.println("�������������������������������� [ �����Ͼ� ��� ] ������������������������������������");
		System.out.println("��                                               ��");
		System.out.println("��               �ش� �׸��� �Է��ϼ���.               ��");
		System.out.println("��-----------------------------------------------��");
//      System.out.println("[[ �����Ͼ� ��� ]]");
//      System.out.println("========================");
      String id = null;
      String pass = null;
      while(true) {
         System.out.print("  �� ���̵�(ID) >> ");
         id = ScanUtil.nextIdPass("id").trim();
         //���̵� �ߺ�üũ            
         Map<String, Object> res = dao.getEngineerInfo("ENG_ID",id);
         if (res != null ) {
            System.out.println("�̹� ���ǰ� �ִ� ID�Դϴ�. \n�ٸ� ID�� �Է��ϼ���.");
         } else if (id.length() >10){
            System.out.println("ID�� ����, ���ڸ� �����Ͽ� �ִ� 10�ڱ��� �����մϴ�. \n�ٸ� ID�� �Է��ϼ���.");
         }else break;
      }

      while(true) {
         System.out.print("  �� ��й�ȣ(PW) >> ");
         pass = ScanUtil.nextIdPass("pass").trim();
         if (pass.length() >20) {
            System.out.println("��й�ȣ�� �ִ� 20�ڱ��� �����մϴ�. \n�ٽ� �Է����ּ���.");
         }else break;
      }
      System.out.print("  �� �̸� >> ");
      String name = ScanUtil.nextHangeul().trim();
      System.out.print("  �� ������ȣ(000-000-0000) >> ");
      String tel = ScanUtil.nextPhone().trim();
      System.out.print("  �� ���� >> ");
      String postn = ScanUtil.nextHangeul().trim();
      System.out.print("  �� ������ �������� ����Ͻðڽ��ϱ�?(Y/N) >> ");
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
         System.out.println("--�����Ͼ ���������� ��ϵǾ����ϴ�.");
      }else {
         System.out.println("--�����Ͼ� ��� ����");
      }
      System.out.println("��������������������������������������������������������������������������������������������������");
      return View.ENGINEER_LIST;
   }

   public View eng_delete() {
      Map<String, Object> selectEngNo =null;
      while(true) {
         System.out.print("  �� �����Ͼ��ȣ >>  ");
         //���̵� ���� üũ
         selectEngNo = dao.getEngineerInfo("ENG_NO", ScanUtil.nextLine().trim());
         if (selectEngNo == null ) {
            System.out.println("--��ϵ� ������ �����ϴ�. �����Ͼ� ��ȣ�� �ٽ� Ȯ�����ּ���.\n");
         }else {
            break;
         }
      }
      List<Object> param = new ArrayList<Object>();
      int result = 0;
      PrintUtil.printfln("|%-13o|%-10o|%-7o|%-22o|", " ", "�����Ͼ��ȣ", "�̸�", "����", "������ȣ");
      PrintUtil.printf("|%-14o|", " ",  selectEngNo.get("ENG_NO"));
      PrintUtil.printf("%-9o|", " ",  selectEngNo.get("ENG_NAME"));
      PrintUtil.printf("%-7o|",  " ", selectEngNo.get("ENG_POSITION"));
      PrintUtil.printfln("%-16o|",  " ", selectEngNo.get("ENG_TEL"));
      System.out.print("�����Ͻ� �����Ͼ��� ������ Ȯ�����ֽʽÿ�.\n �����Ͼ� ������ �����Ͻðڽ��ϱ�?(Y/N) >> ");
      String yesNo = ScanUtil.nextLine();
      if (yesNo.equals("Y")||yesNo.equals("y")) {
         param.add("N");
         String engno = selectEngNo.get("ENG_NO").toString();
         param.add(engno);

         result = dao.delete(param);
      }else {
         System.out.println("������ ����Ͽ����ϴ�.");
         return View.ENGINEER_LIST;
      }
      if (result > 0) {
         System.out.println("�����Ͼ ���������� �����Ǿ����ϴ�.");
      }else {
         System.out.println("�����Ͼ� ���� ����");
      }
      return View.ENGINEER_LIST;
   }   

   public View eng_mypage() {      
	   List<Map<String, Object>> engList = dao.getEngineerMyPage(engInfo.get("ENG_ID").toString());
      System.out.println("======================================================================================================================");
      PrintUtil.printfln("%120o", " ", "   �����Ͼ� : "+ engInfo.get("ENG_NAME").toString());
      System.out.println("======================================================================================================================");


      //HashMap ��üũ
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
            "������ȣ", "ȸ����ȣ", "ȸ����", "��ǰ����", "�𵨸�", "��ǰ��", "��������", "���弼�γ���");
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

      PrintUtil.printfln("|%-16o|%-8o|%-10o|%-34o|%-32o|%-50o|%-10o|", " ", "���೯¥", "����ð�", "�������", "�����Ͻ�", "�����Ϸ��Ͻ�", "�������", "���");   
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
      PrintUtil.printfln("%123o", " ", "   1.���� �Ϸ� 2. ���� �Ϸ� ");
      System.out.println("======================================================================================================================");

      String rpNo = null;
      while(true) {
         while(true) {
            System.out.print("  �� ������ȣ �Է� >> ");
            rpNo = ScanUtil.nextLine().trim();

            //������ȣ ����� �Է��ߴ��� Ȯ��
            Map<String, Object> res = reserdao.getEngRpList("ENG002".toString(),rpNo);         
            if (res == null ) {
               System.out.println("���� ��ȣ�� ��ȿ���� �ʽ��ϴ�. �ٽ� �Է����ּ���.");
            } else{ break;}
         }
         System.out.print("  �� ��ȣ �Է� >> ");
         int upstate = ScanUtil.nextInt();
         Map<String, Object> statecheck = reserdao.getstateCheck(rpNo, upstate);   
         // ���� �Ϸ� ó��
         if (upstate == 1 && statecheck == null) {
            System.out.println("������ ���¸� ���� �Ϸ� �����մϴ�.");
         }else {
            System.out.println("�����Ͻ� ���� �� '"+rpNo+"'�� ���� �Ϸ� ó�� �Ͻðڽ��ϱ�?(Y/N)");

            String yesNo = ScanUtil.nextLine();
            //���� �Ϸ� ���� ������Ʈ(db��)
            if (yesNo.equals("Y")||yesNo.equals("y")) {
               int updateres = reserdao.updateState(rpNo, 1, null);
               if (updateres > 0) {
                  System.out.println("���� �Ϸ� ó�� �Ǿ����ϴ�.");
               }else {
                  System.out.println("���� �Ϸ� ó�� ����");
                  return View.ENGINEER_MY;
               }
            }else {
               System.out.println("���� �Ϸ� ó���� ��� �Ǿ����ϴ�.");
               return View.ENGINEER_MY;
            }
         }
      // ���� �Ϸ� ó��
      if (upstate == 2 && statecheck == null) {
         System.out.println("������ ���¸� ���� �Ϸ� �����մϴ�.");
      }else {
         System.out.println("��� ó���� �������ּ���.(Y:����/N:����)");
         String cost = ScanUtil.nextLine().trim();
         System.out.println("�����Ͻ� ���� �� '"+rpNo+"'�� ���� �Ϸ� ó�� �Ͻðڽ��ϱ�?(Y/N)");
         String yesNo = ScanUtil.nextLine();
         // ���� �Ϸ� ���� ������Ʈ(db��)
         if (yesNo.equals("Y")||yesNo.equals("y")) {
            int updateres = reserdao.updateState(rpNo, 3, cost);
            if (updateres > 0) {
               System.out.println("���� �Ϸ� ó�� �Ǿ����ϴ�.");
            }else {
               System.out.println("���� �Ϸ� ó�� ����");
               return View.ENGINEER_MY;
            }
         }else {
            System.out.println("���� �Ϸ� ó���� ��� �Ǿ����ϴ�.");
            return View.ENGINEER_MY;
         }
      }      
      //1. ���� �Ϸ�
      // ���� ��ȣ�� ��ȸ���� �� ���°��� 1���� �ƴϸ�, �������� ���� �Ǹ� ���� �Ϸ� �����մϴ�. ���
      // ������ȣ  �Է����� ���ư� 
      // ���� ��ȣ�� ��ȸ���� �� ���°��� 1���̸�, ������ �� �����Ͻ�(�ڵ�) ������Ʈ ġ��
      //2. ���� �Ϸ� 
      // ������ ������ ������ ���� ��ȸ > ������ ������ ���� �Ǹ� ���� �Ϸ� �����մϴ�. ���
      // ���� ��ȣ �Է����� ���ư�
      // ���� ��� ���� �ޱ� , ���� �Ϸ� �Ͻ�(�ڵ�) ������Ʈ ġ��
   }
}
}
