CREATE DATABASE CS4400T81;
USE CS4400T81;
CREATE TABLE User (
Username VARCHAR(15) NOT NULL,
Password VARCHAR(10) NOT NULL,
User_Type BOOLEAN NOT NULL,
PRIMARY KEY (Username)
);
CREATE TABLE Department (
DeptName VARCHAR(255),
PRIMARY KEY (DeptName)
);
CREATE TABLE Major (
MajorName VARCHAR(255),
DeptName VARCHAR(255) NOT NULL,
PRIMARY KEY (MajorName),
Foreign KEY (DeptName) REFERENCES Department(DeptName)
);
CREATE TABLE Student (
Username VARCHAR(15) NOT NULL,
Email VARCHAR(255) NOT NULL,
GradYear VARCHAR(20),
MajorName VARCHAR(255),
UNIQUE (Email),
CONSTRAINT uname PRIMARY KEY(Username),
FOREIGN KEY(Username) REFERENCES User(Username),
FOREIGN KEY(MajorName) REFERENCES Major(MajorName)
);
CREATE TABLE Designation (
Name VARCHAR(255) NOT NULL,
PRIMARY KEY (Name)
);
CREATE TABLE Project (
Name VARCHAR(255) NOT NULL,
EstNumberStudents INT NOT NULL,
AdvisorName VARCHAR(255) NOT NULL,
AdvisorEmail VARCHAR(255) NOT NULL,
Description VARCHAR(1000) NOT NULL,
Designation VARCHAR(255) NOT NULL,
PRIMARY KEY(Name),
FOREIGN KEY(Designation) REFERENCES Designation(Name)
);
CREATE TABLE Requirement (
Pname VARCHAR(255) NOT NULL,
Requirement VARCHAR(255),
PRIMARY KEY (Pname,Requirement),
FOREIGN KEY (Pname) REFERENCES Project(Name)
);
CREATE TABLE Application (
Username VARCHAR(15) NOT NULL,
ProjName VARCHAR(255) NOT NULL,
AppDate DATE NOT NULL,
Status VARCHAR(10),
CONSTRAINT uName_projName PRIMARY KEY (Username,Projname),
CONSTRAINT app_uName FOREIGN KEY (Username) REFERENCES Student(Username),
CONSTRAINT pName FOREIGN KEY (ProjName) REFERENCES Project(Name)
);
CREATE TABLE Course (
CourseNumber VARCHAR(50) NOT NULL,
CourseName VARCHAR(100) NOT NULL,
EstNumberStudents INT NOT NULL,
Instructor VARCHAR(255) NOT NULL,
Designation VARCHAR(255) NOT NULL,
UNIQUE (CourseName),
CONSTRAINT cNum PRIMARY KEY (CourseNumber),
FOREIGN KEY (Designation) REFERENCES Designation(Name)
);
CREATE TABLE Category (
Name VARCHAR(255) NOT NULL,
PRIMARY KEY (Name)
);
CREATE TABLE Proj_Is_Category (
CatName VARCHAR(255) NOT NULL,
ProjName VARCHAR(255) NOT NULL,
CONSTRAINT cat_proj PRIMARY KEY (CatName,ProjName), 
FOREIGN KEY (CatName) REFERENCES Category(Name),
FOREIGN KEY (ProjName) REFERENCES Project(Name)
);
CREATE TABLE Course_Is_Category (
CourseNum VARCHAR(50) NOT NULL,
CatName VARCHAR(255) NOT NULL,
CONSTRAINT cNum_cat PRIMARY KEY (CourseNum,CatName),
CONSTRAINT cNum_fk FOREIGN KEY (CourseNum) REFERENCES Course(CourseNumber),
FOREIGN KEY (CatName) REFERENCES Category(Name)
);

