package handlers

import (
	"email-service/internal/models"
	"email-service/internal/queue"
	"email-service/internal/service"
	"encoding/json"
	"net/http"

	"github.com/gin-gonic/gin"
	amqp "github.com/rabbitmq/amqp091-go"
)

func SendEmail(ctx *gin.Context) {
	var body models.SendEmailRequest

	token := ctx.GetHeader("Authorization")
	role, tokenErr := service.ValidateToken(token)

	if tokenErr != nil {
		ctx.JSON(http.StatusUnauthorized, gin.H{
			"error": tokenErr.Error(),
		})

		return
	}

	if role != "ADMIN" {
		ctx.JSON(http.StatusUnauthorized, gin.H{
			"error": "You don't have authorization to send email",
		})

		return
	}

	if err := ctx.ShouldBindJSON(&body); err != nil {
		ctx.JSON(http.StatusBadRequest, gin.H{
			"error": err.Error(),
		})

		return
	}

	jsonBody, err := json.Marshal(body)

	if err != nil {
		ctx.JSON(http.StatusInternalServerError, gin.H{
			"error": "failed to serialize message",
		})

		return
	}

	err = queue.Channel.Publish(
		"",
		"emails",
		false,
		false,
		amqp.Publishing{
			ContentType: "application/json",
			Body:        jsonBody,
		},
	)

	if err != nil {
		ctx.JSON(http.StatusInternalServerError, gin.H{
			"error": "failed to publish message",
		})

		return
	}

	ctx.JSON(http.StatusOK, gin.H{
		"status": "queued",
	})
}
