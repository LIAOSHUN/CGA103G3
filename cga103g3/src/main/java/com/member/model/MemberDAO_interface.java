package com.member.model;

import java.util.List;
import java.util.Map;


public interface MemberDAO_interface {
    public void insert(MemberVO memberVO);
    public void update(MemberVO memberVO);
    public void delete(Integer memID);
    public MemberVO findByPrimaryKey(Integer memID);
    public List<MemberVO> getAll();
	public void registerInsert(MemberVO memberVO1);
	public MemberVO MemberLogin(String memAccount,String memPassWord);
	public MemberVO MemberFindmemID(String memAccount);

}
