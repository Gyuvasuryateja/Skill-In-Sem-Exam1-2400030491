# Tourist Token Validation API

A small Spring Boot practical for fetching one tourist profile by ID after validating a custom token header.

## Requirements
- Java 17 or later
- Maven 3.6+ (or use the Maven support in your IDE)
- Internet connection on first build to download dependencies

## Package and project
- Maven group: `com.klu`
- Java base package: `com.klu.touristapi`
- Port: `8080`

## Run in Spring Tool Suite (STS)
1. Extract the ZIP file.
2. Open STS and choose **File → Import → Maven → Existing Maven Projects**.
3. Browse to the extracted `tourist-token-validation` folder and select `pom.xml`.
4. Click **Finish** and wait for Maven dependencies to download.
5. Open `TouristTokenValidationApplication.java`.
6. Right-click it → **Run As → Spring Boot App**.

## Run from a terminal
From the project folder, run:

```bash
mvn spring-boot:run
```

Run tests with:

```bash
mvn test
```

## Swagger UI
Open: http://localhost:8080/swagger-ui/index.html

Expand `GET /tourists/{id}`, click **Try it out**, enter `1` for `id`, then enter `BORDER123` in the `X-Auth-Token` header and click **Execute**.

## Test cases

### 1. Correct token
Request:
```http
GET http://localhost:8080/tourists/1
X-Auth-Token: BORDER123
```
Expected: `200 OK`
```json
{
  "id": 1,
  "name": "Rahul",
  "country": "India",
  "passportNumber": "P12345"
}
```

### 2. Incorrect token
Use `WRONG123` in `X-Auth-Token`.
Expected: `500 Internal Server Error`, with JSON error details.

### 3. Missing token
Do not provide `X-Auth-Token`.
Expected: `500 Internal Server Error`, with JSON error details.

### 4. Tourist not found
Use ID `99` with the correct token.
Expected: `404 Not Found`.

## Sample tourist IDs
- `1` — Rahul, India
- `2` — John, USA
- `3` — David, UK

## Notes
- The tourist data is stored in an in-memory list. It resets when the app restarts.
- `BORDER123` is a demonstration token for this assignment only. Do not use a hard-coded token in a real production application.
- The assignment asks for HTTP 500 on invalid/missing tokens, so this project follows that requirement. In production, invalid authentication should normally return HTTP 401 Unauthorized.
