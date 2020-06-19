# CheapestStopover

Cheapest Room API

To run this app, 
  - checkout this repo to your local machine
  - navigate to the root directory of this repo on your local machine via Terminal or CommandLine
  - execute the command: mvn spring-boot:run
  
Please not that when you navigate to API in a webbrowser you will be asked for a username and password
  - username: user
  - password: {printed on command line when you run mvn spring-boot:run. Look for: "Using generated security password:" log item}
  
GET: /api/getCheapestRooms
- Mandatory parameter: cityCode
- Optional parameter: checkInDate (defaults to today's date if not supplied)
- Optional parameter: checkOutDate (defaults to tomorrow's date if not supplied)

If this app is running on your localhost port:8080 then a sample REST call would be:
  - Example1: http://localhost:8080/api/getCheapestRooms?cityCode=PAR&checkInDate=2020-06-16&checkOutDate=2020-06-17
  - Example2: http://localhost:8080/api/getCheapestRooms?cityCode=LON&checkInDate=2020-06-16&checkOutDate=2020-06-17
  - Example3: http://localhost:8080/api/getCheapestRooms?cityCode=NYC
