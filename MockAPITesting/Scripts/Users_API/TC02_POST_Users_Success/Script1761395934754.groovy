import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject // Wajib untuk findTestObject()
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS // Wajib untuk WS.sendRequest()
import internal.GlobalVariable as GlobalVariable // Wajib untuk GlobalVariable
import java.util.Random // Wajib untuk Randomization

// ==========================================
// 🔹 STEP 1: Generate Data Unik (untuk menghindari 409 Conflict)
// ==========================================
def currentTimeMillis = System.currentTimeMillis()
def randomNum = new Random().nextInt(999) + 100 
def uniqueUsername = "FirmanUser_${currentTimeMillis}_${randomNum}"
def uniqueLastName = "Test_${randomNum}"

println("✅ Username unik di-generate: " + uniqueUsername)

// ==========================================
// 🔹 STEP 2: POST New User
// ==========================================
def postResponse = WS.sendRequest(findTestObject('Users/TO_Users_POST_Create', [
            ('first_name') : 'Firman', 
            ('last_name') : uniqueLastName, // Menggunakan data unik
            ('username') : uniqueUsername,  // Menggunakan data unik
            ('job_position') : 'Quality Assurance', 
            ('job_level') : 'Junior', 
            ('salary') : 20000000, 
            ('work_duration') : 0
]))

// ==========================================
// 🔹 STEP 3: Verifikasi Status Code
// ==========================================
WS.verifyResponseStatusCode(postResponse, 201)
println('✅ Verifikasi Status Code Berhasil: 201 Created')
