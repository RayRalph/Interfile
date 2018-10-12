Interfile Assessment Design decisions
-----------------------------------------------

Technologies used:
------------------
    Java8
    JEE7
    JPA
    JaxB
    Primefaces 6.2
    MySQL
    Glassfish 4.1.1
    Maven
    JUnit

------------------
Attached Documents:
------------------
    domain.xml
    interfileDB.mwb (Database Model)
    AssessmentERD.png (ERD Diagram)

---------------
The Application
---------------
The decision was made to go with Primefaces and JPA without Hibernate due to time constraints.
Entity files each have Named queries which are used and session beans were created from them.
Reading of the xml files was done via the file upload function in Primefaces, the button is located on the footer panel on the left (bottom left of the screen),
thus giving the user the ability of uploading the file at any time. 
The xsd file provided was used to design the database tables, there are 5 tables each table is represented by a screen, each list screen has print functionality.
Each screen has Create, View, Edit and Delete functionality.

-----------------
Points of clarity
-----------------
I needed some clarity on the following point of the assessment:
    4. Show a summary of all account holders and outstanding amounts.

I used the billing screen and added the account user details to it.
I was not sure if the sum total of each account owner's outstanding amounts was required in this instance.
