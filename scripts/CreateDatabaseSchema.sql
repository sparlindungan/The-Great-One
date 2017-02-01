CREATE TABLE Department (
DeptName VARCHAR(255),
PRIMARY KEY (DeptName)
) ENGINE=InnoDB;
CREATE TABLE Major (
MajorName VARCHAR(255),
DeptName VARCHAR(255) NOT NULL,
PRIMARY KEY (MajorName),
Foreign KEY (DeptName) REFERENCES Department(DeptName)
) ENGINE=InnoDB;
CREATE TABLE Student (
Username VARCHAR(15) NOT NULL,
Password VARCHAR(10) NOT NULL,
Email VARCHAR(255) NOT NULL,
GradYear VARCHAR(20),
MajorName VARCHAR(255),
User_Type BOOLEAN NOT NULL,
UNIQUE (Email),
CONSTRAINT uname PRIMARY KEY(Username),
FOREIGN KEY(MajorName) REFERENCES Major(MajorName) ON UPDATE CASCADE
) ENGINE=InnoDB;
CREATE TABLE Designation (
Name VARCHAR(255) NOT NULL,
PRIMARY KEY (Name)
) ENGINE=InnoDB;
CREATE TABLE Project (
Name VARCHAR(255) NOT NULL,
EstNumberStudents INT NOT NULL,
AdvisorName VARCHAR(255) NOT NULL,
AdvisorEmail VARCHAR(255) NOT NULL,
Description VARCHAR(1000) NOT NULL,
Designation VARCHAR(255) NOT NULL,
PRIMARY KEY(Name),
FOREIGN KEY(Designation) REFERENCES Designation(Name) ON UPDATE CASCADE
) ENGINE=InnoDB;
CREATE TABLE Requirement (
Pname VARCHAR(255) NOT NULL,
Requirement VARCHAR(255),
PRIMARY KEY (Pname,Requirement),
FOREIGN KEY (Pname) REFERENCES Project(Name)
) ENGINE=InnoDB;
CREATE TABLE Application (
Username VARCHAR(15) NOT NULL,
ProjName VARCHAR(255) NOT NULL,
AppDate DATE NOT NULL,
Status VARCHAR(10) NOT NULL,
CONSTRAINT uName_projName PRIMARY KEY (Username,Projname),
CONSTRAINT app_uName FOREIGN KEY (Username) REFERENCES Student(Username),
CONSTRAINT pName FOREIGN KEY (ProjName) REFERENCES Project(Name)
) ENGINE=InnoDB;
CREATE TABLE Course (
CourseNumber VARCHAR(50) NOT NULL,
CourseName VARCHAR(100) NOT NULL,
EstNumberStudents INT NOT NULL,
Instructor VARCHAR(255) NOT NULL,
Designation VARCHAR(255) NOT NULL,
UNIQUE (CourseNumber),
CONSTRAINT Course_cName PRIMARY KEY (CourseName),
FOREIGN KEY (Designation) REFERENCES Designation(Name) ON UPDATE CASCADE
) ENGINE=InnoDB;
CREATE TABLE Category (
Name VARCHAR(255) NOT NULL,
PRIMARY KEY (Name)
) ENGINE=InnoDB;
CREATE TABLE Proj_Is_Category (
ProjName VARCHAR(255) NOT NULL,
CatName VARCHAR(255) NOT NULL,
CONSTRAINT cat_proj PRIMARY KEY (ProjName,CatName), 
FOREIGN KEY (CatName) REFERENCES Category(Name),
FOREIGN KEY (ProjName) REFERENCES Project(Name)
) ENGINE=InnoDB;
CREATE TABLE Course_Is_Category (
CourseName VARCHAR(255) NOT NULL,
CatName VARCHAR(255) NOT NULL,
CONSTRAINT cNum_cat PRIMARY KEY (CourseName,CatName),
CONSTRAINT cNum_fk FOREIGN KEY (CourseName) REFERENCES Course(CourseName),
FOREIGN KEY (CatName) REFERENCES Category(Name)
) ENGINE=InnoDB;