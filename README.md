CarRent MVC

A car rental web application developed with Java and Spring Boot. Customers can search for available cars for a selected date range and make reservations. Administrators can manage cars and view reservations through a protected administration interface.

Features

Date-based car search: Lists active cars available for the selected rental period, including daily rental prices.

Reservation management: Collects customer contact details and saves reservations.

Date validation and conflict prevention: Validates the rental period and checks for overlapping reservations to prevent the same car from being booked for conflicting dates.

Price calculation: Calculates the rental duration and total reservation price on the server.

Administration interface: Displays car details, reservation information and total rental prices in tables.

Car management: Allows administrators to edit car details and deactivate cars without deleting their records.

Admin access protection: Requires a valid API key supplied as a URL query parameter. The key is checked by the controller before granting access to administrative functions.

Technologies

Java 21

Spring Boot 4.1.1

Spring MVC

Spring Data JDBC

Thymeleaf

MySQL

Maven

Architecture

The application follows the Model–View–Controller (MVC) pattern. Controllers handle HTTP requests, services implement business logic, repositories provide database access, DTOs transfer data between layers, and Thymeleaf renders the HTML views.

Running the application

Configure the MySQL connection and administrator API keys in src/main/resources/application.properties. Run the Spring Boot application and open http://localhost:8080 in a browser.