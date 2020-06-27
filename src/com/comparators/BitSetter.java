package com.comparators;

import java.util.ArrayList;
import java.util.BitSet;

public class BitSetter {
    public BitSet setImm(String operand) {
        operand.replace("[", "");
        try {
            int num = Integer.parseInt(operand);
            return intToBitSet(num, 6);
        } catch (Exception e) {
            return null;
        }

    }

    public BitSet setReg(String operand) {
        try {
            operand.replace("[", "");
            switch (operand) {
                case "R1":
                    return intToBitSet(1, 5);
                case "R2":
                    return intToBitSet(2, 5);
                case "R3":
                    return intToBitSet(3, 5);
                case "R4":
                    return intToBitSet(4, 5);
                case "R5":
                    return intToBitSet(5, 5);
                case "R6":
                    return intToBitSet(6, 5);
                case "R7":
                    return intToBitSet(7, 5);
                case "R8":
                    return intToBitSet(8, 5);
                case "R9":
                    return intToBitSet(9, 5);
                case "R10":
                    return intToBitSet(10, 5);
                case "R11":
                    return intToBitSet(11, 5);
                case "IC":
                    return intToBitSet(12, 5);
                case "IP":
                    return intToBitSet(13, 5);
                case "RA":
                    return intToBitSet(14, 5);
                case "SP":
                    return intToBitSet(15, 5);
                case "V1":
                    return intToBitSet(16, 5);
                case "V2":
                    return intToBitSet(17, 5);
                case "V3":
                    return intToBitSet(18, 5);
                case "V4":
                    return intToBitSet(19, 5);
                case "V5":
                    return intToBitSet(20, 5);
                case "V6":
                    return intToBitSet(21, 5);
                case "V7":
                    return intToBitSet(22, 5);
                case "V8":
                    return intToBitSet(23, 5);

            }
        } catch (Exception e) {
            return null;
        }
        return null;

    }


    /**
     * Code taken from http://www.java2s.com/example/java/java.util/convert-an-integer-to-bitset.html
     *
     * @param value  integer to be converted
     * @param length the length of the bitset
     * @return
     */
    public static BitSet intToBitSet(int value, int length) {
        BitSet bits = new BitSet(length);
        int index = 0;
        while (value != 0) {
            if (value % 2 != 0) {
                bits.set(index);
            }
            ++index;
            value = value >>> 1;
        }

        return bits;
    }


    public ArrayList<BitSet> setOperation(String operand) {
        BitSet instructBit = new BitSet(5);
        BitSet aluBit = new BitSet(3);
        try {
            switch (operand) {
                case "add":
                    break;
                case "addi":
                    instructBit.set(1, true);
                    break;
                case "sub":
                    aluBit.set(2, true);
                    break;

                case "subi":
                    instructBit.set(1, true);
                    instructBit.set(4, true);
                    aluBit.set(2, true);
                    break;
                case "mul":
                    aluBit.set(1, true);
                    break;
                case "ldw":
                    instructBit.set(3, true);
                    break;

                case "ldh":
                    instructBit.set(3, true);
                    instructBit.set(4, true);
                    break;

                case "stw":
                    instructBit.set(2, true);
                    break;

                case "sth":
                    instructBit.set(2, true);
                    instructBit.set(4, true);
                    break;

                case "jmp":
                    instructBit.set(0, true);
                    break;

                case "jr":
                    instructBit.set(3, true);
                    instructBit.set(2, true);
                    break;


                case "call":
                    instructBit.set(0, true);
                    instructBit.set(4, true);
                    break;

                case "be":
                    instructBit.set(1, true);
                    instructBit.set(3, true);
                    break;

                case "bgt":
                    instructBit.set(1, true);
                    instructBit.set(3, true);
                    instructBit.set(1, true);
                    instructBit.set(4, true);
                    break;

                case "sl":
                    aluBit.set(0);
                    break;


                case "and":
                    aluBit.set(0);
                    aluBit.set(2);
                    break;


                case "or":
                    aluBit.set(0);
                    aluBit.set(1);
                    break;

                case "vmul":
                    aluBit.set(1);
                    instructBit.set(0, true);
                    instructBit.set(1, true);
                    break;
                case "vsr":
                    aluBit.set(1);
                    aluBit.set(0);
                    aluBit.set(2);
                    instructBit.set(0, true);
                    instructBit.set(1, true);
                    break;

                case "vsub":
                    instructBit.set(0, true);
                    instructBit.set(1, true);
                    aluBit.set(0);
                    aluBit.set(2);
                    break;

                case "vcsub":
                    instructBit.set(0, true);
                    instructBit.set(1, true);
                    instructBit.set(2, true);
                    instructBit.set(3, true);
                    break;

                case "vldw":
                    instructBit.set(0, true);
                    instructBit.set(1, true);
                    instructBit.set(3, true);
                    break;

                case "vldh":
                    instructBit.set(0, true);
                    instructBit.set(1, true);
                    instructBit.set(3, true);
                    instructBit.set(4, true);
                    break;

                case "vstw":
                    instructBit.set(0, true);
                    instructBit.set(1, true);
                    instructBit.set(2, true);
                    break;

                case "vsth":
                    instructBit.set(0, true);
                    instructBit.set(1, true);
                    instructBit.set(2, true);
                    instructBit.set(4, true);
                    break;
                default:
                    instructBit = null;
                    aluBit = null;
            }
        } catch (Exception e) {
            return null;
        }
        ArrayList<BitSet> operators = new ArrayList<>();
        operators.add(instructBit);
        operators.add(aluBit);
        return operators;


    }
}