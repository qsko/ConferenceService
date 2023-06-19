ENDPOINTS:

1. GET localhost:8080/users -> Get all users
2. POST localhost:8080/users/${login}/${email} -> Register new user
3. PUT localhost:8080/users/${login}/${newEmail} -> Update user email
4. GET localhost:8080/users/${login}/reservations -> Get lectures for which user is registered

5. POST localhost:8080/reservations/${login}/${email}/${lectureId} -> Make reservation for lecture 
6. DELETE localhost:8080/reservations/${login}/${lectureId} -> Delete reservation for lecture
7. GET localhost:8080/reservations/statistics/lectures -> get stats for conference for lectures 
8. GET localhost:8080/reservations/statistics/paths -> get stats for conference for paths

9. GET localhost:8080/conferencePlan -> get conference plan

Lectures are stored in database (can checked them in resources file in data.sql file) with ids={x}{y} where x denotes period, y denotes path 
for example ID=12 means lecture is being held in first period, and its second path

examples:
[POST] http://localhost:8080/users/login/email <br />
[PUT] localhost:8080/users/login/newEmail <br />
[GET] localhost:8080/users <br />
[POST] localhost:8080/reservations/login/newEmail/11 <br />
[GET] localhost:8080/users/login/reservations <br />

Means create user with login and email, chang user's email, list all user, make reservation for user,
list reservation for user.

Run project by navigating to root directory and run <br />
mvn spring-boot:run (alternatively you can run project in IDE by finding main class)