# USE CASE: Population Report

## CHARACTERISTIC INFORMATION

### Goal in Context
As a user, I want to generate population reports, so that I can analyze population data at various geographical levels (continent, region, or country).

### Scope
The scope of this use case is within the organization. The system is expected to generate reports that provide population information for geographical levels (continent, region, or country) with detailed breakdowns of urban and rural populations.

### Level
Primary Task.

### Preconditions
- The database must contain accurate and up-to-date population data for all continents, regions, and countries.

### Success Condition
- A detailed population report is successfully generated and available for the user to present to the organization.

### Failed Condition
- No report is generated due to missing or incomplete data.
- Incorrect or inaccurate information in the report.

### Primary Actor
User.

### Trigger
- The user initiates the report generation by selecting a geographical level (continent, region, or country).

## MAIN SUCCESS SCENARIO

1. The organisation requests a population report for a specific geographical level.
2. The user selects the geographical level (continent, region, or country) for the report.
3. The user provides optional parameters, such as limiting the report to the top N populated countries.
4. The system retrieves data from the database.
5. The system calculates total population, urban population (including percentage), and rural population (including percentage).
6. The report is generated and presented in a readable format for the user.
7. The user provides the report to the organisation.

## REPORT CONTENTS

The population report will include the following information:

1. **The name of the continent/region/country.**
    - The selected geographical level for the report.

2. **The total population of the continent/region/country.**
    - Displays the total population for the selected geographical level.

3. **The total population of the continent/region/country living in cities (including a percentage).**
    - Displays the population living in cities and calculates the percentage of the total population this represents.

4. **The total population of the continent/region/country not living in cities (including a percentage).**
    - Displays the population not living in cities and calculates the percentage of the total population this represents.

## EXTENSIONS

1. **Missing or Incomplete Data:**
    - If population data for a selected geographical level is missing or incomplete, the system notifies the user and halts the report generation.
    - The user may then retry with different parameters or address the data issues.

## SUB-VARIATIONS

1. **Report for Top N Populated Countries:**
    - The user can opt to filter the report to only show the top N populated countries at the selected geographical level.

## SCHEDULE

- **DUE DATE:** Release 1.0.
