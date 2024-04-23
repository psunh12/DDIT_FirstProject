package util;

public enum View {
   HOME,      // 로그인, 회원가입 화면
   
   SIGNUP,  // 회원가입
   LOGIN,     // 로그인
   
   MEMBER, // 회원 메인화면
   MEMBER_RESERVE,  // 회원 수리 예약 접수 화면
   MEMBER_LIST,   // 단순 조회성 화면에, 건 선택 후 취소 기능
   MEMBER_LISTDEL,// 삭제화면
   
   REPAIR_CONTENTS,  // 고장 증상 출력하는 부분?
   
   ENGANDSYS,   // 1.엔지니어 로그인 2. 관리자 로그인 0. 돌아가기
   
   ENGSYSMAIN,   // 1. 마이페이지 2. 엔지니어 조회   
   ENGINEER_LIST,     // 2. 등록된 엔지니어 리스트 단순조회/신규등록or삭제   
   ENGINEER_MY,      //엔지니어 별 배당받은 리스트 출력 화면   

   ENGINEER_ADD,      // 엔지니어 신규 등록
   ENGINEER_MODIFY,// 엔지니어 수정/삭제
}