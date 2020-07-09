package com.company;

import com.FileManagement.FileManager;
import com.containers.Instruction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;

import static com.syntax_analisis.SyntaxAnalyzerAndParser.parseTotalFile;

public class Main {

    public static void main(String[] args) throws IOException {

        ArrayList<String> lines = FileManager.getFileLines("C:\\Users\\kenne\\OneDrive\\Desktop\\txtPrueba.txt");
        System.out.println(lines);
        parseTotalFile(lines);
        for (Instruction inst:Instruction.instList){
            printBits(inst.bitSet);
        }
        FileManager.writeFile(null);

    }

    public static void printBits(BitSet bits) {
        int i = 0;
        String str = "";
        while (i != bits.size()) {
            if(i==32){
                break;
            }
            if (bits.get(i)){
                str = "1"+str;
            }
            else{
                str = "0"+str;

            }
            i++;
        }
        System.out.println(str);

    }
}
