package com.act.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.act.model.ActService;
import com.act.model.ActVO;

@WebServlet("/ActStatusChangeServlet")
public class ActStatusChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Timer timer;
      public void init() throws ServletException {
    	  ActService actSvc = new ActService();
    	  List<ActVO> allList = actSvc.getAll();
    	  List<ActVO> list = allList.stream()
    			    .filter(e -> e.getActStatus().equals(1))
    	  			.collect(Collectors.toList());
    	  getServletContext().setAttribute("list", list);
    	  
    	  TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
				List<ActVO> list = (List<ActVO>) getServletContext().getAttribute("list");
				for (ActVO actVO : list) {
					LocalDateTime endTime = actVO.getActTimeEnd();
					LocalDateTime today;
					try {
						today = LocalDateTime.now();
						if(today.isAfter(endTime)) {
							actSvc.changeState(actVO);
							list = actSvc.getAll();
							getServletContext().setAttribute("list", list);
						} else if (actVO.getRegisMax() == actVO.getActRegistration()) {
							actSvc.changeState(actVO);
							list = actSvc.getAll();
							getServletContext().setAttribute("list", list);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		};
		timer = new Timer();
		Calendar rightNow = Calendar.getInstance();
		int year = rightNow.get(Calendar.YEAR);
		int month = rightNow.get(Calendar.MONTH);
		int day = rightNow.get(Calendar.DAY_OF_MONTH);
		
		Calendar calen = new GregorianCalendar(year, month, day, 0, 0, 0);
		timer.scheduleAtFixedRate(task, calen.getTime(), 60*60*1000);
      }
   
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getServletContext();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
	public void detroy() {
		timer.cancel();
	}

}
