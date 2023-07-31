DROP DATABASE IF EXISTS superheroSightingsTest;
CREATE DATABASE superheroSightingsTest;
USE superheroSightingsTest;


-- Create the Superpower table
CREATE TABLE Superpower (
	superpowerID INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(50) NOT NULL	
);


-- Create the Hero table
CREATE TABLE Hero (
	heroId INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
    description VARCHAR(255) NOT NULL,
	superpowerID INT,
	FOREIGN KEY (superpowerID) REFERENCES Superpower(superpowerID)
);

-- Create the Organization table
CREATE TABLE Organization(
	organizationID INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description TINYTEXT,
    address VARCHAR(100),
    contact VARCHAR(100)
);

-- Create the Location table
CREATE TABLE Location (
	locationID INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50),
    description TINYTEXT,
    address VARCHAR(100),
    latitude VARCHAR(10),
    longitude VARCHAR(10)
);

-- Create the Sighting table
CREATE TABLE Sighting (
  sightingID INT PRIMARY KEY AUTO_INCREMENT,
  sightingDate DATETIME NOT NULL,
  locationID INT NOT NULL,
  FOREIGN KEY (locationID) REFERENCES Location(locationID)
);

-- Create the HeroOrganization table
CREATE TABLE HeroOrganization (
    heroID INT,
    organizationID INT,
    CONSTRAINT pk_HeroOrganization
    	PRIMARY KEY (heroID, organizationID),
    CONSTRAINT fk_pk_HeroOrganization_Hero
    	FOREIGN KEY (heroID)
    	REFERENCES Hero(heroID),
    CONSTRAINT fk_pk_HeroOrganization_Organization
    	FOREIGN KEY (organizationID)
    	REFERENCES Organization(organizationID)
);

-- Create the HeroSighting table
CREATE TABLE HeroSighting (
    heroID INT,
    sightingID INT,
    CONSTRAINT pk_HeroSighting
    	PRIMARY KEY (heroID, sightingID),
    CONSTRAINT fk_pk_HeroSighting_Hero
    	FOREIGN KEY (heroID)
    	REFERENCES Hero(heroID),
    CONSTRAINT fk_pk_HeroSighting_Sighting
    	FOREIGN KEY (sightingID)
    	REFERENCES Sighting(sightingID)
);

