/*ARCH 4803*/
INSERT INTO Course VALUES ("ARCH 4803",
"Green Infrastructure: EPA Rainwater Challenge",26,"Richard Dagenhart",
"Sustainable Communities");
INSERT INTO Course_Is_Category VALUES ("Green Infrastructure: EPA Rainwater Challenge","computing for good");
INSERT INTO Course_Is_Category VALUES ("Green Infrastructure: EPA Rainwater Challenge","doing good for your neighborhood");
/*BMED 2250*/
INSERT INTO Course VALUES ("BMED 2250",
"Problems in Biomedical Engineering",300,"Barbara Burks Fasse",
"Community");
INSERT INTO Course_Is_Category VALUES ("Problems in Biomedical Engineering","computing for good");
INSERT INTO Course_Is_Category VALUES ("Problems in Biomedical Engineering","doing good for your neighborhood");
/*PUBP 3315*/
INSERT INTO Course VALUES ("PUBP 3315",
"Environmental Policy and Politics",25,"Alice Favero",
"Sustainable Communities");
INSERT INTO Course_Is_Category VALUES ("Environmental Policy and Politics","computing for good");
INSERT INTO Course_Is_Category VALUES ("Environmental Policy and Politics","sustainable communities");
/*EAS 2803*/
INSERT INTO Course VALUES ("EAS 2803",
"Urban Forest",10,"Monica Halka",
"Sustainable Communities");
INSERT INTO Course_Is_Category VALUES ("Urban Forest","urban development");
INSERT INTO Course_Is_Category VALUES ("Urban Forest","sustainable communities");
/*BIOL 1511*/
INSERT INTO Course VALUES ("BIOL 1511",
"Honors Biological Principles; Honors Organismal Biology",150,"Brian Hammer",
"Sustainable Communities");
INSERT INTO Course_Is_Category VALUES ("Honors Biological Principles; Honors Organismal Biology","sustainable communities");
/*EAS 1600*/
INSERT INTO Course VALUES ("EAS 1600",
"Introduction to Environmental Science",600,"Dana Hartley",
"Community");
INSERT INTO Course_Is_Category VALUES ("Introduction to Environmental Science","urban development");
INSERT INTO Course_Is_Category VALUES ("Introduction to Environmental Science","sustainable communities");
/*EAS 1601*/
INSERT INTO Course VALUES ("EAS 1601",
"Habitable Planet",600,"Dana Hartley","Community");
INSERT INTO Course_Is_Category VALUES ("Habitable Planet","urban development");
INSERT INTO Course_Is_Category VALUES ("Habitable Planet","sustainable communities");
/*EAS 2750*/
INSERT INTO Course VALUES ("EAS 2750",
"Physics of the Weather",30,"Dana Hartley","Community");
INSERT INTO Course_Is_Category VALUES ("Physics of the Weather","urban development");
INSERT INTO Course_Is_Category VALUES ("Physics of the Weather","sustainable communities");
/*Need at least 13 more courses to meet guidelines:
At least 10 total courses must have more than one category*/
/*Created Data*/
INSERT INTO Course VALUES ("BIO 1220", "Biology of Sex & Death", 30, "Kim", "Community");
INSERT INTO Course VALUES ("Bio 2344", "Genetics",45, "Sweat", "Sustainable Communities");
INSERT INTO Course VALUES ("BIO 3755", "Human Physiology", 44, "Kim", "Sustainable Communities");
INSERT INTO Course VALUES ("CS 4002", "Robots and Society", 100, "Jong", "Community");
INSERT INTO Course VALUES ("CS 4235", "Intro to Info Security",38, "Le", "Community");
INSERT INTO Course VALUES ("CS 4261", "Mobile Apps & Services",32, "Le", "Community");
INSERT INTO Course VALUES ("CX 4230", "Computer Simulation",42, "Stanzione", "Sustainable Communities");
INSERT INTO Course VALUES ("CX 4232", "SIM & Military Gaming",88, "Bukrat", "Community");
INSERT INTO Course VALUES ("CX 4242", "Data & Visual Analytics",100, "Beez", "Sustainable Communities");
INSERT INTO Course VALUES ("MATH 1554", "Linear Algebra", 6, "Wang", "Sustainable Communities");
INSERT INTO Course VALUES ("MATH 2552", "Differential Equations",99, "Alan", "Sustainable Communities");
INSERT INTO Course VALUES ("MATH 4280", "Information Theory",15, "Kim", "Community");
/*Categories*/
INSERT INTO Course_Is_Category VALUES ('Biology of Sex & Death', 'doing good for your neighborhood'); 
INSERT INTO Course_Is_Category VALUES ('Biology of Sex & Death', 'reciprocal teaching and learning'); 
INSERT INTO Course_Is_Category VALUES ('Genetics', 'reciprocal teaching and learning');
INSERT INTO Course_Is_Category VALUES ('Human Physiology', 'crowd sourced');
INSERT INTO Course_Is_Category VALUES ('Human Physiology', 'collaborative action');  
INSERT INTO Course_Is_Category VALUES ('Robots and Society', 'computing for good');
INSERT INTO Course_Is_Category VALUES ('Robots and Society', 'urban development');
INSERT INTO Course_Is_Category VALUES ('Robots and Society', 'collaborative action');
INSERT INTO Course_Is_Category VALUES ('Intro to Info Security', 'computing for good'); 
INSERT INTO Course_Is_Category VALUES ('Intro to Info Security', 'crowd sourced'); 
INSERT INTO Course_Is_Category VALUES ('Mobile Apps & Services', 'computing for good'); 
INSERT INTO Course_Is_Category VALUES ('Mobile Apps & Services', 'crowd sourced'); 
INSERT INTO Course_Is_Category VALUES ('Mobile Apps & Services', 'technology for social good'); 
INSERT INTO Course_Is_Category VALUES ('Computer Simulation', 'computing for good'); 
INSERT INTO Course_Is_Category VALUES ('SIM & Military Gaming', 'computing for good'); 
INSERT INTO Course_Is_Category VALUES ('SIM & Military Gaming', 'technology for social good'); 
INSERT INTO Course_Is_Category VALUES ('Data & Visual Analytics', 'computing for good'); 
INSERT INTO Course_Is_Category VALUES ('Linear Algebra', 'reciprocal teaching and learning'); 
INSERT INTO Course_Is_Category VALUES ('Linear Algebra', 'adaptive learning'); 
INSERT INTO Course_Is_Category VALUES ('Differential Equations', 'reciprocal teaching and learning'); 
INSERT INTO Course_Is_Category VALUES ('Differential Equations', 'adaptive learning'); 
INSERT INTO Course_Is_Category VALUES ('Information Theory', 'reciprocal teaching and learning'); 
INSERT INTO Course_Is_Category VALUES ('Information Theory', 'adaptive learning'); 