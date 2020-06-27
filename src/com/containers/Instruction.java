package com.containers;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.BitSet;

public class Instruction {
    public String assemblyLine;
    public ArrayList<String> parsedLine;
    public ArrayList<BitSet> bitLine = new ArrayList<>();
    public boolean attachedToLabel = false;
    public boolean error = false;
    public String errorType;
    public int line;

    public void printInstructionInfo() {
        if(error == false) {
            System.out.println("Line:" + assemblyLine);
            System.out.println("Operation:" + parsedLine.get(0));
            int i=1;
            while (i<parsedLine.size()){
                System.out.println("Operand"+ (i - 1) + ":" + parsedLine.get(i));
            }
            System.out.println("BitValue= "+bitLine);
        }
        else{
            System.out.println("Error in line:" + line +"\n Type: " + errorType);
        }
    }
}
