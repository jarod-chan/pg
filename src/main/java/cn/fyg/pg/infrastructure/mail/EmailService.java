package cn.fyg.pg.infrastructure.mail;

public interface EmailService {

	void sendMail(String to, String subject, String htmlText)
			throws EmailException;

}
