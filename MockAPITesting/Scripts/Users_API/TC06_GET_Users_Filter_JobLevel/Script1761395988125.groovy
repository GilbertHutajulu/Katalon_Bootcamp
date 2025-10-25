import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.testobject.TestObject

String filterLevel = "Senior" 

// Mengambil Test Object GET All
TestObject getFilterTO = findTestObject('Users/TO_Users_GET_All')

// Memodifikasi URL untuk menambahkan filter: /users?job_level=eq.Senior
getFilterTO.setRestUrl(GlobalVariable.baseUrl + "/users?job_level=eq." + filterLevel)

// Mengirim Request
def response = WS.sendRequest(getFilterTO)

WS.verifyResponseStatusCode(response, 200)

List<String> jobLevels = WS.getElementPropertyValues(response, '$[*].job_level')

// Memastikan setiap elemen dalam list adalah "Senior"
for (String level : jobLevels) {
    WS.verifyEqual(level, filterLevel)
}