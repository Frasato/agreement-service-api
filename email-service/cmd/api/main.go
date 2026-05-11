package main

import (
	"email-service/internal/queue"
	"email-service/internal/routes"
	"log"

	"github.com/gin-gonic/gin"
	"github.com/joho/godotenv"
)

func main() {
	err := godotenv.Load()
	if err != nil {
		log.Print("Failed to load .env")
		return
	}

	queue.ConnectRabbitMq()
	router := gin.Default()

	routes.SetUpRoutes(router)

	router.Run(":8080")
}
