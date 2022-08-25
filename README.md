#HLN Java Coding Challenge

The task is to complete the BundleMaker application so that it will read from an input file containing information about various
types of wood, generate bundles of wood based on a given set of parameters, and write to an output file.  
 
The application should take the following command line parameters:

1. Input Filename
2. Minimum Bundle Price
3. Maximum Bundle Price
4. Command
5. Output Filename

The input file will be tab-delimited with 3 fields: wood type, id, and price. The included file contains information for 3 types of
wood: maple, oak, and pine.

The min and max prices will be whole numbers representing a filter to be used on bundle price.

The command will be a sequence of letters indicating the types of wood to be included in the bundles and the sort order of the
output file. For example, "bomp" indicates that the output file should contain bundles of maple, oak, and pine and be sorted by the
bundle price, oak price, maple price, pine price, oak id, maple id, and then pine id. "pom" would indicate the same bundle components but would be sorted by
pine price, oak price, maple price, pine id, oak id, and then maple id. 

The output file should also be tab-delimited and examples of the expected output with various commands are included. They were all
generated with a min/max filter of 50/100. Assume that the file will be read by another program.

The application should not require any 3rd party libraries (JUnit is acceptable) and should be able to compile and run using only a
modern JDK.

This exercise will be judged on the use of standard coding practices (including proper oo design, error handling, formatting, and
parameter checking), efficiency, scalability, accuracy, and attention to detail.
