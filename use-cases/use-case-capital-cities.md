# USE CASE: 3 Produce Detailed Reports on Capital Cities at Various Geographical Levels

## Characteristic Information

### Goal in Context

As a *user* I want to *produce detailed reports on capital cities*, so that *I can analyze and compare populations at various geographical levels*.

### Scope 

Organization

### Level

Primary Task

### Preconditions

Database contains a table structured with information about capital cities, their populations, continents, and regions.

### Success End Condition

The system successfully generates the requested report, showing *capital cities organized by population (from largest to smallest)* at the desired *geographical level (world, continent, region)*, or *optionally limiting the results to the top N cities as specified by the user*.

### Failed End Condition

- The query fails to retrieve the expected data (due to errors such as invalid syntax, connection issues, or missing data).
- System displays incorrect or incomplete data.

### Primary Actor

User

### Trigger

The user triggers the process by selecting options within the application to generate a report, specifying the desired geographical level (world, continent, region), and optionally entering the value of N to limit the results to the top N populated cities.

## Main Success Scenario

1. The user requests a detailed report on the capital cities of a country.
2. The user selects the geographical level *(world, continent, region)*; optionally, they specify a value for *N to limit the results to the top N populated capital cities*. Based on the user’s input, the  application dynamically constructs the appropriate query.
3. The database processes the query, sorting the *capital cities* by population in order, or *optionally limits the result to the top N populated cities*.
4. The application retrieves the result set and displays the data to the user, formatted as a report.
5. The user views or exports the report.

### Extensions

- If the system cannot establish a connection to the database, it shows an error message to the user and prompts them to retry.

### Sub-Variations

### Schedule

**DUE DATE**: Release 1.0