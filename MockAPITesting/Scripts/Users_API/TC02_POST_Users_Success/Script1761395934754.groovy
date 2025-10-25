import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.testobject.RequestObject
import groovy.json.JsonOutput

// Mendefinisikan data unik untuk pengguna baru
String uniqueUsername = "new_user_" + System.currentTimeMillis() 
String firstName = "Lisa"
String lastName = "Marie"

// Mengambil Test Object POST
RequestObject postTO = findTestObject('Users/TO_Users_POST_Create')

// Membuat payload JSON yang dinamis
String jsonBody = JsonOutput.toJson([
    [
        "first_name": firstName,
        "last_name": lastName,
        "username": uniqueUsername,
        "job_position": "Analyst",
        "job_level": "Junior",
        "salary": 60000,
        "work_duration": 0.5
    ]
])

postTO.setHttpBody(jsonBody)

// Mengirim Request
ResponseObject response = WS.sendRequest(postTO)

WS.verifyResponseStatusCode(response, 201)

// Periksa apakah username dan nama depan yang dikirim ada di respons
WS.verifyElementPropertyValue(response, '[0].username', uniqueUsername)
WS.verifyElementPropertyValue(response, '[0].first_name', firstName)

String newId = WS.getElementPropertyValue(response, '[0].id').toString()