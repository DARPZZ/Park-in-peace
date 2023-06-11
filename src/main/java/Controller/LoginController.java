package Controller;

import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;

public class LoginController
{
    private double strengthPercentage = 0;
    public void updatePasswordStrength(String password, ProgressBar progressBar, ProgressIndicator progressIndicator) {
        int passwordStrength = caclStrenght(password);
        strengthPercentage = (double) passwordStrength / 100.0;
        progressBar.setProgress(strengthPercentage);
        progressIndicator.setProgress(strengthPercentage);
    }
    private int caclStrenght(String password) {
        int strength = 0;
        int length = password.length();
        boolean hasNumbers = password.matches(".*\\d+.*");
        boolean hasSpecialChars = !password.matches("[A-Åa-å0-9 ]*");

        strength += length * 4;
        if (hasNumbers) {
            strength += 10;
        }
        if (hasSpecialChars) {
            strength += 10;
        }

        return Math.min(strength, 100);
    }

    public double getStrengthPercentage()
    {
        return strengthPercentage;
    }

    public void setStrengthPercentage(double strengthPercentage)
    {
        this.strengthPercentage = strengthPercentage;
    }
}
