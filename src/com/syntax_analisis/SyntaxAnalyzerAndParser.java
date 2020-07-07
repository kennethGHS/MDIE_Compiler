package com.syntax_analisis;

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

}
