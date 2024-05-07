package com.booklibrary;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class email_id implements Runnable {
	static Boolean EamiMess;

	// this is responsible to send the message with attachment

	// this is responsible to send email..
	protected email_id(String message, String subject, String to, String from) {
		EamiMess = true;
		// Variable for gmail
		String host = "smtp.gmail.com";

		// get the system properties
		Properties properties = System.getProperties();
		// System.out.println("PROPERTIES "+properties);

		// setting important information to properties object

		// host set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// Step 1: to get the session object..
		Session session = Session.getInstance(properties, new Authenticator() {
			/**
			 * @return
			 */
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("bytecoading@gmail.com", "smbzkdqwmhkztzwz");
			}

		});

		session.setDebug(true);

		// Step 2 : compose the message [text,multi media]
		MimeMessage m = new MimeMessage(session);

		try {

			// from email
			m.setFrom(from);

			// adding recipient to message
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// adding subject to message
			m.setSubject(subject);

			// adding text to message
			m.setText(message);

			// send

			// Step 3 : send the message using Transport class
			Transport.send(m);

			// System.out.println("Sent success...................");

		} catch (Exception e) {
			EamiMess = false;

		}
	}

	@Override
	public void run() {

	}

	public static void setSesion(boolean b) {
	}
}
