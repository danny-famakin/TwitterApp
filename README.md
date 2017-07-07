# Project 3 - *Twitter App*

The **Twitter App** is an android app that allows a user to view his Twitter timeline and post a new tweet. The app utilizes [Twitter REST API](https://dev.twitter.com/rest/public).

Time spent: **100** hours spent in total

## User Stories

The following **required** functionality is completed:

* [x]	User can **sign in to Twitter** using OAuth login
* [x]	User can **view tweets from their home timeline**
  * [x] User is displayed the username, name, and body for each tweet
  * [x] User is displayed the [relative timestamp](https://gist.github.com/nesquena/f786232f5ef72f6e10a7) for each tweet "8m", "7h"
* [x] User can **compose and post a new tweet**
  * [x] User can click a “Compose” icon in the Action Bar on the top right
  * [x] User can then enter a new tweet and post this to twitter
  * [x] User is taken back to home timeline with **new tweet visible** in timeline
  * [x] Newly created tweet should be manually inserted into the timeline and not rely on a full refresh
* [x] User can view home and mentions timelines
* [x] Users can view their personal profile and profiles of people they follow on clicking the profile image within a tweet

The following **optional** features are implemented:

* [x] User can **pull down to refresh tweets timeline**
* [x] User is using **"Twitter branded" colors and styles**
  * [x] User can **take favorite (and unfavorite) or reweet** actions on a tweet
  * [x] User can see embedded image media within the tweet item in list or detail view.
  * [x] User can **click a link within a tweet body** on tweet details view. The click will launch the web browser to view the tweet.

The following **bonus** features are implemented:


* [x] Use Parcelable instead of Serializable using the popular [Parceler library](http://guides.codepath.com/android/Using-Parceler).


## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='https://github.com/danny-famakin/TwitterApp/blob/master/Twitter_2.gif' title='Twitter App ' width='' alt='Video Walkthrough' />

GIF created with [ScreenRecorder].

## Notes

Improving the UI, specifically the tweet layout was a bit of a challenge.

## Open-source libraries used

- [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Android

## License

    Copyright [2017] [name of copyright owner]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.




  

    
  
  
