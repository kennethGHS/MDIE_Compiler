package com.FileManagement;

import java.io.*;
import java.util.ArrayList;

public class ReadFile {
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
    private static ArrayList<String> filterLines(String file) {
        String[] list = file.split("\n");
        ArrayList<String> toReturn = new ArrayList<>();
        for (String line : list) {
            String lineSpace = line.trim();
            if (lineSpace.charAt(0) != ';' || (!lineSpace.isEmpty())) {
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
    public static ArrayList<String> getFileLines(String filename) {
        try {
            String file = readFile(filename);
            ArrayList<String> fileLines = filterLines(file);
            return fileLines;
        } catch (Exception e) {
            return null;
        }
    }


}
