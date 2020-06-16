package pl.edu.agh.mwo.java;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class App_Sudoku {

	public static void main(String[] args) {

		System.out.println("************************************************************");
		System.out.println("*\t Matrix Sudoku check  ");
		System.out.println("*\t Autor: Grzegorz Koziel ");
		System.out.println("************************************************************");

		Workbook sudokuWB = WorkbookLoader.openSudokuWorkbook();

		SudokuBoardChecker sudokuObj = new SudokuBoardChecker(sudokuWB);
		
		System.out.println("Zadanie 1 - sprawdź poprawność danych w skroszytach - verifyBoardStructure]n");

		for (Sheet sheet : sudokuObj.workbook) {
			int i = sudokuObj.workbook.getSheetIndex(sheet);
			System.out.println("Arkusz- " + sheet.getSheetName() + ": " + sudokuObj.verifyBoardStructure(i));
		}

		System.out.println("\n\nZadanie 2 - sprawdź poprawność liczb w wierszach kolumnach i boxach");
		for (Sheet sheet : sudokuObj.workbook) {

			int j = sudokuObj.workbook.getSheetIndex(sheet);
			if (sudokuObj.verifyBoardStructure(j)) {
				System.out.println("\n******** Arkusz Sudoku: " + sheet.getSheetName() + " ***************\n");
				String result = sudokuObj.verifyBoard(j) ? "poprawny" : "niepoprawny";			
				System.out.println("\nArkusz " + sheet.getSheetName() + " jest: " + result);
				sudokuObj.printSudoku(j);
			}
		}

	}

}
