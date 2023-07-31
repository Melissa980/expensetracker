USE superheroSightings;

INSERT INTO Superpower(name) VALUES 
	("Flying"),
	("Earthbending"),
	("Elasticity"),
	("Telekinesis"),
	("Teleportation"),
	("Telepathy"),
	("Super Speed"),
	("Super Strength"),
	("Ehanced Durability"),
	("Healing"),
	("Super Hearing"),
	("Master of Deception and Stealth"),
	("Enhanced Senses"),
	("Electromagnetic Field Maniuplation");
    
INSERT INTO Location (name, description, address, latitude, longitude) VALUES 
    ("New York capital building", "The big apple", "State St. and Washington Ave, Albany, NY 12224", "42.6529131", "-73.757271"),
    ("Metropolis", "The City of Tomorrow, home to Superman", "1938 Sullivan Lane, Metropolis, USA", "38.9784975", "-71.49161"),
    ("Xavier's School for Gifted Youngsters", "A school for mutants run by Professor Xavier", "1407 Graymalkin Lane, North Salem, NY 10560", "41.3475234", "-73.40712"),
    ("S.H.I.E.L.D. Helicarrier", "Mobile headquarters of S.H.I.E.L.D.", "Classified Location", "38.897957", "-77.6560"),
    ("Asgard", "Realm of the Asgardian gods", "Rainbow Bridge, Bifrost, Asgard", "51.5252555", "-0.13364"),
    ("Wakanda", "Advanced and secretive African nation", "Birnin Zana, Wakanda", "0.3052849", "32.55444");

INSERT INTO Organization(name, description, address, contact) VALUES 
	('Justice League', 'A team of the world\'s greatest superheroes, united to protect Earth from various threats.', 'Metropolis, USA', 'contact@justiceleague.com'),
    ('Avengers Initiative', 'A group of extraordinary individuals assembled to defend the world from formidable adversaries.', 'New York City, USA', 'avengers@starkindustries.com'),
    ('X-Men', 'A team of mutants with unique abilities, dedicated to promoting peaceful coexistence between mutants and humans.', 'Xavier\'s School for Gifted Youngsters, Westchester, USA', 'info@xavierschool.org'),
    ('Guardians of the Galaxy', 'An intergalactic group of misfits and heroes protecting the universe from cosmic threats.', 'Milano (their spaceship)', 'starlord@gotg.com'),
    ('The Fantastic Four', 'A team of explorers with superpowers who venture into the unknown to protect Earth from threats.', 'Baxter Building, New York City, USA', 'team@fantasticfour.com'),
    ('The Incredibles', 'A family of superheroes with unique powers who work together to fight crime and maintain peace.', 'Metroville', 'incredibles@heroes.com');
    
INSERT INTO Hero(name, description, superpowerID) VALUES
	("Supwerman","The man of Steel is the ultimate symbol of truth, justice, and hope.",9),
	("Captain America", "Incredibly high endurance and skilled in martial arts.", 10),
	("Storm", "A skillful fighter with the ability to control elemental forces to generate powerful storms", 14),
	("Black Widow", "Highly skillful assassin and spy which makes her the most dangerous operatives", 12),
	("Black Panther", "King of Wakanda with extreme hunting and combat abilities",13),
	("Wonder Woman", "An Amazon warrior with powers given by the Greek gods", 8),
	("Multi-Man", "Has the ability to duplicate himself, creating multiple versions of himself simultaneously.", 5);

INSERT INTO Sighting(sightingDate, locationID) VALUES 
	("2022-09-01 10:00:00", 1),
	("2023-01-01 11:00:00", 3),
	("2022-01-11 13:00:00", 4),
	("2022-12-21 10:30:00", 2),
	("2022-11-30 04:00:00", 5);
    
INSERT INTO HeroOrganization(heroID, organizationID) VALUES
    (1, 1), -- Superman belongs to Justice League
    (2, 2), -- Captain America belongs to Avengers Initiative
    (3, 3), -- Storm belongs to X-Men
    (4, 2), -- Black Widow also belongs to Avengers Initiative
    (5, 5), -- Black Panther belongs to The Fantastic Four
    (6, 1), -- Wonder Woman belongs to Justice League
    (7, 1), -- Multi-man belongs to many organizations
    (7, 2),
    (7, 3),
    (7, 4);
    
    
INSERT INTO HeroSighting(heroID, sightingID) VALUES 
	(1,1),
    (1,2),
    (2,1),
    (3,1),
    (4,1),
    (5,3);


