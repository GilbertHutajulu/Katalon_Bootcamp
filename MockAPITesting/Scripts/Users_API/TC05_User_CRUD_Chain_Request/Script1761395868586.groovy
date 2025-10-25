import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.testobject.TestObjectProperty
import internal.GlobalVariable

// --- 1. POST (Create) ---
KeywordUtil.logInfo("--- 1. POST: Creating new user ---")
def postResp = WS.sendRequest(findTestObject('Users/TO_Users_POST_Create'))

// Verifikasi Status dan Ekstrak ID
WS.verifyResponseStatusCode(postResp, 201)
String newId = WS.getElementPropertyValue(postResp, '[0].id').toString()
KeywordUtil.markInfo("Created User ID: " + newId)

GlobalVariable.new_user_id = newId

// --- 2. GET (Read - Verify Creation) ---
KeywordUtil.logInfo("--- 2. GET: Verifying creation ---")
TestObject getSingleTO = findTestObject('Users/TO_Users_GET_All')
getSingleTO.setRestUrl(GlobalVariable.baseUrl + "/users?id=eq." + newId)
ResponseObject getResp = WS.sendRequest(getSingleTO)

WS.verifyResponseStatusCode(getResp, 200)
WS.verifyElementPropertyValue(getResp, '[0].id', newId) 

// --- 3. PATCH (Update) ---
KeywordUtil.logInfo("--- 3. PATCH: Updating job_level to Senior ---")
ResponseObject patchResp = WS.sendRequest(findTestObject('Users/TO_Users_PATCH_Update'))
WS.verifyResponseStatusCode(patchResp, 204) // No Content Expected

// --- 4. GET (Read - Verify Update) ---
KeywordUtil.logInfo("--- 4. GET: Verifying update ---")
ResponseObject getUpdatedResp = WS.sendRequest(getSingleTO)
WS.verifyResponseStatusCode(getUpdatedResp, 200)
WS.verifyElementPropertyValue(getUpdatedResp, '[0].job_level', 'Senior')

// --- 5. DELETE (Delete) ---
KeywordUtil.logInfo("--- 5. DELETE: Deleting user ---")
ResponseObject deleteResp = WS.sendRequest(findTestObject('Users/TO_Users_DELETE'))
WS.verifyResponseStatusCode(deleteResp, 204)

// --- 6. GET (Verify Deletion) ---
KeywordUtil.logInfo("--- 6. GET: Verifying deletion (expecting 404 or empty array) ---")
ResponseObject getDeletedResp = WS.sendRequest(getSingleTO)
WS.verifyResponseStatusCode(getDeletedResp, 200) 
WS.verifyElementText(getDeletedResp, '$', '[]') // Verifikasi respons adalah array kosong