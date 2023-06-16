ENDPOINTS:
1. POST localhost:8080/users/${login}/${email} -> Register new user
2. GET localhost:8080/users/${login}/lectures -> Get lectures for which user is registered
3. POST localhost:8080/users/${login}/${email}/${lectureId} -> Make reservation for lecture
4. DELETE localhost:8080/users/${login}/lecture/${lectureId} -> Delete reservation for lecture
5. PUT localhost:8080/users/${login}/${newEmail} -> Update user email
6. GET localhost:8080/users -> Get all users
7. GET localhost:8080/conferencePlan -> get conference plan
8. GET localhost:8080/statistics -> get stats for conference 