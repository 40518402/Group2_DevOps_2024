# USE CASE: 4 Produce Detailed Reports on Population at Various Geographical Levels

## CHARACTERISTIC INFORMATION

### Goal in Context
As a _**Data Analyst**_, I want to generate population reports, so that I can analyze population data at various geographical levels.

### Scope
Population report generation system for _**United Nations**_.

### Level
Primary Task.

### Preconditions
- The database contains accurate and up-to-date population data for all continents, regions, and countries.

### Success Condition
- A detailed population report is successfully generated and available for the _**data analyst**_ to present to the _**United Nations**_.

### Failed Condition
- No report is generated due to missing or incomplete data.
- Incorrect or inaccurate information in the report.

### Primary Actor
Data Analyst.

### Trigger
- The _**data analyst**_ initiates the report generation by selecting a geographical level (continent, region, or country).

## MAIN SUCCESS SCENARIO

1. The _**United Nations**_ requests a population report for a specific geographical level.
2. The _**data analyst**_ selects the geographical level (continent, region, or country) for the report.
3. The _**data analyst**_ provides optional parameters, such as limiting the report to the top N populated countries.
4. The system retrieves data from the database.
5. The system calculates total population, urban population (including percentage), and rural population (including percentage).
6. The report is generated and presented in a readable format for the _**data analyst**_.
7. The _**data analyst**_ provides the report to the _**United Nations**_.

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

5. **Missing or Incomplete Data:**
   1. If population data for a selected geographical level is missing or incomplete, the system notifies the _**data analyst**_ and halts the report generation.
   2. The _**data analyst**_ may then retry with different parameters or address the data issues.

## SUB-VARIATIONS

1. **Report for Spoken Languages:**
   1. The _**United Nations**_ requests a population report for the number of people who speak Chinese, English, Hindi, Spanish and Arabic in descending order.

## SCHEDULE

- **DUE DATE:** Release 1.0.
