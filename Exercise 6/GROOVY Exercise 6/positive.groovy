import java.net.URL
import groovy.json.JsonSlurper

def url = new URL("https://reqres.in/api/login")
def connection = url.openConnection()

connection.setRequestMethod("POST")
connection.setRequestProperty("Content-Type", "application/json")
connection.setRequestProperty("x-api-key", "reqres_46627f62f6de49fb8dbd061ec3643ec3")  // add your key
connection.setDoOutput(true)

def requestBody = '''
{
  "email": "eve.holt@reqres.in",
  "password": "cityslicka"
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

if (statusCode == 200) {
    println "Test Passed: Status code is 200"
} else {
    println "Test Failed"
}

if (json.token) {
    println "Token Present"
}