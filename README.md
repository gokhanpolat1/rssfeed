# Rss Feed
This project can be used to read rss feed as JSON according to specified frequency.

### Installing

To install project download project as a zip and extract to under your project folder. 

After extracting folder run following command

```
gradlew clean build
```
or for mac users
```
./gradlew clean build
```

### Running

gradle bootrun is available for this project
```
gradlew bootRun
```
or
```
./gradlew bootRun
```
#### Runtime parameters
* Default rss feed url is [BBC](http://feeds.bbci.co.uk/news/world/rss.xml)
* Default rss feed refresh frequency is 60 seconds

If you want to change these values, run following commands;

changing refresh frequency
```
gradlew bootRun -Pargs=--rss.feed.refresh.frequency=<duration in seconds>
```
changing url
```
gradlew bootRun -Pargs=--rss.feed.url=<an xml url>
```
changing both refresh frequency and url
```
gradlew bootRun -Pargs=--rss.feed.refresh.frequency=<duration in seconds>,--rss.feed.url=<an xml url>
```

#### Result
When you run the application go to following link to see result
```
http://localhost:8080/index
```

#### Database
You could trace data by following link
```
http://localhost:8080/h2-console
```
