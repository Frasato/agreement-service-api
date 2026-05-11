package main

import (
	"email-service/internal/queue"
	"email-service/internal/routes"

	"github.com/gin-gonic/gin"
)

func main() {
	queue.ConnectRabbitMq()
	router := gin.Default()

	routes.SetUpRoutes(router)

	router.Run(":8080")
}
