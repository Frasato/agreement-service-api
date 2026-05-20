package handlers

import (
	"email-service/internal/models"
	"email-service/internal/queue"
	"encoding/json"
	"net/http"

	"github.com/gin-gonic/gin"
	amqp "github.com/rabbitmq/amqp091-go"
)

func SendEmail(ctx *gin.Context) {
	var body models.SendEmailRequest

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
