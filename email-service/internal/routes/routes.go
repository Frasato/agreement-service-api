package routes

import (
	"email-service/internal/handlers"

	"github.com/gin-gonic/gin"
)

func SetUpRoutes(router *gin.Engine) {
	router.POST("/email", handlers.SendEmail)
}
