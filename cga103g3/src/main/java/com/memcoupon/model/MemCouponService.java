package com.memcoupon.model;

import java.sql.Timestamp;
import java.util.List;



public class MemCouponService {
	
	private MemCouponDAO_interface dao;
	
	public MemCouponService() {
		
		dao = new MemCouponDAO();
	}
//	-- 新增一筆會員優惠券資料
	public MemCouponVO addMemCoupon(Integer memID, Integer coupTypeNo) {
		
		MemCouponVO memCouponVO = new MemCouponVO();
		
		memCouponVO.setMemID(memID);
		memCouponVO.setCoupTypeNo(coupTypeNo);
		dao.insert(memCouponVO);
		
		return memCouponVO;
	}
	
//	-- 更改 會員擁有的優惠券 資料內容
	public MemCouponVO updateMemCoupon(Integer coupNo, Integer memID, Integer coupTypeNo, Integer coupStatus, Timestamp coupExpDate,
			Timestamp coupGetDate) {
		
		MemCouponVO memCouponVO = new MemCouponVO();
		
		memCouponVO.setCoupNo(coupNo);
		memCouponVO.setMemID(memID);
		memCouponVO.setCoupTypeNo(coupTypeNo);
		memCouponVO.setCoupStatus(coupStatus);
		memCouponVO.setCoupExpDate(coupExpDate);
		memCouponVO.setCoupGetDate(coupGetDate);
		dao.update(memCouponVO);
		
		return memCouponVO;
	}
//	-- 更改 會員擁有的優惠券 的 使用狀態(給排程器偵測用)
	public void updateStatusRoutine(Integer coupNo, Integer coupStatus) {
		MemCouponVO memCouponVO = dao.getOne(coupNo);
		//狀態為未使用才去改為，已過期(上一層servlet呼叫時，coupStatus已給2(已過期))
		Integer status = memCouponVO.getCoupStatus();
		if(status == 0) {
			dao.updateStatusRoutine(coupNo, coupStatus);
		}
		
	}
	
//	-- 找出 某張優惠券的資訊	
	public MemCouponVO getOne(Integer coupNo) {
		return dao.getOne(coupNo);
	}
	
//	-- 秀出 某個會員 擁有的所有優惠券
	public List<MemCouponVO> showMemCouponByMemID(Integer memID){
		return dao.findMemCouponByMemID(memID);
	}
	
//	-- 秀出 某個會員 已使用(未使用、過期的)的所有優惠券
	public List<MemCouponVO> showMemCouponByStatus(Integer memID, Integer coupStatus){
		return dao.findMemCouponByStatus(memID, coupStatus);
	}
	
//	-- 秀出 某個會員 擁有的某種優惠券
	public List<MemCouponVO> showMemCouponByCoupTypeNo(Integer memID, Integer coupTypeNo){
		return dao.findMemCouponByCoupTypeNo(memID, coupTypeNo);
	}
	
//	-- 秀出 所有會員 擁有的優惠券
	public List<MemCouponVO> getAll(){
		return dao.getAll();
	}
	
	
	
	
	
	
}
