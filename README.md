[amazon-review-analyser](https://github.com/kev007/amazon-review-analyser)
=======================

## TODO

* Schedule
    * Sonntag selbstarbeit
    * montag treffen
* Code
* Presentation
* Licence
* README.md - [markdown help](https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet)

### Code
1. Front End: GUI
    1. 3 Pane Layout  - See [Resources/GUI_Design1.jpg](https://github.com/kev007/amazon-review-analyser/blob/master/Resources/GUI_Design1.jpg?raw=true)
        1. ListView (alles)
        2. Darstellung Produktdetails
        3. Darstellung bewertung
            1. analyse positiv/negativ
            2. analyse other interesting data
    2. Events Listener
2. Back End
    1. Crawler
        1. Event Trigger (Progress)
        2. any Amazon.com URL
    2. Incorrect ASIN GET filtering (remove bad crawled data)
3. Other
    1. Code Comments
    2. Code Documentation
    3. UML Diagramm
    4. Refactor Crawler

### Presentation
1. Resources/GUI_Design1.jpg
    1. Basis idee für JavaFX GUI
2. Crawler resultate
    1. SQLite Database
    2. Database struktur
3. Programmstruktur
    1. Objekts (crawler/Item sehr wichtig)
4.
 
---
---
---
## README

A Java crawler to collect and analyse product reviews on Amazon.com.

Updated: 2016-01-29

## Quick Start Guide

### XYZ
Description
```java
	Main.CM.crawl("B00NMJJXU4");
```

## Common Exceptions
1. java.io.IOException means that the item no longer exist on Amazon.com. You do not have to do anything with that item.
2. java.net.SocketTimeoutException means that connection to the website is taking too long. Rerun the crawler on the items with this exception.

## Licence
The code is released into public domain.

## Sources
* "Market Dynamics and User-Generated Content about Tablet Computers" by Xin (Shane) Wang, Feng Mai and Roger H.L. Chiang, Marketing Science 33.3 (2014): 449-458
    [customer-review-crawler](https://github.com/maifeng/customer-review-crawler)
* 
* 
* 

## Links
* http://stackoverflow.com/a/9451851
* 
* 
* 