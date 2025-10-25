import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.testobject.TestObject

String nonExistentId = "9900999999" 

TestObject patchTO = findTestObject('Users/TO_Users_PATCH_Update')

patchTO.setRestUrl(GlobalVariable.baseUrl + "/users?id=eq." + nonExistentId)

def response = WS.sendRequest(patchTO)

WS.verifyResponseStatusCode(response, 204) 


// Verifikasi tambahan: Memastikan tidak ada konten yang dikembalikan (sesuai 204 No Content)
WS.verifyResponseText(response, "")