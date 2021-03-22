# Project : 20210317-HomanHuang-NYCSchools

## Api Information
Display Schools Info from NYC

Data from : https://data.cityofnewyork.us/Education/2017-DOE-High-School-Directory/s3k6-pzi2
Description: 2017 DOE High School DirectoryEducation

Addition SAT Scores from: https://data.cityofnewyork.us/Education/SAT-Results/f9bf-2cp4
Description: 2012 SAT Results of New York City 

Authentication: Access API by app token

## Update
03/22/2021: Add AndroidTest --> SchoolDaoTest, ScoreDaoTest, SchoolDatabaseTest
            Upgrade Database Version 2: School Table add up to 11 columns

03/21/2021: Database Version 1: School Table with 3 columns
                                Scores Table with 6 columns
03/20/2021: Add Score UI

03/19/2021: Remove Data binding for slow click in RecyclerView.

03/18/2021: Setup Retrofit connection; add School List UI

03/17/2021: Build project with MVVM, Hilt, Room, ViewPapers


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



