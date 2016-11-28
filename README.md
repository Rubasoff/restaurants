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

The number of votes is displayed as a numeric field in JSON format. If the request doesn't contain a date as a parameter <br />
the response will contain the count of votes from the whole period or only for the date parameter <br />

# Rest API:
# /rest/v1/restaurants 
GET - gets all restaurants with menus and votes count for all time
Example produces JSON:
 [
       {
       "id": 100100,
       "name": "Pushkin",
       "address": "Pushkin st. 53",
       "contacts": "+7 937 777 777",
       "menu":       [
                   {
             "id": 100002,
             "name": "Pasta",
             "price": 40.5
          },
                   {
             "id": 100003,
             "name": "Pizza",
             "price": 10
          },
                   {
             "id": 100004,
             "name": "Steak",
             "price": 70.3
          }
       ],
       "votesCount": 3
    },
       {
       "id": 100101,
       "name": "Gogol",
       "address": "Gogol st. 40",
       "contacts": "+7 937 234 111",
       "menu": [      {
          "id": 100005,
          "name": "Steak",
          "price": 170.3
       }],
       "votesCount": 1
    }
 ]

POST (consume json) - create a new restaurant and return it (Only ADMIN role)
Example consumed JSON:
{
      "name": "New",
      "address": "New  st. 10",
      "contacts": "+7 937 234 111"
 }
 Example produces JSON:
 {
    "id": 100102,
    "name": "New",
    "address": "New  st. 10",
    "contacts": "+7 937 234 111",
    "menu": null,
    "votesCount": 0
 }

# /rest/v1/restaurants/{id}
GET - gets restaurant from id with menu
PUT (consume json) - update a restaurant with id (Only ADMIN role)

# /rest/v1/restaurants/{id}/menu
GET - gets menu from restaurant with id
[
   {
      "id": 100002,
      "name": "Pasta",
      "price": 40.5
   },
      {
      "id": 100003,
      "name": "Pizza",
      "price": 10
   },
      {
      "id": 100004,
      "name": "Steak",
      "price": 70.3
   }
]

POST (consume json) - add a new dish to restaurant menu (Only ADMIN role)
Example consumed JSON:
{
      "name": "Soup",
      "price": 10.5
 }

# /rest/v1/restaurants/{id}/menu/{dishId}
GET - get dish
