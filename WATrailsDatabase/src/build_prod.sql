CREATE DATABASE prod;
USE prod;

DROP TABLE IF EXISTS `Trail`;
DROP TABLE IF EXISTS `TrailHead`;
DROP TABLE IF EXISTS `Location`;
DROP TABLE IF EXISTS `PolicyMapping`;
DROP TABLE IF EXISTS `Policy`;

CREATE TABLE Trail (
  trailId INT AUTO_INCREMENT NOT NULL,
  trailName VARCHAR(64) NOT NULL,
  rating FLOAT NOT NULL,
  length FLOAT NOT NULL,
  elevationGain FLOAT NOT NULL,
  PRIMARY KEY (trailId)
);

CREATE TABLE TrailHead (
  trailHeadId INT AUTO_INCREMENT NOT NULL,
  trailId INT,
  description VARCHAR(64),
  PRIMARY KEY (trailHeadId)
);

CREATE TABLE Location (
  locationId INT AUTO_INCREMENT NOT NULL,
  description VARCHAR(128),
  PRIMARY KEY (locationId)
);

CREATE TABLE PolicyMapping (
  mappingId INT AUTO_INCREMENT NOT NULL,
  trailId INT,
  policyId INT,
  PRIMARY KEY (mappingId)
);

CREATE TABLE Policy (
  policyId INT AUTO_INCREMENT NOT NULL,
  description VARCHAR(128),
  PRIMARY KEY (policyId)
);
