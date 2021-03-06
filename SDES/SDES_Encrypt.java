//Author Robert Tunn 2021

public class SDES_Encrypt {
    
    public static void main (String args[]) {
    
        //Start by entering secret key and plaintext
        int key [] = {0,1,1,1,1,1,1,1,1,0};
        System.out.println("The key is:");
        for (int i = 0; i < key.length; i++) {
            System.out.print(key[i]);
        }
        System.out.println();
        int [] plaintext = {1,0,1,1,0,0,1,0};
        System.out.println("The plaintext is:");
        for (int i = 0; i < plaintext.length; i++) {
            System.out.print(plaintext[i]);
        }        
        System.out.println();
        
        //CREATE P10 PERMUTATION
        int P10 [] = SDES_Methods.Create_P10(key);
        //Display P10 permutation
        System.out.println("The P10 permutation is:");
        for (int i = 0; i < P10.length; i++) {
            System.out.print(P10[i]);
        }
        
        System.out.println(); //Blank line
        
        //CREATE FIRST LEFT SHIFT
        int [] LS1 = SDES_Methods.Create_LS1(P10);
        //Display LS1 permutation
        //System.out.println("The LS1 permutation is:");
        //for (int i = 0; i < LS1.length; i++) {
        //    System.out.print(LS1[i]);
        //}
        
        //System.out.println();
        
        //CREATE K1 PERMUTATION
        int [] K1 = SDES_Methods.Create_K1(LS1);
        //Display K1 permutation
        System.out.println("The K1 permutation is:");
        for (int i = 0; i < K1.length; i++) {
            System.out.print(K1[i]);
        }        
        
        System.out.println();
        
        //CREATE SECOND LEFT SHIFT
        int [] LS2 = SDES_Methods.Create_LS2(LS1);
        //Display LS2 permutation
        //System.out.println("The LS2 permutation is:");
        //for (int i = 0; i < LS2.length; i++) {
        //    System.out.print(LS2[i]);
        //}
        
        //System.out.println();
        
        //CREATE K2 PERMUTATION
        int [] K2 = SDES_Methods.Create_K2(LS2);
        //Display K2 permutation
        System.out.println("The K2 permutation is:");
        for (int i = 0; i < K2.length; i++) {
            System.out.print(K2[i]);
        }
        
        System.out.println();
        
        //ENCRYPTION
        //ROUND 1
        
        //CREATE INITIAL PERMUTATION (IP)
        int IP [] = SDES_Methods.Create_IP(plaintext);
        //Display IP permutation
        System.out.println("The initial permutation IP is:");
        for (int i = 0; i < IP.length; i++) {
            System.out.print(IP[i]);
        }
        
        System.out.println();

        //CREATE EP (EXPAND AND PERMUTATE)
        int EP [] = SDES_Methods.Create_EP(IP);
        //Display EP permutation
        System.out.println("The EP permutation is:");
        for (int i = 0; i < EP.length; i++) {
            System.out.print(EP[i]);
        }
        
        System.out.println();
        
        //PERFORM BITWISE ADDITION 
        int bitwise1 [] = SDES_Methods.Bitwise_Add(K1, EP);
        //Display bitwise1 permutation
        System.out.println("The bitwise1 permutation is:");
        for (int i = 0; i < bitwise1.length; i++) {
            System.out.print(bitwise1[i]);
        }
              
        System.out.println();
        
        //DETERMINE COORDINATES FOR S0 MATRIX
        int [] SO_Coords = SDES_Methods.S0_Coordinates(bitwise1);
        //Display returned coordinates for S0 matrix
        System.out.println("The relevant element of the S0 matrix is: " 
                + "("+SO_Coords[0]+","+SO_Coords[1]+")");
        
        //DETERMINE COORDINATES FOR S1 MATRIX
        int [] S1_Coords = SDES_Methods.S1_Coordinates(bitwise1);
        //Display returned coordinates for S1 matrix
        System.out.println("The relevant element of the S1 matrix is: " 
                + "("+S1_Coords[0]+","+S1_Coords[1]+")");        
        
        //EXTRACT ELEMENT FROM S0 MATRIX
        String S0_Element = SDES_Methods.S0_Element(SO_Coords);
        //Display first extracted element from S0 matrix
        System.out.println(S0_Element + " has been extracted from the S0 "
                + "matrix");

        //EXTRACT SECOND ELEMENT FROM S1 MATRIX
        String S1_Element = SDES_Methods.S1_Element(S1_Coords);
        //Display second extracted element from S1 matrix
        System.out.println(S1_Element + " has been extracted from the S1 "
                + "matrix");

        //CONCATENATE TWO EXTRACTED ELEMENT FOR PRE-P4 INPUT
        String preP4 = SDES_Methods.Concatenate_2(S0_Element, S1_Element);
        //Display input for P4 permutation as a string
        //System.out.println(preP4 + " will be used as input for the P4 "
        //        + "permutation");
        
        //CONVERT PREP4 FROM STRING TO ARRAY OF INTEGERS AND APPLY 
        //P4 PERMUTATION
        int [] P4 = SDES_Methods.StringtoIntArray(preP4);
        //Display converted string
        System.out.println("The P4 permutation is:");
        for (int i = 0; i < P4.length; i ++) {
            System.out.print(P4[i]);
        }
        
        //System.out.println();
        
        //CREATE SUBARRAY OF IP[0]-[3]
        int [] subArrayIPLeft = SDES_Methods.FirstFour(IP);
        //System.out.println("The first four digits of IP are:");
        //for (int i = 0; i < subArrayIPLeft.length; i++) {
        //    System.out.print(subArrayIPLeft[i]);
        //}
        
        System.out.println();
        
        //PERFORM BITWISE ADDITION ON [0]-[3] OF IP AND ALL OF P4
        int [] bitwise2 = SDES_Methods.Bitwise_Add(subArrayIPLeft, P4);
        System.out.println("The bitwise2 permutation is:");
        for (int i = 0; i < bitwise2.length; i++) {
            System.out.print(bitwise2[i]);
        }
        
        //CREATE SUBARRAY OF IP[4]-[7]
        int [] subArrayIPRight = SDES_Methods.LastFour(IP);
        //System.out.println();
        //System.out.println("IP RHS is:");
        //for (int i = 0; i < subArrayIPRight.length; i++) {
        //    System.out.print(subArrayIPRight[i]);
        //}
        
        System.out.println();
        
        //SWITCH THE FIRST AND LAST FOUR DIGITS OF BITWISE 2 AND IP RHS AROUND
        int[] SW = SDES_Methods.Switch(bitwise2,subArrayIPRight);
        System.out.println("The switched digits SW are: ");
        for (int i = 0; i < SW.length; i++) {
            System.out.print(SW[i]);
        }
    
        //ENCRYPTION
        //ROUND 2
        
        System.out.println();
        
        //CREATE EP2 (SECOND EXPAND AND PERMUTATE)
        int EP2 [] = SDES_Methods.Create_EP(SW);
        //Display EP2 permutation
        System.out.println("The second round EP permutation is:");
        for (int i = 0; i < EP.length; i++) {
            System.out.print(EP2[i]);
        }
        
        System.out.println();
        
        //PERFORM BITWISE ADDITION ON EP2 AND K2
        int bitwise3 [] = SDES_Methods.Bitwise_Add(K2, EP2);
        //Display bitwise3 permutation
        System.out.println("The bitwise3 permutation is:");
        for (int i = 0; i < bitwise3.length; i++) {
            System.out.print(bitwise3[i]);
        }
              
        System.out.println();
        
        //DETERMINE COORDINATES FOR S0 MATRIX
        int [] SO_Coords2 = SDES_Methods.S0_Coordinates(bitwise3);
        //Display returned coordinates for S0 matrix
        System.out.println("The relevant element of the S0 matrix for the "
                + "second round is: " 
                + "("+SO_Coords2[0]+","+SO_Coords2[1]+")");
        
        //DETERMINE COORDINATES FOR S1 MATRIX
        int [] S1_Coords2 = SDES_Methods.S1_Coordinates(bitwise3);
        //Display returned coordinates for S1 matrix
        System.out.println("The relevant element of the S1 matrix for the "
                + "second round is: " 
                + "("+S1_Coords2[0]+","+S1_Coords2[1]+")");        
        
        //EXTRACT ELEMENT FROM S0 MATRIX
        String S0_Element2 = SDES_Methods.S0_Element(SO_Coords2);
        //Display first extracted element from S0 matrix
        System.out.println(S0_Element2 + " has been extracted from the S0 "
                + "matrix");

        //EXTRACT SECOND ELEMENT FROM S0 MATRIX
        String S1_Element2 = SDES_Methods.S1_Element(S1_Coords2);
        //Display second extracted element from S1 matrix
        System.out.println(S1_Element2 + " has been extracted from the S1 "
                + "matrix");

        //CONCATENATE TWO EXTRACTED ELEMENTS FOR PRE-P4 INPUT
        String preP42 = SDES_Methods.Concatenate_2(S0_Element2, S1_Element2);
        //Display input for P4R2 permutation as a string
        //System.out.println(preP42 + " will be used as input for the P42 "
        //        + "permutation");
        
        //CONVERT PRE4R2 FROM STRING TO ARRAY OF INTEGERS AND APPLY 
        //P4 PERMUTATION
        int [] P42 = SDES_Methods.StringtoIntArray(preP42);
        //Display converted string
        System.out.println("The second round P4 permutation is: ");
        for (int i = 0; i < P42.length; i ++) {
            System.out.print(P42[i]);
        }
        
        //System.out.println();
        
        //CREATE SUBARRAY OF SW[0]-[3]
        int [] subArraySWLeft = SDES_Methods.FirstFour(SW);
        //System.out.println("The first four digits of SW are:");
        //for (int i = 0; i < subArraySWLeft.length; i ++) {
        //    System.out.print(subArraySWLeft[i]);
        //}
        
        System.out.println();
        
        //PERFORM BITWISE ADDITION ON FIRST FOUR OF SW AND P42
        int [] bitwise4 = SDES_Methods.Bitwise_Add(subArraySWLeft, P42);
        System.out.println("The bitwise4 permutation is:");
        for (int i = 0; i < bitwise4.length; i++) {
            System.out.print(bitwise4[i]);
        }
        
        //System.out.println();
        
        //CREATE SUBARRAY OF SW[4]-[7]
        int [] subArrayIPRight2 = SDES_Methods.LastFour(SW);
        //System.out.println();
        //System.out.println("IP RHS is:");
        //for (int i = 0; i < subArrayIPRight2.length; i++) {
        //    System.out.print(subArrayIPRight2[i]);
        //}
        
        //System.out.println();
        
        //CREATE PREIP_1 (INPUT FOR INVERSE PERMUTATION IP_1)
        //Array will be returned in order of input entry
        int[] PreIP_1 = SDES_Methods.Four_Plus_Four(bitwise4,subArrayIPRight2);
        //System.out.println("The input for the inverse permutation is: ");
        //for (int i = 0; i < PreIP_1.length; i++) {
        //    System.out.print(PreIP_1[i]);
        //}
        
        System.out.println();
        
        //CREATE INVERSE PERMUTATION IP AND OUTPUT AS CIPHERTEXT
        int [] ciphertext = SDES_Methods.Create_IP_1(PreIP_1);
        System.out.println("The ciphertext is:");
        for (int i = 0; i < ciphertext.length; i++) {
            System.out.print(ciphertext[i]);
        }
    }
}
