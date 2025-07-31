
# Golf Club API

This project is a Spring Boot REST API for managing golf club members and tournaments.

## Features

- Add and retrieve Members
- Add and retrieve Tournaments
- Assign Members to Tournaments
- Search Members by name, phone, start date, and duration
- Search Tournaments by date and location
- List members in a specific tournament

## Running with Docker

```bash
docker-compose up --build
```

## Search API Examples

- `GET /members?name=John`
- `GET /members?phoneNumber=1234567890`
- `GET /tournaments?location=New York`

## AWS Deployment

Replace DB connection in `application.properties` with your RDS endpoint.
