package models

type SendEmailRequest struct {
	Subject string `json:"subject"`
	Message string `json:"message"`
}
