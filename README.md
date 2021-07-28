                                                                                                         BASIC WEATHER APP


Description of the App :

The Application displays  the current weather information i.e temp, humidity , with location name
Location is not taken dynamically it is hardcoded
Application updates the weather information in each 2 hours, no network is called is made with in 2 hours
Application uses openweathermap.org API to get the weather informations.

Improvements of Application or features:

Use cell network/WiFi/GPS to get the location info to show the weather where you are there.
Show proper dialog to user to enable location and grant permissions.
Update location or detect location movement and update weather data accordingly.
X days Forecast
Many location
Give user option to search for city/location
Show Notification when weather changes
Use widget , fragment, animation , etc to make the UI more interactive 
Show more weather info to make it more informative
Show a splash screen
Show menu item with info like About the app, Help , Settings etc.
Show proper error dialog when any error occurs
Support different measuring units
Support different languages

Design decision and improvements required :

Clean architecture with modularity : it has been divided into different modules i.e. ui, repo, data, utils etc. So this can be used in another white level app
MVVM Design Pattern : To reduce complexity, distribution of responsibility between classes
Dependency Injection : Have used dagger to provides dependency , it simplifies access to shared instances, easy configuration, easy integration & unit testing, separation of responsibilities, scoped instances helps in managing instances lifecycle
WorkManger for scheduling periodic task : it is easy to use and can be used to update data over network periodically
Retrofit : It is easy to use and all the required API, callback are already in place and saves developer time
Kotlin : Languages supported & preferred by google , makes development easier, it integration with android and IDE is better, safer than Java
Persistence data : I have used SharedPreferences here as the data was less, it is easy to use as (due to lack of more time preferred this)
Unit test case : it helps to make sure the uint of code is working as desired , quality of code, find any bug early, reduce costs.

Improvements/Todos :
Use RxKotlin/android etc while doing background operation and call back and observing data changes
Use Room for data persistence
Refactor to make the data source (online & offline) more seamlessly integrated with the code
Check on handling of config changes and life cycle handling.
Add more unit test cases for all required class
Add Logger for debugiing and (firebase) for monitoring crash or non-fatals
Use chekstyle, Lint etc for checking code is in proper format and it has no issues
Integrate leak cannery to find any memory issue
