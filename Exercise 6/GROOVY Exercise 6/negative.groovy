import java.net.URL
import groovy.json.JsonSlurper

println "Running API Test..."

// API URL
def url = new URL("https://reqres.in/api/login")
def connection = url.openConnection()

connection.setRequestMethod("POST")
connection.setRequestProperty("Content-Type", "application/json")
connection.setRequestProperty("x-api-key", "reqres_46627f62f6de49fb8dbd061ec3643ec3")
connection.setDoOutput(true)

// Missing password to trigger 400 error
def requestBody = '''
{
  "email": "eve.holt@reqres.in"
}
'''

connection.outputStream.write(requestBody.getBytes("UTF-8"))

def statusCode = connection.responseCode
println "Status Code: " + statusCode

def responseText

if (statusCode < 300) {
    responseText = connection.inputStream.text
} else {
    responseText = connection.errorStream.text
}

println "Response: " + responseText

def json = new JsonSlurper().parseText(responseText)


// Test 1: Status Code 400
if (statusCode == 400) {
    println "Test Passed: Status code is 400"
} else {
    println "Test Failed: Status code is not 400"
}


// Test 2: Error message present
if (json.error) {
    println "Test Passed: Error message is present"
} else {
    println "Test Failed: Error message missing"
}