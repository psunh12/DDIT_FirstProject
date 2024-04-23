package controller;
// ������Ʈ �������̱� ������ main() �޼��� ����!
import java.util.HashMap;
import java.util.Map;

import service.EngineerService;
import service.LoginService;
import service.MemberService;
import service.ReserveService;
import util.PrintUtil;
import util.ScanUtil;
import util.View;

public class Controller {

	// ����
	static public Map<String, Object> sessionStorage = new HashMap<>();

	////// 
	MemberService memberService = MemberService.getInstance();
	LoginService loginService = LoginService.getInstance();
	ReserveService reserveService=ReserveService.getInstance();
	EngineerService engineerService=EngineerService.getInstance();

	// ���ν��Ͻ� �޼���� �� �̱��� �������� �Ǿ��ִٴ� �ǹ�. 
	// �̱����� �ƴϸ� new �����ڷ� ��ü ���������ž�

	//Controller ������ ���� ��ü�� ������.

	public static void main(String[] args) {
		new Controller().start();
	}

	private void start() {
		sessionStorage.put("login", false);	// false: �α��� �ȵ�
		// �α��� ���� true�̸� �α��� �� ����
		sessionStorage.put("loginInfo", null);
		sessionStorage.put("loginID", null);


		View view = View.HOME;//���߿� HOME�� �ٲٱ�
		while(true) {
			// ���ѷ����׿�?
			switch (view) {
			case HOME: view = home(); break;
			// ���θ޴� ��� �� �޴� ����
			// ���� �޴��� home() �޼��尡 ����ǰ���, 
			// ���� ����� �޴��� ��ȯ�Ǵ� ��!

			case SIGNUP: view = memberService.signUp();break;
			case LOGIN: view = loginService.login(); break;

			case MEMBER: view = memberService.home(); break;
			case MEMBER_RESERVE: view = reserveService.reserve(); break;
			case MEMBER_LIST: view = reserveService.reservelist();break;
			case MEMBER_LISTDEL: view = reserveService.reserveDelete();break;

			case ENGANDSYS: view = engineerService.login(); break;            
			case ENGSYSMAIN: view = engineerService.engsys_main(); break;                     
			case ENGINEER_LIST: view = engineerService.eng_list(); break;
			case ENGINEER_MY: view = engineerService.eng_mypage(); break;
			case ENGINEER_MODIFY: view = engineerService.eng_delete(); break;
			case ENGINEER_ADD: view = engineerService.eng_add(); break;
			}

		}
	}

	private View home() {
		System.out.println(sessionStorage.get("login"));
		System.out.println(sessionStorage.get("loginInfo"));

		PrintUtil.printhome();
		System.out.print("  �� ��ȣ �Է�(����) >> ");
		switch (ScanUtil.nextInt()) {
		case 1: return View.LOGIN;
		case 2: return View.SIGNUP;
		case 3 : return View.ENGANDSYS;
		default: 
			System.err.println("ȭ�鿡 ��µ� ��ȣ(����)�� �Է����ּ���.");
			return View.HOME;
		}
	}

}

