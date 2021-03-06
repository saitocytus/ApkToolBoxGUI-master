package edu.jiangxin.apktoolbox.file;

import edu.jiangxin.apktoolbox.swing.extend.DirectorySelectButtonMouseAdapter;
import edu.jiangxin.apktoolbox.swing.extend.EasyPanel;
import edu.jiangxin.apktoolbox.utils.Constants;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

//https://www.zhihu.com/question/50890909
public class BatchRenamePanel extends EasyPanel {
    private JPanel sourcePanel;

    private JPanel targetPanel;

    private JPanel rulePanel;

    private JPanel hierarchyPanel;

    private JPanel othersPanel;

    private JPanel operationPanel;

    private JPanel statusPanel;

    private JTextField sourceTextField;
    private JTextField suffixTextField;
    private JCheckBox recursiveCheckBox;
    private JTextField targetTextField;
    private JRadioButton ruleRadioButton1;
    private JRadioButton ruleRadioButton2;
    private JRadioButton ruleRadioButton3;
    private JRadioButton ruleRadioButton4;
    private JRadioButton ruleRadioButton5;
    private JRadioButton ruleRadioButton6;
    private JRadioButton hierarchyRadioButton1;
    private JRadioButton hierarchyRadioButton2;
    private JRadioButton othersRadioButton1;
    private JRadioButton othersRadioButton2;
    private JRadioButton othersRadioButton3;
    private JTextField textField21;
    private JSpinner spinner21;
    private JCheckBox checkBox31;
    private JCheckBox checkBox32;
    private JCheckBox checkBox33;
    private JTextField textField31;
    private JTextField textField32;
    private JSpinner spinner31;
    private JSpinner spinner32;
    private JSpinner spinner41;
    private JTextField textField42;
    private JSpinner spinner51;
    private JSpinner spinner52;
    private JTextField textField61;
    private JTextField textField62;
    private JTextField currentFileTextField;

    public BatchRenamePanel() throws HeadlessException {
        super();
        initUI();
    }

    private void initUI() {
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxLayout);

        createSourcePanel();
        add(sourcePanel);
        add(Box.createVerticalStrut(Constants.DEFAULT_Y_BORDER));

        createTargetPanel();
        add(targetPanel);
        add(Box.createVerticalStrut(Constants.DEFAULT_Y_BORDER));

        createRulePanel();
        add(rulePanel);
        add(Box.createVerticalStrut(Constants.DEFAULT_Y_BORDER));

        createHierarchyPanel();
        add(hierarchyPanel);
        add(Box.createVerticalStrut(Constants.DEFAULT_Y_BORDER));

        createOthersPanel();
        add(othersPanel);
        add(Box.createVerticalStrut(Constants.DEFAULT_Y_BORDER));

        createOperationPanel();
        add(operationPanel);
        add(Box.createVerticalStrut(Constants.DEFAULT_Y_BORDER));

        createStatusPanel();
        add(statusPanel);
    }

    private void createSourcePanel() {
        sourcePanel = new JPanel();
        sourcePanel.setBorder(BorderFactory.createTitledBorder("1. ?????????"));
        sourcePanel.setLayout(new BorderLayout());

        JPanel secondLevelPanel = new JPanel();
        secondLevelPanel.setLayout(new BoxLayout(secondLevelPanel, BoxLayout.Y_AXIS));
        sourcePanel.add(secondLevelPanel);

        JPanel thirdLevelPanel1 = new JPanel();
        thirdLevelPanel1.setLayout(new BoxLayout(thirdLevelPanel1, BoxLayout.X_AXIS));

        JPanel thirdLevelPanel2 = new JPanel();
        thirdLevelPanel2.setLayout(new BoxLayout(thirdLevelPanel2, BoxLayout.X_AXIS));

        secondLevelPanel.add(thirdLevelPanel1);
        secondLevelPanel.add(Box.createHorizontalStrut(Constants.DEFAULT_X_BORDER));
        secondLevelPanel.add(thirdLevelPanel2);

        JLabel label = new JLabel("????????????????????????");
        sourceTextField = new JTextField();
        JButton button = new JButton("??????");
        button.addMouseListener(new DirectorySelectButtonMouseAdapter("Select a directory", sourceTextField));
        thirdLevelPanel1.add(label);
        thirdLevelPanel1.add(Box.createHorizontalStrut(Constants.DEFAULT_X_BORDER));
        thirdLevelPanel1.add(sourceTextField);
        thirdLevelPanel1.add(Box.createHorizontalStrut(Constants.DEFAULT_X_BORDER));
        thirdLevelPanel1.add(button);

        JLabel label2 = new JLabel("?????????");
        suffixTextField = new JTextField();
        suffixTextField.setToolTipText("an array of extensions, ex. {\"java\",\"xml\"}. If this parameter is empty, all files are returned.");
        recursiveCheckBox = new JCheckBox("???????????????");

        thirdLevelPanel2.add(label2);
        thirdLevelPanel2.add(Box.createHorizontalStrut(Constants.DEFAULT_X_BORDER));
        thirdLevelPanel2.add(suffixTextField);
        thirdLevelPanel2.add(Box.createHorizontalStrut(Constants.DEFAULT_X_BORDER));
        thirdLevelPanel2.add(recursiveCheckBox);
    }

    private void createTargetPanel() {
        targetPanel = new JPanel();
        targetPanel.setBorder(BorderFactory.createTitledBorder("2. ????????????"));
        targetPanel.setLayout(new BorderLayout());

        JPanel secondLevelPanel = new JPanel();
        secondLevelPanel.setLayout(new BoxLayout(secondLevelPanel, BoxLayout.X_AXIS));
        targetPanel.add(secondLevelPanel);

        JLabel label = new JLabel("???????????????????????????");
        targetTextField = new JTextField();
        JButton button = new JButton("??????");
        button.addMouseListener(new DirectorySelectButtonMouseAdapter("Select a directory", targetTextField));
        secondLevelPanel.add(label);
        secondLevelPanel.add(Box.createHorizontalStrut(Constants.DEFAULT_X_BORDER));
        secondLevelPanel.add(targetTextField);
        secondLevelPanel.add(Box.createHorizontalStrut(Constants.DEFAULT_X_BORDER));
        secondLevelPanel.add(button);
    }

    private void createRulePanel() {
        rulePanel = new JPanel();
        rulePanel.setBorder(BorderFactory.createTitledBorder("3. ????????????"));
        rulePanel.setLayout(new BorderLayout());

        JPanel secondLevelPanel = new JPanel();
        secondLevelPanel.setLayout(new BoxLayout(secondLevelPanel, BoxLayout.Y_AXIS));
        rulePanel.add(secondLevelPanel);

        JPanel thirdLevelPanel1 = new JPanel();
        thirdLevelPanel1.setLayout(new FlowLayout(FlowLayout.LEFT,10,3));

        JPanel thirdLevelPanel2 = new JPanel();
        thirdLevelPanel2.setLayout(new FlowLayout(FlowLayout.LEFT,10,3));

        JPanel thirdLevelPanel3 = new JPanel();
        thirdLevelPanel3.setLayout(new FlowLayout(FlowLayout.LEFT,10,3));

        JPanel thirdLevelPanel4 = new JPanel();
        thirdLevelPanel4.setLayout(new FlowLayout(FlowLayout.LEFT,10,3));

        JPanel thirdLevelPanel5 = new JPanel();
        thirdLevelPanel5.setLayout(new FlowLayout(FlowLayout.LEFT,10,3));

        JPanel thirdLevelPanel6 = new JPanel();
        thirdLevelPanel6.setLayout(new FlowLayout(FlowLayout.LEFT,10,3));

        secondLevelPanel.add(thirdLevelPanel1);
        secondLevelPanel.add(Box.createHorizontalStrut(Constants.DEFAULT_X_BORDER));
        secondLevelPanel.add(thirdLevelPanel2);
        secondLevelPanel.add(Box.createHorizontalStrut(Constants.DEFAULT_X_BORDER));
        secondLevelPanel.add(thirdLevelPanel3);
        secondLevelPanel.add(Box.createHorizontalStrut(Constants.DEFAULT_X_BORDER));
        secondLevelPanel.add(thirdLevelPanel4);
        secondLevelPanel.add(Box.createHorizontalStrut(Constants.DEFAULT_X_BORDER));
        secondLevelPanel.add(thirdLevelPanel5);
        secondLevelPanel.add(Box.createHorizontalStrut(Constants.DEFAULT_X_BORDER));
        secondLevelPanel.add(thirdLevelPanel6);

        ButtonGroup buttonGroup = new ButtonGroup();

        {
            ruleRadioButton1 = new JRadioButton("??????????????????????????????");
            ruleRadioButton1.setSelected(true);
            thirdLevelPanel1.add(ruleRadioButton1);
            buttonGroup.add(ruleRadioButton1);
        }

        {
            ruleRadioButton2 = new JRadioButton("?????????{String}??????????????????{Int}???");
            thirdLevelPanel2.add(ruleRadioButton2);
            buttonGroup.add(ruleRadioButton2);

            JLabel label21 = new JLabel("String");
            textField21 = new JTextField("");
            JLabel label22 = new JLabel("Int");
            spinner21 = new JSpinner();
            spinner21.setValue(4);
            thirdLevelPanel2.add(label21);
            thirdLevelPanel2.add(textField21);
            thirdLevelPanel2.add(label22);
            thirdLevelPanel2.add(spinner21);
        }

        {
            ruleRadioButton3 = new JRadioButton("?????????????????????");
            thirdLevelPanel3.add(ruleRadioButton3);
            buttonGroup.add(ruleRadioButton3);

            checkBox31 = new JCheckBox("??????????????????????????????");
            checkBox32 = new JCheckBox("?????????????????????");
            checkBox33 = new JCheckBox("?????????????????????");
            JLabel label31 = new JLabel("????????????");
            textField31 = new JTextField("_");
            JLabel label32 = new JLabel("??????");
            textField32 = new JTextField("File");
            JLabel label33 = new JLabel("????????????");
            spinner31 = new JSpinner();
            spinner31.setValue(1);
            JLabel label34 = new JLabel("????????????");
            spinner32 = new JSpinner();
            spinner32.setValue(3);
            thirdLevelPanel3.add(checkBox31);
            thirdLevelPanel3.add(checkBox32);
            thirdLevelPanel3.add(checkBox33);
            thirdLevelPanel3.add(label31);
            thirdLevelPanel3.add(textField31);
            thirdLevelPanel3.add(label32);
            thirdLevelPanel3.add(textField32);
            thirdLevelPanel3.add(label33);
            thirdLevelPanel3.add(spinner31);
            thirdLevelPanel3.add(label34);
            thirdLevelPanel3.add(spinner32);
        }

        {
            ruleRadioButton4 = new JRadioButton("??????{Int}??????????????????{String}");
            thirdLevelPanel4.add(ruleRadioButton4);
            buttonGroup.add(ruleRadioButton4);

            JLabel label41 = new JLabel("Int");
            spinner41 = new JSpinner();
            spinner41.setValue(0);
            JLabel label42 = new JLabel("String");
            textField42 = new JTextField("");
            thirdLevelPanel4.add(label41);
            thirdLevelPanel4.add(spinner41);
            thirdLevelPanel4.add(label42);
            thirdLevelPanel4.add(textField42);
        }

        {
            ruleRadioButton5 = new JRadioButton("????????????{Int:1}??????????????????{Int:2}?????????");
            thirdLevelPanel5.add(ruleRadioButton5);
            buttonGroup.add(ruleRadioButton5);

            JLabel label51 = new JLabel("Int:1");
            spinner51 = new JSpinner();
            spinner51.setValue(1);
            JLabel label52 = new JLabel("Int:2");
            spinner52 = new JSpinner();
            spinner52.setValue(1);
            thirdLevelPanel5.add(label51);
            thirdLevelPanel5.add(spinner51);
            thirdLevelPanel5.add(label52);
            thirdLevelPanel5.add(spinner52);
        }

        {
            ruleRadioButton6 = new JRadioButton("?????????????????????????????????{String:1}?????????{String:2}");
            thirdLevelPanel6.add(ruleRadioButton6);
            buttonGroup.add(ruleRadioButton6);

            JLabel label61 = new JLabel("String:1");
            textField61 = new JTextField("");
            JLabel label62 = new JLabel("String:2");
            textField62 = new JTextField("");
            thirdLevelPanel6.add(label61);
            thirdLevelPanel6.add(textField61);
            thirdLevelPanel6.add(label62);
            thirdLevelPanel6.add(textField62);
        }
    }

    private void createHierarchyPanel() {
        hierarchyPanel = new JPanel();
        hierarchyPanel.setBorder(BorderFactory.createTitledBorder("4. ????????????????????????"));
        hierarchyPanel.setLayout(new BorderLayout());

        JPanel secondLevelPanel = new JPanel();
        secondLevelPanel.setLayout(new BoxLayout(secondLevelPanel, BoxLayout.Y_AXIS));
        hierarchyPanel.add(secondLevelPanel);

        ButtonGroup buttonGroup = new ButtonGroup();

        hierarchyRadioButton1 = new JRadioButton("???????????????????????????????????????");
        secondLevelPanel.add(hierarchyRadioButton1);
        buttonGroup.add(hierarchyRadioButton1);

        hierarchyRadioButton2 = new JRadioButton("????????????????????????");
        hierarchyRadioButton2.setSelected(true);
        secondLevelPanel.add(hierarchyRadioButton2);
        buttonGroup.add(hierarchyRadioButton2);
    }

    private void createOthersPanel() {
        othersPanel = new JPanel();
        othersPanel.setBorder(BorderFactory.createTitledBorder("5. ??????"));
        othersPanel.setLayout(new BorderLayout());

        JPanel secondLevelPanel = new JPanel();
        secondLevelPanel.setLayout(new BoxLayout(secondLevelPanel, BoxLayout.Y_AXIS));
        othersPanel.add(secondLevelPanel);

        ButtonGroup buttonGroup = new ButtonGroup();

        othersRadioButton1 = new JRadioButton("???????????????");
        othersRadioButton1.setSelected(true);
        secondLevelPanel.add(othersRadioButton1);
        buttonGroup.add(othersRadioButton1);

        othersRadioButton2 = new JRadioButton("???????????????");
        secondLevelPanel.add(othersRadioButton2);
        buttonGroup.add(othersRadioButton2);

        othersRadioButton3 = new JRadioButton("??????????????????????????????????????????");
        secondLevelPanel.add(othersRadioButton3);
        buttonGroup.add(othersRadioButton3);
    }

    private void createOperationPanel() {
        operationPanel = new JPanel();
        operationPanel.setBorder(BorderFactory.createTitledBorder("6. ??????"));
        operationPanel.setLayout(new BorderLayout());

        JPanel secondLevelPanel = new JPanel();
        secondLevelPanel.setLayout(new BoxLayout(secondLevelPanel, BoxLayout.X_AXIS));
        operationPanel.add(secondLevelPanel);

        JButton button = new JButton("??????");
        button.addMouseListener(new StartButtonMouseAdapter());
        secondLevelPanel.add(button);
        secondLevelPanel.add(Box.createHorizontalGlue());
    }

    private void createStatusPanel() {
        statusPanel = new JPanel();
        statusPanel.setBorder(BorderFactory.createTitledBorder("????????????"));
        statusPanel.setLayout(new BorderLayout());

        JPanel secondLevelPanel = new JPanel();
        secondLevelPanel.setLayout(new BoxLayout(secondLevelPanel, BoxLayout.Y_AXIS));
        statusPanel.add(secondLevelPanel);

        currentFileTextField = new JTextField();
        secondLevelPanel.add(currentFileTextField);
    }

    private final class StartButtonMouseAdapter extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            String sourceString = sourceTextField.getText();
            if (StringUtils.isEmpty(sourceString)) {
                JOptionPane.showMessageDialog(BatchRenamePanel.this, "sourceString is empty", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            File sourceFile = new File(sourceString);
            if (!sourceFile.exists() || !sourceFile.isDirectory()) {
                JOptionPane.showMessageDialog(BatchRenamePanel.this, "sourceFile does not exist or not directory", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            String[] extensions = null;
            if (StringUtils.isNotEmpty(suffixTextField.getText())) {
                extensions = suffixTextField.getText().split(",");
            }
            Collection<File> fileCollection = FileUtils.listFiles(sourceFile, extensions, recursiveCheckBox.isSelected());
            Set<File> fileSet = new TreeSet<>(fileCollection);
            if (fileSet.isEmpty()) {
                JOptionPane.showMessageDialog(BatchRenamePanel.this, "fileSet is empty", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            for (File file : fileSet) {
                renameSingleFile(file);
                try {
                    String path = file.getCanonicalPath();
                    currentFileTextField.setText(path);
                    logger.info("current: " + path);
                } catch (IOException ex) {
                    logger.error("getCanonicalPath failed: IOException");
                }
            }
        }
    }

    private void renameSingleFile(File file) {
        String sourceFile = FilenameUtils.normalizeNoEndSeparator(file.getAbsolutePath());
        String sourceFileName = FilenameUtils.getName(sourceFile);
        String targetFileName = sourceFileName;
        String sourceDir = FilenameUtils.normalizeNoEndSeparator(sourceTextField.getText());
        String targetDir = FilenameUtils.normalizeNoEndSeparator(targetTextField.getText());
        String middleStr = StringUtils.substringBetween(sourceFile, sourceDir, sourceFileName);
        if (ruleRadioButton1.isSelected()) {
        } else if (ruleRadioButton2.isSelected()) {

        } else if (ruleRadioButton3.isSelected()) {

        } else if (ruleRadioButton4.isSelected()) {

        } else if (ruleRadioButton5.isSelected()) {
        } else if (ruleRadioButton6.isSelected()) {
            targetFileName = StringUtils.replace(sourceFileName, textField61.getText(), textField62.getText());
        } else {
            logger.error("ruleRadioButton select status error");
            return;
        }
        String targetFile = null;
        if (hierarchyRadioButton1.isSelected()) {
            targetFile = targetDir + File.separatorChar + targetFileName;
        } else if (hierarchyRadioButton2.isSelected()) {
            targetFile = targetDir + File.separatorChar + middleStr + targetFileName;
        } else {
            logger.error("hierarchyRadioButton select status error");
            return;
        }
        try {
            FileUtils.copyFile(new File(sourceFile), new File(targetFile));
        } catch (IOException e) {
            logger.error("copy file failed: IOException. current: " + sourceFile);
        }
        if (othersRadioButton1.isSelected()) {

        } else if (othersRadioButton2.isSelected()) {
            logger.info("delete: " + sourceFile);
        } else if (othersRadioButton3.isSelected()) {
            logger.info("delete: " + sourceFile);
        } else {
            logger.error("othersRadioButton select status error");
            return;
        }
    }
}
