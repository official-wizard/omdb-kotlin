
<p align="center" dir="auto">
    <a href="https://omdbapi.com" rel="nofollow">
        <img src="https://i.imgur.com/2oq2xSh.png" width="200" alt="OMDb API Icon" style="max-width: 100%;"/>
    </a>
</p>

<h1 align="center"><a href="https://omdbapi.com">OMDbAPI.com</a> Client for Kotlin</h1>

## Installation

To begin, import the library using jitpack.io.

You can include jitpack in your `pom.xml` by adding the following jitpack repository:

```xml
<repository>
    <id>jitpack.io</id>
    <url>https://www.jitpack.io</url>
</repository>
```

Then add this `omdb-kotlin` dependency to your `pom.xml` project!

```xml
<dependency>    
    <groupId>com.github.official-wizard</groupId>    
    <artifactId>omdb-kotlin</artifactId>    
    <version>1.0.0</version>
</dependency>
```

## Usage

### Basic Usage

```kotlin
val credentials = Credentials("<web api key>")
val api: OmdbInterface = OmdbClient(credentials).api

// access the api interface in `api`
```

#### Search

<details>
<summary>Search Title (or imdb ID)</summary>
<br>

> A call to this function will retrieve a single result for the provided title or Imdb ID

**Available Parameters**

> NOTE: Although `Title` and `ImdbId` are optional, at least 1 of them is required!

| Name          | Type      | Description                                                        | Example         | OPTIONAL |
|:--------------|:----------|:-------------------------------------------------------------------|:----------------|----------|
| title         | String    | The name of the film you'd like to search for.                     | Demons          | yes      |
| imdbId        | String    | The imdb ID for the film you'd like to search for.                 | tt0089013       | yes      |
| type          | QueryType | The type of film you'd like to search for (Movie, Series, Episode) | QueryType.movie | yes      |
| yearOfRelease | String    | The year the film was released                                     | 1985            | yes      |
| plot          | Plot      | Short or full plot.                                                | Plot.short      | yes      |

**Example**
```kotlin
val credentials = Credentials("<web api key>")
val api: OmdbInterface = OmdbClient(credentials).api

val response: NetworkResponse<Search.Response, Error.Response> = api.searchTitle(
    title = "Demons"
)

if (response is NetworkResponse.Success) {
    // handle the data
    val searchResult: Search.Response = response.body

} else if (response is NetworkResponse.Error) {
    // if the server returns an error it be found here
    val errorResponse: Error.Response? = response.body

    // if the api (locally) had an internal error, it'll be found here
    val internalError: Throwable? = response.error
}
```

</details>

<details>
<summary>Search List</summary>
<br>

> A call to this function will retrieve a list of results for the provided title

**Available Parameters**

| Name          | Type      | Description                                                        | Example         | OPTIONAL |
|:--------------|:----------|:-------------------------------------------------------------------|:----------------|----------|
| title         | String    | The name of the film you'd like to search for.                     | Demons          | no       |
| type          | QueryType | The type of film you'd like to search for (Movie, Series, Episode) | QueryType.movie | yes      |
| yearOfRelease | String    | The year the film was released                                     | 1985            | yes      |
| page          | Int       | The page number to search through.                                 | Plot.short      | yes      |

**Example**
```kotlin
val credentials = Credentials("<web api key>")
val api: OmdbInterface = OmdbClient(credentials).api

val response: NetworkResponse<SearchList.Response, Error.Response> = api.searchList(
    title = "Demons"
)

if (response is NetworkResponse.Success) {
    // handle the data
    val searchResult: SearchList.Response = response.body

} else if (response is NetworkResponse.Error) {
    // if the server returns an error it be found here
    val errorResponse: Error.Response? = response.body

    // if the api (locally) had an internal error, it'll be found here
    val internalError: Throwable? = response.error
}
```

</details>
