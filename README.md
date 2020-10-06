## Google Drive File Upload through OAuth 2.0 Authentication

A demo application to showcase the OAuth 2.0 Framework.

## OAuth Setup in Google Console
1. Create a [Google cloud console](https://console.cloud.google.com/) project if you don't have.
2. Google drive API needs to be enabled for the project in [API Library](https://console.cloud.google.com/apis/library).
3. Generate the credentials for [OAuth - WebApplication](https://console.cloud.google.com/apis/credentials/wizard)
4. Download the keys as JSON, and replace it with `google_oauth_keys.json` file in `src/main/resources/keys` directory.

### Steps to Run the Server
1. Edit the `application.properties` with your properties
2. Build the project with `mvn clean install`
3. Run using `mvn spring-boot:run`
4. Navigate to `http://localhost:8080` in your browser

