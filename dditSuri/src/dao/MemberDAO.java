package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class MemberDAO {
   private static MemberDAO instance = null;
   private MemberDAO() {}
   public static MemberDAO getInstance() {
      if(instance == null) instance = new MemberDAO();
      return instance;
   }
   
   JDBCUtil jdbc = JDBCUtil.getInstance();
   
   public int signUp(List<Object> param) {
      return jdbc.update(" INSERT INTO TB_MEMBER (MEM_NO, MEM_ID, MEM_PW, MEM_NAME, MEM_PHONE)"
                                                   + " VALUES (fn_create_mem_no, ?, ?, ?, ?)", param);
   }
   
   public Map<String, Object> getMemberInfo(String mid) {
      String sql = "SELECT * FROM TB_MEMBER WHERE MEM_ID = '"+mid+"'";
      return jdbc.selectOne(sql);
   }
   
	public Map<String, Object> login(String id, String pass){
		// 로그인
		// 내가 입력한 아이디, 비밀번호에 해당하는 회원정보를 주세요
		// SELECT * FROM MEMBER WHERE MEM_ID = ? AND MEM_PW = ?
		// SELECT * FROM MEMBER WHERE MEM_ID = 'admin' AND MEM_PW = '1234'
		String sql = "SELECT * FROM TB_MEMBER WHERE MEM_ID = ? AND MEM_PW = ?";
		List<Object> param = new ArrayList<Object>();
		param.add(id);
		param.add(pass);
		
		return jdbc.selectOne(sql, param);
	}
}






