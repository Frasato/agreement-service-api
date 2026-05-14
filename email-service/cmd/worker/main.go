package main

import (
	"encoding/json"
	"fmt"
	"log"
	"os"

	"email-service/internal/models"
	"email-service/internal/service"

	"github.com/joho/godotenv"
	amqp "github.com/rabbitmq/amqp091-go"
)

func main() {
	err := godotenv.Load()
	if err != nil {
		log.Print("Failed to load .env")
	}

	conn, err := amqp.Dial(os.Getenv("RABBITMQ_CONNECTION"))

	if err != nil {
		log.Fatal("Failed to connect to RabbitMQ")
	}

	ch, err := conn.Channel()

	if err != nil {
		log.Fatal("Failed to open channel")
	}

	msgs, err := ch.Consume(
		"emails",
		"",
		true,
		false,
		false,
		false,
		nil,
	)

	if err != nil {
		log.Fatal("Failed to consume messages")
	}

	forever := make(chan bool)

	go func() {
		for msg := range msgs {
			var email models.SendEmailRequest

			err := json.Unmarshal(msg.Body, &email)

			if err != nil {
				fmt.Println("Failed to parse message")
				continue
			}

			service.SendEmail(email)
		}
	}()

	<-forever
}
