amazon-review-analyser
=======================

A Java crawler to collect and analyse product reviews on Amazon.com.

Updated: 2016-01-29

# Quick Start Guide

## XYZ
Description
```java
	Main.CM.crawl("B00NMJJXU4");
```

# Common Exceptions
1. java.io.IOException means that the item no longer exist on Amazon.com. You do not have to do anything with that item.
2. java.net.SocketTimeoutException means that connection to the website is taking too long. Rerun the crawler on the items with this exception.

# Licence
The code is released into public domain.

# Sources
"Market Dynamics and User-Generated Content about Tablet Computers" by Xin (Shane) Wang, Feng Mai and Roger H.L. Chiang, Marketing Science 33.3 (2014): 449-458
