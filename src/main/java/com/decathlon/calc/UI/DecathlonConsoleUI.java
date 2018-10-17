package com.decathlon.calc.UI;

import com.decathlon.calc.Calculation.DecathlonResultEntriesProcessor;
import com.decathlon.calc.Properties.DecathlonCoefficients;
import com.decathlon.calc.Reader.CSVDecathlonResultReader;
import com.decathlon.calc.Reader.IDecathlonResultReader;
import com.decathlon.calc.Writer.IDecathlonResultWriter;
import com.decathlon.calc.Writer.XmlDecathlonResultWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class DecathlonConsoleUI {

    private String[] programArgs;
    private DecathlonCoefficients coefficients;

    public DecathlonConsoleUI() {
        coefficients = new DecathlonCoefficients();
    }
    public DecathlonConsoleUI(String[] args) {
        this();
        programArgs = args;
    }

    private String readInputFilename() {
        return readLineBase("Enter input file's name or full path to it.");
    }

    private String readOutputFilename() {
        return readLineBase("Enter output file's name or full path to it.");
    }

    private String readLineBase(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return scanner.nextLine();
    }

    public void startUI() {

        String inputFile = readInputFilename();
        String outputFile = readOutputFilename();


        //Determine input file type and instantiate an appropriate reader
        String fileType = null;
        try {
            fileType = Files.probeContentType(Paths.get(inputFile));
        } catch (IOException e) {
            System.out.println("Failed to open the file.");
            e.printStackTrace();
            System.exit(-1);
        }

        IDecathlonResultReader resultReader = null;

        if(fileType == null) {
            System.out.println("Failed to determine file format.");
            System.exit(-2);
        }

        switch (fileType) {
            case "text/csv":
                resultReader = new CSVDecathlonResultReader(inputFile);
                break;
            default:
                System.out.println("Provided file format is not supported.");
                System.exit(-3);
        }


        IDecathlonResultWriter resultWriter = null;

        //Determine output file type and instantiate and appropriate writer
        int lastIndexOfDot = outputFile.lastIndexOf(".");
        String outputFileType = lastIndexOfDot != -1 ?
                outputFile.substring(lastIndexOfDot + 1) : "";

        switch (outputFileType){
            case "xml":
                resultWriter = new XmlDecathlonResultWriter(outputFile);
                break;
            default:
                System.out.println("Provided output file type is not supported");
                System.exit(-4);

        }

        DecathlonResultEntriesProcessor processor;

        if(programArgs != null && programArgs.length > 0) {
            System.out.println("Detected program argument - it will be " +
                    "treated as Decathlon coefficients file name.");
            coefficients.readPropertiesFile(programArgs[0]);
            processor = new DecathlonResultEntriesProcessor(
                    resultReader,
                    resultWriter,
                    coefficients
            );
        }
        else {
            processor = new DecathlonResultEntriesProcessor(
                            resultReader,
                            resultWriter
            );
        }

        try {
            processor.processRawEntries();
        } catch (Exception e) {
            System.out.println("Failed to process entries." +
                    " Check your input file's format.");
            e.printStackTrace();
        }
    }
}
