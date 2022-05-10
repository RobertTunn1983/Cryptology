public class SDES_Decrypt {public static void main (String args[]) {
        int key [] = {1,0,1,0,1,0,1,0,1,0};
        int [] plaintext = {0,1,1,0,1,0,1,1};
        int P10 [] = SDES_Methods.Create_P10(key);
        int [] LS1 = SDES_Methods.Create_LS1(P10);
        int [] K1 = SDES_Methods.Create_K1(LS1);
        int [] LS2 = SDES_Methods.Create_LS2(LS1);
        int [] K2 = SDES_Methods.Create_K2(LS2);
        int IP [] = SDES_Methods.Create_IP(plaintext);
        int EP [] = SDES_Methods.Create_EP(IP);
        int bitwise1 [] = SDES_Methods.Bitwise_Add(K2, EP);
        int [] SO_Coords = SDES_Methods.S0_Coordinates(bitwise1);
        int [] S1_Coords = SDES_Methods.S1_Coordinates(bitwise1);
        String S0_Element = SDES_Methods.S0_Element(SO_Coords);
        String S1_Element = SDES_Methods.S1_Element(S1_Coords);
        String preP4 = SDES_Methods.Concatenate_2(S0_Element, S1_Element);
        int [] P4 = SDES_Methods.StringtoIntArray(preP4);
        int [] subArrayIPLeft = SDES_Methods.FirstFour(IP);
        int [] bitwise2 = SDES_Methods.Bitwise_Add(subArrayIPLeft, P4);
        int [] subArrayIPRight = SDES_Methods.LastFour(IP);
        int[] SW = SDES_Methods.Switch(bitwise2,subArrayIPRight);
        int EP2 [] = SDES_Methods.Create_EP(SW);
        int bitwise3 [] = SDES_Methods.Bitwise_Add(K1, EP2);
        int [] SO_Coords2 = SDES_Methods.S0_Coordinates(bitwise3);
        int [] S1_Coords2 = SDES_Methods.S1_Coordinates(bitwise3);
        String S0_Element2 = SDES_Methods.S0_Element(SO_Coords2);
        String S1_Element2 = SDES_Methods.S1_Element(S1_Coords2);
        String preP42 = SDES_Methods.Concatenate_2(S0_Element2, S1_Element2);
        int [] P42 = SDES_Methods.StringtoIntArray(preP42);
        int [] subArraySWLeft = SDES_Methods.FirstFour(SW);
        int [] bitwise4 = SDES_Methods.Bitwise_Add(subArraySWLeft, P42);
        int [] subArrayIPRight2 = SDES_Methods.LastFour(SW);
        int[] PreIP_1 = SDES_Methods.Four_Plus_Four(bitwise4,subArrayIPRight2);
        int [] ciphertext = SDES_Methods.Create_IP_1(PreIP_1);
        System.out.println("The ciphertext is:");
        for (int i = 0; i < ciphertext.length; i++) {
            System.out.print(ciphertext[i]);}}}
