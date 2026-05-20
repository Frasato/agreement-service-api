package service

import (
	"errors"
	"strings"

	"github.com/golang-jwt/jwt/v5"
)

var secretKey = []byte("secret-key")

func ValidateToken(tokenString string) (string, error) {
	tokenString = RemovePrefix(tokenString)

	token, err := jwt.Parse(tokenString, func(t *jwt.Token) (interface{}, error) {
		if _, ok := t.Method.(*jwt.SigningMethodHMAC); !ok {
			return nil, errors.New("Wrong Algorithm")
		}

		return secretKey, nil
	})

	if err != nil {
		return "", err
	}

	claims, ok := token.Claims.(jwt.MapClaims)

	if !ok || !token.Valid {
		return "", errors.New("Invalid token")
	}

	if claims["iss"] != "user-service" {
		return "", errors.New("Invalid issuer")
	}

	role, _ := claims["role"].(string)

	return role, nil
}

func RemovePrefix(token string) string {
	token = strings.TrimPrefix(token, "Bearer ")
	return token
}
