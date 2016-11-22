# Restaurants

Voting system for deciding where to have lunch.<br />

2 types of users: admin and regular users<br />
Admin can input a restaurant and it’s lunch menu of the day (2-5 items usually, just a dish name and price)<br />
Menu changes each day (admins do the updates)<br />
Users can vote on which restaurant they want to have lunch at<br />
Only one vote counted per user<br />
If user votes again the same day: <br />
If it is before 11:00 we asume that he changed his mind.<br />
If it is after 11:00 then it is too late, vote can’t be changed<br />
Each restaurant provides new menu each day.<br />

# Rest API:
# /rest/v1/restaurants 
GET - get all restaurants with menus and votes count for all time

POST (consum json) - create a new restaurant 
# /rest/v1/restaurants/{id}
GET - get restaurant from id with menu

PUT (consume json) - update a restaurant with id
# /rest/v1/restaurants/{id}/menu
GET - get menu from restaurant with id

POST (consume json) - add a new dish to restaurant menu
# /rest/v1/restaurants/{id}/menu/{dishId}
GET - get dish
