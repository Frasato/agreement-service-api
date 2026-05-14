FROM golang:1.26.3-alpine AS builder

WORKDIR /app
COPY go.mod go.sum ./
RUN go mod download
COPY . .

RUN CGO_ENABLED=0 GOOS=linux go build -o api ./cmd/api
RUN CGO_ENABLED=0 GOOS=linux go build -o worker ./cmd/worker

# API
FROM alpine:latest AS api
WORKDIR /root/
COPY --from=builder /app/api .
EXPOSE 8080
CMD ["./api"]

# Worker
FROM alpine:latest AS worker
WORKDIR /root/
COPY --from=builder /app/worker .
CMD ["./worker"]