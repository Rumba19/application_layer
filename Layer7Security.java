import java.util.regex.Pattern;

/**
 * Layer 7 Protection Framework
 */
public class Layer7Security {
    

    public boolean validateRequest(String userInput, String clientIP) {
        System.out.println("=== Validating Request ===");
        System.out.println("Input: " + userInput);
        System.out.println("Client IP: " + clientIP);
        
         if (detectSQLInjection(userInput)) {
        System.out.println(" THREAT DETECTED: SQL Injection attempt!");
        return false; // Block the request
         }
    
        System.out.println(" SQL Injection check: PASSED");

        if (detectXSS(userInput)) {
        System.out.println(" THREAT DETECTED: XSS attempt!");
        return false; // Block the request
        }
        System.out.println("XSS check: PASSED");
        return true; // For now, allow all requests
    }
    
    private static final Pattern SQL_INJECTION = Pattern.compile(
        "(?i)(SELECT|INSERT|UPDATE|DELETE|DROP|UNION|CREATE|ALTER|EXEC|SCRIPT)", 
        Pattern.CASE_INSENSITIVE
    );

    private static final Pattern XSS_PATTERN = Pattern.compile(
    "(?i)(<script|</script|javascript:|vbscript:|onload=|onerror=|onclick=|onmouseover=)", 
    Pattern.CASE_INSENSITIVE
    );
  
    private boolean detectSQLInjection(String input) {
            if (input == null) return false;
            
            // Check if input contains dangerous SQL keywords
            if (SQL_INJECTION.matcher(input).find()) {
                return true; // SQL injection detected!
            }
            
            // Check for common SQL injection characters
            if (input.contains("'") || input.contains("--") || input.contains(";")) {
                System.out.println(" Suspicious characters detected: quotes, comments, or semicolons");
                return true;
            }
            
            return false; // No SQL injection found
    }

    private boolean detectXSS(String input) {
    if (input == null) return false;
    
    // Check for dangerous HTML/JavaScript patterns
    if (XSS_PATTERN.matcher(input).find()) {
        return true; // XSS attack detected!
    }
    
    // Check for common XSS characters
    if (input.contains("<") && input.contains(">")) {
        System.out.println("Suspicious HTML tags detected");
        return true;
    }
        
        return false; // No XSS found
    }
    
    public static void main(String[] args) {
        Layer7Security security = new Layer7Security();

                System.out.println("=== TESTING SQL INJECTION PROTECTION ===\n");

        
        // Test with normal input
                System.out.println("TEST 1: Normal Input");
        boolean result1 = security.validateRequest("Hello World", "192.168.1.1");
        System.out.println("Result: " + (result1 ? "ALLOWED" : " BLOCKED"));
        System.out.println();
      
         System.out.println("TEST 2: SQL Injection Attack");

        boolean result2 = security.validateRequest("SELECT * FROM users", "192.168.1.2");
        System.out.println("Result: " + (result2 ? "ALLOWED" : " BLOCKED"));
        System.out.println();
          System.out.println("TEST 3: SQL Injection with Quotes");
        boolean result3 = security.validateRequest("admin'; DROP TABLE users; --", "192.168.1.3");
        System.out.println("Result: " + (result3 ? " ALLOWED" : " BLOCKED"));
        System.out.println();
        
        // Test 4: Union-based SQL injection (should be blocked)
        System.out.println("TEST 4: Union-based Attack");
        boolean result4 = security.validateRequest("' UNION SELECT password FROM users --", "192.168.1.4");
        System.out.println("Result: " + (result4 ? " ALLOWED" : " BLOCKED"));
        System.out.println();
        
        // Test 5: Normal search query (should be allowed)
        System.out.println("TEST 5: Normal Search");
        boolean result5 = security.validateRequest("search for products", "192.168.1.5");
        System.out.println("Result: " + (result5 ? "ALLOWED" : " BLOCKED"));

  

        // Test 6: XSS Script injection (should be blocked)
        System.out.println("TEST 6: XSS Script Attack");
        boolean result6 = security.validateRequest("<script>alert('hacked')</script>", "192.168.1.6");
        System.out.println("Result: " + (result6 ? "ALLOWED" : " BLOCKED"));
        System.out.println();

        // Test 7: XSS JavaScript URL (should be blocked)
        System.out.println("TEST 7: XSS JavaScript URL");
        boolean result7 = security.validateRequest("javascript:alert('xss')", "192.168.1.7");
        System.out.println("Result: " + (result7 ? "ALLOWED" : " BLOCKED"));
        System.out.println();

        // Test 8: XSS Image onerror (should be blocked)
        System.out.println("TEST 8: XSS Image Attack");
        boolean result8 = security.validateRequest("<img onerror=\"alert('xss')\" src=\"x\">", "192.168.1.8");
        System.out.println("Result: " + (result8 ? "ALLOWED" : " BLOCKED"));
        System.out.println();

        // Test 9: Normal HTML-like text (should be allowed)
        System.out.println("TEST 9: Normal Text with Brackets");
        boolean result9 = security.validateRequest("Price: 5 < 10 and 10 > 5", "192.168.1.9");
        System.out.println("Result: " + (result9 ? "ALLOWED" : " BLOCKED"));
            }
}