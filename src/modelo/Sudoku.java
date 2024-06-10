package modelo;

public class Sudoku {

    private char sudoku[][];

    public Sudoku() {
        char sudo[][] = {
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
        };
        sudoku = sudo;
    }
    
    
    // Método booleano para resolver el sudoku una vez cargadas las letras
    public boolean resolverSudoku() {
        // Ciclo para recoger las filas
        for (int i = 0; i < sudoku.length; i++) {
            // Ciclo para recorrer las columnas
            for (int j = 0; j < sudoku[0].length; j++) {
                // Condición para incluir una posible letra en el campo vacío
                if (sudoku[i][j] == ' ') {
                    // Ciclo para recorrer las letras posibles a ingresar
                    for (char letra = 'A'; letra <= 'I'; letra++) {
                        // Condición para validar que la letra no se repita en la fila, columna y cuadrado y así se pueda ingresar la letra
                        if (validarFila(i, letra) && validarColumna(j, letra) && validarCuadrado(i, j, letra)) {
                            sudoku[i][j] = letra;
                            
                            //Se manda a llamar el método recursivo para que no haya valores que no correspondan dentro de las filas y columnas o se muestren espacios en blanco cuando no hay solución
                            if (resolverSudoku()) {
                                return true;
                            }

                            sudoku[i][j] = ' ';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    // Método para validar el cuadrado de 3x3
    public boolean validarCuadrado(int i, int j, char letra) {
        // Obtiene la posición inicial de la fila del cuadrado
        int posI = cuadradoActual(i);
        // Obtiene la posición inicial de la columna del cuadrado
        int posJ = cuadradoActual(j);
        //Ciclo que recorre las filas del cuadrado actual
        for (int k = posI - 3; k < posI; k++) {
            //Ciclo que recorre las columnas del cuadrado actual
            for (int l = posJ - 3; l < posJ; l++) {
                // Condición para verificar si la letra ya existe en el cuadrado
                if (sudoku[k][l] == letra) {
                    // Retorna falso si la letra ya existe en el cuadrado
                    return false;
                }
            }
        }
        // Retorna verdadero si la letra no existe en el cuadrado
        return true;
    }

    public int cuadradoActual(int pos) {
        if (pos <=2) {
            return 3;
        } else if (pos <= 5) {
            return 6;
        } else {
            return 9;
        }
    }

    public boolean validarFila(int i, char letra) {
        for (int j = 0; j < sudoku[i].length; j++) {
            if (sudoku[i][j] == letra) {
                return false;
            }
        }
        return true;
    }

    public boolean validarColumna(int j,char letra) {
        for (int i = 0; i < sudoku.length; i++) {
            if (sudoku[i][j] == letra) {
                return false;
            }
        }
        return true;
    }

    public void mostrarSudoku() {
        resolverSudoku();
        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku[0].length; j++) {
                System.out.print(sudoku[i][j]);
                if (!(j == sudoku[0].length - 1)) {
                    System.out.print(" - ");
                }
            }
            System.out.println();
        }
    }

    public char[][] getSudoku() {
        return sudoku;
    }

    public void setSudoku(char[][] sudoku) {
        this.sudoku = sudoku;
    }
}
