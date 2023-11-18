import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LibraryManagementSystem {
    private static int adminIdCounter = 1;
    private static int userIdCounter = 1;
    private static int bookIdCounter = 1;

    private static ArrayList<AdminRecord> adminRecords = new ArrayList<>();
    private static ArrayList<UserRecord> userRecords = new ArrayList<>();
    private static ArrayList<BookRecord> bookRecords = new ArrayList<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Library Management System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            frame.setContentPane(new LoginPanel(frame));

            frame.setVisible(true);
        });
    }

    public static int generateAdminId() {
        return adminIdCounter++;
    }

    public static int generateUserId() {
        return userIdCounter++;
    }

    public static int generateBookId() {
        return bookIdCounter++;
    }

    public static void addAdminRecord(String name) {
        int adminId = generateAdminId();
        AdminRecord adminRecord = new AdminRecord(adminId, name);
        adminRecords.add(adminRecord);
    }

    public static ArrayList<AdminRecord> getAllAdminRecords() {
        return adminRecords;
    }

    public static void deleteAdminRecord(int adminId) {
        adminRecords.removeIf(record -> record.getAdminId() == adminId);
    }

    public static void addUserRecord(String name) {
        int userId = generateUserId();
        UserRecord userRecord = new UserRecord(userId, name);
        userRecords.add(userRecord);
    }

    public static ArrayList<UserRecord> getAllUserRecords() {
        return userRecords;
    }

    public static void deleteUserRecord(int userId) {
        userRecords.removeIf(record -> record.getUserId() == userId);
    }

    public static void addBookRecord(String name) {
        int bookId = generateBookId();
        BookRecord bookRecord = new BookRecord(bookId, name);
        bookRecords.add(bookRecord);
    }

    public static ArrayList<BookRecord> getAllBookRecords() {
        return bookRecords;
    }

    public static void deleteBookRecord(int bookId) {
        bookRecords.removeIf(record -> record.getBookId() == bookId);
    }
}

class AdminRecord {
    private int adminId;
    private String adminName;

    public AdminRecord(int adminId, String adminName) {
        this.adminId = adminId;
        this.adminName = adminName;
    }

    public int getAdminId() {
        return adminId;
    }

    public String getAdminName() {
        return adminName;
    }
}

class UserRecord {
    private int userId;
    private String userName;

    public UserRecord(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }
}

class BookRecord {
    private int bookId;
    private String bookName;

    public BookRecord(int bookId, String bookName) {
        this.bookId = bookId;
        this.bookName = bookName;
    }

    public int getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }
}

class LoginPanel extends JPanel {
    public LoginPanel(JFrame frame) {
        JButton adminButton = new JButton("Admin Login");
        JButton userButton = new JButton("User Login");

        adminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new AdminModule(frame));
                frame.revalidate();
            }
        });

        userButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new UserModule(frame));
                frame.revalidate();
            }
        });

        add(new JLabel("Select User Type:"));
        add(adminButton);
        add(userButton);
    }
}

class AdminModule extends JPanel {
    private JTextField adminNameField;
    private JTextField userNameField;
    private JTextField bookNameField;
    private JTextArea adminRecordsArea;

    public AdminModule(JFrame frame) {
        JButton addAdminButton = new JButton("Add Admin Record");
        JButton viewAdminRecordsButton = new JButton("View Admin Records");
        JButton deleteAdminRecordButton = new JButton("Delete Admin Record");
        JButton addUserButton = new JButton("Add User");
        JButton viewUsersButton = new JButton("View Users");
        JButton deleteUserButton = new JButton("Delete User");
        JButton addBookButton = new JButton("Add Book");
        JButton viewBooksButton = new JButton("View Books");
        JButton deleteBookButton = new JButton("Delete Book");
        JButton backButton = new JButton("Back");

        adminNameField = new JTextField(20);
        userNameField = new JTextField(20);
        bookNameField = new JTextField(20);
        adminRecordsArea = new JTextArea(10, 30);

        addAdminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String adminName = adminNameField.getText();
                LibraryManagementSystem.addAdminRecord(adminName);
                JOptionPane.showMessageDialog(frame, "Admin Record Added:\nName: " + adminName);
            }
        });

        viewAdminRecordsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<AdminRecord> adminRecords = LibraryManagementSystem.getAllAdminRecords();
                StringBuilder recordsText = new StringBuilder("Admin Records:\n");
                for (AdminRecord record : adminRecords) {
                    recordsText.append("ID: ").append(record.getAdminId()).append(", Name: ").append(record.getAdminName()).append("\n");
                }
                adminRecordsArea.setText(recordsText.toString());
            }
        });

        deleteAdminRecordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int adminId = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter Admin ID to delete:"));
                    LibraryManagementSystem.deleteAdminRecord(adminId);
                    JOptionPane.showMessageDialog(frame, "Admin Record Deleted:\nID: " + adminId);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid Admin ID.");
                }
            }
        });

        addUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userName = userNameField.getText();
                LibraryManagementSystem.addUserRecord(userName);
                JOptionPane.showMessageDialog(frame, "User Record Added:\nName: " + userName);
            }
        });

        viewUsersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<UserRecord> userRecords = LibraryManagementSystem.getAllUserRecords();
                StringBuilder recordsText = new StringBuilder("User Records:\n");
                for (UserRecord record : userRecords) {
                    recordsText.append("ID: ").append(record.getUserId()).append(", Name: ").append(record.getUserName()).append("\n");
                }
                adminRecordsArea.setText(recordsText.toString());
            }
        });

        deleteUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int userId = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter User ID to delete:"));
                    LibraryManagementSystem.deleteUserRecord(userId);
                    JOptionPane.showMessageDialog(frame, "User Record Deleted:\nID: " + userId);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid User ID.");
                }
            }
        });

        addBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String bookName = bookNameField.getText();
                LibraryManagementSystem.addBookRecord(bookName);
                JOptionPane.showMessageDialog(frame, "Book Record Added:\nName: " + bookName);
            }
        });

        viewBooksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<BookRecord> bookRecords = LibraryManagementSystem.getAllBookRecords();
                StringBuilder recordsText = new StringBuilder("Book Records:\n");
                for (BookRecord record : bookRecords) {
                    recordsText.append("ID: ").append(record.getBookId()).append(", Name: ").append(record.getBookName()).append("\n");
                }
                adminRecordsArea.setText(recordsText.toString());
            }
        });

        deleteBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int bookId = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter Book ID to delete:"));
                    LibraryManagementSystem.deleteBookRecord(bookId);
                    JOptionPane.showMessageDialog(frame, "Book Record Deleted:\nID: " + bookId);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid Book ID.");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new LoginPanel(frame));
                frame.revalidate();
            }
        });

        add(new JLabel("Admin Module"));
        add(new JLabel("Admin Name:"));
        add(adminNameField);
        add(addAdminButton);
        add(viewAdminRecordsButton);
        add(deleteAdminRecordButton);
        add(new JLabel("User Name:"));
        add(userNameField);
        add(addUserButton);
        add(viewUsersButton);
        add(deleteUserButton);
        add(new JLabel("Book Name:"));
        add(bookNameField);
        add(addBookButton);
        add(viewBooksButton);
        add(deleteBookButton);
        add(adminRecordsArea);
        add(backButton);
    }
}

class UserModule extends JPanel {
    private JTextField userNameField;
    private JTextArea userRecordsArea;

    public UserModule(JFrame frame) {
        JButton browseButton = new JButton("Browse Books");
        JButton searchButton = new JButton("Search Book");
        JButton viewUserRecordsButton = new JButton("View User Records");
        JButton backButton = new JButton("Back");

        userNameField = new JTextField(20);
        userRecordsArea = new JTextArea(10, 30);

        browseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<BookRecord> bookRecords = LibraryManagementSystem.getAllBookRecords();
                StringBuilder recordsText = new StringBuilder("Available Books:\n");
                for (BookRecord record : bookRecords) {
                    recordsText.append("ID: ").append(record.getBookId()).append(", Name: ").append(record.getBookName()).append("\n");
                }
                userRecordsArea.setText(recordsText.toString());
            }
        });

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int bookId = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter Book ID to search:"));
                    BookRecord foundBook = findBookById(bookId);
                    if (foundBook != null) {
                        JOptionPane.showMessageDialog(frame, "Book Found:\nID: " + foundBook.getBookId() + ", Name: " + foundBook.getBookName());
                    } else {
                        JOptionPane.showMessageDialog(frame, "Book not found. Invalid Book ID.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid Book ID.");
                }
            }
        });

        viewUserRecordsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<UserRecord> userRecords = LibraryManagementSystem.getAllUserRecords();
                StringBuilder recordsText = new StringBuilder("User Records:\n");
                for (UserRecord record : userRecords) {
                    recordsText.append("ID: ").append(record.getUserId()).append(", Name: ").append(record.getUserName()).append("\n");
                }
                userRecordsArea.setText(recordsText.toString());
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new LoginPanel(frame));
                frame.revalidate();
            }
        });

        add(new JLabel("User Module"));
        add(new JLabel("User Name:"));
        add(userNameField);
        add(browseButton);
        add(searchButton);
        add(viewUserRecordsButton);
        add(userRecordsArea);
        add(backButton);
    }

    private BookRecord findBookById(int bookId) {
        ArrayList<BookRecord> bookRecords = LibraryManagementSystem.getAllBookRecords();
        for (BookRecord record : bookRecords) {
            if (record.getBookId() == bookId) {
                return record;
            }
        }
        return null;
    }
}