import Linspace as ls
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
            a = ans.split(' ')
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
            u = input("# ")
            
            # Help center
            if u == 'help' :
                print("# Here's a table of currently supported commands\n")
                print("  history            Prints user entry history")        
                print("  determinant        Calculates determinant of square matrix")
                print("  mutliply           Performs matrix multiplication between two matrices")   
                print("  inverse            Finds inverse of a square matrix")                
                print("  reduce             Finds the reduced echelon form of a matrix")   
                print("  cross              Finds cross product of two 3-dimensional vectors")                
                print("  dot                Finds dot product of two n-dimensional vectors")                
                print("  projection         Finds orthogonal projection of a vector onto another\n")                                
                continue
            
            # Shows the matrices saved in m_save.json
            elif u == 'inventory' :
                if len(data["matrices"]) == 0 :
                    raise ValueError('Current matrix base is empty')
                print('# Heres a list of the saved matrices as long as their Matrix Identification (MID)\n')
                for elements in data["matrices"] :
                    print(f'~ MID : {elements['MID']}')
                    ls.matrix(elements["matrix"]).print()
                    
            # Saves the most recently user inputted matrix
            elif u == 'save' :
                save_matrix(M[-1], data)
                save_matrix_base('m_save.json', data)
                print('# Most recent inputted matrix saved to matrix base')
                
            # Clears the matrix base
            elif u == 'clear' :
                data['matrices'].clear()
                save_matrix_base('m_save.json', data)
                print('# Matrix base cleared')
                                        
            # Calculating the determinant of a square matrix
            elif u == 'determinant':
                n = int(input('# Input square matrix size \'nxn\' : n = '))
                M.append(setMatrix(n, n))
                print(f'\n# The determinant of this matrix is {M[-1].determinant()}')
                
            # Finding the inverse of a square matrix
            elif u == 'inverse' :
                n = int(input('# Input square matrix size \'nxn\' : n = '))
                M.append(setMatrix(n, n))
                if M[-1].determinant() != 0 :
                    print('\n# The inverse of the following matrix is :')
                M[-1].inverse().print()
                
            # Calculating the matrix product of two matrices
            elif u == 'multiply' :
                dimensions_input = input("\n# Input the dimensions of the first matrix as such \'nxm\' : ")
                n1, m1 = map(int, dimensions_input.split("x"))
                M.append(setMatrix(n1,m1))
                dimensions_input = input("\n# Input the dimensions of the second matrix as such \'nxm\' : ")
                n2, m2 = map(int, dimensions_input.split("x"))
                M.append(setMatrix(n2,m2))
                if m1 == n2 :
                    print(f'\n# The result of the matrix product is : ')
                M[-2].multiply(M[-1]).print()
                
            # Finding the reduced echelon form of a matrix
            elif u == 'reduce' :
                dimensions_input = input("# Input the dimensions of matrix as such \'nxm\' : ")
                n, m = map(int, dimensions_input.split("x"))
                M.append(setMatrix(n,m))
                M[-1].reduce()
                print('\n# Here is the reduced echelon form of the matrix')
                M[-1].print()
                
            # Calculates cross product of two vectors 
            elif u == 'cross' :
                print("\n# V1")
                M.append(setVector(3))
                print("\n# V2")
                M.append(setVector(3))
                print("\n# The cross product V1xV2 is :")
                M[-2].cross(M[-1]).print()
                
            # Calculates the dot product of two n-dimensional vectors 
            elif u == 'dot' :
                l = int(input("# Insert vector length : l = "))
                for i in range(2) :
                    print(f'\n# V{i+1}')
                    M.append(setVector(l))
                print(f'\n# The dot product of V1 and V2 is {M[-2].dot(M[-1])}')
                
            # Finds the orthogonal projection of a vector onto another
            elif u == 'projection' :
                l = int(input("# Insert vector length : l = "))
                for i in range(2) :
                    print(f'\n# V{i+1}')
                    M.append(setVector(l))
                print(f'\n# The orthogonaol projection of V1 onto V2 is : ')
                M[-2].projection(M[-1]).print()
                
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