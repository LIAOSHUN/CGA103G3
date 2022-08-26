package com.member.model;

import java.util.List;

public class MemberService {
	private MemberDAO_interface dao;

	public MemberService() {
		dao = new MemberJDBCDAO();
	}

	public MemberVO addMember(Integer gradeID, String memName, String memAccount, String memPassWord, String memGender,
			String memPh, String memEmail, String memAddress, java.sql.Date memBirthday, byte[] memCard, Integer memVio,
			String memCredit, Integer memStatus) {

		MemberVO memberVO = new MemberVO();

		memberVO.setGradeID(gradeID);
		memberVO.setMemName("memName");
		memberVO.setMemAccount("memAccount");
		memberVO.setMemPassWord("memPassWord");
		memberVO.setMemGender("memGender");
		memberVO.setMemPh("memPh");
		memberVO.setMemEmail("memEmail");
		memberVO.setMemAddress("memAddress");
		memberVO.setMemBirthday(memBirthday);
		memberVO.setMemCard(memCard);
		memberVO.setMemVio(memVio);
		memberVO.setMemCredit(memCredit);
		memberVO.setMemStatus(memStatus);

		dao.insert(memberVO);

		return memberVO;
	}

	public MemberVO updateMember(Integer memID, Integer gradeID, String memName, String memAccount, String memPassWord,
			String memGender, String memPh, String memEmail, String memAddress,java.sql.Date memBirthday,
			byte[] memCard, Integer memVio, String memCredit, Integer memStatus) {

		MemberVO memberVO = new MemberVO();
		memberVO.setMemID(memID);
		memberVO.setGradeID(gradeID);
		memberVO.setMemName("memName");
		memberVO.setMemAccount("memAccount");
		memberVO.setMemPassWord("memPassWord");
		memberVO.setMemGender("memGender");
		memberVO.setMemPh("memPh");
		memberVO.setMemEmail("memEmail");
		memberVO.setMemAddress("memAddress");
		memberVO.setMemBirthday(memBirthday);
		memberVO.setMemCard(memCard);
		memberVO.setMemVio(memVio);
		memberVO.setMemCredit(memCredit);
		memberVO.setMemStatus(memStatus);

		dao.update(memberVO);

		return memberVO;
	}

	public void deleteMember(Integer memID) {
		dao.delete(memID);
	}

	public MemberVO getOneMember(Integer memID) {
		return dao.findByPrimaryKey(memID);
	}

	public List<MemberVO> getAll() {
		return dao.getAll();

	}
}
