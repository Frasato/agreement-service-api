package service

import (
	"email-service/internal/models"
	"log"
	"net/smtp"
	"os"
)

func SendEmail(email models.SendEmailRequest) {
	from := os.Getenv("EMAIL_USER")
	password := os.Getenv("EMAIL_PASSWORD")

	to := []string{
		from,
	}

	smtpHost := "smtp.gmail.com"
	smtpPort := "587"

	message := []byte(
		"Subject: " + email.Subject + "\r\n" + "\r\n" + email.Message,
	)

	auth := smtp.PlainAuth(
		"",
		from,
		password,
		smtpHost,
	)

	err := smtp.SendMail(
		smtpHost+":"+smtpPort,
		auth,
		from,
		to,
		message,
	)

	if err != nil {
		log.Println("Failed to send email:", err)
		return
	}

	log.Println("Email sent successfully!")
}
