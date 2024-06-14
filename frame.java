// Import necessary packages
package assign;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.ArrayList;

// Define a class to represent a bus record
class BusRecord {
    String busNumber; // Store bus number
    String routeName; // Store route name
    String inTime; // Store time of arrival
    String outTime; // Store time of departure

    // Constructor to initialize a bus record
    BusRecord(String busNumber, String routeName, String inTime, String outTime) {
        this.busNumber = busNumber;
        this.routeName = routeName;
        this.inTime = inTime;
        this.outTime = outTime;
    }

    // Method to convert bus record to string representation
    
    public String toString() {
        return "BusRecord(busNumber=" + busNumber + ", routeName=" + routeName + ", inTime=" + inTime + ", outTime=" + outTime + ")";
    }
}

// Main class for the frame
public class frame extends JFrame implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image backgroundImage; // Background image for the frame
    private JTextField usernameField; // Text field for username input
    private JPasswordField passwordField; // Password field for password input
    private JPanel mainPanel; // Main panel to hold all components
    private JPanel actionPanel; // Panel for bus management actions
    private ArrayList<BusRecord> busRecords; // List to store bus records
    private JButton manageButton; // Button for managing buses

    // Constructor to initialize the frame
    frame() {
        setTitle("BUS MANAGEMENT"); // Set title of the frame
        setLayout(new BorderLayout()); // Set layout for the frame

        // Load the background image
        try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\Admin\\OneDrive\\Desktop\\WhatsApp Image 2024-05-18 at 18.18.07_54c1b44d.jpg")); // Provide the path to your image file
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create a main panel for holding all components
        mainPanel = new JPanel() {
            
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        mainPanel.setLayout(null); // Set layout to null for manual component placement

        // Create a label for the heading
        JLabel headingLabel = new JLabel("BUS MANAGEMENT", JLabel.CENTER);
        headingLabel.setFont(new Font("Arial", Font.BOLD, 55)); // Set font for the label
        headingLabel.setForeground(Color.BLACK); // Set text color
        headingLabel.setBackground(Color.PINK); // Set background color
        headingLabel.setBounds(450, 50, 600, 100); // Set bounds for positioning

        // Create a sign-in panel
        JPanel signInPanel = new JPanel();
        signInPanel.setLayout(null); // Set layout to null for manual component placement
        signInPanel.setBounds(470, 240, 420, 230); // Set bounds for positioning
        signInPanel.setBackground(new Color(0, 0, 0, 100)); // Semi-transparent black background
        signInPanel.setOpaque(false); // Make panel transparent

        JLabel signInLabel = new JLabel("SIGN IN", JLabel.CENTER);
        signInLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Set font for the label
        signInLabel.setForeground(Color.BLACK); // Set text color
        signInLabel.setBounds(0, 0, 420, 50); // Set bounds for positioning

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Set font for the label
        usernameLabel.setForeground(Color.BLACK); // Set text color
        usernameLabel.setBounds(20, 60, 100, 30); // Set bounds for positioning

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Set font for the label
        passwordLabel.setForeground(Color.BLACK); // Set text color
        passwordLabel.setBounds(20, 110, 100, 30); // Set bounds for positioning

        usernameField = new JTextField(); // Text field for username input
        usernameField.setBounds(140, 60, 250, 30); // Set bounds for positioning

        passwordField = new JPasswordField(); // Password field for password input
        passwordField.setBounds(140, 110, 250, 30); // Set bounds for positioning

        JButton loginButton = new JButton("Login"); // Button for login
        loginButton.setFont(new Font("Arial", Font.BOLD, 18)); // Set font for the button
        loginButton.setBackground(Color.BLUE); // Set background color
        loginButton.setForeground(Color.PINK); // Set text color
        loginButton.setBounds(140, 160, 100, 40); // Set bounds for positioning
        loginButton.addActionListener(this); // Add action listener

        signInPanel.add(signInLabel); // Add sign-in label to sign-in panel
        signInPanel.add(usernameLabel); // Add username label to sign-in panel
        signInPanel.add(passwordLabel); // Add password label to sign-in panel
        signInPanel.add(usernameField); // Add username text field to sign-in panel
        signInPanel.add(passwordField); // Add password field to sign-in panel
        signInPanel.add(loginButton); // Add login button to sign-in panel

        // Create a button for managing buses
        manageButton = new JButton("ManageBus");
        manageButton.setFont(new Font("Arial", Font.BOLD, 18)); // Set font for the button
        manageButton.setBackground(Color.GREEN); // Set background color
        manageButton.setForeground(Color.BLACK); // Set text color
        manageButton.setBounds(710, 430, 150, 40); // Set bounds for positioning
        manageButton.addActionListener(this); // Add action listener
        manageButton.setEnabled(false); // Disable the button initially

        // Add components to the main panel
        mainPanel.add(headingLabel); // Add heading label to main panel
        mainPanel.add(signInPanel); // Add sign-in panel to main panel
        mainPanel.add(manageButton); // Add manage button to main panel

        // Add the main panel to the frame
        add(mainPanel);

        // Initialize bus records
        busRecords = new ArrayList<>();
        busRecords.add(new BusRecord("1", "MADURAI", "08:00", "16:00"));
        busRecords.add(new BusRecord("2", "CHENNAI", "09:00", "17:00"));
        busRecords.add(new BusRecord("3", "COIMBATORE", "10:00", "18:00"));
        busRecords.add(new BusRecord("4", "BANGALORE", "11:00", "19:00"));
        busRecords.add(new BusRecord("5", "ERODE", "12:00", "20:00"));

        // Set frame properties
        setSize(1400, 800); // Set size of the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation
        setVisible(true); // Make the frame visible
    }

    // ActionListener interface method for handling button clicks
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == manageButton) {
            manageBusRecords(); // Call method to manage bus records
        } else if (e.getActionCommand().equals("Login")) {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (username.equals("admin") && password.equals("itb")) {
                JOptionPane.showMessageDialog(this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                manageButton.setEnabled(true); // Enable manage button after successful login
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Method to manage bus records
    private void manageBusRecords() {
        // Create a new frame for displaying bus actions
        JFrame actionFrame = new JFrame("Bus Actions");
        actionFrame.setSize(600, 400);
        actionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        actionFrame.setLayout(new BorderLayout());

        // Create a panel for displaying bus actions
        actionPanel = new JPanel() {
            
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.Y_AXIS));

        // Add buttons for bus actions
        JButton addButton = new JButton("Add Bus");
        JButton deleteButton = new JButton("Delete Bus");
        JButton updateButton = new JButton("Update Bus");
        JButton displayButton = new JButton("Display Buses");

        // Align buttons horizontally at the center
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        updateButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        displayButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add action listeners to buttons
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addBusRecord(); // Call method to add bus record
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteBusRecord(); // Call method to delete bus record
            }
        });

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateBusRecord(); // Call method to update bus record
            }
        });

        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayBusRecords(); // Call method to display bus records
            }
        });

        // Add buttons to the action panel
        actionPanel.add(addButton);
        actionPanel.add(deleteButton);
        actionPanel.add(updateButton);
        actionPanel.add(displayButton);

        actionFrame.add(actionPanel, BorderLayout.CENTER); // Add action panel to the frame
        actionFrame.setVisible(true); // Make the frame visible
    }

    // Method to add a new bus record
    private void addBusRecord() {
        String busNumber = JOptionPane.showInputDialog(this, "Enter Bus Number:");
        String routeName = JOptionPane.showInputDialog(this, "Enter Route Name:");
        String inTime = JOptionPane.showInputDialog(this, "Enter In Time:");
        String outTime = JOptionPane.showInputDialog(this, "Enter Out Time:");

        BusRecord newBusRecord = new BusRecord(busNumber, routeName, inTime, outTime);

        // Prompt the user to choose whether to add the new record at the beginning or the end
        int option = JOptionPane.showOptionDialog(this, "Add the new bus record at the beginning or the end?", "Add Bus Record", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Beginning", "End"}, "End");

        if (option == JOptionPane.YES_OPTION) {
            busRecords.add(0, newBusRecord); // Add at the beginning
        } else {
            busRecords.add(newBusRecord); // Add at the end
        }

        JOptionPane.showMessageDialog(this, "Bus added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    // Method to delete a bus record
    private void deleteBusRecord() {
        String busNumber = JOptionPane.showInputDialog(this, "Enter Bus Number to Delete:");
        String routeName = JOptionPane.showInputDialog(this, "Enter Route Name:");

        for (int i = 0; i < busRecords.size(); i++) {
            if (busRecords.get(i).busNumber.equals(busNumber)) {
                busRecords.remove(i);
                JOptionPane.showMessageDialog(this, "Bus deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "Bus not found!", "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Method to update a bus record
    private void updateBusRecord() {
        String busNumber = JOptionPane.showInputDialog(this, "Enter Bus Number to Update:");

        for (BusRecord record : busRecords) {
            if (record.busNumber.equals(busNumber)) {
                // Prompt the user to choose which field to update
                String[] options = {"Bus Number", "Route Name", "In Time", "Out Time"};
                int option = JOptionPane.showOptionDialog(this, "Choose which field to update", "Update Bus Record", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

                String newValue = JOptionPane.showInputDialog(this, "Enter New Value:");

                switch (option) {
                    case 0:
                        record.busNumber = newValue; // Update bus number
                        break;
                    case 1:
                        record.routeName = newValue; // Update route name
                        break;
                    case 2:
                        record.inTime = newValue; // Update time of arrival
                        break;
                    case 3:
                        record.outTime = newValue; // Update time of departure
                        break;
                    default:
                        break;
                }

                JOptionPane.showMessageDialog(this, "Bus updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "Bus not found!", "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Method to display all bus records
    private void displayBusRecords() {
        // Display the bus records
        StringBuilder records = new StringBuilder("Bus Records:\n");
        for (BusRecord busRecord : busRecords) {
            records.append(busRecord.toString()).append("\n");
        }

        JOptionPane.showMessageDialog(this, records.toString(), "Bus Records", JOptionPane.INFORMATION_MESSAGE);
    }

    // Main method to create an instance of the frame
    public static void main(String[] args) {
        frame f = new frame(); // Create an instance of the frame
    }
}
