//Author Robert Tunn, 2021

public class SDES_Methods {
    
    public static int [] Create_P10 (int [] input) {
    
        //Input key:    1 2 3 4 5 6  7 8 9 10
        //Output P10:   3 5 2 7 4 10 1 9 8 6
        
        int P10 [] = new int [10];
        
        P10[0] = input[2];
        P10[1] = input[4];
        P10[2] = input[1];
        P10[3] = input[6];
        P10[4] = input[3];
        P10[5] = input[9];
        P10[6] = input[0];
        P10[7] = input[8];
        P10[8] = input[7];
        P10[9] = input[5];
        
        return P10;
    }
    
    public static int [] Create_LS1 (int [] input) {
        
        //Take P10 permutation from previous method, split into two and
        //do a left shift wrap around of 1 position
        
        //Input P10:      1 2 3 4 5 6 7 8 9  10
        //Output P10:     2 3 4 5 1 7 8 9 10 6

        int [] LS1 = new int [10];
        
        LS1[0] = input[1];
        LS1[1] = input[2];
        LS1[2] = input[3];
        LS1[3] = input[4];
        LS1[4] = input[0];
        LS1[5] = input[6];
        LS1[6] = input[7];
        LS1[7] = input[8];
        LS1[8] = input[9];
        LS1[9] = input[5];    
        
        return LS1;
    }
    
    public static int [] Create_K1 (int[] input) {
    
        //Input P10:  1 2 3 4 5 6 7  8 9 10
        //Output K1:  6 3 7 4 8 5 10 9
    
        int [] K1 = new int [8];

        K1[0] = input[5];
        K1[1] = input[2];
        K1[2] = input[6];
        K1[3] = input[3];
        K1[4] = input[7];
        K1[5] = input[4];
        K1[6] = input[9];
        K1[7] = input[8];
        
        return K1;
    }
    
    public static int [] Create_LS2 (int [] input) {
        
        //Take LS1 permutation, split into two and
        //do a left shift wrap around of 2 positions
        
        //Input LS1:      1 2 3 4 5 6 7 8  9 10
        //Output LS2:     3 4 5 1 2 8 9 10 6 7

        int [] LS2 = new int [10];
        
        LS2[0] = input[2];
        LS2[1] = input[3];
        LS2[2] = input[4];
        LS2[3] = input[0];
        LS2[4] = input[1];
        LS2[5] = input[7];
        LS2[6] = input[8];
        LS2[7] = input[9];
        LS2[8] = input[5];
        LS2[9] = input[6];    
        
        return LS2;
    }
        
    public static int [] Create_K2 (int[] input) {
    
        //Input LS2:  1 2 3 4 5 6 7  8 9 10
        //Output K2:  6 3 7 4 8 5 10 9
    
        int [] K2 = new int [8];

        K2[0] = input[5];
        K2[1] = input[2];
        K2[2] = input[6];
        K2[3] = input[3];
        K2[4] = input[7];
        K2[5] = input[4];
        K2[6] = input[9];
        K2[7] = input[8];
        
        return K2;
    }

    public static int [] Create_IP (int [] input) {
        
        //Input plaintext:  1 2 3 4 5 6 7 8 
        //Output IP_1:      2 6 3 1 4 8 5 7
        
        int IP [] = new int [8];
        
        IP[0] = input[1];
        IP[1] = input[5];
        IP[2] = input[2];
        IP[3] = input[0];
        IP[4] = input[3];
        IP[5] = input[7];
        IP[6] = input[4];
        IP[7] = input[6];
        
        return IP;
    }
    
    public static int [] Create_EP (int [] input) {
    
        //Don't bother cutting array in half just extract what is needed
        //Input IP:     1 2 3 4 5 6 7 8
        //Output P10:   8 5 6 7 6 7 8 5
        
        int EP [] = new int [8];
        
        EP[0] = input[7];
        EP[1] = input[4];
        EP[2] = input[5];
        EP[3] = input[6];
        EP[4] = input[5];
        EP[5] = input[6];
        EP[6] = input[7];
        EP[7] = input[4];
        
        return EP;
    }
    
    public static int[] Bitwise_Add (int[] input1, int[] input2) {
        
        //Providing arrays are of equal length, the correspondingn elements
        //will be added in a bitwise manner
        
        int [] array1 = input1;
        int [] array2 = input2;

        for (int i = 0; i < input1.length; i++) {
            
            array2[i] = array1[i] ^ array2[i];
        }
        
        return array2;
    }
    
    public static int [] S0_Coordinates (int[] input) {
        
        //Convert bitwise1 array into row and column values which need to
        //be returned as integers
        //Extract elements from bitwise to be extracted from S0 array
        int[] row1 = {input[0], input[3]};
        int[] col1 = {input[1], input[2]};

        //Convert array values to binary string        
        String stringRow1 = "";
        String stringCol1 = "";
        
        for (int i=0; i < row1.length ; i++) {            
            stringRow1 += row1[i];
            stringCol1 += col1[i];
        }
        
        int coord1 = Integer.parseInt(stringRow1,2); 
        int coord2 = Integer.parseInt(stringCol1,2);
        
        int [] outputArray = {coord1, coord2};
        
        return outputArray;
    }
    
    public static int [] S1_Coordinates (int[] input) {
        
        //Convert bitwise1 array into row and column values which need to
        //be returned as integers
        //Extract elements from bitwise to be extracted from S1 array
        int[] row1 = {input[4], input[7]};
        int[] col1 = {input[5], input[6]};

        //Convert array values to binary string        
        String stringRow1 = "";
        String stringCol1 = "";
        
        for (int i=0; i < row1.length ; i++) {            
            stringRow1 += row1[i];
            stringCol1 += col1[i];
        }
        
        int coord1 = Integer.parseInt(stringRow1,2); 
        int coord2 = Integer.parseInt(stringCol1,2);
        
        int [] outputArray = {coord1, coord2};
        
        return outputArray;
    }
    
    public static String S0_Element (int[] input) {
        
        //Lay out S0 array
        String [][] S0 = { {"01", "00", "11", "10"},
                           {"11", "10", "01", "00"},
                           {"00", "10", "01", "11"},
                           {"11", "01", "11", "10"} };
        
        String outputString = S0 [input[0]][input[1]];
        
        return outputString;
    }
    
    public static String S1_Element (int[] input) {
        
        //Lay out S1 array
        String [][] S1 = { {"00", "01", "10", "11"},
                           {"10", "00", "01", "11"},
                           {"11", "00", "01", "00"},
                           {"10", "01", "00", "11"} };
        
        String outputString = S1 [input[0]][input[1]];
        
        return outputString;
    }
    
    public static String Concatenate_2 (String input1, String input2) {
        
        String outputString = input1 + input2;
        
        return outputString;
    }
    
    public static int[] Four_Plus_Four (int[] input1, int[] input2) {
        
        int [] output = new int [8];
        
        output[0] = input1[0];
        output[1] = input1[1];
        output[2] = input1[2];
        output[3] = input1[3];
        output[4] = input2[0];
        output[5] = input2[1];
        output[6] = input2[2];
        output[7] = input2[3];
        
        return output;
    }
        
    public static int [] StringtoIntArray (String inputString) {

        //First convert string to array of characters
        char [] stringArray = new char [4];
        
        for (int i = 0; i < stringArray.length; i ++) {
            stringArray[i] = inputString.charAt(i);
        }
        
        int [] outputArray = new int [4];
        
        //Extra step - P4 permutation also worked in
        
        outputArray[0] = Character.getNumericValue(stringArray[1]);
        outputArray[1] = Character.getNumericValue(stringArray[3]);
        outputArray[2] = Character.getNumericValue(stringArray[2]);
        outputArray[3] = Character.getNumericValue(stringArray[0]);
        
        return outputArray;   
    }
        
    public static int [] Switch (int[] input1, int[] input2) {
        
        int [] array = new int[8];
        
        array[0] = input2[0];
        array[1] = input2[1];
        array[2] = input2[2];
        array[3] = input2[3];
        array[4] = input1[0];
        array[5] = input1[1];
        array[6] = input1[2];
        array[7] = input1[3];
        
        return array;
    }
    
    public static int [] FirstFour (int[] input) {
        
        int [] output = new int [4];
        
        output[0] = input[0];
        output[1] = input[1];        
        output[2] = input[2];
        output[3] = input[3];

        return output;
    }
    
    public static int [] LastFour (int[] input) {
        
        int [] output = new int [4];
        
        output[0] = input[4];
        output[1] = input[5];        
        output[2] = input[6];
        output[3] = input[7];

        return output;
    }
        
    public static int [] Create_IP_1 (int [] input) {
        
        //Input plaintext:  1 2 3 4 5 6 7 8 
        //Output IP_1:      4 1 3 5 7 2 8 6
        
        int IP_1 [] = new int [8];
        
        IP_1[0] = input[3];
        IP_1[1] = input[0];
        IP_1[2] = input[2];
        IP_1[3] = input[4];
        IP_1[4] = input[6];
        IP_1[5] = input[1];
        IP_1[6] = input[7];
        IP_1[7] = input[5];
        
        return IP_1;
    }
    
}
