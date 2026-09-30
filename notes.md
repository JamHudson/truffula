# Truffula Notes
As part of Wave 0, please fill out notes for each of the below files. They are in the order I recommend you go through them. A few bullet points for each file is enough. You don't need to have a perfect understanding of everything, but you should work to gain an idea of how the project is structured and what you'll need to implement. Note that there are programming techniques used here that we have not covered in class! You will need to do some light research around things like enums and and `java.io.File`.

PLEASE MAKE FREQUENT COMMITS AS YOU FILL OUT THIS FILE.

## App.java
* The main project file, the entry point for the program.
* ~~It's main job seems to be organizing the flags and arguments into something usable.~~
* The main method calls the rest of the methods needed to run the operation.

## ConsoleColor.java
* This file contains color codes.
* It's just color, you call the file and get the color in here.
* It's an enum, but that's basically just a fancy array of constants.

## ColorPrinter.java / ColorPrinterTest.java
* This file prints to the console, changing the color based on the provided ConsoleColor.
* All of the print functions call the same print method, varying based on what is appended to the message or whether the color resets afteward.
* Setting a color and printing are two different methods. I find this impractical, though it may be more efficient for how the ColorPrinter is used.

## TruffulaOptions.java / TruffulaOptionsTest.java
* This class is the actual argument handler.
* There's no printing in this class, it only stores information.

## TruffulaPrinter.java / TruffulaPrinterTest.java
* This is the class that uses the ColorPrinter, it is also the class that uses TruffulaOptions.
* TruffulaPrinter is only passed the root file, meaning it must traverse the file structure itself.
* There is no default for TruffulaOptions, because then there'd be no directory to print.

## AlphabeticalFileSorter.java
* This class has a method that sorts files alphabetically.
* It uses a lambda, which from my understanding is a function that immediately returns a value.