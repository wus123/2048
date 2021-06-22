## 2048 Web API

2048 game engine written in Java with Spring and SpringBoot frameworks, accessible via a simple Web API

See it live on http://alanrusnak.com/2048

### Deploying
```
mvn package
java -jar target/2048.jar
```
and the app should be running on localhost:8080/2048/

### API
Create a new game:
```
http://localhost:8080/2048/new/
```
Get game state:
```
http://localhost:8080/2048/{game-id}/
```

Make a move:
```
http://localhost:8080/2048/{game-id}/0   - to move up
http://localhost:8080/2048/{game-id}/1   - to move right
http://localhost:8080/2048/{game-id}/2   - to move down
http://localhost:8080/2048/{game-id}/3   - to move left
```
All requests result in a JSON response in this format:
```
{ 
"id": "3943a369-31a8-4b15-92b0-744660deab64", 
"score": 4, 
"board": { 
          "tiles": [ [ 0, 0, 0, 0 ], [ 0, 0, 2, 0 ], [ 0, 0, 0, 0 ], [ 0, 4, 0, 0 ] ] 
          }, 
"gameOver": false 
}
```
###test 01 asdasdasdasd


# 2048
