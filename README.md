# SN sur la Gestion des PRODUITS – Microservices


## Question 5 : test integration

```bash
# 1. Créer un produit
curl -X POST http://localhost:8081/api/products \
  -H "Content-Type: application/json" \
  -d '{"id":1,"nom":"sac de riz","prix":1000,"quantiteStock":3}'

# 2. Créer une commande
curl -X POST http://localhost:8082/api/commandes \
  -H "Content-Type: application/json" \
  -d '{"id":1,"date":"2026-06-17","productId":1,"statut":EN_COURS}'

# 4. verifier le Stock
curl -X GET http://localhost:8081/api/products

```

## Questions 8 : Lancer et verifier 

```bash
# Builder et lancer
docker-compose up --build -d

# Tester que les 4 conteneurs tournent
curl http://localhost:8081/api/products
curl http://localhost:8082/api/commandes
curl http://localhost:8761
curl http://localhost:8080

#tester un endpoint
curl http://localhost:8080/api/products

# Nettoyer
docker-compose down -v
```



## Questions théoriques


### 1. Role de Eureka et du Gatewy dans notre architecture :
Eureka Server :
est Serveur de découverte de services qui permet de savoir quel microservice tourne sur quel port et de localiser dynamiquement un service à la demande.

API Gateway :
est Point d'entrée unique pour tous les clients, qui permet a tous les microservices de donner l'impression qu'il utilisent le même port réseau, alors qu'ils tournent sur des ports différents.


### 2. Avantages et Inconvenients des  microservices par rapport a un monolithe :
  - Avantages : 
Chaque microservice possède sa propre base de données, et couvre une focntionnalite; alors que dans le monolithique tous est melanger et on a une seule base de donnees

  - Inconvenients :
Dans le microservice la Complexité de developpement est eleves contrairement au monolithe.
De plus on a aussi la complexite dans la gestion des données car comme chaque service a sa propre base de donnee contrairement au  monolithe


### 3. Raison de l'utilisation de stripPrefix=1 dans le Gateway  :



### 4. Definition de l'autoconfiguration de SpringBoot + 1 exemple dans notre projet :
L'autoconfiguration de Spring Boot : est un mécanisme qui permet de configurer automatiquement l'application Spring en fonction des dépendances presentes.

Exemple dans pom.xml on a :

<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
### 5. Ce qui se passe quand un service produit tombe en panne est : 

Quand le service produit tombe en panne alors les appels du service commande vers produit échouent  et annulent les transactions JPA.





