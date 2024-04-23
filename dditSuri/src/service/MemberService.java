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
   // �̱��� ���� ó�� ���� �κ�
   private static MemberService instance = null;
   private MemberService() {}
   public static MemberService getInstance() {
      if(instance == null) instance = new MemberService();
      return instance;
   }

   MemberDAO dao = MemberDAO.getInstance();

   //ȸ�� ���
   public View signUp() {
	   PrintUtil.printbarlong();
		System.out.println("������������������������������������ [ ȸ������  ]����������������������������������������");
		System.out.println("��                                               ��");
		System.out.println("��               �ش� �׸��� �Է��ϼ���.               ��");
		System.out.println("��-----------------------------------------------��");

	   
	  String id = null;
      String pass = null;
      // ���̵� Ư������ �Է��ߴ��� üũ �κ�
      while(true) {
         System.out.print("  �� ���̵�(ID) >> ");
         id = ScanUtil.nextIdPass("id").trim();
         //���̵� �ߺ�üũ            
         Map<String, Object> res = dao.getMemberInfo(id);
         if (res != null ) {
            System.out.println("�̹� ���ǰ� �ִ� ID�Դϴ�. �ٸ� ID�� �Է��ϼ��� ");
         } else if (id.length() >10){
            System.out.println("ID�� �ִ� 10�ڱ��� �����մϴ�. �ٽ� �Է��ϼ���.");
         }else break;
      }

      System.out.print("  �� �̸� >> ");
      String name = ScanUtil.nextHangeul().trim();
      System.out.print("  �� ��ȭ��ȣ(000-0000-0000) >> ");
      String phone = ScanUtil.nextPhone().trim();

      while(true) {
         System.out.print("  �� ��й�ȣ(PW) >> ");
         pass = ScanUtil.nextIdPass("pass").trim();
         if (pass.length() >20) {
            System.out.println("��й�ȣ�� �ִ� 20�ڱ��� �����մϴ�. \n�ٽ� �Է����ּ���.");
         }else break;
      }

      List<Object> param = new ArrayList<Object>();
      param.add(id);
      param.add(pass);
      param.add(name);
      param.add(phone);

      int result = dao.signUp(param);
      if (result > 0) {
    	  
         System.out.println(" --ȸ�� ���� ����! �α��� ���ּ���.");
      }else {
         System.out.println(" --ȸ������ ����");
      }
      System.out.println("��������������������������������������������������������������������������������������������������");
      PrintUtil.printbarlong();
      return View.HOME;
   }
   public View home() {

	   //������! Ȩȭ��, �α׾ƿ�
	   System.out.println("�������������������������������� [ ���������� ] ������������������������������������");
	   System.out.println("��                                             ��");
	   System.out.println("��            ���Ͻô� �޴��� �����ϼ���.             ��");
	   System.out.println("��---------------------------------------------��");
	   System.out.println("�� �������������������������������������������������������������������������������������� ��");
	   System.out.println("�� ��   1.��������     2.������ȸ/���     999.�α׾ƿ�      �� ��");
	   System.out.println("�� �������������������������������������������������������������������������������������� ��");
	   System.out.println("��                                             ��");
	   System.out.println("����������������������������������������������������������������������������������������������");

	   System.out.print("  �� ��ȣ �Է�(����) >> ");
	   switch (ScanUtil.nextInt()) {
	   
	   case 1: PrintUtil.printLine(0); return View.MEMBER_RESERVE;
	   case 2: PrintUtil.printLine(0); return View.MEMBER_LIST;
	   case 999: Controller.sessionStorage.clear();
	   return View.HOME;

	   default: 
		   System.out.println("ȭ�鿡 ��µ� ��ȣ(����)�� �Է����ּ���.");
		   return View.MEMBER;
	   }
   }
  

}