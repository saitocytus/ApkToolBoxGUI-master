package edu.jiangxin.apktoolbox.file.checksum;

import edu.jiangxin.apktoolbox.swing.extend.EasyPanel;
import edu.jiangxin.apktoolbox.utils.Constants;
import org.apache.commons.codec.digest.DigestUtils;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;

public class CheckDigestPanel extends EasyPanel {
    private static final long serialVersionUID = 63924900336217723L;

    private JPanel fileNamePanel;

    private JTextField fileNameTextField;
    private JButton fileNameButton;

    private JTextArea fileSums;
    private JScrollPane fileSumScrollPanel;

    private JTextArea inputSums;
    private JScrollPane inputSumsScroll;

    private JPanel operationPanel;

    private JButton compareButton;

    private JTextField compareResult;

    private JComboBox<Hash> digestTypeComboBox;
    private final String calculating = "Calculating...";

    private static File selectedFile;
    private static Hash selectedHash;
    private static String hashResult;

    public CheckDigestPanel() {
        super();
        initUI();
    }

    private void initUI() {
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxLayout);

        createFileNamePanel();
        add(fileNamePanel);

        add(Box.createVerticalStrut(Constants.DEFAULT_Y_BORDER));

        createFileSumScrollPanel();
        add(fileSumScrollPanel);

        add(Box.createVerticalStrut(Constants.DEFAULT_Y_BORDER));

        createInputSumScrollPanel();
        add(inputSumsScroll);

        add(Box.createVerticalStrut(Constants.DEFAULT_Y_BORDER));

        createOperationPanel();
        add(operationPanel);
    }

    private void createFileNamePanel() {
        fileNamePanel = new JPanel();

        fileNameTextField = new JTextField();

        fileNameButton = new JButton("Choose File");
        fileNameButton.setFocusPainted(false);
        fileNameButton.addActionListener(arg0 -> {

            JFileChooser openFile = new JFileChooser();
            openFile.setCurrentDirectory(new File(System.getProperty("user.dir")));
            openFile.setPreferredSize(new Dimension(400, 300));

            int fileSelect = openFile.showSaveDialog(null);
            if (fileSelect == JFileChooser.APPROVE_OPTION) {
                selectedFile = null;

                Thread fileThread = (new Thread(() -> {
                    fileNameButton.setEnabled(false);
                    selectedFile = openFile.getSelectedFile();

                    if (selectedFile != null) {
                        fileNameTextField.setText(selectedFile.getName());
                        selectedHash = null;
                        selectedHash = (Hash) digestTypeComboBox.getSelectedItem();
                        calculate();
                    }
                    fileNameButton.setEnabled(true);
                }));
                fileThread.start();

                fileSums.setText(calculating);
            }
        });

        fileNamePanel.setLayout(new BoxLayout(fileNamePanel, BoxLayout.X_AXIS));
        fileNamePanel.add(fileNameTextField);
        fileNamePanel.add(Box.createHorizontalStrut(Constants.DEFAULT_X_BORDER));
        fileNamePanel.add(fileNameButton);
    }

    private void createFileSumScrollPanel() {
        fileSums = new JTextArea(5, 4);
        fileSums.setLineWrap(true);
        fileSums.setText("file not selected");
        fileSums.setEditable(false);
        fileSumScrollPanel = new JScrollPane(fileSums);
    }

    private void createInputSumScrollPanel() {
        inputSums = new JTextArea(5, 4);
        inputSums.setLineWrap(true);
        inputSums.setText("input sums");
        inputSumsScroll = new JScrollPane(inputSums);
    }

    private void createOperationPanel() {
        operationPanel = new JPanel();

        compareButton = new JButton("Compare");
        compareButton.setFocusPainted(false);
        compareButton.addActionListener(arg0 -> {

            if (!fileSums.getText().equals(calculating)) {
                if (selectedHash != null && selectedFile != null && inputSums.getText() != null
                        && !inputSums.getText().equals("")) {

                    if (fileSums.getText().equals(inputSums.getText())) {
                        compareResult.setText("match :)");
                    } else if (fileSums.getText().toLowerCase().equals(inputSums.getText().toLowerCase())) {
                        compareResult.setText("match :/");
                        JOptionPane.showMessageDialog(null, "Strings match but case insensitive :/", ":/",
                                JOptionPane.WARNING_MESSAGE);
                    } else {
                        compareResult.setText("don't match :(");
                    }

                    return;
                }
            }

            JOptionPane.showMessageDialog(null, "Something not right :(", ":(", JOptionPane.ERROR_MESSAGE);
        });

        digestTypeComboBox = new JComboBox<>(new Vector<>(Arrays.asList(Hash.values())));
        digestTypeComboBox.addActionListener(e -> {
            Hash selectItem = (Hash) digestTypeComboBox.getSelectedItem();

            if (selectedHash == null || (selectedHash.getId() != selectItem.getId())) {
                selectedHash = selectItem;
                if (selectedFile != null) {
                    Thread calculateThread = (new Thread(() -> {
                        fileNameButton.setEnabled(false);
                        compareResult.setText("");
                        calculate();
                        fileNameButton.setEnabled(true);
                    }));
                    calculateThread.start();
                    fileSums.setText(calculating);
                }
            }

        });

        compareResult = new JTextField("");
        compareResult.setEditable(false);
        compareResult.setBorder(null);

        operationPanel.setLayout(new BoxLayout(operationPanel, BoxLayout.X_AXIS));
        operationPanel.add(compareButton);
        operationPanel.add(Box.createHorizontalStrut(Constants.DEFAULT_X_BORDER));
        operationPanel.add(digestTypeComboBox);
        operationPanel.add(Box.createHorizontalStrut(Constants.DEFAULT_X_BORDER));
        operationPanel.add(compareResult);
    }

    private void calculate() {
        hashResult = calculate(selectedHash, selectedFile);
        fileSums.setText(hashResult);
    }

    private String calculate(final Hash selectedHash, final File file) {
        String result = "";
        try (FileInputStream fis = new FileInputStream(file)) {
            switch (selectedHash) {
                case MD5: {
                    result = DigestUtils.md5Hex(fis);
                    break;
                }
                case Sha1: {
                    result = DigestUtils.sha1Hex(fis);
                    break;
                }
                case Sha256: {
                    result = DigestUtils.sha256Hex(fis);
                    break;
                }
                case Sha384: {
                    result = DigestUtils.sha384Hex(fis);
                    break;
                }
                case Sha512: {
                    result = DigestUtils.sha512Hex(fis);
                    break;
                }
                default: {
                    result = "return of the jedi";
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            logger.error("calculate, FileNotFoundException");
        } catch (IOException e) {
            logger.error("calculate, IOException");
        }
        return result;
    }

    enum Hash {
        MD5(1, "MD5"), Sha1(2, "Sha1"), Sha256(3, "Sha256"), Sha384(4, "Sha384"), Sha512(5, "Sha512");

        private int id;
        private String name;

        Hash(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
