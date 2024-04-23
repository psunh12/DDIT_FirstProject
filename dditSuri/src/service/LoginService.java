package service;

import java.util.Map;

import controller.Controller;
import dao.MemberDAO;
import util.PrintUtil;
import util.ScanUtil;
import util.View;

public class LoginService {
	// �̱��� ������ �����.
	private static LoginService instance = null;

	private LoginService() {
	}

	public static LoginService getInstance() {
		if (instance == null)
			instance = new LoginService();
		return instance;
	}


	// Dao�� �θ���
	MemberDAO dao = MemberDAO.getInstance();


	public View login() {
		System.out.print("  �� ���̵�(ID) �Է� >> ");
		String id = ScanUtil.nextLine();
		Map<String, Object> memInfo = dao.getMemberInfo(id);
		if (memInfo == null) { 
			System.out.println("�������� �ʴ� ���̵��Դϴ�. ȸ�������� �ʿ��մϴ�.");
			return View.HOME;

		}
		while(true) {
			System.out.print("  �� ��й�ȣ(PW) �Է� >> ");
			String pass = ScanUtil.nextLine();

			// ���̺� �Ҽ�?
			// �Է¹��� ���̵� �� ���̺� �ҼӵǾ��ִ��� Ȯ��

			// user,eng�� ���ٸ� 'ȸ�������� �ʿ��մϴ�' ���� ���
			// user���̺� �ִٸ� �α��� 000�� ȯ���մϴ� !

			// result �ʿ��� �� ������ �����Ͱ� ������. �� Ʃ��!

			// ȸ�������� �ִ��� ���� Ȯ��
			// �Է� ���� ���̵� ȸ�� ���̵����� �����Ͼ� ���̵������ �Ǻ�
			// System.out.println(result.get("MEM_NAME") + "��! �α��� �Ǿ����ϴ�"); ���� ����

			Map<String, Object> result = dao.login(id, pass);


			//if�� or dao �� �����ϱ�
	
			System.out.println();
			PrintUtil.printbarlong();
			if(result != null && result.get("MEM_ID").equals(id) && result.get("MEM_PW").equals(pass)) {
				System.out.println("-- "+result.get("MEM_NAME") + "����! �ݰ����ϴ�!");
				System.out.println();
				Controller.sessionStorage.put("login", true);
				Controller.sessionStorage.put("loginInfo", result);
				Controller.sessionStorage.put("loginID", result.get("MEM_ID").toString().trim());

				return View.MEMBER;

			}else {
				System.out.println("��й�ȣ �����Դϴ�. �ٽ� �α��� ���ּ���.");
				login();
			}

			return View.MEMBER;
		}
	}
}