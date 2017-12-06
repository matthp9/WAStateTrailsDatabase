USE prod;

/*
DROP TABLE HikerCategory;
DROP TABLE  Hiker;
DROP TABLE PathFinder;
*/

CREATE TABLE Hiker (
  hikerId INT,
  pathFinderId INT,
  hikerCategoryId INT,
  name VARCHAR(50),
  gender VARCHAR(10),
  age INT,
  yearsExperience INT,
  PRIMARY KEY(hikerId)
);

CREATE TABLE HikerCategory (
  categoryId INT,
  categoryTitle VARCHAR(50),
  PRIMARY KEY(categoryId)
);

CREATE TABLE PathFinder (
  pathFinderId INT,
  hikerId INT,
  PRIMARY KEY(pathFinderId)
);

INSERT INTO Hiker (hikerId, pathFinderId, hikerCategoryId, name, gender, age, yearsExperience) VALUES
  (0, 0, 0, 'Matt', 'Male', 20, 1),
  (1, NULL, 1, 'Hasnah', 'Female', 21, 5),
  (2, 0, 0, 'Bryan', 'Male', 21, 1),
  (3, 0, 0, 'Jin', 'Male', 21, 1);

INSERT INTO HikerCategory (categoryId, categoryTitle) VALUES
  (0, 'Rookies'),
  (1, 'Total Bosses');

INSERT INTO PathFinder (pathFinderId, hikerId) VALUES
  (0, 1);
