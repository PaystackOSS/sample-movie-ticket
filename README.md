# sample-movie-ticket
A simple application that shows how to integrate Paystack into your Android app.

<img src="./sample_movie_ticket.gif" width="360" height="640"/>


## Project setup
This project makes use of the [MovieDB API](https://developers.themoviedb.org/4/getting-started) to fetch movies. Here are the steps for setting up the project
- Create an account on MovieDB. You need an API key to make requests to MovieDB
- Clone this project and open in Android Studio
- Open the `gradle.properties` file and add the following params:
  - MOVIE_DB_API_KEY="your_movie_db_api_key"
  - PSTK_PUBLIC_KEY="your_paystack_public_key"
- Sync project
- Run project when sync is successful

## Code Structure
The project made use of the default Android architecture and the Java programming language.
The project package consists of the following directories:
- adapter: This contains custom adapters for the recycler views
- model: This contains the custom types for data returned from network calls
- network: This contains the network configurations and API endpoints
- utils: This holds classes that are used in multiple places
- view: This holds all the activities code. There are four activities used in this project
