DELETE FROM location;
INSERT INTO location(locationID, name, description, address, latitude, longitude) VALUES
(1, 'Downtown Main', 'Downtown in Kingsplace by Plaza', '123 Main St, Kingsplace, NY', '40.71455', '-74.00712'),
(2, 'University Central', 'On campus by Central Ave', '88 Central Ave, Queensplace, NY', '33.77391', '-84.53921'),
(3, 'The Duckpond', 'The Duckpond in the middle of city park', '11 University Ave, Kingsplace, NY', '44.43777', '-63.63548'),
(4, 'The Plaza', 'Center of  City Park', '456 Plaza Way, Longsplace, NY', '51.88423', '-97.39123');

DELETE FROM organization;
INSERT INTO organization (organizationID, name, description, address, contact) VALUES
(1, 'Justice Federation', 'Heros for justice', '555 Main St, Kingsplace, NY', '222-333-4455'),
(2, 'Freedom Leauge', 'Freedom for all', '123 Harvard Ave, Queensplace, NY', '444-222-5533'),
(3, 'Chaos Charter', 'Creators of chaos', '777 Park Pl, Jokersplace, NY', '222-888-9955'),
(5, 'The Bad Guys', 'All around bad guys doing bad things', '2224 Hyjinks Ave, Gremlinsplace, NY', '222-777-8899'),
(6, 'The Hero Longhouse', 'Group of viking heros', '104 Viking Way, Norsplace, NY', '222-555-1133');