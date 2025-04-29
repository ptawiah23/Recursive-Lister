import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class RecursiveLister extends JFrame {

    private JTextArea textArea;

    public RecursiveLister() {
        // Window title
        setTitle("Recursive File Lister");

        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Recursive File Lister", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton startButton = new JButton("Start");
        JButton quitButton = new JButton("Quit");

        buttonPanel.add(startButton);
        buttonPanel.add(quitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        startButton.addActionListener((ActionEvent e) -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnValue = chooser.showOpenDialog(this);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedDir = chooser.getSelectedFile();
                textArea.setText(""); // Clear previous output
                listFiles(selectedDir, textArea);
            }
        });

        quitButton.addActionListener((e) -> System.exit(0));
    }

    private void listFiles(File dir, JTextArea textArea) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                textArea.append(file.getAbsolutePath() + "\n");
                if (file.isDirectory()) {
                    listFiles(file, textArea); // Recurse into subdirectory
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RecursiveLister().setVisible(true));
    }
}
