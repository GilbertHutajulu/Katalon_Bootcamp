import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

RequestObject ro = findTestObject('Users/TO_Users_GET_All')
def response = WS.sendRequest(ro)

WS.verifyResponseStatusCode(response, 200)

WS.verifyResponseSchema(response, 'Data Files/user_array_schema.json')

WS.verifyElementExist(response, '$[0]')