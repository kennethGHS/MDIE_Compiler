package com.containers;

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
    public Instruction(String line,Boolean labelUp,String labelName,int numLine){
        this.assemblyLine = line;
        this.parsedLine = new ArrayList<>();
        parsedLine= SyntaxAnalyzerAndParser.parseLine(line);
        attachedToLabel = labelUp;
        if (attachedToLabel==true){
            labelAttached=labelName;
        }
        this.line = numLine;


        instList.add(this);



    }
    public static int getFromLabel(String label){
        for (Instruction inst:instList){
            if(inst.attachedToLabel){
                if (inst.labelAttached==label){
                    return inst.line*4;
                }
            }
        }
        return -1;
    }
}
