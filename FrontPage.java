import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.event.ActionEvent;



public class FrontPage extends JPanel{
	
	static ArrayList<Integer> cellNumberList= new ArrayList<Integer>();
	
	static ArrayList<String> ratePlanList= new ArrayList<String>();
	static ArrayList<String> corporateList= new ArrayList<String>();
	
	static ArrayList<String> packageNameList= new ArrayList<String>();
	static ArrayList<String> packageRateList= new ArrayList<String>();
	static ArrayList<String> packageDescriptionList= new ArrayList<String>();
	
	static ArrayList<String> accountNumList= new ArrayList<String>();
	static ArrayList<String> accountNameList= new ArrayList<String>();
	
	static ArrayList<Guest> guestList= new ArrayList<Guest>();
	static ArrayList<Guest> honorsGuestList= new ArrayList<Guest>();
	
	File file= new File("Coupon Report Settings.xlsx");
	
	static int reportCellStart;
	
	static boolean includeOnBoth=false;
	
	private JButton importRSI;
	private JButton importCouponReport;
	private JButton printLists;
	
	FrontPage(){
	
	setBorder( new EmptyBorder(20, 100, 20, 100) );
	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	
	JLabel couponReports= new JLabel("Coupon Reports");
	add(couponReports);
	//add(Box.createVerticalStrut(20));
	
	importRSI= new JButton("Import Room Status Inquiry");
	
	
	add(importRSI);
	importCouponReport= new JButton("Import Coupon Report");
	
	add(importCouponReport);
	printLists= new JButton("Print Lists");
	
	add(printLists);
	
	addActionListeners();
	
	}

	public static void main(String[] args) throws IOException {
		
		FrontPage fp= new FrontPage();
		fp.runProgram();
		
		
		}
               
public  void runProgram() throws IOException {
	
	initializeStartValues();
	JFrame frame= new JFrame("Coupon Reports");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().add(new FrontPage());
	
	frame.pack();
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);
	
	
}
		
	
	


public void initializeStartValues() throws IOException {
	
	//FileInputStream fis= new FileInputStream(new File("C:/users/kenneth/desktop/BreakfastSRP.xlsx"));
	//InputStream fis=this.getClass().getResourceAsStream("BreakfastSRP.xlsx");
	
	
	
	try {
		FileInputStream fis= new FileInputStream(file);
	XSSFWorkbook workbook = new XSSFWorkbook(fis);
	File directory = new File("./");
	System.out.println(directory.getAbsolutePath());
	
	
	XSSFSheet worksheet = workbook.getSheetAt(0);
	XSSFSheet worksheet1 = workbook.getSheetAt(1);
	XSSFSheet worksheet2 = workbook.getSheetAt(2);
	XSSFSheet worksheet3 = workbook.getSheetAt(3);
	
	int rowsCount= worksheet.getLastRowNum();
	int rowsCount1= worksheet1.getLastRowNum();
	int rowsCount2= worksheet2.getLastRowNum();
	
	System.out.println(rowsCount2);
	
	for(int i=1; i<=rowsCount; i++){
		XSSFRow row= worksheet.getRow(i);
		
		XSSFCell cell= row.getCell(0);
		
		String companyName= cell.getStringCellValue();
		
		corporateList.add(companyName);
		
		
		
	}
	
	for(int i=1; i<=rowsCount; i++) {
       XSSFRow row= worksheet.getRow(i);
		
		XSSFCell cell= row.getCell(1);
		
		String srpCode= cell.getStringCellValue();
		
		ratePlanList.add(srpCode);
	}
	
	for(int i=1;i<=rowsCount1;i++) {
		XSSFRow row= worksheet1.getRow(i);
		
		XSSFCell cell= row.getCell(0);
		
		String packageName= cell.getStringCellValue();
		
		packageNameList.add(packageName);
	}
	
	for(int i=1;i<=rowsCount1;i++) {
		
         XSSFRow row= worksheet1.getRow(i);
		
		XSSFCell cell= row.getCell(1);
		
		String srpCode= cell.getStringCellValue();
		
		packageRateList.add(srpCode);
		
		
	}
	
	for(int i=1;i<=rowsCount1;i++) {
		
        XSSFRow row= worksheet1.getRow(i);
		
		XSSFCell cell= row.getCell(2);
		
		String packageDescription= cell.getStringCellValue();
		
		packageDescriptionList.add(packageDescription);
		
		
	}
	
	for(int i=1;i<=rowsCount2; i++) {
         XSSFRow row= worksheet2.getRow(i);
		
		XSSFCell cell= row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		
		String accountName= cell.getStringCellValue();
		
		accountNameList.add(accountName);
		
	}
	
	for(int i=1;i<=rowsCount2; i++) {
        XSSFRow row= worksheet2.getRow(i);
		
		XSSFCell cell= row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		String accountNumber= cell.getStringCellValue();
		
		accountNumList.add(accountNumber);
	
	}
	
	
        XSSFRow row= worksheet3.getRow(1);
        XSSFRow row1= worksheet3.getRow(2);
		
		XSSFCell cell= row.getCell(1);
		XSSFCell cell1= row1.getCell(1);
		
		int startValue= (int) (cell.getNumericCellValue()-1);
		int endValue= (int) (cell1.getNumericCellValue()-1);
		
		
		
		for(int i=startValue; i<=endValue; i++) {
			
			if(startValue%2==0) {
				if(i%2==0) {
				
				cellNumberList.add(i);}
			}
			else {
				if(i%2!=0) {
					cellNumberList.add(i);
				}
					
			}
			
		}
		
		System.out.println(cellNumberList);
	
	  XSSFRow row2= worksheet3.getRow(5);
	  XSSFCell cell2= row2.getCell(1);
	  
	  int reportStartValue= (int) cell2.getNumericCellValue();
	  reportCellStart= reportStartValue-1;
	  
	  System.out.println(reportCellStart);
	  
	  XSSFRow row3= worksheet3.getRow(7);
	  XSSFCell cell3= row3.getCell(1);
	  
	  String yesOrNo= cell3.getStringCellValue();
	  
	  if((yesOrNo.charAt(0)=='y')||(yesOrNo.charAt(0)=='Y')) {
		  includeOnBoth=true;
	  }
	
	  //XSSFRow
	
	
	
	//JOptionPane.showMessageDialog(null, corporateList.get(0));
	//System.out.println(corporateList);
	//System.out.println(ratePlanList);
	//System.out.println(packageNameList);
	  System.out.println(accountNameList);
	  }
	catch(FileNotFoundException e) {
		JOptionPane.showMessageDialog(null, "Your Program Settings File Cannot Be Found!  Program Will Terminate");
	System.exit(0);
	}
	catch (IOException e) {
		 JOptionPane.showMessageDialog(null, "Program settings file is not compatible.  Make sure the file name is the same and values are compatible.");
	     System.exit(0);
	}
	catch(NumberFormatException e) {
		JOptionPane.showMessageDialog(null, "Program settings file is not compatible.  Make sure the file name is the same and values are compatible.");
	    System.exit(0);	
	}
	catch(NullPointerException e) {
		
		JOptionPane.showMessageDialog(null, "Program settings file is not compatible.  Make sure the file name is the same and values are compatible.  We "
				+ "have detected that you may have erased some necessary values in the settings file.  Certain fields cannot be blank.");
        System.exit(0);	
	}
	
}


public void importRSI()  {
	
	JFileChooser chooser= new JFileChooser();
	boolean success=false;
	
	if(chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION){
		JOptionPane.showMessageDialog(null, "The following file type is incompatible.");
		return;
		
    }
	
	try {
		File fileRSI= chooser.getSelectedFile();
		FileInputStream fis= new FileInputStream(fileRSI);
	    HSSFWorkbook workbook= new HSSFWorkbook(fis);
	    HSSFSheet worksheet= workbook.getSheetAt(0);
	    
	    for(int room: cellNumberList) {
	    	
	    	HSSFRow row= worksheet.getRow(room);
	    	HSSFCell cellRoomNumber= row.getCell(1);
	    	HSSFCell cellName= row.getCell(4,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
	    	HSSFCell cellNumGuests= row.getCell(5,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
	    	HSSFCell SRP= row.getCell(14,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
	    	
	    	//int roomNumber= (int) cellRoomNumber.getNumericCellValue();
	    	String guestRoom= cellRoomNumber.getStringCellValue();
	    	int roomNumber = Integer.parseInt(guestRoom);
	    	String name= cellName.getStringCellValue();
	    	String guestCount= cellNumGuests.getStringCellValue();
	    	String srpCode= SRP.getStringCellValue();
	    	
	    	if(name.length()!=0) {
	    		String lastName= name.substring(0, name.indexOf('/'));
	    		String firstName= name.substring(name.indexOf('/')+1, name.length());
	    		int numAdults;
	    		int numChildren;
	    		if (guestCount.length()!=0){
	    		String adults= guestCount.substring(0, guestCount.indexOf(','));
	    		String children= guestCount.substring(guestCount.indexOf(',')+1, guestCount.length());
	    		numAdults= Integer.parseInt(adults);
	    		numChildren= Integer.parseInt(children);
	    		}
	    		else {
	    		 numAdults=1;
	    		 numChildren=0;
	    		}
	    	   
	    		Guest guest= new Guest(roomNumber, lastName, firstName, srpCode, numAdults, numChildren );
	    		guestList.add(guest);
	    		
	    		if(ratePlanList.contains(srpCode)) {
	    			int rateIndex= ratePlanList.indexOf(srpCode);
	    			String company= corporateList.get(rateIndex);
	    			
	    			guest.setCompany(company);
	    			
	    		}
	    		
	    		if(packageRateList.contains(srpCode)) {
	    			int rateIndex= packageRateList.indexOf(srpCode);
	    			String packageName= packageNameList.get(rateIndex);
	    			String packageDescription= packageDescriptionList.get(rateIndex);
	    			
	    			guest.setBreakfastPackageName(packageName);
	    			guest.setBreakfastPackageDescription(packageDescription);
	    		}
	    		//System.out.println(guest.getRatePlan()+":"+guest.getCompany());
	    		
	    	}
	    	
	    	
	    	success=true;
	    
	    	
	    	
	    }
	
	
	
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		JOptionPane.showMessageDialog(null, "Cannot Find File");
	}
	catch (IOException e) {
		 JOptionPane.showMessageDialog(null, "Import Not Successful.  Please review the file and try again.");
	}
	catch(NumberFormatException e) {
		JOptionPane.showMessageDialog(null, "Import Not Successful.  Please review the file and try again.");
		
	}
	
	    if(success==true) {
        JOptionPane.showMessageDialog(null, "Import Successful!");}
  
}
public void importCouponReport() {
	boolean success=false;
	JFileChooser chooser= new JFileChooser();
	if(chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION){
		JOptionPane.showMessageDialog(null, "The following file type is incompatible.");
		return;
		
    }
	
	try {
	File fileCouponReport= chooser.getSelectedFile();
	FileInputStream fis= new FileInputStream(fileCouponReport);
    HSSFWorkbook workbook= new HSSFWorkbook(fis);
    HSSFSheet worksheet= workbook.getSheetAt(0);
    
   
    
    
    System.out.println(reportCellStart);
    int rowsCount= worksheet.getLastRowNum();
    for(int i= reportCellStart; i<rowsCount;i++) {
    	
    	HSSFRow row= worksheet.getRow(i);
    	HSSFCell cell= row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
    	HSSFCell cell1= row.getCell(9, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
    	HSSFCell cell2= row.getCell(11, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
    	
    	String name= cell.getStringCellValue();
    	String honorsStatus= cell1.getStringCellValue();
    	String myWayChoice= cell2.getStringCellValue();
    	
    	String lastName= name.substring(0, name.indexOf(","));
    	String firstName= name.substring(name.indexOf(" ")+1, name.length());
    	
    	//System.out.println(firstName);
    	
    	char honorsTier= honorsStatus.charAt(0);
    	
    	for(Guest g: guestList) {
    		if(g.getLastName().equalsIgnoreCase(lastName)&&(g.getFirstName().equalsIgnoreCase(firstName))&&(g.isGuestFound()==false)) {
    			g.setHonorsTier(honorsTier);
    			g.setMyWaySelection(myWayChoice);
    			g.setGuestFound(true);
    			System.out.println(g.getLastName());
    			honorsGuestList.add(g);
    			success=true;
    		}
    		
    	}
    	
    }}
    catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		JOptionPane.showMessageDialog(null, "Cannot Find File");
	}
	catch (IOException e) {
		 JOptionPane.showMessageDialog(null, "Import Not Successful.  Please review the file and try again.");
	}
	catch(NumberFormatException e) {
		JOptionPane.showMessageDialog(null, "Import Not Successful.  Please review the file and try again.");
		
	}
	catch(NullPointerException e) {
		JOptionPane.showMessageDialog(null, "Import Not Successful.  Please review the file and try again.");
	}
        if(success=true) {
             JOptionPane.showMessageDialog(null, "Import Successful!");}
       
    
     
	
}


public void addActionListeners() {
	importRSI.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			importRSI();
		}
	});
	
	printLists.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Reports r= new Reports();
			try {
				r.createAndShowTable();
			} catch (PrinterException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	});
	
	importCouponReport.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			if(guestList.size()>0) {
			importCouponReport();
			}
			else {
				JOptionPane.showMessageDialog(null, "You must import the room status inquiry first");
			}
		}
	});
}



}
