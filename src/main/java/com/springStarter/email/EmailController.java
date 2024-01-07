package com.springStarter.email;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;

@RestController
@RequestMapping("/api/email")
@Api(tags = "Email Controller", description = "Email handling API")
public class EmailController {

	@Autowired
	private EmailService emailService;

	@GetMapping("/read")
	public ResponseEntity<String> readEmails() {
		try {
			emailService.readEmails();
			return ResponseEntity.ok("Emails read successfully");
		} catch (MessagingException | IOException e) {
			return ResponseEntity.status(500).body("Error reading emails: " + e.getMessage());
		}
	}

	@PostMapping("/send")
	public ResponseEntity<String> sendEmail(
			@RequestParam String to,
			@RequestParam String subject,
			@RequestParam String content) {
		try {
			emailService.sendEmail(to, subject, content);
			return ResponseEntity.ok("Email sent successfully");
		} catch (MessagingException e) {
			return ResponseEntity.status(500).body("Error sending email: " + e.getMessage());
		}
	}
}
