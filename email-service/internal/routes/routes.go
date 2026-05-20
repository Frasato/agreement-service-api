package routes

import (
	"email-service/internal/handlers"
	"email-service/internal/middleware"

	"github.com/gin-gonic/gin"
)

func SetUpRoutes(router *gin.Engine) {
	router.POST("/email", middleware.LoginMiddleware(), handlers.SendEmail)
}
