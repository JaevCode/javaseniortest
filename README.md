# Prueba Tecnica Invex

Microservicios Spring Boot con autenticacion OAuth2:
- `users`: registro y validacion de usuarios/roles.
- `oauth2server`: Authorization Server (emision de tokens).
- `employees`: Resource Server protegido con JWT, CRUD de empleados.
- `mysql`: persistencia para `employees`.

## Requisitos
- Docker Desktop (o Docker Engine + Compose)
- Postman

Opcional (si ejecutas local sin Docker):
- Java 17
- Maven Wrapper (`mvnw`)

## Estructura
- `employees/`
- `oauth2server/`
- `users/`
- `mysql-init/init.sql`
- `docker-compose.yml`
- `postman/invex-full-stack.postman_collection.json`

## Levantar el proyecto (Docker Compose)
Desde la raiz del repositorio:

```bash
docker compose up --build
```

Servicios:
- `users`: `http://localhost:8081`
- `oauth2server`: `http://localhost:8082`
- `employees`: `http://localhost:8080`
- `mysql`: `localhost:3307`

## Health checks rapidos
- `GET http://localhost:8080/actuator/health`
- `GET http://localhost:8082/actuator/health`

## Coleccion Postman
Importa:
- `postman/invex-test.postman_collection.json`

Variables importantes incluidas:
- `usersBaseUrl` = `http://localhost:8081`
- `oauthBaseUrl` = `http://localhost:8082`
- `employeesBaseUrl` = `http://localhost:8080`
- `clientIdMachine` = `employees-client`
- `clientSecretMachine` = `employees-secret`
- `clientIdAuthCode` = `postman-client`
- `clientSecretAuthCode` = `postman-secret`

## Flujo recomendado de pruebas
1. `Users -> Register User`
2. `OAuth2 -> Get Token (Client Credentials)`
3. `Employees -> Get All Employees` (usa `{{access_token}}`)
4. `Employees -> Create/Update/Delete` (usa `{{access_token}}`)

La coleccion ya guarda automaticamente `access_token` y `refresh_token` en variables de coleccion cuando pides token.

## Flujo OAuth2 con usuario (Authorization Code)
Usa requests de la carpeta `OAuth2`:
1. `Open Authorize URL (User Login)`
2. Login en navegador
3. Copia `code` de la redireccion (si Postman no lo captura)
4. Pega `code` en `{{authorization_code}}`
5. Ejecuta `Exchange Code for Token (Authorization Code)`

## Notas de autorizacion en `employees`
- `GET /employees/**`: requiere `SCOPE_employees.read` o rol `EMPLOYEE`/`ADMIN`
- `POST/PUT/DELETE /employees/**`: requiere `SCOPE_employees.write` o rol `ADMIN`

## Ejecucion local sin Docker (opcional)
Abre 3 terminales y corre:

```bash
# users
cd users
./mvnw spring-boot:run
```

```bash
# oauth2server
cd oauth2server
./mvnw spring-boot:run
```

```bash
# employees
cd employees
./mvnw spring-boot:run
```

Para local, ajusta variables de entorno segun necesites (`PORT`, `RESOURCE_SERVER`, `VERIFY_USER_URL`, etc.).

## Pruebas unitarias
Ejemplo por modulo:

```bash
cd employees
./mvnw test
```

```bash
cd users
./mvnw test
```

## Troubleshooting
### 401 en `employees`
- Verifica que estas enviando `Authorization: Bearer {{access_token}}`
- Solicita un token nuevo (si reiniciaste `oauth2server`, tokens viejos pueden invalidarse)
- Confirma que el `iss` del token coincida con `RESOURCE_SERVER` configurado en `employees`

### 400 en `/oauth2/authorize`
- Revisa que `redirect_uri` sea exactamente uno registrado en el cliente OAuth2
- Revisa que `client_id` y `scope` sean validos para el cliente

### El callback de Postman solo abre la app
- Es normal en algunos casos. Puedes copiar manualmente el `code` de la URL y usarlo en `Exchange Code for Token`.

