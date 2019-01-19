# urlshortener
**urlshortener** is a simple Spring Boot app that receives a url via an api enpoint and 
returns a shortened url that redirects to the original url

To use this project simply clone the repository with command line - 

`$ git clone https://github.com/DarkSeidNG/urlshortener.git` 

Open the project on Intellij IDEA or use your favourite IDE, and synchronize gradle build.

**Tools Used**
- 
- Intellij IDEA - IDE
- Spring Boot - Framework
- Spring Data Jpa - Persistence
- Docker - Wrapper
- Apache Commons Validator - Url Validation

Directory Structure
-

![Alt text](/structure.png?raw=true "Directory Structure")

Usage
-
To use this app simply run the USApplication class on Intellij IDEA and wait for the project to build, 
after building it'll be deployed on localhost with port 8080, the base url will be `http://localhost:8080`.

To shorten a url make a post request with the url you want to shorten
to `http://127.0.0.1:8080/api/shortenUrl`, in the post body use the key `url` to specify the url to be shortened.

Sample Requests 

```http request
**HTTP**

POST /api/shortenUrl?= HTTP/1.1
Host: 127.0.0.1:8080
Content-Type: application/x-www-form-urlencoded
cache-control: no-cache
url=http%3A%2F%2Ffacebook.com%2Fdarkseidng
```

```curl
**CURL**

curl -X POST \
  'http://127.0.0.1:8080/api/shortenUrl?=' \
  -H 'Content-Type: application/x-www-form-urlencoded' \
  -H 'cache-control: no-cache' \
  -d 'url=http%3A%2F%2Ffacebook.com%2Fdarkseidng'
```

```java
**Java**

OkHttpClient client = new OkHttpClient();

MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
RequestBody body = RequestBody.create(mediaType, "url=http%3A%2F%2Ffacebook.com%2Fdarkseidng&undefined=");
Request request = new Request.Builder()
  .url("http://127.0.0.1:8080/api/shortenUrl?=")
  .post(body)
  .addHeader("Content-Type", "application/x-www-form-urlencoded")
  .addHeader("cache-control", "no-cache")
  .addHeader("Postman-Token", "72f033ed-e938-4e45-9f8e-7a07e4d186b0")
  .build();

Response response = client.newCall(request).execute();
```
 
 
 ```php
 **PHP**
 
<?php

$curl = curl_init();

curl_setopt_array($curl, array(
  CURLOPT_PORT => "8080",
  CURLOPT_URL => "http://127.0.0.1:8080/api/shortenUrl",
  CURLOPT_RETURNTRANSFER => true,
  CURLOPT_ENCODING => "",
  CURLOPT_MAXREDIRS => 10,
  CURLOPT_TIMEOUT => 30,
  CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
  CURLOPT_CUSTOMREQUEST => "POST",
  CURLOPT_POSTFIELDS => "url=http%3A%2F%2Ffacebook.com%2Fdarkseidng",
  CURLOPT_HTTPHEADER => array(
    "Content-Type: application/x-www-form-urlencoded",
    "cache-control: no-cache"
  ),
));

$response = curl_exec($curl);
$err = curl_error($curl);

curl_close($curl);

if ($err) {
  echo "cURL Error #:" . $err;
} else {
  echo $response;
}
```

To run the project from the docker image on your local system run 

`$ docker run -p 8080:8080 -t darkseidng/urlshortener` 


What's going on?
-
The app controllers are in the controllers directory, 
and there are two controllers in that directory.

**MainController.java** - This controller handles two api calls
 `/api/shortenUrl` (takes url post field, validates the field, generates a 5 character url key and 
 stores it in the database with the original url) 
 and `/api/gatherStatistics` (get request - returns the total number of saved urls and a list of all saved urls)
 
 **RedirectController** - This controller handles one major endpoint `/urlKey` and this is the shortened url endpoint.
 When the user visits the shortened url, the method extracts the url key and searches the database for this key 
 and if it is found redirects the user to the original url using RedirectView.
 
 Some of the other important classes include - 
 
 **UrlDataRepository** - The persistence repository
 
 **UrlData** - A Jpa @Entity for storing Url records.
 
 **UrlHelper** - Contains a method for generating the RedirectView for redirecting urls, a method for generating
  random 5 character alphanumeric urlkeys, a method for fetching the base url of the app.
  
 @Tests
 -
 Test classes have been created for testing certain parts of the app and can be found in the test directory. 
 
 ![Alt text](/tests.png?raw=true "Directory Structure")