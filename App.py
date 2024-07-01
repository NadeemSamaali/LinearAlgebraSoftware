from Linspace import linear_algebra as ls
from Linspace.arithmetics import parse_and_evaluate
import json

# Function converting user input into matrix datatype
def setMatrix(h,l) :
    # Format matrix guide whenever overall dimensionas exceeds 3x3
    if h > 3 and l > 3 :
        print(f"# Insert the entires of the M_{h}x{l} matrix row by row, with each value separated by a space\n  respecting the following form :\n\n  a1_1 a1_2 ... a_1{l}\n  a2_1 a2_2 ... a2_{l}\n  ...\n  a{h}_1 a{h}_2 ... a{h}_{l}\n")
    # Format matrix guide whenever length or height is lesser or equal to 3
    if h <= 3 or l <= 3 :
        print(f"# Insert the entires of the M_{h}x{l} matrix row by row, with each value separated by a space\n  respecting the following form :\n")
        if h > 3 :
            for i in range(2) :
                print("  ", end  ="")
                for j in range (l) :
                    print(f"a{i+1}_{j+1}  ", end="")
                print()
            print("  ...")
            print("  ", end = "")
            for j in range(l) :   
                print(f'a{h}_{j+1}  ', end="")
            print("\n")
        if l > 3 :
            for i in range(h) :
                print("  ", end  ="")
                for j in range(2) :
                    print(f'a{i+1}_{j+1}  ', end ="")
                print(f'...  a{i+1}_{l}')
            print()
        if l <= 3 and h <= 3 :
            for i in range(h) :
                print("  ", end ="")
                for j in range(l) :
                    print(f'a{i+1}_{j+1}  ', end="")
                print()
            print()
    # Transforming the input of the user into a linspace matrix            
    m = [[0]*l for _ in range(h)]
    for i in range(h) :
            print("  ", end="")
            ans = input()
            a = ans.split()
            if len(a) != l :
                raise ValueError("Make sure the amount of entries is consistent with the matrix size")
            m[i] = [float(value) for value in a]
    return ls.matrix(m)

# Function converting user input into vector datatype
def setVector(l) :
    if l > 3 : 
        print(f"# Insert the components of the vector with each value separated by a space\n  respecting the following form :\n\n  v1 v2 ... v{l} : ", end="")
        
    if l <= 3 :
        print(f"# Insert the components of the vector with each value separated by a space\n  respecting the following form : \n\n  ", end ="")
        for i in range(l) :
            print(f'v{i+1}  ',end = "")
        print(":", end ="")
    print("  ", end="")
    ans = input()
    a = ans.split()
    if len(a) != l :
        raise ValueError("Make sure the number of entries is consistent with the vector length")
    v = [float(digit) for digit in a]
    return ls.vector(v)

# Loading json matrix save file
def load_matrix_base(file_path : str) -> dict :
    with open(file_path, 'r') as file : 
        data : dict = json.load(file)
    return data

# Saving the most recent matrix in storage to the json file
def save_matrix(m : ls.matrix, data : dict) :
    temp_dict = {}
    temp_dict['MID'] = len(data['matrices'])
    temp_dict['matrix'] = m.entries
    data['matrices'].append(temp_dict)  

# Update matrix base from data dictionnary   
def save_matrix_base(file_path : str, data : dict) :
    with open(file_path, 'w') as file :
        json.dump(data, file, indent=2)

def MID_lookup(MID : int, data : dict) -> ls.matrix:
    return ls.matrix(data['matrices'][MID]['matrix'])

# User based input
def run_LinearSpace() :
    # Load matrix save file
    data = load_matrix_base('m_save.json')
    
    # Print welcome message
    print("\n: Welcome to LinearSpace (Python Edition) :  ")
    print("\n  The current build supports matrix operations\n  and vector operations. More features\n  coming soon ...\n\n  Designed by Nadeem Samaali\n\n  Type '# help' to get started\n")
    
    # Set up matrix temporary storage
    M = []
    
    # User loop
    while True:
        try:
            u = input("# ").lower().split()
            
            # Help center
            if u[0] == 'help' :
                if len(u) == 1:
                    print("# Here's a table of currently supported commands\n")
                    print("  inventory          Prints indexed user saved matrices and vectors")
                    print("  build              Create matrices/vectors that will be automatically be saved")
                    print("  save               Saves most recent user inputted matrix/vector objects")
                    print("  load               Loads most saved matrix/vector objects for user calculations")
                    print("                     | For more information on the load command, use '# help load'")
                    print("  clear              Deletes all saved matrices and vectors\n")

                    print("  determinant        Calculates determinant of square matrix")
                    print("  mutliply           Performs matrix multiplication between two matrices")   
                    print("  inverse            Finds inverse of a square matrix")                
                    print("  reduce             Finds the reduced echelon form of a matrix")   
                    print("  cross              Finds cross product of two 3-dimensional vectors")                
                    print("  dot                Finds dot product of two n-dimensional vectors")                
                    print("  projection         Finds orthogonal projection of a vector onto another\n")  
                    print("  parse              Evaluate the result of an arithmetical expression\n")                                
                              
                    continue
                
                if len(u) == 2 and u[1] == 'load' :
                    print("\n  To load a saved matrix or vector to perform operations on, type in a regular")
                    print("  command followed by 'load' and the MIDs of the matrices/vectors involved. Here is an example \n")
                    print("  '# determinant load 0' or '# multiply load 2 7'\n")
                    print("  Note : the MIDs of matrices/vectors saved can by consulted with the command '# inventory'\n")
                    continue



            # Shows the matrices saved in m_save.json
            if u[0] == 'inventory' and len(u) == 1:
                if len(data["matrices"]) == 0 :
                    raise ValueError('Current matrix base is empty')
                print('# Heres a list of the saved matrices as long as their Matrix Identification (MID)\n')
                for elements in data["matrices"] :
                    print(f'~ MID : {elements['MID']}')
                    ls.matrix(elements["matrix"]).print()
                continue
                    
            # Saves the most recently user inputted matrix
            if u[0] == 'save' and len(u) == 1:
                if len(M) == 0 :
                    raise ValueError('No matrix in memory')
                if M[-1].entries in data["matrices"] :
                    raise ValueError('Matrix already saved in matrix base')
                save_matrix(M[-1], data)
                save_matrix_base('m_save.json', data)
                print('# Most recent inputted matrix saved to matrix base')
                continue
                
            # Clears the matrix base
            if u[0] == 'clear' and len(u) == 1 :
                if len(data['matrices']) == 0 :
                    raise ValueError('Matrix base already empty')
                data['matrices'].clear()
                save_matrix_base('m_save.json', data)
                print('# Matrix base cleared')
                continue
            
            # Matrix / Vector builder
            if u[0] == 'build' and len(u) == 1 :
                print('# Welcome to matrix builder - Will automatically save')
                dimensions_input = input("# Input the dimensions of the matrix as such \'nxm\' : ")
                n, m = map(int, dimensions_input.split("x"))
                M = setMatrix(n,m)
                save_matrix(M, data)
                save_matrix_base('m_save.json', data)
                if m == 1 :
                    print('\n# Vector saved successfully')
                else :
                    print('\n# Matrix saved successfully')
                continue
                
            # Calculating the determinant of a square matrix
            if u[0] == 'determinant':
                if len(u) == 3 and u[1] == 'load' and type(int(u[2])) == int :
                    if len(data["matrices"]) == 0 or int(u[2]) >= len(data['matrices']) :
                        raise ValueError('This MID does not exist')
                    if len(MID_lookup(int(u[2]), data).entries[0]) != len(MID_lookup(int(u[2]), data).entries) :
                        raise ValueError('Cannot compute the determinant of non-square matrices')
                    print(f'# The determinant of matrix MID:{u[2]} is {MID_lookup(int(u[2]), data).determinant()}')
                    continue
                elif len(u) == 1 :
                    n = int(input('# Input square matrix size \'nxn\' : n = '))
                    M.append(setMatrix(n, n))
                    print(f'\n# The determinant of this matrix is {M[-1].determinant()}')
                    continue
                
            # Finding the inverse of a square matrix
            if u[0] == 'inverse' :
                if len(u) == 3 and u[1] == 'load' and type(int(u[2])) == int :
                    if len(data["matrices"]) == 0 or int(u[2]) >= len(data['matrices']) :
                        raise ValueError('This MID does not exist')
                    if len(MID_lookup(int(u[2]), data).entries[0]) != len(MID_lookup(int(u[2]), data).entries) :
                        raise ValueError('Cannot compute the inverse of non-square matrices')
                    print(f'# The inverse of matrix MID:{u[2]} is :')
                    MID_lookup(int(u[2]), data).inverse().print()
                    continue
                elif len(u) == 1 :
                    n = int(input('# Input square matrix size \'nxn\' : n = '))
                    M.append(setMatrix(n, n))
                    if M[-1].determinant() != 0 :
                        print('\n# The inverse of the following matrix is :')
                    M[-1].inverse().print()
                    continue
                
            # Calculating the matrix product of two matrices
            if u[0] == 'multiply' :
                if len(u) == 4 and u[1] == 'load' and type(int(u[2])) == int and type(int(u[3])) == int :
                    if len(data["matrices"]) == 0 or int(u[2]) >= len(data['matrices']) or  int(u[3]) >= len(data['matrices']) :
                        raise ValueError('One or more MIDs do not exist')
                    if len(MID_lookup(int(u[2]),data).entries[0]) != len(MID_lookup(int(u[3]),data).entries) :
                        raise ValueError(f'These two matrices aren\'t multiplicable | len(MID:{u[2]}[0]) != len(MID:{u[3]})')
                    print(f'# The matrix product of matrices MID:{u[2]} and MID:{u[3]} is :')
                    MID_lookup(int(u[2]),data).multiply(MID_lookup(int(u[3]),data)).print()
                    continue
                if len(u) == 1 :
                    dimensions_input = input("\n# Input the dimensions of the first matrix as such \'nxm\' : ")
                    n1, m1 = map(int, dimensions_input.split("x"))
                    M.append(setMatrix(n1,m1))
                    dimensions_input = input("\n# Input the dimensions of the second matrix as such \'nxm\' : ")
                    n2, m2 = map(int, dimensions_input.split("x"))
                    M.append(setMatrix(n2,m2))
                    if m1 == n2 :
                        print(f'\n# The result of the matrix product is : ')
                    M[-2].multiply(M[-1]).print()
                    continue
                
            # Finding the reduced echelon form of a matrix
            if u[0] == 'reduce' :
                if len(u) == 3 and u[1] == 'load' and type(int(u[2])) == int :
                    if len(data["matrices"]) == 0 or int(u[2]) >= len(data['matrices']) :
                        raise ValueError('This MID does not exist')
                    print(f'# The reduced row echelon form of matrix MID:{u[2]} is :')
                    MID_lookup(int(u[2]), data).reduce().print()
                    continue
                elif len(u) == 1 :
                    dimensions_input = input("# Input the dimensions of matrix as such \'nxm\' : ")
                    n, m = map(int, dimensions_input.split("x"))
                    M.append(setMatrix(n,m))
                    M[-1].reduce()
                    print('\n# Here is the reduced echelon form of the matrix')
                    M[-1].print()
                    continue
                
            # Calculates cross product of two vectors 
            if u[0] == 'cross' :
                if len(u) == 4 and u[1] == 'load' and type(int(u[2])) == int and type(int(u[3])) == int :
                    if len(data["matrices"]) == 0 or int(u[2]) >= len(data['matrices']) or  int(u[3]) >= len(data['matrices']) :
                        raise ValueError('One or more MIDs do not exist')
                    if len(MID_lookup(int(u[2]), data).entries[0]) > 1 | len(MID_lookup(int(u[3]), data).entries[0]) > 1 | len(MID_lookup(int(u[2]), data).entries) > 3 | len(MID_lookup(int(u[3]), data).entries) > 3 :
                        raise ValueError('One or more matrix selected is not a 3-dimensional vector')
                    print(f'# The cross product of the vectors MID:{u[2]} and MID:{u[3]} is :')
                    MID_lookup(int(u[2]), data).cross(MID_lookup(int(u[3]), data)).print()
                    continue
                if len(u) == 1 :
                    print("\n# V1")
                    M.append(setVector(3))
                    print("\n# V2")
                    M.append(setVector(3))
                    print("\n# The cross product V1xV2 is :")
                    M[-2].cross(M[-1]).print()
                    continue
                
            # Calculates the dot product of two n-dimensional vectors 
            if u[0] == 'dot' :
                if len(u) == 4 and u[1] == 'load' and type(int(u[2])) == int and type(int(u[3])) == int :
                    if len(data["matrices"]) == 0 or int(u[2]) >= len(data['matrices']) or  int(u[3]) >= len(data['matrices']) :
                        raise ValueError('This MID does not exist')
                    if len(MID_lookup(int(u[2]), data).entries[0]) > 1 | len(MID_lookup(int(u[3]), data).entries[0]) > 1 | len(MID_lookup(int(u[2]), data).entries) != len(MID_lookup(int(u[3]), data).entries) > 3 :
                        raise ValueError('One or more matrix selected isn\'t a vector or the vectors selected don\'t share the same length')
                    print(f'# The dot product of the vectors MID:{u[2]} and MID:{u[3]} is : {MID_lookup(int(u[2]), data).dot(MID_lookup(int(u[3]), data))}')
                    continue
                if len(u) == 1 :   
                    l = int(input("# Insert vector length : l = "))
                    for i in range(2) :
                        print(f'\n# V{i+1}')
                        M.append(setVector(l))
                    print(f'\n# The dot product of V1 and V2 is {M[-2].dot(M[-1])}')
                    continue
                
            # Finds the orthogonal projection of a vector onto another
            if u[0] == 'projection' :
                if len(u) == 4 and u[1] == 'load' and type(int(u[2])) == int and type(int(u[3])) == int :
                    if len(data["matrices"]) == 0 or int(u[2]) >= len(data['matrices']) or  int(u[3]) >= len(data['matrices']) :
                        raise ValueError('This MID does not exist')
                    if len(MID_lookup(int(u[2]), data).entries[0]) > 1 | len(MID_lookup(int(u[3]), data).entries[0]) > 1 | len(MID_lookup(int(u[2]), data).entries) != len(MID_lookup(int(u[3]), data).entries) > 3 :
                        raise ValueError('One or more matrix selected isn\'t a vector or the vectors selected don\'t share the same length')
                    MID_lookup(int(u[2]), data).projection(MID_lookup(int(u[3]), data)).print()
                    continue
                if len(u) == 1 :
                    l = int(input("# Insert vector length : l = "))
                    for i in range(2) :
                        print(f'\n# V{i+1}')
                        M.append(setVector(l))
                    print(f'\n# The orthogonaol projection of V1 onto V2 is : ')
                    M[-2].projection(M[-1]).print()
                    continue
                
            # Parse and evalutate user inputted equation
            if u[0] == 'parse' and len(u) == 1 :
                expression = input(f'# Insert the expression you want to evaluate : ') 
                result = parse_and_evaluate(expression)
                print(f'# The results of {expression} is {result}')
                
            # Print error message if command does not exist
            else:
                raise ValueError('Command does not exist')
        except ValueError as e:
            print(f'# ERROR : {e}')
        except Exception as e:
            print(f'# UNEXPECTED ERROR : {e}')  # Catch unexpected errors
            
# Main loop
if __name__ == "__main__" :
    run_LinearSpace()