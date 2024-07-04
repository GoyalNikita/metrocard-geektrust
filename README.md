# Metro Card Challenege
## Author
Nikita Goyal

# Metro Train System

## Context
A new metro train has been launched from the Central station to the Airport. It is a non-stop train, which means the train will stop only at the Airport with no intermediate stops. It is also possible to return from the Airport back to the Central station. This is also a non-stop journey.

## MetroCard
Metro authority prefers money to be collected via MetroCard. MetroCard is an electronic payment utility that can be used to pay for the metro travel charges. The MetroCard is like a wallet loaded with money. Each person traveling in this metro must carry a MetroCard and each card will have a unique number.

To travel by this train, one needs a MetroCard. If the MetroCard doesn’t have sufficient balance, then the remaining cost for the travel needs to be paid by recharging the MetroCard. This auto recharge loads only the required amount of money for the journey and the station collects a 2% service fee for the transaction.

## Travel Charges
Costs for the journey are based on the passenger's age. It is categorized as below:
- **ADULT**: $100
- **KID**: $50
- **SENIOR_CITIZEN**: $80

## Journey Types
Travel charges are different for a single trip and for a return journey. When a passenger takes a return journey, there is a discount of 50% for the travel charges of the return journey.

For example, if a senior citizen travels from Central to Airport, the travel charge collected is $80. If the same citizen travels back to Central station, the amount collected for the return journey is $40. If the same citizen passes a third time on the same day, it will be treated as a new single journey and the travel charge collected is $80.

## Goal
Your task is to build a solution that calculates various travel charges collected at each station and print the collection summary and passenger summary.

### Collection Summary
The collection summary should give a breakup of the total amount collected and the total discount given.

### Passenger Summary
The passenger summary should display the total number of passengers traveled per type in descending order of the passenger count. If any of the passenger types have the same value for the passenger count then display in the ascending order of the passenger type for that case. 
For example:


## Assumptions
- All passengers should have a MetroCard.
- If a passenger does not have sufficient balance in the MetroCard, then the MetroCard needs to be recharged before taking up the journey.
- The service fee for doing the recharge is collected by the origin station of the journey.
- The passenger count is calculated based on journeys. For example, if the same passenger travels twice, the count is 2.



# Pre-requisites
* Java 1.8/1.11/1.15
* Gradle 6

# How to run the code

Use `run.sh` if you are Linux/Unix/macOS Operating systems and `run.bat` if you are on Windows.

Internally both the scripts run the following commands 

 * `gradle clean build -x test --no-daemon` - This will create a jar file `geektrust.jar` in the `build/libs` folder.
 * `java -jar build/libs/geektrust.jar sample_input/input1.txt` - This will execute the jar file passing in the sample input file as the command line argument

 Use the build.gradle file provided along with this project. Please change the main class entry under the `jar` task

 ```
 manifest {
        attributes 'Main-Class' : 'com.geektrust.backend.App' //Change this to the main class of your program which will be executed
    }
```
in the build.gradle if your main class has changed.

 # How to execute the unit tests

 `gradle clean test --no-daemon` will execute the unit test cases.

# Help

* You can refer help documents [here](https://help.geektrust.com)
* You can read build instructions [here](https://github.com/geektrust/coding-problem-artefacts/tree/master/Java)
