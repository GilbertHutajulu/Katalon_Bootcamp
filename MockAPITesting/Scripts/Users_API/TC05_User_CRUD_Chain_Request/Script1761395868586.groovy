import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

// ==========================================
// 🔹 STEP 1: Generate Random Username
// ==========================================
def randomNum = new Random().nextInt(9000) + 1000
def randomUsername = "Firman$randomNum"
println("✅ Random username generated: $randomUsername")
GlobalVariable.randomUsername = randomUsername

// ==========================================
// 🔹 STEP 2: POST New User
// ==========================================
// Menggunakan TO_Users_POST_Create
def postResponse = WS.sendRequest(findTestObject('Users/TO_Users_POST_Create', [('first_name') : 'Firman', ('last_name') : 'test'
            , ('username') : randomUsername, ('job_position') : 'Quality Assurance', ('job_level') : 'Mid', ('salary') : 20000000
            , ('work_duration') : 3.5]))

WS.verifyResponseStatusCode(postResponse, 201)
println('✅ POST validation passed')

// ==========================================
// 🔹 STEP 3: UPDATE User using stored username
// ==========================================
// Menggunakan TO_User_PATCH_Update
//def updateResponse = WS.sendRequest(findTestObject('Folder Proyek/Users/TO_User_PATCH_Update', [('first_name') : 'Firman'
//            , ('last_name') : 'test', ('username') : GlobalVariable.randomUsername, ('job_position') : 'Senior QA Engineer'
//            , ('job_level') : 'Senior']))
//
//WS.verifyResponseStatusCode(updateResponse, 204) 
//println('✅ UPDATE validation passed')

// ==========================================
// 🔹 STEP 4: GET Single User (verify changes)
// ==========================================
// Menggunakan TO_Users_GET_All, asumsikan ini bekerja untuk filtering
//def getResponse = WS.sendRequest(findTestObject('Users/TO_Users_GET_All', [('username') : GlobalVariable.randomUsername]))
//
//WS.verifyResponseStatusCode(getResponse, 200)
//
//// Verifikasi elemen
//WS.verifyElementPropertyValue(getResponse, '[0].username', GlobalVariable.randomUsername)
//WS.verifyElementPropertyValue(getResponse, '[0].first_name', 'firman_updated') 
//println('✅ GET validation passed')

// ==========================================
// 🔹 STEP 5: DELETE User
// ==========================================
// Menggunakan TO_Users_DELETE
def deleteResponse = WS.sendRequest(findTestObject('Users/TO_Users_DELETE', [('username') : GlobalVariable.randomUsername]))

WS.verifyResponseStatusCode(deleteResponse, 204)
println('✅ DELETE validation passed')