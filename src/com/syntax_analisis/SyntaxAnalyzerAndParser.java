package com.syntax_analisis;

import com.containers.Instruction;

import java.util.ArrayList;

public class SyntaxAnalyzerAndParser {
    public static ArrayList<String> parseLine(String line){
        ArrayList<String> parsedLine = getOperands(getOperation(line));
        return parsedLine;
    };
    /**
     * @param sentence the first item is the kind of operation and the second one the operands
     * @return returns an arraylist, the first
     */
    public static ArrayList<String> getOperands(ArrayList<String> sentence) {
        ArrayList<String> toReturn = new ArrayList<>();
        if (sentence == null) {
            return null;
        }
        int maxIndex = sentence.get(1).length();
        toReturn.add(sentence.get(0));
        String toParse = sentence.get(1).replaceAll(" ", "");
        int i = 0;
        String currentOperand = "";
        toParse = toParse+'\n';
        while (i != maxIndex) {
            if (toParse.charAt(i) == '\n' || toParse.charAt(i) == ';') {
                toReturn.add(currentOperand);
                return toReturn;
            }
            if (toParse.charAt(i) == ',') {
                toReturn.add(currentOperand);
                currentOperand = "";
            }
            if(toParse.charAt(i) != ',') {
                currentOperand = currentOperand + toParse.charAt(i);
            }
            i++;

        }
        return null;
    }

    /**
     * This method receives a sentence as a string, it searches for the operation that the
     * assembly code is going to make and corroborates that it exists
     *
     * @param sentence the line of assembly
     * @return a list with the operator and the rest of the sentence
     */
    public static ArrayList<String> getOperation(String sentence) {
        sentence = sentence.trim();
        sentence= sentence+'\n';
        int sentenceLength = sentence.length();
        int i = 0;
        String operand = "";
        while (sentence.charAt(i) != ';' && sentence.charAt(i) != ' ' && sentence.charAt(i) != '\n') {
            operand = operand + sentence.charAt(i);
            i++;
            if (i == sentenceLength) {
                return null;
            }
        }
        ArrayList<String> toReturn = new ArrayList<>();
        toReturn.add(operand);
        toReturn.add(sentence.substring(i).trim());
        return toReturn;
    }

    /**
     * Receives a file with all the lines in an arraylist, it creates all the lines and parses them
     * @param lines lines of assembly code
     */
    public static void parseTotalFile(ArrayList<String> lines){
        int i =0;
        String label="";
        boolean isLabel=false;
        for (String line:lines){
            if (line.charAt(0)=='%'){
            isLabel=true;
            label= extractLabel(line);
            continue;
            }
            new Instruction(line,isLabel,label,i);
            isLabel=false;
            label="";
            i++;
        }
        for (Instruction inst : Instruction.instList){
            inst.setBitLine();
            inst.setBitSet();
        }

    }

    /**
     * Receives a line containing a label
     * @param line the line of the label
     * @return the label
     */
    private static String extractLabel(String line){
        String toReturn = "";
        for (char c : line.toCharArray()){
            if(c!='%'|| c!=':'){
                toReturn=toReturn+c;
            }
            if (c==';'){
                return toReturn;
            }
        }
        return toReturn;
    }

}
