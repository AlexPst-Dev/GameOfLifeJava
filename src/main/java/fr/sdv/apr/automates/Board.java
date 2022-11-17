package fr.sdv.apr.automates;

/**
 * Classe Board : Permet de générer un tableau de  cellules pour créer le jeu.
 */
public class Board {

    static final double PROBABILITY_NO_LIFE = 0.7;
    static final int MAX_PROBABILITY = 1;
    static final int MIN_PROBABILITY = 0;
    private int nbRows;
    private int nbColumns;
    private Cell [][]board;
    private Cell [][]nextBoard;

    /**
     * Classe Cell : Permet de remplir le board avec des cellules, vivantes ou non.
     */
    public class Cell {

        private int x;
        private int y;
        private boolean state;


        /**
         * getState : Retourne la valeur de state.
         * @return boolean
         */
        public boolean getState() {
            return this.state;
        }

        public Cell(int x, int y, boolean state) {
            this.x = x;
            this.y = y;
            this.state = state;
        }
    }

    /**
     * displayBoard : Affiche le board du jeu dans la console.
     */
    public void displayBoard() {
        for (Cell[] tab : this.board) {
            for (Cell cell : tab) {
                System.out.print((cell.getState() ? "X" : "-") + "\t");
            }
            System.out.println("\n");
        }
    }

    /**
     * randomBirthCell : Permet de générer aléatoirement une cellule en fonction des probabilités souhaitées.
     * @param x
     * @param y
     * @return Cell
     */
    public Cell randomBirthCell(int x, int y) {
       boolean isAlive = (Math.random() * (MAX_PROBABILITY - MIN_PROBABILITY)) > PROBABILITY_NO_LIFE ? true : false;

       return new Cell(x, y, isAlive);
    }

    /**
     * isValidIndex : permet de vérifier si l'index du tableau bidimensionnel est valide.
     * @param x
     * @param y
     * @return boolean
     */
    public boolean isValidIndex(int x, int y) {
        boolean isValid = (x >= 0 && y >= 0 && x < this.nbRows && y < this.nbColumns) ? true : false;
        return isValid;
    }

    /**
     * checkAdjacentCells : Permet de calculer le nombre de cellules vivantes adjacentes.
     * @param cell
     * @return int
     */
    public int checkAdjacentCells(Cell cell) {
        int x = cell.x;
        int y = cell.y;
        int nbNeighbors = 0;

        //test gauche
        if (isValidIndex(x - 1, y)) {
            if(this.board[x - 1][y].getState()) {
                nbNeighbors++;
            }
        }
        //test diagonale haut gauche
        if (isValidIndex(x - 1, y - 1)) {
            if (this.board[x - 1][y - 1].getState()) {
                nbNeighbors++;
            }
        }

        //test haut
        if (isValidIndex(x, y - 1)) {
            if (this.board[x][y - 1].getState()) {
                nbNeighbors++;
            }
        }
        //test diagonale haut droite
        if (isValidIndex(x + 1, y - 1)){
            if (this.board[x + 1][y - 1].getState()) {
                nbNeighbors++;
            }
        }

        //test droite
        if (isValidIndex(x + 1, y)){
            if (this.board[x + 1][y].getState()) {
                nbNeighbors++;
            }
        }

        //test diagonale bas droite
        if (isValidIndex(x + 1, y + 1)) {
            if (this.board[x + 1][y + 1].getState())
            nbNeighbors++;
        }
        //test bas
        if (isValidIndex(x, y + 1)){
            if (this.board[x][y + 1].getState()) {
                nbNeighbors++;
            }
        }

        //test diagonale bas gauche
        if (isValidIndex(x - 1, y + 1)) {
            if (this.board[x - 1][y + 1].getState()) {
                nbNeighbors++;
            }
        }

        return nbNeighbors;
    }

    /**
     * nextState : Permet de créer un tampon, en fonction des règles du jeu, pour le futur board.
     * @param cell
     * @param nbNeighbors
     */
    public void nextState(Cell cell, int nbNeighbors) {
        if (nbNeighbors < 2) {
            this.nextBoard[cell.x][cell.y] = new Cell(cell.x, cell.y, false);
        } else if (nbNeighbors >= 4) {
            this.nextBoard[cell.x][cell.y] = new Cell(cell.x, cell.y, false);
        } else if (nbNeighbors == 3) {
            this.nextBoard[cell.x][cell.y] = new Cell(cell.x, cell.y, true);
        } else if (nbNeighbors == 2) {
            this.nextBoard[cell.x][cell.y] = new Cell(cell.x, cell.y, cell.state);
        }
    }

    /**
     * browseBoard : Permet de parcourir l'ensemble du board de jeu pour jouer un tour.
     */
    public void browseBoard() {
        for (Cell[] tab : this.board) {
            for (Cell cell: tab) {
                if (cell != null) {
                    int nbNeighbors = checkAdjacentCells(cell);
                    nextState(cell, nbNeighbors);
                }
            }
        }
        this.board = this.nextBoard.clone();
        this.nextBoard = new Cell[nbRows][nbColumns];
    }


    /**
     * constructeur Board : Aura besoin d'un nombre de lignes et de colonnes afin de le générer le jeu.
     * @param nbRows
     * @param nbColumns
     */
    public Board(int nbRows, int nbColumns) {
        this.nbRows = nbRows;
        this.nbColumns = nbColumns;
        this.board = new Cell[nbRows][nbColumns];
        this.nextBoard = new Cell[nbRows][nbColumns];


        for (int x = 0; x < nbRows; x++) {
            for (int y = 0; y < nbColumns; y++) {
                this.board[x][y] = randomBirthCell(x, y);
            }
        }

        // Test de l'oscillateur "clignotant"
        /*this.board[0][0] = new Cell(0, 0, false);
        this.board[0][1] = new Cell(0, 1, true);
        this.board[0][2] = new Cell(0, 2, false);
        this.board[1][0] = new Cell(1, 0, false);
        this.board[1][1] = new Cell(1, 1, true);
        this.board[1][2] = new Cell(1, 2, false);
        this.board[2][0] = new Cell(2, 0, false);
        this.board[2][1] = new Cell(2, 1, true);
        this.board[2][2] = new Cell(2, 2, false);*/
    }

    /**
     * getBoard : Permet de retourner l'instance de jeu.
     * @return Board
     */
    public Cell[][] getBoard() {
        return this.board;
    }
}
