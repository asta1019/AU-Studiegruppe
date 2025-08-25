# Excercises 7.5 and 7.7
drop schema if exists EP;
create schema if not exists EP;
use EP;

drop table if exists Department;
CREATE TABLE DEPARTMENT (
	Dname			VARCHAR(100),
	Dnumber			INT,
	Mgr_ssn			INT,
	Mgr_start_date	DATE,
	PRIMARY KEY		(Dnumber)
);

drop table if exists employee;
CREATE TABLE EMPLOYEE (
	FName		VARCHAR(100),
	Minit		CHAR(1),
	Lname		VARCHAR(100),
	Ssn			INT,
	Bdate		DATE,
	Address		VARCHAR(500),
	Sex			CHAR(1),
	Salary		DECIMAL(10,2),
	Super_ssn	INT,
	Dno			INT,
	PRIMARY KEY (Ssn),
    FOREIGN KEY (Dno) REFERENCES DEPARTMENT(Dnumber),
    FOREIGN KEY (Super_ssn) REFERENCES EMPLOYEE(Ssn)
);

ALTER TABLE DEPARTMENT ADD FOREIGN KEY (Mgr_ssn) REFERENCES EMPLOYEE(Ssn);
drop table if exists dept_locations;

CREATE TABLE DEPT_LOCATIONS (
	Dnumber		INT,
	Dlocation	VARCHAR(100),
	PRIMARY KEY (Dnumber, Dlocation),
    FOREIGN KEY (Dnumber) REFERENCES DEPARTMENT(Dnumber)
);

drop table if exists project;
CREATE TABLE PROJECT (
	Pname		VARCHAR(100),
	Pnumber		INT,
	Plocation	VARCHAR(100),
	Dnum		INT,
	PRIMARY KEY (Pnumber),
    FOREIGN KEY (Dnum) REFERENCES DEPARTMENT(Dnumber)
);

drop table if exists works_on;
CREATE TABLE WORKS_ON (
	Essn	INT,
	Pno		INT,
	Hours	DECIMAL(4,1),
	PRIMARY KEY (Essn, Pno),
    FOREIGN KEY (Essn) REFERENCES EMPLOYEE(Ssn),
    FOREIGN KEY (Pno) REFERENCES PROJECT(Pnumber)
);

drop table if exists dependent;
CREATE TABLE DEPENDENT (
	Essn			INT,
	Dependent_name	VARCHAR(100),
	Sex				CHAR(1),
	Bdate			DATE,
	Relationship	VARCHAR(100),
	PRIMARY KEY (Essn, Dependent_name),
    FOREIGN KEY (Essn) REFERENCES EMPLOYEE(Ssn)
);
    
INSERT INTO DEPARTMENT
VALUES
	('Research', 5, null, '1988-05-22'),
	('Administration', 4, null, '1995-01-01'),
	('Headquarters', 1, NULL, '1981-06-19');


INSERT INTO EMPLOYEE
VALUES
	('James', 'E', 'Borg', 888665555, '1937-11-10', '450 Stone, Houston, TX', 'M', 55000, NULL, 1),
	('Franklin', 'T', 'Wong', 333445555, '1955-12-08', '638 Voss, Houston, TX', 'M', 40000, 888665555, 5),
	('John', 'B', 'Smith', 123456789, '1965-01-09', '731 Fondren, Houston, TX', 'M', 30000, 333445555, 5),
	('Jennifer', 'S', 'Wallace', 987654321, '1941-06-20', '291 Berry, Bellaire, TX', 'F', 43000, 888665555, 4),
	('Alicia', 'J', 'Zelaya', 999887777, '1968-01-19', '3321 Castle, Spring, TX', 'F', 25000, 987654321, 4),
	('Ramesh', 'K', 'Narayan', 666884444, '1962-09-15', '975 Fire Oak, Humble, TX', 'M', 38000, 333445555, 5),
	('Joyce', 'A', 'English', 453453453, '1969-03-29', '980 Dallas, Houston, TX', 'M', 25000, 333445555, 5),
	('Ahmad', 'V', 'Jabbar', 987987987, '1969-03-29', '980 Dallas, Houston, TX', 'M', 25000, 987654321, 4);

UPDATE DEPARTMENT
SET Mgr_ssn = 333445555 WHERE Dnumber = 5;

UPDATE DEPARTMENT
SET Mgr_ssn = 987654321 WHERE Dnumber = 4;

UPDATE DEPARTMENT
SET Mgr_ssn = 888665555 WHERE Dnumber = 1;

INSERT INTO DEPT_LOCATIONS
VALUES
	(1, 'Houston'),
	(4, 'Stafford'),
	(5, 'Bellaire'),
	(5, 'Sugarland'),
	(5, 'Houston');

INSERT INTO PROJECT
VALUES
	('ProductX', 1, 'Bellaire', 5),
	('ProductY', 2, 'Sugarland', 5),
	('ProductZ', 3, 'Houston', 5),
	('Computerization', 10, 'Stafford', 4),
	('Reorganization', 20, 'Houston', 1),
	('Newbenefits', 30, 'Stafford', 4);

INSERT INTO WORKS_ON
VALUES
	(123456789,1,32.5),
	(123456789,2,7.5),
    (123456789,3,32.5),
	(123456789,10,7.5),
    (123456789,20,32.5),
	(123456789,30,7.5),
	(666884444,3,40.0),
	(453453453,1,20.0),
	(453453453,2,20.0),
	(333445555,2,10.0),
	(333445555,3,10.0),
	(333445555,10,10.0),
	(333445555,20,10.0),
	(999887777,30,30.0),
	(999887777,10,10.0),
	(987987987,10,35.0),
	(987987987,30,5.0),
	(987654321,30,20.0),
	(987654321,20,15.0),
	(888665555,20,NULL);

INSERT INTO DEPENDENT
VALUES
	(333445555, 'Alice', 'F', '1986-04-05', 'Daughter'),
	(333445555, 'Theodore', 'M', '1983-10-25', 'Son'),
	(333445555, 'Joy', 'F', '1958-05-03', 'Spouse'),
	(987654321, 'Abner', 'M', '1942-02-28', 'Spouse'),
	(123456789, 'Michael', 'M', '1988-01-04', 'Son'),
	(123456789, 'Alice', 'F', '1988-12-30', 'Daughter'),
	(123456789, 'Elizabeth', 'F', '1967-05-05', 'Spouse');
    
    
## 7.5
# a)
SELECT D.Dname, COUNT(E.Ssn)
FROM DEPARTMENT AS D, EMPLOYEE AS E
WHERE D.Dnumber = E.Dno
GROUP BY D.Dname
HAVING avg(E.Salary) > 30000;

# b)
SELECT D.Dname, SUM(E.Sex = 'M') AS male_employee # til booleans
FROM DEPARTMENT AS D, EMPLOYEE AS E
WHERE D.Dnumber = E.Dno
GROUP BY D.Dname
HAVING avg(E.Salary) > 30000;

SELECT D.Dname, COUNT(CASE WHEN E.Sex = 'M' THEN 1 END) AS male_employee
FROM DEPARTMENT AS D, EMPLOYEE AS E
WHERE D.Dnumber = E.Dno
GROUP BY D.Dname
HAVING avg(E.Salary) > 30000;

SELECT Dname, COUNT(*)
FROM Department, Employee
WHERE Dnumber = Dno AND Sex = 'M' AND Dno In (
											SELECT Dno
                                            FROM Employee
                                            GROUP BY Dno HAVING AVG(Salary) > 30000)
GROUP BY Dname;

## 7.7
# a)
SELECT Fname, Lname
FROM EMPLOYEE
WHERE Dno IN (SELECT Dno 
			  FROM EMPLOYEE
              WHERE Salary = (SELECT max(Salary) 
							  FROM EMPLOYEE));
        
# b)
SELECT Fname, Lname
FROM EMPLOYEE
WHERE Super_ssn IN (SELECT Ssn 
				    FROM EMPLOYEE
                    WHERE Super_ssn = '888665555');
                    
# c)
SELECT Fname, Lname
FROM EMPLOYEE
WHERE Salary > 10000 + (SELECT MIN(Salary) 
						FROM EMPLOYEE);
