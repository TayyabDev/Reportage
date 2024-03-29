package app.java.com.view.ui.createTemplateViews;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import app.java.com.model.entities.account.TeqAccount;
import app.java.com.presenter.CreateTemplatePresenterImpl;
import app.java.com.presenter.interfaces.CreateTemplatePresenter;
import app.java.com.view.interfaces.CreateTemplateView;
import app.java.com.view.ui.UIHelpers;
import app.java.com.view.ui.createAccountViews.TeqDashboard;
import javax.swing.JCheckBox;
import app.java.com.view.ui.uploadTemplateViews.UploadTemplate;

public class CreateTemplate implements CreateTemplateView {

    private JFrame frame;
    private JPanel panel;
    private JButton create;
    private JButton view;
    private JButton select;
    private JButton createSQL;
    private CreateTemplatePresenter presenter;
    @SuppressWarnings("unused")
    private TeqAccount account;

    public CreateTemplate(JFrame frame, TeqAccount account) {
        this.frame = frame;
        this.account = account;

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.decode("#f1f8e9"));

        presenter = new CreateTemplatePresenterImpl();
        presenter.attachView(this);


        JButton back = UIHelpers.generateBackButton(0, 0, 50, 50);
        panel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TeqDashboard(frame, false, account);
            }
        });

        JLabel lTitle = new JLabel("Templates");
        lTitle.setFont(new Font(null, Font.BOLD, 36));
        lTitle.setBounds(400, 20, 200, 40);
        panel.add(lTitle);

        create = UIHelpers.buttonGenerator("Create a new template using file");
        create.setBounds(360, 100, 300, 50);
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc =
                        new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                jfc.setDialogTitle("Select your file");
                jfc.addChoosableFileFilter(new FileNameExtensionFilter("*.csv", "csv"));
                jfc.addChoosableFileFilter(new FileNameExtensionFilter("*.xlsx", "xlsx"));

                int returnValue = jfc.showSaveDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    presenter.createTemplateWithFile(jfc.getSelectedFile().getAbsolutePath(),
                            account);
                }

            }
        });

        createSQL = UIHelpers.buttonGenerator("Create a new template using SQL");
        createSQL.setBounds(360, 200, 300, 50);
        createSQL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get sql query
                String sqlQuery = JOptionPane.showInputDialog(frame, "Enter the query");
                if (sqlQuery != null) {
                    presenter.createTemplateWithQuery(sqlQuery);
                }
            }
        });

        view = UIHelpers.buttonGenerator("View the existing templates");
        view.setBounds(360, 300, 300, 50);
        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ExistingTemplate(frame, account);
                presenter.unbindView();
            }
        });


        select = UIHelpers.buttonGenerator("Upload data to a template");
        select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UploadTemplate(frame, account);
                presenter.unbindView();
            }
        });

        select.setBounds(360, 400, 300, 50);


        panel.add(create);
        panel.add(createSQL);
        panel.add(view);
        panel.add(select);

        this.frame.setContentPane(panel);
        this.frame.revalidate();


    }

    public ArrayList<String> parseFile(File file) {
        // read the column names
        ArrayList<String> headers = new ArrayList<>();
        return headers;

    }


    @Override
    public void onSuccessTemplateCreated() {
        JOptionPane.showMessageDialog(frame, "Template created.");
    }

    @Override
    public boolean isFileValid(String filePath) {
        // check if excel or csv file
        if (filePath.substring(filePath.length() - 3).equals("csv")
                || filePath.substring(filePath.length() - 4).equals("xlsx")) {
            return true;
        }
        return false;
    }

    @Override
    public void onErrorUploadingFile() {
        JOptionPane.showMessageDialog(frame, "There was an error uploading the file.");
    }

    public void disposeView() {
        frame.dispose();
        presenter.unbindView();
    }

    @Override
    /*
     * create a drop down box and display all the sheetNames for client to choose from
     *
     */
    public void displaySheetNames(java.util.List<String> sheetNames) {
        Object[] options = sheetNames.toArray();
        Object selectedOption = JOptionPane.showInputDialog(null, "choose one sheet", "Input",
                JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        if (selectedOption != null) {
            String selectedSheet = selectedOption.toString();
            presenter.sheetSelected(selectedSheet);
        } else {
            presenter.sheetSelected(null);
        }


    }

    @Override
    public void displayRequiredColumnNames(java.util.List<String> columnNames) {
        java.util.List<JCheckBox> cbs = new ArrayList<JCheckBox>();
        for (String columnName : columnNames) {
            JCheckBox box = new JCheckBox(columnName);
            cbs.add(box);
        }
        Object[] obj = (Object[]) cbs.toArray(new Object[cbs.size()]);
        int action = JOptionPane.showConfirmDialog(null, obj);
        if (action == JOptionPane.YES_OPTION) {
            List<String> selectedPKs = new ArrayList<>();
            for (JCheckBox cb : cbs) {
                if (cb.isSelected()) {
                    selectedPKs.add(cb.getText());
                }
            }
            if (selectedPKs.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please select at least one option.");
            }
            presenter.PKSelected(selectedPKs);
        } else {
            presenter.PKSelected(null);
        }

    }
}



