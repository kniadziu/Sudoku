package pl.edu.agh.mwo.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class App {
    public static void main(String[] args) {

       // System.out.println("Hello World!");
       // Workbook f1Wb = WorkbookLoader.openF1Workbook();
      //  PoiDemo.printSheetNames(f1Wb);
       // PoiDemo.printCellsFromSheet(f1Wb.getSheetAt(0));
       // PoiDemo.computePointsForARace(f1Wb.getSheetAt(0));
     //   Workbook sudokuWb = WorkbookLoader.openSudokuWorkbook();
      //  PoiDemo.computeAllNumberInAGivenColumn(sudokuWb.getSheetAt(0), 0);
        
     ///--------ZADANIA NA LABORCE  ------
       // zad1(f1Wb);
        //zad2(f1Wb);
        
       // zad3(f1Wb);
        // zad4(f1Wb);
    	
    	
    
    }
    
    public static void zad1(Workbook wb) {
    	//Napisz kod, który wypisze kierowców, którzy co najmniej raz zwyciężyli wyścig GP.
    	Set<String> winners=new HashSet<String>(); //Set- abstrakcyjny bardzie ogólny
    	
    	for (Sheet sheet : wb) {
    		Cell winner = sheet.getRow(0).getCell(2);
    		String winnerName = winner.getStringCellValue();
    		winners.add(winnerName);
         }
    	
    	for (String winner : winners) {
    		System.out.println(winner);
    	}
            
    }
    
    
    //ZAD. 2 - Napisz kod, który wypisze ile punktów zdobył Lewis Hamilton.
    public static void zad2(Workbook wb) {
    	final String DRIVER_NAME = "Lewis Hamilton HAM";
    	int overalPoints =0;
        for (Sheet sheet : wb) {
        	for (Row row: sheet ) {
        		Cell driver=row.getCell(2);
        		if (driver.getStringCellValue().equals(DRIVER_NAME)){
        			Cell points= row.getCell(5);
        			Integer intPoints=Integer.parseInt(points.getStringCellValue());
        			overalPoints+=intPoints;
        			break;
        		}
        	}
      
        }
        
        System.out.println(String.format("The sum of all numbers is %d", overalPoints));	
    	
    }
    
    
    //Napisz kod, który wypisze ile punktów zdobył zespół Ferrari.//
    public static void zad3(Workbook wb) {
       	final String TEAM_NAME = "Ferrari";
    	int overalPoints =0;
        for (Sheet sheet : wb) {
        	for (Row row: sheet ) {
        		Cell team=row.getCell(3);
        		if (team.getStringCellValue().contains(TEAM_NAME)){
        			Cell points= row.getCell(5);
        			Integer intPoints=Integer.parseInt(points.getStringCellValue());
        			overalPoints+=intPoints;
        		}
        	}
      
        }
        
        System.out.println(String.format("The sum of all numbers for "+ TEAM_NAME + " is %d", overalPoints));	
    	
    }
   
    
   //Napisz kod, który wypiszę tabelę wyników na koniec sezonu. W pojedynczym wierszu na konsoli 
    //wypisz miejsce, imię i nazwisko kierowcy, sumaryczna liczba punktów./
		
	public static void zad4(Workbook wb) {
			Map<String, Integer> ranking = new HashMap<String, Integer>();
		    for (Sheet sheet : wb) {
		        for (Row row: sheet ) {
		       		String driver=row.getCell(2).getStringCellValue();
		       	    Integer points= Integer.parseInt(row.getCell(5).getStringCellValue());
		       	    if (ranking.containsKey(driver)) {
		       	    	ranking.put(driver,  ranking.get(driver) + points);
		       	    } else {
		       	    	ranking.put(driver, points);
		       	    }        		
		        }
		    }
		    
		    List<Entry<String, Integer>> rankingList =  new ArrayList<>(ranking.entrySet());   
		    rankingList.sort(Collections.reverseOrder(Entry.comparingByValue()));
		    Integer position=0;
		    for (Entry<String, Integer> entry: rankingList) {
		    	System.out.println(String.format("%2d. %s -> %d" , ++position, entry.getKey(), entry.getValue()));
		    }
    }
    
    
    
    
    
}
