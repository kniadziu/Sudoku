package pl.edu.agh.mwo.java;

import java.util.HashSet;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class SudokuBoardChecker extends App {

	Workbook workbook;

	public SudokuBoardChecker(Workbook workbook) {
		this.workbook = workbook;

	}

	/**
	 * metoda przyjmuje indeks arkusza(zaczynając od 0 i zwraca true, jesli arkusz
	 * zawiera plansze Sudoku zawierajaca poprawne typy danych (ale nie koniecznie
	 * poprawne), w przeciwnym razie zwraca false.
	 * 
	 * @param sheetIndex
	 * @return true - dane typu numerycznego lub blank, false - pozostałe typy
	 */
	public boolean verifyBoardStructure(int sheetIndex) {
		// Workbook wb;
		Sheet sheet = workbook.getSheetAt(sheetIndex);

		for (Row row : sheet) {
			for (Cell cell : row) {
				if (cell.getCellType().equals(CellType.NUMERIC) || cell.getCellType().equals(CellType.BLANK)) {
				} else {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * metoda sprawdza arkusze, czy w każdym wierszu oraz kolumnnie, oraz w
	 * kwadracie 3x3 nie ma dwóch powtarzających się cyfr, oraz czy są liczby z
	 * zakresu 0-9
	 * 
	 * @param sheetIndex - indeks arkusza
	 * @return true- tablica poprawna, false - tablica bledna
	 */
	public boolean verifyBoard(int sheetIndex) {
		Sheet sheet = workbook.getSheetAt(sheetIndex);

		for (int i = 0; i < 9; i++) {
			HashSet<Integer> row = new HashSet<Integer>();
			HashSet<Integer> col = new HashSet<Integer>();
			HashSet<Integer> cube = new HashSet<Integer>();

			for (int j = 0; j < 9; j++) {
				int valueInRow = (int) sheet.getRow(i).getCell(j).getNumericCellValue();
				int valueInColumn = (int) sheet.getRow(j).getCell(i).getNumericCellValue();

				// Sprawdz, czy liczby sa z zakresiu 0-9
				if (valueInRow < 0 || valueInRow > 9) {
					System.out.printf("Błędna liczba poza zakresem 0-9: Pojawiła się liczba: %d. ", valueInRow);
					return false;
				}

				// sprawdz czy są powtorzenia w wierszach
				if (valueInRow != 0 && !row.add(valueInRow)) {
					System.out.printf("Błąd w wierszu: %d. Powtórzona liczba: %d", i + 1, valueInRow);
					return false;
				}

				// sprawdz czy są powtorzenia w kolumnach
				if (valueInColumn != 0 && !col.add(valueInColumn)) {
					System.out.printf("Błąd w kolumnnie: %d. Powtórzona liczba: %d", i + 1, valueInColumn);
					return false;
				}

				// sprawdz czy są powtorzenia w boxach
				int rowIndex = 3 * (i / 3) + j / 3;
				int colIndex = 3 * (i % 3) + j % 3;
				int valueInBox = (int) sheet.getRow(rowIndex).getCell(colIndex).getNumericCellValue();

				if (valueInBox != 0 && !cube.add(valueInBox)) {
					System.out.printf("Błąd w BOX, wiersz: %d, kolumna: %d. Powtórzona liczba: %d", rowIndex + 1,
							colIndex + 1, valueInBox);
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * metoda wyswietla tablicę Sudoku
	 * 
	 * @param sheetIndex - indeks arkusza Excel
	 * @return void
	 */
	public void printSudoku(int sheetIndex) {
		Sheet sheet = workbook.getSheetAt(sheetIndex);

		for (Row row : sheet) {
			if (row.getRowNum() % 3 == 0)
				System.out.println(" -----------------------");
			for (Cell cell : row) {
				if (cell.getColumnIndex() % 3 == 0)
					System.out.print("| ");
				System.out.print((int) cell.getNumericCellValue() == 0 ? " " : (int) cell.getNumericCellValue());
				System.out.print(' ');
			}
			System.out.println("|");
		}
		System.out.println(" -----------------------");
	}

}