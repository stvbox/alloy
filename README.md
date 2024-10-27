# Spring Boot Demo

This is a simple Spring Boot app that can be monitored with Prometheus and Grafana. It expose all metrics under
`/actuator/prometheus`. Run `./gradlew :bootRun` to launch the app.

## Alloy
- в папке /alloy запук контейнеров: 
- docker compose up -d
- остановка контейнеров
- docker compose down -v

перед запуском контейнеров надо изменит ip приложения для 
парвильной работы снятия метрик
alloy/shared/config.alloy
- 192.168.0.102