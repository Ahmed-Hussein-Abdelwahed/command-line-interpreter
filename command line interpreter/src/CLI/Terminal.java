package CLI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.io.FileNotFoundException;

public class Terminal {

    Scanner Input = new Scanner(System.in);
    private File folder = new File(System.getProperty("user.dir")); // default directory

    public void cp(String sourcePath, String destinationPath ) {  // copy command function

        Path source = null , destination = null;

        source = Paths.get(sourcePath);
        destination = Paths.get(destinationPath);

        if(source.isAbsolute() && destination.isAbsolute()) { // fullPath && fullPath

            try {
                destination = Paths.get(destinationPath + "\\" + source.getFileName());
                Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
            }
            catch(IOException e) {
                System.out.println("faild to copy");
            }
        }
        else if(!source.isAbsolute() && !destination.isAbsolute()) { // short Path && short Path

            try {
                source = Paths.get(this.folder + "\\" +sourcePath);
                destination = Paths.get(this.folder + "\\" + destinationPath + "\\" + sourcePath);
                Files.copy(source.toAbsolutePath(), destination.toAbsolutePath(),StandardCopyOption.REPLACE_EXISTING);
            }
            catch(IOException e) {
                System.out.println("faild to copy");
            }
        }
        else if(source.isAbsolute() && !destination.isAbsolute()) { // fullPath && short Path
            try {
                source = Paths.get(sourcePath);
                destination = Paths.get(this.folder + "\\" + destinationPath + "\\" + source.getFileName());
                Files.copy(source, destination.toAbsolutePath(),StandardCopyOption.REPLACE_EXISTING);
            }
            catch(IOException e) {
                System.out.println("faild to copy");
            }
        }
        else if(!source.isAbsolute() && destination.isAbsolute()) { // short Path && full Path
            try {
                source = Paths.get(this.folder + "\\" +sourcePath);
                destination = Paths.get(destinationPath + "\\" + sourcePath);
                Files.copy(source.toAbsolutePath(), destination,StandardCopyOption.REPLACE_EXISTING);
            }
            catch(IOException e) {
                System.out.println("faild to copy");
            }
        }
    }

    public void mv(String sourcePath, String destinationPath)  { // move command

        Path destination = Paths.get(destinationPath);
        Path source = Paths.get(sourcePath);

        if(destination.isAbsolute() && source.isAbsolute()) { // both are fullPath

            try {
                Files.move (Paths.get(sourcePath), Paths.get(destinationPath + "\\" + sourcePath));
            }
            catch(IOException e) {
                System.out.println("file is not found");
            }
        }
        else if(!destination.isAbsolute() && !source.isAbsolute()) { // both are not full path

            try {

                Files.move (Paths.get(this.folder + "\\" + sourcePath),
                        Paths.get(this.folder + "\\" + destinationPath + "\\" + sourcePath));
            }
            catch(IOException e) {
                System.out.println("file is not found");
            }
        }
        else if(destination.isAbsolute() && !source.isAbsolute()) { // one of them is not full path

            try {

                Files.move (Paths.get(this.folder + "\\" + sourcePath),
                        Paths.get(destinationPath + "\\" + sourcePath));
            }
            catch(IOException e) {
                System.out.println("file is not found");
            }
        }
        else if(!destination.isAbsolute() && source.isAbsolute()) { // one of them is not full path

            try {

                Files.move (Paths.get(sourcePath), Paths.get(this.folder + "\\" + destinationPath + "\\" + sourcePath));
            }
            catch(IOException e) {
                System.out.println("file is not found");
            }
        }
    }

    public void rm(String sourcePath) {  // remove command function

        Path source = Paths.get(sourcePath);

        if(source.isAbsolute())
        {
            File removeFile = new File(sourcePath);
            if(removeFile.exists()) {

                removeFile.delete();
            }
            else {
                System.out.println("file is not found\n");
            }

        }

        else {
            File removeFile = new File(this.folder + "\\" + sourcePath);
            if(removeFile.exists()) {

                removeFile.delete();
            }
            else {
                System.out.println("file is not found\n");
            }
        }
    }

    public File pwd() {  // print working directory command function
        return this.folder;
    }

    public void cat(String[] files) {  // concatenation command function

        for(int num = 1; num < files.length; num++) {

            if(Paths.get(files[num]).isAbsolute()) {

                //File file = new File(files[num]);

                try {
                    FileReader read = new FileReader(files[num]);
                    Scanner input = new Scanner(read);
                    String line = "";
                    while(input.hasNextLine()) {
                        line = input.nextLine();
                        System.out.println(line);
                    }
                    System.out.println();
                    input.close();
                }
                catch(FileNotFoundException e) {
                    System.out.println("file " + files[num] + " not found");
                }
            }
            else {
                files[num] = this.folder + "\\" + files[num];
                if(Paths.get(files[num]).isAbsolute()) {

                    //File file = new File(this.folder + "\\" + files[num]);
                    try {
                        FileReader read = new FileReader(files[num]);
                        Scanner input = new Scanner(read);
                        String line = "";
                        while(input.hasNextLine()) {
                            line = input.nextLine();
                            System.out.println(line);
                        }
                        System.out.println();
                        input.close();
                    }
                    catch(FileNotFoundException e) {
                        System.out.println("file " + files[num] + " not found");
                    }
                }
            }
        }
    }

    public void cd(String directory) { // change directory command function

        File check = new File(directory);
        if(check.exists()) {
            this.folder = new File(directory);
        }
        else {
            System.out.println("Invalid directory");
        }
    }

    public void ls() { // list command function

        try {
            String[] existFiles = this.folder.list();

            for(int num = 0; num <existFiles.length; num++) {
                System.out.println(existFiles[num]);
            }
        }
        catch(Exception E) {
            System.err.println("There are no files in this directory\n");
        }
    }

    public void more(String textFile) { // more command function (show data in a file)

        Path read = Paths.get(textFile);

        if(read.isAbsolute()) {
            File readFile = new File(textFile);

            if(!readFile.exists()) {
                System.out.println("File is not found\n");
            }
            else if(!readFile.canRead()) {
                System.out.println("File can not be readed\n");
            }
            else {
                try {

                    String fileData;
                    Scanner reader = new Scanner(readFile);
                    while (reader.hasNextLine()) {
                        fileData = reader.nextLine();
                        System.out.println(fileData);
                    }
                    reader.close();
                }
                catch(FileNotFoundException e) {
                    System.out.println("not found\n");
                }
            }
        }

        else {

            File readFile = new File(this.folder + "\\" + textFile);

            if(!readFile.exists()) {
                System.out.println("File is not found\n");
            }
            else if(!readFile.canRead()) {
                System.out.println("File can not be readed\n");
            }
            else {
                try {

                    String fileData;
                    Scanner reader = new Scanner(readFile);
                    while (reader.hasNextLine()) {
                        fileData = reader.nextLine();
                        System.out.println(fileData);
                    }
                    reader.close();
                }
                catch(FileNotFoundException e) {
                    System.out.println("not found\n");
                }
            }
        }
    }

    public void date() {  // date command function

        DateTimeFormatter Date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(Date.format(now));
    }

    public void clear() {  // clear command function

      for (int i = 0; i < 100; i++) {
          System.out.println();
      }
    }

    public void help() {   // help command function

        System.out.println("cp: copy file\n" +
                "mv: move files\n" +
                "rm: remove file\n" +
                "pwd: print working directory\n" +
                "cat: concate ==> cereate new file\n" +
                "cd: change directory\n" +
                "ls: list all exists files\n" +
                "more: scroll down data output only\n" +
                "date: show data and time\n" +
                "mkdir: make directory\n" +
                "rmdir: remove directory\n" +
                "args: show arguments for each command\n" +
                "clear: clear screen\n");
    }

    public void mkdir(String folderName) {  // make directory command function

        Path directory = Paths.get(folderName);

        if(directory.isAbsolute()) {

            File createFolder = new File(folderName);
            boolean confirm = createFolder.mkdir();
            if(!confirm) {
                System.out.println("Couldn’t create specified directory\n");
            }
        }
        else {

            File createFolder = new File(folder + "\\" + folderName);
            boolean confirm = createFolder.mkdir();
            if(!confirm) {
                System.out.println("Couldn’t create specified directory\n");
            }
        }
    }

    public void rmdir(String folderName) {  // remove directory command function

        Path directory = Paths.get(folderName);

        if(directory.isAbsolute()) {

            File deleteFile = new File(folderName);

            if(deleteFile.exists()) {

                deleteFile.delete();
            }
            else {

                System.out.println("File is not found in " + folder + " Directory\n");
            }
        }
        else {

            File deleteFile = new File(folder + "\\" + folderName);

            if(deleteFile.exists()) {

                deleteFile.delete();
            }
            else {

                System.out.println("File is not found in " + folder + " Directory\n");
            }
        }
    }

    public void args(String cmd) { // show the needed arguments for each command

        switch(cmd) {

            case "cp":
                System.out.println(cmd + " ==> arg1: source path \t arg2: destination path");
                break;
            case "mv":
                System.out.println(cmd + " ==> arg: file name");
                break;
            case "rm":
                System.out.println(cmd + " ==> arg: file name");
                break;
            case "pwd":
                System.out.println(cmd + " ==> no arguments");
                break;
            case "cat":
                System.out.println(cmd + " ==> arg: file name to create ");
                break;
            case "cd":
                System.out.println(cmd + " ==> arg: directory name");
                break;
            case "ls":
                System.out.println(cmd + " ==> no arguments");
                break;
            case "more":
                System.out.println(cmd + " ==> arg: file name to show its data");
                break;
            case "date":
                System.out.println(cmd + " ==> no arguments");
                break;
            case "clear":
                System.out.println(cmd + " ==> no arguments");
                break;
            case "help":
                System.out.println(cmd + " ==> no arguments");
                break;
            case "mkdir":
                System.out.println(cmd + " ==> arg: directory name to make");
                break;
            case "rmdir":
                System.out.println(cmd + " ==> arg: directory name to remove");
                break;
            default:
                System.out.println(cmd + " command is not found");
        }
    }
}