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

    /**
     * prints the instruction data
     */
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

    /**
     * Creates a new instruction
     * @param line assembly line
     * @param labelUp if it contains a label
     * @param labelName the name of the label
     * @param numLine the number of the line
     */
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

    /**
     * Sets the instruction line
     */
    public void setBitLine() {
        this.bitLine = ToBinConverter.getBitSet(this.parsedLine);
    }

    /**
     * Gets the number of the instruction multiplied by 4 that contains the label
     * @param label which label the instruction jumping to
     * @return the number of instruction multiplied by 4
     */
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

    /**
     * Sets the instruction bit values
     */
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
        this.setRDnRS1();
        this.setRs2();
        this.setOpCode();
        this.setALUop();

    }

    private void parseIType() {
        this.setRDnRS1();
        this.setOpCode();
        this.setImm();
    }

    private void parseJType() {
        this.setOpCode();
        this.setAddress();
    }

    private void parseVType() {
        this.setRDnRS1();
        this.setRs2();
        this.setOpCode();
        this.setALUop();
    }

    private void setRDnRS1() {
        BitSet RD = this.bitLine.get(2);
        BitSet Rs1 = this.bitLine.get(3);
        int beginIndex = 22;
        int len = RD.length();
        int i = 0;
        while (i != len) {
            this.bitSet.set(beginIndex + i, RD.get(i));
            i++;

        }
        i = 0;
        beginIndex = 17;
        while (i != len) {
            this.bitSet.set(beginIndex + i, Rs1.get(i));
            i++;
        }

    }

    private void setOpCode() {
        BitSet opCode = this.bitLine.get(0);
        int i = 0;
        int len = opCode.size();
        int offset = 27;
        while (i != len) {
            this.bitSet.set(offset + i, opCode.get(i));
        }
    }

    private void setRs2() {
        BitSet Rs1 = this.bitLine.get(4);
        int i = 0;
        int len = Rs1.size();
        int offset = 12;
        while (i != len) {
            this.bitSet.set(offset + i, Rs1.get(i));
        }
    }

    private void setAddress() {
        BitSet address = this.bitLine.get(1);
        int i = 0;
        int len = address.size();
        while (i != len) {
            this.bitSet.set(i, address.get(i));
        }
    }

    private void setImm() {
        int i = 0;
        BitSet imm = this.bitLine.get(3);
        int len = imm.size();
        while (i != len) {
            this.bitSet.set(i + 1, imm.get(i));
        }
    }

    private void setALUop() {
        int i = 0;
        BitSet ALUop = this.bitLine.get(1);
        int len = ALUop.size();
        int offset = 9;
        while (i != len) {
            this.bitSet.set(i + offset, ALUop.get(i));
        }
    }
}
