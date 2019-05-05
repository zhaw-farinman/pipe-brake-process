--INSERTS PERSONS
INSERT INTO PERSON (ID, NAME, LASTNAME) VALUES(33, 'Hans', 'Mustermann');
--INSERTS BUILDING
INSERT INTO BUILDING (ID, STREET, BUILDING_NUMBER, BUILDING_STATE, COUNTRY, PLACE, POST_CODE) VALUES(5, 'Seestrasse', 1, 4, 'Switzerland', 'Zurich', '8001');
--INSERTS DWELLINGS
INSERT INTO DWELLING (ID, FLOOR, DOOR, BUILDING_ID) VALUES(123, 2, 2, 5);
--INSERTS DWELLINGS_PERSONS
INSERT INTO DWELLING_PERSONS (PERSON_ID, DWELLING_ID) VALUES(33, 123);
--INSERTS DEVICE
INSERT INTO DEVICE (ID, DWELLING_ID) VALUES('2e001f001247343438323536', '123');
INSERT INTO DEVICE (ID, DWELLING_ID) VALUES('2e001f001247338323536578', '123');
INSERT INTO DEVICE (ID, DWELLING_ID) VALUES('2e001f001247343832336Z9J', '123');
INSERT INTO DEVICE (ID, DWELLING_ID) VALUES('2e001f001247343832353693', '123');
INSERT INTO DEVICE (ID, DWELLING_ID) VALUES('2e001f001247343832353634', '123');
