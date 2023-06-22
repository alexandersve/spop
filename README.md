# Spop - Data flow code generater for Pentaho Kettle and Apache Hop

Code from the Master thesis by Alexander Svensson, Master of Science in Engineering Physics, about code generation and compilation of Pentaho Kettle and Apache Hop structures. The actual paper can be found here: [Code Generation for Accelerating Data Flow](http://umu.diva-portal.org/smash/record.jsf?aq2=%5B%5B%5D%5D&c=28&af=%5B%5D&searchType=LIST_LATEST&sortOrder2=title_sort_asc&query=&language=en&pid=diva2%3A1771458&aq=%5B%5B%5D%5D&sf=all&aqe=%5B%5D&sortOrder=author_sort_asc&onlyFullText=false&noOfRows=50&dswid=2508)

This is cleaned up version of the code used for my Master thesis paper, but still it isn't created with general use in mind and no testing has been done by other users or on other computers. Feel free to contact me on anything that is unclear.

DISCLAIMER: This is a project made on top of two Open Source projects that are under the Apache License. I have, as far as I could, tried to not include explicit code from them in the git code but have not always been succesful. Hopefully these occurences are obvious but reuse all code under your own risk (or according to the Apache License). Pentaho Kettle's open source can be found here: [Github.com/pentaho/pentaho-kettle](https://github.com/pentaho/pentaho-kettle). Apache Hop's open source can be found here: [Github.com/apache/hop](https://github.com/apache/hop).


# Structure
codeGeneration - contains everything connected to the actual code generator.

speedTests - contains several speed tests for different jobs and transformations. Also has a Dockerfile for easily generating new code for jobs and transformations.


# Usage
## Requirements
The only programs that are needed are wget, jar and Docker. This project is currently not developed for use on anything other than an Ubuntu-sytem but could easily be implemented to work on all systems that can run Docker.

## codeGeneration
codeGeneration consists of three folders: go, java and transforms. The go folder is the folder that contains the code for generating java source which is then placed in the java folder. To go from just having downloaded the project to a working java project follow these steps:

- Create your kettle job file .kjb or hop workflow file .hwf. Change the code in exjobb/codeGeneration/go/runGo.sh to have the right path to it and also specify an output path(put it in src in exjobb/codeGeneration/java/ for easy deployment).

- In folder hop there are several bash scripts. Run "buildGo.sh" to build the project and then run "runGo.sh" to execute the program (these can be executed from anywhere). If it is the first time running the project it is wise to run downloadKettleRequirements.sh and downloadHopRequirements.sh first to not have a very long execution time when running runGo.sh. runGo.sh also requires an argument(hop/kettle) to know if it is a kettle or a hop file it is working with.

- Now your source in the java folder should have been updated(if you chose it as the output path). The java folder also have several bash scripts. Run "buildJava.sh" to build the java project and then execute the code with "runJava.sh". They also require hop or kettle as argument to work properly. Here the download scripts doesn't need to be ran before since the executon of them will be seen in the terminal when running buildJava.sh.

The transforms folder contains several manualy generated implementations of kettle transforms that can be copied to the source file in the java folder to then be run the same way as the generated code. They are by default implemented with an empty log channel but can easily be changed to writing to stdout by changing in MyLogChannel.java from EmptyLogChannel to BaseLogChannel.

# speedTests
speedTests has in its folder a bash script, buildTestCreater.sh, for creating a Docker image that is made to easily generate new code and set up a folder where it can easily be ran. The instructions for using the container can be found in instructions.txt, also in the speedTest folder. In the subfolder tests there are several such generated programs, the code to run tests on them and a folder, results, with the results from a few such tests. Note that the source code from Hop or Kettle need to be downloaded to the programs if you have just downloaded this git and want to run the tests.

# Known limitations
- Code Generator: The code has not been thoroughly tested and may fail for some/many kettle or hop plugins(unknown).
- Code generation have only been implementated for jobs in kettle and workflows in hop. Some manual examples of generation for transforms in kettle have however been created.
- In this stage there are also a number of more obvious limitations and also some minor ones that aren't worth mentioning.

# Known buggs
- Code Generator: Writing something with just numbers or just a Y or a N in a string field for the xml input will result in the field becoming the wrong datatype and make the resulting java code not compile. 
- Code Generator: Elements with several words or underscore in their name will not correctly find their setter class (The code currently only changes the first character to a Capital letter, the rest need to be done in the generated Java code.).
- Code Generator: Several level of elements will not correctly be converted to their setter classes.
