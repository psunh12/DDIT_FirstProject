package service;

import java.util.Map;

import controller.Controller;
import dao.MemberDAO;
import util.PrintUtil;
import util.ScanUtil;
import util.View;

public class LoginService {
	// 싱글톤 패턴을 만든다.
	private static LoginService instance = null;

	private LoginService() {
	}

	public static LoginService getInstance() {
		if (instance == null)
			instance = new LoginService();
		return instance;
	}


	// Dao를 부른다
	MemberDAO dao = MemberDAO.getInstance();


	public View login() {
		System.out.print("  ● 아이디(ID) 입력 >> ");
		String id = ScanUtil.nextLine();
		Map<String, Object> memInfo = dao.getMemberInfo(id);
		if (memInfo == null) { 
			System.out.println("존재하지 않는 아이디입니다. 회원가입이 필요합니다.");
			return View.HOME;

		}
		while(true) {
			System.out.print("  ● 비밀번호(PW) 입력 >> ");
			String pass = ScanUtil.nextLine();

			// 테이블 소속?
			// 입력받은 아이디가 각 테이블에 소속되어있는지 확인

			// user,eng도 없다면 '회원가입이 필요합니다' 문구 출력
			// user테이블 있다면 로그인 000님 환영합니다 !

			// result 맵에는 한 개인의 데이터가 들어가있음. 한 튜플!

			// 회원정보가 있는지 여부 확인
			// 입력 받은 아이디가 회원 아이디인지 엔지니어 아이디어인지 판별
			// System.out.println(result.get("MEM_NAME") + "님! 로그인 되었습니다"); 문구 수정

			Map<String, Object> result = dao.login(id, pass);


			//if문 or dao 문 생성하기
	
			System.out.println();
			PrintUtil.printbarlong();
			if(result != null && result.get("MEM_ID").equals(id) && result.get("MEM_PW").equals(pass)) {
				System.out.println("-- "+result.get("MEM_NAME") + "고객님! 반갑습니다!");
				System.out.println();
				Controller.sessionStorage.put("login", true);
				Controller.sessionStorage.put("loginInfo", result);
				Controller.sessionStorage.put("loginID", result.get("MEM_ID").toString().trim());

				return View.MEMBER;

			}else {
				System.out.println("비밀번호 오류입니다. 다시 로그인 해주세요.");
				login();
			}

			return View.MEMBER;
		}
	}
}