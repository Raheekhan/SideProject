# SideProject

Just a simple project using a Page Factory approach in a Hybrid Framework fashion.

Recently added Driver Factory to, the CommonAPI Class was getting clogged up, so
I decided to clean it up and moving helper Methods to separate classes.

I have added Data Driven using [JXL](http://jexcelapi.sourceforge.net/), al though I have plans to add
[Apache POI](https://poi.apache.org/) to replace it.

I am also playing around with catching HTTP Request from the browser in order to convert
it into a .JMX file to be used in JMeter for Performance Testing.
I have experimented with [BrowserMob Proxy](https://bmp.lightbody.net/), however it drastically slows down
the performance of the Test Cases, rather failing them, even though they should be
bullet proof.

I've read that the same thing could be done in a simpler way with [PhantomJS](http://phantomjs.org/) Headless Browser.
But on recent announcements from Selenium HQ, the support is no longer there as Chrome have
added headless options on their own.

This project is nothing but practice.