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
<b>Hint:</b> The number of votes is displayed as a numeric field in JSON format. If the request doesn't contain a date as a parameter <br />
the response will contain the count of votes from the whole period or only for the date parameter <br />

# /rest/v1/profile/registration
<b>POST (consume and produce JSON)</b> - register new user.<br />

Consumed JSON example:<br />
{<br />
	"name": "new user",<br />
	"email": "mail@mail.ru",<br />
	"password": "password"<br />
}<br />

CURL example:<br />
curl -H 'Content-Type: application/json' -X POST -d @jsonfile.txt http://localhost:8080/rest/v1/profile/registration<br />

Produced JSON example:<br />
{
   "id": 100010,
   "name": "new user",
   "email": "mail@mail.ru",
   "password": "password",
   "enabled": true,
   "registered": 1480396232917
}

# /rest/v1/profile/vote
<b>POST (consume JSON)</b> - make vote for restaurant.<br />
Consumed JSON example:<br />
{<br />
	"date": "2016-11-27T11:50:00",<br />
	"restaurantId": 100100<br />
}<br />
CURL example:<br />
curl -H 'Content-Type: application/json' -X POST -d @jsonfile.txt --user User:password http://localhost:8080/rest/v1/profile/vote<br />

# /rest/v1/restaurants 
<b>GET</b> - gets all restaurants with menus and votes count for all time, or on date if use a date request parameter<br />
CURL example:<br />
curl --user User:password http://localhost:8080/rest/v1/restaurants<br />
or <br />
curl --user User:password http://localhost:8080/rest/v1/restaurants?date=2016-11-19<br />
Produced JSON example:<br />
 [<br />
       {<br />
       "id": 100100,<br />
       "name": "Pushkin",<br />
       "address": "Pushkin st. 53",<br />
       "contacts": "+7 937 777 777",<br />
       "menu":       [<br />
                   {<br />
             "id": 100002,<br />
             "name": "Pasta",<br />
             "price": 40.5<br />
          },<br />
                   {<br />
             "id": 100003,<br />
             "name": "Pizza",<br />
             "price": 10<br />
          },<br />
                   {<br />
             "id": 100004,<br />
             "name": "Steak",<br />
             "price": 70.3<br />
          }<br />
       ],<br />
       "votesCount": 3<br />
    },<br />
       {<br />
       "id": 100101,<br />
       "name": "Gogol",<br />
       "address": "Gogol st. 40",<br />
       "contacts": "+7 937 234 111",<br />
       "menu": [      {<br />
          "id": 100005,<br />
          "name": "Steak",<br />
          "price": 170.3<br />
       }],<br />
       "votesCount": 1<br />
    }<br />
 ]<br />

<b>POST</b> (consume JSON, produce JSON) - create a new restaurant and return it (Only ADMIN role)<br />
CURL example:<br />
curl -H 'Content-Type: application/json' -X POST -d @jsonfile.txt --user Admin:admin http://localhost:8080/rest/v1/restaurants<br />
Consumed JSON example:<br />
{<br />
      "name": "New",<br />
      "address": "New  st. 10",<br />
      "contacts": "+7 937 234 111"<br />
 }<br />
Produced JSON example:<br />
 {<br />
    "id": 100102,<br />
    "name": "New",<br />
    "address": "New  st. 10",<br />
    "contacts": "+7 937 234 111",<br />
    "menu": null,<br />
    "votesCount": 0<br />
 }<br />


# /rest/v1/restaurants/{id}<br />
<b>GET</b> - gets restaurant from id with menu and votes count for all time, or on date if use a date request parameter<br />
CURL example:<br />
curl --user User:password http://localhost:8080/rest/v1/restaurants/100100<br />
or <br />
curl --user User:password http://localhost:8080/rest/v1/restaurants/100100?date=2016-11-19<br />

<b>PUT</b> (consume json) - update a restaurant with id (Only ADMIN role)<br />
CURL example:<br />
curl -H 'Content-Type: application/json' -X PUT -d @jsonfile.txt --user Admin:admin http://localhost:8080/rest/v1/restaurants/100100<br />

# /rest/v1/restaurants/{id}/menu<br />
<b>GET</b> - gets menu from restaurant with id<br />
Produced JSON example:<br />
[<br />
   {<br />
      "id": 100002,<br />
      "name": "Pasta",<br />
      "price": 40.5<br />
   },<br />
      {<br />
      "id": 100003,<br />
      "name": "Pizza",<br />
      "price": 10<br />
   },<br />
      {
      "id": 100004,<br />
      "name": "Steak",<br />
      "price": 70.3<br />
   }<br />
]<br />

<b>POST</b> (consume JSON, produce JSON) - add a new dish to restaurant menu (Only ADMIN role)<br />
CURL example:<br />
curl -H 'Content-Type: application/json' -X POST -d @jsonfile.txt --user Admin:admin http://localhost:8080/rest/v1/restaurants/100100/menu<br />

Consumed JSON example:<br />
{<br />
      "name": "Soup",<br />
      "price": 20.7<br />
 }<br />
Produced JSON example:<br />
{
   "id": 100105,
   "name": "Soup",
   "price": 20.7
}
# /rest/v1/restaurants/{id}/menu/{dishId}<br />
<b>GET</b> - gets dish<br />
