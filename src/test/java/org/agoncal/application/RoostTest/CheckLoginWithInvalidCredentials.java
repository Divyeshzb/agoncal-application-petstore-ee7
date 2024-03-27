// ********RoostGPT********
/*
Test generated by RoostGPT for test lseg-integration using AI Type Azure Open AI and AI Model roostgpt-4-32k

ROOST_METHOD_HASH=c34ff5b82c
ROOST_METHOD_SIG_HASH=ea854dd943

 ########## Scenario ########## 

{
  background: null,
  rule: null,
  scenario: 'Check the Login functionality with invalid user credentials\n' +
    'Given the application login page is accessed\n' +
    "When I send a POST request with invalid 'Username' and 'Password'\n" +
    'Then the response status should be 403\n' +
    "And the response body should contain 'Invalid Credentials'",
  title: 'Check the Login functionality with invalid user credentials'
}

*/

// ********RoostGPT********
package org.agoncal.application.RoostTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckLoginWithInvalidCredentialsTest {
	
	private String testDataFile;
    private Map<String,String> headers;
    private String responseCode;
    private Map<String,String> requestBody;
    private Map<String,String> responseBody;
    private String apiEndPoint;
    private String httpMethod;
    
    @BeforeEach
    public void setUp() throws IOException {
        testDataFile = "src" + FileSystems.getDefault().getSeparator() +
                "test" + FileSystems.getDefault().getSeparator() +
                "java" + FileSystems.getDefault().getSeparator() +
                "org" + FileSystems.getDefault().getSeparator() +
                "agoncal" + FileSystems.getDefault().getSeparator() +
                "application" + FileSystems.getDefault().getSeparator() +
                "RoostTest" + FileSystems.getDefault().getSeparator() +
                "CheckLoginWithInvalidCredentials.csv";
        
        BufferedReader reader = new BufferedReader(new FileReader(testDataFile));
        String row = "";
        
        while ((row = reader.readLine()) != null) {
            if(row.isEmpty()){
                continue;
            }
            String[] data = row.split("\\^\\|\\^");

            httpMethod = data[0];
            apiEndPoint = data[1];
            headers = getHashMapFromCSVObject(data[2]);
            requestBody = getHashMapFromCSVObject(data[3]);
            responseCode = data[4];
            responseBody = getHashMapFromCSVObject(data[5]);
        }
        reader.close();
    }
    
     @Test
     @DisplayName("Check the Login functionality with invalid user credentials")
     public void testInvalidUserCredentials() {
        // Given the application login page is accessed  
        // When I send a POST request with invalid 'Username' and 'Password'
        // Then the response status should be 403
        // And the response body should contain 'Invalid Credentials'
        given()
            .headers(headers)
            .body(requestBody)
        .when()
            .request(httpMethod, apiEndPoint)
        .then()
            .statusCode(Integer.parseInt(responseCode))
            .body("msg", responseBody.get("msg"));

        assertEquals(responseCode,"403");
        assertTrue(responseBody.get("msg").contains("Invalid Credentials"));
     }

    private static Map<String,String> getHashMapFromCSVObject(String obj) {
        Map<String,String> map = new HashMap<>();
        String[] pairs = obj.substring(1, obj.length()-1).split(",");
        for (String pair : pairs) {
            String[] keyValue = pair.split(":");
            map.put(keyValue[0].replaceAll("\"", "").trim(), keyValue[1].replaceAll("\"", "").trim());
        }
        return map;
    }
}
