package controller;
// 프로젝트 시작점이기 때문에 main() 메서드 있음!
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

	// 세션
	static public Map<String, Object> sessionStorage = new HashMap<>();

	////// 
	MemberService memberService = MemberService.getInstance();
	LoginService loginService = LoginService.getInstance();
	ReserveService reserveService=ReserveService.getInstance();
	EngineerService engineerService=EngineerService.getInstance();

	// 겟인스턴스 메서드는 다 싱글톤 패턴으로 되어있다는 의미. 
	// 싱글톤이 아니면 new 연산자로 객체 생성됐을거야

	//Controller 에서는 서비스 객체만 생성됨.

	public static void main(String[] args) {
		new Controller().start();
	}

	private void start() {
		sessionStorage.put("login", false);	// false: 로그인 안됨
		// 로그인 값이 true이면 로그인 된 상태
		sessionStorage.put("loginInfo", null);
		sessionStorage.put("loginID", null);


		View view = View.HOME;//나중에 HOME로 바꾸기
		while(true) {
			// 무한루프네용?
			switch (view) {
			case HOME: view = home(); break;
			// 메인메뉴 출력 및 메뉴 선택
			// 메인 메뉴인 home() 메서드가 실행되고나서, 
			// 다음 실행될 메뉴가 반환되는 것!

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
		System.out.print("  ● 번호 입력(숫자) >> ");
		switch (ScanUtil.nextInt()) {
		case 1: return View.LOGIN;
		case 2: return View.SIGNUP;
		case 3 : return View.ENGANDSYS;
		default: 
			System.err.println("화면에 출력된 번호(숫자)를 입력해주세요.");
			return View.HOME;
		}
	}

}

