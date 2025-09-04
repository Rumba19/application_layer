/**
 * Step 1: Basic Application Layer Security Class
 * Layer 7 Protection Framework
 */
public class Layer7Security {
    
    /**
     * Main security validation method
     * This will check if a request is safe or malicious
     */
    public boolean validateRequest(String userInput, String clientIP) {
        System.out.println("=== Validating Request ===");
        System.out.println("Input: " + userInput);
        System.out.println("Client IP: " + clientIP);
        
        // We'll add security checks here step by step
        
        return true; // For now, allow all requests
    }
    
    /**
     * Demo method to test our security
     */
    public static void main(String[] args) {
        Layer7Security security = new Layer7Security();
        
        // Test with normal input
        boolean result1 = security.validateRequest("Hello World", "192.168.1.1");
        System.out.println("Result: " + (result1 ? "ALLOWED" : "BLOCKED"));
        System.out.println();
        
        // Test with suspicious input (we'll add this detection next)
        boolean result2 = security.validateRequest("SELECT * FROM users", "192.168.1.2");
        System.out.println("Result: " + (result2 ? "ALLOWED" : "BLOCKED"));
    }
}