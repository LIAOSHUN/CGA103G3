package com.bookingorder.model;

import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class MailAndQRCode {
	// 設定傳送郵件:至收信人的Email信箱,Email主旨,Email內容
	public void sendMailAndQRCode(String to, String subject, String messageText) throws WriterException, IOException {

		int count = 0;

		// 建立目錄
		String fileName = "C:\\QRCode";
		Path path = Paths.get(fileName);
		if (!Files.exists(path))
			Files.createDirectory(path);

		// QR Code設定
		String[] link = new String[5];
		link[0] = "https://th.bing.com/th/id/OIP.giV4vwUhD8QY4F0aoTanDgHaE0?pid=ImgDet&rs=1";
		link[1] = "https://cdn-static.tibame.com/temp/679c5c25-c0d6-419a-a65f-e848180278a1_0322_v2.png";
		link[2] = "https://scontent.ftpe8-1.fna.fbcdn.net/v/t31.18172-8/26023855_688604831335202_1735860882321999830_o.jpg?"
				+ "_nc_cat=109&ccb=1-7&_nc_sid=730e14&_nc_ohc=BzjZuIkyhQEAX8fZ0aX&_nc_ht=scontent.ftpe8-1.fna&oh=00_AT9SM1LwPwF364Ml-0P3azqu2NP4MbpxXgXR1K5x2D3rMQ&oe=63455017";
		link[3] = "https://th.bing.com/th/id/R.6718aede0c5d828426a5f73376cf05e1?rik=GcIk0h2EQKHMzA&pid=ImgRaw&r=0";
		link[4] = "https://scontent.ftpe8-4.fna.fbcdn.net/v/t31.18172-8/24883596_682588631936822_781044364661058082_o.jpg?"
				+ "_nc_cat=104&ccb=1-7&_nc_sid=730e14&_nc_ohc=0BSyNU068gIAX8ELyVP&_nc_ht=scontent.ftpe8-4.fna&oh=00_AT-UOTEG3PIQ_Edhokp_GFD0CqIQcoEpedZSPjKA7AG4mQ&oe=63483E31";

		String text = link[(int) (Math.random() * 5)];
		int width = 300;
		int height = 300;
		String format = "jpg";
		// 設定編碼格式與容錯率
		Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		// 設置QRCode的存放目錄、檔名與圖片格式
		String qrCodeName = new SimpleDateFormat("yyyyMMdd").format(new java.util.Date()) + "_" + count + ".jpg";
		path = FileSystems.getDefault().getPath(fileName, qrCodeName);
		System.out.println(path);
		// 開始產生QRCode
		BitMatrix matrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
		// 把產生的QRCode存到指定的目錄
		MatrixToImageWriter.writeToPath(matrix, format, path);

		try {
			// 設定使用SSL連線至 Gmail smtp Server
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");

			// ●設定 gmail 的帳號 & 密碼 (將藉由你的Gmail來傳送Email)
			// ●1) 登入你的Gmail的:
			// ●2) 點選【管理你的 Google 帳戶】
			// ●3) 點選左側的【安全性】

			// ●4) 完成【兩步驟驗證】的所有要求如下:
			// ●4-1) (請自行依照步驟要求操作之.....)

			// ●5) 完成【應用程式密碼】的所有要求如下:
			// ●5-1) 下拉式選單【選取應用程式】--> 選取【郵件】
			// ●5-2) 下拉式選單【選取裝置】--> 選取【Windows 電腦】
			// ●5-3) 最後按【產生】密碼
			final String myGmail = "ufo3068@gmail.com";
			final String myGmail_password = "riszhbzajlvwlokp";
			Session session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(myGmail, myGmail_password);
				}
			});

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myGmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			// 設定信中的主旨
			message.setSubject(subject);
//			// 設定信中的內容
//			message.setText(messageText);

			// This mail has 2 part, the BODY and the embedded image
			MimeMultipart multipart = new MimeMultipart("related");

			// first part (the html)
			BodyPart messageBodyPart = new MimeBodyPart();
			String htmlText = messageText + "<H2>QR Code</H2><img src=\"cid:image\">";
			messageBodyPart.setContent(htmlText, "text/html;charset=utf-8");
			// add it
			multipart.addBodyPart(messageBodyPart);

			// second part (the image)
			messageBodyPart = new MimeBodyPart();
			DataSource fds = new FileDataSource(fileName + "\\" + qrCodeName);

			messageBodyPart.setDataHandler(new DataHandler(fds));
			messageBodyPart.setHeader("Content-ID", "<image>");

			// add image to the multipart
			multipart.addBodyPart(messageBodyPart);

			// put everything together
			message.setContent(multipart);

			Transport.send(message);
			System.out.println("傳送成功!");
		} catch (MessagingException e) {
			System.out.println("傳送失敗!");
			e.printStackTrace();
		}

		count++;
	}
}
