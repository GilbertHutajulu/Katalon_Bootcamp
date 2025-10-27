package internal

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.main.TestCaseMain


/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */
public class GlobalVariable {
     
    /**
     * <p></p>
     */
    public static Object base_url
     
    /**
     * <p></p>
     */
    public static Object api_key
     
    /**
     * <p></p>
     */
    public static Object baseUrl
     
    /**
     * <p></p>
     */
    public static Object apiKey
     
    /**
     * <p></p>
     */
    public static Object anonToken
     
    /**
     * <p></p>
     */
    public static Object username
     
    /**
     * <p></p>
     */
    public static Object randomUsername
     

    static {
        try {
            def selectedVariables = TestCaseMain.getGlobalVariables("default")
			selectedVariables += TestCaseMain.getGlobalVariables(RunConfiguration.getExecutionProfile())
    
            base_url = selectedVariables['base_url']
            api_key = selectedVariables['api_key']
            baseUrl = selectedVariables['baseUrl']
            apiKey = selectedVariables['apiKey']
            anonToken = selectedVariables['anonToken']
            username = selectedVariables['username']
            randomUsername = selectedVariables['randomUsername']
            
        } catch (Exception e) {
            TestCaseMain.logGlobalVariableError(e)
        }
    }
}
