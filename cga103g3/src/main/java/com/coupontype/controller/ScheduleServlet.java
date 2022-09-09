package com.coupontype.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coupontype.model.CouponTypeService;
import com.coupontype.model.CouponTypeVO;
import com.memcoupon.model.MemCouponService;
import com.memcoupon.model.MemCouponVO;


//@WebServlet("/ScheduleServlet")
public class ScheduleServlet extends HttpServlet {
       
	private static final long serialVersionUID = 1L;
	Timer timer = null;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
									throws ServletException, IOException {
		res.setContentType("text/plain");
		
		
	};
	
	public void destroy() {
		
		super.destroy();
		timer.cancel();
	}
	
	public void init() {
		timer = new Timer();
		
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
				MemCouponService memCouponService = new MemCouponService();
				CouponTypeService couponTypeService = new CouponTypeService();
				
				List<CouponTypeVO> listCouponType = couponTypeService.getAll();
				
				for(int i = 0; i < listCouponType.size(); i++) {
					CouponTypeVO couponTypeVO = new CouponTypeVO();
					
					couponTypeVO = listCouponType.get(i);
					
					java.util.Date date1 = new java.util.Date();//現在時間
					java.util.Date date2 = couponTypeVO.getCoupEnd();//優惠券類型的到期日
					System.out.println(date1);
					//如果date1晚於date2 將過期優惠券下架
					 if (date1.after(date2)) {
						 System.out.println("優惠券類型下架");
						 couponTypeService.updateDown(couponTypeVO.getCoupTypeNo());//改成已下架 
			         }
				}
				
				List<MemCouponVO> listMemCoupon = memCouponService.getAll();
				for(int i = 0; i < listMemCoupon.size(); i++) {
					MemCouponVO memCouponVO = new MemCouponVO();
					memCouponVO = listMemCoupon.get(i);
					
					java.util.Date date1 = new java.util.Date();//現在時間
					java.util.Date date3 = memCouponVO.getCouponTypeVO().getCoupEnd();//會員擁有的優惠券，找出他是哪個類型，在找出此類型的到期日
					System.out.println(date3);
					//如果date1晚於date2 則將優惠券下架，會員優惠券改為已過期
					 if (date1.after(date3)) {
						 System.out.println("我的優惠券過期");
						 memCouponService.updateStatusRoutine(memCouponVO.getCoupNo(), 2);//改過期(2:過期)
			         }
					
				}
			}
		};
		
		Calendar cal = new GregorianCalendar(2022, Calendar.SEPTEMBER, 9, 23, 59, 59);//9/9號 凌晨1200開始
		timer.scheduleAtFixedRate(task, cal.getTime(),24*60*60*1000);//從上面指定時間開始，並在每天執行一次
	}

}
