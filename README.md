# Project : 20210317-HomanHuang-NYCSchools

## Api Information
Display Schools Info from NYC

Data from : https://data.cityofnewyork.us/Education/2017-DOE-High-School-Directory/s3k6-pzi2
Description: 2017 DOE High School DirectoryEducation

Addition SAT Scores from: https://data.cityofnewyork.us/Education/SAT-Results/f9bf-2cp4
Description: 2012 SAT Results of New York City 

Authentication: Access API by app token


## Project Folder Structure

app : application

data : data section of MVVM
data/entity: POJO+Entity of database
data/room: Room database and repository
data/room/dao: function with SQL queries

di: modules of dependency injection for Dagger Hilt

helper: constants and shared functions

network: network api call

ui/main: MainActivity
ui/main/adapter: listener and adapter functions



