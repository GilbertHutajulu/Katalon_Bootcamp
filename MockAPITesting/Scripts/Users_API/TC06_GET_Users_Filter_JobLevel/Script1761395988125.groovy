import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.testobject.TestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import groovy.json.JsonSlurper // Wajib ditambahkan untuk parsing JSON array

// ==========================================
// 🔹 STEP 1: Konfigurasi Filter & Test Object
// ==========================================
String filterLevel = "Senior" // Level yang ingin difilter

// --- KODE BASE URL ---
String baseUrl = "https://hjaltjbqyttixmfubktb.supabase.co/rest/v1" 
String endpoint = "/users?job_level=eq." + filterLevel
String fullUrl = baseUrl + endpoint
// --- AKHIR KODE BASE URL ---

KeywordUtil.logInfo("Mengirim permintaan ke URL: " + fullUrl)

// Mengambil Test Object dan Mengatur URL Penuh
TestObject getFilterTO = findTestObject('Users/TO_Users_GET_All') 
getFilterTO.setRestUrl(fullUrl)

// ==========================================
// 🔹 STEP 2: Mengirim Request
// ==========================================
def response = WS.sendRequest(getFilterTO)

// ==========================================
// 🔹 STEP 3: Verifikasi Status Code
// ==========================================
WS.verifyResponseStatusCode(response, 200)
KeywordUtil.markPassed("Verifikasi Status Code 200 berhasil.")

// ==========================================
// 🔹 STEP 4: Verifikasi Data Array (Menggunakan JsonSlurper)
// ==========================================

def jsonSlurper = new JsonSlurper()
// Parsing Body Response (berupa List/Array) menjadi objek Groovy
def responseList = jsonSlurper.parseText(response.getResponseText()) 

// Memastikan bahwa hasil filter tidak kosong
WS.verifyGreaterThan(responseList.size(), 0)

// Memastikan setiap elemen dalam list memiliki 'job_level' yang sesuai
for (def user : responseList) {
    // Verifikasi bahwa nilai 'job_level' adalah sama dengan 'filterLevel'
    WS.verifyEqual(user.job_level, filterLevel)
}

KeywordUtil.markPassed("Verifikasi filter job_level '" + filterLevel + 
    "' berhasil: " + responseList.size() + " user ditemukan dengan level tersebut.")