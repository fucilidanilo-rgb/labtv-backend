
# 🎬 LABTV — Backend Spring Boot

Backend REST API del progetto **LABTV**, una piattaforma cinematografica Full Stack sviluppata con Angular, Java, Spring Boot e MySQL.

Il backend gestisce l'autenticazione degli utenti, la sicurezza delle richieste e le operazioni di acquisto e restituzione simulate dei film.

Il progetto è stato realizzato come portfolio per dimostrare competenze nello sviluppo backend con Java e nell'integrazione tra frontend, API REST e database.

## 🌐 Demo online

**Applicazione LABTV:**

https://labtv-omega.vercel.app/

**Backend ospitato su Render:**

https://labtv-backend.onrender.com

**Repository frontend:**

https://github.com/fucilidanilo-rgb/labtv

### Credenziali demo

- Email: `demo@labtv.example`
- Password: `LabTV-Demo-2026!`

La registrazione pubblica è disabilitata nella versione dimostrativa.

Gli acquisti sono simulati e i dati dell'account demo sono condivisi tra i visitatori.

## ⏳ Nota importante su Render Free

Il backend è distribuito su Render Free.

Dopo un periodo di inattività, Render può sospendere temporaneamente il servizio.

La prima richiesta API o il primo login potrebbero quindi richiedere un minuto o più per essere completati.

Le richieste successive sono generalmente più veloci.

Questo comportamento dipende dai limiti del piano di hosting gratuito.

## 🛠️ Tecnologie utilizzate

- Java 21
- Spring Boot 4.1.1
- Spring Web / REST API
- Spring Security
- JWT (JSON Web Token)
- BCrypt
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Docker
- Render Free
- Aiven MySQL
- Git e GitHub

## 🏗️ Architettura Full Stack

LABTV utilizza un'architettura a livelli.

```text
         UTENTE
            |
            v
      ANGULAR 21
        Vercel
            |
            | HTTP / REST
            | Token JWT
            v
    SPRING BOOT 4.1.1
         Render
            |
            | Controller
            | Service
            | Repository
            |
            | JPA / Hibernate
            v
          MYSQL
          Aiven
```

Il frontend Angular gestisce l'interfaccia utente.

Il backend Spring Boot espone API REST per le operazioni applicative.

Il database MySQL memorizza le informazioni degli utenti e degli acquisti.

## 📂 Organizzazione del backend

Il progetto utilizza un'architettura basata su tre livelli principali:

### Controller

Gestiscono le richieste HTTP in ingresso e restituiscono le risposte delle API REST.

### Service

Contengono la logica applicativa, inclusa la gestione delle operazioni sugli acquisti.

### Repository

Gestiscono l'accesso al database attraverso Spring Data JPA.

Questa separazione migliora l'organizzazione, la leggibilità e la manutenzione del codice.

## 🔐 Sicurezza e autenticazione

Il backend utilizza Spring Security per la gestione dell'autenticazione e della protezione delle risorse.

### JWT

Dopo un login riuscito, il backend genera un token JWT utilizzato dal frontend per autenticare le richieste successive.

Le richieste autenticate utilizzano generalmente l'header:

```http
Authorization: Bearer <token>
```

### BCrypt

Le password vengono memorizzate tramite hashing BCrypt, evitando il salvataggio in chiaro nel database.

### Registrazione

La funzionalità di registrazione è implementata nel backend, ma l'accesso pubblico è disabilitato nella demo.

## 🎞️ Gestione degli acquisti

Il backend permette di:

- Recuperare i film acquistati.
- Registrare un acquisto simulato.
- Restituire un film precedentemente acquistato.
- Persistire e aggiornare i dati nel database.

Le operazioni vengono effettuate tramite API REST.

Non sono previsti pagamenti reali.

## 🌐 API REST

Le principali aree funzionali delle API sono:

| Area | Operazione | Metodo HTTP |
|------|------------|-------------|
| Autenticazione | Login utente | POST |
| Autenticazione | Registrazione (non pubblica) | POST |
| Acquisti | Elenco acquisti | GET |
| Acquisti | Nuovo acquisto simulato | POST |
| Acquisti | Restituzione film | DELETE |

Gli endpoint effettivi sono definiti nei Controller Spring Boot.

## 🗄️ Database MySQL

LABTV utilizza MySQL ospitato su Aiven Free.

Database:

`labtv_db`

Le principali tabelle applicative sono:

- `utenti`
- `acquisti`

La persistenza è gestita tramite Spring Data JPA e Hibernate.

Le credenziali del database non devono essere pubblicate nel repository.

## 🐳 Docker

Il backend include un Dockerfile per la distribuzione dell'applicazione.

Docker permette di creare un'immagine del backend ed eseguirla in un ambiente containerizzato.

Il deploy online viene gestito tramite Render.

## 💻 Avvio locale

### Requisiti

- Java 21
- Maven oppure Maven Wrapper
- MySQL
- Configurazione delle variabili d'ambiente necessarie

### 1. Clonare il repository

```bash
git clone https://github.com/fucilidanilo-rgb/labtv-backend.git
```

### 2. Entrare nella cartella

```bash
cd labtv-backend
```

### 3. Configurare il database

Creare o utilizzare un database MySQL compatibile con la configurazione applicativa.

Configurare la connessione e le credenziali attraverso le proprietà applicative e le variabili d'ambiente previste dal progetto.

Per l'autenticazione JWT è necessaria anche la relativa configurazione di sicurezza.

### 4. Avviare il backend

Su Windows:

```cmd
mvnw.cmd spring-boot:run
```

Su macOS/Linux:

```bash
./mvnw spring-boot:run
```

La porta locale dipende dalla configurazione Spring Boot.

Per il frontend LABTV, la configurazione locale prevista è:

```text
http://localhost:8080/api
```

## 🚀 Deployment

L'applicazione è distribuita utilizzando:

| Componente | Hosting |
|------------|---------|
| Frontend Angular | Vercel |
| Backend Spring Boot | Render |
| Database MySQL | Aiven |

Il backend viene pubblicato tramite Docker.

La configurazione di produzione utilizza le impostazioni necessarie per collegare Spring Boot al database MySQL remoto.

## 🎯 Obiettivi del progetto

LABTV è stato realizzato per consolidare competenze in:

- Programmazione Java.
- Sviluppo di API REST.
- Spring Boot e Spring Security.
- Autenticazione JWT.
- Hashing delle password.
- Architettura Controller–Service–Repository.
- Persistenza con JPA/Hibernate.
- Integrazione con MySQL.
- Containerizzazione Docker.
- Deploy di applicazioni backend nel cloud.
- Integrazione tra frontend Angular e backend Java.

## 👨‍💻 Autore

**Danilo Fucili**

Junior Full Stack Developer

GitHub: https://github.com/fucilidanilo-rgb
