package printing;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageable;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.PrintPDF;

import database.LKDatabase;
import java.io.File;
import java.io.IOException;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.PageRanges;
import javax.print.attribute.standard.Sides;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.awt.Desktop;
import  java.awt.print.Pageable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;


public class Printer {

	public static float POINTS_PER_INCH = 72;
	public static float POINTS_PER_MM = 1 / (10 * 2.54f) * POINTS_PER_INCH;
	public static ArrayList<String[]> content = new ArrayList<String[]>();
	public static ArrayList<String[]> cacheSpezific = new ArrayList<String[]>();
	public static ArrayList<String[]> cacheAll = new ArrayList<String[]>();
	public static String fileName = "D:/Karteikarten.pdf"; // name of our file
	public static int pages = 0;
	public static int lastPage = 0;
	public static int actualPage = 0;
	public static PDDocument finalDoc;

	public static void giveToPrint(String stack) throws Exception {
		setValues(stack);
		pages = calculatePages(content.size());
		printContent();
		for (int i = 0; i < pages; i++) { 
			if (i == 0) {
		
				PDDocument doc = new PDDocument();
				doc.save(fileName);
				doc.close();
				
				
				
				cacheAll = content;
			}

			PDDocument doc2 = PDDocument.load(new File(fileName));
			
			cacheSpezific = createCaheSpezific(cacheAll);


			createPage(doc2,2);
			int pages = doc2.getDocumentCatalog().getAllPages().size();
			
			drawLines(doc2,actualPage);
			
			drawText(doc2,actualPage);
			actualPage+=2;
			finalDoc = doc2;
			doc2.save(fileName);
			doc2.close();
			
			
			
			print();
		}
		
		
		printDocument();
	}
	
	public static void printDocument() throws IOException
	{	
		//Desktop.getDesktop().open(new File(fileName));
		/*PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
		DocFlavor myFormat = DocFlavor.URL.PDF
		aset.add(Sides.DUPLEX);
		PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();
	       DocPrintJob printerJob = defaultPrintService.createPrintJob();
	       File pdfFile = new File("D://Karteikarten.pdf");
	       SimpleDoc simpleDoc = null;
	        
	       try {
	           simpleDoc = new SimpleDoc(pdfFile.toURL(), DocFlavor.URL.PDF, null);
	       } catch (MalformedURLException ex) {
	           ex.printStackTrace();
	       }
	       try {
	           printerJob.print(simpleDoc, null);
	       } catch (PrintException ex) {
	           ex.printStackTrace();
	       }*/
		
		/*DocFlavor myFormat = DocFlavor.;
		// Create a Doc
		Doc myDoc = new SimpleDoc(new FileInputStream("D://Karteikarten.pdf"), myFormat, null); 
		// Build a set of attributes
		PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet(); 
		aset.add(Sides.DUPLEX); 
		// discover the printers that can print the format according to the
		// instructions in the attribute set
		PrintService[] services =
		        PrintServiceLookup.lookupPrintServices(myFormat, aset);
		// Create a print job from one of the print services
		if (services.length > 0) { 
		        DocPrintJob job = services[0].createPrintJob(); 
		        try { 
		                job.print(myDoc, aset); 
		        } catch (PrintException pe) {} 
		}*/
		PDDocument d = new PDDocument();
		d.load(new File("D://Karteikarten.pdf"));
		PrinterJob pj = PrinterJob.getPrinterJob();
		pj.setPageable(d);
		  if (pj.printDialog()) {
		        try {pj.print();}
		        catch (PrinterException exc) {
		            System.out.println(exc);
		         }
		     }
	}

	 private static PrintService findPrintService(String printerName) {
	        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
	        for (PrintService printService : printServices) {
	            if (printService.getName().trim().equals(printerName)) {
	                return printService;
	            }
	        }
	        return null;
	    }   

	public static void printContent()
	{
		System.out.println("Start");
		for(String[] s: content)
		{
			System.out.print(s[0] + "|" + s[1] +"|"+ s[2] +"\n");
		}
		System.out.println("End");
	}
	
	public static void setValues(String stack) {
		/*for (int i = 0; i < 30; i++) {
			String[] word = { Integer.toString(i), Integer.toString(i) };
			content.add(word);
		}*/
		
		content =  LKDatabase.myCards.pullFromStock(stack);
	}

	public static int calculatePages(int size) {
		int r = 1;
		int count = 1;
		
		for(int i = 0; i < size; i++)
		{
			if(count == 16)
			{
				count = 1;
				r += 1;
			}
			
			count++;
		}
		System.out.print(r + " pages \n");
		return r;
	}

	public static ArrayList<String[]> createCaheSpezific(ArrayList<String[]> list) 
	{
		ArrayList<String[]> r = new ArrayList<String[]>();
		int count = 0;
		int size = 0;
		
		if(list.size() > 16)
		{
			count = 16;
			size  = list.size() - count -1;
		}
		else
		{
			count = list.size();
			size = -1;
		}
		
		
		
		for(int i = list.size()-1; i > size ; i--)
		{
			System.out.print(i+"!"+(size)+"/");
			
			r.add(list.get(i));
			cacheAll.remove(i);
		}
		
		return r;
	}
	
	public static void print()
	{
		for(String[] s: cacheSpezific)
		{
			System.out.print(s[0] + " "+ s[1] + " \n");
		}
	}

	static void createPage(PDDocument temp,int count)
	{
		for(int i = 0; i < count;i++)
		{
			PDPage page = new PDPage(new PDRectangle(297 * POINTS_PER_MM, 210 * POINTS_PER_MM));
			temp.addPage(page);
		}
	}
	
	static void drawLines(PDDocument doc,int page)
	{
		try {
			PDPageContentStream content1 = new PDPageContentStream(doc,(PDPage) doc.getDocumentCatalog().getAllPages().get(page),true,true);
			//Vertical
			content1.drawLine(0, (float)(POINTS_PER_MM * 52.5),POINTS_PER_MM * 297 , (float)(POINTS_PER_MM * 52.5));
			content1.drawLine(0, POINTS_PER_MM * 105,POINTS_PER_MM * 297 , POINTS_PER_MM * 105);
			content1.drawLine(0, (float)(POINTS_PER_MM * 157.5),POINTS_PER_MM * 297 , (float)(POINTS_PER_MM * 157.5));
			
			//Horizontal
			content1.drawLine((float)(POINTS_PER_MM * 74.25), 0,(float)(POINTS_PER_MM * 74.25),(float)(POINTS_PER_MM * 210));
			content1.drawLine((float)(POINTS_PER_MM * 148.5), 0,(float)(POINTS_PER_MM * 148.5) , (float)(POINTS_PER_MM * 210));
			content1.drawLine((float)(POINTS_PER_MM * 222.75), 0,(float)(POINTS_PER_MM * 222.75), (float)(POINTS_PER_MM * 210));
			content1.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			PDPageContentStream content2 = new PDPageContentStream(doc,(PDPage) doc.getDocumentCatalog().getAllPages().get(page+1),true,true);
			//Vertical
			content2.drawLine(0, (float)(POINTS_PER_MM * 52.5),POINTS_PER_MM * 297 , (float)(POINTS_PER_MM * 52.5));
			content2.drawLine(0, POINTS_PER_MM * 105,POINTS_PER_MM * 297 , POINTS_PER_MM * 105);
			content2.drawLine(0, (float)(POINTS_PER_MM * 157.5),POINTS_PER_MM * 297 , (float)(POINTS_PER_MM * 157.5));
			
			content2.drawLine((float)(POINTS_PER_MM * 74.25), 0,(float)(POINTS_PER_MM * 74.25),(float)(POINTS_PER_MM * 210));
			content2.drawLine((float)(POINTS_PER_MM * 148.5), 0,(float)(POINTS_PER_MM * 148.5) , (float)(POINTS_PER_MM * 210));
			content2.drawLine((float)(POINTS_PER_MM * 222.75), 0,(float)(POINTS_PER_MM * 222.75), (float)(POINTS_PER_MM * 210));
			content2.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void drawText(PDDocument doc, int page)
	{
		PDFont font = PDType1Font.HELVETICA_BOLD;
		int fontSize = 12;
		int pos = 0;
		double val1 = 10; //37.125
		double val2 = 236.25;
		double val3 = 232.75;//259.875
		double val4 = 183.75;
		for(int o = 0; o < 4; o++)
		{
			val2 -= 52.5;
		for(int i = 0; i < 4;i++)
				{
			
			float x = (float)(val1*POINTS_PER_MM);
			float y = (float)(val2*POINTS_PER_MM);
			float x2 = (float)(val3*POINTS_PER_MM);
			float y2 = (float)(val4*POINTS_PER_MM);
			
			String one = "";
			String two = "";
			
			if(pos >= cacheSpezific.size())
			{
				one = "-";
				two = "-";
			}
			else
			{
				one = cacheSpezific.get(pos)[1];
				two = cacheSpezific.get(pos)[2];
			}
			
			try {
				
				PDPageContentStream content1 = new PDPageContentStream(doc,(PDPage) doc.getDocumentCatalog().getAllPages().get(page),true,true);
				//Vertical
				content1.beginText();
				content1.setFont(font, fontSize);
				content1.moveTextPositionByAmount(x, y);
				content1.drawString(one);
				content1.endText();
				content1.close();
				System.out.println(one);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			try {
				PDPageContentStream content2 = new PDPageContentStream(doc,(PDPage) doc.getDocumentCatalog().getAllPages().get(page+1),true,true);
				//Vertical
				content2.beginText();
				content2.setFont(font, fontSize);
				content2.moveTextPositionByAmount(x2, y2);
				content2.drawString(two);
				content2.endText();
				content2.close();
				System.out.println(two);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			val1 += 74.25;
			val3 -= 74.25;
			pos ++;
		}
		val1 = 10; //37.125
		val3 = 232.75;
		
		val4 -= 52.5;
		
		}
	}
}