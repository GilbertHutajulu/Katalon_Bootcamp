import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

def response = WS.sendRequest(findTestObject('Users/TO_Users_POST_Create'))

WS.verifyResponseStatusCode(response, 400) 