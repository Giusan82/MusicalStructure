# MusicalStructure
App created for Udacity.com in the course: Android Basics by Google Nanodegree Program.

This app was made for the project Musical Structure App of the Android Basics: Multi-Screen App, Android Basics by Google Nanodegree Program. 
The goal of this project is to plain for building an app that has the structure of a music playing app to practice app design and the use of explicit intents to navigate around the app. But the making an app adding just the description on what I want create, was not satisfactory for me that I decided to implement a real Media Player that really work and list the songs in the folder of your device. 
The Player was builded using the Media Player Class that allows to play the song and add the control buttons. Also the MediaMetadataRetriever class is been implemented, for retrieving meta data from an input media file selected and give the song details. I decided to share it, for helping some student to implement an Media Player in Android.
Moreover, the list of file use a simply LinearLayout that generate dinamically the content, I know that is a not good choise, but at moment when I made it, I didn't know that the ListView with ArrayAdapeter was better. I thought however to not change it and leave the LinearLayout to show the content of your folder just for teaching that can be helpful at some student that want to learn to create views dinamically using java code. I invite you to take a look on this app and at give me your feedback. 


Here some screenshot:

<p align="center">
  <img src="https://raw.githubusercontent.com/Giusan82/MusicalStructure/master/Screenshot_2017-07-01-17-37-22.png" width="250"/>
  <img src="https://raw.githubusercontent.com/Giusan82/MusicalStructure/master/Screenshot_2017-07-01-17-37-44.png" width="250"/>
  <img src="https://raw.githubusercontent.com/Giusan82/MusicalStructure/master/Screenshot_2017-07-01-17-39-06.png" width="250"/>
</p>


The large TextView with description, was added because required from the project and the songs are not included.
