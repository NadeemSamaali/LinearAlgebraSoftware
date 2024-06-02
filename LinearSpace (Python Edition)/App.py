import Linspace as ls
# Function converting user input into matrix datatype
def setMatrix(h,l) :
    # Format matrix guide whenever overall dimensionas exceeds 3x3
    if h > 3 and l > 3 :
        print(f"# Insert the entires of the M_{h}x{l} matrix row by row, with each value separated by a space\n  respecting the following form :\n\n  a11 a12 ... a1{l}\n  a21 a22 ... a2{l}\n  ...\n  a{h}1 a{h}2 ... a{h}{l}\n")
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

# Print welcome message
print("\n: Welcome to LinearSpace (Python Edition) :  ")
print("\n  The current build supports matrix operations\n  and vector operations. More features\n  coming soon ...\n\n  Designed by Nadeem Samaali\n\n  Type '# help' to get started\n")

# Set up matrix storage
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
            print("  inverse            Finds inverse of a square matrix")                
            print("  mutliply           Performs matrix multiplication between two matrices")   
            print("  cross              Finds cross product of two 3-dimensional vectors")                
            print("  dot                Finds dot product of two n-dimensional vectors")                
            print("  projection         Finds orthogonal projection of a vector onto another\n")                                
            continue
        # Print the user input"" history
        if u == 'history' :
            if len(M) == 0 :
                raise ValueError("History empty")
            for i in range(len(M)) :
                M[len(M)-1-i].print()
            continue
        # Calculating the determinant of a square matrix
        if u == 'determinant':
            n = int(input('# Input square matrix size \'nxn\' : n = '))
            M.append(setMatrix(n, n))
            print(f'\n# The determinant of this matrix is {M[-1].determinant()}')
            continue
        # Finds the inverse of a square matrix
        if u == 'inverse' :
            n = int(input('# Input square matrix size \'nxn\' : n = '))
            M.append(setMatrix(n, n))
            if M[-1].determinant() != 0 :
                print('\n# The inverse of the following matrix is :')
            M[-1].inverse().print()
            continue
        # Function calculating the matrix product of two matrices
        if u == 'multiply' :
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
        # Calculates cross product of two vectors 
        if u == 'cross' :
            print("\n# V1")
            M.append(setVector(3))
            print("\n# V2")
            M.append(setVector(3))
            print("\n# The cross product V1xV2 is :")
            M[-2].cross(M[-1]).print()
            continue
        # Calculates the dot product of two n-dimensional vectors 
        if u == 'dot' :
            l = int(input("# Insert vector length : l = "))
            for i in range(2) :
                print(f'\n# V{i+1}')
                M.append(setVector(l))
            print(f'\n# The dot product of V1 and V2 is {M[-2].dot(M[-1])}')
            continue
        # Finds the orthogonal projection of a vector onto another
        if u == 'projection' :
            l = int(input("# Insert vector length : l = "))
            for i in range(2) :
                print(f'\n# V{i+1}')
                M.append(setVector(l))
            print(f'\n# The orthogonaol projection of V1 onto V2 is : ')
            M[-2].projection(M[-1]).print()
            continue
        # Print error message if command does not exist
        else:
            raise ValueError('Command does not exist')
    except ValueError as e:
        print(f'# ERROR : {e}')
    except Exception as e:
        print(f'# UNEXPECTED ERROR : {e}')  # Catch unexpected errors
