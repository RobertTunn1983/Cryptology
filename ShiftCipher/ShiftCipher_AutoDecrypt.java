public class ShiftCipher {

    public static void main (String args[]) {
    
        String ciphertext = "Ef fev nflcu yrmv svczvmvu ze kyv crjk pvrij fw "
                + "kyv ezevkvveky tveklip kyrk kyzj nficu nrj svzex nrktyvu "
                + "bvvecp reu tcfjvcp sp zekvcczxvetvj xivrkvi kyre drej reu "
                + "pvk rj dfikrc rj yzj fne kyrk rj dve sljzvu kyvdjvcmvj "
                + "rsflk kyvzi mrizflj tfetviej kyvp nviv jtilkzezjvu reu "
                + "jkluzvu gviyrgj rcdfjk rj eriifncp rj r dre nzky r "
                + "dztifjtfgv dzxyk jtilkzezjv kyv kirejzvek tivrklivj kyrk "
                + "jnrid reu dlckzgcp ze r uifg fw nrkvi Pvk rtifjj kyv xlcw "
                + "fw jgrtv dzeuj kyrk riv kf fli dzeuj rj flij riv kf kyfjv "
                + "fw kyv svrjkj kyrk gvizjy zekvccvtkj mrjk reu tffc reu "
                + "lejpdgrkyvkzt ivxriuvu kyzj vriky nzky vemzflj vpvj reu "
                + "jcfncp reu jlivcp uivn kyvzi gcrej rxrzejk lj";
        
        //Convert characters in string to upper case
        String ciphertextUC = ciphertext.toUpperCase();
        System.out.println(ciphertext);
        
        //Convert entire string to an array of chars
        char[] cipherTextArr = new char[ciphertext.length()];
        cipherTextArr = strToCharArr(ciphertext);
        //Cipher_Methods.prArr(cipherTextArr);
        
        //If an element of array is a space, remove it
        cipherTextArr = charArrSpaceRem(cipherTextArr);
        
        //Make all elements of array capital letters
        String string = capitalize(cipherTextArr);

        //Make string back into an array
        cipherTextArr = strToCharArr(string);
        
        //Convert array of chars to their ASCII values
        int[] ascArr = asciiValues(cipherTextArr);

//        prArrChar(ascArr);
        
        ascArr = asciiConvert(ascArr,9);    
        //prArrChar(ascArr);
        
        char[] decrypto = intToAscii(ascArr);
//        System.out.println();
  //      System.out.println("The plaintext is: ");
    //    prArr(decrypto);  
        
        int[] totalsArr = totalFinder(decrypto);
 //       System.out.println();
        System.out.println("The number of times each latin letter occurs in"
                + " the encrypted text is:");
        prArrInt(totalsArr);
        
        double[] freqArr = freqFinder(totalsArr,decrypto);
        System.out.println();
        System.out.println("The frequency of each letter in the encrypted text "
                + "is:");
        prArrDoub(freqArr);

        
        double[] diffArr = new double[26];
        diffArr = diffCalc(freqArr);
        System.out.println();
        System.out.println("The lowest value element represents the correct "
                + "shift");
        prArrDoub(diffArr);
        
        double low = lowest(diffArr);
        System.out.println();
        System.out.println(low);
        
        
        
    }
    
    //Remove spaces from an array of chars
    public static char[] charArrSpaceRem (char[] input) {

        //Determine the number of spaces in the array of chars
        int count = 0;
        for (int i = 0; i < input.length; i++) {
            if (input[i] == ' ') {
                count ++;
            }
        }
        
        //Set output array length to length of input array less spaces
        char[] output = new char[input.length - count]; 
        
        //Create string for intermediate storage of chars without spaces
        String string = "";
        
        //If element in input array is not a space, copy it to array 
        //Otherwise do nothing
        for (int i = 0; i < input.length; i++) {
              if (input[i] != ' ') {
                  string += input[i];
              } 
        }

        //Call other method and convert string back to array
        output = strToCharArr(string);
        
        return output;
    }
    
    //Convert string into an array of chars
    public static char[] strToCharArr (String input) {
            
            char [] output = new char[input.length()];
        
            for (int i = 0; i < input.length(); i++) {
                output[i] = input.charAt(i);
            }
        
        return output;
    }
    
    //Print array of chars
    public static void prArrChar (char[] input) {
       
        for (int i = 0; i < input.length; i++) {
            System.out.print(input[i]);
        }
    }
    
    //Print array of integers
    public static void prArrInt (int[] input) {
        
        for (int i = 0; i < input.length; i++) {
            System.out.print(input[i] + " ");
        }
    }
    
    //Print array of doubles
    public static void prArrDoub (double[] input) {
        
        for (int i = 0; i < input.length; i++) {
            System.out.print(input[i] + " ");
        }
    }
         
    //Take char array and capitalize every element
    public static String capitalize (char[] input) {
        
        String output = "";        
        String string = "";
                
        //Copy chars to a string...
        for (int i = 0; i < input.length; i++) {
            string += input[i];
        }
        
        String stringUC = string.toUpperCase();
                
        return stringUC;
    }
    
    //Convert array of chars into their ASCII values
    public static int[] asciiValues (char[] input) {
        
        int [] output = new int [input.length];
        
        for (int i = 0; i < input.length; i++) {
            output[i] = (int)input[i];
        }

        return output;
    }
    
    //Add a user shift to the ASCII values between 65 and 90
    //In event that ASCII value exceeds 95, wrap it around to 65+
    public static int[] asciiConvert (int[] input, int shift) {
        
        int diff = 0;
        
        for (int i = 0; i < input.length; i ++) {

            input[i] = input[i] + shift;
        
            if (input[i] > 90) {
                diff = input[i] - 90;
                input[i] = 65 + diff - 1;
            }
        }
        return input;
    }
    
    //Convert an array of ASCII values (integers) into an array of characters
    public static char[] intToAscii (int[] input) {
        
        char[] output = new char[input.length];
        
        for (int i = 0; i < output.length; i++) {
            
            output[i] = (char)(input[i]);
        }
        
        return output;
    }
    
    //Find the total number of times that each roman letter occurs in an array
    //of chars
    public static int[] totalFinder (char[] input) {
        
        int letter = 65;
        int tally = 0;
        int[] output = new int[26];
        
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < input.length; j++) {
                if (input[j] == ((char)letter)) {
                    tally ++;
                }
            }
            output[i] = tally;
            tally = 0;
            letter++;
        }
        
        return output;
    }
    
    //Find the frequency of each letter in an array of chars
    public static double[] freqFinder (int[] input1, char[] input2) {
        
        double[] output = new double[26];
        double total = input2.length; 
        
        for (int i = 0; i < input1.length; i++) {
            output[i] = (input1[i]/total)*100;
        }
        
        return output;
    }
    
    //Find the letters corresponding to the frequency of characters in char 
    //array
    public static char[] match (double[] input1) {
        
        char[] output = new char[26];
        
        double freq[] = {8.4966, 2.0720, 4.5388, 3.3844, 11.1607, 1.8121,
                        2.4705, 3.0034, 7.5448, 0.1965, 1.1016, 5.4893, 3.0129,
                        6.6544, 7.1635, 3.1671, 0.1962, 7.5809, 5.7351, 6.9509,
                        3.6308, 1.0074, 1.2899, 0.2902, 1.7779, 0.2722};


        
        return output;
    }
    
    public static double[] cycle (double[] input) {

        double[] output = new double[26];
        
        System.out.println("The array after a shift of +1 is:");
        for (int i = input.length-1; i > 0; i--) {
            output[i] = input[i-1];
        }
    
        return output;
    }
    
    //Compare frequencies from array and standard English language letter
    //frequencies
    public static double [] diffCalc (double[] arrayFreq) {
        
        //Standard English frequencies
        double freq[] = {8.4966, 2.0720, 4.5388, 3.3844, 11.1607, 1.8121,
                        2.4705, 3.0034, 7.5448, 0.1965, 1.1016, 5.4893, 3.0129,
                        6.6544, 7.1635, 3.1671, 0.1962, 7.5809, 5.7351, 6.9509,
                        3.6308, 1.0074, 1.2899, 0.2902, 1.7779, 0.2722};
        
        double total = 0;
        double[] output = new double[26];

        //Calculates the sum of the differences between the freq of letters in
        //the encrypted text if a shift of 0 is applied, then calculates
        //same difference for a shift of 1 and so on up to a shift of 25
        //Smallest difference will be best fit to standard frequencies hence
        //the shift is calculated
        //Absolute values used
        for (int i = 0; i < 26; i++) {
            total = 0;
            for (int j = 0; j < 26; j++) {                
                total = Math.abs(freq[i] - arrayFreq[j]);
            }
            output[i] = total;
        }        
        return output;
    }
            
    //Find the lowest value in an array of doubles]
    public static double lowest (double[] input) {
        
        double output = input[input.length-1];
        
        for (int i = 0; i < input.length; i++) {
            if (input[i] < output) {
                output = input[i];
            }
        }
        return output;
    }
    
}
