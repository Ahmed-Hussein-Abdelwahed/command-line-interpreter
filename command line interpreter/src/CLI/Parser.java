package CLI;

import java.util.*;
import java.io.*;

public class Parser extends Terminal{

    private String[] commands = {"cp", "mv", "rm", "pwd", "cat", "cd","ls", "more", "date", "clear",
                                                "help", "mkdir", "rmdir", "args"};
    private String[] args;
    private String cmd;

    Scanner input = new Scanner(System.in);

    public boolean parseCmd(String input) { // validate command

        boolean confirm = true;

        if(Arrays.asList(commands).contains(input)) {
            confirm = true;
        }
        else {
            confirm = false;
            System.out.println("Invalid command");
        }

        return confirm;
    }

    public void run(String command, String[] arguments) throws IOException{
        // calling each command function according to the inputed command

        System.out.println("true");

        if(command.equals("cp")) {

            if(arguments.length < 3) {
                System.out.println("Too few arguments for cp command it must take two parameters");
            }
            else if(arguments.length == 3){
                cp(arguments[1],arguments[2]);
            }
            else {
                System.out.println("Too many arguments for cp command it must take two parameters only");
            }
        }
        else if(command.equals("mv")) {

            if(arguments.length < 3) {
                System.out.println("Too few arguments for mv command it must take two parameters");
            }
            else if(arguments.length == 3) {
                mv(arguments[1], arguments[2]);
            }
            else {
                System.out.println("Too many arguments for mv command it must take two parameters only");
            }
        }
        else if(command.equals("rm")) {

            if(arguments.length < 2) {
                System.out.println("Too few arguments for rm command it must take one parameter");
            }
            else if(arguments.length == 2) {
                rm(arguments[1]);
            }
            else {
                System.out.println("Too many arguments for rm command it must take one parameter only");
            }
        }
        else if(command.equals("pwd")) {
            if(arguments.length > 1) {
                System.out.println("Too many arguments for pwd command it must take no parameters");
            }
            else {
                System.out.println(pwd());
            }
        }
        else if(command.equals("cat")) {

            if(arguments.length == 2 || arguments.length > 2) {
                cat(arguments);
            }
            else {
                System.out.println("Too few arguments for cat command it must take one parameter or more");
            }
        }
        else if(command.equals("cd")) {

            if(arguments.length < 2) {
                System.out.println("Too few arguments for cd command it must take one parameter");
            }
            else if(arguments.length == 2) {
                cd(arguments[1]);
            }
            else {
                System.out.println("Too many arguments for cd command it must take one parameter only");
            }
        }
        else if(command.equals("ls")) {

            if(arguments.length > 1) {
                System.out.println("Too many arguments for ls command it must take no parameters");
            }
            else {
                ls();
            }
        }
        else if(command.equals("more")) {

            if(arguments.length < 2) {
                System.out.println("Too few arguments for more command it must take one parameter");
            }
            else if(arguments.length == 2){
                more(arguments[1]);
            }
            else {
                System.out.println("Too many arguments for more command it must take one parameter only");
            }
        }
        else if(command.equals("date")) {

            if(arguments.length > 1) {
                System.out.println("Too many arguments for date command it must take no parameters");
            }
            else {
                date();
            }
        }
        else if(command.equals("clear")) {

            if(arguments.length > 1) {
                System.out.println("Too many arguments for clear command it must take no parameters");
            }
            else {
                clear();
            }
        }
        else if(command.equals("help")) {

            if(arguments.length > 1) {
                System.out.println("Too many arguments for help command it must take no parameters");
            }
            else {
                help();
            }
        }
        else if(command.equals("mkdir")) {

            if(arguments.length < 2) {
                System.out.println("Too few arguments for mkdir command it must take one parameter");
            }
            else if(arguments.length == 2){
                mkdir(arguments[1]);
            }
            else {
                System.out.println("Too many arguments for mkdir command it must take one parameter only");
            }
        }
        else if(command.equals( "rmdir")){

            if(arguments.length < 2) {
                System.out.println("Too few arguments for rmdir command it must take one parameter");
            }
            else if(arguments.length == 2){
                rmdir(arguments[1]);
            }
            else {
                System.out.println("Too many arguments for rmdir command it must take one parameter only");
            }
        }
        else if(command.equals("args")) {

            if(arguments.length < 2) {
                System.out.println("Too few arguments for args command it must take one parameter");
            }
            else if(arguments.length == 2) {
                args(arguments[1]);
            }
            else {
                System.out.println("Too many arguments for args command it must take one parameter only");
            }
        }
    }

    public void Interface() throws IOException{ // get input from user then parse the command to validate that

        String userInput = "";

        while(true) {

            System.out.print(this.pwd() + ":~$ ");
            userInput = input.nextLine();

            this.args = userInput.trim().split("\\s+"); // trim extra spaces and split the command and the arguments

            this.cmd = this.args[0];

            if(parseCmd(this.cmd)) {

                run(this.cmd,this.args);
            }
            else {
                System.out.println("false");
            }
        }
    }
}
