# USE CASE: 2 Produce detailed reports on cities for various geographical levels.

## CHARACTERISTIC INFORMATION

### Goal in Context

As a _**user** I want to produce detailed reports on cities, so that I can analyze and compare populations at various geographical levels (global, continent, region, country, district)._

### Scope

Organization

### Level

Primary Task

### Preconditions

* Database contains current population data of the cities.

### Success End Condition

A detailed report of the city is available for the user.

### Failed End Condition

No report is produced or an error message is shown.

### Primary Actor

User.

### Trigger

The user initiates the report generation by pressing a _**city report**_ button within the application's interface. 

## MAIN SUCCESS SCENARIO

1. User goes to the City Reports section on the application. 
2. The user selects the geographical level for the report (world, continent, region, country or district).
3. The user inputs any necessary prerequisites (the number of cities for top N reports).
4. The user presses the Generate Report button 
5. The system connects to the database and retrieves the relevant data. It also formats the data based on the user's request.
6. The user can download or export the report (optional). 

## EXTENSIONS

1. **Database Connection Error**: If the system cannot connect to the database, then this error message will display "_Unable to connect to the database._"
2. **No Data**: If no data is found, then this error message will display "_No data available_."
3. **Invalid Input**: If an invalid input is submitted, then this error message will display "_Invalid input. Please try again._"

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: *Release 1.0.0*

