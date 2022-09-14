package com.notification.model;

import java.sql.Timestamp;
import java.util.List;


public class NotificationService {
	private NotificationDAO_interface dao;

	public NotificationService() {
		dao = new NotificationJDBCDAO();
	}

	public NotificationVO addNotification(Integer memID, String noticeDescription, Timestamp noticeTime, Integer noticeRead) {

		NotificationVO notificationVO = new NotificationVO();

		notificationVO.setMemID(memID);
		notificationVO.setNoticeDescription(noticeDescription);
		notificationVO.setNoticeTime(noticeTime);
		notificationVO.setNoticeRead(noticeRead);

		dao.insert(notificationVO);

		return notificationVO;
	}

	public NotificationVO updateNotification(Integer noticeID,Integer memID, String noticeDescription, Timestamp noticeTime, Integer noticeRead) {

		NotificationVO notificationVO = new NotificationVO();
        notificationVO.setNoticeID(noticeID);
		notificationVO.setMemID(memID);
		notificationVO.setNoticeDescription(noticeDescription);
		notificationVO.setNoticeTime(noticeTime);
		notificationVO.setNoticeRead(noticeRead);


		dao.update(notificationVO);

		return notificationVO;
	}

	public void deleteNotification(Integer noticeID) {
		dao.delete(noticeID);
	}

	public NotificationVO getOneNotification(Integer noticeID) {
		return dao.findByPrimaryKey(noticeID);
	}

	public List<NotificationVO> getAll() {
		return dao.getAll();

	}
}
