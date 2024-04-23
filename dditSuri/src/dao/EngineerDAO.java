package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class EngineerDAO {
   private static EngineerDAO instance = null;
   private EngineerDAO() {}
   public static EngineerDAO getInstance() {
      if(instance == null) instance = new EngineerDAO();
      return instance;
   }
   
   JDBCUtil jdbc = JDBCUtil.getInstance();

   public Map<String, Object> login(String id, String pass) {
      String sql = "SELECT * FROM TB_ENGINEER WHERE ENG_ID = ? AND ENG_PW = ?";
      List<Object> param = new ArrayList<Object>();
      param.add(id);
      param.add(pass);
      return jdbc.selectOne(sql, param);
   }
   
   public Map<String, Object> getEngineerInfo(String col, String mid) {
      String sql = null;
      if (col.equals("ENG_ID")) {
         sql = "SELECT * FROM TB_ENGINEER WHERE "+ col +"= '"+mid+"'";
      }else{
         sql = "SELECT * FROM TB_ENGINEER WHERE "+ col +"= UPPER('"+mid+"')";
      }
      return jdbc.selectOne(sql);
   }

   public Map<String, Object> getEngOrSys(int engorsys, String mid) {
      String sql = null;
      if (engorsys == 1) {
         sql = "SELECT * FROM TB_ENGINEER WHERE ENG_ADMIN = 'N' AND ENG_ID = '"+mid+"' ";
      }else {
         sql = "SELECT * FROM TB_ENGINEER WHERE ENG_ADMIN = 'Y' AND ENG_ID = '"+mid+"' ";
      }
      return jdbc.selectOne(sql);
   }
   
   public List<Map<String, Object>> getEngineerList(int engorsys) {
      String sql = null;
      if (engorsys == 1) {
         sql = "SELECT * FROM TB_ENGINEER E where E.ENG_USEYN = 'Y' AND E.ENG_ADMIN = 'N' ORDER BY E.ENG_NO";
      }else {
         sql = "SELECT * FROM TB_ENGINEER E where E.ENG_USEYN = 'Y' AND E.ENG_ADMIN = 'Y' ORDER BY E.ENG_NO";
      }
      return jdbc.selectList(sql);
   }
   
   public List<Map<String, Object>> getEngineerAll() {
      return jdbc.selectList(" SELECT * FROM TB_ENGINEER ORDER BY ENG_ADMIN DESC, ENG_NAME ");
   }
   

   public List<Map<String, Object>> getEngineerMyPage(String engid) {
      String sql = "  SELECT \r\n " + 
            "     A.RP_NO \"RP_NO\"\n " + 
            "    , A.MEM_NO \"MEM_NO\"\n " + 
            "    , b.MEM_NAME \"MEM_NAME\"\n " + 
            "    , C.GUBUN \"GUBUN\"\n " + 
            "    , C.IT_ID \"IT_ID\"\n " + 
            "    , C.IT_NM \"IT_NM\"\n " + 
            "    , D.CONTENTS_NM \"CONTENTS_NM\"\n " + 
            "    , A.RP_DETAIL \"RP_DETAIL\"\n " + 
            "    , A.RP_RESERVEDT \"RP_RESERVEDT\"\n " + 
            "    , A.RP_RESERVERM||'½Ã' \"RP_RESERVERM\"\n " + 
            "    , E.STATE_NM \"STATE_NM\"\n " + 
            "    , A.RP_STARTDATE \"RP_STARTDATE\"\n " + 
            "    , A.RP_ENDDATE \"RP_ENDDATE\"\n " + 
            "    , A.RP_RESULT \"RP_RESULT\"\n " + 
            "    , A.RP_COSTYN \"RP_COSTYN\"\n " + 
            "  FROM tb_repair A \n " + 
            "    JOIN tb_member B ON b.mem_no = a.mem_no\n " + 
            "    JOIN (\n " + 
            "            SELECT gb.gubun_nm gubun, it.item_id it_id, it.item_nm it_nm\n " + 
            "                FROM TB_ITEM it, tb_gubun gb  where it.gubun = gb.gubun\n " + 
            "            ) C\n " + 
            "    on a.item_id = C.it_id\n " + 
            "    JOIN tb_contents D ON d.contents_cd = a.contents_cd\n " + 
            "    JOIN tb_state E ON E.state_cd = A.STATE_CD\n " + 
            "  WHERE a.eng_no = (SELECT ENG_NO FROM tb_engineer WHERE eng_id = ? ) ";
      List<Object> param = new ArrayList<Object>();
      param.add(engid.trim());
      return jdbc.selectList(sql, param);
   }

   public int add(List<Object> param) {
      return jdbc.update(" INSERT INTO TB_ENGINEER (ENG_NO, ENG_ID, ENG_PW, ENG_NAME, ENG_TEL, ENG_POSITION, ENG_USEYN, ENG_ADMIN)"
                                          + "    VALUES (fn_create_eng_no,?,?,?,?,?,?,?)", param);
   }
   public int delete(List<Object> param) {
      return jdbc.update(" UPDATE TB_ENGINEER SET ENG_USEYN = ? WHERE ENG_NO = ? ", param);
   }
   
   
   
}