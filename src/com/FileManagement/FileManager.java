package com.FileManagement;

import com.company.filechooser;
import com.comparators.BitToHex;
import com.containers.Instruction;
import java.io.*;
import java.util.ArrayList;

public class FileManager {
    /**
     * Code for reading a picture
     *
     * @param filename
     * @return
     * @throws IOException
     */
    private static String readFile(String filename) throws IOException {

        File file = new File(filename);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        StringBuffer sb = new StringBuffer();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }
        fr.close();    //closes the stream and release the resources
        return sb.toString();
    }

    /**
     * Filters the lines, lines containing comments or just empty are filtered
     *
     * @param file all the lines in a file
     * @return an arraylist containing the lines
     */
    public static ArrayList<String> filterLines(String file) {
        String[] list = file.split("\n");
        ArrayList<String> toReturn = new ArrayList<>();
        for (String line : list) {
            String lineSpace = line.trim();
            if (line.isEmpty()) {
                continue;
            }
            String toCompare1 = "" + lineSpace.charAt(0);
            if (!(toCompare1.equals("\n") || toCompare1.equals(";") || lineSpace.isEmpty())) {
                toReturn.add(lineSpace);
            }

        }
        return toReturn;
    }

    /**
     * Public method destined to be used in the parser as a way to get all the files
     *
     * @param filename
     * @return
     */
    public static ArrayList<String> getFileLines(String filename) throws IOException {

        String file = readFile(filename);
        ArrayList<String> fileLines = filterLines(file);
        return fileLines;

    }

    public static void writeFile(String filename) throws IOException {
        File fileOut = new File(filename);
        FileOutputStream fos = new FileOutputStream(fileOut);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        if (!(Instruction.isError())) {
            for (Instruction ins : Instruction.instList) {
                bw.write(BitToHex.bitToHex(ins.bitSet));
                bw.newLine();
            }

            bw.close();
            return;
        }
        bw.write(Instruction.returnErrors());
        bw.newLine();
        filechooser.error.setText("Error: ver archivo generado para mas informacion");
        bw.close();
    }

}
