# LinearSpace
LinearSpace is a console based software serving as a tool to perform various matrix and vector operations.

### Linspace.py
This library introduces three data types (Matrix, Square matrix, Vector), each containing methods performing various matrix and vector operations to each type respectively :

* Matrix
  * `multiply` : Performs matrix multiplications between two matrices
  * `reduce` : Calculates the reduced row echelon form of a matrix
    
* Square matrix (Extending from Matrix)
  * `determinant` : Calculates detertiminants of square matrices
  * `inverse` : Finds the inverse of square matrices if they exist
    
* Vector (Extending from Matrix)
  * `dot`: Calculates dot product of two vectors
  * `cross` : Finds the cross product of two vectors
  * `projection` : Finds the orthogonal projection of a vector onto another

### App.py
Contains the code which allows users to interact with the different methods performing the matrix and vector operations.

## m_save.json
JSON save file containing the matrices / vectors saved by the user. Can be loaded into App.py for furter manipulation.

### run.bat
Batch file runnable by user.
