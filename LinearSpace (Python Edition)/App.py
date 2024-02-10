# Function setting any n*m matrix based on user input
def setMatrix(h,l) :
    m = [[0]*l for _ in range(h)]; a = []
    for i in range(h) :
            print("   ", end="")
            ans = input()
            a += ans.split(' ')
            if len(a) != l :
                raise ValueError("Make sure the amount of entries is consistent with the matrix size")
            m[i] = [float(value) for value in a]
            a.clear()
    return m
# Function to set vectors -- Itteration of the set matrix function where rows can only have a length of 1
def setVector(l) :
    v = [[0]*1 for _ in range(l)]; a = []
    for i in range(l) :
            print("   ", end="")
            ans = input()
            a += ans.split(' ')
            if len(a) != 1 :
                raise ValueError("Make sure the amount of entries is consistent with the matrix size")
            v[i] = [float(value) for value in a]
            a.clear()
    return v
# Function to set up an N amount of matrices from user input
def mSetup(n, M, s) :
    M.clear()
    if s == 0 : 
        for i in range(n) :
            print(f'\n:: Matrix M{i+1} ::')
            ans = input("\n>> To create a new matrix, insert '/new' | To load a saved matrix, insert '/load'\n\n   ")
            if ans == "/load" :
                MID = int(input("\n   Insert the matrix MID : "))
                M.append(saveLoad[MID])
            if ans == "/new" :
                print(f'\n>> Insert the size of the matrix [M{i+1}] to be evaluated | height (H) x length (L) : ')
                H = int(input("   H = "))
                L = int(input("   L = "))
                print("\n>> Insert the values of the entries by row, with each value seperated by a space\n   respecting this form :\n\n   a11 a12 ... a1n\n   a21 a22 ... a2n\n   am1 am2 ... amn\n")
                m = setMatrix(H,L)
                M.append(m)
    if s == 1 :
        for j in range(n) :
            print(f'\n:: Matrix M{j+1} ::')
            ans = input("\n>> To create a new matrix, insert '/new' | To load a saved matrix, insert '/load'\n\n   ")
            if ans == "/load" :
                MID = int(input("\n   Insert the matrix MID : "))
                M.append(saveLoad[MID])
            if ans == "/new" :
                print(f'\n>> Insert the size of the square matrix [M{j+1}] to be evaluated | height (N) x length (N) : ')
                N = int(input("   N = "))
                print("\n>> Insert the values of the entries by row, with each value seperated by a space\n   respecting this form :\n\n   a11 a12 ... a1n\n   a21 a22 ... a2n\n   am1 am2 ... amn\n")
                m = setMatrix(N,N)
                M.append(m)
    if s == 2 :
        for k in range(n) :
            print(f'\n:: Matrix A ::')
            ans = input("\n>> To create a new matrix, insert '/new' | To load a saved matrix, insert '/load'\n\n   ")
            if ans == "/load" :
                MID = int(input("\n   Insert the matrix MID : "))
                M.append(saveLoad[MID])
            if ans == "/new" :
                print(f'\n>> Insert the size of the square coefficient matrix \'A\' to be evaluated | height (N) x length (N) : ')
                N = int(input("   N = "))
                print("\n>> Insert the values of the entries by row, with each value seperated by a space\n   respecting this form :\n\n   a11 a12 ... a1n\n   a21 a22 ... a2n\n   am1 am2 ... amn\n")
                m = setMatrix(N,N)
                M.append(m)
# Function to set up an N amount of vectors from user input
def vSetup(n, V, length, s) :
    M.clear()
    if s == 0 :
        for i in range(n) :
                print(f'\n>> Insert the components of V{i+1} with each component written on different rows like such : \n\n   v{i+1}1\n   v{i+1}2\n   ...\n   v{i+1}n\n')
                v = setVector(length)
                M.append(v)
    if s == 1 :
        for j in range(n) :
            print(f'\n:: Vector \'b\' ::')
            ans = input("\n>> To create a new vector, insert '/new' | To load a saved vector, insert '/load'\n\n   ")
            if ans == "/load" :
                MID = int(input("\n   Insert the vector MID : "))
                M.append(saveLoad[MID])
            if ans == "/new" :
                print(f'\n>> Insert the components of constant vector \'b\' with each component written on different rows like such : \n\n   b{j+1}1\n   b{j+1}2\n   ...\n   b{j+1}n\n')
                v = setVector(length)
                M.append(v)
    if s == 2 : 
        x = int(input("\n>> Insert the length of the vectors V1 and V2 : \n   N = "))
        for j in range(2) : 
            print(f'\n>> Insert the components of constant vector V{j+1} with each component written on different rows like such : \n\n   v{j+1}1\n   v{j+1}2\n   ...\n   v{j+1}n\n')
            v = setVector(x)
            M.append(v)
# Function printing matrix in with alligned columns
def mPrint(m) :
    print()
    k = []
    maxlength = 0
    # Identifying the number with the largest amount of characters in each column
    for a in range(len(m[0])) :
        for b in range(len(m)) :
            length = len(list(f"{m[b][a]:.2f}"))
            if length > maxlength :
                maxlength = length
                if m[b][a] < 0 :
                    maxlength -= 1
            length = 0
        k.append(maxlength)
        maxlength = 0
    # Printing the matrix in determinant form with alligned columns
    for i in range(len(m)) :
        print("  ", end="")
        for j in range(len(m[0])) :
            if m[i][j] < 0 :
                print("", end = "")
                print(f'{"{:.2f}".format(m[i][j])}', end = " ")
                for a in range(k[j] - len(list(f"{m[i][j]:.2f}")) + 1) :
                    print(" ", end = "")
            else :
                print(" ", end = "")
                print(f'{"{:.2f}".format(m[i][j])}', end = " ")
                for a in range(k[j] - len(list(f"{m[i][j]:.2f}"))) :
                    print(" ", end = "")      
        print()
# Function printing matrix in determinant form
def dPrint(m) :
    print()
    k = []
    maxlength = 0
    # Identifying the number with the largest amount of characters in each column
    for a in range(len(m[0])) :
        for b in range(len(m)) :
            length = len(list(f"{m[b][a]:.2f}"))
            if length > maxlength :
                maxlength = length
                if(m[b][a] < 0) :
                    maxlength -= 1
            length = 0
        k.append(maxlength)
        maxlength = 0
    # Printing the matrix in determinant form with alligned columns
    for i in range(len(m)) :
        print("   |", end="")
        for j in range(len(m[0])) :
            if m[i][j] < 0 :
                print("", end = "")
                print(f'{"{:.2f}".format(m[i][j])}', end = " ")
                for a in range(k[j] - len(list(f"{m[i][j]:.2f}"))+1) :
                    print(" ", end = "")
            else :
                print(" ", end = "")
                print(f'{"{:.2f}".format(m[i][j])}', end = " ")
                for a in range(k[j] - len(list(f"{m[i][j]:.2f}"))) :
                    print(" ", end = "")      
        print("|", end="")
        print()
# Function performing matrix multiplication between two matrices M1 and M2
def mMultiply(m1, m2) :
    if len(m1[0]) != len(m2) :
        raise ValueError("These two matrices aren't multiplicable | len(M1[0]) != len(M2)")
    m3 = [[0]*len(m2[0]) for _ in range(len(m1))]

    for i in range(len(m1)) :
        for j in range(len(m2[0])) :
            sum = 0
            for k in range(len(m1[0])) :
                sum += m1[i][k]*m2[k][j]
            m3[i][j] = sum
    return m3
# Function finding the determinant of a matrix by reducing it to upper triangular form 
def getDeterminant(a,s) :
    det = 1.0 ; permuations = 0 ; m = [[0]*len(a) for _ in range(len(a))]
    for u in range(len(a)) :
        for v in range(len(a)) :
            m[u][v] = a[u][v]
    if len(m) != len(m[0]) :
        raise ValueError("Determinants can only be calculated in square matrices")
    k = []
    if s == True : 
        print("\n>> Here is the inputted matrix : ")
        mPrint(m)
    if needsSort(m) :
        S = m
        m = mSort(m)
        permuations += nPermutations(S, m)
        if s == True :
           print(f'\n   Sorted the matrix')
           dPrint(m)
    for i in range(len(m)) :
        for j in range(i, len(m)) :
            if m[i][j] != 0 :
                for a in range(i+1, len(m)) :
                    if m[a][j] != 0 :
                        k.append(m[i][j]/m[a][j])
                        for b in range(len(m)) :
                            m[a][b] *= k[len(k) - 1]
                            if float(f"{m[a][b]:.9f}") == -0.0 :
                                m[a][b] = 0
                        if s == True :
                           print(f'\n   {k[len(k) - 1]} * R{a+1}')
                           dPrint(m)
                        for c in range(len(m)) :
                            m[a][c] -= m[i][c]
                            if float(f"{m[a][c]:.9f}") == -0.0 :
                                m[a][c] = 0
                        if s == True :
                           print(f'\n   R{a+1} - R{i+1} --> R{a+1}')
                           dPrint(m)
            if needsSort(m) :
                S = m
                m = mSort(m)
                permuations += nPermutations(S,m)
                if s == True :
                   print("\n   Sorted the matrix")
                   dPrint(m)    
            break
    if s == True :
       print("\n   |M| = ", end="")
    for x in range(len(k)) :
        det *= 1/k[x]
        if s == True :
            if k[x] != 1 :
                print(f'({1/k[x]})', end = "")
    for y in range(len(m[0])) :
        det *= m[y][y]
        if s == True :
            print(f'({"{:.2f}".format(m[y][y])})', end = "")
    det *= (-1)**permuations
    if (-1)**permuations < 0 :
        if s == True :
            print(f'(-1.00)',end = "")
    if s == True :
        print(f' = {det}\n', end = "")
    return det
# Function to calculate the amount of permuations done on a matrix based on its initial and final state
def nPermutations(I,F) :
    k = 0
    for i in range(len(I[0])) :
        if I[i] != F[i] :
            for j in range(i+1, len(I[0])) :
                if I[j] == F[i] :
                    k += 1
                    break
    return k
# Function to check if a matrix needs to be sorted 
def needsSort(m) :
    s = mSort(m)
    if s == m :
        return False
    else :
        return True
# Function outputing the cofactor matrix of a square matrix
def mCofactor(m) :
    M = [[0]*(len(m)) for _ in range(len(m))]
    subM = [[0]*(len(m)-1) for _ in range(len(m)-1)]
    mStore = []
    a = 0; b = 0; c = 0
    for i in range(len(m)) :
        for j in range(len(m)) :
            for k in range(len(m)) :
                for l in range(len(m)) :
                    if k != i and l != j :
                        subM[a][b] = m[k][l]
                        b+=1
                        if b == len(m) - 1 :
                            a += 1 ; b = 0
                        if a == len(m) - 1 :
                            a = 0 ; b = 0
            mStore.append(subM)
            M[i][j] = (-1)**(i+j)*getDeterminant(mStore[c], False)
            if M[i][j] == -0 :
                M[i][j] = 0
        c += 1
    return M
# Function to store rows of a matrix in ascending order of leading zeros
def mSort(m):
    def count_leading_zeros(row):
        count = 0
        for element in row:
            if element == 0:
                count += 1
            else:
                break
        return count
    sorted_matrix = sorted(m, key=count_leading_zeros)
    return sorted_matrix
# Function calculates the inverse matrix of an invertible matrix
def mInverse(m) :
    c = m
    d = getDeterminant(c, False)
    if d == 0 :
        raise ValueError("This matrix is not invertible | |M| = 0")
    m = mCofactor(m)
    m = [list(row) for row in zip(*m)]
    m = [[element * (1/d) for element in row] for row in m]
    return m
# Function to solve a linear system in matrix form
def findX(A, b) :
    AInverse = mInverse(A)
    X = mMultiply(AInverse, b)
    return X
# Function to calculate dot product of two N-dimensional vectors
def dotProduct(v1, v2) :
    if len(v1) != len(v2) :
        raise ValueError("These two vectors aren't of the same dimension | v1.length != v2.length")
    sum = 0
    for i in range(len(v1)) :
        sum += v1[i][0]*v2[i][0]
    return sum
# Function returning the cross product of two 3-dimensional vectors
def crossProduct(v1, v2) :
    if len(v1) != 3 or len(v2) != 3 :
        raise ValueError("Make sure both vectors are 3-dimensional")
    row_matrix_1 = [sublist[0] for sublist in v1]
    row_matrix_2 = [sublist[0] for sublist in v2]
    M = [[1,-1,1]] ; cList = [] ; C = [[0,0],[0,0]] ; v3 = [[0],[0],[0]]
    M.append(row_matrix_1) ; M.append(row_matrix_2)
    for h in range(3) :
        for i in range(3) :
            for j in range(3) :
                if i != 0 and j != h :
                    cList.append(M[i][j])
        c = 0
        for a in range(2) :
            for b in range(2) :
                C[a][b] = cList[c]
                c+=1
        v3[h][0] = M[0][h]*getDeterminant(C, False)
        if v3[h][0] == -0 :
            v3[h][0] = 0
        cList.clear()
    return v3
# Function to calculate the triple product of three vectors
def tripleProduct(v1, v2, v3) :
    if len(v1) != len(v2) != len(v3) != 3 :
        raise ValueError("All the evaluated vectors must be tridimensional")
    row_matrix_1 = [sublist[0] for sublist in v1]
    row_matrix_2 = [sublist[0] for sublist in v2]
    row_matrix_3 = [sublist[0] for sublist in v3]
    M = [] ; M.append(row_matrix_1) ; M.append(row_matrix_2) ; M.append(row_matrix_3)
    return getDeterminant(M,False)
# Function to find the orthogonal projection of v1 onto v2
def orthogonalProjection(v1, v2) :
    k = 0.0 ; a2 = 0.0
    if len(v1) != len(v2) :
        raise ValueError("Make sure both vectors are of the same dimension")
    v3 = [[0]*1 for _ in range(len(v1))] ; k = dotProduct(v1,v2)
    for i in range(len(v1)) : 
        a2 += v2[i][0]**2
    for j in range(len(v1)) :
        v3[j][0] = (k/a2)*v2[j][0]
    return v3
# app
try :
    M = [] ; V = [] ; saveLoad = {} ; saveable = False
    print("\n ::  Welcome to LinearSpace (Python Edition) ::  ")
    print("\n     The current build only supports matrix\n     operations -- vector operations coming soon ...\n\n     Type \'/help\' to get started")
    key = True
    while key : 
        ans = input("\nU: ")
        if ans == "/help" :
            print("\n   Here is a list of all the currently supported commands : \n")
            print("   >> Storage management : \n")
            print("      /save             ~ Saves most recent outputted matrix/vector to storage")
            print("      /load             ~ Load saved matrix by calling MID (Matrix ID)")
            print("      /clear            ~ Clears memory")
            print("      /inventory        ~ Ouputs all saved matrices/vectors with their respective MIDs")
            print("\n   >> Matrix Operations : \n")
            print("      /determinant      ~ Calculating the determinant of a square matrix")
            print("      /cofactor         ~ Finding the cofactor matrix of a square matrix")
            print("      /inverse          ~ Invert a matrix if invertible")
            print("      /multiply         ~ Multiply two matrix of legal sizes")
            print("      /findX            ~ Solve a linear system in AX = b form")
            print("\n   >> Vector Operations : \n")
            print("      /dotProduct       ~ Calculating the dot product of two N-dimensional vectors")
            print("      /crossProduct     ~ Calculating the cross product of two 3-dimensional vectors")
            print("      /tripleProduct    ~ Calculating the triple product of three 3-dimensional vectors")
            print("      /orthoProjection  ~ Finding the orthogonal projection of vector V1 onto vector V2")
        # Saves the most recent outputed matrix / vector
        if ans == "/save" :
            if saveable == True :
                saveLoad[len(saveLoad)] = M[len(M)-1]
                print(f'\n>> The matrix has been saved to memory | MID (Matrix ID) :{(len(saveLoad) - 1)}')
            else :
                print("\n>> This type isn't saveable")
        # Prints list of saved matrices / vectors
        if ans == "/inventory" :
            for i in range(len(saveLoad)) :
                print(f'\n   MID (Matrix ID) : {i}')
                mPrint(saveLoad[i])
        if ans == "/clear" :
            saveLoad.clear()
            print(f'\n>> Memory cleared')
        if ans == "/determinant" :
            mSetup(1,M,1)
            getDeterminant(M[0], True)
            saveable = False
        if ans == "/multiply" :
            mSetup(2, M, 0)
            print(f'\n   The resultant matrix of M1xM2 is : ')
            mPrint(mMultiply(M[0],M[1]))
            M.append(mMultiply(M[0],M[1]))
            saveable = True
        if ans == "/cofactor" :
            mSetup(1,M,1)
            print(f'\n   The cofactor matrix of M1 is : ')
            mPrint(mCofactor(M[0]))
            M.append(mCofactor(M[0]))
            saveable = True
        if ans == "/inverse" :
            mSetup(1,M,1)
            print("\n   The inverse of matrix M1 is : ")
            mPrint(mInverse(M[0]))
            M.append(mInverse(M[0]))
            saveable = True
        if ans == "/findX" :
            mSetup(1, M, 2)
            vSetup(1, V, len(M[0]),1)
            print("\n   The solution vector \'X\' of the linear system is : ")
            mPrint(findX(M[0], M[1]))
            M.append(findX(M[0], M[1]))
            saveable = True
        if ans == "/dotProduct" :
            vSetup(2,V,0,2)
            print(f'\n>> The dot product of V1 and V2 is : \n   V1*V2 = {dotProduct(M[0],M[1])}')
            saveable = False
        if ans == "/crossProduct" :
            vSetup(2,V,3,0)
            print("\n>>  The cross product of V1 and V2 is | V1 x V2 = ")
            mPrint(crossProduct(M[0],M[1]))
            saveable = True
        if ans == "/tripleProduct" :
            vSetup(3,V,3,0)
            print(f'\n>> The triple product of V1, V2 and V3 is : \n\n   V1*V2xV3 = {tripleProduct(M[0],M[1],M[2])}')
            saveable = False
        if ans == "/orthoProjection" :
            vSetup(2,V,0,2)
            print("\n>> The orthogonal projection of V1 onto V2 is : ")
            mPrint(orthogonalProjection(M[0],M[1]))
            saveable = True
except ValueError as e :
      print(f'\n>> ERROR : {e}\n')