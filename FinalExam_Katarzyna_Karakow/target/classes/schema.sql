CREATE TABLE AvailableSpots(
spotNo int NOT NULL AUTO_INCREMENT, 
spotLocation varchar(30) NOT NULL, 
spotsAvailable boolean NOT NULL, 
PRIMARY KEY (SpotNo)
);

CREATE TABLE ParkingDetails (
ticketNo int NOT NULL AUTO_INCREMENT,
spotNo int NOT NULL,
plateNo varchar(30) NOT NULL,
date varchar(30) NOT NULL,
PRIMARY KEY (ticketNo),
FOREIGN KEY (spotNo) REFERENCES AvailableSpots(spotNo) 
);

