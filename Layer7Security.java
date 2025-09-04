/**
 * Layer 7 Protection Framework
 */
public class Layer7Security {
    

    public boolean validateRequest(String userInput, String clientIP) {
        System.out.println("=== Validating Request ===");
        System.out.println("Input: " + userInput);
        System.out.println("Client IP: " + clientIP);
        
        // We'll add security checks here step by step
        
        return true; // For now, allow all requests
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
    }
}