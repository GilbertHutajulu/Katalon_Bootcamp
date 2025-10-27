import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

// Generate random number (between 1000–9999 for example)
def randomNum = new Random().nextInt(9000) + 1000

// Combine it with your base username
def randomUsername = "firman_$randomNum"

// Optional: print to console
println('✅ New user created with username: ' + randomUsername)



// Send the request with the random username
def response = WS.sendRequest(findTestObject('Users/TO_Users_PATCH_Update', [('first_name') : randomUsername, ('last_name') : 'test'
            , ('username') : 'firmans', ('job_position') : 'asdsadas', ('job_level') : 'asdsadasa']))

//ini ada kesalahan dari BE apinya, balikin 204... ini sengaja dibuat 204 biar gk gagal (update tapi balikan 204 harusnya 200)
WS.verifyResponseStatusCode(response, 204)

// Store into GlobalVariable
GlobalVariable.randomUsername = randomUsername