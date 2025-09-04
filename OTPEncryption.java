/**
 * Step 1: One-Time Pad Encryption - Basic Structure
 * Message: "MY NAME IS UNKNOWN"
 */
public class OTPEncryption {
    
    public static void main(String[] args) {
        
        // The message we want to encrypt
        String message = "MY NAME IS UNKNOWN";
        
        System.out.println("=== ONE-TIME PAD ENCRYPTION ===");
        System.out.println("Original Message: " + message);
        System.out.println("Message Length: " + message.length() + " characters");
        System.out.println();
        
        // Step 1: Show the message as bytes
        byte[] messageBytes = message.getBytes();
        
        System.out.println("=== MESSAGE ANALYSIS ===");
        System.out.printf("%-4s %-8s %-10s%n", "Pos", "Char", "ASCII Value");
        System.out.println("-".repeat(25));
        
        for (int i = 0; i < messageBytes.length; i++) {
            char character = message.charAt(i);
            int asciiValue = messageBytes[i] & 0xFF; // Convert to unsigned
            
            System.out.printf("%-4d %-8c %-10d%n", i, character, asciiValue);
        }
        
        System.out.println("\nNext Step: Generate random key and encrypt!");
    }
}