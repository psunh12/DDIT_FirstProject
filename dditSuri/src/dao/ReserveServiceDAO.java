package dao;


import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class ReserveServiceDAO {

	private static ReserveServiceDAO instance = null;

	private ReserveServiceDAO() {
	}

	public static ReserveServiceDAO getInstance() {
		if (instance == null)
			instance = new ReserveServiceDAO();
		return instance;
	}

	JDBCUtil jdbc = JDBCUtil.getInstance();

	// ��������ǰ ����ȭ��
	// ����Ʈ ���
	// ex) 1. ����Ʈ�� (SP),2.��Ʈ��(NP), 3.��ġ(WC) 4.�º�(TB)
	// sp�� 1������ �����ؼ� 1���� �ҷ��ö� �𵨸� ��ü �ҷ�����

	public List<Map<String, Object>> gubunList(int gubun) {
		String gubunNM = "";
		if (gubun == 1) {
			gubunNM = "SP";
		} else if (gubun == 2) {
			gubunNM = "NP";
		} else if (gubun == 3) {
			gubunNM = "WC";
		} else {
			gubunNM = "TB";
		}

		String sql = " SELECT ROWNUM, ITEM_ID, ITEM_NM FROM TB_ITEM WHERE GUBUN='" + gubunNM + "' ";
		return jdbc.selectList(sql);
	}

	//�����ڵ� ��������
	public List<Map<String, Object>> contentsList(int contentsNum) {

		String sql = " SELECT ROWNUM, CONTENTS_CD, CONTENTS_NM FROM TB_CONTENTS WHERE CONTENTS_CD like '" + contentsNum
				+ "%' AND CONTENTS_CD NOT LIKE '%00'";
		return jdbc.selectList(sql);

	}
	
	//�ش糯¥�� ������ �Ǽ� 
	public Map<String, Object> reserveDate(String rsDate) {
		String sql = " SELECT COUNT(*) FROM TB_REPAIR WHERE RP_RESERVEDT='" + rsDate + "'";
		return jdbc.selectOne(sql);
	}
	//�ش� ��¥, �ð��� ������ �Ǽ�
	public Map<String, Object> reserveTime(List<Object> param) {
		String sql = " SELECT COUNT(*) FROM TB_REPAIR WHERE RP_RESERVEDT= ? AND RP_RESERVERM= ?";
		return jdbc.selectOne(sql, param);
	}

	
	//�Էµ�  ��¥, �ð��� ������ȣ ��ȸ	
	public Map<String, Object> rpNoList(List<Object> param) {
		String sql ="SELECT RP_NO FROM TB_REPAIR WHERE RP_RESERVEDT = ? AND RP_RESERVERM = ?";
		
		return jdbc.selectOne(sql, param);
	}
	
	//������ ������ȣ ��ȸ
	public Map<String, Object> rpNoCheck(String rpNO) {

		String sql = " select rp_no from tb_repair where rp_no like '"+rpNO+"%' and rownum =1 order by rp_no desc ";

		return jdbc.selectOne(sql);
	}
	
	//�����Ͼ� �� �Էµ�  ��¥, �ð��� ������ �����Ǽ� ��ȸ
	public List<Map<String, Object>> engCount(String rpNO) {

		String sql = "select eng_no , count(*) cnt from tb_repair where rp_no like '"+rpNO+"%' group by eng_no having count(*)=3 order by eng_no";

		return jdbc.selectList(sql);
	}

	//�������ప �Է�
	public int insertRepair(List<Object> repairParam) {
		return jdbc.update(" insert into tb_repair(rp_no, mem_no, item_id, contents_cd, rp_detail, rp_reservedt, rp_reserverm, state_cd, eng_no, rp_result, rp_startdate, rp_enddate, rp_costyn)"
                + "    values (?,?,?,?,?,?,?,?,?,null,null,null,null)", repairParam);
	}

	public Map<String, Object> getEngRpList(String engno, String rpNo) {

	      String sql = " SELECT * FROM TB_REPAIR WHERE RP_NO = '"+rpNo+"' AND ENG_NO = '"+engno+"'";

	      return jdbc.selectOne(sql);
	   }

	   public Map<String, Object> getstateCheck(String rpNo, int upstate) {
	      String sql = " SELECT * FROM TB_REPAIR WHERE RP_NO = '"+rpNo+"' AND STATE_CD = '"+upstate+"'";

	      return jdbc.selectOne(sql);
	   }

	   public int updateState(String rpNo, int upstate, String cost) {
	      String sql = null;
	      int ste=0;
	      if (upstate == 1) {
	    	  ste=2;
	         sql = " UPDATE TB_REPAIR SET STATE_CD = '"+ste+"', RP_STARTDATE = TO_DATE(SYSDATE) WHERE RP_NO = '"+rpNo+"'";
	      }else {
	    	  ste=3;
	         sql = " UPDATE TB_REPAIR SET STATE_CD = '"+ste+"', RP_ENDDATE = TO_DATE(SYSDATE), RP_COSTYN = UPPER('"+cost+"')" 
	               + " WHERE RP_NO = '"+rpNo+"'";
	      }
	      return jdbc.update(sql);
	   }
	
	
	//������ ���
	public Map<String, Object> getReceipt(String rpNoReceipt) {
		String sql= "SELECT A.MEM_NAME, B.ITEM_NM, C.CONTENTS_NM, D.ENG_NAME FROM TB_MEMBER A, TB_ITEM B, TB_CONTENTS C, TB_ENGINEER D WHERE (MEM_NO,ITEM_ID,CONTENTS_CD,ENG_NO) = (SELECT MEM_NO,ITEM_ID,CONTENTS_CD,ENG_NO FROM TB_REPAIR WHERE RP_NO='"+rpNoReceipt+"')";
		return jdbc.selectOne(sql);
	}

	//��ȸ(����!)
	public List<Map<String, Object>> reserveList(String memStr) {
		String sql ="select a.rp_no, b.gubun_nm, c.item_nm, d.contents_nm, a.rp_detail, e.state_nm, g.eng_name, a.rp_reservedt, a.rp_reserverm, a.rp_result, a.rp_startdate, a.rp_enddate, a.rp_costyn, f.mem_no "+
				"from tb_repair a, tb_gubun b, tb_item c, tb_contents d, tb_state e, tb_member f, tb_engineer g "+
				"where a.mem_no = f.mem_no and a.item_id =c.item_id and a.contents_cd = d.contents_cd and a.state_cd = e.state_cd and b.gubun = c.gubun and a.eng_no = g.eng_no "+
				"and f.mem_no ='"+memStr+"'";
		return jdbc.selectList(sql);
	}

	public Map<String, Object> reserveInfo(String mem,String selectReNo) {
		String sql= "select a.rp_no, b.gubun_nm, c.item_nm, d.contents_nm, a.rp_detail, a.state_cd, e.state_nm, a.rp_reservedt, a.rp_reserverm "+
				"from tb_repair a, tb_gubun b, tb_item c, tb_contents d, tb_state e, tb_member f "+
				"where a.mem_no = f.mem_no and a.item_id =c.item_id and a.contents_cd = d.contents_cd and a.state_cd = e.state_cd "+
				"and b.gubun = c.gubun "+
				"and f.mem_no ='"+mem+"' and a.rp_no='"+selectReNo+"'";
		return jdbc.selectOne(sql);
	}
	
	public int rpNoDelete(String rpNo) {
		return jdbc.update(" delete from tb_repair where rp_no='"+rpNo+"' ");
	}



}
