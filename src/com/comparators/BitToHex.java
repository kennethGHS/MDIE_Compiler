package com.comparators;

import java.util.BitSet;

public class BitToHex {
    public static String byteToHex(BitSet instruction){
        byte[] bytearray=instruction.toByteArray();
        String toReturn = "";
        for (byte b : bytearray) {
            String st = String.format("%02X", b);
            toReturn= toReturn+st;
        }
        return toReturn;
    }
}
