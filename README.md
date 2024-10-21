# Welcome to the Solar-Watch project repository!

Solar-Watch is an app that allows you to retrieve the time of sunrise and sunset based on date and location.

## Table of Contents
- [Overview](#overview)
- [Technologies](#technologies)
- [Getting Started](#getting-Started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Features](#features)
  - [Dockerization](#dockerization)
  - [User Management](#user-management)
  - [Frontend Integration](#frontend-integration)
- [Usage](#usage)

## Overview
Solar-Watch is an application where you can retrieve sunrise and sunset times based on date and location. Users can register and log in, manage, update and delete the retrieved cities. They can view all the times queried for a city in a table. This project demonstrates comprehensive full-stack development, including database management, RESTful API design, user authentication, and appealing UI development.

## Technologies
### Backend:
- [![spring-boot][spring-boot]][spring-boot-url]
- [![spring-web-mvc][spring-web-mvc]][spring-web-mvc-url]
- [![spring-data-jpa][spring-data-jpa]][spring-data-jpa-url]
- [![spring-security][spring-security]][spring-security-url]
- [![hibernate][hibernate]][hibernate-url]

### Database:
- [![postgresql][postgresql]][postgresql-url]

### Frontend:
- [![React-Vite][React-Vite]][Vite-URL]
- [![Css][Css3]][Css-url]

### Containerization:
- [![docker][docker]][docker-url]

## Getting Started

### Prerequisites

#### To Run the Project with Docker:
  - [![docker][docker]][docker-url]

#### To Run Backend and Frontend Separately:
  - [![java][java]][java-url]
  - [![maven][maven]][maven-url]
  - [![postgresql][postgresql]][postgresql-url]
  - [![nodejs][node.js]][node-url]
  - [![npm][npm]][npm-url]


### Installation

#### Running the Project with Docker
1. Clone the repository from GitHub into your desired folder:
   ```bash
    git clone git@github.com:szidzse/solar-watch.git

    # navigate into the project directory
    cd <foldername>
   ```
2. Build and start the containers:
   ```bash
   docker compose up --build
   ```

   *Docker will automatically set up the database and run the backend and frontend services.*


#### Running Backend and Frontend Separately

##### Backend Setup:

   - Navigate to the backend directory:
     ```bash
      cd backend
     ```

   - Build the project:
     ```bash
      mvn clean install
     ```

   - Set environment variables:
     - Update application.properties (src/main/resources/application.properties) with your database credentials and security data or you can use terminal commands:
      - Option 1: Using PowerShell
        ```bash
        $env:DATASOURCE_URL="jdbc:postgresql://localhost:5432/strings"
        $env:DATASOURCE_USERNAME="YOUR_DATABASE_USERNAME"
        $env:DATASOURCE_PASSWORD="YOUR_DATABASE_PASSWORD"
        $env:JWTSECRETKEY="YOUR_JWT_SECRET_KEY"
        $env:SUNRISE-SUNSET-APU-URL="https://api.sunrisesunset.io/json"
        $env:GEOCODING-API-URL="http://api.openweathermap.org/geo/1.0/direct"
        $env:GEOCODING-API-KEY="YOUR_GEOCODING_API_KEY"
        ```
      *The JWT secret key should be 64 characters long and should only include alphanumeric characters (A-Z, a-z, 0-9). It is advisable to avoid using special characters such as `-`, `/`, `+`, and `=` to prevent potential issues with encryption and encoding.*

      - Option 2: Using Command Prompt
        ```bash
        $env:DATASOURCE_URL="jdbc:postgresql://localhost:5432/strings"
        $env:DATASOURCE_USERNAME="YOUR_DATABASE_USERNAME"
        $env:DATASOURCE_PASSWORD="YOUR_DATABASE_PASSWORD"
        $env:JWTSECRETKEY="YOUR_JWT_SECRET_KEY"
        $env:SUNRISE-SUNSET-APU-URL="https://api.sunrisesunset.io/json"
        $env:GEOCODING-API-URL="http://api.openweathermap.org/geo/1.0/direct"
        $env:GEOCODING-API-KEY="YOUR_GEOCODING_API_KEY"
        ```
       *JWT Secret key should be 64 characters long.*

   - Run the application:
     ```bash
      mvn spring-boot:run
     ```

##### Frontend Setup:

   - Navigate to the frontend directory:
     ```bash
      cd ../frontend
     ```

   - Install dependencies:
     ```bash
      npm install
     ```

   - Start the development server:
       ```bash
        npm run dev
       ```

## Features
  ### Dockerization
  - The application is fully containerized using Docker.
  - Docker Compose manages multi-container setups, including the PostgreSQL database.
  - Running with Docker Compose eliminates the need to manually create the database or configure environment variables locally.

  ### User Management
  - User Registration and Authentication using Spring Security and JWT tokens.
  - Features include:
    - User Registration: Allows new users to sign up.
    - Authentication: Users log in to receive a JWT token.
    - Authorization: Secures endpoints to authenticated users.
    - Role-based Access Control: Differentiates between user and admin roles.

  ### Frontend Integration
  - Built with React-Vite for a responsive and dynamic user interface.
  - Features include:
    - Responsive design for various screen sizes.
    - RESTful API integration for smooth data exchange between frontend and backend.
    - Client-side routing with React Router for multiple pages.

## Usage
Using El Proyecte Grande:
  - Home Page:
    - Detail: Displays home page..
    - Visuals: ![home](https://github.com/user-attachments/assets/4286454a-1809-4f23-9884-03bba884ceb4)
  - Register an Account:
    - Detail: Sign up to create a new account.
    - Visual: ![register](https://github.com/user-attachments/assets/200f37fe-7e81-474f-8447-7cceaea56fe0)
  - Log In:
    - Detail: Use your credentials to log in.
    - Visual: ![login](https://github.com/user-attachments/assets/78193da3-f613-44ff-81b1-2c19f0274f9d)
  - Solar Times:
    - Detail: Get solar times based on date and location.
    - Visual: ![solar-times](https://github.com/user-attachments/assets/fd0fcc08-2d30-4f9b-94b9-19f04fd99ffe)
  - City Manager:
    - Detail: See the cities that are in the database.
    - Visuals: ![cities](https://github.com/user-attachments/assets/f72c8ce6-9de6-42f3-b9b0-0328efbb54f5)
  - Update city details:
    - Detail: Update city details.
    - Visual: ![city-update](https://github.com/user-attachments/assets/ee87da2c-144f-49a2-8e98-de89a25e4a62)
  - Solar Times Details:
    - Detail: All retrieved sunrise and sunset times for a city.
    - Visuals: ![solar-times-details](https://github.com/user-attachments/assets/4afc258b-01b4-4989-97f9-f86fd219bc73)


[React-Vite]: https://img.shields.io/badge/-Vite-D3D3D3?logo=Vite&logoColor=646CFF
[Vite-URL]: https://vitejs.dev/guide/

[Css3]: https://img.shields.io/badge/Css-4361ee?style=for-the-badge&logo=css&logoColor=61DAFB
[Css-url]: https://en.wikipedia.org/wiki/CSS

[docker]: https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white
[docker-url]: https://docs.docker.com/engine/install/

[spring-boot]: https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=Spring&logoColor=white
[spring-boot-url]: https://docs.spring.io/spring-boot/installing.html

[spring-web-mvc]: https://img.shields.io/badge/SPRING%20WEB%20MVC-6DB33F?style=for-the-badge&logo=Spring&logoColor=white
[spring-web-mvc-url]: https://docs.spring.io/spring-framework/reference/web/webmvc.html

[spring-data-jpa]: https://img.shields.io/badge/SPRING%20DATA%20JPA-6DB33F?style=for-the-badge&logo=Spring&logoColor=white
[spring-data-jpa-url]: https://spring.io/projects/spring-data-jpa

[spring-security]: https://img.shields.io/badge/Spring%20Security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white
[spring-security-url]: https://spring.io/projects/spring-security

[hibernate]: https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white
[hibernate-url]: https://hibernate.org/ 

[postgresql]: https://img.shields.io/badge/postgresql-4169e1?style=for-the-badge&logo=postgresql&logoColor=white
[postgresql-url]: https://www.postgresql.org/download/

[java]: https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&labelColor=ED8B00&logo=java&color=808080[Java
[java-url]: https://www.java.com/en/download/

[maven]: https://img.shields.io/badge/Maven-4%2B-ED8B00?style=for-the-badge&labelColor=ED8B00&logo=maven&color=808080[Maven
[maven-url]: https://maven.apache.org/

[node.js]: https://img.shields.io/badge/Node.js-339933?style=for-the-badge&logo=nodedotjs&logoColor=white
[node-url]: https://nodejs.org/en

[npm]: https://img.shields.io/badge/npm-CB3837?style=for-the-badge&logo=npm&logoColor=white
[npm-url]: https://www.npmjs.com/

[zsugonicsbrigitta]: https://img.shields.io/badge/Brigitta%20Zsugonics-181717?style=for-the-badge&logo=github&logoColor=white
[zsugonicsbrigitta-url]: https://github.com/zsbrigi

[fodoreszter]: https://img.shields.io/badge/Eszter%20Fodor-181717?style=for-the-badge&logo=github&logoColor=white
[fodoreszter-url]: https://github.com/eszti9902
