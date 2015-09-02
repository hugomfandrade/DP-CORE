package gui;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import javax.swing.*;
import parser.ClassObject;
import parser.ClassObject.Abstraction;
import parser.Connection;
import patterns.Pattern;

public class mainWindow extends JFrame {
	
	public static String patternfolder, projectfolder, exportfolder;
	private File folder;
	private JButton detect, create;
	public static JCheckBox grouping;
	public static JComboBox<String> cb;
	private static JPanel panel;
	public static Pattern p = new Pattern("");
	public static boolean parseoccured = false;
	
	public static int MemberNum, ConnNum;
	public static String PatternName;
	
	//Constructor
	public mainWindow(){
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLayout(null);

		detect = new JButton(new ShowWaitAction2("Detect Pattern"));
		detect.setToolTipText("Detects Pattern candidates of the chosen pattern type.");
		detect.setSize(150, 25);
		detect.setLocation(30, 150);
		add(detect);
			
		create = new JButton("Create Custom Pattern");
		create.setToolTipText("Tool for creating custom patterns.");
		create.setSize(150, 25);
		create.setLocation(250, 150);
		add(create);
		
		grouping = new JCheckBox("Grouping");
		grouping.setToolTipText("Check to group final results into HyperCandidates.");
		grouping.setSize(100, 25);
		grouping.setLocation(30, 185);
		add(grouping);
		
		panel = new JPanel();
		panel.setSize(180, 25);
		panel.setLocation(250, 185);
		panel.setLayout(null);
		add(panel);
		String[] choices = {"Choose a Pattern"};		
		cb = new JComboBox<String>(choices);
		cb.setSize(180, 25);
		cb.setLocation(0, 0);
		cb.setEditable(true);
		cb.getEditor().getEditorComponent().setFocusable(false); 
		cb.setVisible(true);
		panel.add(cb);
		
		JPanel panel1 = new JPanel();
		panel1.setSize(450, 35);
		panel1.setLocation(0, 10);
		add(panel1);
		JPanel panel2 = new JPanel();
		panel2.setSize(450, 35);
		panel2.setLocation(0, 45);
		add(panel2);
		JPanel panel3 = new JPanel();
		panel3.setSize(450, 35);
		panel3.setLocation(0, 80);
		add(panel3);
        // set up file picker components
        JFilePicker patternPicker = new JFilePicker("Pick Pattern Folder", "Browse...", JFilePicker.type.Pattern);
        patternPicker.setMode(JFilePicker.MODE_SAVE);	
        panel1.add(patternPicker);
        
        JFilePicker projectPicker = new JFilePicker("Pick Project Folder", "Browse...", JFilePicker.type.Project);
        projectPicker.setMode(JFilePicker.MODE_SAVE);
        panel2.add(projectPicker);	
        
        JFilePicker exportPicker = new JFilePicker("Pick Results Folder", "Browse...", JFilePicker.type.Export);
        exportPicker.setMode(JFilePicker.MODE_SAVE);
        panel3.add(exportPicker);	            
			
		event3 e3 = new event3();
		create.addActionListener(e3);
		
		addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent windowEvent) {
		        int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to close this window?", "Really Closing?", JOptionPane.YES_NO_OPTION);
		        if(reply == JOptionPane.YES_OPTION){
		            System.exit(0);
		        }
		    }
		});
		
	}
		
	/**
	 *  Pattern Creator Event
	 * 
	 * @author Tony
	 */
	public class event3 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(patternfolder==null){
				JOptionPane.showMessageDialog(new JFrame(), "Pattern Folder Location Undefined!", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			else{
				p.clear();
				new NumbersWindow();
				folder = new File(patternfolder);
				refresh(folder);
			}
		}
	}
	
	public static void main(String args[]){
        // Set System Java L&F 
        try {
			UIManager.setLookAndFeel(
			    UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}	
		mainWindow gui = new mainWindow();
		gui.setSize(470, 260);
		gui.setResizable(false);
		gui.setLocationRelativeTo(null);
		gui.setVisible(true);
		gui.setTitle("DPDHMMY");
	}
	/**
	 * Parses a .pattern file to create a Pattern Object.
	 * 
	 * @param file file to be parsed
	 * @return Returns a Pattern Object created through parsing
	 */
	public static Pattern extractPattern(File file){
		String name = file.getName();
		Pattern p;
		int phase = 1;
		if (name.substring(name.lastIndexOf(".")).equalsIgnoreCase(".pattern")){
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				String line;
				p = new Pattern(br.readLine());
				while ((line = br.readLine()) != null) {
					// process the line.
					if(phase == 1 && !(line.equals("End_Members"))){
						String[] parts = line.split(" ");
						String s = "";
						for(int i = 2 ; i < parts.length ; i++ ){
							s += parts[i];
							if(!(i==parts.length-1)){
								s += " ";
							}
						}
						p.insert_member(parts[0], StringtoAbstraction(parts[1]), s);
					}
					else if(line.equals("End_Members")){
						phase = 2;
					}
					else if(phase == 2 && !(line.equals("End_Connections"))){
						String[] parts = line.split(" ");
						p.insert_connection(parts[0], StringtoConnectionType(parts[1]), parts[2]);
					}
					else{}
				}
				return p;
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		else{
			JOptionPane.showMessageDialog(new JFrame(), "Invalid File Type for Pattern.", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}
	
	/**
	 *  Used to refresh the Dropdown menu of available patterns.
	 *  
	 * @param f folder containing the .pattern files
	 */
	public static void refresh(File f){
		ArrayList<String> Patterns = listPatternFilesForFolder(f);
		if (Patterns==null||Patterns.size()==0){
			JOptionPane.showMessageDialog(new JFrame(), "Current Folder contains no pattern files.", "No Pattern Files", JOptionPane.INFORMATION_MESSAGE);
			String choices[] = new String[1];
			choices[0] = "Choose a Pattern";
			cb.setVisible(false);
			cb = new JComboBox<String>(choices);
			cb.setSize(180, 25);
			cb.setLocation(0, 0);
			cb.setEditable(true);
			cb.getEditor().getEditorComponent().setFocusable(false); 
			cb.setVisible(true);
			panel.add(cb);
		}else{
			String choices[] = new String[Patterns.size()+1];
			choices[0] = "Choose a Pattern";
			for(int i=1 ; i<Patterns.size()+1 ; i++){
				choices[i] = Patterns.get(i-1);
			}
			cb.setVisible(false);
			cb = new JComboBox<String>(choices);
			cb.setSize(180, 25);
			cb.setLocation(0, 0);
			cb.setEditable(true);
			cb.getEditor().getEditorComponent().setFocusable(false); 
			cb.setVisible(true);
			panel.add(cb);		
		}
	}

	/**
	 * Converts the input string to the equivalent Connection Type
	 * 
	 * @param string String representing a Connection Type
	 * @return Returns a Connection Type after processing input String
	 */
	public static Connection.Type StringtoConnectionType(String string) {
		Connection.Type t;
		switch (string) {
		case "uses":
			t = Connection.Type.use;
			break;
		case "inherits":
			t = Connection.Type.inh;
			break;
		case "creates":
			t = Connection.Type.create;		
			break;
			// Either interface or abstract
		case "calls":
			t = Connection.Type.call;
			break;
		case "references":
			t = Connection.Type.ref;
			break;
		case "has":
			t = Connection.Type.has;
			break;
		default:
			t = null;
		}
		return t;
	}
	
	/**
	 * Converts the input string to the equivalent Abstraction Type
	 * 
	 * @param string String representing an Abstraction Type
	 * @return Returns an Abstraction Type after processing the input String
	 */
	public static Abstraction StringtoAbstraction(String string) {
		Abstraction abs;
		switch (string) {
		case "Normal":
				abs = Abstraction.Normal;
				break;
		case "Interface":
				abs = Abstraction.Interface;
				break;
		case "Abstract":
				abs = Abstraction.Abstract;
				break;
			// Either interface or abstract
		case "Abstracted":
				abs = Abstraction.Abstracted;
				break;
		case "Any":
			abs = Abstraction.Any;
			break;
		default:
			abs = null;
		}
		return abs;
	}
	
	/**
	 *  Constructs a .pattern file out of a Pattern Object
	 *  
	 * @param p Input pattern object to export into a .pattern file
	 * @param file Input file containing the location of the file to be created
	 */
	public static void createPatternFile(Pattern p, File file){
		try {
			PrintWriter writer;
			writer = new PrintWriter(file.getAbsolutePath(), "UTF-8");
			writer.println(p.get_name());
			for(ClassObject cb: p.get_Members()){
				writer.println( cb.getName()+" "+cb.get_abstraction()+" "+cb.getAbility());
			}
			writer.println("End_Members");
			for(Connection c : p.get_Connections()){
				writer.println(c.get_From().getName()+" "+c.getType() +" "+c.get_To().getName());
			}
			writer.println("End_Connections");
			writer.close();
		} catch (FileNotFoundException e1) {
			System.out.println("File Not Found");
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			System.out.println("Unsupported");
			e1.printStackTrace();
		}	
	}
	
	/**
	 * Constructs a File at the input File's location, containing the input String.
	 * 
	 * @param s Input String to be written to the file
	 * @param file Input file containing the location of the file to be created
	 */
	public static void createFile(String s, File file){
		try {
			PrintWriter writer;
			writer = new PrintWriter(file.getAbsolutePath(), "UTF-8");
			writer.print(s);
			writer.close();
		} catch (FileNotFoundException e1) {
			System.out.println("File Not Found");
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			System.out.println("Unsupported");
			e1.printStackTrace();
		}	
	}

	/**
	 *  Takes the path of a folder and returns a list of the .pattern files in it
	 * 
	 * @param folder File containing the path of the folder to be processed
	 * @return Returns a String ArrayList containing .pattern files in the input folder (returns null if none exist)
	 */
	public static ArrayList<String> listPatternFilesForFolder(File folder) {
		if (folder.listFiles()==null){
		}
		else{
			ArrayList<String> files = new ArrayList<String>();
			for (final File fileEntry : folder.listFiles()) {
				if (fileEntry.isDirectory()) {
				} else {
					if(fileEntry.getName().contains(".")){
						if(fileEntry.getName().substring(fileEntry.getName().lastIndexOf(".")).equalsIgnoreCase(".pattern"))
							files.add(fileEntry.getName().substring(0, fileEntry.getName().lastIndexOf(".")));
					}
				}
			}
			return files;
		}
		return null;
	}
	
	/**
	 * Converts the input String to the corresponding Integer (range 1-10)
	 * 
	 * @param s Input String representing an Integer
	 * @return Returns an Integer created after processing the input String
	 */
	public static int StringtoInt1to10(String s){
		int result;
		switch (s){
			case "1":
				result = 1;
				break;
			case "2":
				result = 2;
				break;
			case "3":
				result = 3;
				break;
			case "4":
				result = 4;
				break;
			case "5":
				result = 5;
				break;
			case "6":
				result = 6;
				break;
			case "7":
				result = 7;
				break;
			case "8":
				result = 8;
				break;	
			case "9":
				result = 9;
				break;
			case "10":
				result = 10;
				break;	
			default:
				result = 0;
		}
		return result;
	}
}
