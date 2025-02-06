## Auth-Service
Auth-Service - a Spring-boot microservice that integrates with Keycloak to provide robust authentication and authorization for your microservices.
### Features
Secure Authentication: Leverages Keycloak for user authentication.
Role-Based Access Control: Manages user roles and permissions efficiently.
Scalable Architecture: Designed to support scalable distributed systems.
### Prerequisites
Java Development Kit (JDK): Version 17 or higher.
Gradle: For project build and dependency management.
Keycloak: Set up and running for authentication management.
## Getting started
1| Clone the repo:
```
git clone https://github.com/Fl1s/auth-service.git
cd auth-service
```
2| Build the project:
```
./gradlew build
```
3| Configure Keycloak:
* Start Keycloak (if not already running):
```
docker run -p 9090:8080 -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin quay.io/keycloak/keycloak:latest
```
* Set up Realm and Client:
* * Access the Keycloak admin console at http://localhost:9090.
* * Create a new realm for your application.
* * Within the realm, add a new client with the appropriate Redirect URI.
* Update application.yml:
* * In `src/main/resources/application.yml`, configure the Keycloak settings:
```
keycloak:
  realm: <your-realm>
  auth-server-url: <your-auth-server-url>
  resource: <your-resource>
  credentials:
    client-id: <your-client-id>
    secret: <your-secret>
  use-resource-role-mappings: true
```
4| Run the microservice:
```
./gradlew bootRun
```
The service will be accessible at http://localhost:8081 (or your configured port).
### Usage
Interact with the Auth Service API to authenticate and authorize users.
Ensure your requests include the necessary access tokens issued by Keycloak to access protected resources.
## Contributing
Contributions are welcome! Please fork the repository and create a new branch for your feature or bug fix:
1| Fork the repo.
2| Create a new branch:
```
git checkout -b feature/your-feature-name
```
3| Add and commit your changes(process in the root directory):
```
git add ./
git commit -m "Add your feature description"
```
4| Push to your branch:
```
git push origin feature/your-feature-name
```
5| Open a Pull Request (PR).
## License
This project is licensed under the MIT License. See the LICENSE file for details.
