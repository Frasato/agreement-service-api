package middleware

import (
	"email-service/internal/service"
	"net/http"

	"github.com/gin-gonic/gin"
)

func LoginMiddleware() gin.HandlerFunc {
	return func(ctx *gin.Context) {

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

		ctx.Next()
	}
}
