import java.util.Scanner;

public class PasswordStrengthCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a password: ");
        String password = scanner.nextLine();
        
        int strengthScore = calculateStrength(password);
        String strength = getStrengthCategory(strengthScore);
        long bruteForceTime = calculateBruteForceTime(password);
        
        System.out.println("Password Strength: " + strength);
        System.out.println("Estimated time to brute-force: " + formatTime(bruteForceTime));
    }

    private static int calculateStrength(String password) {
        int score = 0;
        if (password.length() >= 8) score++;
        if (password.matches(".*[a-z].*")) score++;
        if (password.matches(".*[A-Z].*")) score++;
        if (password.matches(".*[0-9].*")) score++;
        if (password.matches(".*[!@#$%^&()-+].*")) score++;
        return score;
    }

    private static String getStrengthCategory(int score) {
        switch (score) {
            case 5: return "Very Strong";
            case 4: return "Strong";
            case 3: return "Moderate";
            case 2: return "Weak";
            default: return "Very Weak";
        }
    }

    private static long calculateBruteForceTime(String password) {
        int charsetSize = 0;
        if (password.matches(".*[a-z].*")) charsetSize += 26;
        if (password.matches(".*[A-Z].*")) charsetSize += 26;
        if (password.matches(".*[0-9].*")) charsetSize += 10;
        if (password.matches(".*[!@#$%^&()-+].*")) charsetSize += 10;
        
        long combinations = (long) Math.pow(charsetSize, password.length());
        long attemptsPerSecond = 1000000000; // 1 billion attempts per second
        return combinations / attemptsPerSecond;
    }

    private static String formatTime(long seconds) {
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;
        long years = days / 365;
        
        if (years > 0) return years + " years";
        if (days > 0) return days + " days";
        if (hours > 0) return hours + " hours";
        if (minutes > 0) return minutes + " minutes";
        return seconds + " seconds";
    }
}
