package com.containers;

import com.comparators.ToBinConverter;
import com.comparators.TypeComparator;
import com.syntax_analisis.SyntaxAnalyzerAndParser;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.BitSet;

public class Instruction {
    public static ArrayList<Instruction> instList = new ArrayList<>();
    public String assemblyLine;
    public ArrayList<String> parsedLine;
    public ArrayList<BitSet> bitLine = new ArrayList<>();
    public boolean attachedToLabel = false;
    public String labelAttached = null;
    public boolean error = false;
    public String errorType;
    public int line;
    public BitSet bitSet = new BitSet(32);

    public void printInstructionInfo() {
        if (error == false) {
            System.out.println("Line:" + assemblyLine);
            System.out.println("Operation:" + parsedLine.get(0));
            int i = 1;
            while (i < parsedLine.size()) {
                System.out.println("Operand" + (i - 1) + ":" + parsedLine.get(i));
            }
            System.out.println("BitValue= " + bitLine);
        } else {
            System.out.println("Error in line:" + line + "\n Type: " + errorType);
        }
    }

    public Instruction(String line, Boolean labelUp, String labelName, int numLine) {
        instList.add(this);
        this.assemblyLine = line;
        this.parsedLine = new ArrayList<>();
        parsedLine = SyntaxAnalyzerAndParser.parseLine(line);
        attachedToLabel = labelUp;
        if (attachedToLabel == true) {
            labelAttached = labelName;
        }
        if (parsedLine == null) {
            error = true;
            errorType = "No se logro leer la linea";
        }
        this.line = numLine;


    }

    public void setBitLine() {
        this.bitLine = ToBinConverter.getBitSet(this.parsedLine);
    }

    public static int getFromLabel(String label) {
        for (Instruction inst : instList) {
            if (inst.attachedToLabel) {
                if (inst.labelAttached == label) {
                    return inst.line * 4;
                }
            }
        }
        return -1;
    }

    public void setBitSet() {
        int type = TypeComparator.type(this.parsedLine.get(0));
        if (type == -1) {
            error = true;
            errorType = "No se logro identificar la operacion";
        } else if (type == 1) {
            parseRType();

        } else if ((type == 2)) {
            parseIType();
        } else if (type == 3) {
            parseJType();
        } else if (type == 4) {
            parseVType();
        }
    }

    private void parseRType() {

    }

    private void parseIType() {

    }

    private void parseJType() {

    }

    private void parseVType() {

    }
}
