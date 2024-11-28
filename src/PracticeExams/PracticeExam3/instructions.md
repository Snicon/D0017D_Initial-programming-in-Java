# D0017D Practice Exam 3: LTU Course Evaluator

## Assignment description
Your task is to implement a course evaluation system for LTU courses. The program is supposed to handle information about different courses, as well as the evaluation scores, that are submitted by students (see instructions).

### Menu
At the start, the program should print a menu of options that can be selected by the user. It
should be possible to exit the program by pressing **_q_**
```
----------------------------------
# LTU Course Evaluator
----------------------------------
1. Register new course
2. Register evaluation score
3. Print evaluation summary for a course
4. Print course list (sorted by course code)
5. Print course list (sorted by evaluation score)
q. End program
> Enter your option:
```

### Registering new course
A course is registered by providing its course code, as well as its name. A course code should always start with a capital letter, followed by four (4) digits and ending with a capital letter (for example D0017D, R0003N, M7017M, etc.). Make sure to implement checks that prevent having multiple courses with the same course code and makes sure that the course codes follow the above mentioned format..

```
> Enter your option: 1
> Enter course code: D0017D
> Enter course name: Initial Programming in Java

Course D0017D: Initial Programming in Java was added!
```

### Evaluation score registration
Once a course is registered, it should be possible to submit the evaluation score. The evaluation itself consists out of three (3) categories: Teacher, Course contents and Examination. A student should grade the course in those three categories on a scale from 1 to 5.
```
> Enter your option: 2
> Enter course code: D0017D

Enter your evaluation score for course Initial Programming in Java.

> Teacher: 5
> Course contents: 3
> Examination: 4

Your evaluation was registered, thank you!
```

### Printing evaluation summary for a course
It should be possible to print evaluation summary for a given course. You should display all grades, given by the students, number of submitted evaluations, average values for all categories, as well as the overall average grade. All average grades should be rounded to two (2) decimals.

```
> Enter your option: 3
> Enter course code: D0017D

Evaluation history:
Teacher   Contents   Examination
5         3          4
4         3          4
2         5          5
5         5          5
5         3          2
1         4          5

Number of evaluations: 6

Average score
Teacher: 3.67
Contents: 3.83
Examination: 4.17
Total average: 3.89
```

### Printing course list (sorted by course code)
It should be possible to print all courses. List should be sorted by course code (A-Z). The list should include course code, course name, average category scores, as well as the total average scores.

```
> Enter your option: 4
Code     Name                           Teacher    Contents   Exam     Average
D0017D   Initial Programming in Java    3.67       3.83       4.17     3.89
R0007N   Real Time Systems              4.20       4.80       3.67     4.22
X7017E   Project in Computer Science    2.88       3.10       3.26     3.08
```

### Printing course list (sorted by evaluation score)
It should be possible to print all courses, sorted by average evaluation grade (from highest to lowest). The list should include same information, as the list in previous part. **Important: if you do not have time, you will still get points if you manage to present the correct unsorted data!**

```
> Enter your option: 5
Code     Name                           Teacher    Contents   Exam     Average
R0007N   Real Time Systems              4.20       4.80       3.67     4.22
D0017D   Initial Programming in Java    3.67       3.83       4.17     3.89
X7017E   Project in Computer Science    2.88       3.10       3.26     3.08
```

## Instructions
The program should have necessary error handling (ways of validating the input, etc.). The program should under no circumstances crash when receiving incorrect input. It is allowed to store additional information, other than provided in the assignment description. You should create appropriate data structures to store the information. All floating point numbers should be rounded up to two (2) decimals. The programs that compile with the compilation error will not be graded (it should be possible to run the program).

## Tips
* Do one functionality/method/option at a time. It is possible to pass the assignment by not finalizing all the parts! Leave the hardest parts till the end. For example, First get the program working for correct/valid inputs. Then update the program to handle error conditions, such as checking if a number is entered for price.
* Write down requirements for each function as comment on top of the function, this will help you not to forget a requirement
* Note that there are 7 points available for two printing options (4 and 5 in Memu) and 4 points for printing evaluation history (Menu option 3). Maybe you can start with these three functionalities. If you are wondering how I can test them without data, you can hard code data to your array.



## Assumptions
* If you notice that some information is missing from the assignment description, you are allowed to make own assumptions as long as it does not change the condition and basis of the assignment.
* It is ok to assume fixed number for your data structures, for example, it is ok to assume that there can be maximum 10 courses and 100 course evaluations at most for all courses included. This is just an example. This is so that you dont have to handle expanding the array to add more data. 

## Requirements
* **_Please do not forget to include your name, LTU username on the top of the file as a author_**
* You are free to use either replit or any IDE of your choice.
* Do not use packages (there should not be any code, beginning with package).
* Follow the course’s coding conventions and formatting conventions (method comments, usage of constants, indentation etc.).
* Use methods in your solution.
* It is not allowed to use any global variables (only global Scanner objects are allowed).
* It is not allowed to use built in functionality (such as ArrayLists etc.), in other words, same rules as your assignments. In other words, only import statement you will have is ```import java.util.Scanner; ```. **It is ok to use Random library as well.**
* The assignment is to be solved independently! It is allowed to get inspiration from the Internet, but you are to solve the task by your own! Always provide a source for your information that you get from external sources. **_NOTE! It is not allowed to copy entire code sections!_** You are, however, allowed to copy the code, that you have written yourself.
  
## Submission
You must submit a single java file to canvas.
* If you are using your own IDE, such as eclipse, make sure your filename is called ```Main.java``` and class name is called ```Main```. Upload this single file Main.java to canvas. 
* You can upload multiple times to Canvas.

# Examination Correction Matrix

**Maximum of 21 points, 12 points needed to pass**

## Functionality 17p
### Menu - 1p The menu is printed and there is validation.
* The menu is presented - 1p

### Register new course - 3p
* Check if there is a course with the same code - 1p
* Check course code validity (format) - 1p
* Store information - 1p

### Course evaluation - 2p
* Check if course exists - 0.5p
* Store and validate information - 1.5p

### Evaluation history - 4p
* Check if course exists - 0.5p
* Print evaluation history - 1.5p
* Compute average grades - 2p

### Print history - 7p
* Table format - 0.5p
* Correct information - 2.5p
* Sorted by code - 2p
* Sorted by grade - 2p

### Other
* Deduct 2p if program crashes

## Code style 4p
* Usage of correct/appropriate methods - 2p
* Coding guidelines - 2p
