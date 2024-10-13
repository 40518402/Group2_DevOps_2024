# USE CASE: 2 Produce detailed reports on cities for various geographical levels.

## CHARACTERISTIC INFORMATION

### Goal in Context

As a _**Data Anaylst**_ I want to _produce detailed reports on cities_, so that _I can analyze and compare populations at various geographical levels (global, continent, region, country, district)_.

### Scope

Population report generation system for _**United Nations**_.

### Level

Primary Task

### Preconditions

* Database contains current population data of the cities.

### Success End Condition

A detailed report of the city is available for the _**data analyst**_ to provide to the _**United Nations**_..

### Failed End Condition

No report is produced or an error message is shown.

### Primary Actor

Data Analyst.

### Trigger

The *data analyst* initiates the report generation by pressing a _**city report**_ button within the application's interface. 

## MAIN SUCCESS SCENARIO

1. _**Data analyst**_ goes to the City Reports section on the application. 
2. The _**data analyst**_ selects the geographical level for the report (world, continent, region, country or district).
3. The _**data analyst**_ inputs any necessary prerequisites (the number of cities for top N reports).
4. The _**data analyst**_ presses the Generate Report button 
5. The system connects to the database and retrieves the relevant data. It also formats the data based on the _**data analyst's**_ request.
6. The _**data analyst**_ can download or export the report (optional). 

## EXTENSIONS

1. **Database Connection Error:** 
   1. If the system cannot connect to the database, then this error message will display "_Unable to connect to the database._"
2. **No Data:** 
   1. If no data is found, then this error message will display "_No data available_."
3. **Invalid Input:** 
   1. If an invalid input is submitted, then this error message will display "_Invalid input. Please try again._"

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: *Release 1.0.0*

