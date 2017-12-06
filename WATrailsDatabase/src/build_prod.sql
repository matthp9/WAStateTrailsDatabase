USE prod;

DROP TABLE IF EXISTS `Trail`;
DROP TABLE IF EXISTS `TrailHead`;
DROP TABLE IF EXISTS `Location`;
DROP TABLE IF EXISTS `PolicyMapping`;
DROP TABLE IF EXISTS `Policy`;

CREATE TABLE Trail (
  trailId INT AUTO_INCREMENT NOT NULL,
  trailName VARCHAR(64) NOT NULL,
  locationId INT,
  rating FLOAT,
  length FLOAT,
  elevationGain FLOAT,
  hasCampsites BIT(1),
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

CREATE TABLE Policy (
  policyId INT AUTO_INCREMENT NOT NULL,
  kidFriendly BIT(1),
  dogFriendly BIT(1),
  PRIMARY KEY (policyId)
);
