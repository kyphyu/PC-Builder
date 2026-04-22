// Swing: toolkit for creating windows, buttons, and pop-ups
import javax.swing.*;
// Handles image data stored in the computer's memory
import java.awt.image.BufferedImage;
// Makes the program wait for the user to finish an action
import java.util.concurrent.CountDownLatch;
// ZXing creates QR code
import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class PromptPay implements PaymentStrategy {
    @Override
    public void pay(double grandTotal) {
        showQRWindow(grandTotal);

        loadingEffect();

        // Display result
        System.out.println("\n--- Payment Summary ---");
        System.out.println("Payment Method: PromptPay");
        System.out.printf("Amount Paid: %s%.2f%n", Order.CURRENCY, grandTotal);
        System.out.println("Order confirmed!");
        System.out.println("-----------------------");
    }

    private void showQRWindow(double amount) {
        try {
            String text = "AMOUNT = " + String.format("%.2f", amount);
            int size = 300;
            // Converts text into a matrix of 1s and 0s
            BitMatrix matrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, size, size);
            // Converts matrix into an image that can be displayed
            BufferedImage image = MatrixToImageWriter.toBufferedImage(matrix);

            // Gate that stops the program until 1 turns to 0
            CountDownLatch latch = new CountDownLatch(1);

            // Creates the window frame
            JFrame frame = new JFrame("Scan QR to Pay");
            // Closes the window when 'X' is clicked
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Sets the window's width and height in pixels
            frame.setSize(300, 300);
            // Centers the window in the middle of the screen
            frame.setLocationRelativeTo(null);

            // Puts QR image onto a label so that it can be put stuck inside the window
            JLabel label = new JLabel(new ImageIcon(image));
            frame.add(label);

            // Watches the window
            frame.addWindowListener(new java.awt.event.WindowAdapter() {
                // When the QR window is closed, the gate count goes from 1 to 0
                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {
                    latch.countDown(); // program resumes
                }
            });
            // Makes the window appear on the screen
            frame.setVisible(true);
            // Pauses the program until the QR window is closed
            latch.await();
        } catch (Exception e) {
            System.out.println("Error generating QR code.");
        }
    }

    // Loading animation
    private void loadingEffect() {
        System.out.print("Processing");

        try {
            for (int i = 0; i < 3; i++) {
                Thread.sleep(700);
                System.out.print(".");
            }
            Thread.sleep(700);
        } catch (InterruptedException e) {
            System.out.println("\nProcessing interrupted.");
        }

        System.out.println();
    }
}