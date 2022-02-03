import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
class Student {
    private Long studentId;
    private String name;
    private String surname;
    private String middleName; 
    private Integer groupNumber;
    private Float averageMark;
    private Float familyMemberSalary;
    public Student(String name, String surname, String middleName, int groupNumber, float averageMark, float familyMemberSalary) {
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.groupNumber = groupNumber;
        this.averageMark = averageMark;
        this.familyMemberSalary = familyMemberSalary;
    }
    public Student(Long studentId , String name, String surname, String middleName, int groupNumber, float averageMark, float familyMemberSalary) {
        this.studentId = studentId;
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.groupNumber = groupNumber;
        this.averageMark = averageMark;
        this.familyMemberSalary = familyMemberSalary;
    }
    public Long getStudentId() {
        return studentId;
    }
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() { return surname; }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getMiddleName() {
        return middleName;
    }
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    public Integer getGroupNumber() {
        return groupNumber;
    }
    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }
    public Float getAverageMark() {
        return averageMark;
    }
    public void setAverageMark(float averageMark) {
        this.averageMark = averageMark;
    }
    public Float getFamilyMemberSalary() {
        return familyMemberSalary;
    }
    public void setFamilyMemberSalary(float familyMemberSalary) {
        this.familyMemberSalary = familyMemberSalary;
    }
    @Override
    public String toString() {
        return "Prac10.StudentsTask.Student{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", middleName='" + middleName + '\'' +
                ", groupNumber=" + groupNumber +
                ", averageMark=" + averageMark +
                ", familyMemberSalary=" + familyMemberSalary +
                '}';
    }
}
interface StudentDAO
{
    Long addStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(Long contactId);
    Student getStudent(Long contactId);
    List<Student> findStudents();
}
class StudentManager
{
    private StudentDAO dao;
    public StudentManager() {
        dao = StudentDAOFactory.getStudentDAO();
    }
    // Добавление контакта - возвращает ID добавленного контакта
    public Long addStudent(Student student) {
        return dao.addStudent(student);
    }
    // Редактирование контакта
    public void updateStudent(Student student) {
        dao.updateStudent(student);
    }
    // Удаление контакта по его ID
    public void deleteStudent(Long studentId) {
        dao.deleteStudent(studentId);
    }
    // Получение одного контакта
    public Student getStudent(Long studentId) {
        return dao.getStudent(studentId);
    }
    // Получение списка контактов
    public List<Student> findStudents() {
        return dao.findStudents();
    }
}

class StudentDAOFactory
{
    public static StudentDAO getStudentDAO() {
        return new StudentSimpleDAO();
    }
}

final class StudentSimpleDAO implements StudentDAO
{
    private final List<Student> students = new ArrayList<>();
    public StudentSimpleDAO() {
        addStudent(new Student("Паша", "Степанов", "Александрович", 32, 4.3f, 13_000));
        addStudent(new Student("Катя", "Костюкова", "Степановна", 33, 4.5f, 25_000));
        addStudent(new Student("Саша", "Иванов", "Александрович", 31, 3.9f, 10_000));
    }
    @Override
    public Long addStudent(Student student) {
        Long id = generateStudentId();
        student.setStudentId(id);
        students.add(student);
        return id;
    }
    @Override
    public void updateStudent(Student student) {
        Student oldStudent = getStudent(student.getStudentId());
        if (oldStudent != null) {
            oldStudent.setName(student.getName());
            oldStudent.setSurname(student.getSurname());
            oldStudent.setMiddleName(student.getMiddleName());
            oldStudent.setGroupNumber(student.getGroupNumber());
            oldStudent.setAverageMark(student.getAverageMark());
            oldStudent.setFamilyMemberSalary(student.getFamilyMemberSalary());
        }
    }
    @Override
    public void deleteStudent(Long studentId) {
        for(Iterator<Student> it = students.iterator(); it.hasNext();) {
            Student cnt = it.next();
            if(cnt.getStudentId().equals(studentId)) {
                it.remove();
            }
        }
    }
    @Override
    public Student getStudent(Long studentId) {
        for(Student student : students) {
            if(student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }
    @Override
    public List<Student> findStudents() {
        return students;
    }
    private Long generateStudentId() {
        Long studentId = Math.round(Math.random() * 1000 + System.currentTimeMillis());
        while(getStudent(studentId) != null) {
            studentId = Math.round(Math.random() * 1000 + System.currentTimeMillis());
        }
        return studentId;
    }
}

class ContactModel extends AbstractTableModel {
    private static final String[] headers = {"ID", "Имя", "Фамилия", "Отчество", "Номер группы", "Средний балл", "Доход на члена семьи"};
    private final List<Student> students;
    public ContactModel (List<Student> students) {
        this.students = students;
    }
    public int getRowCount() {
        return students.size();
    }
    public int getColumnCount() {
        return 7;
    }
    public String getColumnName(int col) {
        return headers[col];
    }
    public Object getValueAt(int row, int col) {
        Student student = students.get(row);
        switch (col) {
            case 0:
                return student.getStudentId().toString();
            case 1: 
                return student.getName();
            case 2:
                return student.getSurname();
            case 3:
                return student.getMiddleName();
            case 4:
                return student.getGroupNumber();
            case 5:
                return student.getAverageMark();
            default:
                return student.getFamilyMemberSalary();
        }
    }
}
class ContactFrame extends JFrame implements ActionListener {
    private static final String LOAD = "LOAD";
    private static final String ADD = "ADD";
    private static final String EDIT = "EDIT";
    private static final String DELETE = "DELETE";
    private final StudentManager studentManager = new StudentManager();
    private final JTable contactTable = new JTable();
    public ContactFrame() {
        contactTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 0, 5);
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(gridbag);
        btnPanel.add(createButton(gridbag, gbc, "Обновить", LOAD));
        btnPanel.add(createButton(gridbag, gbc, "Добавить", ADD));
        btnPanel.add(createButton(gridbag, gbc, "Исправить", EDIT));
        btnPanel.add(createButton(gridbag, gbc, "Удалить", DELETE));
        JPanel left = new JPanel();
        left.setLayout(new BorderLayout());
        left.add(btnPanel, BorderLayout.NORTH);
        add(left, BorderLayout.WEST);
        add(new JScrollPane(contactTable), BorderLayout.CENTER);
        setBounds(100, 200, 900, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loadStudent();
    }
    private JButton createButton(GridBagLayout gridbag, GridBagConstraints gbc, String title, String action) {
        JButton button = new JButton(title);
        button.setActionCommand(action);
        button.addActionListener(this);
        gridbag.setConstraints(button, gbc);
        return button;
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();
        switch (action) {
            case LOAD:
                loadStudent();
                break;
            case ADD:
                addStudent();
                break;
            case EDIT:
                editStudent();
                break;
            case DELETE:
                deleteStudent();    
                break;
        }
    }
    private void loadStudent() {
        List<Student> students = studentManager.findStudents();
        ContactModel cm = new ContactModel(students);
        contactTable.setModel(cm);
    }
    private void addStudent() {
        EditContactDialog ecd = new EditContactDialog();
        saveStudent(ecd);
    }
    private void editStudent() {
        int sr = contactTable.getSelectedColumn();
        if (sr != -1) {
            Long id = Long.parseLong(contactTable.getModel().getValueAt(sr, 0).toString());
            Student cnt = studentManager.getStudent(id);
            EditContactDialog ecd = new EditContactDialog(studentManager.getStudent(id));
            saveStudent(ecd);
        } else {
            JOptionPane.showMessageDialog(this, "Вы должны выделить строку для редактирования");
        }
    }
    private void deleteStudent() {
        int sr = contactTable.getSelectedColumn();
        if (sr != -1) {
            Long id = Long.parseLong(contactTable.getModel().getValueAt(sr, 0).toString());
            studentManager.deleteStudent(id);
            loadStudent();
        } else {
            JOptionPane.showMessageDialog(this, "Вы должны выделить строку для удаления");
        }
    }
    private void saveStudent(EditContactDialog ecd) {
        if (ecd.isSave()) {
            Student cnt = ecd.getStudent();
            if (cnt.getStudentId() != null) {
                studentManager.updateStudent(cnt);
            } else {
                studentManager.addStudent(cnt);
            }
            loadStudent();
        }
    }
}
class EditContactDialog extends JDialog implements ActionListener {
    private static final String SAVE = "SAVE";
    private static final String CANCEL = "CANCEL";
    private static final int PAD = 10;
    private static final int W_L = 100;
    private static final int W_T = 300;
    private static final int W_B = 120;
    private static final int H_B = 25;
    private final JTextPane txtName = new JTextPane();
    private final JTextPane txtSurname = new JTextPane();
    private final JTextPane txtMiddleName = new JTextPane();
    private final JTextPane txtGroupNumber = new JTextPane();
    private final JTextPane txtAverageMark = new JTextPane();
    private final JTextPane txtFamilyMemberSalary = new JTextPane();
    private Long studentId = null;
    private boolean save = false;
    public EditContactDialog() {
        this(null);
    }
    public EditContactDialog(Student student) {
        setLayout(null);
        buildFields();
        initFields(student);
        buildButtons();
        setModal(true);
        setResizable(false);
        setBounds(300, 300, 450, 200);
        setVisible(true);
    }
    private void buildFields() {
        JLabel lblName = new JLabel("Имя:");
        lblName.setHorizontalAlignment(SwingConstants.RIGHT);
        lblName.setBounds(new Rectangle(PAD, 0 * H_B + PAD, W_L, H_B));
        add(lblName);
        txtName.setBounds(new Rectangle(W_L + 2*PAD, 0 * H_B + PAD, W_T, H_B));
        txtName.setBorder(BorderFactory.createEtchedBorder());
        add(txtName);
        JLabel lblSurname = new JLabel("Фамилия:");
        lblSurname.setHorizontalAlignment(SwingConstants.RIGHT);
        lblSurname.setBounds(new Rectangle(PAD, 1 * H_B + PAD, W_L, H_B));
        add(lblSurname);
        txtSurname.setBounds(new Rectangle(W_L + 2*PAD, 1 * H_B + PAD, W_T, H_B));
        txtSurname.setBorder(BorderFactory.createEtchedBorder());
        add(txtSurname);
        JLabel lblMiddleName = new JLabel("Отчество:");
        lblMiddleName.setHorizontalAlignment(SwingConstants.RIGHT);
        lblMiddleName.setBounds(new Rectangle(PAD, 2 * H_B + PAD, W_L, H_B));
        add(lblMiddleName);
        txtMiddleName.setBounds(new Rectangle(W_L + 2*PAD, 2 * H_B + PAD, W_T, H_B));
        txtMiddleName.setBorder(BorderFactory.createEtchedBorder());
        add(txtMiddleName);
        JLabel lblGroupNumber = new JLabel("Номер группы:");
        lblGroupNumber.setHorizontalAlignment(SwingConstants.RIGHT);
        lblGroupNumber.setBounds(new Rectangle(PAD, 3 * H_B + PAD, W_L, H_B));
        add(lblGroupNumber);
        txtGroupNumber.setBounds(new Rectangle(W_L + 2*PAD, 3 * H_B + PAD, W_T, H_B));
        txtGroupNumber.setBorder(BorderFactory.createEtchedBorder());
        add(txtGroupNumber);
        JLabel lblAverageMark = new JLabel("Средний балл:");
        lblAverageMark.setHorizontalAlignment(SwingConstants.RIGHT);
        lblAverageMark.setBounds(new Rectangle(PAD, 4 * H_B + PAD, W_L, H_B));
        add(lblAverageMark);
        txtAverageMark.setBounds(new Rectangle(W_L + 2*PAD, 4 * H_B + PAD, W_T, H_B));
        txtAverageMark.setBorder(BorderFactory.createEtchedBorder());
        add(txtAverageMark);
        JLabel lblFamilyMemberSalary = new JLabel("Доход на члена семьи:");
        lblFamilyMemberSalary.setHorizontalAlignment(SwingConstants.RIGHT);
        lblFamilyMemberSalary.setBounds(new Rectangle(PAD, 5 * H_B + PAD, W_L, H_B));
        add(lblFamilyMemberSalary);
        txtFamilyMemberSalary.setBounds(new Rectangle(W_L + 2*PAD, 5 * H_B + PAD, W_T, H_B));
        txtFamilyMemberSalary.setBorder(BorderFactory.createEtchedBorder());
        add(txtFamilyMemberSalary);
    }
    private void initFields(Student student) {
        if (student != null) {
            studentId = student.getStudentId();
            txtName.setText(student.getName());
            txtSurname.setText(student.getSurname());
            txtMiddleName.setText(student.getMiddleName());
            txtGroupNumber.setText(student.getGroupNumber().toString());
            txtAverageMark.setText(student.getAverageMark().toString());
            txtFamilyMemberSalary.setText(student.getFamilyMemberSalary().toString());
        }
    }
    private void buildButtons() {
        JButton btnSave = new JButton("SAVE");
        btnSave.setActionCommand(SAVE);
        btnSave.addActionListener(this);
        btnSave.setBounds(new Rectangle(PAD, 7 * H_B + PAD, W_B, H_B));
        add(btnSave);
        JButton btnCancel = new JButton("CANCEL");
        btnCancel.setActionCommand(CANCEL);
        btnCancel.addActionListener(this);
        btnCancel.setBounds(new Rectangle(W_B + 2*PAD, 7 * H_B + PAD, W_B, H_B));
        add(btnCancel);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();
        save = SAVE.equals(action);
        setVisible(false);
    }
    public boolean isSave() {
        return save;
    }
    public Student getStudent() {
        Student student = new Student(studentId, txtName.getText(), txtSurname.getText(), txtMiddleName.getText(), new Integer (txtGroupNumber.getText()), new Float (txtAverage-Mark.getText()), new Float (txtFamilyMemberSalary.getText()));
        return student;
    }
}
public class Main {
    public static void main(String[] args) {
        ContactFrame cf = new ContactFrame();
        cf.setVisible(true);
    }
}