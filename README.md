# decathlonCalc
A simple CLI decathlon score calculator
Requirements:
  1. Gradle.
  2. Java SE 7 (at least)

Compiling:
  1. Clone the master branch of this repo.
  2. Extract the archive.
  3. Navigate to the extracted folder.
  4. Open terminal at the extracted folder.
  5. execute command `gradle build`
  6. Compiled classes and a jar shold be located in the build forlder
  
Running:
  There are two ways to run the compiled code:
    1. Using gradle - just execute `gradle run -q` command in the same folder where the application was built. 
    2. Using java - starting at the build folder, navigate to build/libs and execute `java -jar [jar_name]`
    
P.S. Decathlon coefficients can be specified in a .properties file. This file should be located at <build_folder>/src/main/resources.
To use this file run the program with an additional argument - i.e. `java -jar [jar_name] [properties_file_without_the_extension]`
