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
# Function to set up an N amount of matrices from user input
def mSetup(n, M, s) :
    M.clear()
    if s == False : 
        for i in range(n) :
            print(f'\n>> Insert the size of the matrix [M{i+1}] to be evaluated | height (H) x length (L) : ')
            H = int(input("   H = "))
            L = int(input("   L = "))
            print("\n>> Insert the values of the entries by row, with each value seperated by a space\n   respecting this form :\n\n   a11 a12 ... a1n\n   a21 a22 ... a2n\n   am1 am2 ... amn\n")
            m = setMatrix(H,L)
            M.append(m)
    if s == True :
        for j in range(n) :
            print(f'\n>> Insert the size of the square matrix [M{j+1}] to be evaluated | height (N) x length (N) : ')
            N = int(input("   N = "))
            print("\n>> Insert the values of the entries by row, with each value seperated by a space\n   respecting this form :\n\n   a11 a12 ... a1n\n   a21 a22 ... a2n\n   am1 am2 ... amn\n")
            m = setMatrix(N,N)
            M.append(m)
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
    m3 = [[0]*len(m1) for _ in range(len(m2[0]))]

    for i in range(len(m1)) :
        for j in range(len(m2[0])) :
            sum = 0
            for k in range(len(m1[0])) :
                sum += m1[i][k]*m2[k][j]
            m3[i][j] = sum
    return m3
# Function finding the determinant of a matrix by reducing it to upper triangular form 
def getDeterminant(m,s) :
    det = 1.0 ; permuations = 0
    if len(m) != len(m[0]) :
        raise ValueError("Determinants can only be calculated in square matrices")
    k = []
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
        print(f' = {det}', end = "")
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
    if getDeterminant(m, False) == 0 :
        raise ValueError("This matrix is not invertible | |M| = 0")
    d = getDeterminant(m, False)
    m = list(map(list, zip(*mCofactor(m))))
    a = [[element * (1/d) for element in row] for row in m]
    return a
# app
try :
    """
    M = [[0,0,0],[1,0,1],[4,32,1]]

    getDeterminant(M, True)
    """
    M = []  
    key = True
    while key : 
        ans = input("\nU: ")
        if ans == "/help" :
            print("\n   Here is a list of all the currently supported commands : \n")
            print("   >> Matrix Operations : \n")
            print("      /determinant      ~ Calculating the determinant of a square matrix")
            print("      /cofactor         ~ Finding the cofactor matrix of a square matrix")
            print("      /inverse          ~ Invert a matrix if invertible")
            print("      /multiply         ~ Multiply two matrix of legal sizes")
        if ans == "/determinant" :
            mSetup(1,M,True)
            getDeterminant(M[0], True)
        if ans == "/multiply" :
            mSetup(2, M, False)
            print(f'\n   The resultant matrix of M1xM2 is : ')
            mPrint(mMultiply(M[0],M[1]))
        if ans == "/cofactor" :
            mSetup(1,M,True)
            print(f'\n   The cofactor matrix of M1 is : ')
            mPrint(mCofactor(M[0]))
        if ans == "/inverse" :
            mSetup(1,M,True)
            print("\n   The inverse of matrix M1 is : ")
            mPrint(mInverse(M[0]))
        #"""
except ValueError as e :
      print(f'\n>> ERROR : {e}\n')