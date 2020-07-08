package com.comparators;

import com.containers.Instruction;

import java.util.ArrayList;
import java.util.BitSet;

public class ToBinConverter {
    /**
     * parses the instruction to a arraylist of BitSets, first it creates the instruction bitset,
     * then tries to check if its a register, then a imm then a label, it appends the result
     * @param parsedInstruction the parsed instruction in an arraylist
     * @return the bitset containing all the binary numbers of the operands
     */
    public static ArrayList<BitSet> getBitSet(ArrayList<String> parsedInstruction) {
        if (parsedInstruction == null) {
            return null;
        }
        ArrayList<BitSet> bitSetToReturn = BitSetter.setOperation(parsedInstruction.get(0));
        int i = 1;
        int limit = parsedInstruction.size();
        while (limit != i) {
            BitSet toAppend = null;
            try {
                toAppend = BitSetter.setReg(parsedInstruction.get(i));
                if (toAppend == null) {
                    toAppend = BitSetter.intToBitSet(Integer.parseInt(parsedInstruction.get(i)), 0);
                }
            } catch (Exception e) {
                int instructionNumber = Instruction.getFromLabel(parsedInstruction.get(i));
                if (instructionNumber == -1) {
                    return null;
                }
                toAppend = BitSetter.intToBitSet(instructionNumber, 0);
                bitSetToReturn.add(toAppend);
                return bitSetToReturn;

            }
            bitSetToReturn.add(toAppend);
            i++;
        }
        if (bitSetToReturn.contains(null)) {
            return null;
        } else {
            return bitSetToReturn;
        }
    }

}
