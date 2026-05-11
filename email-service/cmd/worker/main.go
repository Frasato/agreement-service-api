package main

import (
	"encoding/json"
	"fmt"
	"log"

	"email-service/internal/models"

	amqp "github.com/rabbitmq/amqp091-go"
)

func main() {
	conn, err := amqp.Dial("amqp://guest:guest@localhost:5672/")

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

	fmt.Println("Worker started")

	forever := make(chan bool)

	go func() {
		for msg := range msgs {
			var email models.SendEmailRequest

			err := json.Unmarshal(msg.Body, &email)

			if err != nil {
				fmt.Println("Failed to parse message")
				continue
			}

			fmt.Println("New email received:")
			fmt.Println("Subject:", email.Subject)
			fmt.Println("Message:", email.Message)
		}
	}()

	<-forever
}
