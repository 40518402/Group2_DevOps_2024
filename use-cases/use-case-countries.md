# USE CASE: 1 Produce Detailed Reports on Countries at Various Geographical Levels

## CHARACTERISTIC INFORMATION

### Goal in Context
As a _**Data Analyst**_, I want to *produce detailed reports on countries*, so that *I can analyze and compare populations at various geographical levels (world, continent, region).*

### Scope

Population report generation system for _**United Nations**_.

### Level

Primary task.

### Preconditions

Database contains population data for all countries.

### Success End Condition

A report is available for the _**data analyst**_ to provide to the _**United Nations**_.

### Failed End Condition

No report is produced.

### Primary Actor

Data Analyst.

### Trigger
The *data analyst* initiates the report generation by selecting geographical levels and, optionally, providing an N value for top-populated countries.

## MAIN SUCCESS SCENARIO

1. _**United Nations**_ requests a detailed report on country information regarding population.
2. The _**data analyst**_ selects the geographical level (world, continent, or region) or provides an N value to filter the top N populated countries.
3. The system generates the report and displays it to the _**data analyst**_ in a readable format.
4. _**Data analyst**_ provides report to the _**United Nations**_.

## EXTENSIONS

3. **Missing or Incomplete Data:**
    1. If population data for a selected geographical level is missing or incomplete, the system notifies the _**data analyst**_ and halts the report generation.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0