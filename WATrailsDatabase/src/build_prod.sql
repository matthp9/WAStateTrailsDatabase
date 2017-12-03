USE prod;

DROP TABLE IF EXISTS `Trail`;
DROP TABLE IF EXISTS `TrailHead`;
DROP TABLE IF EXISTS `DifficultyLevel`;
DROP TABLE IF EXISTS `CampsiteMapping`;
DROP TABLE IF EXISTS `Campsite`;
DROP TABLE IF EXISTS `AmenityMapping`;
DROP TABLE IF EXISTS `Amenity`;
DROP TABLE IF EXISTS `PolicyMapping`;
DROP TABLE IF EXISTS `Policy`;
DROP TABLE IF EXISTS `ParkSiteMapping`;
DROP TABLE IF EXISTS `ParkSite`;
DROP TABLE IF EXISTS `SiteFeatureMapping`;
DROP TABLE IF EXISTS `SiteFeature`;

CREATE TABLE Trail (
  trailId INT AUTO_INCREMENT NOT NULL,
  trailName VARCHAR(64) NOT NULL,
  difficultyLevelId INT NOT NULL,
  length FLOAT NOT NULL,
  elevationGain FLOAT NOT NULL,
  PRIMARY KEY (trailId)
);

CREATE TABLE TrailHead (
  trailHeadId INT AUTO_INCREMENT NOT NULL,
  trailId INT NOT NULL,
  latitude FLOAT NOT NULL,
  longitude FLOAT NOT NULL,
  city VARCHAR(32),
  PRIMARY KEY (trailHeadId)
);

CREATE TABLE DifficultyLevel (
  levelId INT AUTO_INCREMENT NOT NULL,
  description VARCHAR(32),
  PRIMARY KEY (levelId)
);

CREATE TABLE CampsiteMapping (
  mappingId INT AUTO_INCREMENT NOT NULL,
  campsiteId INT NOT NULL,
  trailId INT NOT NULL,
  PRIMARY KEY (mappingId)
);

CREATE TABLE Campsite (
  campsiteId INT AUTO_INCREMENT NOT NULL,
  numSpaces INT,
  elevation INT,
  PRIMARY KEY (campsiteId)
);

CREATE TABLE AmenityMapping (
  mappingId INT AUTO_INCREMENT NOT NULL,
  trailId INT NOT NULL,
  amenityId INT NOT NULL,
  PRIMARY KEY (mappingId)
);

CREATE TABLE Amenity (
  amenityId INT AUTO_INCREMENT NOT NULL,
  name VARCHAR(64),
  PRIMARY KEY (amenityId)
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

CREATE TABLE ParkSiteMapping (
  mappingId INT AUTO_INCREMENT NOT NULL,
  trailHeadId INT,
  parkingLotId INT,
  PRIMARY KEY (mappingId)
);

CREATE TABLE ParkSite (
  parkSiteId INT AUTO_INCREMENT NOT NULL,
  numSpots INT,
  PRIMARY KEY (parkSiteId)
);

CREATE TABLE SiteFeatureMapping (
  mappingId INT AUTO_INCREMENT NOT NULL,
  siteFeatureId INT,
  parkSiteId INT,
  PRIMARY KEY (mappingId)
);

CREATE TABLE SiteFeature (
  featureId INT AUTO_INCREMENT NOT NULL,
  description VARCHAR(64),
  PRIMARY KEY (featureId)
);