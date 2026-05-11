package queue

import (
	"log"
	"os"

	amqp "github.com/rabbitmq/amqp091-go"
)

var Channel *amqp.Channel

func ConnectRabbitMq() {
	conn, err := amqp.Dial("amqp://" + os.Getenv("RABBIT_USER") + ":" + os.Getenv("RABBIT_PASSWORD") + "@localhost:5672/")

	if err != nil {
		log.Fatal("Failed to connect to RabbitMQ")
	}

	ch, err := conn.Channel()

	if err != nil {
		log.Fatal("Failed to open channel")
	}

	_, err = ch.QueueDeclare(
		"emails",
		true,
		false,
		false,
		false,
		nil,
	)

	if err != nil {
		log.Fatal("Failed to declare queue")
	}

	Channel = ch

	log.Println("RabbitMQ connected")
}
