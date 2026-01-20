# Getting Started Docker-Mysql-sprinboot

### 1. Docker Mysql setup
* Install Mysql in Docker : ``` docker pull mysql ```
* Create Docker Network for connectiong with application to DB : 
```bash
docker network create springboot-mysql-network 
```
* Check Network list : ``` docker network ls ```
* Run MySQL:
```bash
docker run  -p 3306:3306 --name mysqldb --network springboot-mysql-network -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=employee-department -d mysql
```

* Check Mysql running Container : ``` docker ps ```
* Mysql Bash Execute : ``` docker exec -it <mysql-container-id> bash ```
* Connect Mysql from Bash : ``` mysql -u root -p ```
* Show databases: ``` show databases ```
* It will show out database : **employee-department**

### 2. Docker Mysql setup
* Create docker profile.
* Create Dockerfile.
* Build image :
```bash
docker build -t emp-dept-management .
```
* Check build image : ``` docker images ```
* Check docker containers : ``` docker ps ```
* Check docker network : ```  docker network ls ```
* Run docker image :
```bash
 docker run --network springboot-mysql-network --name springboot-mysql-employee-department-container -p 8080:8080 emp-dept-management:latest
```
** Note: Environment involved,
    1. Network name : springboot-mysql-network
    2. Container Name (Going to create): springboot-mysql-employee-department-container
    3. Image Name : emp-dept-management:latest

* Ctrl + C : Stop service.
* Restart image : ``` docker run -p 8080:8080 emp-dept-management ```
* Run container in Detached-mode (-d) (in background).
```bash
docker run -p 8080:8080 -d emp-dept-management 
```

* Check API call from local system.

* Docker container check : ``` docker ps ```
* Docker container stop : ``` docker stop <container-id>```

