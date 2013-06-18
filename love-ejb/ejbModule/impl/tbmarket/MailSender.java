package impl.tbmarket;

import interfaces.system.ISystemSingleton;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.InitialContext;

import org.jboss.logging.Logger;

import com.spstudio.love.system.qualifier.LoveLogged;

@Model
public class MailSender {

	@EJB ISystemSingleton systemSingletonBean;
	@Inject @LoveLogged Logger log;

	private String mapFolderPath;

	@PostConstruct
	public void onConstruct() {
		List<String> listConfigValue = systemSingletonBean
				.retrieveSystemConfiguration().get("MAP_FOLDER");
		mapFolderPath = listConfigValue.get(0);
		log.info("map folder : " + mapFolderPath);
	}
	
	public void sendMail(int requireId) {

		Session mailSession = null;

		try {
			
			/*
			 * 设置发信人，收信人，主题，日期等参数
			 */
			
			mailSession = (Session) new InitialContext().lookup("java:/Mail");
			MimeMessage m = new MimeMessage(mailSession);
			Address from = new InternetAddress(mailSession.getProperty("mail.from"), "Smart Matrix");
			Address[] to = new InternetAddress[] { new InternetAddress("streetpoet@163.com") };
			m.setFrom(from);
			m.setRecipients(Message.RecipientType.TO, to);
			m.setSubject("Traffic Map Realtime");
			m.setSentDate(new Date());
			
			/*
			 * 混合型对象将最终作为邮件的内容
			 */
			Multipart allPart = new MimeMultipart("mixed");
			
			/*
			 * 正文部分的容器
			 */
			MimeBodyPart contentBody = new MimeBodyPart();
			
			// 用于组合文本和图片，"related"型的MimeMultipart对象  
	        MimeMultipart contentMulti = new MimeMultipart("related"); 
	        
			// 正文文本部分
			BodyPart textBody = new MimeBodyPart();
			textBody.setContent("<h1>实时路况图仅供参考，切勿过度依赖! </h1><br /><img src = \"cid:realtime-traffic\">", "text/html;charset=utf-8");
			contentMulti.addBodyPart(textBody);
			
			// 正文图片部分
			MimeBodyPart jpgBody = new MimeBodyPart();
			File picFileFolder = new File(mapFolderPath + File.separator + requireId);
			File picFile = picFileFolder.listFiles()[0];
			String fileFullPath = picFile.getAbsolutePath();
			log.info("pic sent: " + fileFullPath);
	        FileDataSource fds = new FileDataSource(fileFullPath);
	        jpgBody.setDataHandler(new DataHandler(fds));
	        jpgBody.setContentID("realtime-traffic");  
	        contentMulti.addBodyPart(jpgBody);
	        
	        // 组装到正文的容器中
	        contentBody.setContent(contentMulti);
	        
	        /*
	         * 将正文的容器加入到邮件中来
	         */
	        allPart.addBodyPart(contentBody);
	        
			m.setContent(allPart);
			Transport.send(m);
			
		} catch (Exception e) {
			log.error(e);
		}
	}
}
