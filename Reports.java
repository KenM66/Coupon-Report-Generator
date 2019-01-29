import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.print.PrinterException;
import java.text.MessageFormat;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.SwingConstants;

public class Reports extends JPanel{
	
	JScrollPane listPane;
	JScrollPane packageListPane;
	JScrollPane honorsListPane;
	static JTable corporateTable;
	private JButton printButton;
	DefaultTableModel modelCorporate;
	private JButton printButtonPackage;
	private JLabel corporateLabel;
	private JLabel honorsLabel;
	private JButton printButtonHonors;
	static JTable honorsTable;
	private DefaultTableModel modelHonors;
	
	Reports(){
		
		String columnNamesCorporate []= {"Room #", "Name", "Company", "SRP Code", "Server"};
		
		setBorder( new EmptyBorder(20, 20, 20, 20) );
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(Box.createVerticalStrut(20));
		
		 modelCorporate= new DefaultTableModel( columnNamesCorporate,0);
		
		corporateLabel = new JLabel("                  Corporate");
		corporateLabel.setAlignmentX(0.5f);
		add(corporateLabel);
		corporateTable= new JTable(modelCorporate);
		listPane= new JScrollPane(corporateTable);
		listPane.setPreferredSize(new Dimension(600,100));
			 add(listPane);
			 
			 corporateTable.getColumnModel().getColumn(0).setPreferredWidth(40);
			 corporateTable.getColumnModel().getColumn(1).setPreferredWidth(200);
			 corporateTable.getColumnModel().getColumn(2).setPreferredWidth(130);
			 
			 RowSorter<TableModel> sorter= new TableRowSorter<TableModel>(modelCorporate);
			 
			 corporateTable.setRowSorter(sorter);
			 
			 initCorporateModel(modelCorporate);
			 
	     add(Box.createVerticalStrut(20));
	     
	     printButton= new JButton("PRINT");
	     printButton.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent e) {
	     		try {
					printTable(corporateTable, 0);
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	     	}
	     });
	     add(printButton);
	     
	     add(Box.createVerticalStrut(20));
	     
	     String columnNamesPackage[]= {"Room #", "Name", "#A","#C", "Package", "Description", "Server"};
	     
	     JLabel packageLabel= new JLabel("Meal Package");
	     add(packageLabel);
	     
	     DefaultTableModel modelPackage= new DefaultTableModel(columnNamesPackage, 0);
	     JTable packageTable= new JTable(modelPackage);
	     packageListPane= new JScrollPane(packageTable);
	     RowSorter<TableModel> sorter1= new TableRowSorter<TableModel>(modelPackage);
	     packageTable.setRowSorter(sorter1);
	     
	     add(packageListPane);
	     packageListPane.setPreferredSize(new Dimension(650,100));
	     
	     packageTable.getColumnModel().getColumn(0).setMaxWidth(50);
		 packageTable.getColumnModel().getColumn(1).setPreferredWidth(40);
		 packageTable.getColumnModel().getColumn(2).setMaxWidth(25);
		 packageTable.getColumnModel().getColumn(3).setMaxWidth(25);
		
		 packageTable.getColumnModel().getColumn(5).setPreferredWidth(80);
		 packageTable.getColumnModel().getColumn(6).setMaxWidth(50);
	     
	     initPackageModel(modelPackage);
	     
	     add(Box.createVerticalStrut(20));
	     
	     printButtonPackage= new JButton("PRINT");
	     printButtonPackage.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent e) {
	     		try {
					printTable(packageTable, 2);
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	     	}
	     });
	     add(printButtonPackage);
	     
	     honorsLabel= new JLabel("Hilton Honors");
	     add(honorsLabel);
	     
	     String columnNameHonors []= {"Room #", "Name", "HH Tier", "MyWay Selection", "Server"};
	     
	     modelHonors= new DefaultTableModel(columnNameHonors,0);
	     honorsTable= new JTable(modelHonors);
	     
	     honorsTable.getColumnModel().getColumn(0).setMaxWidth(50);
	     honorsTable.getColumnModel().getColumn(2).setMaxWidth(50);
	     honorsTable.getColumnModel().getColumn(4).setMaxWidth(50);
	     
	     printButtonHonors= new JButton("PRINT");
	     printButtonHonors.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		try {
					printTable(honorsTable, 1);
				} catch (PrinterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	     	}
	     });
	     
	     honorsListPane= new JScrollPane(honorsTable);
	     honorsListPane.setPreferredSize(new Dimension(600,100));
	     RowSorter<TableModel> sorter2= new TableRowSorter<TableModel>(modelHonors);
	     honorsTable.setRowSorter(sorter2);
	     
	     add(honorsListPane);
	     add(printButtonHonors);
	     
	     initHonorsModel(modelHonors);
	     
	     
	 
			
	
	}
	
	public static void createAndShowTable() throws PrinterException{
		JFrame frame= new JFrame("Corporate");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().add(new Reports());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	
	public void initCorporateModel(DefaultTableModel model) {
		
		
		for(Guest g: FrontPage.guestList) {
			if(FrontPage.ratePlanList.contains(g.getRatePlan())) {
				model.addRow(new Object[] {g.getRoomNumber(), g.getLastName()+", "+g.getFirstName()
				, g.getCompany(), g.getRatePlan() });
			}
		}
		
	   
		
	}
public void initHonorsModel(DefaultTableModel model) {
        for(Guest g: FrontPage.honorsGuestList) {
        	if((FrontPage.ratePlanList.contains(g.getRatePlan()))||FrontPage.packageRateList.contains(g.getRatePlan())) {
        		if(FrontPage.includeOnBoth==false) {}
        			 
        		else {
        			model.addRow(new Object[] {g.getRoomNumber(), g.getLastName()+", "+g.getFirstName()
        			,g.getHonorsTier(), g.getMyWaySelection()
        			});
        		}
        	}
        	else {
        		model.addRow(new Object[] {g.getRoomNumber(), g.getLastName()+", "+g.getFirstName()
    			,g.getHonorsTier(), g.getMyWaySelection()
    			});
        	}
        }
	
	
	}
		
	
	private static void printTable(JTable table, int accountIndex) throws PrinterException {
		String accountName= FrontPage.accountNameList.get(accountIndex);
		String accountNumber= FrontPage.accountNumList.get(accountIndex);
		MessageFormat headerFormat = new MessageFormat(accountName+": "+accountNumber);
        MessageFormat footerFormat = new MessageFormat("- {0} -");
        table.print(JTable.PrintMode.NORMAL, headerFormat, footerFormat);
		
	}
	
	public void initPackageModel(DefaultTableModel model) {
		for(Guest g: FrontPage.guestList) {
			if(FrontPage.packageRateList.contains(g.getRatePlan())) {
				model.addRow(new Object[]{g.getRoomNumber(), g.getLastName()+", "+g.getFirstName().charAt(0)
				, g.getNumAdults(), g.getNumKids() ,g.getBreakfastPackageName(), g.getBreakfastPackageDescription()});
			}
		}
	}
}
