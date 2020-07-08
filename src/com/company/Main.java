package com.company;

import com.comparators.BitToHex;
import com.comparators.ToBinConverter;

import java.util.BitSet;

public class Main {

    public static void main(String[] args) {
        BitSet bit = new BitSet(8);
        bit.set(0, true);
        bit.set(2, true);


        System.out.println(BitToHex.byteToHex(bit));
    }
}
